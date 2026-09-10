package org.example.fast_app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fast_app.model.Cargo;

public class CargoController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TableView<Cargo> tblCargos;
    @FXML private TableColumn<Cargo, Integer> colId;
    @FXML private TableColumn<Cargo, String> colNombre;
    @FXML private TableColumn<Cargo, String> colDescripcion;

    private final ObservableList<Cargo> listaCargos = FXCollections.observableArrayList();
    private int contadorId = 4;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tblCargos.setItems(listaCargos);
    }

    @FXML
    private void guardar() {
        if (txtNombre.getText().isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ingrese el nombre del cargo.");
            return;
        }

        Cargo cargo = new Cargo(
                contadorId++,
                txtNombre.getText().trim(),
                txtDescripcion.getText().trim()
        );

        listaCargos.add(cargo);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Cargo agregado exitosamente.");
        limpiar();
    }

    @FXML
    private void cerrar() {
        ((Stage) txtNombre.getScene().getWindow()).close();
    }

    private void limpiar() {
        txtNombre.clear();
        txtDescripcion.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String msg) {
        new Alert(tipo, msg, ButtonType.OK).showAndWait();
    }
}
