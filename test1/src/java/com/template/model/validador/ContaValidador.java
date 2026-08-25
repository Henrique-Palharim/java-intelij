package com.template.model.validador;

import com.template.model.dto.PlayerDTO;
import java.util.ArrayList;
import java.util.List;

public class ContaValidador {

    public static boolean campoVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static PlayerDTO criarEValidarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        // lista de validadores em sequência
        List<Validador<String>> validadores = new ArrayList<>();

        // adicionando validadores de campos obrigatórios
        validadores.add(new ValidarCamposObrigatorios("Nickname", nickname));
        validadores.add(new ValidarCamposObrigatorios("Tag", tag));
        validadores.add(new ValidarCamposObrigatorios("Senha", senha));
        validadores.add(new ValidarCamposObrigatorios("E-mail", email));
        validadores.add(new ValidarCamposObrigatorios("Level", level));
        validadores.add(new ValidarCamposObrigatorios("Elo", elo));
        validadores.add(new ValidarCamposObrigatorios("Role Principal", rolePrincipal));
        validadores.add(new ValidarCamposObrigatorios("Role Secundária", roleSecundaria));
        validadores.add(new ValidarCamposObrigatorios("Champion Favorito", championFavorito));
        validadores.add(new ValidarCamposObrigatorios("Servidor", servidor));

        // validadores com regras específicas
        validadores.add(new EmailValidador(email));
        validadores.add(new NumInteiroValidador("Level", level, 1));

        // iteração e execução de cada validador
        for (Validador<String> validador : validadores) {
            if (!validador.validar()) {
                throw new IllegalArgumentException(validador.getErrorMessage());
            }
        }

        // retorno do DTO após passar em todos os testes
        PlayerDTO player = new PlayerDTO();
        player.setNickname(nickname.trim());
        player.setTag(tag.trim().toUpperCase());
        player.setSenha(senha);
        player.setEmail(email.trim());
        player.setLevel(Integer.parseInt(level.trim()));
        player.setElo(elo.trim());
        player.setRole_principal(rolePrincipal.trim());
        player.setRole_secundaria(roleSecundaria.trim());
        player.setChampion_favorito(championFavorito.trim());
        player.setServidor(servidor.trim());

        return player;
    }
}