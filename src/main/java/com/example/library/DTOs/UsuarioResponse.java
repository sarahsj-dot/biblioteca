package com.example.library.DTOs;

import com.example.library.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String email;
    private CarteiraResponse carteira;

    // metodo para converter a entidade pro dto
    public static UsuarioResponse fromEntity(Usuario usuario) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        if (usuario.getCarteira() != null) {
            dto.setCarteira(CarteiraResponse.fromEntity(usuario.getCarteira()));
        }
        return dto;
    }
}
