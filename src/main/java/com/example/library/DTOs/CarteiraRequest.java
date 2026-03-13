package com.example.library.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarteiraRequest {

    @NotNull(message = "Data de emissão é obrigatória")
    private LocalDate dataEmissao;

    private boolean isValid = true;
}
