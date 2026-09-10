package org.example.fast_app.controller;

import java.io.File;
import java.math.BigDecimal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.example.fast_app.model.Categoria;
import org.example.fast_app.model.Producto;

public class ProductoController {

    @FXML private TextField txtCodigo, txtNombre, txtPrecio, txtExistencia;
    @FXML private ComboBox<Categoria> cmbCategoria;
    @FXML private CheckBox chkActivo;
    @FXML private ImageView imgProducto;
    @FXML private TableView<Producto> tblProductos;

    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Categoria> colCategoria;
    @FXML private TableColumn<Producto, BigDecimal> colPrecio;
    @FXML private TableColumn<Producto, Integer> colExistencia;
    @FXML private TableColumn<Producto, Boolean> colActivo;

    private final ObservableList<Producto> productos = FXCollections.observableArrayList();
    private String rutaImagen;

    @FXML
    private void initialize() {
        // Mapeo de columnas
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

        // Carga de categorías en el ComboBox
        cmbCategoria.setItems(FXCollections.observableArrayList());

        tblProductos.setItems(productos);
        chkActivo.setSelected(true);
    }

    // Caso 6: Seleccionar imagen y mostrarla en ImageView
    @FXML
    private void seleccionarImagen() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleccionar Imagen");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File archivo = chooser.showOpenDialog(txtCodigo.getScene().getWindow());
        if (archivo != null) {
            rutaImagen = archivo.toURI().toString();
            imgProducto.setImage(new Image(rutaImagen));
        }
    }

    @FXML
    private void guardar() {
        // Validación de campos vacíos
        if (txtCodigo.getText().isBlank() || txtNombre.getText().isBlank()
                || txtPrecio.getText().isBlank() || txtExistencia.getText().isBlank()
                || cmbCategoria.getValue() == null) {
            mensaje(Alert.AlertType.WARNING, "Complete los campos obligatorios.");
            return;
        }

        try {
            BigDecimal precio = new BigDecimal(txtPrecio.getText().trim());
            int existencia = Integer.parseInt(txtExistencia.getText().trim());

            // Caso 5: Precio menor/igual a cero o existencia negativa
            if (precio.compareTo(BigDecimal.ZERO) <= 0 || existencia < 0) {
                mensaje(Alert.AlertType.WARNING, "El precio debe ser mayor que cero y la existencia no puede ser negativa.");
                return;
            }

            // Caso 7: Registro de datos válidos en TableView
            productos.add(new Producto(
                    null,
                    txtCodigo.getText().trim(),
                    txtNombre.getText().trim(),
                    cmbCategoria.getValue(),
                    precio,
                    existencia,
                    rutaImagen,
                    chkActivo.isSelected()
            ));

            mensaje(Alert.AlertType.INFORMATION, "Producto agregado correctamente.");
            limpiar();

        } catch (NumberFormatException e) {
            // Caso 4: Texto ingresado en precio o existencia
            mensaje(Alert.AlertType.ERROR, "Precio o existencia no válidos. Ingrese valores numéricos.");
        }
    }

    @FXML
    private void cerrar() {
        ((Stage) txtCodigo.getScene().getWindow()).close();
    }

    private void limpiar() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtExistencia.clear();
        cmbCategoria.getSelectionModel().clearSelection();
        chkActivo.setSelected(true);
        imgProducto.setImage(null);
        rutaImagen = null;
    }

    private void mensaje(Alert.AlertType tipo, String texto) {
        new Alert(tipo, texto, ButtonType.OK).showAndWait();
    }
}