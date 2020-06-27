package com.fun.committee.service.interfaces;

import com.fun.committee.model.sql.GameCompletionStatusEntity;

/**
 * Created by harshams on 27/06/2020
 */
public interface GameCompletionStatusService {

    public GameCompletionStatusEntity createEntryForUser(Long userId);

    public void refreshStatusForUserId(Long userId);

    public Boolean hasUserCompletedGame(Long userId);
}
