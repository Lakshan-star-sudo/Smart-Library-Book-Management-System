package gui;

import overdue.OverdueManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OverduePanel extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(35, 45, 55);
    private final Color SUBTEXT_COLOR = new Color(100, 110, 120);
    private final Color BUTTON_COLOR = new Color(45, 95, 160);

    private JTextArea resultArea;

    public OverduePanel() {

        setTitle("Overdue Book Management");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(30, 40, 30, 40)
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
                new JLabel("OVERDUE BOOKS");

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
                new JLabel(
                        "Track books that have passed their due date"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
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

        // ================= BUTTON =================

        JButton checkButton =
                new JButton("CHECK OVERDUE BOOKS");

        checkButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        checkButton.setForeground(Color.WHITE);

        checkButton.setBackground(
                BUTTON_COLOR
        );

        checkButton.setFocusPainted(false);

        checkButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        checkButton.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 25, 12, 25
                )
        );

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

        resultArea.setBackground(
                CARD_COLOR
        );

        resultArea.setLineWrap(true);

        resultArea.setWrapStyleWord(true);

        resultArea.setBorder(
                new EmptyBorder(
                        20, 25, 20, 25
                )
        );

        resultArea.setText(
                "Click \"CHECK OVERDUE BOOKS\" "
                        + "to view overdue books."
        );

        JScrollPane scrollPane =
                new JScrollPane(resultArea);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        // ================= CENTER PANEL =================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(
                BACKGROUND
        );

        buttonPanel.add(checkButton);

        centerPanel.add(
                buttonPanel,
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

        checkButton.addActionListener(
                e -> showOverdueBooks()
        );

        add(mainPanel);
    }

    // ================= OVERDUE LOGIC =================

    private void showOverdueBooks() {

        OverdueManager manager =
                new OverdueManager();

        List<String> borrowedBooks =
                new ArrayList<>();

        borrowedBooks.add(
                "Data Structures|Kamal|2026-08-05"
        );

        borrowedBooks.add(
                "Java Programming|Nimal|2026-08-20"
        );

        borrowedBooks.add(
                "Database Systems|Saman|2026-08-01"
        );

        borrowedBooks.add(
                "Software Engineering|Amal|2026-08-25"
        );

        List<String> overdueBooks =
                manager.findOverdueBooks(
                        borrowedBooks
                );

        resultArea.setText("");

        if (overdueBooks.isEmpty()) {

            resultArea.setText(
                    "NO OVERDUE BOOKS\n\n"
                            + "All borrowed books "
                            + "are within their due dates."
            );

        } else {

            resultArea.append(
                    "OVERDUE BOOKS\n\n"
            );

            resultArea.append(
                    "The following books "
                            + "have passed their due date:\n\n"
            );

            for (String book :
                    overdueBooks) {

                resultArea.append(
                        "• " + book + "\n\n"
                );
            }
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            OverduePanel panel =
                    new OverduePanel();

            panel.setVisible(true);
        });
    }
}