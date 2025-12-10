package com.example.garcom.Controller;

import com.example.garcom.DTOs.PedidoDTO;
import com.example.garcom.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Gestão dos pedidos de clientes.")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Cria um novo pedido", description = "Inicia um novo pedido para um cliente, incluindo itens iniciais.")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
    @PostMapping
    public ResponseEntity<PedidoDTO> create(@RequestBody PedidoDTO dto) {
        PedidoDTO createdDto = pedidoService.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pedidos")
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() {
        List<PedidoDTO> dtoList = pedidoService.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "Busca pedido por ID")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        PedidoDTO dto = pedidoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Atualiza um pedido existente")
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        PedidoDTO updatedDto = pedidoService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @Operation(summary = "Deleta um pedido por ID")
    @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}