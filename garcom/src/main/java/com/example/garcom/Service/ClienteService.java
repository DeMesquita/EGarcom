package com.example.garcom.Service;

import com.example.garcom.DTOs.ClienteDTO;
import com.example.garcom.Model.Cliente;
import com.example.garcom.Repository.ClienteRepository;
import org.springframework.stereotype.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public ClienteDTO create(ClienteDTO dto) {
        Cliente entity = toEntity(dto);
        entity.setHoraChegada(new Date());
        Cliente savedEntity = clienteRepository.save(entity);
        return toDTO(savedEntity);
    }
    
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public ClienteDTO findById(Long id) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return toDTO(entity);
    }
    
    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        
        entity.setNome(dto.getNome());
        if (dto.getHoraSaida() != null) entity.setHoraSaida(dto.getHoraSaida());
        
        Cliente updatedEntity = clienteRepository.save(entity);
        return toDTO(updatedEntity);
    }
    
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
    
    private Cliente toEntity(ClienteDTO dto) {
        Cliente entity = new Cliente();
        entity.setNome(dto.getNome());
        return entity;
    }
    
    private ClienteDTO toDTO(Cliente entity) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setHoraChegada(entity.getHoraChegada());
        dto.setHoraSaida(entity.getHoraSaida());
        return dto;
    }
}
