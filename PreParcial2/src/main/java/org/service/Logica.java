package org.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.dto.AsignarDonacionDTO;
import org.dto.CrearDonacionDTO;
import org.dto.ResultadoDTO;
import org.dto.TotalRecaudadoPorTipoDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.models.AsignacionDonacion;
import org.models.Donacion;
import org.utils.HibernateUtil;

import java.util.List;

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
    //1. crear donacion
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
    //2. Asignar Donación
    public ResultadoDTO asignarDonacion(AsignarDonacionDTO parametros){
        ResultadoDTO resultado= new ResultadoDTO();
        try(Session session= HibernateUtil.getSession()){
            Transaction transaccion= session.beginTransaction();
            CriteriaBuilder cb= session.getCriteriaBuilder();
            CriteriaQuery<Donacion> query= cb.createQuery(Donacion.class);
            Root<Donacion> donacion= query.from(Donacion.class);
            Predicate predicate= cb.equal(donacion.get("id"), parametros.getIdDonacion());
            query.select(donacion).where(predicate);
            List<Donacion> donacionList= session.createQuery(query).getResultList();
            if(donacionList.isEmpty()){
                resultado.setSuccess(false);
                resultado.setMessage("no se encontro la donacion que se desea asignar");
                return resultado;
            }
            Donacion donacionAsignar= donacionList.get(0);
            if(donacionAsignar.getEstado()!= Donacion.Estado.RECEIVED){
                resultado.setSuccess(false);
                resultado.setMessage("la donacion no esta en estado RECEIVED, NO se puede asignar");
                return resultado;
            }
            donacionAsignar.setEstado(Donacion.Estado.ASSIGNED);
            AsignacionDonacion nuevaAsignacion= new AsignacionDonacion();
            nuevaAsignacion.setDonacion(donacionAsignar);
            nuevaAsignacion.setFechaAsignacion(parametros.getFechaAsignacion());
            nuevaAsignacion.setNotas(parametros.getNotas());
            session.persist(nuevaAsignacion);
            session.persist(nuevaAsignacion);
            transaccion.commit();
            resultado.setSuccess(true);
            resultado.setMessage("Se creo con exito la asignacion");
            return resultado;
        }
    }
    // 3. Total recaudado por tipo de donante
    public List<TotalRecaudadoPorTipoDTO> consultaTotalTipoDonante(){
        
    }
}
