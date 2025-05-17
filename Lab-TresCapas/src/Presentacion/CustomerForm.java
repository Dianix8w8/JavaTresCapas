package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LogicaNegocio.CustomerService;
import LogicaNegocio.Customer;
import java.time.LocalDate;

public class CustomerForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Clientes");
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 10, 150, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(180, 10, 165, 25);
        panel.add(idText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 40, 150, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(180, 40, 165, 25);
        panel.add(emailText);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 70, 150, 25);
        panel.add(passwordLabel);

        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(180, 70, 165, 25);
        panel.add(passwordText);

        JLabel lastLoginLabel = new JLabel("Último Login (YYYY-MM-DD):");
        lastLoginLabel.setBounds(10, 100, 180, 25);
        panel.add(lastLoginLabel);

        JTextField lastLoginText = new JTextField(20);
        lastLoginText.setBounds(180, 130, 165, 25);
        panel.add(lastLoginText);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(10, 160, 150, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(180, 160, 165, 25);
        panel.add(nameText);

        JLabel billingAddressLabel = new JLabel("Dirección de Facturación:");
        billingAddressLabel.setBounds(10, 190, 150, 25);
        panel.add(billingAddressLabel);

        JTextField billingAddressText = new JTextField(20);
        billingAddressText.setBounds(180, 190, 165, 25);
        panel.add(billingAddressText);

        JLabel defaultShippingLabel = new JLabel("Dirección de Envío:");
        defaultShippingLabel.setBounds(10, 220, 150, 25);
        panel.add(defaultShippingLabel);

        JTextField defaultShippingText = new JTextField(20);
        defaultShippingText.setBounds(180, 220, 165, 25);
        panel.add(defaultShippingText);

        CustomerService service = new CustomerService();

        JButton createButton = new JButton("Registrar");
        createButton.setBounds(10, 260, 100, 25);
        panel.add(createButton);

        createButton.addActionListener(e -> {
            Customer customer = getCustomerFromFields(idText, emailText, passwordText, lastLoginText, nameText, billingAddressText, defaultShippingText);
            service.registerCustomer(customer);
            JOptionPane.showMessageDialog(null, "Cliente registrado!");
        });

        JButton readButton = new JButton("Buscar");
        readButton.setBounds(120, 260, 80, 25);
        panel.add(readButton);

        readButton.addActionListener(e -> {
            int id = Integer.parseInt(idText.getText());
            Customer customer = service.getCustomerById(id);
            if (customer != null) {
                emailText.setText(customer.getEmail());
                passwordText.setText(customer.getPassword());
                lastLoginText.setText(customer.getLastLogin().toString());
                nameText.setText(customer.getName());
                billingAddressText.setText(customer.getBillingAddress());
                defaultShippingText.setText(customer.getDefaultShippingAddress());
                JOptionPane.showMessageDialog(null, "Cliente encontrado.");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        });

        JButton updateButton = new JButton("Actualizar");
        updateButton.setBounds(210, 260, 100, 25);
        panel.add(updateButton);

        updateButton.addActionListener(e -> {
            Customer customer = getCustomerFromFields(idText, emailText, passwordText, lastLoginText, nameText, billingAddressText, defaultShippingText);
            service.updateCustomer(customer);
            JOptionPane.showMessageDialog(null, "Cliente actualizado.");
        });

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setBounds(320, 260, 80, 25);
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            int id = Integer.parseInt(idText.getText());
            service.deleteCustomer(id);
            clearFields(emailText, passwordText, lastLoginText, nameText, billingAddressText, defaultShippingText);
            JOptionPane.showMessageDialog(null, "Cliente eliminado.");
        });
    }

    private static Customer getCustomerFromFields(JTextField id, JTextField email, JTextField password, JTextField lastLogin,
                                                  JTextField name, JTextField billing, JTextField shipping) {
        return new Customer(
                Integer.parseInt(id.getText()),
                email.getText(),
                password.getText(),
                LocalDate.parse(lastLogin.getText()),
                name.getText(),
                billing.getText(),
                shipping.getText()
        );
    }

    private static void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
