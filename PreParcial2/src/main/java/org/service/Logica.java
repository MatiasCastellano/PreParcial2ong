package org.service;

import org.dto.CrearDonacionDTO;
import org.dto.ResultadoDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.models.Donacion;
import org.utils.HibernateUtil;

public class Logica {
    private static Logica instance;

    private Logica() {
    }
    public static Logica getInstance() {
        if (instance == null) {
            instance = new Logica();
        }
        return instance;
    }
    //crear donacion
    public ResultadoDTO crearDonacion(CrearDonacionDTO crear) {
        ResultadoDTO resultado = new ResultadoDTO();
        try(Session session= HibernateUtil.getSession()){
            Transaction transaccion= session.beginTransaction();
            Donacion donacion= new Donacion();
            donacion.setNombre(crear.getNombre());
            donacion.setTipo(crear.getTipo());
            donacion.setCantidad(crear.getMonto());
            donacion.setFecha(crear.getFecha());
            donacion.setCategoria(crear.getCategoria());
            donacion.setEstado(Donacion.Estado.RECEIVED);
            session.persist(donacion);
            resultado.setMessage("donacion creada con exito");
            resultado.setSuccess(true);
            transaccion.commit();
            return resultado;
        }
    }
}
