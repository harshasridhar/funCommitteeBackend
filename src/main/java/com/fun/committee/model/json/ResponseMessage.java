package com.fun.committee.model.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by harshams on 24/06/2020
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ResponseMessage {

    private String type = "responseMessage";

    private String message;
}
