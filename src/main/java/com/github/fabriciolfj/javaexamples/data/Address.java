package com.github.fabriciolfj.javaexamples.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}