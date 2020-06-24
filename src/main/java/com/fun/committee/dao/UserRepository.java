package com.fun.committee.dao;

import com.fun.committee.model.sql.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by harshams on 24/06/2020
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);
}
