package com.template.model.functions;

import com.template.model.dao.PlayerDAO;
import com.template.model.dto.PlayerDTO;

public class AtualizarPlayerFunction {

    private final PlayerDAO playerDAO = new PlayerDAO();

    public void executar(
            PlayerDTO playerExistente,
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        PlayerDTO dadosAtualizados = CriarPlayerFunction.executar(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        playerExistente.setNickname(dadosAtualizados.getNickname());
        playerExistente.setTag(dadosAtualizados.getTag());
        playerExistente.setSenha(dadosAtualizados.getSenha());
        playerExistente.setEmail(dadosAtualizados.getEmail());
        playerExistente.setLevel(dadosAtualizados.getLevel());
        playerExistente.setElo(dadosAtualizados.getElo());
        playerExistente.setRole_principal(dadosAtualizados.getRole_principal());
        playerExistente.setRole_secundaria(dadosAtualizados.getRole_secundaria());
        playerExistente.setChampion_favorito(dadosAtualizados.getChampion_favorito());
        playerExistente.setServidor(dadosAtualizados.getServidor());

        playerDAO.updatePlayer(playerExistente);
    }
}