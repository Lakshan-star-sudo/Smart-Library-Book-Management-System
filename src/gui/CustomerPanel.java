package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomerPanel extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(35, 45, 55);
    private final Color SUBTEXT_COLOR = new Color(100, 110, 120);
    private final Color BUTTON_COLOR = new Color(45, 95, 160);

    public CustomerPanel() {

        setTitle("Customer - Smart Library");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                new JLabel("CUSTOMER PORTAL");

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
                        "Welcome to Smart Library"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        subtitleLabel.setForeground(
                SUBTEXT_COLOR
        );

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

        // ================= DASHBOARD CARDS =================

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

        // 1. Browse Books

        JButton booksButton =
                createCard(
                        "BROWSE BOOKS",
                        "Search and explore available books"
                );

        // 2. Borrow / Return

        JButton borrowButton =
                createCard(
                        "BORROW / RETURN",
                        "Borrow or return library books"
                );

        // 3. Recommendations

        JButton recommendationButton =
                createCard(
                        "RECOMMENDATIONS",
                        "Get smart book recommendations"
                );

        // 4. Waiting List

        JButton waitingButton =
                createCard(
                        "WAITING LIST",
                        "Manage books you are waiting for"
                );

        // 5. Overdue

        JButton overdueButton =
                createCard(
                        "MY OVERDUE BOOKS",
                        "Check overdue library books"
                );

        // 6. My Account

        JButton accountButton =
                createCard(
                        "MY ACCOUNT",
                        "View your library account"
                );

        cardPanel.add(booksButton);
        cardPanel.add(borrowButton);
        cardPanel.add(recommendationButton);
        cardPanel.add(waitingButton);
        cardPanel.add(overdueButton);
        cardPanel.add(accountButton);

        mainPanel.add(
                cardPanel,
                BorderLayout.CENTER
        );

        // ================= FOOTER =================

        JLabel footerLabel =
                new JLabel(
                        "Smart Library • Customer Portal",
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

        // ================= BUTTON ACTIONS =================

        booksButton.addActionListener(e ->
                showBookSearch()
        );

        borrowButton.addActionListener(e ->
                showMessage(
                        "Borrow / Return",
                        "Borrow and Return functionality "
                                + "will be connected here."
                )
        );

        recommendationButton.addActionListener(e ->
                new RecommendationPanel().setVisible(true)
        );

        waitingButton.addActionListener(e ->
                new WaitingListPanel().setVisible(true)
        );

        overdueButton.addActionListener(e ->
                new OverduePanel().setVisible(true)
        );

        accountButton.addActionListener(e ->
                showMessage(
                        "My Account",
                        "Customer account details "
                                + "will be displayed here."
                )
        );

        add(mainPanel);
    }

    // ================= BOOK SEARCH =================

    private void showBookSearch() {

        JFrame searchFrame =
                new JFrame("Browse Books");

        searchFrame.setSize(750, 550);
        searchFrame.setLocationRelativeTo(this);
        searchFrame.setResizable(false);

        JPanel panel =
                new JPanel(new BorderLayout(15, 15));

        panel.setBackground(BACKGROUND);

        panel.setBorder(
                new EmptyBorder(
                        25, 30, 25, 30
                )
        );

        JPanel topPanel =
                new JPanel();

        topPanel.setBackground(CARD_COLOR);

        JLabel searchLabel =
                new JLabel("Search Book");

        searchLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        JTextField searchField =
                new JTextField(20);

        JButton searchButton =
                createSmallButton("SEARCH");

        JButton viewButton =
                createSmallButton("VIEW ALL");

        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(viewButton);

        JTextArea resultArea =
                new JTextArea();

        resultArea.setEditable(false);

        resultArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        resultArea.setBackground(CARD_COLOR);

        resultArea.setBorder(
                new EmptyBorder(
                        20, 20, 20, 20
                )
        );

        resultArea.setText(
                "Search for a book or click "
                        + "\"VIEW ALL\"."
        );

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        searchButton.addActionListener(e -> {

            String text =
                    searchField.getText().trim();

            if (text.isEmpty()) {

                resultArea.setText(
                        "Please enter a book name "
                                + "or Book ID."
                );

            } else {

                resultArea.setText(
                        "BOOK SEARCH\n\n"
                                + "Searching for: "
                                + text
                                + "\n\n"
                                + "Actual BST search will "
                                + "be connected here."
                );
            }
        });

        viewButton.addActionListener(e -> {

            resultArea.setText(
                    "AVAILABLE BOOKS\n\n"
                            + "1. Data Structures\n"
                            + "2. Java Programming\n"
                            + "3. Database Systems\n"
                            + "4. Software Engineering\n"
                            + "5. Clean Code"
            );
        });

        panel.add(
                topPanel,
                BorderLayout.NORTH
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        searchFrame.add(panel);

        searchFrame.setVisible(true);
    }

    // ================= CARD DESIGN =================

    private JButton createCard(
            String title,
            String description) {

        JButton button =
                new JButton();

        button.setLayout(
                new BoxLayout(
                        button,
                        BoxLayout.Y_AXIS
                )
        );

        button.setBackground(CARD_COLOR);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

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

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(TEXT_COLOR);

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

    // ================= SMALL BUTTON =================

    private JButton createSmallButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(BUTTON_COLOR);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

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

            CustomerPanel panel =
                    new CustomerPanel();

            panel.setVisible(true);
        });
    }
}