package org.example.fast_app.model;

import lombok.*;
import java.math.BigDecimal;

@Data
public class DetalleFactura {
    private Producto producto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public DetalleFactura() { }

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        if (producto != null && producto.getPrecioVenta() != null) {
            this.precioUnitario = producto.getPrecioVenta();
            this.subtotal = precioUnitario.multiply(new BigDecimal(cantidad));
        } else {
            this.precioUnitario = BigDecimal.ZERO;
            this.subtotal = BigDecimal.ZERO;
        }
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        if (precioUnitario != null) {
            this.subtotal = precioUnitario.multiply(new BigDecimal(cantidad));
        }
    }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
        if (precioUnitario != null) {
            this.subtotal = precioUnitario.multiply(new BigDecimal(cantidad));
        }
    }
    public BigDecimal getSubtotal() { return subtotal; }

    public String getNombreProducto() {
        return producto != null ? producto.getNombre() : "";
    }

    public String getCodigoProducto() {
        return producto != null ? producto.getCodigo() : "";
    }
}
