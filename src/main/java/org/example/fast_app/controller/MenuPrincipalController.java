package org.example.fast_app.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.fast_app.util.SceneManager;
import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private void abrirProductos() {
        try {
            SceneManager.abrirVentana(
                    "/org/example/fast_app/fxml/producto-view.fxml",
                    "Gestión de productos");
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,
                    "No fue posible abrir Productos: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void salir() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Desea cerrar la aplicación?", ButtonType.OK, ButtonType.CANCEL);
        if (a.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            Platform.exit();
        }
    }
}