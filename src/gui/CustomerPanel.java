package gui;

import library.app.LibrarySystem;
import library.manager.BorrowingRecord;
import library.model.Book;
import library.model.Member;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerPanel extends JFrame {


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

    private static final Color LIGHT_BLUE =
            new Color(239, 246, 255);



    // DATA
    private final LibrarySystem librarySystem;
    private final Member loggedInMember;



    // OVERVIEW LABELS
    private JLabel borrowedBooksLabel;
    private JLabel waitingBooksLabel;
    private JLabel overdueBooksLabel;


    public CustomerPanel(
            LibrarySystem librarySystem,
            Member loggedInMember
    ) {

        this.librarySystem = librarySystem;
        this.loggedInMember = loggedInMember;

        setTitle(
                "Smart Library - "
                        + loggedInMember.getName()
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

        refreshOverview();

        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowActivated(
                            WindowEvent e
                    ) {

                        refreshOverview();
                    }
                }
        );
    }



    // MAIN UI
    private void createUI() {

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
                BACKGROUND
        );


        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                HEADER_COLOR
        );

        header.setBorder(
                new EmptyBorder(
                        22,
                        35,
                        22,
                        35
                )
        );


        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(
                false
        );

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Welcome, "
                                + loggedInMember
                                .getName()
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                Color.WHITE
        );


        JLabel subtitle =
                new JLabel(
                        "Member ID: "
                                + loggedInMember
                                .getMemberId()
                                + "  •  "
                                + loggedInMember
                                .getEmail()
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


        titlePanel.add(
                title
        );

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(
                subtitle
        );


        JLabel role =
                new JLabel(
                        "MEMBER"
                );

        role.setForeground(
                Color.WHITE
        );

        role.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );


        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        header.add(
                role,
                BorderLayout.EAST
        );


        root.add(
                header,
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel();

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        25,
                        40,
                        25,
                        40
                )
        );


        JLabel sectionTitle =
                new JLabel(
                        "Library Services"
                );

        sectionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        sectionTitle.setForeground(
                TEXT_COLOR
        );

        sectionTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        content.add(
                sectionTitle
        );

        content.add(
                Box.createVerticalStrut(15)
        );


        JPanel services =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                18,
                                18
                        )
                );

        services.setOpaque(
                false
        );

        services.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        330
                )
        );

        services.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JButton booksButton =
                createServiceCard(
                        "Browse Books",
                        "Search and explore library books"
                );


        JButton borrowButton =
                createServiceCard(
                        "Borrow / Return",
                        "Borrow or return a book"
                );


        JButton recommendationButton =
                createServiceCard(
                        "Recommendations",
                        "Discover recommended books"
                );


        JButton waitingButton =
                createServiceCard(
                        "Waiting List",
                        "Manage your waiting requests"
                );


        JButton overdueButton =
                createServiceCard(
                        "My Overdue Books",
                        "Check only your overdue books"
                );


        JButton accountButton =
                createServiceCard(
                        "My Account",
                        "View your member details"
                );


        services.add(
                booksButton
        );

        services.add(
                borrowButton
        );

        services.add(
                recommendationButton
        );

        services.add(
                waitingButton
        );

        services.add(
                overdueButton
        );

        services.add(
                accountButton
        );


        content.add(
                services
        );

        content.add(
                Box.createVerticalStrut(25)
        );


        JLabel overviewTitle =
                new JLabel(
                        "My Library Overview"
                );

        overviewTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        overviewTitle.setForeground(
                TEXT_COLOR
        );

        overviewTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        content.add(
                overviewTitle
        );

        content.add(
                Box.createVerticalStrut(15)
        );


        JPanel overview =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        overview.setOpaque(
                false
        );

        overview.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        110
                )
        );

        overview.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        borrowedBooksLabel =
                createStatValueLabel();

        waitingBooksLabel =
                createStatValueLabel();

        overdueBooksLabel =
                createStatValueLabel();


        overview.add(
                createStatCard(
                        "Currently Borrowed",
                        borrowedBooksLabel
                )
        );


        overview.add(
                createStatCard(
                        "Waiting List",
                        waitingBooksLabel
                )
        );


        overview.add(
                createStatCard(
                        "Overdue Books",
                        overdueBooksLabel
                )
        );


        content.add(
                overview
        );

        content.add(
                Box.createVerticalGlue()
        );


        root.add(
                content,
                BorderLayout.CENTER
        );


        setContentPane(
                root
        );

        // BUTTON ACTIONS
        booksButton.addActionListener(
                e -> showBookSearch()
        );


        borrowButton.addActionListener(
                e -> showBorrowReturn()
        );


        recommendationButton.addActionListener(
                e -> new RecommendationPanel(
                        librarySystem
                                .getBookManager()
                ).setVisible(true)
        );


        waitingButton.addActionListener(
                e -> new WaitingListPanel(
                        librarySystem
                                .getWaitingListManager(),
                        loggedInMember
                                .getMemberId()
                ).setVisible(true)
        );


        overdueButton.addActionListener(
                e -> showMyOverdueBooks()
        );


        accountButton.addActionListener(
                e -> showMyAccount()
        );
    }


// BORROW / RETURN
    private void showBorrowReturn() {

        JFrame frame =
                new JFrame(
                        "Smart Library - Borrow / Return"
                );

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        frame.setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
                BACKGROUND
        );

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                HEADER_COLOR
        );

        header.setBorder(
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


        JLabel title =
                new JLabel(
                        "Borrow / Return Books"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        29
                )
        );

        title.setForeground(
                Color.WHITE
        );


        JLabel subtitle =
                new JLabel(
                        "Manage your library borrowing activities"
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


        JLabel memberBadge =
                new JLabel(
                        "MEMBER "
                                + loggedInMember
                                .getMemberId()
                );

        memberBadge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        memberBadge.setForeground(
                Color.WHITE
        );


        header.add(
                headerText,
                BorderLayout.WEST
        );

        header.add(
                memberBadge,
                BorderLayout.EAST
        );


        root.add(
                header,
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        JPanel topSection =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        topSection.setOpaque(
                false
        );

        topSection.setPreferredSize(
                new Dimension(
                        0,
                        215
                )
        );

        JPanel actionCard =
                new JPanel();

        actionCard.setLayout(
                new BoxLayout(
                        actionCard,
                        BoxLayout.Y_AXIS
                )
        );

        actionCard.setBackground(
                CARD_COLOR
        );

        actionCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );


        JLabel actionTitle =
                new JLabel(
                        "Book Transaction"
                );

        actionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        actionTitle.setForeground(
                TEXT_COLOR
        );

        actionTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel actionSubtitle =
                new JLabel(
                        "Enter a Book ID and choose an action"
                );

        actionSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        actionSubtitle.setForeground(
                SUBTEXT_COLOR
        );

        actionSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JTextField bookIdField =
                new JTextField();

        bookIdField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        bookIdField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        bookIdField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel bookIdLabel =
                new JLabel(
                        "Book ID"
                );

        bookIdLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        bookIdLabel.setForeground(
                TEXT_COLOR
        );

        bookIdLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        actionCard.add(
                actionTitle
        );

        actionCard.add(
                Box.createVerticalStrut(4)
        );

        actionCard.add(
                actionSubtitle
        );

        actionCard.add(
                Box.createVerticalStrut(18)
        );

        actionCard.add(
                bookIdLabel
        );

        actionCard.add(
                Box.createVerticalStrut(6)
        );

        actionCard.add(
                bookIdField
        );

        actionCard.add(
                Box.createVerticalStrut(15)
        );

        // ACTION BUTTONS
        JPanel actionButtons =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                0
                        )
                );

        actionButtons.setOpaque(
                false
        );

        actionButtons.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        43
                )
        );

        actionButtons.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JButton borrowButton =
                new JButton(
                        "BORROW BOOK"
                );

        borrowButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        borrowButton.setForeground(
                Color.WHITE
        );

        borrowButton.setBackground(
                PRIMARY_COLOR
        );

        borrowButton.setOpaque(
                true
        );

        borrowButton.setContentAreaFilled(
                true
        );

        borrowButton.setBorderPainted(
                false
        );

        borrowButton.setFocusPainted(
                false
        );

        borrowButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        JButton returnButton =
                new JButton(
                        "RETURN BOOK"
                );

        returnButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        returnButton.setForeground(
                Color.WHITE
        );

        returnButton.setBackground(
                new Color(
                        22,
                        163,
                        74
                )
        );

        returnButton.setOpaque(
                true
        );

        returnButton.setContentAreaFilled(
                true
        );

        returnButton.setBorderPainted(
                false
        );

        returnButton.setFocusPainted(
                false
        );

        returnButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        actionButtons.add(
                borrowButton
        );

        actionButtons.add(
                returnButton
        );


        actionCard.add(
                actionButtons
        );

        JPanel memberCard =
                new JPanel(
                        new BorderLayout()
                );

        memberCard.setBackground(
                CARD_COLOR
        );

        memberCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );


        JPanel memberInfo =
                new JPanel();

        memberInfo.setOpaque(
                false
        );

        memberInfo.setLayout(
                new BoxLayout(
                        memberInfo,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel memberTitle =
                new JLabel(
                        "My Borrowing Account"
                );

        memberTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        memberTitle.setForeground(
                TEXT_COLOR
        );


        JLabel memberName =
                new JLabel(
                        loggedInMember
                                .getName()
                );

        memberName.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        memberName.setForeground(
                TEXT_COLOR
        );


        JLabel memberDetails =
                new JLabel(
                        "Member ID: "
                                + loggedInMember
                                .getMemberId()
                                + "   |   "
                                + loggedInMember
                                .getEmail()
                );

        memberDetails.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        memberDetails.setForeground(
                SUBTEXT_COLOR
        );


        JLabel policyLabel =
                new JLabel(
                        "Standard borrowing period: 14 days"
                );

        policyLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        policyLabel.setForeground(
                SUBTEXT_COLOR
        );


        memberInfo.add(
                memberTitle
        );

        memberInfo.add(
                Box.createVerticalStrut(20)
        );

        memberInfo.add(
                memberName
        );

        memberInfo.add(
                Box.createVerticalStrut(7)
        );

        memberInfo.add(
                memberDetails
        );

        memberInfo.add(
                Box.createVerticalStrut(20)
        );

        memberInfo.add(
                policyLabel
        );


        memberCard.add(
                memberInfo,
                BorderLayout.CENTER
        );


        topSection.add(
                actionCard
        );

        topSection.add(
                memberCard
        );


        content.add(
                topSection,
                BorderLayout.NORTH
        );

        // BORROWING RECORDS CARD
        JPanel recordCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        recordCard.setBackground(
                CARD_COLOR
        );

        recordCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );


        JPanel recordHeading =
                new JPanel(
                        new BorderLayout()
                );

        recordHeading.setOpaque(
                false
        );


        JPanel recordTitlePanel =
                new JPanel();

        recordTitlePanel.setOpaque(
                false
        );

        recordTitlePanel.setLayout(
                new BoxLayout(
                        recordTitlePanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel recordTitle =
                new JLabel(
                        "My Borrowing Records"
                );

        recordTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        recordTitle.setForeground(
                TEXT_COLOR
        );


        JLabel recordSubtitle =
                new JLabel(
                        "Your current and previous borrowing activities"
                );

        recordSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        recordSubtitle.setForeground(
                SUBTEXT_COLOR
        );


        recordTitlePanel.add(
                recordTitle
        );

        recordTitlePanel.add(
                Box.createVerticalStrut(3)
        );

        recordTitlePanel.add(
                recordSubtitle
        );


        JLabel borrowedCountLabel =
                new JLabel(
                        "Currently Borrowed: 0"
                );

        borrowedCountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        borrowedCountLabel.setForeground(
                PRIMARY_COLOR
        );


        recordHeading.add(
                recordTitlePanel,
                BorderLayout.WEST
        );

        recordHeading.add(
                borrowedCountLabel,
                BorderLayout.EAST
        );


        recordCard.add(
                recordHeading,
                BorderLayout.NORTH
        );


        String[] columns = {
                "Book ID",
                "Book Title",
                "Borrow Date",
                "Due Date",
                "Status"
        };


        DefaultTableModel model =
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


        JTable table =
                new JTable(
                        model
                );


        styleTable(
                table
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );


        recordCard.add(
                scrollPane,
                BorderLayout.CENTER
        );


        content.add(
                recordCard,
                BorderLayout.CENTER
        );

        JLabel statusLabel =
                new JLabel(
                        "Ready. Enter a Book ID to borrow or return a book."
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


        content.add(
                statusLabel,
                BorderLayout.SOUTH
        );


        root.add(
                content,
                BorderLayout.CENTER
        );


        frame.setContentPane(
                root
        );

        // REFRESH MEMBER RECORDS
        Runnable refreshRecords = () -> {

            model.setRowCount(
                    0
            );


            int activeBorrowings = 0;


            String memberId =
                    String.valueOf(
                            loggedInMember
                                    .getMemberId()
                    );


            for (BorrowingRecord record :
                    librarySystem
                            .getBorrowingManager()
                            .getRecords()) {


                if (!record
                        .getMemberId()
                        .equals(memberId)) {

                    continue;
                }


                String bookTitle =
                        "Unknown Book";


                try {

                    Book book =
                            librarySystem
                                    .getBookManager()
                                    .searchBook(
                                            Integer.parseInt(
                                                    record
                                                            .getBookId()
                                            )
                                    );


                    if (book != null) {

                        bookTitle =
                                book.getTitle();
                    }


                } catch (
                        NumberFormatException ignored
                ) {
                }


                model.addRow(
                        new Object[]{
                                record.getBookId(),
                                bookTitle,
                                record.getBorrowDate(),
                                record.getDueDate(),
                                record.getStatus()
                        }
                );


                if (record
                        .getStatus()
                        .equalsIgnoreCase(
                                "Borrowed"
                        )) {

                    activeBorrowings++;
                }
            }


            borrowedCountLabel.setText(
                    "Currently Borrowed: "
                            + activeBorrowings
            );
        };

        borrowButton.addActionListener(
                e -> {

                    String input =
                            bookIdField
                                    .getText()
                                    .trim();


                    if (input.isEmpty()) {

                        statusLabel.setText(
                                "Please enter a Book ID."
                        );

                        return;
                    }


                    try {

                        int bookId =
                                Integer.parseInt(
                                        input
                                );


                        if (bookId <= 0) {

                            throw new NumberFormatException();
                        }


                        String result =
                                librarySystem
                                        .getBorrowingManager()
                                        .borrowBook(
                                                loggedInMember
                                                        .getMemberId(),
                                                bookId
                                        );


                        statusLabel.setText(
                                result
                        );


                        bookIdField.setText(
                                ""
                        );


                        refreshRecords.run();

                        refreshOverview();


                    } catch (
                            NumberFormatException ex
                    ) {

                        statusLabel.setText(
                                "Book ID must be a positive number."
                        );
                    }
                }
        );

        returnButton.addActionListener(
                e -> {

                    String input =
                            bookIdField
                                    .getText()
                                    .trim();


                    if (input.isEmpty()) {

                        statusLabel.setText(
                                "Please enter a Book ID."
                        );

                        return;
                    }


                    try {

                        int bookId =
                                Integer.parseInt(
                                        input
                                );


                        if (bookId <= 0) {

                            throw new NumberFormatException();
                        }


                        String result =
                                librarySystem
                                        .getBorrowingManager()
                                        .returnBook(
                                                loggedInMember
                                                        .getMemberId(),
                                                bookId
                                        );


                        statusLabel.setText(
                                result
                        );


                        bookIdField.setText(
                                ""
                        );


                        refreshRecords.run();

                        refreshOverview();


                    } catch (
                            NumberFormatException ex
                    ) {

                        statusLabel.setText(
                                "Book ID must be a positive number."
                        );
                    }
                }
        );


        // Enter = Borrow
        bookIdField.addActionListener(
                e -> borrowButton.doClick()
        );


        refreshRecords.run();


        frame.setVisible(
                true
        );
    }

    // MY ACCOUNT
    private void showMyAccount() {

        JFrame frame =
                new JFrame(
                        "Smart Library - My Account"
                );

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        frame.setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
                BACKGROUND
        );
        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                HEADER_COLOR
        );

        header.setBorder(
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


        JLabel title =
                new JLabel(
                        "My Account"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        29
                )
        );

        title.setForeground(
                Color.WHITE
        );


        JLabel subtitle =
                new JLabel(
                        "View your membership and library account details"
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


        JLabel badge =
                new JLabel(
                        "MEMBER "
                                + loggedInMember
                                .getMemberId()
                );

        badge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        badge.setForeground(
                Color.WHITE
        );


        header.add(
                headerText,
                BorderLayout.WEST
        );

        header.add(
                badge,
                BorderLayout.EAST
        );


        root.add(
                header,
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );
        JPanel profileCard =
                new JPanel();

        profileCard.setLayout(
                new BoxLayout(
                        profileCard,
                        BoxLayout.Y_AXIS
                )
        );

        profileCard.setBackground(
                CARD_COLOR
        );

        profileCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                25,
                                28,
                                25,
                                28
                        )
                )
        );


        JLabel profileTitle =
                new JLabel(
                        "Member Profile"
                );

        profileTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        profileTitle.setForeground(
                TEXT_COLOR
        );

        profileTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel profileSubtitle =
                new JLabel(
                        "Your registered account information"
                );

        profileSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        profileSubtitle.setForeground(
                SUBTEXT_COLOR
        );

        profileSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        profileCard.add(
                profileTitle
        );

        profileCard.add(
                Box.createVerticalStrut(4)
        );

        profileCard.add(
                profileSubtitle
        );

        profileCard.add(
                Box.createVerticalStrut(25)
        );


        JPanel idRow =
                createAccountRow(
                        "Member ID",
                        String.valueOf(
                                loggedInMember
                                        .getMemberId()
                        )
                );

        idRow.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        idRow.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JPanel nameRow =
                createAccountRow(
                        "Full Name",
                        loggedInMember
                                .getName()
                );

        nameRow.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        nameRow.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JPanel emailRow =
                createAccountRow(
                        "Email",
                        loggedInMember
                                .getEmail()
                );

        emailRow.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        emailRow.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JPanel phoneRow =
                createAccountRow(
                        "Phone Number",
                        loggedInMember
                                .getPhone()
                );

        phoneRow.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        phoneRow.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        profileCard.add(
                idRow
        );

        profileCard.add(
                Box.createVerticalStrut(12)
        );

        profileCard.add(
                nameRow
        );

        profileCard.add(
                Box.createVerticalStrut(12)
        );

        profileCard.add(
                emailRow
        );

        profileCard.add(
                Box.createVerticalStrut(12)
        );

        profileCard.add(
                phoneRow
        );

        JPanel rightPanel =
                new JPanel();

        rightPanel.setOpaque(
                false
        );

        rightPanel.setLayout(
                new BoxLayout(
                        rightPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel summaryTitle =
                new JLabel(
                        "Library Activity"
                );

        summaryTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        summaryTitle.setForeground(
                TEXT_COLOR
        );

        summaryTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel summarySubtitle =
                new JLabel(
                        "Your current library account overview"
                );

        summarySubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        summarySubtitle.setForeground(
                SUBTEXT_COLOR
        );

        summarySubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        rightPanel.add(
                summaryTitle
        );

        rightPanel.add(
                Box.createVerticalStrut(4)
        );

        rightPanel.add(
                summarySubtitle
        );

        rightPanel.add(
                Box.createVerticalStrut(18)
        );


        int borrowed = 0;
        int overdue = 0;

        String memberId =
                String.valueOf(
                        loggedInMember
                                .getMemberId()
                );


        for (BorrowingRecord record :
                librarySystem
                        .getBorrowingManager()
                        .getRecords()) {

            if (!record
                    .getMemberId()
                    .equals(memberId)) {

                continue;
            }


            if (record
                    .getStatus()
                    .equalsIgnoreCase(
                            "Borrowed"
                    )) {

                borrowed++;
            }


            if (record.isOverdue()) {

                overdue++;
            }
        }


        int waiting =
                librarySystem
                        .getWaitingListManager()
                        .getWaitingCountForMember(
                                loggedInMember
                                        .getMemberId()
                        );


        JLabel borrowedValue =
                createStatValueLabel();

        borrowedValue.setText(
                String.valueOf(
                        borrowed
                )
        );


        JLabel waitingValue =
                createStatValueLabel();

        waitingValue.setText(
                String.valueOf(
                        waiting
                )
        );


        JLabel overdueValue =
                createStatValueLabel();

        overdueValue.setText(
                String.valueOf(
                        overdue
                )
        );


        JPanel stats =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                15
                        )
                );

        stats.setOpaque(
                false
        );

        stats.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        310
                )
        );

        stats.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        stats.add(
                createStatCard(
                        "Currently Borrowed",
                        borrowedValue
                )
        );


        stats.add(
                createStatCard(
                        "Waiting List",
                        waitingValue
                )
        );


        stats.add(
                createStatCard(
                        "Overdue Books",
                        overdueValue
                )
        );


        rightPanel.add(
                stats
        );


        rightPanel.add(
                Box.createVerticalStrut(20)
        );

        JPanel statusCard =
                new JPanel();

        statusCard.setLayout(
                new BoxLayout(
                        statusCard,
                        BoxLayout.Y_AXIS
                )
        );

        statusCard.setBackground(
                CARD_COLOR
        );

        statusCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        statusCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        statusCard.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        110
                )
        );


        JLabel activeTitle =
                new JLabel(
                        "Account Status"
                );

        activeTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        activeTitle.setForeground(
                SUBTEXT_COLOR
        );


        JLabel activeValue =
                new JLabel(
                        "ACTIVE MEMBER"
                );

        activeValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        activeValue.setForeground(
                new Color(
                        22,
                        163,
                        74
                )
        );


        statusCard.add(
                activeTitle
        );

        statusCard.add(
                Box.createVerticalStrut(7)
        );

        statusCard.add(
                activeValue
        );


        rightPanel.add(
                statusCard
        );
        JPanel mainGrid =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        mainGrid.setOpaque(
                false
        );


        mainGrid.add(
                profileCard
        );

        mainGrid.add(
                rightPanel
        );


        content.add(
                mainGrid,
                BorderLayout.CENTER
        );


        JLabel footer =
                new JLabel(
                        "Your account information is managed by the library administrator."
                );

        footer.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        footer.setForeground(
                SUBTEXT_COLOR
        );


        content.add(
                footer,
                BorderLayout.SOUTH
        );


        root.add(
                content,
                BorderLayout.CENTER
        );


        frame.setContentPane(
                root
        );

        frame.setVisible(
                true
        );
    }

    // MY OVERDUE BOOKS
    private void showMyOverdueBooks() {

        JFrame frame =
                new JFrame(
                        "Smart Library - My Overdue Books"
                );

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        frame.setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
                BACKGROUND
        );

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                HEADER_COLOR
        );

        header.setBorder(
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


        JLabel title =
                new JLabel(
                        "My Overdue Books"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        29
                )
        );

        title.setForeground(
                Color.WHITE
        );


        JLabel subtitle =
                new JLabel(
                        "Review books that have passed their due date"
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


        JLabel badge =
                new JLabel(
                        "MEMBER "
                                + loggedInMember
                                .getMemberId()
                );

        badge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        badge.setForeground(
                Color.WHITE
        );


        header.add(
                headerText,
                BorderLayout.WEST
        );

        header.add(
                badge,
                BorderLayout.EAST
        );


        root.add(
                header,
                BorderLayout.NORTH
        );

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        summaryPanel.setOpaque(
                false
        );

        summaryPanel.setPreferredSize(
                new Dimension(
                        0,
                        115
                )
        );


        JLabel overdueCountValue =
                createStatValueLabel();


        JLabel borrowedCountValue =
                createStatValueLabel();


        JLabel policyValue =
                new JLabel(
                        "14 Days"
                );

        policyValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        policyValue.setForeground(
                TEXT_COLOR
        );

        String[] columns = {
                "Book ID",
                "Book Title",
                "Borrow Date",
                "Due Date",
                "Status"
        };


        DefaultTableModel model =
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


        int overdueCount = 0;
        int borrowedCount = 0;


        String memberId =
                String.valueOf(
                        loggedInMember
                                .getMemberId()
                );


        for (BorrowingRecord record :
                librarySystem
                        .getBorrowingManager()
                        .getRecords()) {


            if (!record
                    .getMemberId()
                    .equals(memberId)) {

                continue;
            }


            if (record
                    .getStatus()
                    .equalsIgnoreCase(
                            "Borrowed"
                    )) {

                borrowedCount++;
            }


            if (!record.isOverdue()) {

                continue;
            }


            overdueCount++;


            String bookTitle =
                    "Unknown Book";


            try {

                Book book =
                        librarySystem
                                .getBookManager()
                                .searchBook(
                                        Integer.parseInt(
                                                record
                                                        .getBookId()
                                        )
                                );


                if (book != null) {

                    bookTitle =
                            book.getTitle();
                }


            } catch (
                    NumberFormatException ignored
            ) {
            }


            model.addRow(
                    new Object[]{
                            record.getBookId(),
                            bookTitle,
                            record.getBorrowDate(),
                            record.getDueDate(),
                            "OVERDUE"
                    }
            );
        }


        overdueCountValue.setText(
                String.valueOf(
                        overdueCount
                )
        );


        borrowedCountValue.setText(
                String.valueOf(
                        borrowedCount
                )
        );


        summaryPanel.add(
                createStatCard(
                        "Overdue Books",
                        overdueCountValue
                )
        );


        summaryPanel.add(
                createStatCard(
                        "Currently Borrowed",
                        borrowedCountValue
                )
        );


        summaryPanel.add(
                createStatCard(
                        "Borrowing Period",
                        policyValue
                )
        );


        content.add(
                summaryPanel,
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
                                20,
                                22,
                                20,
                                22
                        )
                )
        );


        JPanel heading =
                new JPanel(
                        new BorderLayout()
                );

        heading.setOpaque(
                false
        );


        JPanel headingText =
                new JPanel();

        headingText.setOpaque(
                false
        );

        headingText.setLayout(
                new BoxLayout(
                        headingText,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel tableTitle =
                new JLabel(
                        "Overdue Borrowing Records"
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
                        "Books listed below should be returned as soon as possible"
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


        headingText.add(
                tableTitle
        );

        headingText.add(
                Box.createVerticalStrut(4)
        );

        headingText.add(
                tableSubtitle
        );


        JLabel recordCount =
                new JLabel(
                        overdueCount == 0
                                ? "No overdue books"
                                : overdueCount
                                  + " overdue book(s)"
                );

        recordCount.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        recordCount.setForeground(
                overdueCount == 0
                        ? new Color(
                        22,
                        163,
                        74
                )
                        : new Color(
                        220,
                        38,
                        38
                )
        );


        heading.add(
                headingText,
                BorderLayout.WEST
        );

        heading.add(
                recordCount,
                BorderLayout.EAST
        );


        tableCard.add(
                heading,
                BorderLayout.NORTH
        );


        JTable table =
                new JTable(
                        model
                );


        styleTable(
                table
        );


        JScrollPane scroll =
                new JScrollPane(
                        table
                );


        scroll.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );


        scroll
                .getViewport()
                .setBackground(
                        Color.WHITE
                );


        tableCard.add(
                scroll,
                BorderLayout.CENTER
        );


        content.add(
                tableCard,
                BorderLayout.CENTER
        );

        JLabel statusLabel =
                new JLabel(
                        overdueCount == 0
                                ? "Your account currently has no overdue books."
                                : "Please return overdue books to keep your account in good standing."
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


        content.add(
                statusLabel,
                BorderLayout.SOUTH
        );


        root.add(
                content,
                BorderLayout.CENTER
        );


        frame.setContentPane(
                root
        );

        frame.setVisible(
                true
        );
    }

    private void refreshOverview() {

        int borrowed = 0;
        int overdue = 0;


        String memberId =
                String.valueOf(
                        loggedInMember
                                .getMemberId()
                );


        for (BorrowingRecord record :
                librarySystem
                        .getBorrowingManager()
                        .getRecords()) {


            if (!record
                    .getMemberId()
                    .equals(memberId)) {

                continue;
            }


            if (record
                    .getStatus()
                    .equalsIgnoreCase(
                            "Borrowed"
                    )) {

                borrowed++;
            }


            if (record.isOverdue()) {

                overdue++;
            }
        }


        borrowedBooksLabel.setText(
                String.valueOf(
                        borrowed
                )
        );


        overdueBooksLabel.setText(
                String.valueOf(
                        overdue
                )
        );


        int waitingCount =
                librarySystem
                        .getWaitingListManager()
                        .getWaitingCountForMember(
                                loggedInMember
                                        .getMemberId()
                        );


        waitingBooksLabel.setText(
                String.valueOf(
                        waitingCount
                )
        );
    }

    // BROWSE BOOKS
    private void showBookSearch() {

        JFrame frame =
                new JFrame(
                        "Browse Books"
                );


        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );


        frame.setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );


        frame.setMinimumSize(
                new Dimension(
                        950,
                        600
                )
        );


        JPanel root =
                new JPanel(
                        new BorderLayout()
                );


        root.setBackground(
                BACKGROUND
        );

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );


        header.setBackground(
                HEADER_COLOR
        );


        header.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
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


        JLabel title =
                new JLabel(
                        "Browse Books"
                );


        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );


        title.setForeground(
                Color.WHITE
        );


        JLabel subtitle =
                new JLabel(
                        "Search and explore the library collection"
                );


        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
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


        header.add(
                headerText,
                BorderLayout.WEST
        );


        root.add(
                header,
                BorderLayout.NORTH
        );


        JPanel content =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );


        content.setBackground(
                BACKGROUND
        );


        content.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );


        searchPanel.setBackground(
                CARD_COLOR
        );


        searchPanel.setBorder(
                BorderFactory
                        .createCompoundBorder(
                                BorderFactory
                                        .createLineBorder(
                                                BORDER_COLOR
                                        ),
                                new EmptyBorder(
                                        14,
                                        16,
                                        14,
                                        16
                                )
                        )
        );


        JLabel searchLabel =
                new JLabel(
                        "Search Book:"
                );


        searchLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        JTextField searchField =
                new JTextField();


        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        JButton searchButton =
                createSmallPrimaryButton(
                        "Search"
                );


        JButton allButton =
                createSecondaryButton(
                        "Show All"
                );


        searchPanel.add(
                searchLabel,
                BorderLayout.WEST
        );


        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );


        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                7,
                                0
                        )
                );


        buttons.setOpaque(
                false
        );


        buttons.add(
                searchButton
        );


        buttons.add(
                allButton
        );


        searchPanel.add(
                buttons,
                BorderLayout.EAST
        );


        content.add(
                searchPanel,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Book ID",
                "Title",
                "Author",
                "Category",
                "Year",
                "Availability"
        };


        DefaultTableModel model =
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


        JTable table =
                new JTable(
                        model
                );


        styleTable(
                table
        );


        JScrollPane scroll =
                new JScrollPane(
                        table
                );


        scroll.setBorder(
                BorderFactory
                        .createLineBorder(
                                BORDER_COLOR
                        )
        );


        content.add(
                scroll,
                BorderLayout.CENTER
        );

        Runnable loadAll = () -> {

            model.setRowCount(
                    0
            );


            for (Book book :
                    librarySystem
                            .getBookManager()
                            .getAllBooks()) {

                addBookRow(
                        model,
                        book
                );
            }
        };

        searchButton.addActionListener(
                e -> {

                    String query =
                            searchField
                                    .getText()
                                    .trim()
                                    .toLowerCase();


                    if (query.isEmpty()) {

                        loadAll.run();
                        return;
                    }


                    model.setRowCount(
                            0
                    );


                    for (Book book :
                            librarySystem
                                    .getBookManager()
                                    .getAllBooks()) {


                        boolean idMatch =
                                String.valueOf(
                                        book.getBookId()
                                ).equals(query);


                        boolean titleMatch =
                                book.getTitle() != null
                                        && book
                                        .getTitle()
                                        .toLowerCase()
                                        .contains(query);


                        boolean authorMatch =
                                book.getAuthor() != null
                                        && book
                                        .getAuthor()
                                        .toLowerCase()
                                        .contains(query);


                        boolean categoryMatch =
                                book.getCategory() != null
                                        && book
                                        .getCategory()
                                        .toLowerCase()
                                        .contains(query);


                        if (idMatch
                                || titleMatch
                                || authorMatch
                                || categoryMatch) {

                            addBookRow(
                                    model,
                                    book
                            );
                        }
                    }


                    if (model.getRowCount() == 0) {

                        JOptionPane.showMessageDialog(
                                frame,
                                "No matching books were found.",
                                "Search Result",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
        );


        // Press Enter to search
        searchField.addActionListener(
                e -> searchButton.doClick()
        );

        allButton.addActionListener(
                e -> {

                    searchField.setText(
                            ""
                    );

                    loadAll.run();
                }
        );


        loadAll.run();


        root.add(
                content,
                BorderLayout.CENTER
        );


        frame.setContentPane(
                root
        );


        frame.setVisible(
                true
        );
    }

    private void addBookRow(
            DefaultTableModel model,
            Book book
    ) {

        model.addRow(
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


        button.setBackground(
                CARD_COLOR
        );


        // Windows Look & Feel fix
        button.setOpaque(
                true
        );

        button.setContentAreaFilled(
                true
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
                BorderFactory
                        .createCompoundBorder(
                                BorderFactory
                                        .createLineBorder(
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


    private JPanel createStatCard(
            String title,
            JLabel value
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
                BorderFactory
                        .createCompoundBorder(
                                BorderFactory
                                        .createLineBorder(
                                                BORDER_COLOR
                                        ),
                                new EmptyBorder(
                                        18,
                                        20,
                                        18,
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
                value
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
                        28
                )
        );


        label.setForeground(
                TEXT_COLOR
        );


        return label;
    }

    private JPanel createAccountRow(
            String label,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );


        row.setBackground(
                CARD_COLOR
        );


        row.setBorder(
                BorderFactory
                        .createCompoundBorder(
                                BorderFactory
                                        .createLineBorder(
                                                BORDER_COLOR
                                        ),
                                new EmptyBorder(
                                        10,
                                        15,
                                        10,
                                        15
                                )
                        )
        );


        JLabel nameLabel =
                new JLabel(
                        label
                );


        nameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        nameLabel.setForeground(
                SUBTEXT_COLOR
        );


        JLabel valueLabel =
                new JLabel(
                        value == null
                                ? "-"
                                : value
                );


        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        valueLabel.setForeground(
                TEXT_COLOR
        );


        row.add(
                nameLabel,
                BorderLayout.WEST
        );


        row.add(
                valueLabel,
                BorderLayout.EAST
        );


        return row;
    }


    private void styleTable(
            JTable table
    ) {

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        table.setRowHeight(
                36
        );


        table.setAutoCreateRowSorter(
                true
        );


        table.setFillsViewportHeight(
                true
        );


        table.setShowVerticalLines(
                false
        );


        table.setGridColor(
                BORDER_COLOR
        );


        table.setSelectionBackground(
                LIGHT_BLUE
        );


        table.setSelectionForeground(
                TEXT_COLOR
        );


        table.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );


        table.getTableHeader()
                .setBackground(
                        new Color(
                                249,
                                250,
                                251
                        )
                );


        table.getTableHeader()
                .setForeground(
                        TEXT_COLOR
                );


        table.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );
    }

    private JButton createSmallPrimaryButton(
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
                new Color(
                        243,
                        244,
                        246
                )
        );


        button.setOpaque(
                true
        );


        button.setContentAreaFilled(
                true
        );


        button.setFocusPainted(
                false
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }
}