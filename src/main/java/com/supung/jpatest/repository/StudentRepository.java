package com.supung.jpatest.repository;

import com.supung.jpatest.model.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByFirstName(String firstName);
    List<Student> findAllByFirstNameContaining(String firstName);
    List<Student> findAllByLastNameNotNull();
    List<Student> findAllByGuardianName(String guardianName);

    //JPQL
    @Query("select s from Student s where s.studentEmail = ?1")
    Student getStudentByEmail(String email);

    //JPQL
    @Query("select s.firstName from Student s where s.studentEmail = ?1")
    String getStudentFirstNameByEmail(String email);

    //NativeQuery
    @Query(
            value = "select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true

    )
    Student getStudentByEmailNative(String email);

    //NamedParam
    @Query(
            value = "select * from tbl_student s where s.email_address = :email",
            nativeQuery = true

    )
    Student getStudentByEmailNativeNamedParam(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(
            value = "update tbl_student s set s.first_name = :firstName where s.email_address = :email",
            nativeQuery = true
    )
    void updateStudentFirstNameByEmail(@Param("firstName") String firstName, @Param("email") String email);

}
