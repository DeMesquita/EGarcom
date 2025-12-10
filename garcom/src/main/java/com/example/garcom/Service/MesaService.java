package com.example.garcom.Service;

import com.example.garcom.DTOs.GarcomDTO;
import com.example.garcom.DTOs.MesaDTO;
import com.example.garcom.Model.Mesa;
import com.example.garcom.Model.Usuario;
import com.example.garcom.Repository.MesaRepository;
import com.example.garcom.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaService {
    
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;
    
    public MesaService(MesaRepository mesaRepository, UsuarioRepository usuarioRepository) {
        this.mesaRepository = mesaRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    public MesaDTO create(MesaDTO dto) {
        Mesa entity = toEntity(dto);
        Mesa savedEntity = mesaRepository.save(entity);
        return toDTO(savedEntity);
    }
    
    public List<MesaDTO> findAll() {
        return mesaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public MesaDTO findById(Long id) {
        Mesa entity = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada!"));
        return toDTO(entity);
    }
    
    public MesaDTO update(Long id, MesaDTO dto) {
        Mesa entity = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada!"));
        
        entity.setNumero(dto.getNumero());
        entity.setDisponivel(dto.getDisponivel());
        
        if (dto.getGarcom() != null && dto.getGarcom().getId() != null) {
            Usuario garcom = usuarioRepository.findById(dto.getGarcom().getId())
                    .orElseThrow(() -> new RuntimeException("Garçom não encontrado!"));
            entity.setGarcom(garcom);
        } else if (dto.getGarcom() == null) {
            entity.setGarcom(null);
        }
        
        Mesa updatedEntity = mesaRepository.save(entity);
        return toDTO(updatedEntity);
    }
    
    public void delete(Long id) {
        mesaRepository.deleteById(id);
    }
    
    private Mesa toEntity(MesaDTO dto) {
        Mesa entity = new Mesa();
        entity.setNumero(dto.getNumero());
        entity.setDisponivel(dto.getDisponivel());
        
        if (dto.getGarcom() != null && dto.getGarcom().getId() != null) {
            Usuario garcom = usuarioRepository.findById(dto.getGarcom().getId())
                    .orElseThrow(() -> new RuntimeException("Garçom não encontrado!"));
            entity.setGarcom(garcom);
        }
        
        return entity;
    }
    
    private MesaDTO toDTO(Mesa entity) {
        MesaDTO dto = new MesaDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setDisponivel(entity.getDisponivel());
        
        if (entity.getGarcom() != null) {
            GarcomDTO garcomDto = new GarcomDTO();
            garcomDto.setId(entity.getGarcom().getId());
            garcomDto.setNome(entity.getGarcom().getNome());
            dto.setGarcom(garcomDto);
        }
        
        return dto;
    }
}