package com.template.model.validador;

import com.template.model.dto.PlayerDTO;
import java.util.ArrayList;
import java.util.List;

public class ContaValidador implements IPlayerValidador {

    public static boolean campoVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    @Override
    public PlayerDTO criarEValidarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        ValidarCamposObrigatorios vNickname = new ValidarCamposObrigatorios("Nickname", nickname);
        ValidarCamposObrigatorios vTag = new ValidarCamposObrigatorios("Tag", tag);
        ValidarCamposObrigatorios vSenha = new ValidarCamposObrigatorios("Senha", senha);
        ValidarCamposObrigatorios vElo = new ValidarCamposObrigatorios("Elo", elo);
        ValidarCamposObrigatorios vRoleMain = new ValidarCamposObrigatorios("Role Principal", rolePrincipal);
        ValidarCamposObrigatorios vRoleSec = new ValidarCamposObrigatorios("Role Secundária", roleSecundaria);
        ValidarCamposObrigatorios vChamp = new ValidarCamposObrigatorios("Champion Favorito", championFavorito);
        ValidarCamposObrigatorios vServidor = new ValidarCamposObrigatorios("Servidor", servidor);

        EmailValidador vEmail = new EmailValidador(email);
        NumInteiroValidador vLevel = new NumInteiroValidador("Level", level, 1);

        List<Validador<?>> validadores = new ArrayList<>();
        validadores.add(vNickname);
        validadores.add(vTag);
        validadores.add(vSenha);
        validadores.add(vElo);
        validadores.add(vRoleMain);
        validadores.add(vRoleSec);
        validadores.add(vChamp);
        validadores.add(vServidor);
        validadores.add(vEmail);
        validadores.add(vLevel);

        for (Validador<?> validador : validadores) {
            if (!validador.validar()) {
                throw new IllegalArgumentException(validador.getErrorMessage());
            }
        }

        // montagem do DTO utilizando getValor()
        PlayerDTO player = new PlayerDTO();
        player.setNickname(vNickname.getValor());
        player.setTag(vTag.getValor().toUpperCase());
        player.setSenha(vSenha.getValor());
        player.setEmail(vEmail.getValor());
        player.setLevel(Integer.parseInt(vLevel.getValor()));
        player.setElo(vElo.getValor());
        player.setRole_principal(vRoleMain.getValor());
        player.setRole_secundaria(vRoleSec.getValor());
        player.setChampion_favorito(vChamp.getValor());
        player.setServidor(vServidor.getValor());

        return player;
    }
}