package com.fun.committee.model.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by harshams on 24/06/2020
 */
@Entity
@Table(name = "user")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserEntity extends SecurityProperties.User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private String token;

    @Column(name = "token_expiry_time")
    private Timestamp tokenExpiryTime;
}
