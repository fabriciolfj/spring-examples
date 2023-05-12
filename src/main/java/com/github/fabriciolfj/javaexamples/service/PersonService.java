package com.github.fabriciolfj.javaexamples.service;


import com.github.fabriciolfj.javaexamples.data.Person;
import com.github.fabriciolfj.javaexamples.repository.AddressRepository;
import com.github.fabriciolfj.javaexamples.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Lazy
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void save(final Person person) {
        if (person.isPresentAddress()) {
            person.getAddresses().forEach(ad -> {
                ad.setPerson(person);
            });
        }

        repository.save(person);
    }

    @Transactional(propagation = Propagation.NEVER)
    public Person getPersonTwoAdresses(final Long id) {
        return ofNullable(repository.getPersonGreaterThanThowAddress(id))
                .orElseThrow(() -> new RuntimeException("person not found by id " + id));
    }


}
