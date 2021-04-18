package com.dio.expensemanager.api.service;

import com.dio.expensemanager.api.domain.Expense;
import com.dio.expensemanager.api.domain.Person;
import com.dio.expensemanager.api.exception.PersonAlredyExist;
import com.dio.expensemanager.api.exception.PersonNotFoundException;
import com.dio.expensemanager.api.repository.PersonRepository;
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
        return personRepository.findById(cpf).orElseThrow(() -> new PersonNotFoundException(cpf));
    }

    public Person addExpense(String cpf, Expense expense) throws PersonNotFoundException {
        Person person = findByCpf(cpf);
        person.getExpenses().add(expense);
        return personRepository.save(person);
    }

    public Person create(Person person) throws Exception {
        if (personRepository.existsById(person.getCpf())) {
            throw new PersonAlredyExist(person.getCpf());
        } else {
            return personRepository.save(person);
        }
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
