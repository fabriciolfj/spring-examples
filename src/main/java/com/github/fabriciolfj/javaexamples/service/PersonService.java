package com.github.fabriciolfj.javaexamples.service;


import com.github.fabriciolfj.javaexamples.data.Person;
import com.github.fabriciolfj.javaexamples.repository.AddressRepository;
import com.github.fabriciolfj.javaexamples.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final AddressRepository addressRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void save(final Person person) {
        if (person.isPresentAddress()) {
            person.getAddresses().forEach(ad -> {
                ad.setPerson(person);
                addressRepository.save(ad);
            });

            return;
        }

        repository.save(person);
    }
}
