package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(35, 45, 55);
    private final Color SUBTEXT_COLOR = new Color(100, 110, 120);
    private final Color BUTTON_COLOR = new Color(45, 95, 160);

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public Login() {

        setTitle("Smart Library - Login");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(
                new EmptyBorder(30, 40, 30, 40)
        );

        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(CARD_COLOR);
        cardPanel.setLayout(
                new BoxLayout(cardPanel, BoxLayout.Y_AXIS)
        );

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 235)
                        ),
                        new EmptyBorder(
                                30, 35, 30, 35
                        )
                )
        );

        // ===== TITLE =====

        JLabel titleLabel =
                new JLabel("SMART LIBRARY");

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel =
                new JLabel("Library Management System");

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(SUBTEXT_COLOR);
        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        cardPanel.add(titleLabel);

        cardPanel.add(
                Box.createVerticalStrut(6)
        );

        cardPanel.add(subtitleLabel);

        cardPanel.add(
                Box.createVerticalStrut(30)
        );

        // ===== USERNAME =====

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        usernameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        cardPanel.add(usernameLabel);

        cardPanel.add(
                Box.createVerticalStrut(6)
        );

        usernameField = new JTextField();

        usernameField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        usernameField.setMaximumSize(
                new Dimension(300, 38)
        );

        cardPanel.add(usernameField);

        cardPanel.add(
                Box.createVerticalStrut(15)
        );

        // ===== PASSWORD =====

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        passwordLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        cardPanel.add(passwordLabel);

        cardPanel.add(
                Box.createVerticalStrut(6)
        );

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        passwordField.setMaximumSize(
                new Dimension(300, 38)
        );

        cardPanel.add(passwordField);

        cardPanel.add(
                Box.createVerticalStrut(15)
        );

        // ===== ROLE =====

        JLabel roleLabel =
                new JLabel("Login As");

        roleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        roleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        cardPanel.add(roleLabel);

        cardPanel.add(
                Box.createVerticalStrut(6)
        );

        roleComboBox =
                new JComboBox<>(
                        new String[]{
                                "Customer",
                                "Admin"
                        }
                );

        roleComboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        roleComboBox.setMaximumSize(
                new Dimension(300, 38)
        );

        cardPanel.add(roleComboBox);

        cardPanel.add(
                Box.createVerticalStrut(25)
        );

        // ===== LOGIN BUTTON =====

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(BUTTON_COLOR);

        loginButton.setFocusPainted(false);

        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        loginButton.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 40, 12, 40
                )
        );

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        cardPanel.add(loginButton);

        cardPanel.add(
                Box.createVerticalStrut(20)
        );

        JLabel infoLabel =
                new JLabel(
                        "Enter your credentials to continue"
                );

        infoLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        infoLabel.setForeground(SUBTEXT_COLOR);

        infoLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        cardPanel.add(infoLabel);

        mainPanel.add(cardPanel);

        add(mainPanel);

        // ===== LOGIN ACTION =====

        loginButton.addActionListener(
                e -> login()
        );
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String role =
                (String) roleComboBox.getSelectedItem();

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        /*
         * Temporary login validation.
         * Actual authentication can be connected
         * later with the member/admin data.
         */

        if (role.equals("Admin")
                && username.equals("admin")
                && password.equals("admin123")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Admin login successful!",
                    "Login",
                    JOptionPane.INFORMATION_MESSAGE
            );

            new AdminPanel().setVisible(true);
            dispose();

        } else if (role.equals("Customer")
                && username.equals("customer")
                && password.equals("customer123")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer login successful!",
                    "Login",
                    JOptionPane.INFORMATION_MESSAGE
            );

            new CustomerPanel().setVisible(true);
            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username, password or role.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Login login = new Login();

            login.setVisible(true);
        });
    }
}