package com.fun.committee.model.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fun.committee.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by harshams on 24/06/2020
 */
@JsonSerialize
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {

    private String type = "user";

    private String username;

    private String password;

    private String name;

    private Role role;

}
