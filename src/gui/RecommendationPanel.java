package gui;

import recommendation.RecommendationManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RecommendationPanel extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(35, 45, 55);
    private final Color SUBTEXT_COLOR = new Color(100, 110, 120);
    private final Color BUTTON_COLOR = new Color(45, 95, 160);

    private JComboBox<String> categoryComboBox;
    private JTextArea resultArea;

    public RecommendationPanel() {

        setTitle("Smart Recommendations");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(
                new EmptyBorder(30, 40, 30, 40)
        );

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(BACKGROUND);
        headerPanel.setLayout(
                new BoxLayout(headerPanel, BoxLayout.Y_AXIS)
        );

        JLabel titleLabel =
                new JLabel("BOOK RECOMMENDATIONS");

        titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 28)
        );

        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Find books based on your interests"
                );

        subtitleLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
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

        // ================= SELECTION CARD =================

        JPanel selectionPanel = new JPanel();

        selectionPanel.setLayout(
                new BoxLayout(
                        selectionPanel,
                        BoxLayout.Y_AXIS
                )
        );

        selectionPanel.setBackground(CARD_COLOR);

        selectionPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 235)
                        ),
                        new EmptyBorder(
                                20, 30, 20, 30
                        )
                )
        );

        // Category label

        JLabel categoryLabel =
                new JLabel("Select a Book Category");

        categoryLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        categoryLabel.setForeground(TEXT_COLOR);
        categoryLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        selectionPanel.add(categoryLabel);

        selectionPanel.add(
                Box.createVerticalStrut(10)
        );

        // Category ComboBox

        categoryComboBox =
                new JComboBox<>(
                        new String[]{
                                "Computing",
                                "Fiction",
                                "Software Engineering"
                        }
                );

        categoryComboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        categoryComboBox.setPreferredSize(
                new Dimension(300, 40)
        );

        categoryComboBox.setMaximumSize(
                new Dimension(300, 40)
        );

        categoryComboBox.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        selectionPanel.add(categoryComboBox);

        selectionPanel.add(
                Box.createVerticalStrut(15)
        );

        // Recommendation Button

        JButton recommendButton =
                new JButton(
                        "GET RECOMMENDATIONS"
                );

        recommendButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        recommendButton.setForeground(
                Color.WHITE
        );

        recommendButton.setBackground(
                BUTTON_COLOR
        );

        recommendButton.setFocusPainted(false);

        recommendButton.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 25, 12, 25
                )
        );

        recommendButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
        recommendButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                recommendButton.setBackground(
                        new Color(30, 75, 135)
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {

                recommendButton.setBackground(
                        BUTTON_COLOR
                );
            }

            @Override
            public void mousePressed(MouseEvent e) {

                recommendButton.setBackground(
                        new Color(20, 55, 105)
                );
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                recommendButton.setBackground(
                        new Color(30, 75, 135)
                );
            }
        });

        recommendButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        selectionPanel.add(recommendButton);

        // ================= RESULT AREA =================

        resultArea = new JTextArea();

        resultArea.setEditable(false);

        resultArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );

        resultArea.setForeground(TEXT_COLOR);
        resultArea.setBackground(CARD_COLOR);

        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        resultArea.setBorder(
                new EmptyBorder(
                        20, 25, 20, 25
                )
        );

        resultArea.setText(
                "Select a category and click\n"
                        + "\"GET RECOMMENDATIONS\"."
        );

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        // ================= CENTER =================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        centerPanel.add(
                selectionPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ================= BUTTON ACTION =================

        recommendButton.addActionListener(
                e -> showRecommendations()
        );

        add(mainPanel);
    }

    // ================= RECOMMENDATION LOGIC =================

    private void showRecommendations() {

        String selectedCategory =
                (String) categoryComboBox
                        .getSelectedItem();

        RecommendationManager manager =
                new RecommendationManager();

        List<String> books =
                new ArrayList<>();

        books.add(
                "Data Structures|Computing"
        );

        books.add(
                "Java Programming|Computing"
        );

        books.add(
                "Database Systems|Computing"
        );

        books.add(
                "Harry Potter|Fiction"
        );

        books.add(
                "Clean Code|Software Engineering"
        );

        List<String> recommendations =
                manager.recommendBooks(
                        selectedCategory,
                        books
                );

        resultArea.setText("");

        if (recommendations.isEmpty()) {

            resultArea.setText(
                    "No recommended books found."
            );

        } else {

            resultArea.append(
                    "RECOMMENDED BOOKS\n\n"
            );

            resultArea.append(
                    "Category: "
                            + selectedCategory
                            + "\n\n"
            );

            for (String book :
                    recommendations) {

                resultArea.append(
                        "• " + book + "\n\n"
                );
            }
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            RecommendationPanel panel =
                    new RecommendationPanel();

            panel.setVisible(true);
        });
    }
}