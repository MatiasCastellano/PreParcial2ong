package org.dto;

import org.models.AsignacionDonacion;

import java.time.LocalDate;

public class AsignacionDonacionDTO {
    private long id;
    private DonacionDTO donacion;
    private String notas;
    private LocalDate fechaA;


    public AsignacionDonacionDTO(){};
    public AsignacionDonacionDTO(DonacionDTO donacion, String nota, LocalDate fecha){
        this.donacion=donacion;
        this.notas=nota;
        this.fechaA=fecha;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DonacionDTO getDonacion() {
        return donacion;
    }

    public void setDonacion(DonacionDTO donacion) {
        this.donacion = donacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDate getFechaA() {
        return fechaA;
    }

    public void setFechaA(LocalDate fechaA) {
        this.fechaA = fechaA;
    }
}
