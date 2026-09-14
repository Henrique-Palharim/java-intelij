package com.template.model.validador;

import com.template.model.dto.PlayerDTO;

public interface IPlayerValidador {
    PlayerDTO criarEValidarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    );
}