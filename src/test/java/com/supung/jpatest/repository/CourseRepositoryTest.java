package com.supung.jpatest.repository;

import com.supung.jpatest.model.domain.Course;
import com.supung.jpatest.model.domain.Guardian;
import com.supung.jpatest.model.domain.Student;
import com.supung.jpatest.model.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        log.info("courses : " + courseList);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Rangika")
                .lastName("Perera")
                .build();

        Course course = Course.builder()
                .title("History")
                .credit(10)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
        log.info("Course : " + course);
    }

    @Test
    public void findCourseWithPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        log.info("totalElements :" + totalElements);
        log.info("totalPages :" + totalPages);
        log.info("Course : " + courses);
    }

    @Test
    public void findCoursesWithPaginationAndSorting() {
        Pageable firstPageWithThreeRecordsSortByTitle = PageRequest.of(0, 3, Sort.by("title"));
        Pageable secondPageWithTwoRecordsSortByTileDesc = PageRequest.of(1, 2, Sort.by("title").descending());

        Pageable secondPageWithTwoRecordsSortByTileAndCredit = PageRequest.of(1, 2,
                Sort.by("title").descending()
                        .and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecordsSortByTileAndCredit).getContent();

        log.info("Course : " + courses);
    }

    @Test
    public void findAllByTitleContainingWithPagination() {
        Pageable firstPageWithThreeRecordsSortByTitle = PageRequest.of(0, 3,
                Sort.by("title").descending());

        List<Course> courses = courseRepository
                .findAllByTitleContaining("a", firstPageWithThreeRecordsSortByTitle).getContent();

        log.info("Course : " + courses);

    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Pushpa")
                .lastName("Gamage")
                .build();
        Guardian guardian = Guardian.builder()
                .name("SumithG").email("sumith@gmail.com").mobile("1234567890")
                .build();

        Student student = Student.builder()
                .firstName("Susankha").lastName("Gamage")
                .studentEmail("susa@gmail.com")
                .guardian(guardian)
                .build();

        Course course = Course.builder()
                .title("Commerce")
                .credit(10)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }

}
