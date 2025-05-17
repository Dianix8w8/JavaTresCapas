package AccesoDatos;

import LogicaNegocio.Customer;
import java.util.List;

/**
 * Interfaz DAO para la gestión de clientes.
 */
public interface CustomerDAO {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
}
