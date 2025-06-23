package org.models;


import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="donation_assignment ")
public class AsignacionDonacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "assignment_id")
    private long idAsignacion;

    @OneToOne
    @JoinColumn(name= "donation_id")
    private Donacion donacion;

    @Column(name="notes", nullable = false)
    private String notas;

    @Column(name= "assigned_date", nullable = false)
    private LocalDate FechaAsignacion;
    public AsignacionDonacion(){};
    public AsignacionDonacion(Donacion donacion1, String nota, LocalDate fecha){
        this.donacion=donacion1;
        this.FechaAsignacion=fecha;
        this.notas=nota;
    }

    public long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDate getFechaAsignacion() {
        return FechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        FechaAsignacion = fechaAsignacion;
    }
}
