package com.dio.expensemanager.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class PersonAlredyExist extends Exception {
    public PersonAlredyExist(String cpf) {
        super("Person with cpf: " + cpf + " already exists");
    }
}
