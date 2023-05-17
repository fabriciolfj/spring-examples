package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
