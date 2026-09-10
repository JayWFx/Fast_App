package org.example.fast_app.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.fast_app.model.*;

public class DatosManager {
    private static final DatosManager INSTANCE = new DatosManager();

    private final ObservableList<Categoria> categorias = FXCollections.observableArrayList();
    private final ObservableList<Producto> productos = FXCollections.observableArrayList();
    private final ObservableList<Cargo> cargos = FXCollections.observableArrayList();
    private final ObservableList<Empleado> empleados = FXCollections.observableArrayList();
    private final ObservableList<Factura> facturas = FXCollections.observableArrayList();

    private DatosManager() { }

    public static DatosManager getInstance() {
        return INSTANCE;
    }

    public ObservableList<Categoria> getCategorias() { return categorias; }
    public ObservableList<Producto> getProductos() { return productos; }
    public ObservableList<Cargo> getCargos() { return cargos; }
    public ObservableList<Empleado> getEmpleados() { return empleados; }
    public ObservableList<Factura> getFacturas() { return facturas; }
}
