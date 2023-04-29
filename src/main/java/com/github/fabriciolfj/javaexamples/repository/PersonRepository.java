package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
