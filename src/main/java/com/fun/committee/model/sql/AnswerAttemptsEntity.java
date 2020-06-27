package com.fun.committee.model.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by harshams on 27/06/2020
 */
@Entity
@Table(name = "answerAttempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerAttemptsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private long id;

    @Column
    private Long userId;

    @Column
    private Long guessId;

    @Column
    private String answer;

    @Column
    private String status;

    @Column(columnDefinition = "bigint(20) default 2")
    private Long retriesLeft;
}
