package com.dio.expensemanager.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static com.dio.expensemanager.api.shared.Constants.DATA_HOTA_BR;
import static com.dio.expensemanager.api.shared.Constants.ZONE_ID_MACEIO;

@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    private String category;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_HOTA_BR)
    private LocalDateTime dateTime = LocalDateTime.now(ZONE_ID_MACEIO);
}
