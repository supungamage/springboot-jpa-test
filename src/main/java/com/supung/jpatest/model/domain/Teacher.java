package com.supung.jpatest.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long teacherId;
    private String firstName;
    private String lastName;

    //one to many relationship (required)
    /*@OneToMany(
            cascade = CascadeType.ALL //save courses with teacher
    )
    @JoinColumn(
            name = "teacher_id", //course.teacher_id
            referencedColumnName = "teacherId" //Teacher.teacherId
    )
    private List<Course> courses; */
}
