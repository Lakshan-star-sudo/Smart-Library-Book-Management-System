package library.ui;

import library.datastructure.BookBST;
import library.manager.BookManager;
import library.model.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class BSTDashboard extends JFrame {

    private final BookBST tree;
    private final BookManager bookManager;

    private JTextField bookIdField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField categoryField;
    private JTextField yearField;
    private JCheckBox availableCheckBox;

    private JTextField searchField;

    private JTable bookTable;
    private DefaultTableModel tableModel;

    private JLabel totalBooksLabel;
    private JLabel minIdLabel;
    private JLabel maxIdLabel;
    private JLabel heightLabel;
    private JLabel statusLabel;

    public BSTDashboard(BookBST tree) {

        this.tree = tree;
        this.bookManager = new BookManager(tree);

        setTitle("Library Book Management - BST");
        setSize(1150, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();
        refreshBookTable();
    }

    private void createUI() {

        getContentPane().setBackground(
                new Color(245, 247, 250)
        );

        setLayout(new BorderLayout());

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(
                new BorderLayout()
        );

        headerPanel.setBackground(
                new Color(31, 41, 55)
        );

        headerPanel.setBorder(
                new EmptyBorder(20, 30, 20, 30)
        );

        JLabel titleLabel =
                new JLabel("Book Management");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Library books managed using Binary Search Tree"
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

        JPanel titlePanel = new JPanel();

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

        JButton analysisButton =
                createPrimaryButton(
                        "BST Analysis"
                );

        analysisButton.addActionListener(e ->
                new TreeAnalysisDialog(
                        this,
                        tree
                ).setVisible(true)
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                analysisButton,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================
        // MAIN CONTENT
        // =========================

        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        contentPanel.setBackground(
                new Color(245, 247, 250)
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        // =========================
        // BOOK INPUT PANEL
        // =========================

        JPanel inputPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                5,
                                10,
                                8
                        )
                );

        inputPanel.setBackground(Color.WHITE);

        inputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel bookIdLabel =
                new JLabel("Book ID");

        JLabel titleLabelInput =
                new JLabel("Title");

        JLabel authorLabel =
                new JLabel("Author");

        JLabel categoryLabel =
                new JLabel("Category");

        JLabel yearLabel =
                new JLabel("Published Year");

        bookIdField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        categoryField = new JTextField();
        yearField = new JTextField();

        inputPanel.add(bookIdLabel);
        inputPanel.add(titleLabelInput);
        inputPanel.add(authorLabel);
        inputPanel.add(categoryLabel);
        inputPanel.add(yearLabel);

        inputPanel.add(bookIdField);
        inputPanel.add(titleField);
        inputPanel.add(authorField);
        inputPanel.add(categoryField);
        inputPanel.add(yearField);

        contentPanel.add(inputPanel);

        contentPanel.add(
                Box.createVerticalStrut(10)
        );

        // =========================
        // AVAILABILITY
        // =========================

        JPanel availabilityPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        availabilityPanel.setBackground(
                Color.WHITE
        );

        availableCheckBox =
                new JCheckBox(
                        "Book Available"
                );

        availableCheckBox.setSelected(true);

        availabilityPanel.add(
                availableCheckBox
        );

        contentPanel.add(
                availabilityPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(10)
        );

        // =========================
        // CRUD BUTTONS
        // =========================

        JPanel crudPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                5
                        )
                );

        crudPanel.setBackground(
                new Color(245, 247, 250)
        );

        JButton addButton =
                createPrimaryButton(
                        "Add Book"
                );

        JButton updateButton =
                createPrimaryButton(
                        "Update Book"
                );

        JButton deleteButton =
                createPrimaryButton(
                        "Delete Book"
                );

        JButton clearButton =
                createSecondaryButton(
                        "Clear"
                );

        JButton showAllButton =
                createSecondaryButton(
                        "Show All"
                );

        crudPanel.add(addButton);
        crudPanel.add(updateButton);
        crudPanel.add(deleteButton);
        crudPanel.add(clearButton);
        crudPanel.add(showAllButton);

        contentPanel.add(
                crudPanel
        );

        // Button actions

        addButton.addActionListener(
                e -> addBook()
        );

        updateButton.addActionListener(
                e -> updateBook()
        );

        deleteButton.addActionListener(
                e -> deleteBook()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        showAllButton.addActionListener(
                e -> {
                    clearFields();
                    refreshBookTable();
                }
        );

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // =========================
        // SEARCH AREA
        // =========================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(10, 0)
                );

        searchPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        searchPanel.setBackground(
                Color.WHITE
        );

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        JLabel searchLabel =
                new JLabel(
                        "Search Book ID"
                );

        searchLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JButton searchButton =
                createPrimaryButton(
                        "Search"
                );

        JPanel searchButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        searchButtons.setOpaque(false);

        searchButtons.add(
                searchButton
        );

        searchPanel.add(
                searchLabel,
                BorderLayout.WEST
        );

        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                searchButtons,
                BorderLayout.EAST
        );

        searchButton.addActionListener(
                e -> searchBook()
        );

        contentPanel.add(
                searchPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // =========================
        // STATISTICS
        // =========================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        statsPanel.setOpaque(false);

        statsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90
                )
        );

        totalBooksLabel =
                new JLabel("-");

        minIdLabel =
                new JLabel("-");

        maxIdLabel =
                new JLabel("-");

        heightLabel =
                new JLabel("-");

        statsPanel.add(
                createStatCard(
                        "Total Books",
                        totalBooksLabel
                )
        );

        statsPanel.add(
                createStatCard(
                        "Minimum Book ID",
                        minIdLabel
                )
        );

        statsPanel.add(
                createStatCard(
                        "Maximum Book ID",
                        maxIdLabel
                )
        );

        statsPanel.add(
                createStatCard(
                        "Tree Height",
                        heightLabel
                )
        );

        contentPanel.add(
                statsPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(15)
        );

        // =========================
        // BOOK TABLE
        // =========================

        String[] columns = {
                "Book ID",
                "Title",
                "Author",
                "Category",
                "Year",
                "Availability"
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

        bookTable =
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
                new Color(
                        229,
                        231,
                        235
                )
        );

        bookTable.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );

        bookTable.setSelectionForeground(
                new Color(
                        17,
                        24,
                        39
                )
        );

        JTableHeader header =
                bookTable.getTableHeader();

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        header.setBackground(
                new Color(
                        243,
                        244,
                        246
                )
        );

        header.setForeground(
                new Color(
                        55,
                        65,
                        81
                )
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        bookTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                229,
                                231,
                                235
                        )
                )
        );

        scrollPane.getViewport()
                .setBackground(
                        Color.WHITE
                );

        contentPanel.add(
                scrollPane
        );

        // =========================
        // TABLE SELECTION
        // =========================

        bookTable.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        bookTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    bookIdField.setText(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            0
                                                    )
                                                    .toString()
                                    );

                                    titleField.setText(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            1
                                                    )
                                                    .toString()
                                    );

                                    authorField.setText(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            2
                                                    )
                                                    .toString()
                                    );

                                    categoryField.setText(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            3
                                                    )
                                                    .toString()
                                    );

                                    yearField.setText(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            4
                                                    )
                                                    .toString()
                                    );

                                    String availability =
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            5
                                                    )
                                                    .toString();

                                    availableCheckBox
                                            .setSelected(
                                                    availability
                                                            .equals(
                                                                    "Available"
                                                            )
                                            );
                                }
                            }
                        }
                );

        // =========================
        // STATUS
        // =========================

        contentPanel.add(
                Box.createVerticalStrut(10)
        );

        statusLabel =
                new JLabel(
                        "Books are displayed using BST inorder traversal."
                );

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        statusLabel.setForeground(
                new Color(
                        107,
                        114,
                        128
                )
        );

        contentPanel.add(
                statusLabel
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );
    }

    // =========================
    // ADD BOOK
    // =========================

    private void addBook() {

        try {

            int bookId =
                    Integer.parseInt(
                            bookIdField
                                    .getText()
                                    .trim()
                    );

            String title =
                    titleField
                            .getText()
                            .trim();

            String author =
                    authorField
                            .getText()
                            .trim();

            String category =
                    categoryField
                            .getText()
                            .trim();

            int year =
                    Integer.parseInt(
                            yearField
                                    .getText()
                                    .trim()
                    );

            boolean available =
                    availableCheckBox.isSelected();

            Book book =
                    new Book(
                            bookId,
                            title,
                            author,
                            category,
                            year,
                            available
                    );

            boolean success =
                    bookManager.addBook(book);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                refreshBookTable();
                clearFields();

                statusLabel.setText(
                        "Book ID "
                                + bookId
                                + " added successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Book could not be added.\n"
                                + "Check the Book ID and book details.",
                        "Add Book",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book ID and Published Year must be numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // SEARCH BOOK
    // =========================

    private void searchBook() {

        String input =
                searchField
                        .getText()
                        .trim();

        if (input.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a Book ID.",
                    "Search",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int bookId =
                    Integer.parseInt(input);

            Book book =
                    bookManager.searchBook(
                            bookId
                    );

            if (book == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No book was found with Book ID "
                                + bookId
                                + ".",
                        "Book Not Found",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            tableModel.setRowCount(0);

            addBookToTable(book);

            bookIdField.setText(
                    String.valueOf(
                            book.getBookId()
                    )
            );

            titleField.setText(
                    book.getTitle()
            );

            authorField.setText(
                    book.getAuthor()
            );

            categoryField.setText(
                    book.getCategory()
            );

            yearField.setText(
                    String.valueOf(
                            book.getPublishedYear()
                    )
            );

            availableCheckBox.setSelected(
                    book.isAvailable()
            );

            statusLabel.setText(
                    "Book ID "
                            + bookId
                            + " found."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book ID must be a number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // UPDATE BOOK
    // =========================

    private void updateBook() {

        try {

            int bookId =
                    Integer.parseInt(
                            bookIdField
                                    .getText()
                                    .trim()
                    );

            String title =
                    titleField
                            .getText()
                            .trim();

            String author =
                    authorField
                            .getText()
                            .trim();

            String category =
                    categoryField
                            .getText()
                            .trim();

            int year =
                    Integer.parseInt(
                            yearField
                                    .getText()
                                    .trim()
                    );

            boolean success =
                    bookManager.updateBook(
                            bookId,
                            title,
                            author,
                            category,
                            year
                    );

            if (success) {

                Book book =
                        bookManager.searchBook(
                                bookId
                        );

                if (book != null) {

                    book.setAvailable(
                            availableCheckBox
                                    .isSelected()
                    );
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Book updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                refreshBookTable();

                statusLabel.setText(
                        "Book ID "
                                + bookId
                                + " updated successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Book could not be updated.\n"
                                + "Check the Book ID and book details.",
                        "Update Book",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book ID and Published Year must be numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // DELETE BOOK
    // =========================

    private void deleteBook() {

        String input =
                bookIdField
                        .getText()
                        .trim();

        if (input.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a Book ID to delete.",
                    "Delete Book",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int bookId =
                    Integer.parseInt(input);

            Book book =
                    bookManager.searchBook(
                            bookId
                    );

            if (book == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book not found.",
                        "Delete Book",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete Book ID "
                                    + bookId
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            boolean success =
                    bookManager.deleteBook(
                            bookId
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                refreshBookTable();
                clearFields();

                statusLabel.setText(
                        "Book ID "
                                + bookId
                                + " deleted successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Book could not be deleted.",
                        "Delete Book",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book ID must be a number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        bookIdField.setText("");
        titleField.setText("");
        authorField.setText("");
        categoryField.setText("");
        yearField.setText("");

        availableCheckBox.setSelected(true);

        searchField.setText("");

        bookTable.clearSelection();

        refreshBookTable();
    }

    // =========================
    // REFRESH TABLE
    // =========================

    private void refreshBookTable() {

        tableModel.setRowCount(0);

        List<Book> books =
                bookManager.getAllBooks();

        for (Book book : books) {

            addBookToTable(book);
        }

        updateStatistics();

        if (statusLabel != null) {

            statusLabel.setText(
                    "Showing books ordered by Book ID using inorder traversal."
            );
        }
    }

    // =========================
    // ADD BOOK TO TABLE
    // =========================

    private void addBookToTable(
            Book book
    ) {

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

    // =========================
    // BST INFORMATION
    // =========================

    private void updateStatistics() {

        List<Book> books =
                bookManager.getAllBooks();

        totalBooksLabel.setText(
                String.valueOf(
                        books.size()
                )
        );

        Book minBook =
                bookManager.getMinimumBook();

        Book maxBook =
                bookManager.getMaximumBook();

        if (minBook == null) {

            minIdLabel.setText("-");
            maxIdLabel.setText("-");
            heightLabel.setText("-");

            return;
        }

        minIdLabel.setText(
                String.valueOf(
                        minBook.getBookId()
                )
        );

        maxIdLabel.setText(
                String.valueOf(
                        maxBook.getBookId()
                )
        );

        heightLabel.setText(
                String.valueOf(
                        bookManager.getBookTreeHeight()
                )
        );
    }

    // =========================
    // STAT CARD
    // =========================

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
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                14,
                                18,
                                14,
                                18
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        titleLabel.setForeground(
                new Color(
                        107,
                        114,
                        128
                )
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        valueLabel.setForeground(
                new Color(
                        31,
                        41,
                        55
                )
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(5)
        );

        card.add(valueLabel);

        return card;
    }

    // =========================
    // PRIMARY BUTTON
    // =========================

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

        button.setBackground(
                new Color(
                        37,
                        99,
                        235
                )
        );

        button.setForeground(
                Color.WHITE
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
                        9,
                        18,
                        9,
                        18
                )
        );

        return button;
    }

    // =========================
    // SECONDARY BUTTON
    // =========================

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
                        229,
                        231,
                        235
                )
        );

        button.setForeground(
                new Color(
                        31,
                        41,
                        55
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}