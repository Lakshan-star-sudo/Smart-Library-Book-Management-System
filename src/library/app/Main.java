package library.app;

import library.datastructure.BookBST;
import library.manager.BookManager;
import library.manager.MemberManager;
import library.model.Book;
import library.model.Member;
import library.ui.BSTDashboard;
import library.ui.MemberDashboard;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        // =========================
        // CREATE DATA STRUCTURES
        // =========================

        BookBST tree = new BookBST();

        // =========================
        // CREATE MANAGERS
        // =========================

        BookManager bookManager = new BookManager(tree);
        MemberManager memberManager = new MemberManager();

        // =========================
        // TEMPORARY SAMPLE MEMBER DATA
        // =========================

        memberManager.addMember(new Member(
                1,
                "John Silva",
                "john@gmail.com",
                "0712345678"
        ));

        memberManager.addMember(new Member(
                2,
                "Sarah Perera",
                "sarah@gmail.com",
                "0723456789"
        ));

        memberManager.addMember(new Member(
                3,
                "David Fernando",
                "david@gmail.com",
                "0774567890"
        ));

        // =========================
        // TEMPORARY SAMPLE BOOK DATA
        // =========================

        tree.insert(new Book(
                50,
                "Data Structures",
                "John Smith",
                "Computing",
                2024,
                true
        ));

        tree.insert(new Book(
                30,
                "Java Programming",
                "David Brown",
                "Programming",
                2022,
                true
        ));

        tree.insert(new Book(
                70,
                "Database Systems",
                "Sarah Lee",
                "Database",
                2023,
                true
        ));

        tree.insert(new Book(
                20,
                "Computer Networks",
                "James Wilson",
                "Networking",
                2021,
                true
        ));

        tree.insert(new Book(
                40,
                "Algorithms",
                "Robert Martin",
                "Computing",
                2020,
                true
        ));

        tree.insert(new Book(
                60,
                "Software Engineering",
                "Ian Sommerville",
                "Software Engineering",
                2023,
                true
        ));

        tree.insert(new Book(
                80,
                "Artificial Intelligence",
                "Peter Norvig",
                "AI",
                2024,
                true
        ));

        // =========================
        // START GUI
        // =========================

        SwingUtilities.invokeLater(() -> {

            // BST / Book Dashboard
            BSTDashboard dashboard =
                    new BSTDashboard(tree);

            dashboard.setVisible(true);

            // Member Management Dashboard
            MemberDashboard memberDashboard =
                    new MemberDashboard(memberManager);

            memberDashboard.setVisible(true);
        });
    }
}