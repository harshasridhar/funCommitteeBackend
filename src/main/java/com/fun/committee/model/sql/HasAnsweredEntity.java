package com.fun.committee.model.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by harshams on 26/06/2020
 */
@Entity
@Table(name = "has_answered_entity")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HasAnsweredEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long questionId;

    @Column
    private String answer;
}
