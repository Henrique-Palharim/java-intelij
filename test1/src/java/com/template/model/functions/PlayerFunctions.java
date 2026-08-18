package com.template.model.functions;

import com.template.model.dao.PlayerDAO;
import com.template.model.dto.PlayerDTO;
import com.template.model.validador.ContaValidador;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.util.List;

public class PlayerFunctions {

    private static final PlayerDAO PLAYER_DAO = new PlayerDAO();

    // ---------- OPERAÇÕES DE NEGÓCIO E PERSISTÊNCIA (DAO) ----------

    public static PlayerDTO cadastrarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        PlayerDTO player = ContaValidador.criarEValidarPlayer(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        PLAYER_DAO.insertPlayer(player);
        return player;
    }

    public static void atualizarPlayer(
            PlayerDTO playerExistente,
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        PlayerDTO dadosAtualizados = ContaValidador.criarEValidarPlayer(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        playerExistente.setNickname(dadosAtualizados.getNickname());
        playerExistente.setTag(dadosAtualizados.getTag());
        playerExistente.setSenha(dadosAtualizados.getSenha());
        playerExistente.setEmail(dadosAtualizados.getEmail());
        playerExistente.setLevel(dadosAtualizados.getLevel());
        playerExistente.setElo(dadosAtualizados.getElo());
        playerExistente.setRole_principal(dadosAtualizados.getRole_principal());
        playerExistente.setRole_secundaria(dadosAtualizados.getRole_secundaria());
        playerExistente.setChampion_favorito(dadosAtualizados.getChampion_favorito());
        playerExistente.setServidor(dadosAtualizados.getServidor());

        PLAYER_DAO.updatePlayer(playerExistente);
    }

    public static void excluirPlayer(int id) {
        PLAYER_DAO.deletePlayer(id);
    }

    public static ObservableList<PlayerDTO> listarPlayers() {
        return PLAYER_DAO.listarTodos();
    }

    // ---------- AUXILIARES DE INTERFACE ----------

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