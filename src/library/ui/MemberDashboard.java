package library.ui;

import library.manager.MemberManager;
import library.model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MemberDashboard extends JFrame {

    private final MemberManager memberManager;

    private DefaultTableModel tableModel;
    private JTable memberTable;

    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    public MemberDashboard(MemberManager memberManager) {

        this.memberManager = memberManager;

        setTitle("Smart Library - Member Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initializeUI();
        refreshMemberTable();
    }

    private void initializeUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 15, 20
                )
        );

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "Member Management"
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Member Information"
                )
        );

        idField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();

        formPanel.add(new JLabel("Member ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);


        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        10,
                        5
                )
        );

        JButton addButton =
                new JButton("Add Member");

        JButton searchButton =
                new JButton("Search");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");

        JButton refreshButton =
                new JButton("Show All");

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);


        // =========================
        // TOP SECTION
        // =========================

        JPanel topSection = new JPanel(
                new BorderLayout(10, 10)
        );

        topSection.add(
                formPanel,
                BorderLayout.CENTER
        );

        topSection.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =========================
        // MEMBER TABLE
        // =========================

        String[] columns = {
                "Member ID",
                "Name",
                "Email",
                "Phone"
        };

        tableModel = new DefaultTableModel(
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

        memberTable.setRowHeight(28);

        JScrollPane scrollPane =
                new JScrollPane(memberTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Members"
                )
        );


        // =========================
        // CENTER CONTENT
        // =========================

        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        centerPanel.add(
                topSection,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =========================
        // BUTTON ACTIONS
        // =========================

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

        refreshButton.addActionListener(
                e -> refreshMemberTable()
        );


        // =========================
        // TABLE ROW SELECTION
        // =========================

        memberTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                memberTable.getSelectedRow();

                        if (row >= 0) {

                            idField.setText(
                                    memberTable
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            nameField.setText(
                                    memberTable
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            emailField.setText(
                                    memberTable
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            phoneField.setText(
                                    memberTable
                                            .getValueAt(row, 3)
                                            .toString()
                            );
                        }
                    }
                });


        // =========================
        // ADD TO FRAME
        // =========================

        add(mainPanel);
    }


    // =========================
    // ADD MEMBER
    // =========================

    private void addMember() {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            String name =
                    nameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            Member member = new Member(
                    id,
                    name,
                    email,
                    phone
            );

            boolean success =
                    memberManager.addMember(member);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member added successfully."
                );

                refreshMemberTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member could not be added.\n"
                                + "Check the information or duplicate ID.",
                        "Add Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Member ID must be a number.",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    // =========================
    // SEARCH MEMBER
    // =========================

    private void searchMember() {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            Member member =
                    memberManager.searchMember(id);

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

                JOptionPane.showMessageDialog(
                        this,
                        "Member found."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found."
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Member ID.",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    // =========================
    // UPDATE MEMBER
    // =========================

    private void updateMember() {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            String name =
                    nameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            boolean success =
                    memberManager.updateMember(
                            id,
                            name,
                            email,
                            phone
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member updated successfully."
                );

                refreshMemberTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member could not be updated.",
                        "Update Member",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Member ID must be a number.",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    // =========================
    // DELETE MEMBER
    // =========================

    private void deleteMember() {

        try {

            int id = Integer.parseInt(
                    idField.getText().trim()
            );

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete member with ID "
                                    + id + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirmation != JOptionPane.YES_OPTION) {
                return;
            }

            boolean success =
                    memberManager.deleteMember(id);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member deleted successfully."
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

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Member ID.",
                    "Invalid ID",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    // =========================
    // REFRESH TABLE
    // =========================

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
    }


    // =========================
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");

        memberTable.clearSelection();
    }
}