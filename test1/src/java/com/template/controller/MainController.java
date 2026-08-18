package com.template.controller;

import com.template.model.functions.PlayerFunctions;
import com.template.model.dto.PlayerDTO;
import com.template.model.service.PlayerService;
import com.template.util.DialogUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private final PlayerService playerService = new PlayerService();

    @FXML private Button btnCadastrar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;

    @FXML private TextField txtNickname;
    @FXML private TextField txtTag;
    @FXML private PasswordField txtSenha;
    @FXML private TextField txtEmail;
    @FXML private TextField txtLevel;
    @FXML private TextField txtElo;
    @FXML private TextField txtRolePrincipal;
    @FXML private TextField txtRoleSecundaria;
    @FXML private TextField txtChampionFavorito;
    @FXML private TextField txtServidor;

    @FXML private TableView<PlayerDTO> tabelaContas;

    @FXML private TableColumn<PlayerDTO, String> colNickname;
    @FXML private TableColumn<PlayerDTO, String> colTag;
    @FXML private TableColumn<PlayerDTO, String> colSenha;
    @FXML private TableColumn<PlayerDTO, String> colEmail;
    @FXML private TableColumn<PlayerDTO, Integer> colLevel;
    @FXML private TableColumn<PlayerDTO, String> colElo;
    @FXML private TableColumn<PlayerDTO, String> colRolePrincipal;
    @FXML private TableColumn<PlayerDTO, String> colRoleSecundaria;
    @FXML private TableColumn<PlayerDTO, String> colChampionFavorito;
    @FXML private TableColumn<PlayerDTO, String> colServidor;

    private List<TextField> todosCampos;

    @FXML
    private void initialize() {
        todosCampos = Arrays.asList(
                txtNickname, txtTag, txtSenha, txtEmail, txtLevel,
                txtElo, txtRolePrincipal, txtRoleSecundaria, txtChampionFavorito, txtServidor
        );

        colNickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        colTag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        colElo.setCellValueFactory(new PropertyValueFactory<>("elo"));
        colRolePrincipal.setCellValueFactory(new PropertyValueFactory<>("role_principal"));
        colRoleSecundaria.setCellValueFactory(new PropertyValueFactory<>("role_secundaria"));
        colChampionFavorito.setCellValueFactory(new PropertyValueFactory<>("champion_favorito"));
        colServidor.setCellValueFactory(new PropertyValueFactory<>("servidor"));

        tabelaContas.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) ->
                        PlayerFunctions.carregarCamposFormulario(
                                newValue, txtNickname, txtTag, txtSenha, txtEmail, txtLevel,
                                txtElo, txtRolePrincipal, txtRoleSecundaria, txtChampionFavorito, txtServidor
                        )
                );

        PlayerFunctions.configurarValidacoesBotoes(todosCampos, tabelaContas, btnCadastrar, btnLimpar, btnAlterar, btnExcluir);
        carregarTabela();

        LOGGER.info("Tela inicializada.");
    }

    private void carregarTabela() {
        tabelaContas.setItems(playerService.listarTodos());
    }

    @FXML
    void btnLimparAction(ActionEvent event) {
        PlayerFunctions.limparCamposFormulario(todosCampos, tabelaContas);
        LOGGER.info("Campos limpos.");
    }

    @FXML
    void btnCadastrarAction(ActionEvent event) {
        try {
            playerService.cadastrarPlayer(
                    txtNickname.getText(), txtTag.getText(), txtSenha.getText(),
                    txtEmail.getText(), txtLevel.getText(), txtElo.getText(),
                    txtRolePrincipal.getText(), txtRoleSecundaria.getText(),
                    txtChampionFavorito.getText(), txtServidor.getText()
            );

            DialogUtil.showInfo("Cadastro realizado", "Player cadastrado com sucesso!");
            PlayerFunctions.limparCamposFormulario(todosCampos, tabelaContas);
            carregarTabela();

        } catch (IllegalArgumentException e) {
            DialogUtil.showError("Erro de validação", e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar jogador.", e);
            DialogUtil.showError("Erro", "Não foi possível cadastrar o jogador.");
        }
    }

    @FXML
    void btnAlterarAction(ActionEvent event) {
        PlayerDTO player = tabelaContas.getSelectionModel().getSelectedItem();

        if (player == null) {
            DialogUtil.showError("Erro", "Selecione um jogador.");
            return;
        }

        if (!DialogUtil.showConfirmation("Alteração", "Deseja salvar as alterações de " + player.getNickname() + "?")) {
            LOGGER.info("Alteração cancelada pelo usuário.");
            return;
        }

        try {
            playerService.atualizarPlayer(
                    player,
                    txtNickname.getText(), txtTag.getText(), txtSenha.getText(),
                    txtEmail.getText(), txtLevel.getText(), txtElo.getText(),
                    txtRolePrincipal.getText(), txtRoleSecundaria.getText(),
                    txtChampionFavorito.getText(), txtServidor.getText()
            );

            PlayerFunctions.limparCamposFormulario(todosCampos, tabelaContas);
            carregarTabela();

            DialogUtil.showInfo("Sucesso", "Jogador atualizado com sucesso!");
            LOGGER.info("Jogador atualizado: " + player.getNickname());

        } catch (IllegalArgumentException e) {
            DialogUtil.showError("Erro de validação", e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar jogador.", e);
            DialogUtil.showError("Erro", "Não foi possível atualizar o jogador.");
        }
    }

    @FXML
    void btnExcluirAction(ActionEvent event) {
        PlayerDTO player = tabelaContas.getSelectionModel().getSelectedItem();

        if (player == null) {
            DialogUtil.showError("Erro", "Selecione um jogador.");
            return;
        }

        if (!DialogUtil.showConfirmation("Excluir jogador", "Deseja excluir " + player.getNickname() + "#" + player.getTag() + "?")) {
            LOGGER.info("Exclusão cancelada pelo usuário.");
            return;
        }

        try {
            playerService.excluirPlayer(player.getId());

            PlayerFunctions.limparCamposFormulario(todosCampos, tabelaContas);
            carregarTabela();

            DialogUtil.showInfo("Sucesso", "Jogador excluído com sucesso!");
            LOGGER.info("Jogador excluído: " + player.getNickname());

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir jogador.", e);
            DialogUtil.showError("Erro", "Não foi possível excluir o jogador.");
        }
    }
}