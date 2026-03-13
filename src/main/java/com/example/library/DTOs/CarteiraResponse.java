package com.example.library.DTOs;

import com.example.library.entities.CarteiraBiblioteca;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarteiraResponse {
    private Long numeroCarteira;
    private LocalDate dataEmissao;
    private boolean isValid;

    // metodo para converter a entidade pro dto
    public static CarteiraResponse fromEntity(CarteiraBiblioteca carteira) {
        CarteiraResponse dto = new CarteiraResponse();
        dto.setNumeroCarteira(carteira.getNumeroCarteira());
        dto.setDataEmissao(carteira.getDataEmissao());
        dto.setValid(carteira.isValid());
        return dto;
    }
}
