package com.demo.elearn.repository;

import com.demo.elearn.models.Course;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    public Optional<Course> findByUuid(String uuid);
}