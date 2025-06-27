import net.bytebuddy.asm.Advice;
import org.dto.CrearDonacionDTO;
import org.dto.ResultadoDTO;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.models.AsignacionDonacion;
import org.models.Donacion;
import org.service.Logica;
import org.utils.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicaTest {
    private Logica logica;
    private Session session;
    private Donacion donacion1;
    private Donacion donacion2;
    private Donacion donacion3;
    private AsignacionDonacion donacionAsignada1;
    private AsignacionDonacion donacionAsignada2;

    @BeforeAll
    public void setUp(){
        logica= Logica.getInstance();
        session= HibernateUtil.getSession();
        session.beginTransaction();

        donacion1= new Donacion();
        donacion1.setNombre("Matias");
        donacion1.setTipo(Donacion.Tipo.INDIVIDUAL);
        donacion1.setCantidad(new BigDecimal("1500.05"));
        donacion1.setFecha(LocalDate.of(2025,10,10));
        donacion1.setCategoria("eduacion");
        donacion1.setEstado(Donacion.Estado.RECEIVED);

        donacion2= new Donacion();
        donacion2.setNombre("Municipalidad");
        donacion2.setTipo(Donacion.Tipo.COMPANY);
        donacion2.setCantidad(new BigDecimal("25000.10"));
        donacion2.setFecha(LocalDate.of(2025,8,12));
        donacion2.setCategoria("salud");
        donacion2.setEstado(Donacion.Estado.RECEIVED);

        donacion3= new Donacion();
        donacion3.setNombre("Carlos");
        donacion3.setTipo(Donacion.Tipo.INDIVIDUAL);
        donacion3.setCantidad(new BigDecimal("1000.10"));
        donacion3.setFecha(LocalDate.of(2025,7,16));
        donacion3.setCategoria("educacion");
        donacion3.setEstado(Donacion.Estado.RECEIVED);

        session.persist(donacion1);
        session.persist(donacion2);
        session.persist(donacion3);

        session.getTransaction().commit();

    }
    @AfterAll
    void tearDown(){
        if(session!=null && session.isOpen()){
            session.beginTransaction();
            session.createQuery("delete from AsignacionDonacion").executeUpdate();
            session.createQuery("delete from Donacion").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
    @Test
    public void testAgregarDonacion_Ok(){
        CrearDonacionDTO donacion4= new CrearDonacionDTO("ministerio", Donacion.Tipo.COMPANY,new BigDecimal("1000"),LocalDate.of(2025,01,01),"cultura");
        ResultadoDTO resultado=  logica.crearDonacion(donacion4);
        assertTrue(resultado.getSuccess());
    }
}
