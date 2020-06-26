package com.fun.committee.model.sql;

import lombok.*;

import javax.persistence.*;

/**
 * Created by harshams on 25/06/2020
 */
@Entity
@Table(name = "options")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private long id;

    @Column
    private String value;
}
