package org.dto;

import org.models.Donacion;

import java.time.LocalDate;

public class DonacionDTO {
    private long id;
    private String nombre;
    private Donacion.Tipo tipo;
    private double cantidad;
    private LocalDate fecha;
    private String categoria;
    private Donacion.Estado estado;

    
}
