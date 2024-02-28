/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.service;

import com.APIVentas.demo.dto.VentaRequestDTO;
import com.APIVentas.demo.dto.VentaResponseDTO;
import com.APIVentas.demo.exception.MiException;
import com.APIVentas.demo.models.Producto;
import com.APIVentas.demo.models.Venta;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IVentaService {
    public void crearVenta(VentaRequestDTO ventaRequestDTO) throws MiException;
    public List<Venta> listarVentas();
    public Venta traerVenta(Long idVenta);
    public void eliminarVenta(Long idVenta);
    public void modificarVenta(Long idVenta, Venta venta);
    public List<Producto> listarProductosVendidos(Long idVenta);
    public String listarVtasPorFecha(String fechaVenta) throws MiException;
    public List<VentaResponseDTO> listarVtasMasAlta();
        
    
}
