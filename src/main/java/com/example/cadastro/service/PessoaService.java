package com.example.cadastro.service;

import com.example.cadastro.controller.resource.PeopleRequestDTO;
import com.example.cadastro.controller.resource.PeopleResponseDTO;
import com.example.cadastro.model.Pessoa;
import com.example.cadastro.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public void savePeople(PeopleRequestDTO data) {
        repository.save(parsePessoa(data));
    }

    public List<PeopleResponseDTO> getAll() {
        List<Pessoa> pessoaList = repository.findAll();
        return pessoaList.stream().map(PeopleResponseDTO::new).collect(Collectors.toList());
    }

    public PeopleResponseDTO getById(Long id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        return new PeopleResponseDTO(pessoa);
    }

    public void updatePeople(Long id, PeopleRequestDTO data) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.setNome(data.nome());
        pessoa.setAltura(data.altura());
        pessoa.setPeso(data.peso());
        pessoa.setSexo(data.sexo());
        pessoa.setCpf(data.cpf());
        pessoa.setDataNasc(LocalDate.parse(data.dataNasc(), DateTimeFormatter.ISO_DATE));
        repository.save(pessoa);
    }

    public void deletePeople(Long id) {
        repository.deleteById(id);
    }

    public double calculateIdealWeight(Long id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        if (pessoa.getSexo().equalsIgnoreCase("masculino")) {
            return (72.7 * pessoa.getAltura()) - 58;
        } else {
            return (62.1 * pessoa.getAltura()) - 44.7;
        }
    }

    private Pessoa parsePessoa(PeopleRequestDTO data){
        return Pessoa.builder()
                .altura(data.altura()).cpf(data.cpf()).dataNasc(LocalDate.parse(data.dataNasc(), DateTimeFormatter.ISO_DATE))
                .peso(data.peso()).sexo(data.sexo()).nome(data.nome())
                .build();


    }
}
