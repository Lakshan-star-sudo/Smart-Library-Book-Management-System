package library.ui;

import library.manager.MemberManager;
import library.model.Member;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MemberDashboard extends JFrame {

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color HEADER_COLOR =
            new Color(31, 41, 55);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color PRIMARY_COLOR =
            new Color(37, 99, 235);

    private static final Color DELETE_COLOR =
            new Color(220, 38, 38);

    private static final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private static final Color SUBTEXT_COLOR =
            new Color(107, 114, 128);

    private static final Color BORDER_COLOR =
            new Color(229, 231, 235);

    private static final Color SECONDARY_COLOR =
            new Color(249, 250, 251);


    private final MemberManager memberManager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;

    private JTable memberTable;
    private DefaultTableModel tableModel;

    private JLabel memberCountLabel;
    private JLabel statusLabel;


    public MemberDashboard(
            MemberManager memberManager
    ) {

        this.memberManager = memberManager;

        setTitle(
                "Smart Library - Member Management"
        );

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

        JPanel root =
                new JPanel(new BorderLayout());

        root.setBackground(BACKGROUND);


        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(HEADER_COLOR);

        header.setBorder(
                new EmptyBorder(
                        20, 30, 20, 30
                )
        );

        JPanel headerText = new JPanel();

        headerText.setOpaque(false);

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Member Management");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(Color.WHITE);

        JLabel subtitle =
                new JLabel(
                        "Manage members and customer login accounts"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                new Color(209, 213, 219)
        );

        headerText.add(title);
        headerText.add(Box.createVerticalStrut(4));
        headerText.add(subtitle);

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
                                18,
                                18
                        )
                );

        content.setBackground(BACKGROUND);

        content.setBorder(
                new EmptyBorder(
                        18, 30, 18, 30
                )
        );


        JPanel top =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        top.setOpaque(false);

        top.setPreferredSize(
                new Dimension(0, 76)
        );

        top.add(createMemberCountCard());

        content.add(
                top,
                BorderLayout.NORTH
        );



        JPanel center =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );

        center.setOpaque(false);


        JPanel formCard =
                createCard();

        formCard.setPreferredSize(
                new Dimension(430, 0)
        );

        formCard.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );

        JLabel formTitle =
                new JLabel("Member Information");

        formTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        formTitle.setForeground(TEXT_COLOR);

        formCard.add(
                formTitle,
                BorderLayout.NORTH
        );


        JPanel fields =
                new JPanel(
                        new GridBagLayout()
                );

        fields.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        idField = createTextField();
        addField(
                fields,
                gbc,
                row++,
                "Member ID",
                idField
        );

        nameField = createTextField();
        addField(
                fields,
                gbc,
                row++,
                "Name",
                nameField
        );

        emailField = createTextField();
        addField(
                fields,
                gbc,
                row++,
                "Email",
                emailField
        );

        phoneField = createTextField();
        addField(
                fields,
                gbc,
                row++,
                "Phone Number",
                phoneField
        );

        passwordField =
                createPasswordField();

        addField(
                fields,
                gbc,
                row,
                "Login Password",
                passwordField
        );


        JPanel fieldsWrapper =
                new JPanel(
                        new BorderLayout()
                );

        fieldsWrapper.setOpaque(false);

        fieldsWrapper.add(
                fields,
                BorderLayout.NORTH
        );

        formCard.add(
                fieldsWrapper,
                BorderLayout.CENTER
        );


        JPanel buttons =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                8
                        )
                );

        buttons.setOpaque(false);

        buttons.setPreferredSize(
                new Dimension(0, 105)
        );

        JButton addButton =
                createButton(
                        "Add Member",
                        PRIMARY_COLOR,
                        Color.WHITE
                );

        JButton searchButton =
                createButton(
                        "Search",
                        PRIMARY_COLOR,
                        Color.WHITE
                );

        JButton updateButton =
                createButton(
                        "Update",
                        PRIMARY_COLOR,
                        Color.WHITE
                );

        JButton deleteButton =
                createButton(
                        "Delete",
                        DELETE_COLOR,
                        Color.WHITE
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        SECONDARY_COLOR,
                        TEXT_COLOR
                );

        JButton showAllButton =
                createButton(
                        "Show All",
                        SECONDARY_COLOR,
                        TEXT_COLOR
                );

        buttons.add(addButton);
        buttons.add(searchButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(clearButton);
        buttons.add(showAllButton);

        formCard.add(
                buttons,
                BorderLayout.SOUTH
        );

        center.add(
                formCard,
                BorderLayout.WEST
        );

        JPanel tableCard = createCard();

        tableCard.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );

        JPanel tableHeading = new JPanel();

        tableHeading.setOpaque(false);

        tableHeading.setLayout(
                new BoxLayout(
                        tableHeading,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel tableTitle =
                new JLabel("Library Members");

        tableTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        tableTitle.setForeground(TEXT_COLOR);

        JLabel tableSubtitle =
                new JLabel(
                        "Passwords are hidden for security"
                );

        tableSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tableSubtitle.setForeground(SUBTEXT_COLOR);

        tableHeading.add(tableTitle);
        tableHeading.add(Box.createVerticalStrut(4));
        tableHeading.add(tableSubtitle);

        tableCard.add(
                tableHeading,
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

        memberTable = new JTable(tableModel);

        memberTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        memberTable.setRowHeight(36);
        memberTable.setFillsViewportHeight(true);
        memberTable.setShowVerticalLines(false);
        memberTable.setGridColor(BORDER_COLOR);
        memberTable.setAutoCreateRowSorter(true);

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
                .setBackground(SECONDARY_COLOR);

        memberTable
                .getTableHeader()
                .setForeground(TEXT_COLOR);

        memberTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(0, 40)
                );

        JScrollPane scrollPane =
                new JScrollPane(memberTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        center.add(
                tableCard,
                BorderLayout.CENTER
        );

        content.add(
                center,
                BorderLayout.CENTER
        );


        statusLabel = new JLabel("Ready");

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        statusLabel.setForeground(SUBTEXT_COLOR);

        content.add(
                statusLabel,
                BorderLayout.SOUTH
        );

        root.add(
                content,
                BorderLayout.CENTER
        );

        setContentPane(root);



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
                    statusLabel.setText(
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
                                    memberTable.getSelectedRow();

                            if (viewRow < 0) {
                                return;
                            }

                            int rowIndex =
                                    memberTable
                                            .convertRowIndexToModel(
                                                    viewRow
                                            );

                            idField.setText(
                                    tableModel
                                            .getValueAt(
                                                    rowIndex,
                                                    0
                                            )
                                            .toString()
                            );

                            nameField.setText(
                                    tableModel
                                            .getValueAt(
                                                    rowIndex,
                                                    1
                                            )
                                            .toString()
                            );

                            emailField.setText(
                                    tableModel
                                            .getValueAt(
                                                    rowIndex,
                                                    2
                                            )
                                            .toString()
                            );

                            phoneField.setText(
                                    tableModel
                                            .getValueAt(
                                                    rowIndex,
                                                    3
                                            )
                                            .toString()
                            );

                            passwordField.setText("");

                            statusLabel.setText(
                                    "Member selected. "
                                            + "Leave password blank to keep the current password."
                            );
                        }
                );
    }


    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JComponent field
    ) {

        gbc.gridy = row * 2;
        gbc.insets =
                new Insets(
                        row == 0 ? 0 : 5,
                        0,
                        4,
                        0
                );

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(TEXT_COLOR);

        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        3,
                        0
                );

        panel.add(field, gbc);
    }


    private void addMember() {

        try {

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            Member member =
                    new Member(
                            id,
                            nameField.getText().trim(),
                            emailField.getText().trim(),
                            phoneField.getText().trim(),
                            password
                    );

            if (memberManager.addMember(member)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member added successfully.\n"
                                + "The member can now login using\n"
                                + "Member ID or Email + Password.",
                        "Member Added",
                        JOptionPane.INFORMATION_MESSAGE
                );

                refreshMemberTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member could not be added.\n\n"
                                + "Check:\n"
                                + "- Member ID must be unique and greater than 0\n"
                                + "- Email must be valid and unique\n"
                                + "- Phone must contain exactly 10 digits\n"
                                + "- Password must contain at least 6 characters",
                        "Add Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

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
                    memberManager.searchMember(id);

            if (member == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found.",
                        "Search Member",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            nameField.setText(member.getName());
            emailField.setText(member.getEmail());
            phoneField.setText(member.getPhone());

            // Never show stored password
            passwordField.setText("");

            selectMemberInTable(id);

            if (member.getPassword() == null
                    || member.getPassword().isEmpty()) {

                statusLabel.setText(
                        "Old member account: enter a new password and click Update."
                );

            } else {

                statusLabel.setText(
                        "Member found. Leave password blank to keep current password."
                );
            }

        } catch (NumberFormatException e) {

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

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            boolean success =
                    memberManager.updateMember(
                            id,
                            nameField.getText().trim(),
                            emailField.getText().trim(),
                            phoneField.getText().trim(),
                            password
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member updated successfully.",
                        "Update Member",
                        JOptionPane.INFORMATION_MESSAGE
                );

                refreshMemberTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member could not be updated.\n\n"
                                + "Check email, phone and password.\n"
                                + "Old members without a password must be given a password.",
                        "Update Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

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

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete member ID " + id + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            if (memberManager.deleteMember(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member deleted successfully.",
                        "Delete Member",
                        JOptionPane.INFORMATION_MESSAGE
                );

                refreshMemberTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found.",
                        "Delete Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

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

        memberCountLabel.setText(
                String.valueOf(
                        memberManager.getMemberCount()
                )
        );
    }


    private void selectMemberInTable(int memberId) {

        for (int row = 0;
             row < tableModel.getRowCount();
             row++) {

            int id =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(row, 0)
                                    .toString()
                    );

            if (id == memberId) {

                int viewRow =
                        memberTable
                                .convertRowIndexToView(row);

                memberTable.setRowSelectionInterval(
                        viewRow,
                        viewRow
                );

                break;
            }
        }
    }


    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");

        memberTable.clearSelection();

        statusLabel.setText("Ready");

        idField.requestFocusInWindow();
    }


    private JPanel createCard() {

        JPanel panel = new JPanel();

        panel.setBackground(CARD_COLOR);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
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

        return panel;
    }


    private JPanel createMemberCountCard() {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setPreferredSize(
                new Dimension(250, 72)
        );

        card.setBackground(CARD_COLOR);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                10,
                                18,
                                10,
                                18
                        )
                )
        );

        JLabel title =
                new JLabel("Total Members");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        title.setForeground(SUBTEXT_COLOR);

        memberCountLabel = new JLabel("0");

        memberCountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        memberCountLabel.setForeground(TEXT_COLOR);

        card.add(title, BorderLayout.NORTH);
        card.add(
                memberCountLabel,
                BorderLayout.CENTER
        );

        return card;
    }


    private JTextField createTextField() {

        JTextField field = new JTextField();

        styleInput(field);

        return field;
    }


    private JPasswordField createPasswordField() {

        JPasswordField field =
                new JPasswordField();

        styleInput(field);

        return field;
    }


    private void styleInput(JTextField field) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(360, 34)
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
    }


    private JButton createButton(
            String text,
            Color background,
            Color foreground
    ) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(foreground);
        button.setBackground(background);

        // IMPORTANT - Windows Look & Feel fix
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}