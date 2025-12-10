package com.example.garcom.Repository;

import com.example.garcom.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByContaId(Long contaId);
    
    @Query("SELECT MAX(p.numero) FROM Pedido p")
    Optional<Integer> findMaxNumero();
}