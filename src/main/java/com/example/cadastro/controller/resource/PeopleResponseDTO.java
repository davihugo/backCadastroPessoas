package com.example.cadastro.controller.resource;

import com.example.cadastro.model.Pessoa;

import java.time.LocalDate;

public record PeopleResponseDTO(Long id, String nome, double altura, double peso, String sexo, String cpf, LocalDate dataNasc) {
    public PeopleResponseDTO(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getAltura(), pessoa.getPeso(), pessoa.getSexo(), pessoa.getCpf(), pessoa.getDataNasc());
    }
}
