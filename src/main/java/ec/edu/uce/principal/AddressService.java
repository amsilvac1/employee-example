package ec.edu.uce.principal;



import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


@Stateless
public class AddressService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeUnity");
    private EntityManager em;


    public AddressService() {
        em = emf.createEntityManager();
    }

    public void save(Address address) {

        em.persist(address);

    }


}
