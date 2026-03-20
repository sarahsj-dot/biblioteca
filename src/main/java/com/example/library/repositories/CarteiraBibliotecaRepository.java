package com.example.library.repositories;

import com.example.library.entities.CarteiraBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraBibliotecaRepository extends JpaRepository<CarteiraBiblioteca, Long> {
    //busca carteira pelo id do usuario
    Optional<CarteiraBiblioteca> findByUsuarioId(Long usuarioId);
    //verifica se o usuario já tem uma carteira
    boolean existsByUsuarioId(Long usuarioId);
}