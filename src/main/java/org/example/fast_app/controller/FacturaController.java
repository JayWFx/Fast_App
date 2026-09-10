package org.example.fast_app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fast_app.model.DetalleFactura;
import org.example.fast_app.model.Factura;
import org.example.fast_app.model.Producto;
import org.example.fast_app.util.DatosManager;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class FacturaController {

    @FXML private TextField txtNumFactura;
    @FXML private TextField txtCliente;
    @FXML private TextField txtNit;
    @FXML private Label lblFecha;

    @FXML private ComboBox<Producto> cmbProducto;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtPrecioUnitario;

    @FXML private TableView<DetalleFactura> tblDetalles;
    @FXML private TableColumn<DetalleFactura, String> colCodigo;
    @FXML private TableColumn<DetalleFactura, String> colProducto;
    @FXML private TableColumn<DetalleFactura, Integer> colCantidad;
    @FXML private TableColumn<DetalleFactura, BigDecimal> colPrecioUnitario;
    @FXML private TableColumn<DetalleFactura, BigDecimal> colSubtotal;

    @FXML private Label lblSubtotal;
    @FXML private Label lblImpuesto;
    @FXML private Label lblTotal;

    private final ObservableList<DetalleFactura> detalles = FXCollections.observableArrayList();
    private final Factura facturaActual = new Factura();
    private static int contadorFactura = 1001;

    @FXML
    private void initialize() {
        txtNumFactura.setText("FAC-" + contadorFactura);
        if (lblFecha != null && facturaActual.getFecha() != null) {
            lblFecha.setText(facturaActual.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        }

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        tblDetalles.setItems(detalles);

        // Enlazar combo de productos con la lista creada por el usuario
        cmbProducto.setItems(DatosManager.getInstance().getProductos());

        cmbProducto.setOnAction(e -> {
            Producto p = cmbProducto.getValue();
            if (p != null && p.getPrecioVenta() != null) {
                txtPrecioUnitario.setText(p.getPrecioVenta().toString());
            } else {
                txtPrecioUnitario.clear();
            }
        });
    }

    @FXML
    private void agregarDetalle() {
        Producto p = cmbProducto.getValue();
        if (p == null) {
            mensaje(Alert.AlertType.WARNING, "Seleccione un producto de la lista (debe agregarlo en el módulo de Productos primero).");
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad <= 0) {
                mensaje(Alert.AlertType.WARNING, "La cantidad debe ser mayor que cero.");
                return;
            }

            DetalleFactura detalle = new DetalleFactura(p, cantidad);
            detalles.add(detalle);
            actualizarTotales();

            txtCantidad.setText("1");
            cmbProducto.getSelectionModel().clearSelection();
            txtPrecioUnitario.clear();

        } catch (NumberFormatException e) {
            mensaje(Alert.AlertType.ERROR, "Cantidad no válida.");
        }
    }

    @FXML
    private void eliminarDetalle() {
        DetalleFactura seleccionado = tblDetalles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            detalles.remove(seleccionado);
            actualizarTotales();
        } else {
            mensaje(Alert.AlertType.WARNING, "Seleccione una línea de la tabla para eliminar.");
        }
    }

    private void actualizarTotales() {
        facturaActual.getDetalles().clear();
        facturaActual.getDetalles().addAll(detalles);
        facturaActual.recalcularTotales();

        lblSubtotal.setText(String.format("$%.2f", facturaActual.getSubtotal()));
        lblImpuesto.setText(String.format("$%.2f", facturaActual.getImpuesto()));
        lblTotal.setText(String.format("$%.2f", facturaActual.getTotal()));
    }

    @FXML
    private void guardarFactura() {
        if (txtCliente.getText().isBlank()) {
            mensaje(Alert.AlertType.WARNING, "Ingrese el nombre del cliente.");
            return;
        }

        if (detalles.isEmpty()) {
            mensaje(Alert.AlertType.WARNING, "Debe agregar al menos un producto a la factura.");
            return;
        }

        facturaActual.setNumeroFactura(txtNumFactura.getText());
        facturaActual.setCliente(txtCliente.getText().trim());
        facturaActual.setNit(txtNit.getText().trim());

        DatosManager.getInstance().getFacturas().add(facturaActual);

        mensaje(Alert.AlertType.INFORMATION, "Factura " + facturaActual.getNumeroFactura() + " emitida exitosamente por un total de " + String.format("$%.2f", facturaActual.getTotal()));

        contadorFactura++;
        limpiar();
    }

    @FXML
    private void cerrar() {
        if (tblDetalles != null && tblDetalles.getScene() != null) {
            ((Stage) tblDetalles.getScene().getWindow()).close();
        }
    }

    private void limpiar() {
        txtNumFactura.setText("FAC-" + contadorFactura);
        txtCliente.clear();
        txtNit.clear();
        detalles.clear();
        actualizarTotales();
    }

    private void mensaje(Alert.AlertType tipo, String msg) {
        new Alert(tipo, msg, ButtonType.OK).showAndWait();
    }
}
