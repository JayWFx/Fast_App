package org.example.fast_app.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import java.io.IOException;

public final class SceneManager {
    private SceneManager() { }

    public static void abrirVentana(String recurso, String titulo) throws IOException {
        abrirVentana(recurso, titulo, -1, -1);
    }

    public static void abrirVentana(String recurso, String titulo, double ancho, double alto) throws IOException {
        var url = SceneManager.class.getResource(recurso);
        if (url == null) throw new IOException("recurso FXML no encontrado: " + recurso);

        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        if (ancho > 0 && alto > 0) {
            stage.setScene(new Scene(root, ancho, alto));
        } else {
            stage.setScene(new Scene(root));
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}