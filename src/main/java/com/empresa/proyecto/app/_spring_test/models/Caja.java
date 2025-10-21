package com.empresa.proyecto.app._spring_test.models;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="cajas")
public class Caja {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;
	
    @Column(name="total_descuento")
	private BigDecimal totalDescuento;

	public Caja() {
	}

	public Caja(Integer id, String nombre, BigDecimal totalDescuento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.totalDescuento = totalDescuento;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(BigDecimal totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
