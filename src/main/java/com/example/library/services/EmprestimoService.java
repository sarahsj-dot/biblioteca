package com.example.library.services;

import com.example.library.DTOs.EmprestimoRequest;
import com.example.library.DTOs.EmprestimoResponse;
import com.example.library.entities.CarteiraBiblioteca;
import com.example.library.entities.Emprestimo;
import com.example.library.entities.Usuario;
import com.example.library.repositories.CarteiraRepository;
import com.example.library.repositories.EmprestimoRepository;
import com.example.library.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarteiraRepository carteiraRepository;

    private static final int LIMITE_EMPRESTIMOS_ATIVOS = 3;

    @Transactional
    public EmprestimoResponse registrar(Long usuarioId, EmprestimoRequest request) {
        // Regra 1: usuário deve existir
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + usuarioId + " não encontrado."));

        // Regra 2: usuário deve ter carteira válida para emprestar
        CarteiraBiblioteca carteira = carteiraRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException(
                        "Usuário não possui CarteiraBiblioteca. Crie uma carteira primeiro."));

        if (!carteira.isValid()) {
            throw new RuntimeException("CarteiraBiblioteca inválida. Não é possível realizar empréstimos.");
        }

        // Regra 3: limite de empréstimos ativos
        long ativos = emprestimoRepository.countByUsuarioIdAndDataDevolucaoIsNull(usuarioId);
        if (ativos >= LIMITE_EMPRESTIMOS_ATIVOS) {
            throw new RuntimeException(
                    "Limite de " + LIMITE_EMPRESTIMOS_ATIVOS + " empréstimos ativos atingido. " +
                    "Devolva um livro antes de realizar novo empréstimo.");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(request.getDataEmprestimo());
        emprestimo.setDataDevolucao(request.getDataDevolucao());
        emprestimo.setUsuario(usuario);

        return EmprestimoResponse.fromEntity(emprestimoRepository.save(emprestimo));
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponse> listarPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário com id " + usuarioId + " não encontrado.");
        }
        return EmprestimoResponse.fromList(emprestimoRepository.findByUsuarioId(usuarioId));
    }
}
