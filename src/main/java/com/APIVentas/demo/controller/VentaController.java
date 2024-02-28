/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.controller;

import com.APIVentas.demo.dto.VentaRequestDTO;
import com.APIVentas.demo.dto.VentaResponseDTO;
import com.APIVentas.demo.exception.MiException;
import com.APIVentas.demo.models.Producto;
import com.APIVentas.demo.models.Venta;
import com.APIVentas.demo.service.IVentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/ventas")
public class VentaController {
    
    @Autowired
    private IVentaService ventaServ;
    
    @PostMapping("/crear")
    public ResponseEntity<?> crearVenta(@RequestBody VentaRequestDTO ventaRequestDTO) {
        try {
            ventaServ.crearVenta(ventaRequestDTO);
            return new ResponseEntity(ventaRequestDTO, HttpStatus.CREATED);
        } catch (MiException e) {
            return new ResponseEntity(e.getMensaje(), e.getStatus());
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<?> listarVentas(){
        List<Venta> listaVentas = ventaServ.listarVentas();
        return new ResponseEntity(listaVentas, HttpStatus.OK);
    }
    
    @GetMapping("/{idVenta}")
    public ResponseEntity<?> traerVenta(@PathVariable("idVenta") Long idVenta){
        Venta venta = ventaServ.traerVenta(idVenta);
        return new ResponseEntity(venta, HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminar/{idVenta}")
    public ResponseEntity<?> eliminarVenta(@PathVariable("idVenta") Long idVenta){
        ventaServ.eliminarVenta(idVenta);
        return new ResponseEntity("Venta eliminada", HttpStatus.OK);
    }
    
    @GetMapping("/productos/{idVenta}")
    public ResponseEntity<?> buscarProductosVendidos(@PathVariable("idVenta") Long idVenta){
        List<Producto> listaProductos = ventaServ.listarProductosVendidos(idVenta);
        return new ResponseEntity(listaProductos, HttpStatus.OK);
    }
    
    @GetMapping("/fecha/{fechaVenta}")
    public ResponseEntity<?> obtenerSumaTotalyDias(@PathVariable("fechaVenta") String fechaVenta) throws MiException{
        String texto = ventaServ.listarVtasPorFecha(fechaVenta);
        return new ResponseEntity(texto, HttpStatus.OK);
    }
    
    @GetMapping("/mayor_venta")
    public ResponseEntity<?> listarMontoMasAlto(){
        List<VentaResponseDTO> listaVentasDTO = ventaServ.listarVtasMasAlta();
        return new ResponseEntity(listaVentasDTO, HttpStatus.OK);
    }
}
