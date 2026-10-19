
package com.campeoes.cadastro_campeoes.business;

import com.campeoes.cadastro_campeoes.infrastructure.entitys.Campeao;
import com.campeoes.cadastro_campeoes.infrastructure.repository.CampeaoRepository;
import org.springframework.stereotype.Service;

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

    public Campeao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Campeão não encontrado"));
    }

    public Campeao atualizar(Long id, Campeao dados) {
        Campeao campeao = buscarPorId(id);

        campeao.setNome(dados.getNome());
        campeao.setRota(dados.getRota());
        campeao.setClasse(dados.getClasse());
        campeao.setRegiao(dados.getRegiao());

        return repository.save(campeao);
    }

    public void excluir(Long id) {
        Campeao campeao = buscarPorId(id);
        repository.delete(campeao);
    }
}
