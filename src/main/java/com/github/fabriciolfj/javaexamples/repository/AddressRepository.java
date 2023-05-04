package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.data.Address;
import com.github.fabriciolfj.javaexamples.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select e from Address e where e.person = ?1")
    List<Address> findAllAddress(final Person person);
}
