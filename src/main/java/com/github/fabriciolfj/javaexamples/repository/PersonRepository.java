package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p join fetch p.addresses where p.id = ?1 and (select count(e) from Address e where e.person = p) >= 2")
    Person getPersonGreaterThanThowAddress(final Long id);
}
