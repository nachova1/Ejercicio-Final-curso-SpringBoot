/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.service;

import com.APIVentas.demo.models.Cliente;
import com.APIVentas.demo.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepo;
    
    @Override
    public void crearCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente traerCliente(Long idCliente) {
        return clienteRepo.findById(idCliente).get();
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        clienteRepo.deleteById(idCliente);
    }

    @Override
    public void modificarCliente(Long idCliente, Cliente cliente) {
        Cliente clienteModificado = this.traerCliente(idCliente);
        
        clienteModificado.setNombre(cliente.getNombre());
        clienteModificado.setApellido(cliente.getApellido());
        clienteModificado.setDni(cliente.getDni());
        
        clienteRepo.save(clienteModificado);
    }
    
}
