package com.fun.committee.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun.committee.model.json.User;
import com.fun.committee.model.sql.UserEntity;
import com.fun.committee.service.implementation.UserDetailsServiceImpl;
import com.fun.committee.service.interfaces.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by harshams on 21/04/2020
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authManager;
    private JwtConfig jwtConfig;

    UserService userService;

    private Logger logger = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig, ApplicationContext context) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;
        this.userService = context.getBean(UserService.class);

        // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
        // In our case, we use "/auth". So, we need to override the defaults.
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            logger.info("Attempting Authentication");
            // 1. Get credentials from request
            User creds = new ObjectMapper().readValue(request.getInputStream(), User.class);

            // 2. Create auth object (contains credentials) which will be used by auth manager
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getUsername(), creds.getPassword(), Collections.emptyList());

            // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
            return authManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException{
        logger.info("Authentication Successful!");
        UserEntity user = userService.findUserByUsername(auth.getName());
        String token = user.getToken();
        Timestamp tokenExpiryTime = user.getTokenExpiryTime();
        Long now = System.currentTimeMillis();

        if(tokenExpiryTime != null){
            logger.info("Token exists");
            if(tokenExpiryTime.getTime() > now){
                logger.info("Token valid");
                response.addHeader(jwtConfig.getHeader(),jwtConfig.getPrefix() +token);
                return;
            }
        }
        logger.info("Token does not exist/ not valid!");
        Date tokenExpiration = new Date(now + jwtConfig.getExpiration() * 1000);
        token = Jwts.builder()
                .setSubject(auth.getName())
                // Convert to list of strings.
                // This is important because it affects the way we get them back in the Gateway.
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(tokenExpiration)  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
        user.setToken(token);
        user.setTokenExpiryTime(new Timestamp(tokenExpiration.getTime()));
        userService.updateUser(user);
        logger.info("Updated user data with new token");
        // Add token to header
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        logger.info("Authentication Failed due to exception: {}",failed.getMessage());
    }
}
