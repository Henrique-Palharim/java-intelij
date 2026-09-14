package com.template.main;

import com.template.controller.MainController;
import com.template.model.dao.PlayerDAO;
import com.template.model.service.IPlayerService;
import com.template.model.service.PlayerService;
import com.template.model.validador.ContaValidador; // Import alterado
import com.template.model.validador.IPlayerValidador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/com/template/main.fxml")
        );

        IPlayerValidador validador = new ContaValidador();
        PlayerDAO playerDAO = new PlayerDAO();
        IPlayerService playerService = new PlayerService(validador, playerDAO);

        loader.setControllerFactory(clazz -> {
            if (clazz == MainController.class) {
                return new MainController(playerService);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(loader.load(), 1450, 850);

        String cssFocus = "data:text/css," +
                ".text-field:focused {" +
                "  -fx-background-color: #211B3D;" +
                "  -fx-text-fill: #000000;" +
                "  -fx-border-color: #63D0FF;" +
                "  -fx-border-width: 2px;" +
                "  -fx-border-radius: 3px;" +
                "}";
        scene.getStylesheets().add(cssFocus);

        stage.setTitle("Contas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}