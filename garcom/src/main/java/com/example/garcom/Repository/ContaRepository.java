package com.example.garcom.Repository;

import com.example.garcom.Model.Conta;
import com.example.garcom.Model.StatusConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByMesaIdAndStatus(Long mesaId, StatusConta status);
}