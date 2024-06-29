package com.example.cadastro.model;

import com.example.cadastro.controller.resource.PeopleRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name="pessoa")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String nome;

    @NotNull
    @Pattern(regexp = "\\d{11}")
    private String cpf;

    @NotNull
    @PastOrPresent
    private LocalDate dataNasc;

    @NotNull
    @Min(0)
    private double altura;

    @NotNull
    @Min(0)
    private double peso;

    @NotNull
    @Size(min = 1, max = 10)
    private String sexo;

}
