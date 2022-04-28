package com.supung.jpatest.repository;

import com.supung.jpatest.model.domain.Course;
import com.supung.jpatest.model.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Anula")
                .lastName("Perera")
                .build();

        teacherRepository.save(teacher);
        log.info("Teacher : " + teacher);
    }

    @Test
    public void saveTeacherWithCourses() {
        Course courseEcon = Course.builder()
                .credit(10).title("econ").build();
        Course courseLang = Course.builder()
                .credit(10).title("Lang").build();

        Teacher teacher = Teacher.builder()
                .firstName("Anula")
                .lastName("Perera")
                //.courses(List.of(courseEcon, courseLang))
                .build();

        teacherRepository.save(teacher);
        log.info("Teacher : " + teacher);
    }
}
