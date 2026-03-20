package com.example.library.services;

import com.example.library.DTOs.EmprestimoRequest;
import com.example.library.DTOs.EmprestimoResponse;
import com.example.library.entities.Emprestimo;
import com.example.library.entities.Usuario;
import com.example.library.repositories.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioService usuarioService;

    //registra um novo emprestimo de um usuario existente
    public EmprestimoResponse registrar(Long usuarioId, EmprestimoRequest request) {
        Usuario usuario = usuarioService.getExcecao(usuarioId);

        Emprestimo emprestimo = Emprestimo.builder()
                .dataEmprestimo(request.getDataEmprestimo())
                .dataDevolucao(request.getDataDevolucao()) // pode ser null se ainda não devolvido
                .usuario(usuario)
                .build();

        return EmprestimoResponse.fromEntity(emprestimoRepository.save(emprestimo));
    }

    // Lista os emprestimos de um usuario existente
    public List<EmprestimoResponse> listarPorUsuario(Long usuarioId) {
        usuarioService.getExcecao(usuarioId);

        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(usuarioId);
        return EmprestimoResponse.fromList(emprestimos);
    }
}
