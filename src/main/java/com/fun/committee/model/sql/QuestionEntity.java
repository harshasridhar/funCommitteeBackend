package com.fun.committee.model.sql;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by harshams on 25/06/2020
 */
@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "question_type")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class QuestionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private long id;

    @Column(name = "question", nullable = false, length = 300)
    @Lob
    private String question;

    @Column
    private String tag;

}
