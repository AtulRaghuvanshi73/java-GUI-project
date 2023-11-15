import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public login() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Set the title of the window
        setTitle("Manga DB");

        // Set the default operation when the close button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // Set layout manager
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add components to the form panel
        formPanel.add(usernameLabel);
        formPanel.add(createStyledTextField(usernameField));
        formPanel.add(passwordLabel);
        formPanel.add(createStyledPasswordField(passwordField));
        formPanel.add(new JLabel()); // Empty label for spacing
        formPanel.add(loginButton);

        // Style the login button
        loginButton.setBackground(new Color(51, 153, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // Add ActionListener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform login validation
                if (validateLogin(usernameField.getText(), new String(passwordField.getPassword()))) {
                    // If login is successful, open the ImageSection page
                    openImageSection();
                } else {
                    // Show an error message for unsuccessful login
                    JOptionPane.showMessageDialog(login.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the content pane
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        // Pack the components and set the location of the window
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen

        // Make the window visible
        setVisible(true);
    }

    private boolean validateLogin(String username, String password) {
        // Perform a simple validation (you can replace this with your authentication logic)
        return "admin".equals(username) && "password".equals(password);
    }

    private void openImageSection() {
        // Close the login page
        dispose();

        // Open the ImageSection page
        SwingUtilities.invokeLater(() -> new ImageSection());
    }

    private JTextField createStyledTextField(JTextField textField) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return textField;
    }

    private JPasswordField createStyledPasswordField(JPasswordField passwordField) {
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return passwordField;
    }

    public static void main(String[] args) {
        // Create and run the Swing program on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new login());
    }
}
