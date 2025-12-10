package com.example.garcom.Repository;

import com.example.garcom.Model.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
    Optional<Cardapio> findByGerenteId(Long gerenteId);
}