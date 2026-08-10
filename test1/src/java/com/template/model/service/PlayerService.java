package com.template.model.service;

import com.template.model.dao.PlayerDAO;
import com.template.model.dto.PlayerDTO;
import com.template.model.validador.ContaValidador;
import javafx.collections.ObservableList;

public class PlayerService {

    private final PlayerDAO playerDAO = new PlayerDAO();

    public PlayerDTO cadastrarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {

        PlayerDTO player = ContaValidador.criarPlayer(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        playerDAO.insertPlayer(player);
        return player;
    }

    public void atualizarPlayer(
            PlayerDTO playerExistente,
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {

        PlayerDTO dadosAtualizados = ContaValidador.criarPlayer(
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

    public void excluirPlayer(int id) {
        playerDAO.deletePlayer(id);
    }

    public ObservableList<PlayerDTO> listarTodos() {
        return playerDAO.listarTodos();
    }
}