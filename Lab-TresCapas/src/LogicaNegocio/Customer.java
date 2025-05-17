package LogicaNegocio;

import java.time.LocalDate;

/**
 * Clase Customer que hereda de User, representando a un cliente del sistema.
 */
public class Customer extends User {
    private String name;
    private String billingAddress;
    private String defaultShippingAddress;

    /**
     * Constructor de la clase Customer.
     * @param id Identificador único del cliente.
     * @param email Correo electrónico del cliente.
     * @param password Contraseña del cliente.
     * @param lastLogin Última fecha de inicio de sesión.
     * @param name Nombre del cliente.
     * @param billingAddress Dirección de facturación del cliente.
     * @param defaultShippingAddress Dirección de envío predeterminada del cliente.
     */
    public Customer(int id, String email, String password, LocalDate lastLogin, String name, String billingAddress, String defaultShippingAddress) {
        super(id, email, password, lastLogin); // Llama al constructor de User
        this.name = name;
        this.billingAddress = billingAddress;
        this.defaultShippingAddress = defaultShippingAddress;
    }

    // Métodos Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDefaultShippingAddress() {
        return defaultShippingAddress;
    }

    public void setDefaultShippingAddress(String defaultShippingAddress) {
        this.defaultShippingAddress = defaultShippingAddress;
    }
}
