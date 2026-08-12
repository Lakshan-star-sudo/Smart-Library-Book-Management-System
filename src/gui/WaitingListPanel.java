package gui;

import waitinglist.WaitingListManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class WaitingListPanel extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(35, 45, 55);
    private final Color SUBTEXT_COLOR = new Color(100, 110, 120);
    private final Color BUTTON_COLOR = new Color(45, 95, 160);

    private JTextArea resultArea;
    private JTextField bookIdField;
    private JTextField memberIdField;

    private WaitingListManager manager;

    public WaitingListPanel() {

        setTitle("Waiting List Management");
        setSize(700, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        manager = new WaitingListManager();

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
                new JLabel("WAITING LIST");

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
                        "Manage members waiting for unavailable books"
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

        // ================= INPUT CARD =================

        JPanel inputPanel =
                new JPanel();

        inputPanel.setLayout(
                new BoxLayout(
                        inputPanel,
                        BoxLayout.Y_AXIS
                )
        );

        inputPanel.setBackground(
                CARD_COLOR
        );

        inputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 235)
                        ),
                        new EmptyBorder(
                                20, 30, 20, 30
                        )
                )
        );

        // Book ID

        JLabel bookLabel =
                new JLabel("Book ID");

        bookLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        bookLabel.setForeground(TEXT_COLOR);

        bookLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        inputPanel.add(bookLabel);

        inputPanel.add(
                Box.createVerticalStrut(6)
        );

        bookIdField =
                new JTextField();

        bookIdField.setMaximumSize(
                new Dimension(300, 35)
        );

        bookIdField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        inputPanel.add(bookIdField);

        inputPanel.add(
                Box.createVerticalStrut(12)
        );

        // Member ID

        JLabel memberLabel =
                new JLabel("Member ID");

        memberLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        memberLabel.setForeground(TEXT_COLOR);

        memberLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        inputPanel.add(memberLabel);

        inputPanel.add(
                Box.createVerticalStrut(6)
        );

        memberIdField =
                new JTextField();

        memberIdField.setMaximumSize(
                new Dimension(300, 35)
        );

        memberIdField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        inputPanel.add(memberIdField);

        inputPanel.add(
                Box.createVerticalStrut(15)
        );

        // Buttons

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(
                CARD_COLOR
        );

        JButton addButton =
                createButton("ADD TO WAITING LIST");

        JButton viewButton =
                createButton("VIEW WAITING LIST");

        JButton removeButton =
                createButton("REMOVE MEMBER");

        buttonPanel.add(addButton);

        buttonPanel.add(viewButton);

        buttonPanel.add(removeButton);

        inputPanel.add(buttonPanel);

        // ================= RESULT AREA =================

        resultArea =
                new JTextArea();

        resultArea.setEditable(false);

        resultArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
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
                "Enter a Book ID and Member ID\n"
                        + "to manage the waiting list."
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
                inputPanel,
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

        // ================= BUTTON ACTIONS =================

        addButton.addActionListener(
                e -> addMember()
        );

        viewButton.addActionListener(
                e -> viewWaitingList()
        );

        removeButton.addActionListener(
                e -> removeMember()
        );

        add(mainPanel);
    }

    // ================= ADD MEMBER =================

    private void addMember() {

        try {

            int bookId =
                    Integer.parseInt(
                            bookIdField.getText().trim()
                    );

            int memberId =
                    Integer.parseInt(
                            memberIdField.getText().trim()
                    );

            manager.addToWaitingList(
                    bookId,
                    memberId
            );

            resultArea.setText(
                    "Member " + memberId
                            + " added to the waiting list "
                            + "for Book " + bookId + "."
            );

        } catch (NumberFormatException e) {

            resultArea.setText(
                    "Please enter valid numeric "
                            + "Book ID and Member ID."
            );
        }
    }

    // ================= VIEW LIST =================

    private void viewWaitingList() {

        try {

            int bookId =
                    Integer.parseInt(
                            bookIdField.getText().trim()
                    );

            List<Integer> members =
                    manager.getWaitingList(bookId);

            resultArea.setText(
                    "WAITING LIST\n\n"
                            + "Book ID: "
                            + bookId
                            + "\n\n"
            );

            if (members.isEmpty()) {

                resultArea.append(
                        "No members are waiting "
                                + "for this book."
                );

            } else {

                resultArea.append(
                        "Members waiting:\n\n"
                );

                for (int i = 0;
                     i < members.size();
                     i++) {

                    resultArea.append(
                            (i + 1)
                                    + ". Member "
                                    + members.get(i)
                                    + "\n"
                    );
                }

                Integer nextMember =
                        manager.getNextMember(bookId);

                resultArea.append(
                        "\nNext Member: "
                                + nextMember
                );
            }

        } catch (NumberFormatException e) {

            resultArea.setText(
                    "Please enter a valid Book ID."
            );
        }
    }

    // ================= REMOVE MEMBER =================

    private void removeMember() {

        try {

            int bookId =
                    Integer.parseInt(
                            bookIdField.getText().trim()
                    );

            int memberId =
                    Integer.parseInt(
                            memberIdField.getText().trim()
                    );

            manager.removeFromWaitingList(
                    bookId,
                    memberId
            );

            resultArea.setText(
                    "Member " + memberId
                            + " was removed from the "
                            + "waiting list for Book "
                            + bookId + "."
            );

        } catch (NumberFormatException e) {

            resultArea.setText(
                    "Please enter valid numeric "
                            + "Book ID and Member ID."
            );
        }
    }

    // ================= BUTTON DESIGN =================

    private JButton createButton(String text) {

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

        button.setBackground(
                BUTTON_COLOR
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 15, 10, 15
                )
        );

        return button;
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            WaitingListPanel panel =
                    new WaitingListPanel();

            panel.setVisible(true);
        });
    }
}