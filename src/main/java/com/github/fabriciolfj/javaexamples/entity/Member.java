package com.github.fabriciolfj.javaexamples.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Member {

    private Integer id;
    @NotBlank(message = "{member.name}")
    private String name;
    @NotBlank
    @Email
    private String email;
    private String phone;
}
