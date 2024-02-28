/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.APIVentas.demo.service;

import com.APIVentas.demo.models.Producto;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IProductoService {
    public void crearProducto(Producto producto);
    public List<Producto> listarProductos();
    public Producto traerProducto(Long idProducto);
    public void eliminarProducto(Long idProducto);
    public void modificarProducto(Long idProducto, Producto producto);
    
    public List<Producto> listarUltimosCinco();
    
    
}
