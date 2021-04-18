package com.dio.expensemanager.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private String cpf;

    private String name;

    private BigDecimal salary;

    private LocalDate birthday;

    private String office;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expenses;

}
