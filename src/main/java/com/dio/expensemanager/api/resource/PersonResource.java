package com.dio.expensemanager.api.resource;

import com.dio.expensemanager.api.domain.Expense;
import com.dio.expensemanager.api.domain.Person;
import com.dio.expensemanager.api.exception.PersonNotFoundException;
import com.dio.expensemanager.api.resource.dto.ExpenseDTO;
import com.dio.expensemanager.api.resource.dto.PersonDTO;
import com.dio.expensemanager.api.resource.mapper.ExpenseMapper;
import com.dio.expensemanager.api.resource.mapper.PersonMapper;
import com.dio.expensemanager.api.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/person")
@Log4j2
public class PersonResource {

    private final PersonService personService;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    private final ExpenseMapper expenseMapper  = ExpenseMapper.INSTANCE;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAllPersons() {
        log.info("Request to find all persons");
        return ResponseEntity.ok(personMapper.personsToPersonDtos(personService.findAll()));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PersonDTO> findPersonByCpf(@PathVariable String cpf) throws PersonNotFoundException {
        log.info("Request to find person by cpf: {}", cpf);
        return ResponseEntity.ok(personMapper.toDto(personService.findByCpf(cpf)));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) throws Exception {
        log.info("Request to save person: {}", personDTO);
        return ResponseEntity.status(CREATED).body(personMapper.toDto(personService.create(personMapper.toModel(personDTO))));
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<PersonDTO> addExpense(@PathVariable String cpf, @Valid @RequestBody ExpenseDTO expenseDTO) throws PersonNotFoundException {
        log.info("Request to add expense: {} to person with cpf: {}", expenseDTO, cpf);
        Expense expense = expenseMapper.toModel(expenseDTO);
        Person person = personService.addExpense(cpf,expense);
        return ResponseEntity.status(CREATED).body(personMapper.toDto(person));
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@Valid @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        log.info("Request to update person: {}", personDTO);
        return ResponseEntity.ok().body(personMapper.toDto(personService.update(personMapper.toModel(personDTO))));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletePersonByCpf(@PathVariable String cpf) {
        log.info("Request to delete person by cpf: {}", cpf);
        personService.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
