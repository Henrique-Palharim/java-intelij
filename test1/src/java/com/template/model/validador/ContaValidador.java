package com.template.model.validador;

import com.template.model.dto.PlayerDTO;

public class ContaValidador {

    public static boolean campoVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static void validarObrigatorio(String valor, String nomeCampo) {
        ValidarCamposObrigatorios validator = new ValidarCamposObrigatorios(nomeCampo);
        if (!validator.validar(valor)) {
            throw new IllegalArgumentException(validator.getErrorMessage());
        }
    }

    public static void validarEmail(String email) {
        EmailValidador validator = new EmailValidador();
        if (!validator.validar(email)) {
            throw new IllegalArgumentException(validator.getErrorMessage());
        }
    }

    public static int validarLevel(String level) {
        NumInteiroValidador validator = new NumInteiroValidador("Level", 1);
        if (!validator.validar(level)) {
            throw new IllegalArgumentException(validator.getErrorMessage());
        }
        return Integer.parseInt(level.trim());
    }

    public static void validarTag(String tag) {
        if (tag.length() < 3 || tag.length() > 5) {
            throw new IllegalArgumentException("A Tag deve ter entre 3 e 5 caracteres.");
        }
    }

    public static void validarSenha(String senha) {
        if (senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.");
        }
    }

    public static PlayerDTO criarEValidarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        // campos obrigatórios
        validarObrigatorio(nickname, "Nickname");
        validarObrigatorio(tag, "Tag");
        validarObrigatorio(senha, "Senha");
        validarObrigatorio(email, "E-mail");
        validarObrigatorio(level, "Level");
        validarObrigatorio(elo, "Elo");
        validarObrigatorio(rolePrincipal, "Role Principal");
        validarObrigatorio(roleSecundaria, "Role Secundária");
        validarObrigatorio(championFavorito, "Champion Favorito");
        validarObrigatorio(servidor, "Servidor");

        // regras específicas de validação
        validarTag(tag);
        validarSenha(senha);
        validarEmail(email);
        int levelValidado = validarLevel(level);

        PlayerDTO player = new PlayerDTO();
        player.setNickname(nickname.trim());
        player.setTag(tag.trim().toUpperCase());
        player.setSenha(senha);
        player.setEmail(email.trim());
        player.setLevel(levelValidado);
        player.setElo(elo.trim());
        player.setRole_principal(rolePrincipal.trim());
        player.setRole_secundaria(roleSecundaria.trim());
        player.setChampion_favorito(championFavorito.trim());
        player.setServidor(servidor.trim());

        return player;
    }
}