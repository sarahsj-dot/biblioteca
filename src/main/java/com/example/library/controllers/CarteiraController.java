package com.example.library.controllers;

import com.example.library.DTOs.CarteiraRequest;
import com.example.library.DTOs.CarteiraResponse;
import com.example.library.services.CarteiraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/carteira")
@RequiredArgsConstructor
public class CarteiraController {

    private final CarteiraService carteiraService;

    // Cria carteira para o usuario
    @PostMapping
    public ResponseEntity<CarteiraResponse> criar(
            @PathVariable Long usuarioId,
            @RequestBody @Valid CarteiraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carteiraService.criar(usuarioId, request));
    }

    // Busca carteira do usuario
    @GetMapping
    public ResponseEntity<CarteiraResponse> buscar(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carteiraService.buscarPorUsuario(usuarioId));
    }
}
