package library.ui;

import library.manager.MemberManager;
import library.model.Member;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberDashboard extends JFrame {


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

    private static final Color DELETE_COLOR =
            new Color(220, 38, 38);

    private static final Color DELETE_HOVER =
            new Color(185, 28, 28);

    private static final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private static final Color SUBTEXT_COLOR =
            new Color(107, 114, 128);

    private static final Color BORDER_COLOR =
            new Color(229, 231, 235);

    private static final Color SECONDARY_COLOR =
            new Color(249, 250, 251);

    private static final Color SECONDARY_HOVER =
            new Color(243, 244, 246);




    private final MemberManager memberManager;



    // FORM FIELDS
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;




    private JTable memberTable;
    private DefaultTableModel tableModel;


    private JLabel memberCountLabel;
    private JLabel statusLabel;

    public MemberDashboard(MemberManager memberManager) {

        this.memberManager = memberManager;

        setTitle("Smart Library - Member Management");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setMinimumSize(
                new Dimension(1100, 700)
        );

        createUI();

        refreshMemberTable();
    }


    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);


        JPanel headerPanel =
                new JPanel(new BorderLayout());

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


        JPanel headerTextPanel =
                new JPanel();

        headerTextPanel.setOpaque(false);

        headerTextPanel.setLayout(
                new BoxLayout(
                        headerTextPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel titleLabel =
                new JLabel(
                        "Member Management"
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
                        "Manage library members and membership information"
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


        headerTextPanel.add(titleLabel);

        headerTextPanel.add(
                Box.createVerticalStrut(4)
        );

        headerTextPanel.add(
                subtitleLabel
        );


        headerPanel.add(
                headerTextPanel,
                BorderLayout.WEST
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                18,
                                18
                        )
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        18,
                        30
                )
        );


        JPanel statsPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        statsPanel.setOpaque(false);

        statsPanel.setPreferredSize(
                new Dimension(
                        0,
                        82
                )
        );


        JPanel totalMembersCard =
                createTotalMembersCard();

        statsPanel.add(
                totalMembersCard
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


        JPanel formCard =
                createCard();

        formCard.setPreferredSize(
                new Dimension(
                        430,
                        0
                )
        );

        formCard.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );


        JLabel formTitle =
                new JLabel(
                        "Member Information"
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


        formCard.add(
                formTitle,
                BorderLayout.NORTH
        );



        // FORM FIELDS
        JPanel fieldsPanel =
                new JPanel();

        fieldsPanel.setOpaque(false);

        fieldsPanel.setLayout(
                new BoxLayout(
                        fieldsPanel,
                        BoxLayout.Y_AXIS
                )
        );



        JLabel idLabel =
                createFieldLabel(
                        "Member ID"
                );

        idLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(idLabel);

        fieldsPanel.add(
                Box.createVerticalStrut(5)
        );


        idField =
                createTextField();

        idField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(idField);


        fieldsPanel.add(
                Box.createVerticalStrut(12)
        );

        JLabel nameLabel =
                createFieldLabel(
                        "Name"
                );

        nameLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(nameLabel);

        fieldsPanel.add(
                Box.createVerticalStrut(5)
        );


        nameField =
                createTextField();

        nameField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(nameField);


        fieldsPanel.add(
                Box.createVerticalStrut(12)
        );


        JLabel emailLabel =
                createFieldLabel(
                        "Email"
                );

        emailLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(emailLabel);

        fieldsPanel.add(
                Box.createVerticalStrut(5)
        );


        emailField =
                createTextField();

        emailField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(emailField);


        fieldsPanel.add(
                Box.createVerticalStrut(12)
        );


        JLabel phoneLabel =
                createFieldLabel(
                        "Phone Number"
                );

        phoneLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(phoneLabel);

        fieldsPanel.add(
                Box.createVerticalStrut(5)
        );


        phoneField =
                createTextField();

        phoneField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        fieldsPanel.add(phoneField);



        JPanel fieldsWrapper =
                new JPanel(
                        new BorderLayout()
                );

        fieldsWrapper.setOpaque(false);

        fieldsWrapper.add(
                fieldsPanel,
                BorderLayout.NORTH
        );


        formCard.add(
                fieldsWrapper,
                BorderLayout.CENTER
        );


        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                8
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.setPreferredSize(
                new Dimension(
                        0,
                        108
                )
        );


        JButton addButton =
                createPrimaryButton(
                        "Add Member"
                );


        JButton searchButton =
                createPrimaryButton(
                        "Search"
                );


        JButton updateButton =
                createPrimaryButton(
                        "Update"
                );


        JButton deleteButton =
                createDeleteButton(
                        "Delete"
                );


        JButton clearButton =
                createSecondaryButton(
                        "Clear"
                );


        JButton showAllButton =
                createSecondaryButton(
                        "Show All"
                );


        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        buttonPanel.add(clearButton);
        buttonPanel.add(showAllButton);


        formCard.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        centerPanel.add(
                formCard,
                BorderLayout.WEST
        );



        JPanel tableCard =
                createCard();

        tableCard.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );


        JPanel tableHeaderPanel =
                new JPanel();

        tableHeaderPanel.setOpaque(false);

        tableHeaderPanel.setLayout(
                new BoxLayout(
                        tableHeaderPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel tableTitle =
                new JLabel(
                        "Library Members"
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
                        "Select a row to view or edit member information"
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


        tableHeaderPanel.add(
                tableTitle
        );

        tableHeaderPanel.add(
                Box.createVerticalStrut(4)
        );

        tableHeaderPanel.add(
                tableSubtitle
        );


        tableCard.add(
                tableHeaderPanel,
                BorderLayout.NORTH
        );


        String[] columns = {
                "Member ID",
                "Name",
                "Email",
                "Phone"
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


        memberTable =
                new JTable(
                        tableModel
                );


        memberTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        memberTable.setRowHeight(
                36
        );

        memberTable.setSelectionMode(
                ListSelectionModel
                        .SINGLE_SELECTION
        );

        memberTable.setFillsViewportHeight(
                true
        );

        memberTable.setShowVerticalLines(
                false
        );

        memberTable.setShowHorizontalLines(
                true
        );

        memberTable.setGridColor(
                BORDER_COLOR
        );

        memberTable.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );

        memberTable.setSelectionForeground(
                TEXT_COLOR
        );

        memberTable.setAutoCreateRowSorter(
                true
        );


        memberTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );


        memberTable
                .getTableHeader()
                .setBackground(
                        SECONDARY_COLOR
                );


        memberTable
                .getTableHeader()
                .setForeground(
                        TEXT_COLOR
                );


        memberTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        memberTable
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


        centerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );


        contentPanel.add(
                centerPanel,
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



        addButton.addActionListener(
                e -> addMember()
        );


        searchButton.addActionListener(
                e -> searchMember()
        );


        updateButton.addActionListener(
                e -> updateMember()
        );


        deleteButton.addActionListener(
                e -> deleteMember()
        );


        clearButton.addActionListener(
                e -> clearFields()
        );


        showAllButton.addActionListener(
                e -> {

                    refreshMemberTable();

                    clearFields();

                    setStatus(
                            "Showing all members."
                    );
                }
        );



        memberTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (e.getValueIsAdjusting()) {
                                return;
                            }


                            int viewRow =
                                    memberTable
                                            .getSelectedRow();


                            if (viewRow < 0) {
                                return;
                            }


                            int modelRow =
                                    memberTable
                                            .convertRowIndexToModel(
                                                    viewRow
                                            );


                            idField.setText(
                                    tableModel
                                            .getValueAt(
                                                    modelRow,
                                                    0
                                            )
                                            .toString()
                            );


                            nameField.setText(
                                    tableModel
                                            .getValueAt(
                                                    modelRow,
                                                    1
                                            )
                                            .toString()
                            );


                            emailField.setText(
                                    tableModel
                                            .getValueAt(
                                                    modelRow,
                                                    2
                                            )
                                            .toString()
                            );


                            phoneField.setText(
                                    tableModel
                                            .getValueAt(
                                                    modelRow,
                                                    3
                                            )
                                            .toString()
                            );


                            setStatus(
                                    "Member selected."
                            );
                        }
                );
    }


    private void addMember() {

        try {

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );


            String name =
                    nameField
                            .getText()
                            .trim();


            String email =
                    emailField
                            .getText()
                            .trim();


            String phone =
                    phoneField
                            .getText()
                            .trim();


            Member member =
                    new Member(
                            id,
                            name,
                            email,
                            phone
                    );


            boolean success =
                    memberManager
                            .addMember(
                                    member
                            );


            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member added successfully.",
                        "Member Management",
                        JOptionPane.INFORMATION_MESSAGE
                );


                refreshMemberTable();

                clearFields();

                setStatus(
                        "Member added successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member could not be added.\n\n"
                                + "Please check:\n"
                                + "- Member ID must be greater than 0\n"
                                + "- Member ID must be unique\n"
                                + "- Name cannot be empty\n"
                                + "- Email must be valid\n"
                                + "- Phone number must contain exactly 10 digits",
                        "Add Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Member ID must be a valid number.",
                    "Invalid Member ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    private void searchMember() {

        try {

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );


            Member member =
                    memberManager
                            .searchMember(
                                    id
                            );


            if (member != null) {

                nameField.setText(
                        member.getName()
                );


                emailField.setText(
                        member.getEmail()
                );


                phoneField.setText(
                        member.getPhone()
                );


                selectMemberInTable(
                        id
                );


                setStatus(
                        "Member ID "
                                + id
                                + " found."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found.",
                        "Search Member",
                        JOptionPane.INFORMATION_MESSAGE
                );


                setStatus(
                        "Member not found."
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Member ID.",
                    "Invalid Member ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void updateMember() {

        try {

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );


            String name =
                    nameField
                            .getText()
                            .trim();


            String email =
                    emailField
                            .getText()
                            .trim();


            String phone =
                    phoneField
                            .getText()
                            .trim();


            boolean success =
                    memberManager
                            .updateMember(
                                    id,
                                    name,
                                    email,
                                    phone
                            );


            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member updated successfully.",
                        "Member Management",
                        JOptionPane.INFORMATION_MESSAGE
                );


                refreshMemberTable();

                clearFields();

                setStatus(
                        "Member updated successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member could not be updated.\n\n"
                                + "Check Member ID, email and phone number.",
                        "Update Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Member ID must be a valid number.",
                    "Invalid Member ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void deleteMember() {

        try {

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );


            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete member ID "
                                    + id
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );


            if (confirmation
                    != JOptionPane.YES_OPTION) {

                return;
            }


            boolean success =
                    memberManager
                            .deleteMember(
                                    id
                            );


            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member deleted successfully.",
                        "Member Management",
                        JOptionPane.INFORMATION_MESSAGE
                );


                refreshMemberTable();

                clearFields();

                setStatus(
                        "Member deleted successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found.",
                        "Delete Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Member ID.",
                    "Invalid Member ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
    private void refreshMemberTable() {

        tableModel.setRowCount(0);


        for (Member member :
                memberManager.getAllMembers()) {

            tableModel.addRow(
                    new Object[]{
                            member.getMemberId(),
                            member.getName(),
                            member.getEmail(),
                            member.getPhone()
                    }
            );
        }


        updateMemberCount();
    }

    private void updateMemberCount() {

        memberCountLabel.setText(
                String.valueOf(
                        memberManager
                                .getMemberCount()
                )
        );
    }


    private void selectMemberInTable(
            int memberId
    ) {

        for (int modelRow = 0;
             modelRow < tableModel.getRowCount();
             modelRow++) {


            int id =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            modelRow,
                                            0
                                    )
                                    .toString()
                    );


            if (id == memberId) {

                int viewRow =
                        memberTable
                                .convertRowIndexToView(
                                        modelRow
                                );


                if (viewRow >= 0) {

                    memberTable
                            .setRowSelectionInterval(
                                    viewRow,
                                    viewRow
                            );


                    memberTable
                            .scrollRectToVisible(
                                    memberTable
                                            .getCellRect(
                                                    viewRow,
                                                    0,
                                                    true
                                            )
                            );
                }


                break;
            }
        }
    }


    private void clearFields() {

        idField.setText("");

        nameField.setText("");

        emailField.setText("");

        phoneField.setText("");


        memberTable.clearSelection();

        idField.requestFocusInWindow();

        setStatus("Ready");
    }


    private void setStatus(String message) {

        statusLabel.setText(
                message
        );
    }


    private JPanel createCard() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                CARD_COLOR
        );


        panel.setBorder(
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


        return panel;
    }

    private JPanel createTotalMembersCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );


        card.setPreferredSize(
                new Dimension(
                        250,
                        80
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
                                12,
                                18,
                                12,
                                18
                        )
                )
        );


        JLabel title =
                new JLabel(
                        "Total Members"
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


        memberCountLabel =
                new JLabel(
                        "0"
                );


        memberCountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );


        memberCountLabel.setForeground(
                TEXT_COLOR
        );


        card.add(
                title,
                BorderLayout.NORTH
        );


        card.add(
                memberCountLabel,
                BorderLayout.CENTER
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

        Dimension size =
                new Dimension(
                        360,
                        38
                );


        field.setPreferredSize(size);

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        38
                )
        );


        field.setMinimumSize(
                new Dimension(
                        150,
                        38
                )
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );


        return field;
    }



    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        styleButton(
                button,
                PRIMARY_COLOR,
                PRIMARY_HOVER,
                Color.WHITE
        );


        return button;
    }



    private JButton createDeleteButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        styleButton(
                button,
                DELETE_COLOR,
                DELETE_HOVER,
                Color.WHITE
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


        button.setFocusPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);


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



    private void styleButton(
            JButton button,
            Color normalColor,
            Color hoverColor,
            Color textColor
    ) {

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        button.setForeground(
                textColor
        );


        button.setBackground(
                normalColor
        );


        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);


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
    }
}