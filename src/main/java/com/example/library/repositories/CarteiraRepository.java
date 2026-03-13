package com.example.library.repositories;

import com.example.library.entities.CarteiraBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<CarteiraBiblioteca, Long> {

    // Verifica se usuário já tem carteira (regra 1:1)
    boolean existsByUsuarioId(Long usuarioId);

    // Busca a carteira pelo usuário
    Optional<CarteiraBiblioteca> findByUsuarioId(Long usuarioId);
}
