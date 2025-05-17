package AccesoDatos;

import LogicaNegocio.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación DAO para la tabla users en MySQL.
 */
public class UserDAOImpl implements UserDAO {

    /**
     * Agrega un nuevo usuario a la base de datos.
     * @param user El usuario a insertar.
     * @return El ID generado para el usuario.
     */
    @Override
    public int addUser(User user) {
        String query = "INSERT INTO users (email, password, lastLogin) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setDate(3, java.sql.Date.valueOf(user.getLastLogin())); 

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Retorna el ID generado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Si hay error, retorna -1
    }

    /**
     * Actualiza los datos de un usuario en la base de datos.
     * @param user Usuario con los nuevos datos.
     */
    @Override
    public void updateUser(User user) {
        String query = "UPDATE users SET email=?, password=?, lastLogin=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setDate(3, java.sql.Date.valueOf(user.getLastLogin())); 
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();

            System.out.println("✅ Usuario actualizado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     * @param id Identificador del usuario a eliminar.
     */
    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("✅ Usuario eliminado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id ID del usuario.
     * @return El objeto User o null si no existe.
     */
    @Override
    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getDate("lastLogin").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene todos los usuarios registrados en la base de datos.
     * @return Lista de usuarios.
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getDate("lastLogin").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
