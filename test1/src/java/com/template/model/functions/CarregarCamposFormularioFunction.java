package com.template.model.functions;

import com.template.model.dto.PlayerDTO;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CarregarCamposFormularioFunction {

    public static void executar(
            PlayerDTO player,
            TextField txtNickname, TextField txtTag, PasswordField txtSenha,
            TextField txtEmail, TextField txtLevel, TextField txtElo,
            TextField txtRolePrincipal, TextField txtRoleSecundaria,
            TextField txtChampionFavorito, TextField txtServidor
    ) {
        if (player == null) return;

        txtNickname.setText(player.getNickname());
        txtTag.setText(player.getTag());
        txtSenha.setText(player.getSenha());
        txtEmail.setText(player.getEmail());
        txtLevel.setText(String.valueOf(player.getLevel()));
        txtElo.setText(player.getElo());
        txtRolePrincipal.setText(player.getRole_principal());
        txtRoleSecundaria.setText(player.getRole_secundaria());
        txtChampionFavorito.setText(player.getChampion_favorito());
        txtServidor.setText(player.getServidor());
    }
}