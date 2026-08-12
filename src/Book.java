public class Book {

    private String bookId;
    private String title;
    private String author;
    private String category;
    private int publishedYear;
    private boolean available;

    public Book(String bookId, String title, String author,
                String category, int publishedYear) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishedYear = publishedYear;
        this.available = true;
    }

    public String getBookId() {
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

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return bookId + " | " +
                title + " | " +
                author + " | " +
                category + " | " +
                publishedYear +
                " | Available: " + available;
    }
}