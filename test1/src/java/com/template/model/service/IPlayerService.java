package com.template.model.service;

import com.template.model.dto.PlayerDTO;
import javafx.collections.ObservableList;

public interface IPlayerService {
    PlayerDTO cadastrarPlayer(String nickname, String tag, String senha, String email, String level, String elo, String rolePrincipal, String roleSecundaria, String championFavorito, String servidor);
    void atualizarPlayer(PlayerDTO playerExistente, String nickname, String tag, String senha, String email, String level, String elo, String rolePrincipal, String roleSecundaria, String championFavorito, String servidor);
    void excluirPlayer(int id);
    ObservableList<PlayerDTO> listarTodos();
}