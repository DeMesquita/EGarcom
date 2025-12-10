package com.example.garcom.Controller;

import com.example.garcom.DTOs.MesaDTO;
import com.example.garcom.Service.MesaService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@Tag(name = "Mesas", description = "Mesas do restaurante.")
public class MesaController {
    
    private final MesaService mesaService;
    
    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }
    
    @Operation(summary = "Cria uma nova mesa", description = "Adiciona uma nova mesa ao restaurante.")
    @ApiResponse(responseCode = "201", description = "Mesa criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<MesaDTO> create(@RequestBody MesaDTO dto) {
        MesaDTO createdDto = mesaService.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Lista todas as mesas", description = "Retorna uma lista de todas as mesas cadastradas.")
    @GetMapping
    public ResponseEntity<List<MesaDTO>> findAll() {
        List<MesaDTO> dtoList = mesaService.findAll();
        return ResponseEntity.ok(dtoList);
    }
    
    @Operation(summary = "Busca mesa por ID")
    @ApiResponse(responseCode = "200", description = "Mesa encontrada")
    @ApiResponse(responseCode = "404", description = "Mesa não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> findById(@PathVariable Long id) {
        MesaDTO dto = mesaService.findById(id);
        return ResponseEntity.ok(dto);
    }
    
    @Operation(summary = "Atualiza uma mesa existente")
    @ApiResponse(responseCode = "200", description = "Mesa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Mesa ou Garçom não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<MesaDTO> update(@PathVariable Long id, @RequestBody MesaDTO dto) {
        MesaDTO updatedDto = mesaService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }
    
    @Operation(summary = "Deleta uma mesa por ID")
    @ApiResponse(responseCode = "204", description = "Mesa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Mesa não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
