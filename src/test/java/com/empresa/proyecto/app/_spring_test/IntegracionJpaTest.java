package com.empresa.proyecto.app._spring_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.empresa.proyecto.app._spring_test.models.Producto;
import com.empresa.proyecto.app._spring_test.repositories.IProductoRepository;

@DataJpaTest
public class IntegracionJpaTest {
    @Autowired
    IProductoRepository productoRepository;

    @Test
    void testFindById() {
        Optional<Producto> producto = productoRepository.findById(1);
        assertTrue(producto.isPresent());
        assertEquals("LAPTOP", producto.orElseThrow().getDescripcion());
    }

    @Test
    void testFindByPerson() {
        Optional<Producto> producto = productoRepository.findByDescripcion("LAPTOP");
        assertTrue(producto.isPresent());
        assertEquals("LAPTOP", producto.orElseThrow().getDescripcion());
        assertEquals("100.00", producto.orElseThrow().getPrecio().toPlainString());
    }

    @Test
    void testFindByPersonThrowException() {
        Optional<Producto> producto = productoRepository.findByDescripcion("audifonoe");
        /*
         * assertThrows(NoSuchElementException.class, () -> {
         * producto.orElseThrow();
         * });
         */
        assertThrows(NoSuchElementException.class, producto::orElseThrow);
        assertFalse(producto.isPresent());
    }

    @Test
    void testFindAll() {
        List<Producto> productos = productoRepository.findAll();
        assertFalse(productos.isEmpty());
        assertEquals(2, productos.size());
    }

    @Test
    void testSave() {
        // GIVEN
        Producto producto = new Producto(null, "TELEVISION", new BigDecimal("1200"));
        // productoRepository.save(producto);
        Producto productoGuardado = productoRepository.save(producto);

        // WHEN
        // Producto productoGuardado =
        // productoRepository.findByDescripcion("TELEVISION").orElseThrow();

        // THEN
        assertEquals("TELEVISION", productoGuardado.getDescripcion());
        assertEquals("1200", productoGuardado.getPrecio().toPlainString());
        assertEquals(3, productoGuardado.getId());

    }

    @Test
    void testUpdate() {
        // GIVEN
        Producto producto = new Producto(null, "TELEVISION", new BigDecimal("1200"));
        // productoRepository.save(producto);
        Producto productoGuardado = productoRepository.save(producto);

        // WHEN
        // Producto productoGuardado =
        // productoRepository.findByDescripcion("TELEVISION").orElseThrow();

        // THEN
        assertEquals("TELEVISION", productoGuardado.getDescripcion());
        assertEquals("1200", productoGuardado.getPrecio().toPlainString());
        // assertEquals(3, productoGuardado.getId());

        // when
        producto.setPrecio(new BigDecimal(3000));
        Producto productoActualizado = productoRepository.save(producto);

        // then
        assertEquals("TELEVISION", productoActualizado.getDescripcion());
        assertEquals("3000", productoActualizado.getPrecio().toPlainString());
    }

    @Test
    void testDelete(){
        Producto producto = productoRepository.findById(1).orElseThrow();
        assertEquals("LAPTOP", producto.getDescripcion());

        productoRepository.delete(producto);

        assertThrows(NoSuchElementException.class, () -> {
            productoRepository.findById(1).orElseThrow();
        });

        assertEquals(1, productoRepository.findAll().size());

    }
}
