package com.dio.expensemanager.api.resource.mapper;

import com.dio.expensemanager.api.domain.Person;
import com.dio.expensemanager.api.resource.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDto(Person person);

    List<PersonDTO> personsToPersonDtos(List<Person> persons);

    Person toModel(PersonDTO personDTO);
}
