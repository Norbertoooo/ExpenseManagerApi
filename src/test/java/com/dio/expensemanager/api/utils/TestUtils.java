package com.dio.expensemanager.api.utils;

import com.dio.expensemanager.api.domain.Person;
import com.dio.expensemanager.api.resource.dto.PersonDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class TestUtils {

    public static Person createFakePerson() {
        return new Person();
    }

    public static PersonDTO createFakePersonDto() {
        return PersonDTO.builder().cpf("12332112332")
                .birthday(LocalDate.of(1997, 6, 2))
                .name("vitor norberto")
                .office("dev")
                .salary(5000.00)
                .build();
    }

    public static String asJsonString(Person object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(PersonDTO object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
