package com.dio.expensemanager.api.resource.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static com.dio.expensemanager.api.shared.Constants.DATA_BR;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @NotNull
    private String cpf;

    @NotBlank
    private String name;

    @NotNull
    private Double salary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_BR)
    private LocalDate birthday;

    private String office;

    @Valid
    private List<ExpenseDTO> expenses;
}
