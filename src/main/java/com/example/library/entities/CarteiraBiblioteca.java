package com.example.library.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Table(name = "tb_carteira")
@Data
@NoArgsConstructor
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
