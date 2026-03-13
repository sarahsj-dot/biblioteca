package com.example.library.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Table(name = "tb_emprestimo")
@Data
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataEmprestimo; // se for null - o emprestimo ainda está em aberto

    @Column
    private LocalDate dataDevolucao;

    // emprestimo N:1
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false) // mapeia a fk na tabela emprestimo
    private Usuario usuario;

}
