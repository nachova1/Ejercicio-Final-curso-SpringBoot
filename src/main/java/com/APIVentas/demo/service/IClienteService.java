/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.APIVentas.demo.service;

import com.APIVentas.demo.models.Cliente;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IClienteService {
    public void crearCliente(Cliente cliente);
    public List<Cliente> listarClientes();
    public Cliente traerCliente(Long idCliente);
    public void eliminarCliente(Long idCliente);
    public void modificarCliente(Long idCliente, Cliente cliente);
}
