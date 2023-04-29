package com.github.fabriciolfj.javaexamples.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "last_name", length = 255, nullable = false)
    @JsonProperty("last_name")
    private String lastName;
    @OneToMany(mappedBy = "person")
    private List<Address> addresses;

    public boolean isPresentAddress() {
        return isNotNullAddress() && isNotEmpty();
    }

    public boolean isNotNullAddress() {
        return Objects.nonNull(addresses);
    }

    public boolean isNotEmpty() {
        return !addresses.isEmpty();
    }
}
