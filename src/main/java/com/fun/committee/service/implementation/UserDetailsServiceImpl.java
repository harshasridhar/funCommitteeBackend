package com.fun.committee.service.implementation;

import com.fun.committee.dao.UserRepository;
import com.fun.committee.model.Role;
import com.fun.committee.model.json.User;
import com.fun.committee.model.json.UserList;
import com.fun.committee.model.sql.UserEntity;
import com.fun.committee.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshams on 24/06/2020
 */
@Service
public class UserDetailsServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    private BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user !=null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            String[] authStrings = user.getRole().split(",");
            for(String authString : authStrings) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+authString));
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }



    @Override
    public void addUser(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(bcryptEncoder.encode(user.getPassword()));
        userEntity.setRole(user.getRole().name());
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserList getUsers(String username){
        List<UserEntity> userEntities = userRepository.getOtherUsers(username);
        List<User> users = new ArrayList<>();
        for(UserEntity userEntity: userEntities){
            User user = new User();
            user.setUsername(userEntity.getUsername());
            user.setName(userEntity.getName());
            users.add(user);
        }
        UserList userList = new UserList();
        userList.setList(users);
        return userList;
    }
}
