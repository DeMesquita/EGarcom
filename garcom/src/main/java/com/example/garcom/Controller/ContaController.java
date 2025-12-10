package com.example.garcom.Controller;

import com.example.garcom.DTOs.ContaDTO;
import com.example.garcom.Service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
@Tag(name = "Contas", description = "Contas dos clientes por mesa.")
public class ContaController {
    
    private final ContaService contaService;
    
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }
    
    
    @Operation(summary = "Fecha uma conta", description = "Calcula o valor total dos pedidos entregues e fecha a conta. A mesa fica DISPONÍVEL.")
    @ApiResponse(responseCode = "200", description = "Conta fechada com sucesso")
    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    @PutMapping("/fechar/{contaId}")
    public ResponseEntity<ContaDTO> fecharConta(@PathVariable Long contaId) {
        ContaDTO closedDto = contaService.fecharConta(contaId);
        return ResponseEntity.ok(closedDto);
    }
    
    @Operation(summary = "Lista todas as contas")
    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        List<ContaDTO> dtoList = contaService.findAll();
        return ResponseEntity.ok(dtoList);
    }
    
    @Operation(summary = "Busca conta por ID")
    @ApiResponse(responseCode = "200", description = "Conta encontrada")
    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable Long id) {
        ContaDTO dto = contaService.findById(id);
        return ResponseEntity.ok(dto);
    }
}