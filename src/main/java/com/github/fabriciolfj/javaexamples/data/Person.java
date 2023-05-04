package com.github.fabriciolfj.javaexamples.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
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

    public List<Address> getAddresses() {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        return this.addresses;
    }

    @JsonIgnore
    public boolean isPresentAddress() {
        return isNotNullAddress() && isNotEmpty();
    }

    @JsonIgnore
    public boolean isNotNullAddress() {
        return Objects.nonNull(addresses);
    }

    @JsonIgnore
    public boolean isNotEmpty() {
        return !addresses.isEmpty();
    }
}
