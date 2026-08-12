package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(35, 45, 55);
    private final Color SUBTEXT_COLOR = new Color(100, 110, 120);

    public Dashboard() {

        setTitle("Smart Library Management System");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(30, 45, 25, 45));

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(BACKGROUND);
        headerPanel.setLayout(
                new BoxLayout(headerPanel, BoxLayout.Y_AXIS)
        );

        JLabel titleLabel =
                new JLabel("SMART LIBRARY");

        titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 32)
        );

        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel =
                new JLabel("Library Management System");

        subtitleLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 16)
        );

        subtitleLabel.setForeground(SUBTEXT_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(6));
        headerPanel.add(subtitleLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ================= CARDS =================

        JPanel cardPanel =
                new JPanel(new GridLayout(3, 2, 20, 20));

        cardPanel.setBackground(BACKGROUND);
        cardPanel.setBorder(
                new EmptyBorder(20, 20, 10, 20)
        );

        JButton booksButton =
                createCard(
                        "BOOKS",
                        "Manage library books"
                );

        JButton membersButton =
                createCard(
                        "MEMBERS",
                        "Manage library members"
                );

        JButton borrowButton =
                createCard(
                        "BORROW / RETURN",
                        "Manage borrowing activities"
                );

        JButton recommendationButton =
                createCard(
                        "RECOMMENDATIONS",
                        "Get smart book suggestions"
                );

        JButton overdueButton =
                createCard(
                        "OVERDUE BOOKS",
                        "Track overdue books"
                );

        JButton waitingListButton =
                createCard(
                        "WAITING LIST",
                        "Manage waiting requests"
                );

        cardPanel.add(booksButton);
        cardPanel.add(membersButton);
        cardPanel.add(borrowButton);
        cardPanel.add(recommendationButton);
        cardPanel.add(overdueButton);
        cardPanel.add(waitingListButton);

        mainPanel.add(
                cardPanel,
                BorderLayout.CENTER
        );

        // ================= FOOTER =================

        JLabel footerLabel =
                new JLabel(
                        "Smart Library • Easy • Fast • Organized",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 13)
        );

        footerLabel.setForeground(SUBTEXT_COLOR);

        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ================= RECOMMENDATION =================

        recommendationButton.addActionListener(e -> {

            RecommendationPanel panel =
                    new RecommendationPanel();

            panel.setVisible(true);
        });

        overdueButton.addActionListener(e -> {

            OverduePanel panel =
                    new OverduePanel();

            panel.setVisible(true);
        });
        waitingListButton.addActionListener(e -> {

            WaitingListPanel panel =
                    new WaitingListPanel();

            panel.setVisible(true);
        });
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
        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 235),
                                1
                        ),
                        new EmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
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
                        13
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

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.setVisible(true);
        });
    }
}