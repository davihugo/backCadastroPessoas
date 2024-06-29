package com.example.cadastro.controller;

import com.example.cadastro.controller.resource.PeopleRequestDTO;
import com.example.cadastro.controller.resource.PeopleResponseDTO;
import com.example.cadastro.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void savePeople(@RequestBody PeopleRequestDTO data) {
        service.savePeople(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<PeopleResponseDTO> getAll() {
        return service.getAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public PeopleResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void updatePeople(@PathVariable Long id, @RequestBody PeopleRequestDTO data) {
        service.updatePeople(id, data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deletePeople(@PathVariable Long id) {
        service.deletePeople(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}/ideal-weight")
    public double calculateIdealWeight(@PathVariable Long id) {
        return service.calculateIdealWeight(id);
    }
}
