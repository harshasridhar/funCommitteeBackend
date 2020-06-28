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

    UserEntity getById(Long id);

    UserEntity findByUsernameAndPassword(String username, String password);

    @Query("select id from UserEntity where id <> :userId and role <> 'ADMIN'")
    List<Long> getOtherUserIds(@Param("userId")Long userId);

    @Query(" from UserEntity where username <> :username and role <> 'ADMIN' order by username")
    List<UserEntity> getOtherUsers(@Param("username")String username);
}
