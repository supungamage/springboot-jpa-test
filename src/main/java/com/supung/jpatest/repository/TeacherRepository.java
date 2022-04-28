package com.supung.jpatest.repository;

import com.supung.jpatest.model.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
