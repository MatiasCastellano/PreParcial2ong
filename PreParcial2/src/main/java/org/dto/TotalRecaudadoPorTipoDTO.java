package org.dto;

import org.models.Donacion;

import java.math.BigDecimal;

public class TotalRecaudadoPorTipoDTO {
    private Donacion.Tipo tipoDonacion;
    private Long cantidad;
    private BigDecimal monto;

    public TotalRecaudadoPorTipoDTO(Donacion.Tipo tipo1,Long cant, BigDecimal montoRecaudado){
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

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
