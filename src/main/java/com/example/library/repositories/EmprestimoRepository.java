package com.example.library.repositories;

import com.example.library.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    //retorna os emprestimos de um usuario
    List<Emprestimo> findByUsuarioId(Long usuarioId);
}