package com.empresa.proyecto.app._spring_test;

import java.math.BigDecimal;
import java.util.Optional;

import com.empresa.proyecto.app._spring_test.models.Caja;
import com.empresa.proyecto.app._spring_test.models.Producto;

public class Datos {

   //  public static final Producto PRODUCTO = new Producto(1, "LAPTOP", new BigDecimal("100.00"));
   //  public static final Producto PRODUCTO = new Producto(2, "CELULAR", new BigDecimal("200.00"));
   //  public static final Caja CAJA = new Caja(1, "CAJA 1", new BigDecimal("0.00"));

    public static Optional<Producto> crearProducto001(){  
      return  Optional.of(new Producto(1, "LAPTOP", new BigDecimal("100.00")));
    }

    public static Optional<Producto> crearProducto002(){  
      return   Optional.of(new Producto(2, "CELULAR", new BigDecimal("200.00")));
    }

    public static  Optional<Caja>  crearCaja001(){  
      return  Optional.of(new Caja(1, "CAJA 1", new BigDecimal("0.00")));
    }
    
    
}
