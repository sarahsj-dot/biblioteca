package com.example.library.services;

import com.example.library.DTOs.UsuarioRequest;
import com.example.library.DTOs.UsuarioResponse;
import com.example.library.entities.Usuario;
import com.example.library.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponse criar(UsuarioRequest request) {
        // Regra de negócio: email deve ser único
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email '" + request.getEmail() + "' já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());

        return UsuarioResponse.fromEntity(usuarioRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado."));
        return UsuarioResponse.fromEntity(usuario);
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado."));

        // Valida se novo email conflita com OUTRO usuário
        usuarioRepository.findByEmailAndIdNot(request.getEmail(), id).ifPresent(u -> {
            throw new RuntimeException("Email '" + request.getEmail() + "' já está em uso por outro usuário.");
        });

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());

        return UsuarioResponse.fromEntity(usuarioRepository.save(usuario));
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário com id " + id + " não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
}
