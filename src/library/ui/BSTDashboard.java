package library.ui;

import library.datastructure.BookBST;
import library.model.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class BSTDashboard extends JFrame {

    private final BookBST tree;

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

        setTitle("Library Book Index - BST");
        setSize(1050, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();
        refreshBookTable();
    }

    private void createUI() {

        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(new BorderLayout());

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(31, 41, 55));
        headerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Book Index");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel subtitleLabel = new JLabel(
                "Binary Search Tree based library book indexing"
        );

        subtitleLabel.setForeground(new Color(209, 213, 219));
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        JButton analysisButton = createPrimaryButton("BST Analysis");

        analysisButton.addActionListener(e ->
                new TreeAnalysisDialog(this, tree).setVisible(true)
        );

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(analysisButton, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);


        // =========================
        // MAIN CONTENT
        // =========================

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 247, 250));
        contentPanel.setBorder(new EmptyBorder(25, 30, 25, 30));


        // =========================
        // SEARCH AREA
        // =========================

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 55)
        );

        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(229, 231, 235)
                        ),
                        new EmptyBorder(10, 15, 10, 15)
                )
        );

        JLabel searchLabel = new JLabel("Search Book ID");
        searchLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        searchField = new JTextField();
        searchField.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        JButton searchButton =
                createPrimaryButton("Search");

        JButton clearButton =
                createSecondaryButton("Show All");

        JPanel searchButtons = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 8, 0)
        );

        searchButtons.setOpaque(false);

        searchButtons.add(searchButton);
        searchButtons.add(clearButton);

        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButtons, BorderLayout.EAST);

        searchButton.addActionListener(e -> searchBook());

        clearButton.addActionListener(e -> {
            searchField.setText("");
            refreshBookTable();
            statusLabel.setText(
                    "Showing books ordered by Book ID."
            );
        });


        contentPanel.add(searchPanel);
        contentPanel.add(Box.createVerticalStrut(20));


        // =========================
        // STATISTICS
        // =========================

        JPanel statsPanel = new JPanel(
                new GridLayout(1, 4, 15, 0)
        );

        statsPanel.setOpaque(false);
        statsPanel.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 90)
        );

        totalBooksLabel = new JLabel("-");
        minIdLabel = new JLabel("-");
        maxIdLabel = new JLabel("-");
        heightLabel = new JLabel("-");

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

        contentPanel.add(statsPanel);
        contentPanel.add(Box.createVerticalStrut(20));


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

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        bookTable = new JTable(tableModel);

        bookTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        bookTable.setRowHeight(34);
        bookTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        bookTable.setShowVerticalLines(false);
        bookTable.setGridColor(
                new Color(229, 231, 235)
        );

        bookTable.setSelectionBackground(
                new Color(219, 234, 254)
        );

        bookTable.setSelectionForeground(
                new Color(17, 24, 39)
        );

        JTableHeader header = bookTable.getTableHeader();

        header.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        header.setBackground(
                new Color(243, 244, 246)
        );

        header.setForeground(
                new Color(55, 65, 81)
        );

        header.setPreferredSize(
                new Dimension(0, 38)
        );

        JScrollPane scrollPane =
                new JScrollPane(bookTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(229, 231, 235)
                )
        );

        scrollPane.getViewport().setBackground(
                Color.WHITE
        );

        contentPanel.add(scrollPane);


        // =========================
        // STATUS
        // =========================

        contentPanel.add(
                Box.createVerticalStrut(12)
        );

        statusLabel = new JLabel(
                "Books are displayed using BST inorder traversal."
        );

        statusLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        statusLabel.setForeground(
                new Color(107, 114, 128)
        );

        contentPanel.add(statusLabel);

        add(contentPanel, BorderLayout.CENTER);
    }


    // =========================
    // SEARCH
    // =========================

    private void searchBook() {

        String input = searchField
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
                    tree.search(bookId);

            if (book == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No book was found with Book ID "
                                + bookId + ".",
                        "Book Not Found",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            tableModel.setRowCount(0);
            addBookToTable(book);

            statusLabel.setText(
                    "Book ID " + bookId
                            + " was found using BST search."
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
    // REFRESH / SORTED BOOKS
    // =========================

    private void refreshBookTable() {

        tableModel.setRowCount(0);

        /*
         * Inorder traversal returns books
         * ordered by Book ID.
         */
        List<Book> books =
                tree.inorderTraversal();

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


    private void addBookToTable(Book book) {

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
                tree.inorderTraversal();

        totalBooksLabel.setText(
                String.valueOf(books.size())
        );

        Book minBook =
                tree.findMin();

        Book maxBook =
                tree.findMax();

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
                        tree.getHeight()
                )
        );
    }


    // =========================
    // UI HELPERS
    // =========================

    private JPanel createStatCard(
            String title,
            JLabel valueLabel
    ) {

        JPanel card = new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(Color.WHITE);

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