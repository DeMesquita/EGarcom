package com.example.garcom.Controller;

import com.example.garcom.DTOs.ClienteDTO;
import com.example.garcom.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gestão dos clientes do restaurante.")
public class ClienteController {

  private final ClienteService clienteService;

  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @Operation(summary = "Registra a chegada de um novo cliente")
  @ApiResponse(responseCode = "201", description = "Cliente registrado com sucesso")
  @PostMapping
  public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto) {
    ClienteDTO createdDto = clienteService.create(dto);
    return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
  }

  @Operation(summary = "Lista todos os clientes")
  @GetMapping
  public ResponseEntity<List<ClienteDTO>> findAll() {
    List<ClienteDTO> dtoList = clienteService.findAll();
    return ResponseEntity.ok(dtoList);
  }

  @Operation(summary = "Busca cliente por ID")
  @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
  @GetMapping("/{id}")
  public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
    ClienteDTO dto = clienteService.findById(id);
    return ResponseEntity.ok(dto);
  }

  @Operation(summary = "Atualiza informações do cliente")
  @PutMapping("/{id}")
  public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
    ClienteDTO updatedDto = clienteService.update(id, dto);
    return ResponseEntity.ok(updatedDto);
  }

  @Operation(summary = "Deleta um cliente por ID")
  @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    clienteService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
