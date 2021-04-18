package com.dio.expensemanager.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(String cpf) {
        super("Person with cpf: " + cpf + " not founded");
    }
}
