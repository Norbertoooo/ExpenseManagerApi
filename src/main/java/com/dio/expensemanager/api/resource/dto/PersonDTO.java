package com.dio.expensemanager.api.resource.dto;

import com.dio.expensemanager.api.domain.Expense;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PersonDTO {

    private String cpf;

    private String name;

    private BigDecimal salary;

    private LocalDate birthday;

    private String office;

    private List<Expense> expenses;
}
