package com.example.library.controllers;

import com.example.library.DTOs.EmprestimoRequest;
import com.example.library.DTOs.EmprestimoResponse;
import com.example.library.services.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    // Registra emprestimo
    @PostMapping
    public ResponseEntity<EmprestimoResponse> registrar(
            @PathVariable Long usuarioId,
            @RequestBody @Valid EmprestimoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emprestimoService.registrar(usuarioId, request));
    }

    // Lista emprestimos do usuario
    @GetMapping
    public ResponseEntity<List<EmprestimoResponse>> listar(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(emprestimoService.listarPorUsuario(usuarioId));
    }
}
