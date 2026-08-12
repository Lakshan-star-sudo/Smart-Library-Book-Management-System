package library.model;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private String category;
    private int publishedYear;
    private boolean available;

    public Book(int bookId, String title, String author,
                String category, int publishedYear, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishedYear = publishedYear;
        this.available = available;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId +
                ", Title: " + title +
                ", Author: " + author +
                ", Category: " + category +
                ", Year: " + publishedYear +
                ", Available: " + available;
    }
}



