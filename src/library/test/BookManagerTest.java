package library.test;

import library.datastructure.BookBST;
import library.manager.BookManager;
import library.model.Book;

public class BookManagerTest {

    public static void main(String[] args) {

        // Create Member 1's BST
        BookBST bookBST = new BookBST();

        // Create your Book Manager
        BookManager bookManager = new BookManager(bookBST);

        System.out.println("===== BOOK MANAGEMENT TEST =====");

        // 1. ADD BOOKS
        Book book1 = new Book(
                101,
                "Java Programming",
                "James Gosling",
                "Programming",
                2020,
                true
        );

        Book book2 = new Book(
                50,
                "Data Structures",
                "Mark Allen",
                "Computer Science",
                2019,
                true
        );

        Book book3 = new Book(
                200,
                "Algorithms",
                "Thomas Cormen",
                "Computer Science",
                2022,
                true
        );

        System.out.println("\n--- Adding Books ---");

        System.out.println("Book 101 added: "
                + bookManager.addBook(book1));

        System.out.println("Book 50 added: "
                + bookManager.addBook(book2));

        System.out.println("Book 200 added: "
                + bookManager.addBook(book3));

        // 2. DISPLAY BOOKS
        System.out.println("\n--- All Books (ID Order) ---");

        for (Book book : bookManager.getAllBooks()) {
            System.out.println(book);
        }

        // 3. SEARCH
        System.out.println("\n--- Search Book ---");

        Book foundBook = bookManager.searchBook(101);

        if (foundBook != null) {
            System.out.println("Found: " + foundBook);
        } else {
            System.out.println("Book not found.");
        }

        // 4. UPDATE
        System.out.println("\n--- Update Book ---");

        boolean updated = bookManager.updateBook(
                101,
                "Advanced Java Programming",
                "James Gosling",
                "Programming",
                2024
        );

        System.out.println("Update successful: " + updated);

        System.out.println("Updated Book:");
        System.out.println(bookManager.searchBook(101));

        // 5. DELETE
        System.out.println("\n--- Delete Book ---");

        boolean deleted = bookManager.deleteBook(50);

        System.out.println("Delete successful: " + deleted);

        // 6. DISPLAY AFTER DELETE
        System.out.println("\n--- Books After Delete ---");

        for (Book book : bookManager.getAllBooks()) {
            System.out.println(book);
        }

        // 7. MINIMUM AND MAXIMUM
        System.out.println("\n--- Minimum and Maximum ---");

        System.out.println("Minimum ID Book: "
                + bookManager.getMinimumBook());

        System.out.println("Maximum ID Book: "
                + bookManager.getMaximumBook());

        // 8. HEIGHT
        System.out.println("\n--- Tree Information ---");

        System.out.println("Tree Height: "
                + bookManager.getBookTreeHeight());

        System.out.println("Is Empty: "
                + bookManager.isEmpty());

        System.out.println("\n===== TEST COMPLETE =====");
    }
}