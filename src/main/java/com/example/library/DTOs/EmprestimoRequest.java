package com.example.library.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoRequest {

    @NotNull(message = "Data de empréstimo é obrigatória")
    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;
}
