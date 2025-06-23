package org.dto;

import org.models.Donacion;

import java.sql.Time;
import java.time.LocalDate;

public class CrearDonacionDTO {
    private String nombre;
    private Donacion.Tipo tipo;
    private double monto;
    private LocalDate fecha;
    private String categoria;


    public CrearDonacionDTO(String nom, Donacion.Tipo tipo1, double cant, LocalDate fecha1,String cat){
        this.nombre=nom;
        this.tipo=tipo1;
        this.monto=cant;
        this.fecha=fecha1;
        this.categoria=cat;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Donacion.Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Donacion.Tipo tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
