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

    // LISTAR TODOS OS CAMPEÕES
    @GetMapping("/campeoes")
    public ResponseEntity<List<Campeao>> listarTodosCampeoes() {
        return ResponseEntity.ok(campeaoService.listarTodos());
    }

    // CREATE: Cadastrar um novo Matchup
    @PostMapping
    public ResponseEntity<Matchup> cadastrarMatchup(@RequestBody Matchup matchup) {
        return ResponseEntity.ok(matchupService.salvarMatchup(matchup));
    }

    // READ: Buscar todos os counters de um determinado campeão
    @GetMapping("/{nomeCampeao}")
    public ResponseEntity<List<Matchup>> buscarCounters(@PathVariable String nomeCampeao) {
        return ResponseEntity.ok(matchupService.buscarCountersPorCampeao(nomeCampeao));
    }

    // UPDATE: Atualizar um Matchup existente pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Matchup> atualizarMatchup(@PathVariable Long id, @RequestBody Matchup matchupAtualizado) {
        return ResponseEntity.ok(matchupService.atualizarMatchup(id, matchupAtualizado));
    }

    // DELETE: Excluir um Matchup pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatchup(@PathVariable Long id) {
        matchupService.deletarMatchup(id);
        return ResponseEntity.noContent().build();
    }
}