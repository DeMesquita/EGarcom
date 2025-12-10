package com.example.garcom.Service;

import com.example.garcom.DTOs.*;
import com.example.garcom.Model.*;
import com.example.garcom.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public UsuarioDTO create(UsuarioDTO dto) {
        if (usuarioRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new RuntimeException("Login já está em uso.");
        }
        Usuario entity = toEntity(dto);
        Usuario savedEntity = usuarioRepository.save(entity);
        return toDTO(savedEntity);
    }
    
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public UsuarioDTO findById(Long id) {
        Usuario entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return toDTO(entity);
    }
    
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        
        entity.setNome(dto.getNome());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            entity.setSenha(dto.getSenha());
        }
        
        Usuario updatedEntity = usuarioRepository.save(entity);
        return toDTO(updatedEntity);
    }
    
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    private Usuario toEntity(UsuarioDTO dto) {
        Usuario entity;
        if (dto instanceof GarcomDTO) {
            entity = new Usuario.Garcom();
        } else if (dto instanceof CaixaDTO) {
            entity = new Usuario.Caixa();
        } else if (dto instanceof CozinhaDTO) {
            entity = new Usuario.Cozinha();
        } else if (dto instanceof GerenteDTO) {
            entity = new Usuario.Gerente();
        } else {
            throw new IllegalArgumentException("Tipo de DTO de usuário inválido.");
        }
        
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
        return entity;
    }
    
    private UsuarioDTO toDTO(Usuario entity) {
        UsuarioDTO dto;
        if (entity instanceof Usuario.Garcom) {
            dto = new GarcomDTO();
        } else if (entity instanceof Usuario.Caixa) {
            dto = new CaixaDTO();
        } else if (entity instanceof Usuario.Cozinha) {
            dto = new CozinhaDTO();
        } else if (entity instanceof Usuario.Gerente) {
            dto = new GerenteDTO();
        } else {
            dto = new UsuarioDTO();
        }
        
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setLogin(entity.getLogin());
        return dto;
    }
}