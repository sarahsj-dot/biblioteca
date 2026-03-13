package com.example.library.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_carteira")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraBiblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCarteira;
    @Column(nullable = false)
    private LocalDate dataEmissao;
    @Column(nullable = false)
    private boolean isValid = true;

    // gerencia fk
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;
}
