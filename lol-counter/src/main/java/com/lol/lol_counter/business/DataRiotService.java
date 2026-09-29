package com.lol.lol_counter.business;

import com.lol.lol_counter.infrastructure.entitys.Campeao;
import com.lol.lol_counter.infrastructure.repository.CampeaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class DataRiotService implements CommandLineRunner {

    private final CampeaoRepository campeaoRepository;

    public DataRiotService(CampeaoRepository campeaoRepository) {
        this.campeaoRepository = campeaoRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run(String... args) throws Exception {
        // Se a tabela já tiver campeões, não executa a sincronização novamente
        if (campeaoRepository.count() > 0) {
            return;
        }

        // Endpoint público do Data Dragon com a lista de campeões da Riot
        String urlRiot = "https://ddragon.leagueoflegends.com/cdn/14.1.1/data/pt_BR/champion.json";
        RestTemplate restTemplate = new RestTemplate();

        // O Spring converte o JSON automaticamente para Map
        Map<String, Object> resposta = restTemplate.getForObject(urlRiot, Map.class);

        if (resposta != null && resposta.containsKey("data")) {
            Map<String, Object> data = (Map<String, Object>) resposta.get("data");

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                Map<String, Object> campJson = (Map<String, Object>) entry.getValue();

                String nome = (String) campJson.get("name");
                String idRiot = (String) campJson.get("id");
                String imagemUrl = "https://ddragon.leagueoflegends.com/cdn/14.1.1/img/champion/" + idRiot + ".png";

                Campeao campeao = Campeao.builder()
                        .nome(nome)
                        .chaveRiot(idRiot)
                        .urlImagem(imagemUrl)
                        .build();

                campeaoRepository.save(campeao);
            }

            System.out.println(">>> Todos os campeões do League of Legends foram importados da Riot com sucesso!");
        }
    }
}