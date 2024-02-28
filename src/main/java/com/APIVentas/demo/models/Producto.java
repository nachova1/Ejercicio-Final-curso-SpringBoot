/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.models;

import com.APIVentas.demo.exception.MiException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Admin
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    
    
    @ManyToMany(mappedBy = "listaProductos", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Venta> ventas;
    
    public void vender(int cantidad) throws MiException {
        if (cantidad > 0 && cantidad <= cantidad_disponible) {
            cantidad_disponible -= cantidad;
        } else {
            throw new MiException("Stock insuficiente para el producto: " + nombre, HttpStatus.BAD_REQUEST);
        }
    }
}
