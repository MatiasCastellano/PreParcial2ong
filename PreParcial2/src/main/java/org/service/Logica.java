package org.service;

import jakarta.persistence.criteria.*;
import org.dto.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.models.AsignacionDonacion;
import org.models.Donacion;
import org.utils.HibernateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
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
            Donacion donacion= new Donacion(crear.getNombre(), crear.getTipo(),crear.getMonto(),crear.getFecha(),crear.getCategoria());
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
            transaccion.commit();
            resultado.setSuccess(true);
            resultado.setMessage("Se creo con exito la asignacion");
            return resultado;
        }
    }
    // 3. Total recaudado por tipo de donante
    public List<TotalRecaudadoPorTipoDTO> consultaTotalTipoDonante(){
        List<TotalRecaudadoPorTipoDTO> listaResultado= new ArrayList<>();
        try(Session session=HibernateUtil.getSession()){
            CriteriaBuilder cb= session.getCriteriaBuilder();
            CriteriaQuery<TotalRecaudadoPorTipoDTO> query= cb.createQuery(TotalRecaudadoPorTipoDTO.class);
            Root<Donacion> donacion= query.from(Donacion.class);
            Path<Donacion.Tipo> tipo= donacion.get("tipo");
            Expression<Long> cant= cb.count(donacion);
            Expression<BigDecimal> suma= cb.sum(donacion.get("cantidad"));
            query.select(cb.construct(TotalRecaudadoPorTipoDTO.class,tipo,cant,suma));
            query.groupBy(tipo);
            query.orderBy(cb.desc(suma));
            listaResultado=session.createQuery(query).getResultList();
            return listaResultado;
        }
    }

    //Consulta
    public List<TotalRecaudadoPorCatYEstado> consultaTotalCatYEstado(){
        List<TotalRecaudadoPorCatYEstado> listaResultado=new ArrayList<>();
        try(Session session=HibernateUtil.getSession()){
            CriteriaBuilder cb= session.getCriteriaBuilder();
            CriteriaQuery<TotalRecaudadoPorCatYEstado> query= cb.createQuery(TotalRecaudadoPorCatYEstado.class);
            Root<Donacion> donacion= query.from(Donacion.class);
            Path<String> categoria= donacion.get("categoria");
            Expression<Long> cantR= cb.sum(cb.<Long>selectCase().when(cb.equal(donacion.get("estado"), Donacion.Estado.RECEIVED),1L).otherwise(0L));
            Expression<Long> cantA= cb.sum(cb.<Long>selectCase().when(cb.equal(donacion.get("estado"), Donacion.Estado.ASSIGNED),1L).otherwise(0L));
            Expression<BigDecimal> suma= cb.sum(donacion.get("cantidad"));
            query.select(cb.construct(TotalRecaudadoPorCatYEstado.class,categoria,cantR,cantA,suma));
            query.groupBy(categoria);
            query.orderBy(cb.desc(suma));
            listaResultado=session.createQuery(query).getResultList();
            return listaResultado;
        }
    }
}
