package com.empresa.proyecto.app._spring_test.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.empresa.proyecto.app._spring_test.Datos;
import com.empresa.proyecto.app._spring_test.models.DescuentoDto;
import com.empresa.proyecto.app._spring_test.services.IProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IProductoService productoService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testDetalle()  throws Exception {
        // Given
        when(productoService.findById(1)).thenReturn(Datos.crearProducto001().orElseThrow());

        // When
        mvc.perform(get("/api/productos/1").contentType(MediaType.APPLICATION_JSON))

        // Then
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.descripcion", is("LAPTOP")))
            .andExpect(jsonPath("$.precio", is(100.0)));

        verify(productoService, times(1)).findById(1);
    }

    @Test
    void testDescontar() throws Exception, JsonProcessingException {
        // Given
        DescuentoDto dto = new DescuentoDto();
        dto.setIdProducto(1);
        dto.setDescuento(new BigDecimal(10));
        dto.setIdCaja(1);

        System.out.println(objectMapper.writeValueAsString(dto));

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("mensaje", "Descuento aplicado");
        response.put("transaccion", dto);

        System.out.println(objectMapper.writeValueAsString(response));

        // When
        mvc.perform(post("/api/productos/descontar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))

                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.mensaje").value("Descuento aplicado"))
                .andExpect(jsonPath("$.transaccion.idProducto").value(dto.getIdProducto()))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

}
