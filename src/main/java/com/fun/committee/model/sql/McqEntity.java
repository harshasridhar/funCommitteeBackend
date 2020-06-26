package com.fun.committee.model.sql;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshams on 25/06/2020
 */
@Entity
@Table(name = "mcq")
@PrimaryKeyJoinColumn(name = "question_id")
@DiscriminatorValue("MCQ")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class McqEntity extends QuestionEntity implements Serializable {

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(name = "has_options", joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "option_id", referencedColumnName = "id")})
    private List<OptionEntity> options;

//    @Column(name = "answer",nullable = true)
//    private String answer;

    @Column
    private byte hasOtherOption;

    public List<OptionEntity> getOptions(){
        if(this.options == null)
            this.options = new ArrayList<>();
        return this.options;
    }

}
