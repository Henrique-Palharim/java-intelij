package com.campeoes.cadastro_campeoes.infrastructure.repository;

import com.campeoes.cadastro_campeoes.infrastructure.entitys.Campeao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {

    // busca um campeão pelo nome
    Optional<Campeao> findByNome(String nome);

    // deleta um campeão pelo nome
    void deleteByNome(String nome);
}