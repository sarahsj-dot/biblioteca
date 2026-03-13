package com.example.library.repositories;

import com.example.library.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Lista todos os empréstimos de um usuário
    List<Emprestimo> findByUsuarioId(Long usuarioId);

    // Conta empréstimos sem devolução (ainda em aberto)
    long countByUsuarioIdAndDataDevolucaoIsNull(Long usuarioId);
}
