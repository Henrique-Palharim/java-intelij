package com.template.model.service;

import com.template.model.functions.PlayerFunctions;
import com.template.model.dto.PlayerDTO;
import javafx.collections.ObservableList;

public class PlayerService {

    public PlayerDTO cadastrarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        return PlayerFunctions.cadastrarPlayer(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );
    }

    public void atualizarPlayer(
            PlayerDTO playerExistente,
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        PlayerFunctions.atualizarPlayer(
                playerExistente, nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );
    }

    public void excluirPlayer(int id) {
        PlayerFunctions.excluirPlayer(id);
    }

    public ObservableList<PlayerDTO> listarTodos() {
        return PlayerFunctions.listarPlayers();
    }
}