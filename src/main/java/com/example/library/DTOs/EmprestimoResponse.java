package com.example.library.DTOs;

import com.example.library.entities.Emprestimo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EmprestimoResponse {
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Long usuarioId;
    private String usuarioNome;

    public static EmprestimoResponse fromEntity(Emprestimo emprestimo) {
        EmprestimoResponse dto = new EmprestimoResponse();
        dto.setId(emprestimo.getId());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataDevolucao(emprestimo.getDataDevolucao());
        dto.setUsuarioId(emprestimo.getUsuario().getId());
        dto.setUsuarioNome(emprestimo.getUsuario().getNome());
        return dto;
    }

    public static List<EmprestimoResponse> fromList(List<Emprestimo> lista) {
        return lista.stream()
                .map(EmprestimoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
