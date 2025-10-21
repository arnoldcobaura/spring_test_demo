package com.empresa.proyecto.app._spring_test.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.proyecto.app._spring_test.models.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

    // List<Producto> findAll();
    // Producto findById(Integer idProducto);
    // void update(Producto producto);

    @Query("SELECT p FROM Producto p WHERE p.descripcion = ?1")
    Optional<Producto> findByDescripcion(String descripcion);


    
    
}
