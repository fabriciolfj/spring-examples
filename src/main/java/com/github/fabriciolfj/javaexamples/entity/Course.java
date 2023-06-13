package com.github.fabriciolfj.javaexamples.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "COURSE")
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;
    @Column(name = "BEGIN_DATE")
    private LocalDate beginDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "FEE")
    @OneToMany(orphanRemoval = true)
    private int fee;
}
