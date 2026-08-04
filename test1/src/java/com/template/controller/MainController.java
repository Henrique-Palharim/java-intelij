package com.template.controller;

import com.template.model.dao.PlayerDAO;
import com.template.model.dto.PlayerDTO;
import com.template.util.DialogUtil;
import com.template.validador.ContaValidador;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.logging.Level;
import java.util.logging.Logger;


public class MainController {


    private static final Logger LOGGER =
            Logger.getLogger(MainController.class.getName());


    // Botões
    @FXML private Button btnCadastrar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;


    // Campos
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


    // Tabela
    @FXML private TableView<PlayerDTO> tabelaContas;

    @FXML private TableColumn<PlayerDTO,String> colNickname;
    @FXML private TableColumn<PlayerDTO,String> colTag;
    @FXML private TableColumn<PlayerDTO,String> colSenha;
    @FXML private TableColumn<PlayerDTO,String> colEmail;
    @FXML private TableColumn<PlayerDTO,Integer> colLevel;
    @FXML private TableColumn<PlayerDTO,String> colElo;
    @FXML private TableColumn<PlayerDTO,String> colRolePrincipal;
    @FXML private TableColumn<PlayerDTO,String> colRoleSecundaria;
    @FXML private TableColumn<PlayerDTO,String> colChampionFavorito;
    @FXML private TableColumn<PlayerDTO,String> colServidor;



    @FXML
    private void initialize(){


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
                .addListener((obs,oldValue,newValue)-> carregarCampos(newValue));


        configurarValidacoesBotoes();

        carregarTabela();


        LOGGER.info("Tela inicializada.");

    }




    private void configurarValidacoesBotoes(){


        BooleanBinding camposInvalidos =
                Bindings.createBooleanBinding(() ->

                                campoEstaVazio(txtNickname) ||
                                        campoEstaVazio(txtTag) ||
                                        campoEstaVazio(txtSenha) ||
                                        campoEstaVazio(txtEmail) ||
                                        campoEstaVazio(txtLevel) ||
                                        campoEstaVazio(txtElo) ||
                                        campoEstaVazio(txtRolePrincipal) ||
                                        campoEstaVazio(txtRoleSecundaria) ||
                                        campoEstaVazio(txtChampionFavorito) ||
                                        campoEstaVazio(txtServidor),


                        txtNickname.textProperty(),
                        txtTag.textProperty(),
                        txtSenha.textProperty(),
                        txtEmail.textProperty(),
                        txtLevel.textProperty(),
                        txtElo.textProperty(),
                        txtRolePrincipal.textProperty(),
                        txtRoleSecundaria.textProperty(),
                        txtChampionFavorito.textProperty(),
                        txtServidor.textProperty()
                );


        btnCadastrar.disableProperty().bind(camposInvalidos);


        btnAlterar.disableProperty().bind(
                camposInvalidos.or(
                        tabelaContas.getSelectionModel()
                                .selectedItemProperty()
                                .isNull()
                )
        );



        BooleanBinding camposVazios =
                Bindings.createBooleanBinding(() ->

                                campoEstaVazio(txtNickname) &&
                                        campoEstaVazio(txtTag) &&
                                        campoEstaVazio(txtSenha) &&
                                        campoEstaVazio(txtEmail) &&
                                        campoEstaVazio(txtLevel) &&
                                        campoEstaVazio(txtElo) &&
                                        campoEstaVazio(txtRolePrincipal) &&
                                        campoEstaVazio(txtRoleSecundaria) &&
                                        campoEstaVazio(txtChampionFavorito) &&
                                        campoEstaVazio(txtServidor),


                        txtNickname.textProperty(),
                        txtTag.textProperty(),
                        txtSenha.textProperty(),
                        txtEmail.textProperty(),
                        txtLevel.textProperty(),
                        txtElo.textProperty(),
                        txtRolePrincipal.textProperty(),
                        txtRoleSecundaria.textProperty(),
                        txtChampionFavorito.textProperty(),
                        txtServidor.textProperty()
                );


        btnLimpar.disableProperty().bind(camposVazios);


        btnExcluir.disableProperty().bind(
                tabelaContas.getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );

    }




    private boolean campoEstaVazio(TextField campo){

        return campo.getText()==null ||
                campo.getText().trim().isEmpty();

    }




    private void carregarTabela(){

        tabelaContas.setItems(
                new PlayerDAO().listarTodos()
        );

    }




    @FXML
    void btnLimparAction(ActionEvent event){

        limparCampos();

        LOGGER.info("Campos limpos.");

    }





    @FXML
    void btnCadastrarAction(ActionEvent event){


        try{


            PlayerDTO player =
                    ContaValidador.criarPlayer(

                            txtNickname.getText(),
                            txtTag.getText(),
                            txtSenha.getText(),
                            txtEmail.getText(),
                            txtLevel.getText(),
                            txtElo.getText(),
                            txtRolePrincipal.getText(),
                            txtRoleSecundaria.getText(),
                            txtChampionFavorito.getText(),
                            txtServidor.getText()
                    );



            new PlayerDAO()
                    .insertPlayer(player);



            DialogUtil.showInfo(
                    "Cadastro realizado",
                    "Player cadastrado com sucesso!"
            );


            LOGGER.info("Player cadastrado: "
                    + player.getNickname());


            limparCampos();
            carregarTabela();



        }catch(IllegalArgumentException e){


            DialogUtil.showError(
                    "Erro de validação",
                    e.getMessage()
            );


        }catch(Exception e){


            LOGGER.log(
                    Level.SEVERE,
                    "Erro ao cadastrar jogador.",
                    e
            );


            DialogUtil.showError(
                    "Erro",
                    "Não foi possível cadastrar o jogador."
            );

        }

    }

    @FXML
    void btnAlterarAction(ActionEvent event){


        PlayerDTO player =
                tabelaContas.getSelectionModel()
                        .getSelectedItem();


        if(player == null){

            DialogUtil.showError(
                    "Erro",
                    "Selecione um jogador."
            );

            return;
        }



        if(!DialogUtil.showConfirmation(
                "Alteração",
                "Deseja salvar as alterações de "
                        + player.getNickname()
                        + "?"
        )){

            LOGGER.info("Alteração cancelada pelo usuário.");

            return;

        }



        try{


            PlayerDTO atualizado =
                    ContaValidador.criarPlayer(

                            txtNickname.getText(),
                            txtTag.getText(),
                            txtSenha.getText(),
                            txtEmail.getText(),
                            txtLevel.getText(),
                            txtElo.getText(),
                            txtRolePrincipal.getText(),
                            txtRoleSecundaria.getText(),
                            txtChampionFavorito.getText(),
                            txtServidor.getText()

                    );



            player.setNickname(
                    atualizado.getNickname()
            );

            player.setTag(
                    atualizado.getTag()
            );

            player.setSenha(
                    atualizado.getSenha()
            );

            player.setEmail(
                    atualizado.getEmail()
            );

            player.setLevel(
                    atualizado.getLevel()
            );

            player.setElo(
                    atualizado.getElo()
            );

            player.setRole_principal(
                    atualizado.getRole_principal()
            );

            player.setRole_secundaria(
                    atualizado.getRole_secundaria()
            );

            player.setChampion_favorito(
                    atualizado.getChampion_favorito()
            );

            player.setServidor(
                    atualizado.getServidor()
            );



            new PlayerDAO()
                    .updatePlayer(player);



            limparCampos();

            carregarTabela();



            DialogUtil.showInfo(
                    "Sucesso",
                    "Jogador atualizado com sucesso!"
            );



            LOGGER.info(
                    "Jogador atualizado: "
                            + player.getNickname()
            );



        }catch(IllegalArgumentException e){


            DialogUtil.showError(
                    "Erro de validação",
                    e.getMessage()
            );



        }catch(Exception e){


            LOGGER.log(
                    Level.SEVERE,
                    "Erro ao atualizar jogador.",
                    e
            );


            DialogUtil.showError(
                    "Erro",
                    "Não foi possível atualizar o jogador."
            );

        }

    }





    @FXML
    void btnExcluirAction(ActionEvent event){



        PlayerDTO player =
                tabelaContas.getSelectionModel()
                        .getSelectedItem();



        if(player == null){


            DialogUtil.showError(
                    "Erro",
                    "Selecione um jogador."
            );


            return;

        }



        if(!DialogUtil.showConfirmation(
                "Excluir jogador",
                "Deseja excluir "
                        + player.getNickname()
                        + "#"
                        + player.getTag()
                        + "?"
        )){


            LOGGER.info(
                    "Exclusão cancelada pelo usuário."
            );


            return;

        }



        try{


            new PlayerDAO()
                    .deletePlayer(player.getId());



            limparCampos();

            carregarTabela();



            DialogUtil.showInfo(
                    "Sucesso",
                    "Jogador excluído com sucesso!"
            );



            LOGGER.info(
                    "Jogador excluído: "
                            + player.getNickname()
            );



        }catch(Exception e){



            LOGGER.log(
                    Level.SEVERE,
                    "Erro ao excluir jogador.",
                    e
            );



            DialogUtil.showError(
                    "Erro",
                    "Não foi possível excluir o jogador."
            );

        }

    }






    private void carregarCampos(PlayerDTO player){


        if(player == null){

            return;

        }



        txtNickname.setText(
                player.getNickname()
        );

        txtTag.setText(
                player.getTag()
        );

        txtSenha.setText(
                player.getSenha()
        );

        txtEmail.setText(
                player.getEmail()
        );

        txtLevel.setText(
                String.valueOf(player.getLevel())
        );

        txtElo.setText(
                player.getElo()
        );

        txtRolePrincipal.setText(
                player.getRole_principal()
        );

        txtRoleSecundaria.setText(
                player.getRole_secundaria()
        );

        txtChampionFavorito.setText(
                player.getChampion_favorito()
        );

        txtServidor.setText(
                player.getServidor()
        );

    }






    private void limparCampos(){



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



        tabelaContas
                .getSelectionModel()
                .clearSelection();



        LOGGER.info(
                "Campos limpos."
        );

    }


}