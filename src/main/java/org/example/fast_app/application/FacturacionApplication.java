package org.example.fast_app.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacturacionApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/fast_app/fxml/menu-principal.fxml"));
        stage.setTitle("Sistema de Facturación");
        stage.setScene(new Scene(loader.load(), 900, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}