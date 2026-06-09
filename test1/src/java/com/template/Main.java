package com.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));

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

    public static void main(String[] args)
    {
        launch();
    }
}