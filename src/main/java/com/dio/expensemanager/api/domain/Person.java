package com.dio.expensemanager.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static com.dio.expensemanager.api.shared.Constants.DATA_BR;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @Column(unique = true)
    private String cpf;

    private String name;

    private Double salary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_BR)
    private LocalDate birthday;

    private String office;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expenses;

}
