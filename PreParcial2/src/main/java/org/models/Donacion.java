package org.models;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="donation")

public class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "donation_id")
    private long id;

    @Column(name="donor_name", nullable = false, length = 20)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name="donor_type", nullable = false)
    private Tipo tipo;

    @Column(name= "amount", nullable = false)
    private BigDecimal cantidad;

    @Column(name= "donation_date", nullable = false)
    private LocalDate fecha;

    @Column(name="category", nullable = false)
    private String categoria;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private Estado estado;

    @OneToOne(mappedBy = "donacion", fetch = FetchType.LAZY)
    private AsignacionDonacion asignacion;

    public enum Estado{
        RECEIVED,
        ASSIGNED
    }

    public enum Tipo{
        INDIVIDUAL,
        COMPANY
    }
    public Donacion(){};
    public Donacion(String nombre, Tipo tipo1,BigDecimal cant, LocalDate fechaDon,String categoria){
        this.nombre=nombre;
        this.tipo=tipo1;
        this.cantidad=cant;
        this.fecha=fechaDon;
        this.fecha=fechaDon;
        this.categoria=categoria;
        this.estado= Estado.RECEIVED;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
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

    public void setEstado(Estado estado1){
        this.estado=estado1;
    }
    public Estado getEstado(){
        return estado;
    }
    public void setAsignacion(AsignacionDonacion asig){
        this.asignacion=asig;
    }
    public AsignacionDonacion getAsignacion(){
        return asignacion;
    }
}
