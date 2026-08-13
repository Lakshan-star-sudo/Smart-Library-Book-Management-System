package gui;

import library.manager.BorrowingManager;
import library.manager.BorrowingRecord;
import library.model.Book;
import overdue.OverdueManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OverduePanel extends JFrame {

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color HEADER_COLOR =
            new Color(31, 41, 55);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color PRIMARY_COLOR =
            new Color(37, 99, 235);

    private static final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private static final Color SUBTEXT_COLOR =
            new Color(107, 114, 128);

    private static final Color BORDER_COLOR =
            new Color(229, 231, 235);

    private static final Color RED_COLOR =
            new Color(220, 38, 38);


    private final BorrowingManager borrowingManager;
    private final OverdueManager overdueManager;

    private JTable overdueTable;
    private DefaultTableModel tableModel;

    private JLabel overdueCountLabel;
    private JLabel statusLabel;


    public OverduePanel(
            BorrowingManager borrowingManager
    ) {

        this.borrowingManager =
                borrowingManager;

        this.overdueManager =
                new OverdueManager(
                        borrowingManager
                );

        setTitle(
                "Smart Library - Overdue Books"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );

        createUI();

        refreshOverdueTable();
    }


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
                        "Overdue Books"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Monitor borrowed books that have passed their due date"
                );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        209,
                        213,
                        219
                )
        );


        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(4)
        );

        titlePanel.add(
                subtitleLabel
        );


        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        20,
                        30
                )
        );


        JPanel topPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        topPanel.setOpaque(false);


        JPanel countCard =
                createCountCard();

        topPanel.add(
                countCard,
                BorderLayout.WEST
        );


        JButton refreshButton =
                new JButton(
                        "Refresh Overdue Books"
                );

        refreshButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        refreshButton.setForeground(
                Color.WHITE
        );

        refreshButton.setBackground(
                PRIMARY_COLOR
        );

        refreshButton.setFocusPainted(
                false
        );

        refreshButton.setBorderPainted(
                false
        );

        refreshButton.setOpaque(
                true
        );

        refreshButton.setContentAreaFilled(
                true
        );

        refreshButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        refreshButton.setPreferredSize(
                new Dimension(
                        190,
                        42
                )
        );


        JPanel refreshWrapper =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                20
                        )
                );

        refreshWrapper.setOpaque(false);

        refreshWrapper.add(
                refreshButton
        );


        topPanel.add(
                refreshWrapper,
                BorderLayout.EAST
        );


        contentPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        JPanel tableCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        tableCard.setBackground(
                CARD_COLOR
        );

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                22,
                                24,
                                22,
                                24
                        )
                )
        );


        JPanel tableHeading =
                new JPanel();

        tableHeading.setOpaque(false);

        tableHeading.setLayout(
                new BoxLayout(
                        tableHeading,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel tableTitle =
                new JLabel(
                        "Current Overdue Records"
                );

        tableTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        tableTitle.setForeground(
                TEXT_COLOR
        );


        JLabel tableSubtitle =
                new JLabel(
                        "Books are overdue when the due date has passed and the book is not returned"
                );

        tableSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tableSubtitle.setForeground(
                SUBTEXT_COLOR
        );


        tableHeading.add(
                tableTitle
        );

        tableHeading.add(
                Box.createVerticalStrut(4)
        );

        tableHeading.add(
                tableSubtitle
        );


        tableCard.add(
                tableHeading,
                BorderLayout.NORTH
        );


        String[] columns = {
                "Member ID",
                "Book ID",
                "Book Title",
                "Borrow Date",
                "Due Date",
                "Status"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        overdueTable =
                new JTable(
                        tableModel
                );


        overdueTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        overdueTable.setRowHeight(
                36
        );

        overdueTable.setFillsViewportHeight(
                true
        );

        overdueTable.setShowVerticalLines(
                false
        );

        overdueTable.setGridColor(
                BORDER_COLOR
        );

        overdueTable.setAutoCreateRowSorter(
                true
        );


        overdueTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        overdueTable
                .getTableHeader()
                .setBackground(
                        new Color(
                                249,
                                250,
                                251
                        )
                );

        overdueTable
                .getTableHeader()
                .setForeground(
                        TEXT_COLOR
                );

        overdueTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        overdueTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );


        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );


        contentPanel.add(
                tableCard,
                BorderLayout.CENTER
        );


        statusLabel =
                new JLabel(
                        "Ready"
                );

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        statusLabel.setForeground(
                SUBTEXT_COLOR
        );


        contentPanel.add(
                statusLabel,
                BorderLayout.SOUTH
        );


        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        setContentPane(
                mainPanel
        );


        refreshButton.addActionListener(
                e -> refreshOverdueTable()
        );
    }


    private JPanel createCountCard() {

        JPanel card =
                new JPanel();

        card.setPreferredSize(
                new Dimension(
                        260,
                        95
                )
        );

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
                                14,
                                18,
                                14,
                                18
                        )
                )
        );


        JLabel title =
                new JLabel(
                        "Overdue Books"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        title.setForeground(
                SUBTEXT_COLOR
        );


        overdueCountLabel =
                new JLabel(
                        "0"
                );

        overdueCountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        overdueCountLabel.setForeground(
                RED_COLOR
        );


        card.add(
                title
        );

        card.add(
                Box.createVerticalStrut(5)
        );

        card.add(
                overdueCountLabel
        );


        return card;
    }


    private void refreshOverdueTable() {

        tableModel.setRowCount(
                0
        );


        List<BorrowingRecord> overdueRecords =
                overdueManager
                        .findOverdueBooks();


        for (BorrowingRecord record :
                overdueRecords) {

            String bookTitle =
                    "Unknown Book";


            try {

                int bookId =
                        Integer.parseInt(
                                record.getBookId()
                        );


                Book book =
                        borrowingManager
                                .findBook(
                                        bookId
                                );


                if (book != null) {

                    bookTitle =
                            book.getTitle();
                }

            } catch (NumberFormatException ignored) {
            }


            tableModel.addRow(
                    new Object[]{
                            record.getMemberId(),
                            record.getBookId(),
                            bookTitle,
                            record.getBorrowDate(),
                            record.getDueDate(),
                            "OVERDUE"
                    }
            );
        }


        overdueCountLabel.setText(
                String.valueOf(
                        overdueRecords.size()
                )
        );


        if (overdueRecords.isEmpty()) {

            statusLabel.setText(
                    "No overdue books found."
            );

        } else {

            statusLabel.setText(
                    overdueRecords.size()
                            + " overdue book(s) found."
            );
        }
    }
}