package com.lol.lol_counter.infrastructure.repository;

import com.lol.lol_counter.infrastructure.entitys.Matchup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchupRepository extends JpaRepository<Matchup, Long> {

    List<Matchup> findByCampeaoNomeIgnoreCase(String nomeCampeao);

}