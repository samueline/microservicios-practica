package com.motoservice.repositorio;

import com.motoservice.entidades.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Integer> {
    List<Moto> findByUsuarioId(int usuarioId);
}
