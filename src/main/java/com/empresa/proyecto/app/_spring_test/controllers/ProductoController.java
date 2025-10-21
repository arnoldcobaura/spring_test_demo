package com.empresa.proyecto.app._spring_test.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.proyecto.app._spring_test.models.DescuentoDto;
import com.empresa.proyecto.app._spring_test.models.Producto;
import com.empresa.proyecto.app._spring_test.services.IProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/{id}")
	@ResponseStatus(code = org.springframework.http.HttpStatus.OK)
	public Producto detalle(@PathVariable Integer id) {
		return productoService.findById(id);
	}
	
	@PostMapping("/descontar")
	public ResponseEntity<?> descontar(@RequestBody DescuentoDto dto) {
		productoService.descontar(dto.getIdProducto(), dto.getDescuento(), dto.getIdCaja());
		Map<String, Object> response = new HashMap<>();
		response.put("date",LocalDate.now().toString());
		response.put("status", "OK");
		response.put("mensaje", "Descuento aplicado");
		response.put("transaccion", dto);
		return ResponseEntity.ok(response);
	}
}
