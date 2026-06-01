package com.template;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {

    // --- botões ---
    @FXML private Button btnCadastrar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar; // <-- Adicionado o mapeamento do botão Limpar

    // --- campos de texto ---
    @FXML private TextField txtNickname;
    @FXML private TextField txtTag;
    @FXML private TextField txtSenha;
    @FXML private TextField txtEmail;
    @FXML private TextField txtLevel;
    @FXML private TextField txtElo;
    @FXML private TextField txtRolePrincipal;
    @FXML private TextField txtRoleSecundaria;
    @FXML private TextField txtChampionFavorito;
    @FXML private TextField txtServidor;

    // --- tabela ---
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

    @FXML private void initialize() {
        System.out.println("FXML loaded successfully!");
    }

    // --- métodos de ação ---

    @FXML void btnLimparAction(ActionEvent event) {
        limparCampos();
        System.out.println("Campos limpos pelo usuário.");
    }

    @FXML void btnCadastrarAction(ActionEvent event) {
        PlayerDAO playerDAO = new PlayerDAO();
        PlayerDTO novoPlayer = new PlayerDTO();

        try {
            novoPlayer.setNickname(txtNickname.getText());
            novoPlayer.setTag(txtTag.getText());
            novoPlayer.setSenha(txtSenha.getText());
            novoPlayer.setEmail(txtEmail.getText());

            String levelTexto = txtLevel.getText();
            int level = (levelTexto == null || levelTexto.trim().isEmpty()) ? 0 : Integer.parseInt(levelTexto.trim());
            novoPlayer.setLevel(level);

            novoPlayer.setElo(txtElo.getText());
            novoPlayer.setRole_principal(txtRolePrincipal.getText());
            novoPlayer.setRole_secundaria(txtRoleSecundaria.getText());
            novoPlayer.setChampion_favorito(txtChampionFavorito.getText());
            novoPlayer.setServidor(txtServidor.getText());

            playerDAO.insertPlayer(novoPlayer);

            limparCampos();
            System.out.println("Conta cadastrada com sucesso via interface!");

        } catch (NumberFormatException e) {
            System.out.println("Erro: O campo Level precisa ser um número inteiro válido.");
        }
    }

    @FXML void btnAlterarAction(ActionEvent event) {
        PlayerDTO playerSelecionado = tabelaContas.getSelectionModel().getSelectedItem();

        if (playerSelecionado != null) {
            PlayerDAO playerDAO = new PlayerDAO();

            playerSelecionado.setNickname(txtNickname.getText());
            playerSelecionado.setTag(txtTag.getText());
            playerSelecionado.setSenha(txtSenha.getText());
            playerSelecionado.setEmail(txtEmail.getText());
            playerSelecionado.setLevel(Integer.parseInt(txtLevel.getText().trim()));
            playerSelecionado.setElo(txtElo.getText());
            playerSelecionado.setRole_principal(txtRolePrincipal.getText());
            playerSelecionado.setRole_secundaria(txtRoleSecundaria.getText());
            playerSelecionado.setChampion_favorito(txtChampionFavorito.getText());
            playerSelecionado.setServidor(txtServidor.getText());

            playerDAO.updatePlayer(playerSelecionado);

            limparCampos();
            System.out.println("Conta alterada com sucesso!");
        } else {
            System.out.println("Por favor, selecione uma conta na tabela para alterar.");
        }
    }

    @FXML void btnExcluirAction(ActionEvent event) {
        PlayerDTO playerSelecionado = tabelaContas.getSelectionModel().getSelectedItem();

        if (playerSelecionado != null) {
            PlayerDAO playerDAO = new PlayerDAO();

            playerDAO.deletePlayer(playerSelecionado.getId());

            limparCampos();
            System.out.println("Conta excluída com sucesso!");
        } else {
            System.out.println("Por favor, selecione uma conta na tabela para excluir.");
        }
    }

    private void limparCampos() {
        txtNickname.clear();
        txtTag.clear();
        txtSenha.clear();
        txtEmail.clear();
        txtLevel.clear();
        txtElo.clear();
        txtRolePrincipal.clear();
        txtRoleSecundaria.clear();
        txtChampionFavorito.clear();
        txtServidor.clear();
    }
}