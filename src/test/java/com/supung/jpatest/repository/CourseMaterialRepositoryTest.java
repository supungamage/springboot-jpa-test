package com.supung.jpatest.repository;

import com.supung.jpatest.model.domain.Course;
import com.supung.jpatest.model.domain.CourseMaterial;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .credit(10).title("art").build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.art.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);

    }

    @Test
    public void findAllCourseMaterials() {
        List<CourseMaterial> courseMaterialList = courseMaterialRepository.findAll();
        log.info("courseMaterialList : " + courseMaterialList);
    }

}
