package com.example.library.services;

import com.example.library.DTOs.CarteiraRequest;
import com.example.library.DTOs.CarteiraResponse;
import com.example.library.entities.CarteiraBiblioteca;
import com.example.library.entities.Usuario;
import com.example.library.repositories.CarteiraRepository;
import com.example.library.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public CarteiraResponse criar(Long usuarioId, CarteiraRequest request) {
        // Regra 1: usuário deve existir
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + usuarioId + " não encontrado."));

        // Regra 2: usuário já não pode ter uma carteira (relacionamento 1:1)
        if (carteiraRepository.existsByUsuarioId(usuarioId)) {
            throw new RuntimeException("Usuário já possui uma CarteiraBiblioteca.");
        }

        CarteiraBiblioteca carteira = new CarteiraBiblioteca();
        carteira.setDataEmissao(request.getDataEmissao());
        carteira.setValid(request.isValid());
        carteira.setUsuario(usuario);

        return CarteiraResponse.fromEntity(carteiraRepository.save(carteira));
    }

    @Transactional(readOnly = true)
    public CarteiraResponse buscarPorUsuario(Long usuarioId) {
        return carteiraRepository.findByUsuarioId(usuarioId)
                .map(CarteiraResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada para o usuário " + usuarioId));
    }
}
