package com.empresa.proyecto.app._spring_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.empresa.proyecto.app._spring_test.exception.DescuentoInsuficienteException;
import com.empresa.proyecto.app._spring_test.models.Caja;
import com.empresa.proyecto.app._spring_test.models.Producto;
import com.empresa.proyecto.app._spring_test.repositories.ICajaRepository;
import com.empresa.proyecto.app._spring_test.repositories.IProductoRepository;
import com.empresa.proyecto.app._spring_test.services.IProductoService;
// import com.empresa.proyecto.app._spring_test.services.ProductoServiceImpl;
import com.empresa.proyecto.app._spring_test.services.ProductoServiceImpl;

@SpringBootTest
class ApplicationTests {

	// @Mock
	@MockBean // CON SPRING BOOT
	IProductoRepository productoRepository;

	//@Mock
	@MockBean // CON SPRING BOOT
	ICajaRepository cajaRepository;

	// @InjectMocks
	@Autowired // TAMBIEN SI QUIERES PONLE AUTOCABLEADO
	IProductoService productoService;
	//ProductoServiceImpl productoService;

	// DESHABILITANDO LA TRAZA DE LOS LOGS EN EL CONTEXTO TEST

	@BeforeEach
	void setUp() throws Exception {
		// productoRepository = mock(IProductoRepository.class);
		// cajaRepository = mock(ICajaRepository.class);
		// productoService = new ProductoServiceImpl(productoRepository, cajaRepository);
		/*
		 * 
		when(productoRepository.findById(1)).thenReturn(Datos.PRODUCTO_001);
		when(cajaRepository.findById(1)).thenReturn(Datos.CAJA);
		 */
	}


	@Test
	void contextLoads() {
		when(productoRepository.findById(1)).thenReturn(Datos.crearProducto001());
		when(cajaRepository.findById(1)).thenReturn(Datos.crearCaja001());

		BigDecimal precio1  = productoService.revisarPrecio(1);
		assertEquals("100.00", precio1.toString());

		productoService.descontar(1, new BigDecimal("10.00"), 1);

		precio1  = productoService.revisarPrecio(1);

		assertEquals("90.00", precio1.toString());

		verify(productoRepository, times(3)).findById(1);
		verify(productoRepository, times(1)).save(any(Producto.class));

		verify(cajaRepository, times(1)).findById(1);
		verify(cajaRepository).save(any(Caja.class));
	}

	@Test
	void contextLoads2() {
		when(productoRepository.findById(1)).thenReturn(Datos.crearProducto001());
		when(cajaRepository.findById(1)).thenReturn(Datos.crearCaja001());
		BigDecimal precio1  = productoService.revisarPrecio(1);
		assertEquals("100.00", precio1.toString());
		assertThrows(DescuentoInsuficienteException.class, () -> {
			productoService.descontar(1, new BigDecimal("110.00"), 1);
		});
		precio1  = productoService.revisarPrecio(1);
		System.out.println(precio1);
		verify(productoRepository, times(3)).findById(1);
		verify(productoRepository,never()).save(any(Producto.class));
		verify(cajaRepository, never()).findById(1);
		verify(cajaRepository, never()).save(any(Caja.class));
	}
	
	// USANDO ASSERTSAME
	@Test
	void contextLoads3() {
		when(productoRepository.findById(1)).thenReturn(Datos.crearProducto001());

		Producto producto1 = productoService.findById(1);
		Producto producto2 = productoService.findById(1);


		assertSame(producto1, producto2);
		assertTrue(producto1 == producto2);

		verify(productoRepository, times(2)).findById(1);

	}



}
