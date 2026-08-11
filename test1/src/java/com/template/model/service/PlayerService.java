package com.template.model.service;

import com.template.model.functions.AtualizarPlayerFunction;
import com.template.model.functions.CadastrarPlayerFunction;
import com.template.model.functions.ExcluirPlayerFunction;
import com.template.model.functions.ListarPlayersFunction;
import com.template.model.dto.PlayerDTO;
import javafx.collections.ObservableList;

public class PlayerService {

    private final CadastrarPlayerFunction cadastrarPlayerFunction = new CadastrarPlayerFunction();
    private final AtualizarPlayerFunction atualizarPlayerFunction = new AtualizarPlayerFunction();
    private final ExcluirPlayerFunction excluirPlayerFunction = new ExcluirPlayerFunction();
    private final ListarPlayersFunction listarPlayersFunction = new ListarPlayersFunction();

    public PlayerDTO cadastrarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        return cadastrarPlayerFunction.executar(
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
        atualizarPlayerFunction.executar(
                playerExistente, nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );
    }

    public void excluirPlayer(int id) {
        excluirPlayerFunction.executar(id);
    }

    public ObservableList<PlayerDTO> listarTodos() {
        return listarPlayersFunction.executar();
    }
}