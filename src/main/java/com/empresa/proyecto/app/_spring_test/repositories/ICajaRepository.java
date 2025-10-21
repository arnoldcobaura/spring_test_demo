package com.empresa.proyecto.app._spring_test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.proyecto.app._spring_test.models.Caja;

public interface ICajaRepository extends JpaRepository<Caja, Integer> {

   //  List<Caja> findAll();

// Caja findById(Integer idCaja);

   // void update(Caja caja);

}
