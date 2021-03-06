package com.dio.expensemanager.api.resource;

import com.dio.expensemanager.api.domain.Person;
import com.dio.expensemanager.api.exception.PersonNotFoundException;
import com.dio.expensemanager.api.resource.dto.PersonDTO;
import com.dio.expensemanager.api.resource.mapper.PersonMapper;
import com.dio.expensemanager.api.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;

import static com.dio.expensemanager.api.utils.TestUtils.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
public class PersonResourceTest {

    private static final String PERSON_API_URL_PATH = "/api/v1/person";

    private MockMvc mockMvc;

    private PersonResource personResource;

    @Mock
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personResource = new PersonResource(personService);
        mockMvc = MockMvcBuilders.standaloneSetup(personResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOSTIsCalledThenAPersonShouldBeCreated() throws Exception {
        PersonDTO personDTO = createFakePersonDto();
        Person person = PersonMapper.INSTANCE.toModel(personDTO);

        when(personService.create(person)).thenReturn(person);

        mockMvc.perform(post(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testWhenPUTIsCalledThenAPersonShouldBeUpdated() throws Exception {
        PersonDTO personDTO = createFakePersonDto();
        Person person = PersonMapper.INSTANCE.toModel(personDTO);
        when(personService.update(person)).thenReturn(person);

        mockMvc.perform(put(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is(personDTO.getCpf())))
                .andExpect(jsonPath("$.name", is(personDTO.getName())))
                .andExpect(jsonPath("$.salary", is(personDTO.getSalary())))
                .andExpect(jsonPath("$.office", is(personDTO.getOffice())));

    }

    @Test
    void testWhenGETIsCalledThenPersonListShouldBeReturned() throws Exception {
        String cpf = "12321312312";
        Person expectedperson = createFakePerson();
        expectedperson.setCpf(cpf);
        List<Person> expectedPeopleDTOList = Collections.singletonList(expectedperson);

        when(personService.findAll()).thenReturn(expectedPeopleDTOList);

        mockMvc.perform(get(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cpf", is(cpf)))
                .andDo(print());
    }

    @Test
    void testWhenGETWithValidCPFIsCalledThenAPersonShouldBeReturned() throws Exception {
        String cpf = "12321312312";
        Person expectedPerson = createFakePerson();
        expectedPerson.setCpf(cpf);

        when(personService.findByCpf(cpf)).thenReturn(expectedPerson);

        mockMvc.perform(get(PERSON_API_URL_PATH + "/" + cpf)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is(expectedPerson.getCpf())));
    }

    @Test
    void testWhenGETWithNoValidCPFIsCalledThenAErrorShouldReturned() throws Exception {
        String cpf = "12321312312";

        when(personService.findByCpf(cpf)).thenThrow(PersonNotFoundException.class);

        mockMvc.perform(get(PERSON_API_URL_PATH + "/" + cpf)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testWhenDELETEWithValidCPFIsCalledThenAPersonShoudBeDeleted() throws Exception {
        String cpf = "12321312312";

        mockMvc.perform(delete(PERSON_API_URL_PATH + "/" + cpf)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
