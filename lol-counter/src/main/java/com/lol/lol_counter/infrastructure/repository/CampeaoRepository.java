package com.lol.lol_counter.infrastructure.repository;

import com.lol.lol_counter.infrastructure.entitys.Campeao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {

    Optional<Campeao> findByNomeIgnoreCase(String nome);

}