package org.dto;

import org.models.Donacion;

public class TotalRecaudadoPorTipoDTO {
    private Donacion.Tipo tipoDonacion;
    private int cantidad;
    private double monto;

    public TotalRecaudadoPorTipoDTO(Donacion.Tipo tipo1,int cant, double montoRecaudado){
        this.tipoDonacion= tipo1;
        this.cantidad=cant;
        this.monto=montoRecaudado;
    }
    public Donacion.Tipo getTipoDonacion() {
        return tipoDonacion;
    }

    public void setTipoDonacion(Donacion.Tipo tipoDonacion) {
        this.tipoDonacion = tipoDonacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
