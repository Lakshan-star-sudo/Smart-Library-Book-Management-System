package gui;

import library.app.LibrarySystem;

import library.model.Book;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomerPanel extends JFrame {

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color HEADER_COLOR = new Color(31, 41, 55);
    private final Color TEXT_COLOR = new Color(31, 41, 55);
    private final Color SUBTEXT_COLOR = new Color(107, 114, 128);
    private final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private final Color BORDER_COLOR = new Color(229, 231, 235);

    private final LibrarySystem librarySystem;

    public CustomerPanel(LibrarySystem librarySystem) {

        this.librarySystem = librarySystem;

        setTitle("Smart Library - Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        createUI();
    }

    private void createUI() {

        getContentPane().setBackground(BACKGROUND);
        setLayout(new BorderLayout());


        // HEADER
        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(HEADER_COLOR);

        headerPanel.setBorder(
                new EmptyBorder(
                        22, 35, 22, 35
                )
        );

        JLabel titleLabel =
                new JLabel("Customer Dashboard");

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
                        "Explore and use the Smart Library"
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
                new JLabel("CUSTOMER");

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

        contentPanel.setBackground(BACKGROUND);

        contentPanel.setBorder(
                new EmptyBorder(
                        25, 40, 25, 40
                )
        );


        // SECTION TITLE
        JLabel sectionLabel =
                new JLabel("Library Services");

        sectionLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        sectionLabel.setForeground(TEXT_COLOR);

        contentPanel.add(sectionLabel);

        contentPanel.add(
                Box.createVerticalStrut(15)
        );


        // SERVICE CARDS
        JPanel servicePanel =
                new JPanel(
                        new GridLayout(
                                2, 3, 18, 18
                        )
                );

        servicePanel.setOpaque(false);

        JButton booksButton =
                createServiceCard(
                        "Browse Books",
                        "Search and explore available books"
                );

        JButton borrowButton =
                createServiceCard(
                        "Borrow / Return",
                        "Borrow or return library books"
                );

        JButton recommendationButton =
                createServiceCard(
                        "Recommendations",
                        "Discover recommended books"
                );

        JButton waitingButton =
                createServiceCard(
                        "Waiting List",
                        "Manage books you are waiting for"
                );

        JButton overdueButton =
                createServiceCard(
                        "My Overdue Books",
                        "Check your overdue books"
                );

        JButton accountButton =
                createServiceCard(
                        "My Account",
                        "View your library account"
                );

        servicePanel.add(booksButton);
        servicePanel.add(borrowButton);
        servicePanel.add(recommendationButton);
        servicePanel.add(waitingButton);
        servicePanel.add(overdueButton);
        servicePanel.add(accountButton);

        contentPanel.add(servicePanel);

        contentPanel.add(
                Box.createVerticalStrut(25)
        );


        // CUSTOMER OVERVIEW
        JLabel overviewTitle =
                new JLabel("My Library Overview");

        overviewTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        overviewTitle.setForeground(TEXT_COLOR);

        contentPanel.add(overviewTitle);

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        JPanel overviewPanel =
                new JPanel(
                        new GridLayout(
                                1, 3, 18, 0
                        )
                );

        overviewPanel.setOpaque(false);

        JLabel borrowedBooksLabel =
                new JLabel("0");

        JLabel waitingBooksLabel =
                new JLabel("0");

        JLabel overdueBooksLabel =
                new JLabel("0");

        overviewPanel.add(
                createStatCard(
                        "Borrowed Books",
                        borrowedBooksLabel
                )
        );

        overviewPanel.add(
                createStatCard(
                        "Waiting List",
                        waitingBooksLabel
                )
        );

        overviewPanel.add(
                createStatCard(
                        "Overdue Books",
                        overdueBooksLabel
                )
        );

        contentPanel.add(overviewPanel);

        add(
                contentPanel,
                BorderLayout.CENTER
        );


        // ACTIONS
        booksButton.addActionListener(
                e -> showBookSearch()
        );

        borrowButton.addActionListener(
                e -> showMessage(
                        "Borrow / Return",
                        "Borrow and Return functionality "
                                + "will be connected here."
                )
        );

        recommendationButton.addActionListener(
                e -> showMessage(
                        "Recommendations",
                        "Recommendation module will be connected here."
                )
        );

        waitingButton.addActionListener(
                e -> new WaitingListPanel().setVisible(true)
        );

        overdueButton.addActionListener(
                e -> new OverduePanel().setVisible(true)
        );

        accountButton.addActionListener(
                e -> showMessage(
                        "My Account",
                        "Customer account details "
                                + "will be displayed here."
                )
        );
    }


    private void showBookSearch() {

        JFrame searchFrame =
                new JFrame("Browse Books");

        searchFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        searchFrame.setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);


        // HEADER
        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                HEADER_COLOR
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        20, 30, 20, 30
                )
        );

        JLabel titleLabel =
                new JLabel("Browse Books");

        titleLabel.setForeground(
                Color.WHITE
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Search and explore books in the library"
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

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // CONTENT
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
                        25, 30, 25, 30
                )
        );

        // SEARCH PANEL
        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(10, 0)
                );

        searchPanel.setBackground(
                CARD_COLOR
        );

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                12, 15, 12, 15
                        )
                )
        );

        JLabel searchLabel =
                new JLabel("Search Book");

        searchLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JTextField searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JButton searchButton =
                createPrimaryButton("Search");

        JButton showAllButton =
                createSecondaryButton("Show All");

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);

        searchPanel.add(
                searchLabel,
                BorderLayout.WEST
        );

        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        contentPanel.add(searchPanel);

        contentPanel.add(
                Box.createVerticalStrut(20)
        );


        // BOOK TABLE
        String[] columns = {
                "Book ID",
                "Title",
                "Author",
                "Category",
                "Year",
                "Availability"
        };

        DefaultTableModel tableModel =
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

        JTable bookTable =
                new JTable(tableModel);

        bookTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        bookTable.setRowHeight(34);

        bookTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        bookTable.setShowVerticalLines(false);

        bookTable.setGridColor(
                BORDER_COLOR
        );

        bookTable.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );

        bookTable.setSelectionForeground(
                TEXT_COLOR
        );

        JTableHeader tableHeader =
                bookTable.getTableHeader();

        tableHeader.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        tableHeader.setBackground(
                new Color(
                        243,
                        244,
                        246
                )
        );

        tableHeader.setForeground(
                new Color(
                        55,
                        65,
                        81
                )
        );

        tableHeader.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(bookTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );

        scrollPane.getViewport().setBackground(
                Color.WHITE
        );

        contentPanel.add(scrollPane);

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        // LOAD BOOKS
        Runnable loadAllBooks = () -> {

            tableModel.setRowCount(0);

            List<Book> books =
                    librarySystem
                            .getBookManager()
                            .getAllBooks();

            for (Book book : books) {

                tableModel.addRow(
                        new Object[]{
                                book.getBookId(),
                                book.getTitle(),
                                book.getAuthor(),
                                book.getCategory(),
                                book.getPublishedYear(),
                                book.isAvailable()
                                        ? "Available"
                                        : "Borrowed"
                        }
                );
            }
        };


        // SEARCH
        searchButton.addActionListener(e -> {

            String input =
                    searchField
                            .getText()
                            .trim();

            if (input.isEmpty()) {

                loadAllBooks.run();
                System.out.println(
                        "Customer books: "
                                + librarySystem
                                .getBookManager()
                                .getAllBooks()
                                .size()
                );
                return;
            }

            tableModel.setRowCount(0);

            List<Book> books =
                    librarySystem
                            .getBookManager()
                            .getAllBooks();

            boolean found = false;

            for (Book book : books) {

                boolean idMatch = false;

                try {

                    idMatch =
                            book.getBookId()
                                    == Integer.parseInt(input);

                } catch (NumberFormatException ignored) {
                    // Search by title/category/author below.
                }

                boolean textMatch =
                        book.getTitle()
                                .toLowerCase()
                                .contains(
                                        input.toLowerCase()
                                )
                                ||
                                book.getAuthor()
                                        .toLowerCase()
                                        .contains(
                                                input.toLowerCase()
                                        )
                                ||
                                book.getCategory()
                                        .toLowerCase()
                                        .contains(
                                                input.toLowerCase()
                                        );

                if (idMatch || textMatch) {

                    tableModel.addRow(
                            new Object[]{
                                    book.getBookId(),
                                    book.getTitle(),
                                    book.getAuthor(),
                                    book.getCategory(),
                                    book.getPublishedYear(),
                                    book.isAvailable()
                                            ? "Available"
                                            : "Borrowed"
                            }
                    );

                    found = true;
                }
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        searchFrame,
                        "No matching books were found.",
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // SHOW ALL
        showAllButton.addActionListener(
                e -> {
                    searchField.setText("");
                    loadAllBooks.run();
                }
        );

        // Load current books immediately.
        loadAllBooks.run();

        searchFrame.add(mainPanel);

        searchFrame.setVisible(true);
    }

    // SERVICE CARD
    private JButton createServiceCard(
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

        titleLabel.setForeground(TEXT_COLOR);

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

        card.setBackground(CARD_COLOR);

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

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        valueLabel.setForeground(TEXT_COLOR);

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(7)
        );

        card.add(valueLabel);

        return card;
    }


    // PRIMARY BUTTON
    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                PRIMARY_COLOR
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        9, 18, 9, 18
                )
        );

        return button;
    }


    // SECONDARY BUTTON
    private JButton createSecondaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        button.setBackground(
                new Color(
                        229, 231, 235
                )
        );

        button.setForeground(TEXT_COLOR);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
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