package org.dto;

import java.math.BigDecimal;

public class TotalRecaudadoPorCatYEstado {
    private String categoria;
    private long cantDonRecibidas;
    private long cantDonacionesAsignadas;
    private BigDecimal total;

    public TotalRecaudadoPorCatYEstado(String cat, long cantR,long cantA, BigDecimal total){
        this.categoria=cat;
        this.cantDonRecibidas=cantR;
        this.cantDonacionesAsignadas=cantA;
        this.total=total;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public long getCantDonRecibidas() {
        return cantDonRecibidas;
    }

    public void setCantDonRecibidas(long cantDonRecibidas) {
        this.cantDonRecibidas = cantDonRecibidas;
    }

    public long getCantDonacionesAsignadas() {
        return cantDonacionesAsignadas;
    }

    public void setCantDonacionesAsignadas(long cantDonacionesAsignadas) {
        this.cantDonacionesAsignadas = cantDonacionesAsignadas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
