package com.example.library.repositories;

import com.example.library.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Verifica se email já existe (usado ao criar usuário)
    boolean existsByEmail(String email);

    // Busca por email (útil para validações)
    Optional<Usuario> findByEmail(String email);

    // Verifica email duplicado EXCLUINDO o próprio usuário (usado no update)
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.id != :id")
    Optional<Usuario> findByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
}
