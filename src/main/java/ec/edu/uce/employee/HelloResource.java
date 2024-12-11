package ec.edu.uce.employee;


import ec.edu.uce.principal.Address;
import ec.edu.uce.principal.AddressService;
import ec.edu.uce.principal.Employee;
import ec.edu.uce.principal.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;


@Path("/employee")
public class HelloResource {

    @Inject
    EmployeeService employeeService;

    @Inject
    AddressService addressService;

    //crear un empleado
    @GET
    @Produces("text/plain")
    @Path("/get")
    public String getEmployee() {
        Employee employee = new Employee();
        employee.setName("Carlos");
        employeeService.save(employee);
        return "hola mundo";
    }

    //encontrar un empleado por id
    @GET
    @Produces("text/plain")
    @Path("/get/{id}")
    public String getEmployee(@PathParam("id") int id) {
        Employee employee = employeeService.getEmployeebyId(id);
        if (employee != null) {
            return "hola " + employee.getName();
        } else
            return "no se encontro el empleado";
    }

    @GET
    @Produces("text/plain")
    @Path("/get/all")
    public String getAllEmployees() {
        String ListEmployees = "";
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            ListEmployees += employee.getName() + " - " + employee.getAddresses().toString() + "\n";

        }
        return ListEmployees;
    }

    @GET
    @Produces("text/plain")
    @Path("/get/save_with_address")
    public String saveEmployeeWithAddress(@QueryParam("name") String name,
                                          @QueryParam("street") String street) {
        Address address = new Address();
        address.setStreet(street);
        List<Address> addresses = List.of(address);


        Employee employee = new Employee();
        employee.setName(name);
        employee.setAddresses(addresses);

        employeeService.saveWithAddress(employee, address);
        return "Empleado guardado con dirección";

    }

    @GET
    @Produces("text/plain")
    @Path("/get/all")
    public String getAllEmployeesWithAddresses() {
        StringBuilder listEmployees = new StringBuilder();  // Usamos StringBuilder para mejorar la concatenación de Strings
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee employee : employees) {
            listEmployees.append("Empleado: ").append(employee.getName()).append("\n");
            // Verificar si el empleado tiene direcciones asociadas
            if (employee.getAddresses() != null && !employee.getAddresses().isEmpty()) {
                for (Address address : employee.getAddresses()) {
                    listEmployees.append("  Dirección: ").append(address.getStreet()).append(", ")
                            .append(address.getCity()).append("\n");  // Ajusta según las propiedades de tu clase Address
                }
            } else {
                listEmployees.append("  Sin direcciones asociadas.\n");
            }
        }

        return listEmployees.toString();  // Devuelve el texto con la lista de empleados y sus direcciones
    }

}
