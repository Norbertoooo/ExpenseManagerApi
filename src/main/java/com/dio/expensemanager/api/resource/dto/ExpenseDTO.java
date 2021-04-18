package com.dio.expensemanager.api.resource.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseDTO {

    private BigDecimal value;

    private String category;
}
