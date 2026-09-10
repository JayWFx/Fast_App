package org.example.fast_app.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.fast_app.util.SceneManager;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private void abrirFacturacion() {
        abrirModulo("/org/example/fast_app/fxml/factura-view.fxml", "Facturación");
    }

    @FXML
    private void abrirProductos() {
        abrirModulo("/org/example/fast_app/fxml/producto-view.fxml", "Productos");
    }

    @FXML
    private void abrirCategorias() {
        abrirModulo("/org/example/fast_app/fxml/categoria-view.fxml", "Categorías");
    }

    @FXML
    private void abrirEmpleados() {
        abrirModulo("/org/example/fast_app/fxml/empleado-view.fxml", "Empleados");
    }

    private void abrirModulo(String fxmlPath, String titulo) {
        try {
            SceneManager.abrirVentana(fxmlPath, titulo);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,
                    "No fue posible abrir el formulario (" + titulo + "): " + e.getMessage(),
                    ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void salir() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Desea salir de la aplicación?", ButtonType.OK, ButtonType.CANCEL);
        if (a.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            Platform.exit();
        }
    }
}