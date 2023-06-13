package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Long> {
}
