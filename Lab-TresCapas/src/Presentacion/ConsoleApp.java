package Presentacion;

import LogicaNegocio.Customer;
import LogicaNegocio.CustomerService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Aplicación de consola para gestionar clientes.
 */
public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService service = new CustomerService();

        System.out.println("Ingrese el ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el email del cliente: ");
        String email = scanner.nextLine();
        System.out.println("Ingrese la contraseña del cliente: ");
        String password = scanner.nextLine();




        
        System.out.println("Ingrese la última fecha de inicio de sesión (YYYY-MM-DD): ");
        LocalDate lastLogin = LocalDate.now();
        boolean fechaValida = false;
        while (!fechaValida) {
        try {
            String fechaInput = scanner.nextLine();
            lastLogin = LocalDate.parse(fechaInput, DateTimeFormatter.ISO_LOCAL_DATE);
            fechaValida = true;  // Si el parseo es exitoso, salimos del bucle
        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato inválido. Intente de nuevo (YYYY-MM-DD): ");
        }
        }

            
        System.out.println("Ingrese el nombre del cliente: ");
        String name = scanner.nextLine();
        System.out.println("Ingrese la dirección de facturación: ");
        String billingAddress = scanner.nextLine();
        System.out.println("Ingrese la dirección de envío predeterminada: ");
        String defaultShippingAddress = scanner.nextLine();

        Customer customer = new Customer(id, email, password, lastLogin, name, billingAddress, defaultShippingAddress);
        
        service.registerCustomer(customer);

        System.out.println("Cliente registrado con éxito!");
    }
}
