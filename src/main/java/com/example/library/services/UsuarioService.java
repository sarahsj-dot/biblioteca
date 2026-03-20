package com.example.library.services;

import com.example.library.DTOs.UsuarioRequest;
import com.example.library.DTOs.UsuarioResponse;
import com.example.library.entities.Usuario;
import com.example.library.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    //cria novo usuario
    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .build();
        return UsuarioResponse.fromEntity(usuarioRepository.save(usuario));
    }

    //retorna os usuarios convertidos para dto
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //busca usuario por id e retorna como dto
    public UsuarioResponse buscarPorId(Long id) {
        return UsuarioResponse.fromEntity(getExcecao(id));
    }

    //atualiza nome e email do usuario que já existe
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = getExcecao(id);
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        return UsuarioResponse.fromEntity(usuarioRepository.save(usuario));
    }


    //remove usuario do banco pelo id
    public void deletar(Long id) {
        getExcecao(id); //garante que o usuario existe antes de deletar
        usuarioRepository.deleteById(id);
    }

    public Usuario getExcecao(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com id: " + id));
    }
}