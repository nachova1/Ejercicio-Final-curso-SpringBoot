/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APIVentas.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Admin
 */
@Getter
public class MiException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String mensaje;
    private final HttpStatus status;

    public MiException(String mensaje, HttpStatus status) {
        super(mensaje);
        this.mensaje = mensaje;
        this.status = status;
    }

    public MiException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
        this.status = null;

    }

}
