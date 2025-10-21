package com.empresa.proyecto.app._spring_test.services;

import java.math.BigDecimal;

import com.empresa.proyecto.app._spring_test.models.Producto;

public interface IProductoService {

        Producto findById(Integer idProducto);

        public BigDecimal revisarPrecio(Integer idProducto);

        void descontar(Integer idProducto, BigDecimal descuento, Integer idCaja);
    
}
