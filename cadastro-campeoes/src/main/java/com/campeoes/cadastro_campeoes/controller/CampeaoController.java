
package com.campeoes.cadastro_campeoes.controller;

import com.campeoes.cadastro_campeoes.business.CampeaoService;
import com.campeoes.cadastro_campeoes.infrastructure.entitys.Campeao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campeoes")
public class CampeaoController {

    private final CampeaoService campeaoService;

    public CampeaoController(CampeaoService campeaoService) {
        this.campeaoService = campeaoService;
    }

    @PostMapping
    public ResponseEntity<Campeao> cadastrar(
            @RequestBody Campeao campeao) {
        Campeao salvo = campeaoService.cadastrar(campeao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Campeao>> listarTodos() {
        return ResponseEntity.ok(campeaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campeao> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(campeaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campeao> atualizar(
            @PathVariable Long id,
            @RequestBody Campeao campeao) {
        return ResponseEntity.ok(campeaoService.atualizar(id, campeao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        campeaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
