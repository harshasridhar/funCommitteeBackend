package com.fun.committee.model.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by harshams on 26/06/2020
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuestionIdAnswer {

    private String type = "questionIdAnswer";

    private Long questionId;

    private String answer;

    private String tag;
}
