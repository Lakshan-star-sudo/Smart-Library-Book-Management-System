package gui;

import library.manager.BookManager;
import library.manager.BorrowingManager;
import library.manager.MemberManager;
import library.model.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BorrowingGUI extends JFrame {

    // ================= COLORS =================

    private static final Color PAGE_BG =
            new Color(244, 247, 252);

    private static final Color SIDEBAR =
            new Color(31, 41, 55);

    private static final Color PRIMARY =
            new Color(37, 99, 235);

    private static final Color GREEN =
            new Color(22, 163, 74);

    private static final Color ORANGE =
            new Color(234, 88, 12);

    private static final Color PURPLE =
            new Color(124, 58, 237);

    private static final Color CARD =
            Color.WHITE;

    private static final Color TEXT_DARK =
            new Color(31, 41, 55);

    private static final Color TEXT_GRAY =
            new Color(107, 114, 128);

    private static final Color BORDER =
            new Color(229, 231, 235);


    // ================= COMPONENTS =================

    private JTextField memberIdField;
    private JTextField bookIdField;

    private JTextArea outputArea;
    private JLabel statusLabel;

    private final BorrowingManager manager;


    // ================= CONSTRUCTOR =================

    public BorrowingGUI(
            BookManager bookManager,
            MemberManager memberManager
    ) {

        manager = new BorrowingManager(
                bookManager,
                memberManager
        );

        setTitle("Smart Library - Borrowing Management");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createGUI();
    }


    // =====================================================
    // MAIN GUI
    // =====================================================

    private void createGUI() {

        JPanel rootPanel =
                new JPanel(new BorderLayout());

        rootPanel.setBackground(PAGE_BG);


        JPanel sidebar =
                createSidebar();

        rootPanel.add(
                sidebar,
                BorderLayout.WEST
        );


        JPanel contentPanel =
                createMainContent();

        rootPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        add(rootPanel);
    }


    // =====================================================
    // SIDEBAR
    // =====================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(220, 0)
        );

        sidebar.setBackground(SIDEBAR);

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(
                        30, 20, 25, 20
                )
        );


        JLabel logo =
                new JLabel("SMART LIBRARY");

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        logo.setForeground(Color.WHITE);

        logo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel smallTitle =
                new JLabel("Borrowing Management");

        smallTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        smallTitle.setForeground(
                new Color(209, 213, 219)
        );

        smallTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        sidebar.add(logo);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(smallTitle);

        sidebar.add(
                Box.createVerticalStrut(50)
        );


        JLabel menuLabel =
                new JLabel("BORROWING");

        menuLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        menuLabel.setForeground(
                new Color(156, 163, 175)
        );

        menuLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(menuLabel);

        sidebar.add(
                Box.createVerticalStrut(12)
        );


        JButton borrowMenu =
                createSidebarButton("Borrow Book");

        JButton returnMenu =
                createSidebarButton("Return Book");

        JButton availabilityMenu =
                createSidebarButton(
                        "Check Availability"
                );

        JButton recordsMenu =
                createSidebarButton(
                        "Borrowing Records"
                );


        sidebar.add(borrowMenu);

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(returnMenu);

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(availabilityMenu);

        sidebar.add(
                Box.createVerticalStrut(8)
        );

        sidebar.add(recordsMenu);


        sidebar.add(
                Box.createVerticalGlue()
        );


        JButton exitButton =
                createSidebarButton("Close");

        exitButton.setForeground(
                new Color(248, 113, 113)
        );

        sidebar.add(exitButton);


        borrowMenu.addActionListener(
                e -> {

                    memberIdField.requestFocus();

                    statusLabel.setText(
                            "Enter Member ID and Book ID to borrow a book."
                    );
                }
        );


        returnMenu.addActionListener(
                e -> {

                    memberIdField.requestFocus();

                    statusLabel.setText(
                            "Enter Member ID and Book ID to return a book."
                    );
                }
        );


        availabilityMenu.addActionListener(
                e -> {

                    bookIdField.requestFocus();

                    statusLabel.setText(
                            "Enter a Book ID to check availability."
                    );
                }
        );


        recordsMenu.addActionListener(
                e -> viewRecords()
        );


        exitButton.addActionListener(
                e -> dispose()
        );


        return sidebar;
    }


    // =====================================================
    // MAIN CONTENT
    // =====================================================

    private JPanel createMainContent() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        mainPanel.setBackground(PAGE_BG);

        mainPanel.setBorder(
                new EmptyBorder(
                        30,
                        35,
                        25,
                        35
                )
        );


        JPanel topSection =
                createTopSection();

        mainPanel.add(
                topSection,
                BorderLayout.NORTH
        );


        JPanel centerPanel =
                new JPanel();

        centerPanel.setOpaque(false);

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JPanel cards =
                createFeatureCards();

        cards.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        120
                )
        );

        centerPanel.add(cards);

        centerPanel.add(
                Box.createVerticalStrut(20)
        );


        JPanel formCard =
                createBorrowingForm();

        formCard.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        230
                )
        );

        centerPanel.add(formCard);

        centerPanel.add(
                Box.createVerticalStrut(20)
        );


        JPanel outputCard =
                createOutputCard();

        centerPanel.add(outputCard);


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        JPanel statusBar =
                createStatusBar();

        mainPanel.add(
                statusBar,
                BorderLayout.SOUTH
        );


        return mainPanel;
    }


    // =====================================================
    // TOP HEADER
    // =====================================================

    private JPanel createTopSection() {

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setOpaque(false);


        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
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
                        30
                )
        );

        title.setForeground(TEXT_DARK);


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

        subtitle.setForeground(TEXT_GRAY);


        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);


        topPanel.add(
                titlePanel,
                BorderLayout.WEST
        );


        return topPanel;
    }


    // =====================================================
    // FEATURE CARDS
    // =====================================================

    private JPanel createFeatureCards() {

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        cards.setOpaque(false);


        cards.add(
                createFeatureCard(
                        "Borrow Book",
                        "Issue an available book",
                        PRIMARY
                )
        );


        cards.add(
                createFeatureCard(
                        "Return Book",
                        "Complete a borrowing record",
                        GREEN
                )
        );


        cards.add(
                createFeatureCard(
                        "Availability",
                        "Check current book status",
                        ORANGE
                )
        );


        return cards;
    }


    private JPanel createFeatureCard(
            String title,
            String description,
            Color color
    ) {

        RoundedPanel panel =
                new RoundedPanel(
                        18,
                        CARD
                );

        panel.setLayout(
                new BorderLayout(
                        15,
                        0
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );


        JPanel indicator =
                new JPanel();

        indicator.setPreferredSize(
                new Dimension(
                        6,
                        0
                )
        );

        indicator.setBackground(color);


        JPanel textPanel =
                new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setForeground(TEXT_DARK);


        JLabel descLabel =
                new JLabel(description);

        descLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        descLabel.setForeground(TEXT_GRAY);


        textPanel.add(titleLabel);

        textPanel.add(
                Box.createVerticalStrut(7)
        );

        textPanel.add(descLabel);


        panel.add(
                indicator,
                BorderLayout.WEST
        );

        panel.add(
                textPanel,
                BorderLayout.CENTER
        );


        return panel;
    }


    // =====================================================
    // BORROWING FORM CARD
    // =====================================================

    private JPanel createBorrowingForm() {

        RoundedPanel formCard =
                new RoundedPanel(
                        18,
                        CARD
                );

        formCard.setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );

        formCard.setBorder(
                new EmptyBorder(
                        22,
                        25,
                        22,
                        25
                )
        );


        JPanel heading =
                new JPanel();

        heading.setOpaque(false);

        heading.setLayout(
                new BoxLayout(
                        heading,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel headingTitle =
                new JLabel(
                        "Book Transaction"
                );

        headingTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        headingTitle.setForeground(TEXT_DARK);


        JLabel headingDesc =
                new JLabel(
                        "Enter the member and book information below"
                );

        headingDesc.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        headingDesc.setForeground(TEXT_GRAY);


        heading.add(headingTitle);

        heading.add(
                Box.createVerticalStrut(4)
        );

        heading.add(headingDesc);


        formCard.add(
                heading,
                BorderLayout.NORTH
        );


        JPanel inputs =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        inputs.setOpaque(false);


        JPanel memberPanel =
                createInputPanel(
                        "Member ID"
                );

        memberIdField =
                createTextField();

        memberPanel.add(
                memberIdField,
                BorderLayout.CENTER
        );


        JPanel bookPanel =
                createInputPanel(
                        "Book ID"
                );

        bookIdField =
                createTextField();

        bookPanel.add(
                bookIdField,
                BorderLayout.CENTER
        );


        inputs.add(memberPanel);
        inputs.add(bookPanel);


        formCard.add(
                inputs,
                BorderLayout.CENTER
        );


        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        buttons.setOpaque(false);


        RoundedButton borrowButton =
                createActionButton(
                        "Borrow Book",
                        PRIMARY
                );

        RoundedButton returnButton =
                createActionButton(
                        "Return Book",
                        GREEN
                );

        RoundedButton checkButton =
                createActionButton(
                        "Check Availability",
                        ORANGE
                );

        RoundedButton recordsButton =
                createActionButton(
                        "View Records",
                        PURPLE
                );

        RoundedButton clearButton =
                createActionButton(
                        "Clear",
                        new Color(
                                100,
                                116,
                                139
                        )
                );


        buttons.add(borrowButton);
        buttons.add(returnButton);
        buttons.add(checkButton);
        buttons.add(recordsButton);
        buttons.add(clearButton);


        formCard.add(
                buttons,
                BorderLayout.SOUTH
        );


        borrowButton.addActionListener(
                e -> borrowBook()
        );

        returnButton.addActionListener(
                e -> returnBook()
        );

        checkButton.addActionListener(
                e -> checkAvailability()
        );

        recordsButton.addActionListener(
                e -> viewRecords()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );


        return formCard;
    }


    // =====================================================
    // INPUT PANEL
    // =====================================================

    private JPanel createInputPanel(
            String labelText
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                8
                        )
                );

        panel.setOpaque(false);


        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(TEXT_DARK);


        panel.add(
                label,
                BorderLayout.NORTH
        );


        return panel;
    }


    // =====================================================
    // TEXT FIELD
    // =====================================================

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
                        200,
                        42
                )
        );

        field.setForeground(TEXT_DARK);

        field.setBackground(
                new Color(
                        248,
                        250,
                        252
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );


        return field;
    }


    // =====================================================
    // OUTPUT CARD
    // =====================================================

    private JPanel createOutputCard() {

        RoundedPanel card =
                new RoundedPanel(
                        18,
                        CARD
                );

        card.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        card.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );


        JPanel heading =
                new JPanel(
                        new BorderLayout()
                );

        heading.setOpaque(false);


        JLabel title =
                new JLabel(
                        "Activity / Results"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        title.setForeground(TEXT_DARK);


        JLabel live =
                new JLabel("LIVE");

        live.setOpaque(true);

        live.setBackground(
                new Color(
                        220,
                        252,
                        231
                )
        );

        live.setForeground(GREEN);

        live.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        live.setBorder(
                new EmptyBorder(
                        5,
                        9,
                        5,
                        9
                )
        );


        heading.add(
                title,
                BorderLayout.WEST
        );

        heading.add(
                live,
                BorderLayout.EAST
        );


        outputArea =
                new JTextArea(
                        8,
                        40
                );

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        outputArea.setForeground(TEXT_DARK);

        outputArea.setBackground(
                new Color(
                        248,
                        250,
                        252
                )
        );

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);

        outputArea.setBorder(
                new EmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        outputArea
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );


        card.add(
                heading,
                BorderLayout.NORTH
        );

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );


        return card;
    }


    // =====================================================
    // STATUS BAR
    // =====================================================

    private JPanel createStatusBar() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);

        panel.setBorder(
                new EmptyBorder(
                        8,
                        5,
                        0,
                        5
                )
        );


        statusLabel =
                new JLabel(
                        "System ready"
                );

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        statusLabel.setForeground(TEXT_GRAY);


        JLabel moduleLabel =
                new JLabel(
                        "Borrowing Module"
                );

        moduleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        moduleLabel.setForeground(TEXT_GRAY);


        panel.add(
                statusLabel,
                BorderLayout.WEST
        );

        panel.add(
                moduleLabel,
                BorderLayout.EAST
        );


        return panel;
    }


    // =====================================================
    // SIDEBAR BUTTON
    // =====================================================

    private JButton createSidebarButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        button.setForeground(
                new Color(
                        220,
                        228,
                        240
                )
        );

        button.setBackground(SIDEBAR);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =====================================================
    // ACTION BUTTON
    // =====================================================

    private RoundedButton createActionButton(
            String text,
            Color color
    ) {

        RoundedButton button =
                new RoundedButton(
                        text,
                        color
                );

        button.setPreferredSize(
                new Dimension(
                        135,
                        40
                )
        );

        return button;
    }


    // =====================================================
    // BORROW BOOK
    // =====================================================

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
                        + "----------------------------------------\n\n"
                        + "Member ID : "
                        + memberId
                        + "\n"
                        + "Book ID   : "
                        + bookId
                        + "\n\n"
                        + result
        );


        statusLabel.setText(result);
    }


    // =====================================================
    // RETURN BOOK
    // =====================================================

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
                        + "----------------------------------------\n\n"
                        + "Member ID : "
                        + memberId
                        + "\n"
                        + "Book ID   : "
                        + bookId
                        + "\n\n"
                        + result
        );


        statusLabel.setText(result);
    }


    // =====================================================
    // CHECK AVAILABILITY
    // =====================================================

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
                manager.findBook(bookId);


        if (book == null) {

            outputArea.setText(
                    "BOOK AVAILABILITY\n"
                            + "----------------------------------------\n\n"
                            + "Book ID : "
                            + bookId
                            + "\n\n"
                            + "Book not found."
            );

            statusLabel.setText(
                    "Book not found"
            );

            return;
        }


        String status =
                book.isAvailable()
                        ? "AVAILABLE"
                        : "BORROWED";


        outputArea.setText(
                "BOOK AVAILABILITY\n"
                        + "----------------------------------------\n\n"
                        + "Book ID         : "
                        + book.getBookId()
                        + "\n"
                        + "Title           : "
                        + book.getTitle()
                        + "\n"
                        + "Author          : "
                        + book.getAuthor()
                        + "\n"
                        + "Category        : "
                        + book.getCategory()
                        + "\n"
                        + "Published Year  : "
                        + book.getPublishedYear()
                        + "\n\n"
                        + "Current Status  : "
                        + status
        );


        statusLabel.setText(
                "Availability checked"
        );
    }


    // =====================================================
    // VIEW RECORDS
    // =====================================================

    private void viewRecords() {

        outputArea.setText(
                "BORROWING RECORDS\n"
                        + "========================================\n\n"
                        + manager.getAllRecords()
        );


        statusLabel.setText(
                "Showing borrowing records"
        );
    }


    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        memberIdField.setText("");

        bookIdField.setText("");

        outputArea.setText("");

        statusLabel.setText(
                "System ready"
        );

        memberIdField.requestFocus();
    }


    // =====================================================
    // ID VALIDATION
    // =====================================================

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
                    Integer.parseInt(text);

            if (value <= 0) {

                throw new NumberFormatException();
            }

            return value;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    fieldName + " must be a positive number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return null;
        }
    }


    // =====================================================
    // ROUNDED PANEL
    // =====================================================

    static class RoundedPanel
            extends JPanel {

        private final int radius;
        private final Color backgroundColor;


        public RoundedPanel(
                int radius,
                Color backgroundColor
        ) {

            this.radius =
                    radius;

            this.backgroundColor =
                    backgroundColor;

            setOpaque(false);
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(g);


            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            g2.setColor(
                    backgroundColor
            );


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );


            g2.dispose();
        }
    }


    // =====================================================
    // ROUNDED BUTTON
    // =====================================================

    static class RoundedButton
            extends JButton {

        private final Color buttonColor;


        public RoundedButton(
                String text,
                Color buttonColor
        ) {

            super(text);

            this.buttonColor =
                    buttonColor;


            setForeground(Color.WHITE);

            setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            12
                    )
            );


            setFocusPainted(false);

            setBorderPainted(false);

            setContentAreaFilled(false);

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            if (getModel().isPressed()) {

                g2.setColor(
                        buttonColor.darker()
                );

            } else if (
                    getModel().isRollover()
            ) {

                g2.setColor(
                        buttonColor.brighter()
                );

            } else {

                g2.setColor(
                        buttonColor
                );
            }


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    14,
                    14
            );


            g2.dispose();


            super.paintComponent(g);
        }
    }
}