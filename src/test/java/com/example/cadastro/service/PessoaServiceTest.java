package com.example.cadastro.service;

import com.example.cadastro.controller.resource.PeopleRequestDTO;
import com.example.cadastro.controller.resource.PeopleResponseDTO;
import com.example.cadastro.model.Pessoa;
import com.example.cadastro.repositories.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    private PessoaRepository repository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePeople() {
        PeopleRequestDTO requestDTO = new PeopleRequestDTO("João", 1.80, 80.0, "masculino", "12345678900", "1990-01-01");
        Pessoa pessoa = new Pessoa(1L, "João", "12345678900",LocalDate.parse("1990-01-01", DateTimeFormatter.ISO_DATE) , 1.78, 80.0, "F" );

        when(repository.save(any())).thenReturn(pessoa);

        pessoaService.savePeople(requestDTO);

        verify(repository, times(1)).save(any());
    }

    @Test
    void getAll() {
        Pessoa pessoa1 = new Pessoa(1L, "João", "12345678900",LocalDate.parse("1990-01-01", DateTimeFormatter.ISO_DATE) , 1.78, 80.0, "M" );
        Pessoa pessoa2 = new Pessoa(2L, "João", "12345678900",LocalDate.parse("1990-01-01", DateTimeFormatter.ISO_DATE) , 1.78, 80.0, "M" );
        List<Pessoa> pessoaList = Arrays.asList(pessoa1, pessoa2);

        when(repository.findAll()).thenReturn(pessoaList);

        List<PeopleResponseDTO> responseDTOList = pessoaService.getAll();

        assertEquals(2, responseDTOList.size());
        assertEquals(pessoa1.getNome(), responseDTOList.get(0).nome());
        assertEquals(pessoa2.getNome(), responseDTOList.get(1).nome());
    }

    @Test
    void getById() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa(id, "João", "12345678900",LocalDate.parse("1990-01-01", DateTimeFormatter.ISO_DATE) , 1.78, 80.0, "M" );;

        when(repository.findById(id)).thenReturn(Optional.of(pessoa));

        PeopleResponseDTO responseDTO = pessoaService.getById(id);

        assertNotNull(responseDTO);
        assertEquals(pessoa.getNome(), responseDTO.nome());
    }

    @Test
    void updatePeople() {
        Long id = 1L;
        PeopleRequestDTO requestDTO = new PeopleRequestDTO("José", 1.75, 75.0, "masculino", "12345678900", "1985-06-15");
        Pessoa pessoa = new Pessoa(id, "João", "12345678900",LocalDate.parse("1990-01-01", DateTimeFormatter.ISO_DATE) , 1.98, 100.0, "M" );;

        when(repository.findById(id)).thenReturn(Optional.of(pessoa));
        when(repository.save(any())).thenReturn(pessoa);

        pessoaService.updatePeople(id, requestDTO);

        verify(repository, times(1)).save(any());
        assertEquals(requestDTO.nome(), pessoa.getNome());
        assertEquals(requestDTO.altura(), pessoa.getAltura());
    }

    @Test
    void deletePeople() {
        Long id = 1L;

        pessoaService.deletePeople(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void calculateIdealWeightForFemale() {
        Long id = 2L;
        Pessoa pessoa = new Pessoa(id, "Maria", "98765432100", LocalDate.parse("1995-05-10", DateTimeFormatter.ISO_DATE), 1.65, 60.0, "feminino");

        when(repository.findById(id)).thenReturn(Optional.of(pessoa));
        double idealWeight = pessoaService.calculateIdealWeight(id);

        assertEquals(62.1 * pessoa.getAltura() - 44.7, idealWeight);
    }
}
