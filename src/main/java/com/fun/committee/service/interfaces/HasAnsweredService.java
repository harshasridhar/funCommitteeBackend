package com.fun.committee.service.interfaces;

import com.fun.committee.model.json.Answers;
import com.fun.committee.model.json.AnswersList;
import com.fun.committee.model.json.QuestionIdAnswer;

import java.util.List;

/**
 * Created by harshams on 26/06/2020
 */
public interface HasAnsweredService {

    public void submitQuestionaire(Answers answers)throws Exception;

    public AnswersList getAnswers(String username)throws Exception;

}
