package com.example.garcom.Controller;

import com.example.garcom.DTOs.ItemCardapioDTO;
import com.example.garcom.Service.ItemCardapioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/itens-cardapio")
@Tag(name = "Itens de Cardápio", description = "Gestão dos itens individuais do cardápio.")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    public ItemCardapioController(ItemCardapioService itemCardapioService) {
        this.itemCardapioService = itemCardapioService;
    }

    @Operation(summary = "Cria um novo item de cardápio", description = "Adiciona um novo prato/produto ao cardápio. Requer um ID de Categoria válido.")
    @ApiResponse(responseCode = "201", description = "Item criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou Categoria não encontrada")
    @PostMapping
    public ResponseEntity<ItemCardapioDTO> create(@RequestBody ItemCardapioDTO dto) {
        ItemCardapioDTO createdDto = itemCardapioService.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os itens de cardápio")
    @GetMapping
    public ResponseEntity<List<ItemCardapioDTO>> findAll() {
        List<ItemCardapioDTO> dtoList = itemCardapioService.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "Busca item de cardápio por ID")
    @ApiResponse(responseCode = "200", description = "Item encontrado")
    @ApiResponse(responseCode = "404", description = "Item não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioDTO> findById(@PathVariable Long id) {
        ItemCardapioDTO dto = itemCardapioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Atualiza um item de cardápio existente")
    @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Item ou Categoria não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioDTO> update(@PathVariable Long id, @RequestBody ItemCardapioDTO dto) {
        ItemCardapioDTO updatedDto = itemCardapioService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @Operation(summary = "Deleta um item de cardápio por ID")
    @ApiResponse(responseCode = "204", description = "Item deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Item não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemCardapioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}