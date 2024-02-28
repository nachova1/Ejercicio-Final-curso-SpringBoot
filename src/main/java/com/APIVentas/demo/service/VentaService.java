/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.service;

import com.APIVentas.demo.dto.ProductoRequestDTO;
import com.APIVentas.demo.dto.VentaRequestDTO;
import com.APIVentas.demo.dto.VentaResponseDTO;
import com.APIVentas.demo.exception.MiException;
import com.APIVentas.demo.models.Cliente;
import com.APIVentas.demo.models.Producto;
import com.APIVentas.demo.models.Venta;
import com.APIVentas.demo.repository.IClienteRepository;
import com.APIVentas.demo.repository.IProductoRepository;
import com.APIVentas.demo.repository.IVentaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepo;
    @Autowired
    private IProductoRepository productoRepo;

    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public void crearVenta(VentaRequestDTO ventaRequestDTO) throws MiException {

        try {
            Cliente cliente = clienteRepo.findByNombre(ventaRequestDTO.getNombreCliente())
                    .orElseThrow(() -> new MiException("No existe un cliente con ese nombre", HttpStatus.BAD_REQUEST));

            List<Producto> listaProdVendidos = new ArrayList<>();
            Venta venta = new Venta();
            Producto prodVenta = null;

            double totalVenta = 0.0;

            for (ProductoRequestDTO productoDTO : ventaRequestDTO.getListaProductos()) {
                prodVenta = new Producto();
                prodVenta.setNombre(productoDTO.getNombre());
                prodVenta.setMarca(productoDTO.getMarca());

                Optional<Producto> optionalProducto = productoRepo.buscarPorNombre(prodVenta.getNombre());
                if (optionalProducto.isPresent()) {
                    prodVenta = optionalProducto.get();
                    try {
                        prodVenta.vender(productoDTO.getCantidad());
                        totalVenta += productoDTO.getCantidad() * prodVenta.getCosto();
                        listaProdVendidos.add(prodVenta);
                    } catch (MiException e) {
                        throw e;
                    }

                } else {
                    throw new MiException("No se encuentra un producto con ese nombre", HttpStatus.BAD_REQUEST);
                }
            }

            venta.setFecha_venta(LocalDate.now());
            venta.setCliente(cliente);
            venta.setTotal(totalVenta);
            venta.setListaProductos(listaProdVendidos);
            ventaRepo.save(venta);

        } catch (MiException e) {
            Logger.getLogger(VentaService.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }

    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta traerVenta(Long idVenta) {
        return ventaRepo.findById(idVenta).get();
    }

    @Override
    public void eliminarVenta(Long idVenta) {
        ventaRepo.deleteById(idVenta);
    }

    @Override
    public void modificarVenta(Long idVenta, Venta venta) {
        Venta ventaModificada = this.traerVenta(idVenta);
        ventaModificada.setListaProductos(venta.getListaProductos());
        ventaRepo.save(ventaModificada);
    }

    @Override
    public List<Producto> listarProductosVendidos(Long idVenta) {

        return ventaRepo.findProductos(idVenta);

    }

    @Override
    public String listarVtasPorFecha(String fechaVenta) throws MiException {
        LocalDate fechaBuscada = this.validarFecha(fechaVenta);
        List<Venta> listaVentas = ventaRepo.findVentasByDate(fechaBuscada);
        Double montoAcumulado = 0.0;
        Integer cantidadVtas = 0;
        String texto;

        for (Venta venta : listaVentas) {
            montoAcumulado = montoAcumulado + venta.getTotal();
            cantidadVtas = cantidadVtas + 1;
        }

        texto = "La cantidad de ventas en el dia " + fechaBuscada + " es de: " + cantidadVtas
                + " Y el monto acumulado es de: " + montoAcumulado;
        return texto;
    }

    private LocalDate validarFecha(String fechaNacimiento) throws MiException {
        try {
            //Si la fecha que recibe es en formato corrrecto devuelve la fecha como localdate
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);

            return fechaNac;

        } catch (DateTimeParseException e) {
            throw new MiException("El formato de fecha debe ser yyyy-MM-dd", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public List<VentaResponseDTO> listarVtasMasAlta() {
        List<Venta> listaVentas = this.listarVentas();

        // Encuentra el total más alto
        double totalMasAlto = listaVentas.stream()
                .mapToDouble(Venta::getTotal) // Extrae los totales como un DoubleStream
                .max() // Encuentra el valor máximo (total más alto)
                .orElse(Double.MIN_VALUE); // Si no hay totales, establece un valor mínimo

        // Filtra las ventas con el total más alto
        // Double.compare devuelve 0 si los dos valores son iguales.
        List<Venta> ventasConTotalMasAlto = listaVentas.stream()
                .filter(venta -> Double.compare(venta.getTotal(), totalMasAlto) == 0)
                .collect(Collectors.toList());

        // Mapea las ventas filtradas a DTOs
        List<VentaResponseDTO> listaVentaDTO = ventasConTotalMasAlto.stream()
                .map(venta -> {
                    VentaResponseDTO ventaDTO = new VentaResponseDTO();
                    ventaDTO.setCodigo_venta(venta.getCodigo_venta());
                    ventaDTO.setTotal(venta.getTotal());
                    ventaDTO.setCantidadProductos(venta.getListaProductos().size());
                    ventaDTO.setCliente(venta.getCliente().getNombre()); // Asegúrate de tener un método getNombre en tu entidad Cliente

                    return ventaDTO;
                }).collect(Collectors.toList());

        return listaVentaDTO;

    }

}
