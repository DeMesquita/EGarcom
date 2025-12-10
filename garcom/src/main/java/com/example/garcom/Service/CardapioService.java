package com.example.garcom.Service;

import com.example.garcom.DTOs.CardapioDTO;
import com.example.garcom.DTOs.CategoriaDTO;
import com.example.garcom.Model.Cardapio;
import com.example.garcom.Model.Usuario;
import com.example.garcom.Model.Usuario.Gerente;
import com.example.garcom.Repository.CardapioRepository;
import com.example.garcom.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CardapioService {
    
    private final CardapioRepository cardapioRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaService categoriaService;
    
    public CardapioService(CardapioRepository cardapioRepository, UsuarioRepository usuarioRepository, CategoriaService categoriaService) {
        this.cardapioRepository = cardapioRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaService = categoriaService;
    }
    
    public CardapioDTO create(Long gerenteId) {
        if (cardapioRepository.findByGerenteId(gerenteId).isPresent()) {
            throw new RuntimeException("Um Cardápio já existe para este Gerente.");
        }
        
        Usuario gerente = usuarioRepository.findById(gerenteId)
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado!"));
        
        if (!(gerente instanceof Gerente)) {
            throw new RuntimeException("O ID fornecido não pertence a um Gerente.");
        }
        
        Cardapio cardapio = new Cardapio();
        cardapio.setGerente(gerente);
        
        return toDTO(cardapioRepository.save(cardapio));
    }
    
    public CardapioDTO findById(Long id) {
        Cardapio entity = cardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado!"));
        return toDTO(entity);
    }
    
    public CardapioDTO getCardapioCompleto(Long id) {
        Cardapio cardapio = cardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado!"));
        
        CardapioDTO dto = new CardapioDTO();
        dto.setId(cardapio.getId());
        
        dto.setCategorias(cardapio.getCategorias().stream()
                .map(categoriaService::toDTO)
                .collect(Collectors.toList()));
        
        return dto;
    }
    
    public CardapioDTO findByGerenteId(Long gerenteId) {
        Cardapio entity = cardapioRepository.findByGerenteId(gerenteId)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado para o Gerente!"));
        return toDTO(entity);
    }
    
    private CardapioDTO toDTO(Cardapio entity) {
        CardapioDTO dto = new CardapioDTO();
        dto.setId(entity.getId());
        return dto;
    }
}