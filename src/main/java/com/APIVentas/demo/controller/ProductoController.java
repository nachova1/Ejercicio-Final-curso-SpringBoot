/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.controller;

import com.APIVentas.demo.models.Producto;
import com.APIVentas.demo.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;
    
    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){
        productoService.crearProducto(producto);
        return new ResponseEntity(producto, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<?> listarProductos(){
        List<Producto> listaProductos = productoService.listarProductos();
        return new ResponseEntity(listaProductos, HttpStatus.OK);
    }
    
    @GetMapping("/{idProducto}")
    public ResponseEntity<?> buscarProducto(@PathVariable("idProducto") Long idProducto){
        Producto producto = productoService.traerProducto(idProducto);
        return new ResponseEntity(producto, HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("idProducto") Long idProducto){
        productoService.eliminarProducto(idProducto);
        return new ResponseEntity("Producto eliminado", HttpStatus.OK);
    }
    
    @PutMapping("/editar/{idProducto}")
    public ResponseEntity<?> modificarProducto(@PathVariable("idProducto") Long idProducto, @RequestBody Producto producto){
        productoService.modificarProducto(idProducto, producto);
        return new ResponseEntity(producto, HttpStatus.OK);
    }
    
    @GetMapping("/falta_stock")
    public ResponseEntity<?> listarUltimosCinco(){
        List<Producto> listaProductos = productoService.listarUltimosCinco();
        return new ResponseEntity(listaProductos, HttpStatus.OK);
    }
}
