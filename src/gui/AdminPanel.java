package gui;

import library.app.LibrarySystem;
import library.ui.BSTDashboard;
import library.manager.BorrowingManager;
import library.manager.BookManager;
import library.manager.MemberManager;
import library.ui.MemberDashboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



public class AdminPanel extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color HEADER_COLOR = new Color(31, 41, 55);
    private final Color TEXT_COLOR = new Color(31, 41, 55);
    private final Color SUBTEXT_COLOR = new Color(107, 114, 128);
    private final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private final Color BORDER_COLOR = new Color(229, 231, 235);

    private final LibrarySystem librarySystem;

    private JLabel totalBooksLabel;
    private JLabel totalMembersLabel;
    private JLabel activeBorrowingsLabel;
    private JLabel overdueLabel;

    public AdminPanel(LibrarySystem librarySystem) {

        this.librarySystem = librarySystem;

        setTitle("Smart Library - Admin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Full screen / maximized window
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        createUI();
    }

    private void createUI() {

        getContentPane().setBackground(BACKGROUND);
        setLayout(new BorderLayout());

        // HEADER
        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                HEADER_COLOR
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        22, 35, 22, 35
                )
        );

        JLabel titleLabel =
                new JLabel("Admin Dashboard");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Manage and monitor the Smart Library"
                );

        subtitleLabel.setForeground(
                new Color(209, 213, 219)
        );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(false);

        titlePanel.add(titleLabel);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitleLabel);

        JLabel roleLabel =
                new JLabel("ADMIN");

        roleLabel.setForeground(Color.WHITE);

        roleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                roleLabel,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );


        // MAIN CONTENT
        JPanel contentPanel =
                new JPanel();

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        contentPanel.setBackground(
                BACKGROUND
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        25, 40, 25, 40
                )
        );


        // MANAGEMENT TITLE
        JLabel sectionLabel =
                new JLabel(
                        "Library Management"
                );

        sectionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        sectionLabel.setForeground(
                TEXT_COLOR
        );

        contentPanel.add(sectionLabel);

        contentPanel.add(
                Box.createVerticalStrut(15)
        );


        // MANAGEMENT CARDS
        JPanel managementPanel =
                new JPanel(
                        new GridLayout(
                                2, 3, 18, 18
                        )
                );

        managementPanel.setOpaque(false);

        JButton booksButton =
                createManagementCard(
                        "Manage Books",
                        "Add, update, delete and search books"
                );

        JButton membersButton =
                createManagementCard(
                        "Manage Members",
                        "Manage library member information"
                );

        JButton borrowingButton =
                createManagementCard(
                        "Borrowing Records",
                        "View and manage borrowing records"
                );

        JButton overdueButton =
                createManagementCard(
                        "Overdue Books",
                        "Monitor overdue library books"
                );

        JButton waitingButton =
                createManagementCard(
                        "Waiting List",
                        "Manage waiting list requests"
                );

        JButton reportsButton =
                createManagementCard(
                        "Library Reports",
                        "View important library information"
                );

        managementPanel.add(booksButton);
        managementPanel.add(membersButton);
        managementPanel.add(borrowingButton);
        managementPanel.add(overdueButton);
        managementPanel.add(waitingButton);
        managementPanel.add(reportsButton);

        contentPanel.add(managementPanel);

        contentPanel.add(
                Box.createVerticalStrut(25)
        );


        // STATISTICS TITLE
        JLabel statisticsTitle =
                new JLabel("System Overview");

        statisticsTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        statisticsTitle.setForeground(
                TEXT_COLOR
        );

        contentPanel.add(
                statisticsTitle
        );

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // STATISTICS
        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                1, 4, 18, 0
                        )
                );

        statisticsPanel.setOpaque(false);

        totalBooksLabel =
                new JLabel(
                        String.valueOf(
                                librarySystem
                                        .getBookBST()
                                        .inorderTraversal()
                                        .size()
                        )
                );

        totalMembersLabel =
                new JLabel(
                        String.valueOf(
                                librarySystem
                                        .getMemberManager()
                                        .getMemberCount()
                        )
                );

        activeBorrowingsLabel =
                new JLabel("0");

        overdueLabel =
                new JLabel("0");

        statisticsPanel.add(
                createStatCard(
                        "Total Books",
                        totalBooksLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "Total Members",
                        totalMembersLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "Active Borrowings",
                        activeBorrowingsLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "Overdue Books",
                        overdueLabel
                )
        );

        contentPanel.add(
                statisticsPanel
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );


        // BUTTON ACTIONS
        booksButton.addActionListener(e ->
                new BSTDashboard(
                        librarySystem.getBookManager()
                ).setVisible(true)
        );

        membersButton.addActionListener(e ->
                new MemberDashboard(
                        librarySystem.getMemberManager()
                ).setVisible(true)
        );

        borrowingButton.addActionListener(e -> {

            BorrowingGUI borrowingGUI =
                    new BorrowingGUI(
                            librarySystem.getBookManager(),
                            librarySystem.getMemberManager()
                    );

            borrowingGUI.setVisible(true);
        });

        waitingButton.addActionListener(e ->
                new WaitingListPanel().setVisible(true)
        );

        reportsButton.addActionListener(e ->
                showMessage(
                        "Library Reports",
                        "Library reports will be connected later."
                )
        );
    }


    // MANAGEMENT CARD
    private JButton createManagementCard(
            String title,
            String description
    ) {

        JButton button =
                new JButton();

        button.setLayout(
                new BoxLayout(
                        button,
                        BoxLayout.Y_AXIS
                )
        );

        button.setBackground(
                CARD_COLOR
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                22, 22, 22, 22
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        titleLabel.setForeground(
                TEXT_COLOR
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html><center>"
                                + description
                                + "</center></html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "Segoe UI",
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


    // STAT CARD
    private JPanel createStatCard(
            String title,
            JLabel valueLabel
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                CARD_COLOR
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                18, 20, 18, 20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        titleLabel.setForeground(
                SUBTEXT_COLOR
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        valueLabel.setForeground(
                TEXT_COLOR
        );

        valueLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(7)
        );

        card.add(valueLabel);

        return card;
    }


    // MESSAGE
    private void showMessage(
            String title,
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}