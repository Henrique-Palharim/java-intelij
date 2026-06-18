package com.template;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.PasswordField;

public class MainController {

    // --- botões ---
    @FXML private Button btnCadastrar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;

    // --- campos de texto ---
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

        tabelaContas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> carregarCampos(newValue));

        configurarValidacoesBototes();

        carregarTabela();
    }

    // --- métodos ---
    private void configurarValidacoesBototes() {

        BooleanBinding camposInvalidos = Bindings.createBooleanBinding(() ->
                        campoEstaVazio(txtNickname) || campoEstaVazio(txtTag) || campoEstaVazio(txtSenha) ||
                                campoEstaVazio(txtEmail) || campoEstaVazio(txtLevel) || campoEstaVazio(txtElo) ||
                                campoEstaVazio(txtRolePrincipal) || campoEstaVazio(txtRoleSecundaria) ||
                                campoEstaVazio(txtChampionFavorito) || campoEstaVazio(txtServidor),

                txtNickname.textProperty(), txtTag.textProperty(), txtSenha.textProperty(),
                txtEmail.textProperty(), txtLevel.textProperty(), txtElo.textProperty(),
                txtRolePrincipal.textProperty(), txtRoleSecundaria.textProperty(),
                txtChampionFavorito.textProperty(), txtServidor.textProperty()
        );

        btnCadastrar.disableProperty().bind(camposInvalidos);

        btnAlterar.disableProperty().bind(
                camposInvalidos.or(tabelaContas.getSelectionModel().selectedItemProperty().isNull())
        );

        BooleanBinding todosCamposVazios = Bindings.createBooleanBinding(() ->
                        campoEstaVazio(txtNickname) && campoEstaVazio(txtTag) && campoEstaVazio(txtSenha) &&
                                campoEstaVazio(txtEmail) && campoEstaVazio(txtLevel) && campoEstaVazio(txtElo) &&
                                campoEstaVazio(txtRolePrincipal) && campoEstaVazio(txtRoleSecundaria) &&
                                campoEstaVazio(txtChampionFavorito) && campoEstaVazio(txtServidor),

                txtNickname.textProperty(), txtTag.textProperty(), txtSenha.textProperty(),
                txtEmail.textProperty(), txtLevel.textProperty(), txtElo.textProperty(),
                txtRolePrincipal.textProperty(), txtRoleSecundaria.textProperty(),
                txtChampionFavorito.textProperty(), txtServidor.textProperty()
        );

        btnLimpar.disableProperty().bind(todosCamposVazios);

        btnExcluir.disableProperty().bind(tabelaContas.getSelectionModel().selectedItemProperty().isNull());
    }

    private boolean campoEstaVazio(TextField txt) {
        return txt.getText() == null || txt.getText().trim().isEmpty();
    }

    private void carregarTabela() {
        PlayerDAO playerDAO = new PlayerDAO();
        tabelaContas.setItems(playerDAO.listarTodos());
    }

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

            Alert alertaSucesso = new Alert(Alert.AlertType.INFORMATION);
            alertaSucesso.setTitle("Cadastro Realizado");
            alertaSucesso.setHeaderText(null);
            alertaSucesso.setContentText("O player " + novoPlayer.getNickname() + "#" + novoPlayer.getTag() + " foi cadastrado com sucesso!");
            alertaSucesso.showAndWait();

            limparCampos();
            carregarTabela();
            // System.out.println("Conta cadastrada com sucesso via interface!");

        } catch (NumberFormatException e) {
            Alert alertaErro = new Alert(Alert.AlertType.ERROR);
            alertaErro.setTitle("Erro no Formulário");
            alertaErro.setHeaderText("Valor inválido");
            alertaErro.setContentText("O campo 'Level' precisa ser um número inteiro válido.");
            alertaErro.showAndWait();

            // System.out.println("Erro: O campo Level precisa ser um número inteiro válido.");
        }
    }

    @FXML void btnAlterarAction(ActionEvent event) {
        PlayerDTO playerSelecionado = tabelaContas.getSelectionModel().getSelectedItem();

        if (playerSelecionado != null) {

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação de Alteração");
            alerta.setHeaderText("Você está prestes a alterar os dados deste player.");
            alerta.setContentText("Deseja salvar as alterações para: " + playerSelecionado.getNickname() + "?");

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
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
                carregarTabela();
                System.out.println("Conta alterada com sucesso!");

                // alerta de sucesso
                mostrarAlertaInformativo("Sucesso", "Jogador atualizado com sucesso!");
            } else {
                System.out.println("Alteração cancelada pelo usuário.");
            }
        } else {
            System.out.println("Por favor, selecione uma conta na tabela para alterar.");
        }
    }

    @FXML void btnExcluirAction(ActionEvent event) {
        PlayerDTO playerSelecionado = tabelaContas.getSelectionModel().getSelectedItem();

        if (playerSelecionado != null) {

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação de Exclusão");
            alerta.setHeaderText("Atenção! Esta ação não pode ser desfeita.");
            alerta.setContentText("Tem certeza que deseja excluir o player: " + playerSelecionado.getNickname() + "#" + playerSelecionado.getTag() + "?");

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                PlayerDAO playerDAO = new PlayerDAO();

                playerDAO.deletePlayer(playerSelecionado.getId());

                limparCampos();
                carregarTabela();
                System.out.println("Conta excluída com sucesso!");

                // alerta de sucesso
                mostrarAlertaInformativo("Sucesso", "Jogador excluído com sucesso!");
            } else {
                System.out.println("Exclusão cancelada pelo usuário.");
            }
        } else {
            System.out.println("Por favor, selecione uma conta na tabela para excluir.");
        }
    }

    private void carregarCampos(PlayerDTO objPlayerDTO) {
        if (objPlayerDTO != null) {
            txtNickname.setText(objPlayerDTO.getNickname());
            txtTag.setText(objPlayerDTO.getTag());
            txtSenha.setText(objPlayerDTO.getSenha());
            txtEmail.setText(objPlayerDTO.getEmail());

            txtLevel.setText(String.valueOf(objPlayerDTO.getLevel()));

            txtElo.setText(objPlayerDTO.getElo());
            txtRolePrincipal.setText(objPlayerDTO.getRole_principal());
            txtRoleSecundaria.setText(objPlayerDTO.getRole_secundaria());
            txtChampionFavorito.setText(objPlayerDTO.getChampion_favorito());
            txtServidor.setText(objPlayerDTO.getServidor());
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

        tabelaContas.getSelectionModel().clearSelection();
    }

    private void mostrarAlertaInformativo(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}