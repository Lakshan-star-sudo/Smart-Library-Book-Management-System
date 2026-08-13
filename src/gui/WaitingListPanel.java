package gui;

import waitinglist.WaitingListManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class WaitingListPanel extends JFrame {


    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color HEADER_COLOR =
            new Color(31, 41, 55);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private static final Color SUBTEXT_COLOR =
            new Color(107, 114, 128);

    private static final Color BORDER_COLOR =
            new Color(229, 231, 235);

    private static final Color PRIMARY_COLOR =
            new Color(37, 99, 235);

    private static final Color SUCCESS_COLOR =
            new Color(22, 163, 74);

    private static final Color DANGER_COLOR =
            new Color(220, 38, 38);

    private static final Color SECONDARY_COLOR =
            new Color(243, 244, 246);


    private final WaitingListManager manager;

    private final Integer loggedInMemberId;

    private final boolean customerMode;


    private JTextField bookIdField;

    private JTextField memberIdField;

    private DefaultTableModel tableModel;

    private JTable waitingTable;

    private JLabel queueCountLabel;

    private JLabel nextMemberLabel;

    private JLabel selectedBookLabel;

    private JLabel statusLabel;


    public WaitingListPanel(
            WaitingListManager manager
    ) {

        this.manager = manager;

        this.loggedInMemberId = null;

        this.customerMode = false;

        initializeWindow();
    }


    public WaitingListPanel(
            WaitingListManager manager,
            int loggedInMemberId
    ) {

        this.manager = manager;

        this.loggedInMemberId =
                loggedInMemberId;

        this.customerMode = true;

        initializeWindow();
    }


    private void initializeWindow() {

        setTitle(
                customerMode
                        ? "Smart Library - My Waiting List"
                        : "Smart Library - Waiting List Management"
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
    }


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
                        customerMode
                                ? "My Waiting List"
                                : "Waiting List Management"
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
                        customerMode
                                ? "Manage books you are currently waiting for"
                                : "Manage members waiting for unavailable books"
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


        JLabel moduleBadge =
                new JLabel(
                        customerMode
                                ? "MEMBER "
                                  + loggedInMemberId
                                : "ADMIN MODULE"
                );

        moduleBadge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        moduleBadge.setForeground(
                Color.WHITE
        );


        header.add(
                headerText,
                BorderLayout.WEST
        );

        header.add(
                moduleBadge,
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
                        20,
                        35
                )
        );


        JPanel topSection =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        topSection.setOpaque(
                false
        );

        JPanel managementCard =
                createCard();

        managementCard.setLayout(
                new BoxLayout(
                        managementCard,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel managementTitle =
                new JLabel(
                        customerMode
                                ? "Waiting List Request"
                                : "Manage Waiting List"
                );

        managementTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        managementTitle.setForeground(
                TEXT_COLOR
        );

        managementTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel managementSubtitle =
                new JLabel(
                        customerMode
                                ? "Enter a Book ID to join or check your queue position"
                                : "Enter a Book ID and Member ID to manage the queue"
                );

        managementSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        managementSubtitle.setForeground(
                SUBTEXT_COLOR
        );

        managementSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        managementCard.add(
                managementTitle
        );

        managementCard.add(
                Box.createVerticalStrut(4)
        );

        managementCard.add(
                managementSubtitle
        );

        managementCard.add(
                Box.createVerticalStrut(18)
        );


        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                12,
                                10
                        )
                );

        formPanel.setOpaque(
                false
        );

        formPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        95
                )
        );

        formPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel bookLabel =
                createFieldLabel(
                        "Book ID"
                );


        bookIdField =
                createTextField();


        JLabel memberLabel =
                createFieldLabel(
                        "Member ID"
                );


        memberIdField =
                createTextField();


        if (customerMode) {

            memberIdField.setText(
                    String.valueOf(
                            loggedInMemberId
                    )
            );

            memberIdField.setEditable(
                    false
            );
        }


        formPanel.add(
                bookLabel
        );

        formPanel.add(
                bookIdField
        );

        formPanel.add(
                memberLabel
        );

        formPanel.add(
                memberIdField
        );


        managementCard.add(
                formPanel
        );

        managementCard.add(
                Box.createVerticalStrut(15)
        );


        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(
                false
        );

        buttonPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        buttonPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JButton addButton =
                createButton(
                        customerMode
                                ? "JOIN WAITING LIST"
                                : "ADD TO WAITING LIST",
                        PRIMARY_COLOR,
                        Color.WHITE
                );


        JButton viewButton =
                createButton(
                        customerMode
                                ? "VIEW MY POSITION"
                                : "VIEW QUEUE",
                        SUCCESS_COLOR,
                        Color.WHITE
                );


        JButton removeButton =
                createButton(
                        customerMode
                                ? "LEAVE WAITING LIST"
                                : "REMOVE MEMBER",
                        DANGER_COLOR,
                        Color.WHITE
                );


        buttonPanel.add(
                addButton
        );

        buttonPanel.add(
                viewButton
        );

        buttonPanel.add(
                removeButton
        );


        managementCard.add(
                buttonPanel
        );


        topSection.add(
                managementCard,
                BorderLayout.CENTER
        );


        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                10
                        )
                );

        summaryPanel.setOpaque(
                false
        );

        summaryPanel.setPreferredSize(
                new Dimension(
                        280,
                        245
                )
        );


        queueCountLabel =
                createValueLabel(
                        "0"
                );


        nextMemberLabel =
                createValueLabel(
                        "-"
                );


        selectedBookLabel =
                createValueLabel(
                        "-"
                );


        summaryPanel.add(
                createStatCard(
                        customerMode
                                ? "Queue Size"
                                : "Members Waiting",
                        queueCountLabel
                )
        );


        summaryPanel.add(
                createStatCard(
                        customerMode
                                ? "My Position"
                                : "Next Member",
                        nextMemberLabel
                )
        );


        summaryPanel.add(
                createStatCard(
                        "Selected Book",
                        selectedBookLabel
                )
        );


        topSection.add(
                summaryPanel,
                BorderLayout.EAST
        );


        content.add(
                topSection,
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
                        customerMode
                                ? "My Queue Status"
                                : "Waiting Queue"
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
                        customerMode
                                ? "Your position for the selected book"
                                : "Members are served using FIFO order"
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
                "Position",
                "Member ID",
                "Queue Status"
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


        waitingTable =
                new JTable(
                        tableModel
                );


        styleTable(
                waitingTable
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        waitingTable
                );


        scrollPane.setBorder(
                BorderFactory.createLineBorder(
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


        content.add(
                tableCard,
                BorderLayout.CENTER
        );


        statusLabel =
                new JLabel(
                        customerMode
                                ? "Enter a Book ID to manage your waiting request."
                                : "Enter a Book ID to view or manage its waiting queue."
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


        setContentPane(
                root
        );


        addButton.addActionListener(
                e -> addMember()
        );


        viewButton.addActionListener(
                e -> viewWaitingList()
        );


        removeButton.addActionListener(
                e -> removeMember()
        );


        bookIdField.addActionListener(
                e -> viewWaitingList()
        );
    }


    private void addMember() {

        Integer bookId =
                parseId(
                        bookIdField,
                        "Book ID"
                );


        if (bookId == null) {
            return;
        }


        Integer memberId =
                getSelectedMemberId();


        if (memberId == null) {
            return;
        }


        String result =
                manager.addToWaitingList(
                        bookId,
                        memberId
                );


        statusLabel.setText(
                result
        );


        refreshQueue(
                bookId
        );
    }



    private void viewWaitingList() {

        Integer bookId =
                parseId(
                        bookIdField,
                        "Book ID"
                );


        if (bookId == null) {
            return;
        }


        refreshQueue(
                bookId
        );
    }

    private void refreshQueue(
            int bookId
    ) {

        tableModel.setRowCount(
                0
        );


        selectedBookLabel.setText(
                String.valueOf(
                        bookId
                )
        );


        List<Integer> members =
                manager.getWaitingList(
                        bookId
                );


        queueCountLabel.setText(
                String.valueOf(
                        members.size()
                )
        );


        if (customerMode) {

            int position =
                    manager.getMemberPosition(
                            bookId,
                            loggedInMemberId
                    );


            if (position == -1) {

                nextMemberLabel.setText(
                        "-"
                );


                statusLabel.setText(
                        "You are not currently in the waiting list "
                                + "for Book "
                                + bookId
                                + "."
                );


                return;
            }


            nextMemberLabel.setText(
                    String.valueOf(
                            position
                    )
            );


            String status =
                    position == 1
                            ? "NEXT IN QUEUE"
                            : "WAITING";


            tableModel.addRow(
                    new Object[]{
                            position,
                            loggedInMemberId,
                            status
                    }
            );


            statusLabel.setText(
                    "You are currently position "
                            + position
                            + " in the waiting list for Book "
                            + bookId
                            + "."
            );


            return;
        }


        if (members.isEmpty()) {

            nextMemberLabel.setText(
                    "-"
            );


            statusLabel.setText(
                    "No members are currently waiting for Book "
                            + bookId
                            + "."
            );


            return;
        }


        for (int i = 0;
             i < members.size();
             i++) {


            int position =
                    i + 1;


            String status =
                    position == 1
                            ? "NEXT IN QUEUE"
                            : "WAITING";


            tableModel.addRow(
                    new Object[]{
                            position,
                            members.get(i),
                            status
                    }
            );
        }


        Integer nextMember =
                manager.getNextMember(
                        bookId
                );


        nextMemberLabel.setText(
                nextMember == null
                        ? "-"
                        : String.valueOf(
                        nextMember
                )
        );


        statusLabel.setText(
                members.size()
                        + " member(s) waiting for Book "
                        + bookId
                        + "."
        );
    }


    private void removeMember() {

        Integer bookId =
                parseId(
                        bookIdField,
                        "Book ID"
                );


        if (bookId == null) {
            return;
        }


        Integer memberId =
                getSelectedMemberId();


        if (memberId == null) {
            return;
        }


        String result =
                manager.removeFromWaitingList(
                        bookId,
                        memberId
                );


        statusLabel.setText(
                result
        );


        refreshQueue(
                bookId
        );
    }


    private Integer getSelectedMemberId() {

        if (customerMode) {

            return loggedInMemberId;
        }


        return parseId(
                memberIdField,
                "Member ID"
        );
    }


    private Integer parseId(
            JTextField field,
            String fieldName
    ) {

        String text =
                field
                        .getText()
                        .trim();


        if (text.isEmpty()) {

            statusLabel.setText(
                    "Please enter "
                            + fieldName
                            + "."
            );

            field.requestFocus();

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


        } catch (
                NumberFormatException e
        ) {

            statusLabel.setText(
                    fieldName
                            + " must be a positive number."
            );


            field.requestFocus();

            return null;
        }
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
                                20,
                                24,
                                20,
                                24
                        )
                )
        );


        return card;
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


        JLabel titleLabel =
                new JLabel(
                        title
                );


        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        titleLabel.setForeground(
                SUBTEXT_COLOR
        );


        card.add(
                titleLabel
        );


        card.add(
                Box.createVerticalStrut(5)
        );


        card.add(
                value
        );


        return card;
    }


    private JLabel createValueLabel(
            String value
    ) {

        JLabel label =
                new JLabel(
                        value
                );


        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );


        label.setForeground(
                TEXT_COLOR
        );


        return label;
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


        return field;
    }


    private JButton createButton(
            String text,
            Color background,
            Color foreground
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setUI(
                new BasicButtonUI()
        );


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );


        button.setForeground(
                foreground
        );


        button.setBackground(
                background
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


        return button;
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
                38
        );


        table.setFillsViewportHeight(
                true
        );


        table.setSelectionMode(
                ListSelectionModel
                        .SINGLE_SELECTION
        );


        table.setShowVerticalLines(
                false
        );


        table.setGridColor(
                BORDER_COLOR
        );


        table.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
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
                        SECONDARY_COLOR
                );


        table.getTableHeader()
                .setForeground(
                        TEXT_COLOR
                );


        table.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                42
                        )
                );
    }
}