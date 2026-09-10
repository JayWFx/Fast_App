package org.example.fast_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fast_app.model.Categoria;
import org.example.fast_app.util.DatosManager;

public class CategoriaController {

    @FXML private TextField txtNombre;
    @FXML private CheckBox chkActiva;
    @FXML private TableView<Categoria> tblCategorias;
    @FXML private TableColumn<Categoria, Integer> colId;
    @FXML private TableColumn<Categoria, String> colNombre;
    @FXML private TableColumn<Categoria, Boolean> colActiva;

    private int contadorId = 1;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colActiva.setCellValueFactory(new PropertyValueFactory<>("activa"));

        tblCategorias.setItems(DatosManager.getInstance().getCategorias());
        chkActiva.setSelected(true);
    }

    @FXML
    private void guardar() {
        if (txtNombre.getText().isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ingrese el nombre de la categoría.");
            return;
        }

        Categoria cat = new Categoria(
                contadorId++,
                txtNombre.getText().trim(),
                chkActiva.isSelected()
        );

        DatosManager.getInstance().getCategorias().add(cat);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Categoría agregada exitosamente.");
        limpiar();
    }

    @FXML
    private void cerrar() {
        ((Stage) txtNombre.getScene().getWindow()).close();
    }

    private void limpiar() {
        txtNombre.clear();
        chkActiva.setSelected(true);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String msg) {
        new Alert(tipo, msg, ButtonType.OK).showAndWait();
    }
}
