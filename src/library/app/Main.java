package library.app;

import library.datastructure.BookBST;
import library.model.Book;
import library.ui.BSTDashboard;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        BookBST tree = new BookBST();

        // Temporary sample data for BST/UI testing.
        // This will be replaced during full system integration.
        tree.insert(new Book(50, "Data Structures", "John Smith",
                "Computing", 2024, true));

        tree.insert(new Book(30, "Java Programming", "David Brown",
                "Programming", 2022, true));

        tree.insert(new Book(70, "Database Systems", "Sarah Lee",
                "Database", 2023, true));

        tree.insert(new Book(20, "Computer Networks", "James Wilson",
                "Networking", 2021, true));

        tree.insert(new Book(40, "Algorithms", "Robert Martin",
                "Computing", 2020, true));

        tree.insert(new Book(60, "Software Engineering", "Ian Sommerville",
                "Software Engineering", 2023, true));

        tree.insert(new Book(80, "Artificial Intelligence", "Peter Norvig",
                "AI", 2024, true));

        SwingUtilities.invokeLater(() -> {
            BSTDashboard dashboard = new BSTDashboard(tree);
            dashboard.setVisible(true);
        });
    }
}


