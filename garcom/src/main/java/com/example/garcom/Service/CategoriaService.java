package com.example.garcom.Service;

import com.example.garcom.DTOs.CategoriaDTO;
import com.example.garcom.Model.Categoria;
import com.example.garcom.Repository.CategoriaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.*;

@Service
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    public CategoriaDTO create(CategoriaDTO dto) {
        Categoria categoria = toEntity(dto);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return toDTO(savedCategoria);
    }
    
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        return toDTO(categoria);
    }
    
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        
        if (dto.getCategoriaMae() != null && dto.getCategoriaMae().getId() != null) {
            Categoria categoriaMae = categoriaRepository.findById(dto.getCategoriaMae().getId())
                    .orElseThrow(() -> new RuntimeException("Categoria Mãe não encontrada!"));
            categoria.setcategoriaMae(categoriaMae);
        } else if (dto.getCategoriaMae() == null) {
            categoria.setcategoriaMae(null); 
        }
        
        categoria.setNome(dto.getNome());
        
        Categoria updatedCategoria = categoriaRepository.save(categoria);
        return toDTO(updatedCategoria);
    }
    
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
    
    private Categoria toEntity(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        
        if (dto.getCategoriaMae() != null && dto.getCategoriaMae().getId() != null) {
            Categoria Mae = categoriaRepository.findById(dto.getCategoriaMae().getId())
                    .orElseThrow(() -> new RuntimeException("Categoria Mãe para criação não encontrada!"));
            entity.setcategoriaMae(Mae);
        }
        
        return entity;
    }
    
    CategoriaDTO toDTO(Categoria entity) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        
        if (entity.getcategoriaMae() != null) {
            CategoriaDTO maeDto = new CategoriaDTO();
            maeDto.setId(entity.getcategoriaMae().getId());
            maeDto.setNome(entity.getcategoriaMae().getNome());
            dto.setCategoriaMae(maeDto);
        }
        
        return dto;
    }
}
