package com.dio.expensemanager.api.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

    @NotNull
    private Double value;

    @NotBlank
    private String category;

    @NotBlank
    private String description;
}
