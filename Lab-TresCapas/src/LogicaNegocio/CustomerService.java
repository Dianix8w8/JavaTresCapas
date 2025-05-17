package LogicaNegocio;

import AccesoDatos.CustomerDAO;
import AccesoDatos.CustomerDAOImpl;
import java.util.List;

/**
 * Servicio que maneja la lógica de negocio para los clientes.
 */
public class CustomerService {
    private CustomerDAO customerDAO;

    /**
     * Constructor que inicializa el DAO.
     */
    public CustomerService() {
        this.customerDAO = new CustomerDAOImpl();
    }

    /**
     * Método para registrar un nuevo cliente (Create).
     */
    public void registerCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    /**
     * Método para obtener un cliente por ID (Read).
     */
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    /**
     * Método para obtener todos los clientes (Read).
     */
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    /**
     * Método para actualizar un cliente (Update).
     */
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    /**
     * Método para eliminar un cliente por ID (Delete).
     */
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }
}
