package com.example.garcom.Controller;

import com.example.garcom.DTOs.CardapioDTO;
import com.example.garcom.Service.CardapioService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Hidden
@RestController
@RequestMapping("/api/cardapios")
@Tag(name = "Cardápios", description = "Cardápio e o Gerente responsável.")
public class CardapioController {
    
    private final CardapioService cardapioService;
    
    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }
    
    @Operation(summary = "Cria um novo Cardápio", description = "Associa um Cardápio a um Gerente.")
    @ApiResponse(responseCode = "201", description = "Cardápio criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Gerente não encontrado ou já possui um Cardápio")
    @PostMapping("/gerente/{gerenteId}")
    public ResponseEntity<CardapioDTO> create(@PathVariable Long gerenteId) {
        CardapioDTO createdDto = cardapioService.create(gerenteId);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Obtém a estrutura completa do Cardápio", description = "Retorna o Cardápio e todas as suas categorias.")
    @ApiResponse(responseCode = "200", description = "Cardápio encontrado")
    @ApiResponse(responseCode = "404", description = "Cardápio não encontrado")
    @GetMapping("/{id}/completo")
    public ResponseEntity<CardapioDTO> getCardapioCompleto(@PathVariable Long id) {
        CardapioDTO dto = cardapioService.getCardapioCompleto(id);
        return ResponseEntity.ok(dto);
    }
    
    @Operation(summary = "Busca Cardápio por ID do Gerente")
    @ApiResponse(responseCode = "200", description = "Cardápio encontrado")
    @ApiResponse(responseCode = "404", description = "Cardápio não encontrado para este Gerente")
    @GetMapping("/gerente/{gerenteId}")
    public ResponseEntity<CardapioDTO> findByGerenteId(@PathVariable Long gerenteId) {
        CardapioDTO dto = cardapioService.findByGerenteId(gerenteId);
        return ResponseEntity.ok(dto);
    }
}