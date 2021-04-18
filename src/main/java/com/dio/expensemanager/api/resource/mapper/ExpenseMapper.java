package com.dio.expensemanager.api.resource.mapper;

import com.dio.expensemanager.api.domain.Expense;
import com.dio.expensemanager.api.resource.dto.ExpenseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    ExpenseDTO toDto(Expense expense);

    List<ExpenseDTO> expensesToExpenseDtos(List<Expense> Expenses);

    Expense toModel(ExpenseDTO expenseDTO);
}
