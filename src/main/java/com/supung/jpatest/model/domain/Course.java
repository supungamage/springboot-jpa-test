package com.supung.jpatest.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;
    //uni-directional relationship (optional)
    @OneToOne(
            mappedBy = "course" //Course.course
    )
    private CourseMaterial courseMaterial;

    //ManyToOne choose ManyToOne instead of OneToMany
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id", //course.teacher_id
            referencedColumnName = "teacherId" //Teacher.teacherId
    )
    private Teacher teacher;

    //ManyToOne
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_mapping", //mapping table
            joinColumns = @JoinColumn(        //join column in this class
                    name = "course_mid",        //student_course_mapping.course_mid
                    referencedColumnName = "courseId"  //Course.courseId
            ),
            inverseJoinColumns = @JoinColumn(  //join column in other class
                    name = "student_mid",       //student_course_mapping.student_mid
                    referencedColumnName = "studentId" //Student.studentId
            )
    )
    private List<Student> students;

    public void addStudent(Student student) {
        if(Objects.isNull(students)) {
            students = new ArrayList<>();
        }

        students.add(student);
    }

}
