package com.lol.lol_counter.business;

import com.lol.lol_counter.infrastructure.entitys.Matchup;
import com.lol.lol_counter.infrastructure.repository.MatchupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchupService {

    private final MatchupRepository repository;

    public MatchupService(MatchupRepository repository) {
        this.repository = repository;
    }

    public Matchup salvarMatchup(Matchup matchup) {
        return repository.save(matchup);
    }

    public List<Matchup> buscarCountersPorCampeao(String nomeCampeao) {
        return repository.findByCampeaoNomeIgnoreCase(nomeCampeao);
    }

    public List<Matchup> listarTodos() {
        return repository.findAll();
    }

}