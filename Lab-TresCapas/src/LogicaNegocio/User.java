package LogicaNegocio;

import java.time.LocalDate;

/**
 * Clase User que representa a un usuario en el sistema.
 */
public class User {
    private int id;
    private String email;
    private String password;
    private LocalDate lastLogin;

    public User(int id, String email, String password, LocalDate lastLogin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
    }

    // Métodos Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }
}

