package com.dio.expensemanager.api.resource;

import com.dio.expensemanager.api.domain.Person;
import com.dio.expensemanager.api.exception.PersonNotFoundException;
import com.dio.expensemanager.api.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/person")
@Log4j2
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPersons() {
        log.info("Request to find all persons");
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Person> findPersonByCpf(@PathVariable String cpf) throws PersonNotFoundException {
        log.info("Request to find person by cpf: {}", cpf);
        return ResponseEntity.ok(personService.findByCpf(cpf));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        log.info("Request to save person: {}", person);
        return ResponseEntity.status(CREATED).body(personService.create(person));
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws PersonNotFoundException {
        log.info("Request to update person: {}", person);
        return ResponseEntity.ok().body(personService.update(person));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletePersonByCpf(@PathVariable String cpf) {
        log.info("Request to delete person by cpf: {}", cpf);
        personService.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}
