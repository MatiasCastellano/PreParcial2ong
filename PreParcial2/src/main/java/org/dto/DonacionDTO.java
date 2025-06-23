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

    public DonacionDTO(){};

    public DonacionDTO(String nom, Donacion.Tipo tipo1, double cant, LocalDate fecha1, String cat, Donacion.Estado estado1){
        this.nombre=nom;
        this.tipo= tipo1;
        this.cantidad=cant;
        this.fecha=fecha1;
        this.categoria=cat;
        this.estado=estado1;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
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

    public Donacion.Estado getEstado() {
        return estado;
    }

    public void setEstado(Donacion.Estado estado) {
        this.estado = estado;
    }
}
