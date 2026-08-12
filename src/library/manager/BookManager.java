package library.manager;

import library.datastructure.BookBST;
import library.model.Book;

import java.util.List;

public class BookManager {

    private final BookBST bookBST;

    public BookManager(BookBST bookBST) {
        this.bookBST = bookBST;
    }

    // Add Book
    public boolean addBook(Book book) {

        if (book == null) {
            return false;
        }

        if (book.getBookId() <= 0) {
            return false;
        }

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            return false;
        }

        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            return false;
        }

        if (book.getCategory() == null || book.getCategory().trim().isEmpty()) {
            return false;
        }

        if (book.getPublishedYear() < 1000 ||
                book.getPublishedYear() > 2026) {
            return false;
        }

        // BST will return false if the Book ID already exists
        return bookBST.insert(book);
    }

    // Search Book
    public Book searchBook(int bookId) {
        return bookBST.search(bookId);
    }

    // Update Book
    public boolean updateBook(int bookId,
                              String title,
                              String author,
                              String category,
                              int publishedYear) {

        if (bookId <= 0) {
            return false;
        }

        if (title == null || title.trim().isEmpty()) {
            return false;
        }

        if (author == null || author.trim().isEmpty()) {
            return false;
        }

        if (category == null || category.trim().isEmpty()) {
            return false;
        }

        if (publishedYear < 1000 || publishedYear > 2026) {
            return false;
        }

        Book book = bookBST.search(bookId);

        if (book == null) {
            return false;
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setPublishedYear(publishedYear);

        return true;
    }

    // Delete Book
    public boolean deleteBook(int bookId) {
        return bookBST.delete(bookId);
    }

    // Get all books in ID order
    public List<Book> getAllBooks() {
        return bookBST.inorderTraversal();
    }

    // Get books using preorder traversal
    public List<Book> getPreorderBooks() {
        return bookBST.preorderTraversal();
    }

    // Get books using postorder traversal
    public List<Book> getPostorderBooks() {
        return bookBST.postorderTraversal();
    }

    // Get book with minimum ID
    public Book getMinimumBook() {
        return bookBST.findMin();
    }

    // Get book with maximum ID
    public Book getMaximumBook() {
        return bookBST.findMax();
    }

    // Get tree height
    public int getBookTreeHeight() {
        return bookBST.getHeight();
    }

    // Check whether the book collection is empty
    public boolean isEmpty() {
        return bookBST.isEmpty();
    }
}