package com.fun.committee.model.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fun.committee.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by harshams on 25/06/2020
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorMessage {

    private Long errorId;

    private ErrorCode errorCode;

    private String message;

}
