package com.fun.committee.service.interfaces;

import com.fun.committee.model.Role;
import com.fun.committee.model.json.User;
import com.fun.committee.model.sql.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by harshams on 24/06/2020
 */
public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserEntity findUserByUsername(String username);

    void addUser(User user)throws Exception;

    void updateUser(UserEntity userEntity);

}
