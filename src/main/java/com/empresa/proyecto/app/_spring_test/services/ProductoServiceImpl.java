
package com.empresa.proyecto.app._spring_test.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.proyecto.app._spring_test.models.Caja;
import com.empresa.proyecto.app._spring_test.models.Producto;
import com.empresa.proyecto.app._spring_test.repositories.ICajaRepository;
import com.empresa.proyecto.app._spring_test.repositories.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

    private IProductoRepository productoRepository;
    private ICajaRepository cajaRepository;

    public ProductoServiceImpl(IProductoRepository productoRepository, ICajaRepository cajaRepository) {
        this.productoRepository = productoRepository;
        this.cajaRepository = cajaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Integer idProducto) {
        return productoRepository.findById(idProducto).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal revisarPrecio(Integer idProducto) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        return producto.getPrecio();
    }

    @Override
    @Transactional
    public void descontar(Integer idProducto, BigDecimal descuento, Integer idCaja) {

        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        producto.descontar(descuento);
        // productoRepository.update(producto);
        productoRepository.save(producto);

        Caja caja = cajaRepository.findById(idCaja).orElseThrow();
        BigDecimal totalDescuento = caja.getTotalDescuento();
        caja.setTotalDescuento(totalDescuento.add(descuento));
        cajaRepository.save(caja);
    }

}