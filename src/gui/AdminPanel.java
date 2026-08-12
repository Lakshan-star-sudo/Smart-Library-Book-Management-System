package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminPanel extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(35, 45, 55);
    private final Color SUBTEXT_COLOR = new Color(100, 110, 120);
    private final Color BUTTON_COLOR = new Color(45, 95, 160);

    public AdminPanel() {

        setTitle("Admin - Smart Library");
        setSize(900, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(30, 40, 25, 40)
        );

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();

        headerPanel.setBackground(BACKGROUND);

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel("ADMIN PANEL");

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setForeground(TEXT_COLOR);

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Manage and monitor the Smart Library"
                );

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

        headerPanel.add(titleLabel);

        headerPanel.add(
                Box.createVerticalStrut(6)
        );

        headerPanel.add(subtitleLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ================= CARDS =================

        JPanel cardPanel =
                new JPanel(
                        new GridLayout(
                                3, 2, 20, 20
                        )
                );

        cardPanel.setBackground(BACKGROUND);

        cardPanel.setBorder(
                new EmptyBorder(
                        20, 20, 10, 20
                )
        );

        JButton booksButton =
                createCard(
                        "MANAGE BOOKS",
                        "Add, update, delete and search books"
                );

        JButton membersButton =
                createCard(
                        "MANAGE MEMBERS",
                        "Manage library member information"
                );

        JButton borrowingButton =
                createCard(
                        "BORROWING RECORDS",
                        "View and manage borrowing records"
                );

        JButton overdueButton =
                createCard(
                        "OVERDUE BOOKS",
                        "Monitor overdue library books"
                );

        JButton waitingListButton =
                createCard(
                        "WAITING LIST",
                        "Manage waiting list requests"
                );

        JButton reportsButton =
                createCard(
                        "LIBRARY REPORTS",
                        "View important library information"
                );

        cardPanel.add(booksButton);
        cardPanel.add(membersButton);
        cardPanel.add(borrowingButton);
        cardPanel.add(overdueButton);
        cardPanel.add(waitingListButton);
        cardPanel.add(reportsButton);

        mainPanel.add(
                cardPanel,
                BorderLayout.CENTER
        );

        // ================= FOOTER =================

        JLabel footerLabel =
                new JLabel(
                        "Administrator • Smart Library Management System",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        footerLabel.setForeground(
                SUBTEXT_COLOR
        );

        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ================= TEMPORARY ACTIONS =================

        booksButton.addActionListener(e ->
                showMessage(
                        "Book Management",
                        "Book CRUD will be connected here."
                )
        );

        membersButton.addActionListener(e ->
                showMessage(
                        "Member Management",
                        "Member management will be connected here."
                )
        );

        borrowingButton.addActionListener(e ->
                showMessage(
                        "Borrowing Records",
                        "Borrowing records will be connected here."
                )
        );

        overdueButton.addActionListener(e ->
                new OverduePanel().setVisible(true)
        );

        waitingListButton.addActionListener(e ->
                new WaitingListPanel().setVisible(true)
        );

        reportsButton.addActionListener(e ->
                showMessage(
                        "Library Reports",
                        "Reports will be added later."
                )
        );
    }

    // ================= CARD DESIGN =================

    private JButton createCard(
            String title,
            String description) {

        JButton button = new JButton();

        button.setLayout(
                new BoxLayout(
                        button,
                        BoxLayout.Y_AXIS
                )
        );

        button.setBackground(CARD_COLOR);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 235)
                        ),
                        new EmptyBorder(
                                18, 18, 18, 18
                        )
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(
                TEXT_COLOR
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                SUBTEXT_COLOR
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.add(titleLabel);

        button.add(
                Box.createVerticalStrut(8)
        );

        button.add(descriptionLabel);

        return button;
    }

    // ================= MESSAGE =================

    private void showMessage(
            String title,
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            AdminPanel panel =
                    new AdminPanel();

            panel.setVisible(true);
        });
    }
}