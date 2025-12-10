package com.example.garcom.Service;


import com.example.garcom.DTOs.CategoriaDTO;
import com.example.garcom.DTOs.ItemCardapioDTO;
import com.example.garcom.Model.Categoria;
import com.example.garcom.Model.ItemCardapio;
import com.example.garcom.Repository.CategoriaRepository;
import com.example.garcom.Repository.ItemCardapioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCardapioService {
    
    private final ItemCardapioRepository itemCardapioRepository;
    private final CategoriaRepository categoriaRepository;
    
    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository, CategoriaRepository categoriaRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
        this.categoriaRepository = categoriaRepository;
    }
    
    public ItemCardapioDTO create(ItemCardapioDTO dto) {
        if (dto.getCategoria() == null || dto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("ItemCardapio deve ter uma Categoria associada.");
        }
        Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        
        ItemCardapio entity = toEntity(dto, categoria);
        ItemCardapio savedEntity = itemCardapioRepository.save(entity);
        return toDTO(savedEntity);
    }
    
    public List<ItemCardapioDTO> findAll() {
        return itemCardapioRepository.findAll().stream()
                .map(entity -> this.toDTO(entity))
                .collect(Collectors.toList());
    }
    
    public ItemCardapioDTO findById(Long id) {
        ItemCardapio entity = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemCardapio não encontrado!"));
        return toDTO(entity);
    }
    
    public ItemCardapioDTO update(Long id, ItemCardapioDTO dto) {
        ItemCardapio entity = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemCardapio não encontrado!"));
        
        Categoria categoria = null;
        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            categoria = categoriaRepository.findById(dto.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        }
        
        entity.setNome(dto.getNome());
        entity.setIngredientes(dto.getIngredientes());
        entity.setPreco(dto.getPreco());
        entity.setDisponivelNaCozinha(dto.getDisponivelNaCozinha());
        if (categoria != null) entity.setCategoria(categoria);
        
        ItemCardapio updatedEntity = itemCardapioRepository.save(entity);
        return toDTO(updatedEntity);
    }
    
    public void delete(Long id) {
        itemCardapioRepository.deleteById(id);
    }
    
    private ItemCardapio toEntity(ItemCardapioDTO dto, Categoria categoria) {
        ItemCardapio entity = new ItemCardapio();
        entity.setNome(dto.getNome());
        entity.setIngredientes(dto.getIngredientes());
        entity.setPreco(dto.getPreco());
        entity.setDisponivelNaCozinha(dto.getDisponivelNaCozinha());
        entity.setCategoria(categoria);
        return entity;
    }
    
    private ItemCardapioDTO toDTO(ItemCardapio entity) {
        ItemCardapioDTO dto = new ItemCardapioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setIngredientes(entity.getIngredientes());
        dto.setPreco(entity.getPreco());
        dto.setDisponivelNaCozinha(entity.getDisponivelNaCozinha());
        
        CategoriaDTO categoriaDto = new CategoriaDTO();
        categoriaDto.setId(entity.getCategoria().getId());
        categoriaDto.setNome(entity.getCategoria().getNome());
        dto.setCategoria(categoriaDto);
        
        return dto;
    }
}