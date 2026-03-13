package com.example.library.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Data                   // getters, setters, toString, equals, hashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100) // not null e limite de caracteres
    private String nome;
    @Column(nullable = false, unique = true, length = 150) // não permite que dois usuários tenham o mesmo email
    private String email;

    // usuario 1:1 carteiraBiblioteca
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // define o relacionamento entre carteira e usuario(fk)
    private CarteiraBiblioteca carteira;

    // usuario 1:N emprestimo
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // define o relacionamento entre emprestom e usuario(fk)
    private List<Emprestimo> emprestimos = new ArrayList<>(); //inicia como ArrayList vazio
}
