package com.fun.committee.model.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by harshams on 27/06/2020
 */
@Entity
@Table(name = "gameCompletionStatus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameCompletionStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private long id;

    @Column
    private long userId;

    @Column
    private Double percentageCompletion;

    @Column
    private String status;

    @Column
    @CreationTimestamp
    private Timestamp createTime;

    @Column
    @UpdateTimestamp
    private Timestamp updateTime;
}
