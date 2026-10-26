package com.lol.lol_counter.business;

import com.lol.lol_counter.infrastructure.entitys.Campeao;
import com.lol.lol_counter.infrastructure.repository.CampeaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CampeaoService {

    private final CampeaoRepository repository;

    public CampeaoService(CampeaoRepository repository) {
        this.repository = repository;
    }

    public Campeao cadastrar(Campeao campeao) {
        return repository.save(campeao);
    }

    public List<Campeao> listarTodos() {
        return repository.findAll();
    }

    // --- métodos por ID ---

    public Campeao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeão não encontrado com ID: " + id));
    }

    public Campeao atualizar(Long id, Campeao dados) {
        Campeao campeao = buscarPorId(id);

        if (dados.getNome() != null) {
            campeao.setNome(dados.getNome());
        }
        if (dados.getRota() != null) {
            campeao.setRota(dados.getRota());
        }
        if (dados.getClasse() != null) {
            campeao.setClasse(dados.getClasse());
        }
        if (dados.getRegiao() != null) {
            campeao.setRegiao(dados.getRegiao());
        }

        return repository.save(campeao);
    }

    @Transactional
    public void excluir(Long id) {
        Campeao campeao = buscarPorId(id);
        repository.delete(campeao);
    }

    // --- métodos por NOME ---

    public Campeao buscarPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Campeão não encontrado com o nome: " + nome));
    }

    public Campeao atualizarPorNome(String nome, Campeao dados) {
        Campeao campeao = buscarPorNome(nome);

        if (dados.getNome() != null) {
            campeao.setNome(dados.getNome());
        }
        if (dados.getRota() != null) {
            campeao.setRota(dados.getRota());
        }
        if (dados.getClasse() != null) {
            campeao.setClasse(dados.getClasse());
        }
        if (dados.getRegiao() != null) {
            campeao.setRegiao(dados.getRegiao());
        }

        return repository.save(campeao);
    }

    @Transactional
    public void excluirPorNome(String nome) {
        Campeao campeao = buscarPorNome(nome); // Garante que existe antes de tentar deletar
        repository.delete(campeao);
    }
}