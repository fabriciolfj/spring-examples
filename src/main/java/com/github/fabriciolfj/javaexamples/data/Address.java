package com.github.fabriciolfj.javaexamples.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city", length = 50, nullable = false)
    private String city;
    @Column(name = "state", length = 2, nullable = false)
    private String state;
    @Column(name = "number")
    private int number;
    private String street;
    @ManyToOne(cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;
}