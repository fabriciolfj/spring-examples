package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.data.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
