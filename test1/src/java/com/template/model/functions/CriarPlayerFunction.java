package com.template.model.functions;

import com.template.model.dto.PlayerDTO;

public class CriarPlayerFunction {

    public static PlayerDTO executar(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        ValidarCamposObrigatoriosFunction.executar(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        PlayerDTO player = new PlayerDTO();
        player.setNickname(nickname);
        player.setTag(tag);
        player.setSenha(senha);
        player.setEmail(email);
        player.setLevel(ValidarLevelFunction.executar(level));
        player.setElo(elo);
        player.setRole_principal(rolePrincipal);
        player.setRole_secundaria(roleSecundaria);
        player.setChampion_favorito(championFavorito);
        player.setServidor(servidor);

        return player;
    }
}