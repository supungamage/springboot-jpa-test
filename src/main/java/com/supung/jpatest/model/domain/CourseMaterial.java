package com.supung.jpatest.model.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    //one to one relationship (required bi-directional)
    @OneToOne(
            cascade = CascadeType.ALL, //save attached Course to CourseMaterial when saving CourseMaterial
            fetch = FetchType.LAZY, //do not fetch Course with CourseMaterial
            optional = false //CourseMaterial cannot be saved without Course
    )
    @JoinColumn(
            name = "course_id", //course_material.course_id
            referencedColumnName = "courseId" //Course.courseId
    )
    private Course course;
}
