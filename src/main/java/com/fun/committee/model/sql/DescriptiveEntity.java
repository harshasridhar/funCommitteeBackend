package com.fun.committee.model.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by harshams on 25/06/2020
 */
@Entity
@Table(name = "descriptive")
@PrimaryKeyJoinColumn(name = "question_id")
@DiscriminatorValue("DESCRIPTIVE")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DescriptiveEntity extends QuestionEntity{

    @Column(name = "answer", length = 300)
    @Lob
    private String answer;

}
