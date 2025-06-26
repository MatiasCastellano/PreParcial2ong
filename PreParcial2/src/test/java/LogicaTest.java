import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.service.Logica;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicaTest {
    private Logica logica;
    private Session session;

    @BeforeAll
    public void setUp(){
        logica= Logica.getInstance();

    }

    @AfterAll
    void tearDown(){
        if(session!=null && session.isOpen()){
            session.beginTransaction();
            session.createQuery("delete from Reserva").executeUpdate();
            session.createQuery("delete from Vehiculo").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

}
