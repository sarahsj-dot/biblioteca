package com.example.library.services;

import com.example.library.DTOs.CarteiraRequest;
import com.example.library.DTOs.CarteiraResponse;
import com.example.library.entities.CarteiraBiblioteca;
import com.example.library.entities.Usuario;
import com.example.library.repositories.CarteiraBibliotecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraBibliotecaRepository carteiraRepository;
    private final UsuarioService usuarioService;

    //cria uma carteira para um usuario existente
    public CarteiraResponse criar(Long usuarioId, CarteiraRequest request) {
        Usuario usuario = usuarioService.getExcecao(usuarioId);

        //relacionamento 1:1
        if (carteiraRepository.existsByUsuarioId(usuarioId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Usuário já possui uma carteira de biblioteca.");
        }

        //salva os dados do request
        CarteiraBiblioteca carteira = CarteiraBiblioteca.builder()
                .dataEmissao(request.getDataEmissao())
                .isValid(request.isValid())
                .usuario(usuario)
                .build();

        return CarteiraResponse.fromEntity(carteiraRepository.save(carteira));
    }
    //busca carteira por um usuario existente
    public CarteiraResponse buscarPorUsuario(Long usuarioId) {
        usuarioService.getExcecao(usuarioId);

        CarteiraBiblioteca carteira = carteiraRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Carteira não encontrada para o usuário id: " + usuarioId));

        return CarteiraResponse.fromEntity(carteira);
    }
}