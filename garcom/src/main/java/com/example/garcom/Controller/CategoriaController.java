package com.example.garcom.Controller;

import com.example.garcom.DTOs.CategoriaDTO;
import com.example.garcom.Service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Categorias de itens do cardápio.")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(summary = "Cria uma nova categoria", description = "Endpoint para adicionar uma nova categoria ao cardápio.")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO dto) {
        CategoriaDTO novaCategoria = categoriaService.create(dto);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas.")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Busca categoria por ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @Operation(summary = "Atualiza uma categoria existente")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoriaAtualizada = categoriaService.update(id, dto);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @Operation(summary = "Deleta uma categoria por ID")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}