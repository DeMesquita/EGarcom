package com.example.garcom.Controller;

import com.example.garcom.DTOs.PagamentoDTO;
import com.example.garcom.Service.PagamentoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Hidden
@RestController
@RequestMapping("/api/pagamentos")
@Tag(name = "Pagamentos", description = "Gestão dos pagamentos das contas.")
public class PagamentoController {
    
    private final PagamentoService pagamentoService;
    
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }
    
    @Operation(summary = "Registra um novo pagamento para uma conta", description = "O pagamento só pode ser realizado em contas com status 'FECHADA' e o valor deve ser o total da conta.")
    @ApiResponse(responseCode = "201", description = "Pagamento registrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Conta não fechada, valor incorreto ou pagamento já existente")
    @PostMapping("/conta/{contaId}")
    public ResponseEntity<PagamentoDTO> realizarPagamento(@PathVariable Long contaId, @RequestBody PagamentoDTO dto) {
        PagamentoDTO createdDto = pagamentoService.realizarPagamento(contaId, dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }
}