package com.example.cadastro.repositories;

import com.example.cadastro.model.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testSaveAndFindById() {
        Pessoa pessoa = Pessoa.builder()
                .nome("Carlos")
                .cpf("12345678901")
                .dataNasc(LocalDate.of(1990, 1, 1))
                .altura(1.80)
                .peso(80)
                .sexo("M")
                .build();
        pessoaRepository.save(pessoa);

        Optional<Pessoa> found = pessoaRepository.findById(pessoa.getId());
        assertTrue(found.isPresent());
    }
}
