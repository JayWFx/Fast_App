package org.example.fast_app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fast_app.model.Cargo;
import org.example.fast_app.model.Empleado;

import java.time.LocalDate;

public class EmpleadoController {

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<Cargo> cmbCargo;
    @FXML private DatePicker dtpFechaContratacion;
    @FXML private CheckBox chkActivo;

    @FXML private TableView<Empleado> tblEmpleados;
    @FXML private TableColumn<Empleado, Integer> colId;
    @FXML private TableColumn<Empleado, String> colNombres;
    @FXML private TableColumn<Empleado, String> colApellidos;
    @FXML private TableColumn<Empleado, Cargo> colCargo;
    @FXML private TableColumn<Empleado, LocalDate> colFechaContratacion;
    @FXML private TableColumn<Empleado, Boolean> colActivo;

    private final ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();
    private int contadorId = 3;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colFechaContratacion.setCellValueFactory(new PropertyValueFactory<>("fechaContratacion"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

        cmbCargo.setItems(FXCollections.observableArrayList());
        tblEmpleados.setItems(listaEmpleados);
        chkActivo.setSelected(true);
        dtpFechaContratacion.setValue(LocalDate.now());
    }

    @FXML
    private void guardar() {
        if (txtNombres.getText().isBlank() || txtApellidos.getText().isBlank() || cmbCargo.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Complete los campos obligatorios.");
            return;
        }

        Empleado emp = new Empleado(
                contadorId++,
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                cmbCargo.getValue(),
                dtpFechaContratacion.getValue() != null ? dtpFechaContratacion.getValue() : LocalDate.now(),
                chkActivo.isSelected()
        );

        listaEmpleados.add(emp);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Empleado registrado correctamente.");
        limpiar();
    }

    @FXML
    private void cerrar() {
        ((Stage) txtNombres.getScene().getWindow()).close();
    }

    private void limpiar() {
        txtNombres.clear();
        txtApellidos.clear();
        cmbCargo.getSelectionModel().clearSelection();
        dtpFechaContratacion.setValue(LocalDate.now());
        chkActivo.setSelected(true);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String msg) {
        new Alert(tipo, msg, ButtonType.OK).showAndWait();
    }
}
