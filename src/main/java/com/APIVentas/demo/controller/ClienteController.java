/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.controller;

import com.APIVentas.demo.models.Cliente;
import com.APIVentas.demo.service.IClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private IClienteService clienteServ;
    
    @PostMapping("/crear")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente){
        clienteServ.crearCliente(cliente);
        return new ResponseEntity(cliente, HttpStatus.CREATED);
    }
    
    @GetMapping("")
    public ResponseEntity<?> listarClientes(){
        List<Cliente> listaClientes = clienteServ.listarClientes();
        return new ResponseEntity(listaClientes, HttpStatus.OK);
    }
    
    @GetMapping("/{idCliente}")
    public ResponseEntity<?> traerCliente(@PathVariable("idCliente") Long idCliente){
        Cliente cliente = clienteServ.traerCliente(idCliente);
        return new ResponseEntity(cliente, HttpStatus.OK);
    }
    
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> eliminarCliente(@PathVariable("idCliente") Long idCliente){
        clienteServ.eliminarCliente(idCliente);
        return new ResponseEntity("Cliente eliminado", HttpStatus.OK);
    }
    
    @PutMapping("/editar/{idCliente}")
    public ResponseEntity<?> editarCliente(@PathVariable("idCliente") Long idCliente, @RequestBody Cliente cliente){
        clienteServ.modificarCliente(idCliente, cliente);
        return new ResponseEntity(cliente, HttpStatus.OK);
    }
}
