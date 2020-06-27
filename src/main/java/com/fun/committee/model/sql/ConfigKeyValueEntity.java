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
@Table(name = "configKeyValue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigKeyValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private long id;

    @Column
    private String cKey;

    @Column
    private String value;
}
