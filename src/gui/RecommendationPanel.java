package gui;

import library.manager.BookManager;
import library.model.Book;
import recommendation.RecommendationManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RecommendationPanel extends JFrame {

    // COLORS
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

    private static final Color SECONDARY_COLOR =
            new Color(249, 250, 251);

    private final RecommendationManager
            recommendationManager;


    private JComboBox<String>
            categoryComboBox;

    private JTable
            recommendationTable;

    private DefaultTableModel
            tableModel;

    private JLabel
            resultCountLabel;

    private JLabel
            statusLabel;



    public RecommendationPanel(
            BookManager bookManager
    ) {

        recommendationManager =
                new RecommendationManager(
                        bookManager
                );


        setTitle(
                "Smart Library - Book Recommendations"
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

        loadCategories();
    }


    private void createUI() {

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
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


        JPanel headerText =
                new JPanel();

        headerText.setOpaque(
                false
        );

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel titleLabel =
                new JLabel(
                        "Book Recommendations"
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Discover available books based on your interests"
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


        headerText.add(
                titleLabel
        );

        headerText.add(
                Box.createVerticalStrut(5)
        );

        headerText.add(
                subtitleLabel
        );


        JLabel smartLabel =
                new JLabel(
                        "SMART FEATURE"
                );

        smartLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        smartLabel.setForeground(
                Color.WHITE
        );


        headerPanel.add(
                headerText,
                BorderLayout.WEST
        );

        headerPanel.add(
                smartLabel,
                BorderLayout.EAST
        );


        root.add(
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
                        35,
                        20,
                        35
                )
        );


        JPanel topPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        topPanel.setOpaque(
                false
        );


        JPanel filterCard =
                createCard();

        filterCard.setLayout(
                new BorderLayout(
                        15,
                        10
                )
        );


        JPanel filterText =
                new JPanel();

        filterText.setOpaque(
                false
        );

        filterText.setLayout(
                new BoxLayout(
                        filterText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel filterTitle =
                new JLabel(
                        "Find Recommendations"
                );

        filterTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        filterTitle.setForeground(
                TEXT_COLOR
        );


        JLabel filterSubtitle =
                new JLabel(
                        "Select a category to view matching available books"
                );

        filterSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        filterSubtitle.setForeground(
                SUBTEXT_COLOR
        );


        filterText.add(
                filterTitle
        );

        filterText.add(
                Box.createVerticalStrut(4)
        );

        filterText.add(
                filterSubtitle
        );


        filterCard.add(
                filterText,
                BorderLayout.NORTH
        );


        JPanel controls =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        controls.setOpaque(
                false
        );


        categoryComboBox =
                new JComboBox<>();

        categoryComboBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        categoryComboBox.setPreferredSize(
                new Dimension(
                        350,
                        42
                )
        );


        JButton recommendButton =
                createPrimaryButton(
                        "GET RECOMMENDATIONS"
                );


        controls.add(
                categoryComboBox,
                BorderLayout.CENTER
        );

        controls.add(
                recommendButton,
                BorderLayout.EAST
        );


        filterCard.add(
                controls,
                BorderLayout.SOUTH
        );


        topPanel.add(
                filterCard,
                BorderLayout.CENTER
        );


        JPanel countCard =
                new JPanel(
                        new BorderLayout()
                );

        countCard.setPreferredSize(
                new Dimension(
                        230,
                        120
                )
        );

        countCard.setBackground(
                CARD_COLOR
        );

        countCard.setBorder(
                BorderFactory
                        .createCompoundBorder(
                                BorderFactory
                                        .createLineBorder(
                                                BORDER_COLOR
                                        ),
                                new EmptyBorder(
                                        18,
                                        22,
                                        18,
                                        22
                                )
                        )
        );


        JLabel countTitle =
                new JLabel(
                        "Recommended Books"
                );

        countTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        countTitle.setForeground(
                SUBTEXT_COLOR
        );


        resultCountLabel =
                new JLabel(
                        "0"
                );

        resultCountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        resultCountLabel.setForeground(
                TEXT_COLOR
        );


        countCard.add(
                countTitle,
                BorderLayout.NORTH
        );

        countCard.add(
                resultCountLabel,
                BorderLayout.CENTER
        );


        topPanel.add(
                countCard,
                BorderLayout.EAST
        );


        contentPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        JPanel tableCard =
                createCard();

        tableCard.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );


        JPanel tableHeading =
                new JPanel();

        tableHeading.setOpaque(
                false
        );

        tableHeading.setLayout(
                new BoxLayout(
                        tableHeading,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel tableTitle =
                new JLabel(
                        "Recommended Library Books"
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
                        "Only currently available books are recommended"
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
                "Book ID",
                "Title",
                "Author",
                "Category",
                "Published Year",
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


        recommendationTable =
                new JTable(
                        tableModel
                );


        recommendationTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        recommendationTable.setRowHeight(
                38
        );


        recommendationTable.setSelectionMode(
                ListSelectionModel
                        .SINGLE_SELECTION
        );


        recommendationTable.setFillsViewportHeight(
                true
        );


        recommendationTable.setShowVerticalLines(
                false
        );


        recommendationTable.setShowHorizontalLines(
                true
        );


        recommendationTable.setGridColor(
                BORDER_COLOR
        );


        recommendationTable.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );


        recommendationTable.setSelectionForeground(
                TEXT_COLOR
        );


        recommendationTable.setAutoCreateRowSorter(
                true
        );


        recommendationTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );


        recommendationTable
                .getTableHeader()
                .setBackground(
                        SECONDARY_COLOR
                );


        recommendationTable
                .getTableHeader()
                .setForeground(
                        TEXT_COLOR
                );


        recommendationTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                42
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        recommendationTable
                );


        scrollPane.setBorder(
                BorderFactory
                        .createLineBorder(
                                BORDER_COLOR
                        )
        );


        scrollPane
                .getViewport()
                .setBackground(
                        Color.WHITE
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
                        "Select a category to get recommendations."
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


        root.add(
                contentPanel,
                BorderLayout.CENTER
        );


        setContentPane(
                root
        );


        recommendButton.addActionListener(
                e -> showRecommendations()
        );


        categoryComboBox.addActionListener(
                e -> {

                    if (categoryComboBox
                            .getSelectedItem() != null) {

                        statusLabel.setText(
                                "Selected category: "
                                        + categoryComboBox
                                        .getSelectedItem()
                        );
                    }
                }
        );
    }

    private void loadCategories() {

        categoryComboBox.removeAllItems();


        List<String> categories =
                recommendationManager
                        .getCategories();


        for (String category :
                categories) {

            categoryComboBox.addItem(
                    category
            );
        }


        if (categories.isEmpty()) {

            statusLabel.setText(
                    "No book categories are available."
            );

            resultCountLabel.setText(
                    "0"
            );

        } else {

            statusLabel.setText(
                    "Select a category and click "
                            + "GET RECOMMENDATIONS."
            );
        }
    }


    private void showRecommendations() {

        String category =
                (String)
                        categoryComboBox
                                .getSelectedItem();


        tableModel.setRowCount(
                0
        );


        if (category == null) {

            resultCountLabel.setText(
                    "0"
            );

            statusLabel.setText(
                    "No category selected."
            );

            return;
        }


        List<Book> recommendations =
                recommendationManager
                        .recommendBooks(
                                category
                        );


        for (Book book :
                recommendations) {

            tableModel.addRow(
                    new Object[]{
                            book.getBookId(),
                            book.getTitle(),
                            book.getAuthor(),
                            book.getCategory(),
                            book.getPublishedYear(),
                            "Available"
                    }
            );
        }


        resultCountLabel.setText(
                String.valueOf(
                        recommendations.size()
                )
        );


        if (recommendations.isEmpty()) {

            statusLabel.setText(
                    "No available books found in "
                            + category
                            + "."
            );

        } else {

            statusLabel.setText(
                    recommendations.size()
                            + " recommendation(s) found for "
                            + category
                            + "."
            );
        }
    }

    private JPanel createCard() {

        JPanel card =
                new JPanel();


        card.setBackground(
                CARD_COLOR
        );


        card.setBorder(
                BorderFactory
                        .createCompoundBorder(
                                BorderFactory
                                        .createLineBorder(
                                                BORDER_COLOR
                                        ),
                                new EmptyBorder(
                                        20,
                                        24,
                                        20,
                                        24
                                )
                        )
        );


        return card;
    }


    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        button.setForeground(
                Color.WHITE
        );


        button.setBackground(
                PRIMARY_COLOR
        );

        button.setOpaque(
                true
        );

        button.setContentAreaFilled(
                true
        );

        button.setBorderPainted(
                false
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
                new EmptyBorder(
                        12,
                        22,
                        12,
                        22
                )
        );


        return button;
    }
}