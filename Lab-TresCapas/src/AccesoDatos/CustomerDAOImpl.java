package AccesoDatos;

import LogicaNegocio.Customer;
import LogicaNegocio.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación DAO para la tabla customers en MySQL.
 */
public class CustomerDAOImpl implements CustomerDAO {
    private UserDAO userDAO = new UserDAOImpl(); // Relación con UserDAO

    /**
     * Agrega un nuevo cliente a la base de datos.
     * Primero inserta el User y luego el Customer.
     * @param customer Cliente a registrar.
     */
    @Override
    public void addCustomer(Customer customer) {
        int userId = userDAO.addUser(customer); // Inserta el usuario y obtiene su ID
        if (userId == -1) {
            System.err.println("❌ Error al insertar el usuario");
            return;
        }

        String query = "INSERT INTO customers (id, name, billingAddress, defaultShippingAddress) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getBillingAddress());
            stmt.setString(4, customer.getDefaultShippingAddress());
            stmt.executeUpdate();

            System.out.println("✅ Cliente registrado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza los datos de un cliente.
     * Primero actualiza en la tabla users y luego en customers.
     * @param customer Cliente a actualizar.
     */
    @Override
    public void updateCustomer(Customer customer) {
        userDAO.updateUser(customer); // Actualiza la información de User

        String query = "UPDATE customers SET name=?, billingAddress=?, defaultShippingAddress=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getBillingAddress());
            stmt.setString(3, customer.getDefaultShippingAddress());
            stmt.setInt(4, customer.getId());
            stmt.executeUpdate();

            System.out.println("✅ Cliente actualizado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un cliente por su ID.
     * Se eliminan registros en ambas tablas por integridad.
     * @param id ID del cliente a eliminar.
     */
    @Override
    public void deleteCustomer(int id) {
        userDAO.deleteUser(id); // Se eliminará automáticamente en customers por la FK con ON DELETE CASCADE
        System.out.println("✅ Cliente eliminado con éxito.");
    }

    /**
     * Obtiene un cliente por su ID.
     * Se recupera información de users y customers.
     * @param id ID del cliente.
     * @return Cliente encontrado o null si no existe.
     */
    @Override
    public Customer getCustomerById(int id) {
        User user = userDAO.getUserById(id);
        if (user == null) {
            return null;
        }

        String query = "SELECT * FROM customers WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getLastLogin(),
                    rs.getString("name"),
                    rs.getString("billingAddress"),
                    rs.getString("defaultShippingAddress")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene todos los clientes registrados en la base de datos.
     * @return Lista de clientes.
     */
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT c.id, c.name, c.billingAddress, c.defaultShippingAddress, u.email, u.password, u.lastLogin " +
                       "FROM customers c INNER JOIN users u ON c.id = u.id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getDate("lastLogin").toLocalDate(),
                    rs.getString("name"),
                    rs.getString("billingAddress"),
                    rs.getString("defaultShippingAddress")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
