package com.template.model.validador;

import com.template.model.functions.CampoVazioFunction;
import com.template.model.functions.CriarPlayerFunction;
import com.template.model.functions.ValidarCamposObrigatoriosFunction;
import com.template.model.functions.ValidarLevelFunction;
import com.template.model.dto.PlayerDTO;

public class ContaValidador {

    public static boolean campoVazio(String valor) {
        return CampoVazioFunction.executar(valor);
    }

    public static void validarCamposObrigatorios(String... campos) {
        ValidarCamposObrigatoriosFunction.executar(campos);
    }

    public static int validarLevel(String level) {
        return ValidarLevelFunction.executar(level);
    }

    public static PlayerDTO criarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        return CriarPlayerFunction.executar(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );
    }
}