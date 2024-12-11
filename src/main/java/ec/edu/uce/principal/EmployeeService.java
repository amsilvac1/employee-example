package ec.edu.uce.principal;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

@Stateless
public class EmployeeService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeUnity");
    private EntityManager em;

    public EmployeeService() {

        em = emf.createEntityManager();
    }

    public void save(Employee employee) {
        try {
            em.persist(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeebyId(int id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> getAllEmployees() {
        String query = "SELECT e FROM Employee e";
        return em.createQuery(query, Employee.class).getResultList();
    }

    public void saveWithAddress(Employee employee, Address address) {
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        //metodo para obtener todas las direcciones
        public List<Employee> getAllEmployeeswithaddress() {
            String query = "SELECT e FROM Employee e LEFT JOIN FETCH e.addresses";  // Asegúrate de cargar las direcciones.
            return em.createQuery(query, Employee.class).getResultList();
        }


}