package com.fun.committee.service.interfaces;

import com.fun.committee.model.json.Answers;

/**
 * Created by harshams on 26/06/2020
 */
public interface HasAnsweredService {

    public void submitQuestionaire(Answers answers)throws Exception;
}
