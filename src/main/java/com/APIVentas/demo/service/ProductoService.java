/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.service;

import com.APIVentas.demo.models.Producto;
import com.APIVentas.demo.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepo;
    
    @Override
    public void crearProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
       return productoRepo.findAll();
    }

    @Override
    public Producto traerProducto(Long idProducto) {
       return productoRepo.findById(idProducto).get();
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        productoRepo.deleteById(idProducto);
    }

    @Override
    public void modificarProducto(Long idProducto, Producto producto) {
        Producto productoModificado = this.traerProducto(idProducto);
        
        
        productoModificado.setNombre(producto.getNombre());
        productoModificado.setMarca(producto.getMarca());
        productoModificado.setCosto(producto.getCosto());
        productoModificado.setCantidad_disponible(producto.getCantidad_disponible());
                
        productoRepo.save(productoModificado);
    }

    @Override
    public List<Producto> listarUltimosCinco(){
        return productoRepo.buscarUltimosCinco();
    }
    
}
