package com.fun.committee.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by harshams on 27/06/2020
 */
@JsonSerialize
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AnswerAttempt {

    private String type = "answerAttempt";

    private String username;

    private Long guessId;

    private String status;

    private String answer;

    private Long retriesLeft;
}
