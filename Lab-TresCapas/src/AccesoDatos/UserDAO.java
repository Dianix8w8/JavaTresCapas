package AccesoDatos;

import LogicaNegocio.User;
import java.util.List;

/**
 * Interfaz DAO para la gestión de usuarios.
 */
public interface UserDAO {
    int addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUserById(int id);
    List<User> getAllUsers();
}
