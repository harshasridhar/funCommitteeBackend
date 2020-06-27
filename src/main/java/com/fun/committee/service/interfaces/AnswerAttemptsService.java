package com.fun.committee.service.interfaces;

import com.fun.committee.model.json.AnswerAttempt;

/**
 * Created by harshams on 27/06/2020
 */
public interface AnswerAttemptsService {

    public AnswerAttempt validateAttempt(AnswerAttempt answerAttempt)throws Exception;
}
