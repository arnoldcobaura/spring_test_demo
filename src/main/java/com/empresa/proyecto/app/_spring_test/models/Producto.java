package com.empresa.proyecto.app._spring_test.models;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;

import com.empresa.proyecto.app._spring_test.exception.DescuentoInsuficienteException;

@Entity
@Table(name="productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;
	
	private BigDecimal precio;

	public Producto() {
	}

	public Producto(Integer id, String descripcion, BigDecimal precio) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public void descontar(BigDecimal descuento) {
		BigDecimal nuevoPrecio = this.precio.subtract(descuento);
		if (nuevoPrecio.compareTo(BigDecimal.ZERO) < 0) {
			throw new DescuentoInsuficienteException("El descuento es mayor al precio del producto");
		}
		this.precio = nuevoPrecio;
	}

	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto Producto = (Producto) o;
		return Objects.equals(id, Producto.id) &&
				Objects.equals(descripcion, Producto.descripcion) &&
				Objects.equals(precio, Producto.precio);
    }

    @Override
    public int hashCode() {
		return Objects.hash(id, descripcion, precio);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

}
