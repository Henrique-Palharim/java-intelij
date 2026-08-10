package com.template.model.validador;

import com.template.model.dto.PlayerDTO;
import java.util.Arrays;

public class ContaValidador {

    public static boolean campoVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static void validarCamposObrigatorios(String... campos) {
        boolean algumVazio = Arrays.stream(campos).anyMatch(ContaValidador::campoVazio);

        if (algumVazio) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }
    }

    public static int validarLevel(String level) {
        try {
            return Integer.parseInt(level.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo Level deve conter apenas números.");
        }
    }

    public static PlayerDTO criarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {

        validarCamposObrigatorios(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        PlayerDTO player = new PlayerDTO();
        player.setNickname(nickname);
        player.setTag(tag);
        player.setSenha(senha);
        player.setEmail(email);
        player.setLevel(validarLevel(level));
        player.setElo(elo);
        player.setRole_principal(rolePrincipal);
        player.setRole_secundaria(roleSecundaria);
        player.setChampion_favorito(championFavorito);
        player.setServidor(servidor);

        return player;
    }
}