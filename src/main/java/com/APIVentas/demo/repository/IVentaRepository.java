/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.APIVentas.demo.repository;

import com.APIVentas.demo.models.Producto;
import com.APIVentas.demo.models.Venta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long>{
    @Query("SELECT v.listaProductos FROM Venta v WHERE v.codigo_venta = :idVenta")
    List<Producto> findProductos(@Param("idVenta") Long idVenta);
    
    @Query("SELECT v FROM Venta v WHERE v.fecha_venta = :fechaVenta")
    List<Venta> findVentasByDate(@Param("fechaVenta") LocalDate fechaVenta);
}
