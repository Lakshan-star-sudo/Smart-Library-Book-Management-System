package gui;

import library.app.LibrarySystem;
import library.model.Member;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login extends JFrame {

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private static final Color SUBTEXT_COLOR =
            new Color(107, 114, 128);

    private static final Color BUTTON_COLOR =
            new Color(79, 70, 229);

    private static final Color BORDER_COLOR =
            new Color(229, 231, 235);


    private final LibrarySystem librarySystem;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JLabel usernameLabel;


    public Login(
            LibrarySystem librarySystem
    ) {

        this.librarySystem = librarySystem;

        setTitle("Smart Library - Login");

        setSize(500, 590);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        setLocationRelativeTo(null);

        createUI();
    }


    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new GridBagLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );


        JPanel cardPanel =
                new JPanel(
                        new GridBagLayout()
                );

        cardPanel.setBackground(
                CARD_COLOR
        );

        cardPanel.setPreferredSize(
                new Dimension(
                        390,
                        490
                )
        );

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                25,
                                35,
                                25,
                                35
                        )
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;


        int row = 0;

        JLabel titleLabel =
                new JLabel(
                        "SMART LIBRARY",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                TEXT_COLOR
        );


        gbc.gridy = row++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        5,
                        0
                );

        cardPanel.add(
                titleLabel,
                gbc
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Library Management System",
                        SwingConstants.CENTER
                );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(
                SUBTEXT_COLOR
        );


        gbc.gridy = row++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        25,
                        0
                );

        cardPanel.add(
                subtitleLabel,
                gbc
        );

        JLabel roleLabel =
                createLabel(
                        "Login As"
                );


        gbc.gridy = row++;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        6,
                        0
                );

        cardPanel.add(
                roleLabel,
                gbc
        );


        roleComboBox =
                new JComboBox<>(
                        new String[]{
                                "Customer",
                                "Admin"
                        }
                );

        styleInput(
                roleComboBox
        );


        gbc.gridy = row++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        16,
                        0
                );

        cardPanel.add(
                roleComboBox,
                gbc
        );

        usernameLabel =
                createLabel(
                        "Email or Member ID"
                );


        gbc.gridy = row++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        6,
                        0
                );

        cardPanel.add(
                usernameLabel,
                gbc
        );


        usernameField =
                new JTextField();

        styleInput(
                usernameField
        );


        gbc.gridy = row++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        16,
                        0
                );

        cardPanel.add(
                usernameField,
                gbc
        );


        JLabel passwordLabel =
                createLabel(
                        "Password"
                );


        gbc.gridy = row++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        6,
                        0
                );

        cardPanel.add(
                passwordLabel,
                gbc
        );


        passwordField =
                new JPasswordField();

        styleInput(
                passwordField
        );


        gbc.gridy = row++;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        24,
                        0
                );

        cardPanel.add(
                passwordField,
                gbc
        );


        JButton loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setBackground(
                BUTTON_COLOR
        );

        loginButton.setFocusPainted(
                false
        );

        loginButton.setBorderPainted(
                false
        );

        loginButton.setOpaque(
                true
        );

        loginButton.setContentAreaFilled(
                true
        );

        loginButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        loginButton.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
        );


        gbc.gridy = row++;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        18,
                        0
                );

        cardPanel.add(
                loginButton,
                gbc
        );


        JLabel infoLabel =
                new JLabel(
                        "Members can login using Email or Member ID",
                        SwingConstants.CENTER
                );

        infoLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        infoLabel.setForeground(
                SUBTEXT_COLOR
        );


        gbc.gridy = row;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        cardPanel.add(
                infoLabel,
                gbc
        );


        // Add card to center
        mainPanel.add(
                cardPanel
        );

        setContentPane(
                mainPanel
        );


        roleComboBox.addActionListener(
                e -> {

                    String role =
                            (String)
                                    roleComboBox
                                            .getSelectedItem();

                    if ("Admin".equals(role)) {

                        usernameLabel.setText(
                                "Username"
                        );

                    } else {

                        usernameLabel.setText(
                                "Email or Member ID"
                        );
                    }
                }
        );


        loginButton.addActionListener(
                e -> login()
        );


        passwordField.addActionListener(
                e -> login()
        );
    }


    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();


        String password =
                new String(
                        passwordField
                                .getPassword()
                );


        String role =
                (String)
                        roleComboBox
                                .getSelectedItem();


        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter login details.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        if ("Admin".equals(role)) {

            if (username.equals("admin")
                    && password.equals("admin123")) {

                AdminPanel adminPanel =
                        new AdminPanel(
                                librarySystem
                        );

                adminPanel.setVisible(
                        true
                );

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid admin username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            return;
        }


        Member member =
                librarySystem
                        .getMemberManager()
                        .authenticateMember(
                                username,
                                password
                        );


        if (member == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Member ID/Email or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        JOptionPane.showMessageDialog(
                this,
                "Welcome, "
                        + member.getName()
                        + "!",
                "Login Successful",
                JOptionPane.INFORMATION_MESSAGE
        );


        CustomerPanel customerPanel =
                new CustomerPanel(
                        librarySystem,
                        member
                );

        customerPanel.setVisible(
                true
        );

        dispose();
    }


    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        return label;
    }

    private void styleInput(
            JComponent component
    ) {

        component.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        component.setPreferredSize(
                new Dimension(
                        300,
                        38
                )
        );

        component.setMinimumSize(
                new Dimension(
                        300,
                        38
                )
        );

        component.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );
    }
}