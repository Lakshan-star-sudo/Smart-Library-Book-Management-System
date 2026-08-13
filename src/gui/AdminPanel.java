package gui;

import library.app.LibrarySystem;
import library.ui.BSTDashboard;
import library.ui.MemberDashboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminPanel extends JFrame {

    // COLORS
    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color HEADER_COLOR =
            new Color(31, 41, 55);

    private static final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private static final Color SUBTEXT_COLOR =
            new Color(107, 114, 128);

    private static final Color PRIMARY_COLOR =
            new Color(37, 99, 235);

    private static final Color BORDER_COLOR =
            new Color(229, 231, 235);



    // SHARED SYSTEM
    private final LibrarySystem librarySystem;



    // STAT LABELS
    private JLabel totalBooksLabel;
    private JLabel totalMembersLabel;
    private JLabel activeBorrowingsLabel;
    private JLabel overdueLabel;


    public AdminPanel(
            LibrarySystem librarySystem
    ) {

        this.librarySystem = librarySystem;

        setTitle(
                "Smart Library - Admin"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createUI();

        refreshStatistics();


        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowActivated(
                            WindowEvent e
                    ) {

                        refreshStatistics();
                    }
                }
        );
    }



    // CREATE UI
    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );


        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                HEADER_COLOR
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        22,
                        35,
                        22,
                        35
                )
        );


        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel titleLabel =
                new JLabel(
                        "Admin Dashboard"
                );

        titleLabel.setForeground(
                Color.WHITE
        );

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
                new Color(
                        209,
                        213,
                        219
                )
        );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(
                subtitleLabel
        );


        JLabel roleLabel =
                new JLabel(
                        "ADMIN"
                );

        roleLabel.setForeground(
                Color.WHITE
        );

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


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


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
                        25,
                        40,
                        25,
                        40
                )
        );



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

        sectionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        contentPanel.add(
                sectionLabel
        );

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        JPanel managementPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                18,
                                18
                        )
                );

        managementPanel.setOpaque(false);

        managementPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        managementPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        330
                )
        );


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
                        "Borrow, return and view borrowing records"
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


        managementPanel.add(
                booksButton
        );

        managementPanel.add(
                membersButton
        );

        managementPanel.add(
                borrowingButton
        );

        managementPanel.add(
                overdueButton
        );

        managementPanel.add(
                waitingButton
        );

        managementPanel.add(
                reportsButton
        );


        contentPanel.add(
                managementPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(25)
        );


        JLabel statisticsTitle =
                new JLabel(
                        "System Overview"
                );

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

        statisticsTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        contentPanel.add(
                statisticsTitle
        );

        contentPanel.add(
                Box.createVerticalStrut(15)
        );


        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                18,
                                0
                        )
                );

        statisticsPanel.setOpaque(false);

        statisticsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        statisticsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        110
                )
        );


        totalBooksLabel =
                createStatValueLabel();


        totalMembersLabel =
                createStatValueLabel();


        activeBorrowingsLabel =
                createStatValueLabel();


        overdueLabel =
                createStatValueLabel();


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


        contentPanel.add(
                Box.createVerticalGlue()
        );


        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        setContentPane(
                mainPanel
        );



        // BUTTON ACTIONS
        booksButton.addActionListener(
                e -> {

                    BSTDashboard dashboard =
                            new BSTDashboard(
                                    librarySystem
                                            .getBookManager()
                            );

                    dashboard.setVisible(
                            true
                    );
                }
        );


        membersButton.addActionListener(
                e -> {

                    MemberDashboard dashboard =
                            new MemberDashboard(
                                    librarySystem
                                            .getMemberManager()
                            );

                    dashboard.setVisible(
                            true
                    );
                }
        );


        borrowingButton.addActionListener(
                e -> {

                    BorrowingGUI borrowingGUI =
                            new BorrowingGUI(
                                    librarySystem
                                            .getBorrowingManager()
                            );

                    borrowingGUI.setVisible(
                            true
                    );
                }
        );


        overdueButton.addActionListener(
                e -> {

                    OverduePanel overduePanel =
                            new OverduePanel(
                                    librarySystem.getBorrowingManager()
                            );

                    overduePanel.setVisible(
                            true
                    );
                }
        );


        waitingButton.addActionListener(
                e -> {

                    WaitingListPanel waitingListPanel =
                            new WaitingListPanel();

                    waitingListPanel.setVisible(
                            true
                    );
                }
        );


        reportsButton.addActionListener(
                e -> showReports()
        );
    }



    // REFRESH STATISTICS
    private void refreshStatistics() {

        if (totalBooksLabel != null) {

            totalBooksLabel.setText(
                    String.valueOf(
                            librarySystem
                                    .getBookManager()
                                    .getAllBooks()
                                    .size()
                    )
            );
        }


        if (totalMembersLabel != null) {

            totalMembersLabel.setText(
                    String.valueOf(
                            librarySystem
                                    .getMemberManager()
                                    .getMemberCount()
                    )
            );
        }


        if (activeBorrowingsLabel != null) {

            activeBorrowingsLabel.setText(
                    String.valueOf(
                            librarySystem
                                    .getBorrowingManager()
                                    .getActiveBorrowingCount()
                    )
            );
        }


        if (overdueLabel != null) {

            overdue.OverdueManager overdueManager =
                    new overdue.OverdueManager(
                            librarySystem.getBorrowingManager()
                    );

            overdueLabel.setText(
                    String.valueOf(
                            overdueManager.getOverdueCount()
                    )
            );
        }
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

        button.setFocusPainted(
                false
        );

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
                                22,
                                22,
                                22,
                                22
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

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


        button.add(
                Box.createVerticalGlue()
        );

        button.add(
                titleLabel
        );

        button.add(
                Box.createVerticalStrut(8)
        );

        button.add(
                descriptionLabel
        );

        button.add(
                Box.createVerticalGlue()
        );


        return button;
    }



    // STAT CARD
    private JPanel createStatCard(
            String title,
            JLabel valueLabel
    ) {

        JPanel card =
                new JPanel();

        card.setBackground(
                CARD_COLOR
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

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


        card.add(
                titleLabel
        );

        card.add(
                Box.createVerticalStrut(6)
        );

        card.add(
                valueLabel
        );


        return card;
    }


    private JLabel createStatValueLabel() {

        JLabel label =
                new JLabel(
                        "0"
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        label.setForeground(
                TEXT_COLOR
        );


        return label;
    }


    // REPORTS
    private void showReports() {

        int totalBooks =
                librarySystem
                        .getBookManager()
                        .getAllBooks()
                        .size();


        int totalMembers =
                librarySystem
                        .getMemberManager()
                        .getMemberCount();


        int activeBorrowings =
                librarySystem
                        .getBorrowingManager()
                        .getActiveBorrowingCount();


        int totalBorrowings =
                librarySystem
                        .getBorrowingManager()
                        .getTotalBorrowingCount();


        int returnedBooks =
                librarySystem
                        .getBorrowingManager()
                        .getReturnedBorrowingCount();


        String report =
                "SMART LIBRARY REPORT\n\n"
                        + "Total Books: "
                        + totalBooks
                        + "\n"
                        + "Total Members: "
                        + totalMembers
                        + "\n"
                        + "Active Borrowings: "
                        + activeBorrowings
                        + "\n"
                        + "Total Borrowing Transactions: "
                        + totalBorrowings
                        + "\n"
                        + "Returned Transactions: "
                        + returnedBooks;


        JOptionPane.showMessageDialog(
                this,
                report,
                "Library Reports",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}