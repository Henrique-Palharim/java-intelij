package com.template.model.functions;

import com.template.model.dto.PlayerDTO;
import com.template.model.validador.ContaValidador;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.util.List;

public class PlayerFunctions {

    // ---------- auxiliares de interface ----------

    public static void carregarCamposFormulario(
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

    public static void limparCamposFormulario(List<TextField> todosCampos, TableView<PlayerDTO> tabelaContas) {
        todosCampos.forEach(TextInputControl::clear);
        tabelaContas.getSelectionModel().clearSelection();
    }

    public static void configurarValidacoesBotoes(
            List<TextField> todosCampos,
            TableView<PlayerDTO> tabelaContas,
            Button btnCadastrar,
            Button btnLimpar,
            Button btnAlterar,
            Button btnExcluir
    ) {
        Property<?>[] propriedadesTexto = todosCampos.stream()
                .map(TextInputControl::textProperty)
                .toArray(Property[]::new);

        BooleanBinding algumCampoVazio = Bindings.createBooleanBinding(
                () -> todosCampos.stream().anyMatch(c -> ContaValidador.campoVazio(c.getText())),
                propriedadesTexto
        );

        BooleanBinding todosCamposVazios = Bindings.createBooleanBinding(
                () -> todosCampos.stream().allMatch(c -> ContaValidador.campoVazio(c.getText())),
                propriedadesTexto
        );

        btnCadastrar.disableProperty().bind(algumCampoVazio);
        btnLimpar.disableProperty().bind(todosCamposVazios);

        BooleanBinding nenhumItemSelecionado = tabelaContas.getSelectionModel().selectedItemProperty().isNull();
        btnAlterar.disableProperty().bind(algumCampoVazio.or(nenhumItemSelecionado));
        btnExcluir.disableProperty().bind(nenhumItemSelecionado);
    }
}