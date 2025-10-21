package com.empresa.proyecto.app._spring_test.models;

import java.math.BigDecimal;

public class DescuentoDto {
    private Integer idProducto;
    private BigDecimal descuento;
    private Integer idCaja;
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public BigDecimal getDescuento() {
        return descuento;
    }
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    public Integer getIdCaja() {
        return idCaja;
    }
    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    
    
}
