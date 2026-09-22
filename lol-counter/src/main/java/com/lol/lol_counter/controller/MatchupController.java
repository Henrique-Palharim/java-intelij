package com.lol.lol_counter.controller;

import com.lol.lol_counter.business.CampeaoService;
import com.lol.lol_counter.business.MatchupService;
import com.lol.lol_counter.infrastructure.entitys.Campeao;
import com.lol.lol_counter.infrastructure.entitys.Matchup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matchups")
public class MatchupController {

    private final MatchupService matchupService;
    private final CampeaoService campeaoService;

    public MatchupController(MatchupService matchupService, CampeaoService campeaoService) {
        this.matchupService = matchupService;
        this.campeaoService = campeaoService;
    }

    @PostMapping("/campeao")
    public ResponseEntity<Campeao> cadastrarCampeao(@RequestBody Campeao campeao) {
        return ResponseEntity.ok(campeaoService.salvar(campeao));
    }

    @PostMapping
    public ResponseEntity<Matchup> cadastrarMatchup(@RequestBody Matchup matchup) {
        return ResponseEntity.ok(matchupService.salvarMatchup(matchup));
    }

    @GetMapping("/{nomeCampeao}")
    public ResponseEntity<List<Matchup>> buscarCounters(@PathVariable String nomeCampeao) {
        return ResponseEntity.ok(matchupService.buscarCountersPorCampeao(nomeCampeao));
    }

}