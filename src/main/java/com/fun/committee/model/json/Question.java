package com.fun.committee.model.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fun.committee.model.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by harshams on 25/06/2020
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Question {

    private String type = "question";

    private Long id;

    private String question;

    private QuestionType questionType;

    private String tag;

    private List<String> options;

    private String answer;

    private byte hasOtherOption;


}
