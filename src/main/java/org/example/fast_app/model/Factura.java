package org.example.fast_app.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Factura {
    private Integer id;
    private String numeroFactura;
    private String cliente;
    private String nit;
    private LocalDateTime fecha;
    private List<DetalleFactura> detalles;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;

    public Factura() {
        this.detalles = new ArrayList<>();
        this.fecha = LocalDateTime.now();
        this.subtotal = BigDecimal.ZERO;
        this.impuesto = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
    }

    public Factura(Integer id, String numeroFactura, String cliente, String nit, LocalDateTime fecha) {
        this();
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.nit = nit;
        if (fecha != null) this.fecha = fecha;
    }

    public void recalcularTotales() {
        subtotal = BigDecimal.ZERO;
        for (DetalleFactura d : detalles) {
            if (d.getSubtotal() != null) {
                subtotal = subtotal.add(d.getSubtotal());
            }
        }
        // Ejemplo: 15% IVA o Impuesto
        impuesto = subtotal.multiply(new BigDecimal("0.15"));
        total = subtotal.add(impuesto);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public List<DetalleFactura> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleFactura> detalles) { this.detalles = detalles; recalcularTotales(); }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getImpuesto() { return impuesto; }
    public BigDecimal getTotal() { return total; }
}
