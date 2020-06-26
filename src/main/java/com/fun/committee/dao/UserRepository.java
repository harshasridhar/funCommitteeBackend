package com.fun.committee.dao;

import com.fun.committee.model.sql.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by harshams on 24/06/2020
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);

    @Query("select id from UserEntity where id <> :userId")
    List<Long> getOtherUserIds(@Param("userId")Long userId);
}
