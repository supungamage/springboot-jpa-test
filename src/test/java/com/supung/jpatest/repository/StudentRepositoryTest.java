package com.supung.jpatest.repository;

import com.supung.jpatest.model.domain.Guardian;
import com.supung.jpatest.model.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void save() {
        Student student = Student.builder()
                .firstName("Supun").lastName("Gamage")
                .studentEmail("supun@gmail.com")
                //.guardianName("Sumith").guardianEmail("sumith@gmail.com").guardianMobile("1234567890")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Sumith").email("sumith@gmail.com").mobile("1234567890")
                .build();

        Student student = Student.builder()
                .firstName("Sahan").lastName("Gamage")
                .studentEmail("sahan@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void findAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        log.info("students : " + studentList);
    }

    @Test
    public void findAllByFirstName() {
        List<Student> studentList = studentRepository.findAllByFirstName("Supun");
        log.info("students : " + studentList);
    }

    @Test
    public void findAllByGuardianName() {
        List<Student> studentList = studentRepository.findAllByGuardianName("Sumith");
        log.info("students : " + studentList);
    }

    @Test
    public void getStudentByEmail() {
        Student student = studentRepository.getStudentByEmail("supun@gmail.com");
        log.info("students : " + student);
    }

    @Test
    public void getStudentFirstNameByEmail() {
        String firstName = studentRepository.getStudentFirstNameByEmail("supun@gmail.com");
        log.info("student firstName : " + firstName);
    }

    @Test
    public void getStudentByEmailNative() {
        Student student = studentRepository.getStudentByEmailNative("supun@gmail.com");
        log.info("students : " + student);
    }

    @Test
    public void getStudentByEmailNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailNativeNamedParam("supun@gmail.com");
        log.info("students : " + student);
    }

    @Test
    public void updateStudentFirstNameByEmail() {
        studentRepository.updateStudentFirstNameByEmail("Supung", "supun@gmail.com");
        Student student = studentRepository.getStudentByEmailNativeNamedParam("supun@gmail.com");
        log.info("students : " + student.getFirstName());
    }
}
