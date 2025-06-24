package org.dto;

import java.time.LocalDate;

public class AsignarDonacionDTO {
    private long idDonacion;
    private LocalDate fechaAsignacion;
    private String notas;

    public AsignarDonacionDTO(long id,LocalDate fecha,String nota){
        this.idDonacion=id;
        this.fechaAsignacion=fecha;
        this.notas=nota;
    }
    public long getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(long idDonacion) {
        this.idDonacion = idDonacion;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
