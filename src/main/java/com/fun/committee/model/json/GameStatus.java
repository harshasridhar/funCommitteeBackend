package com.fun.committee.model.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by harshams on 28/06/2020
 */
@JsonSerialize
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GameStatus {

    private String type = "gameStatusInfo";

    private String username;

    private Double percentageCompletion;

    private String status;

}
