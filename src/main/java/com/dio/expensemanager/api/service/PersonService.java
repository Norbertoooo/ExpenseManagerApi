package com.dio.expensemanager.api.service;

import com.dio.expensemanager.api.domain.Person;
import com.dio.expensemanager.api.exception.PersonNotFoundException;
import com.dio.expensemanager.api.repository.PersonRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByCpf(String cpf) throws PersonNotFoundException {
        return personRepository.findById(cpf).orElseThrow( () -> new PersonNotFoundException(cpf));
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public void deleteByCpf(String cpf) {
        personRepository.deleteById(cpf);
    }

    public Person update(Person personToUpdate) throws PersonNotFoundException {
        Person personFounded = findByCpf(personToUpdate.getCpf());
        personFounded.setBirthday(personToUpdate.getBirthday());
        personFounded.setName(personFounded.getName());
        personFounded.setOffice(personFounded.getOffice());
        personFounded.setSalary(personFounded.getSalary());
        return personRepository.save(personFounded);
    }

}
