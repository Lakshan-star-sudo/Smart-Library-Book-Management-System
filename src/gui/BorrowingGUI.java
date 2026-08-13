package gui;

import library.manager.BorrowingManager;
import library.model.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrowingGUI extends JFrame {


    // COLORS
    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color HEADER_COLOR =
            new Color(31, 41, 55);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color PRIMARY_COLOR =
            new Color(37, 99, 235);

    private static final Color PRIMARY_HOVER =
            new Color(29, 78, 216);

    private static final Color GREEN_COLOR =
            new Color(22, 163, 74);

    private static final Color GREEN_HOVER =
            new Color(21, 128, 61);

    private static final Color ORANGE_COLOR =
            new Color(234, 88, 12);

    private static final Color ORANGE_HOVER =
            new Color(194, 65, 12);

    private static final Color PURPLE_COLOR =
            new Color(124, 58, 237);

    private static final Color PURPLE_HOVER =
            new Color(109, 40, 217);

    private static final Color SECONDARY_COLOR =
            new Color(249, 250, 251);

    private static final Color SECONDARY_HOVER =
            new Color(243, 244, 246);

    private static final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private static final Color SUBTEXT_COLOR =
            new Color(107, 114, 128);

    private static final Color BORDER_COLOR =
            new Color(229, 231, 235);



    // SHARED MANAGER
    private final BorrowingManager manager;


    private JTextField memberIdField;
    private JTextField bookIdField;

    private JTextArea outputArea;

    private JLabel statusLabel;

    private JLabel activeBorrowingLabel;
    private JLabel totalBorrowedLabel;
    private JLabel totalReturnedLabel;


    public BorrowingGUI(
            BorrowingManager manager
    ) {

        this.manager = manager;

        setTitle(
                "Smart Library - Borrowing Management"
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setMinimumSize(
                new Dimension(
                        1100,
                        700
                )
        );

        createUI();

        updateStatistics();
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
                        20,
                        30,
                        20,
                        30
                )
        );


        JPanel headerText =
                new JPanel();

        headerText.setOpaque(false);

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Borrowing Management"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                Color.WHITE
        );


        JLabel subtitle =
                new JLabel(
                        "Manage book borrowing, returns and availability"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                new Color(
                        209,
                        213,
                        219
                )
        );


        headerText.add(
                title
        );

        headerText.add(
                Box.createVerticalStrut(4)
        );

        headerText.add(
                subtitle
        );


        headerPanel.add(
                headerText,
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
                        22,
                        30,
                        18,
                        30
                )
        );

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        statsPanel.setOpaque(false);

        statsPanel.setPreferredSize(
                new Dimension(
                        0,
                        100
                )
        );


        // ACTIVE

        activeBorrowingLabel =
                createStatValueLabel();

        statsPanel.add(
                createStatCard(
                        "Active Borrowings",
                        activeBorrowingLabel
                )
        );


        // TOTAL BORROWED

        totalBorrowedLabel =
                createStatValueLabel();

        statsPanel.add(
                createStatCard(
                        "Total Borrowed",
                        totalBorrowedLabel
                )
        );


        // TOTAL RETURNED

        totalReturnedLabel =
                createStatValueLabel();

        statsPanel.add(
                createStatCard(
                        "Total Returned",
                        totalReturnedLabel
                )
        );


        contentPanel.add(
                statsPanel,
                BorderLayout.NORTH
        );


        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        centerPanel.setOpaque(false);


        JPanel transactionCard =
                createCard();

        transactionCard.setPreferredSize(
                new Dimension(
                        470,
                        0
                )
        );

        transactionCard.setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );


        JLabel formTitle =
                new JLabel(
                        "Book Transaction"
                );

        formTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        formTitle.setForeground(
                TEXT_COLOR
        );


        transactionCard.add(
                formTitle,
                BorderLayout.NORTH
        );



        // INPUT FIELDS
        JPanel formPanel =
                new JPanel();

        formPanel.setOpaque(false);

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );


        // MEMBER ID

        JLabel memberLabel =
                createFieldLabel(
                        "Member ID"
                );

        memberLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(
                memberLabel
        );

        formPanel.add(
                Box.createVerticalStrut(7)
        );


        memberIdField =
                createTextField();

        memberIdField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(
                memberIdField
        );


        formPanel.add(
                Box.createVerticalStrut(22)
        );


        // BOOK ID

        JLabel bookLabel =
                createFieldLabel(
                        "Book ID"
                );

        bookLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(
                bookLabel
        );

        formPanel.add(
                Box.createVerticalStrut(7)
        );


        bookIdField =
                createTextField();

        bookIdField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        formPanel.add(
                bookIdField
        );


        JPanel formWrapper =
                new JPanel(
                        new BorderLayout()
                );

        formWrapper.setOpaque(false);

        formWrapper.add(
                formPanel,
                BorderLayout.NORTH
        );


        transactionCard.add(
                formWrapper,
                BorderLayout.CENTER
        );



        // BUTTON PANEL
        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.setPreferredSize(
                new Dimension(
                        0,
                        125
                )
        );


        JButton borrowButton =
                createColorButton(
                        "Borrow Book",
                        PRIMARY_COLOR,
                        PRIMARY_HOVER
                );


        JButton returnButton =
                createColorButton(
                        "Return Book",
                        GREEN_COLOR,
                        GREEN_HOVER
                );


        JButton availabilityButton =
                createColorButton(
                        "Check Availability",
                        ORANGE_COLOR,
                        ORANGE_HOVER
                );


        JButton recordsButton =
                createColorButton(
                        "View Records",
                        PURPLE_COLOR,
                        PURPLE_HOVER
                );


        JButton clearButton =
                createSecondaryButton(
                        "Clear"
                );


        JButton closeButton =
                createSecondaryButton(
                        "Close"
                );


        buttonPanel.add(
                borrowButton
        );

        buttonPanel.add(
                returnButton
        );

        buttonPanel.add(
                availabilityButton
        );

        buttonPanel.add(
                recordsButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );


        transactionCard.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        centerPanel.add(
                transactionCard,
                BorderLayout.WEST
        );



        // ACTIVITY CARD
        JPanel outputCard =
                createCard();

        outputCard.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );


        JPanel outputHeading =
                new JPanel();

        outputHeading.setOpaque(false);

        outputHeading.setLayout(
                new BoxLayout(
                        outputHeading,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel outputTitle =
                new JLabel(
                        "Activity / Results"
                );

        outputTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        outputTitle.setForeground(
                TEXT_COLOR
        );


        JLabel outputSubtitle =
                new JLabel(
                        "Borrowing information and transaction results"
                );

        outputSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        outputSubtitle.setForeground(
                SUBTEXT_COLOR
        );


        outputHeading.add(
                outputTitle
        );

        outputHeading.add(
                Box.createVerticalStrut(4)
        );

        outputHeading.add(
                outputSubtitle
        );


        outputCard.add(
                outputHeading,
                BorderLayout.NORTH
        );


        outputArea =
                new JTextArea();

        outputArea.setEditable(
                false
        );

        outputArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        outputArea.setForeground(
                TEXT_COLOR
        );

        outputArea.setBackground(
                new Color(
                        248,
                        250,
                        252
                )
        );

        outputArea.setLineWrap(
                true
        );

        outputArea.setWrapStyleWord(
                true
        );

        outputArea.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        outputArea
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );


        outputCard.add(
                scrollPane,
                BorderLayout.CENTER
        );


        centerPanel.add(
                outputCard,
                BorderLayout.CENTER
        );


        contentPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );



        // STATUS BAR
        statusLabel =
                new JLabel(
                        "System ready"
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

        borrowButton.addActionListener(
                e -> borrowBook()
        );


        returnButton.addActionListener(
                e -> returnBook()
        );


        availabilityButton.addActionListener(
                e -> checkAvailability()
        );


        recordsButton.addActionListener(
                e -> viewRecords()
        );


        clearButton.addActionListener(
                e -> clearFields()
        );


        closeButton.addActionListener(
                e -> dispose()
        );
    }



    // BORROW BOOK
    private void borrowBook() {

        Integer memberId =
                parseId(
                        memberIdField.getText(),
                        "Member ID"
                );


        if (memberId == null) {
            return;
        }


        Integer bookId =
                parseId(
                        bookIdField.getText(),
                        "Book ID"
                );


        if (bookId == null) {
            return;
        }


        String result =
                manager.borrowBook(
                        memberId,
                        bookId
                );


        outputArea.setText(
                "BORROW BOOK\n"
                        + "========================================\n\n"
                        + "Member ID : "
                        + memberId
                        + "\n"
                        + "Book ID   : "
                        + bookId
                        + "\n\n"
                        + result
        );


        statusLabel.setText(
                result
        );


        updateStatistics();
    }



    // RETURN BOOK
    private void returnBook() {

        Integer memberId =
                parseId(
                        memberIdField.getText(),
                        "Member ID"
                );


        if (memberId == null) {
            return;
        }


        Integer bookId =
                parseId(
                        bookIdField.getText(),
                        "Book ID"
                );


        if (bookId == null) {
            return;
        }


        String result =
                manager.returnBook(
                        memberId,
                        bookId
                );


        outputArea.setText(
                "RETURN BOOK\n"
                        + "========================================\n\n"
                        + "Member ID : "
                        + memberId
                        + "\n"
                        + "Book ID   : "
                        + bookId
                        + "\n\n"
                        + result
        );


        statusLabel.setText(
                result
        );


        updateStatistics();
    }



    // CHECK AVAILABILITY
    private void checkAvailability() {

        Integer bookId =
                parseId(
                        bookIdField.getText(),
                        "Book ID"
                );


        if (bookId == null) {
            return;
        }


        Book book =
                manager.findBook(
                        bookId
                );


        if (book == null) {

            outputArea.setText(
                    "BOOK AVAILABILITY\n"
                            + "========================================\n\n"
                            + "Book ID : "
                            + bookId
                            + "\n\n"
                            + "Book not found."
            );


            statusLabel.setText(
                    "Book not found."
            );

            return;
        }


        String availability =
                book.isAvailable()
                        ? "AVAILABLE"
                        : "BORROWED";


        outputArea.setText(
                "BOOK AVAILABILITY\n"
                        + "========================================\n\n"
                        + "Book ID        : "
                        + book.getBookId()
                        + "\n"
                        + "Title          : "
                        + book.getTitle()
                        + "\n"
                        + "Author         : "
                        + book.getAuthor()
                        + "\n"
                        + "Category       : "
                        + book.getCategory()
                        + "\n"
                        + "Published Year : "
                        + book.getPublishedYear()
                        + "\n\n"
                        + "Current Status : "
                        + availability
        );


        statusLabel.setText(
                "Availability checked."
        );
    }



    // VIEW RECORDS
    private void viewRecords() {
        outputArea.setText(
                "BORROWING RECORDS\n"
                        + "========================================\n\n"
                        + manager.getAllRecords()
        );


        statusLabel.setText(
                "Showing borrowing records."
        );


        updateStatistics();
    }



    // UPDATE STATISTICS
    private void updateStatistics() {

        activeBorrowingLabel.setText(
                String.valueOf(
                        manager.getActiveBorrowingCount()
                )
        );


        totalBorrowedLabel.setText(
                String.valueOf(
                        manager.getTotalBorrowingCount()
                )
        );


        totalReturnedLabel.setText(
                String.valueOf(
                        manager.getReturnedBorrowingCount()
                )
        );
    }


    private void clearFields() {

        memberIdField.setText("");

        bookIdField.setText("");

        outputArea.setText("");

        statusLabel.setText(
                "System ready"
        );


        memberIdField.requestFocusInWindow();
    }



    // VALIDATE ID
    private Integer parseId(
            String text,
            String fieldName
    ) {

        text = text.trim();


        if (text.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter " + fieldName + ".",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }


        try {

            int value =
                    Integer.parseInt(
                            text
                    );


            if (value <= 0) {
                throw new NumberFormatException();
            }


            return value;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    fieldName
                            + " must be a positive number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );


            return null;
        }
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
                                16,
                                20,
                                16,
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
                Box.createVerticalStrut(7)
        );

        card.add(
                valueLabel
        );


        return card;
    }



    // STAT VALUE
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

    private JPanel createCard() {

        JPanel card =
                new JPanel();

        card.setBackground(
                CARD_COLOR
        );


        card.setBorder(
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


        return card;
    }


    private JLabel createFieldLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXT_COLOR
        );


        return label;
    }


    private JTextField createTextField() {

        JTextField field =
                new JTextField();


        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        field.setPreferredSize(
                new Dimension(
                        380,
                        42
                )
        );


        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                7,
                                10,
                                7,
                                10
                        )
                )
        );


        return field;
    }


    private JButton createColorButton(
            String text,
            Color normalColor,
            Color hoverColor
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
                normalColor
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setContentAreaFilled(
                true
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                hoverColor
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                normalColor
                        );
                    }
                }
        );


        return button;
    }


    private JButton createSecondaryButton(
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
                TEXT_COLOR
        );

        button.setBackground(
                SECONDARY_COLOR
        );

        button.setFocusPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setContentAreaFilled(
                true
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                SECONDARY_HOVER
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                SECONDARY_COLOR
                        );
                    }
                }
        );


        return button;
    }
}