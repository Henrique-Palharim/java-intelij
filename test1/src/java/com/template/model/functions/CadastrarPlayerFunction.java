package com.template.model.functions;

import com.template.model.dao.PlayerDAO;
import com.template.model.dto.PlayerDTO;

public class CadastrarPlayerFunction {

    private final PlayerDAO playerDAO = new PlayerDAO();

    public PlayerDTO executar(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        PlayerDTO player = CriarPlayerFunction.executar(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        playerDAO.insertPlayer(player);
        return player;
    }
}