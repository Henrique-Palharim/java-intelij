package com.lol.lol_counter.business;

import com.lol.lol_counter.infrastructure.entitys.Campeao;
import com.lol.lol_counter.infrastructure.repository.CampeaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeaoService {

    private final CampeaoRepository repository;

    public CampeaoService(CampeaoRepository repository) {
        this.repository = repository;
    }

    public Campeao salvar(Campeao campeao) {
        return repository.save(campeao);
    }

    public List<Campeao> listarTodos() {
        return repository.findAll();
    }

    public Campeao buscarPorNome(String nome) {
        return repository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new RuntimeException("Campeão não encontrado: " + nome));
    }

}