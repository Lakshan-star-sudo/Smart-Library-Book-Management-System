import java.time.LocalDate;
import java.util.ArrayList;

public class BorrowingManager {

    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<BorrowingRecord> records;

    public BorrowingManager() {

        books = new ArrayList<>();
        members = new ArrayList<>();
        records = new ArrayList<>();
    }

    // ---------------- BOOK MANAGEMENT ----------------

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBook(String bookId) {

        for (Book book : books) {

            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }

        return null;
    }

    public boolean checkAvailability(String bookId) {

        Book book = findBook(bookId);

        if (book == null) {
            return false;
        }

        return book.isAvailable();
    }

    // ---------------- MEMBER MANAGEMENT ----------------

    public void addMember(Member member) {
        members.add(member);
    }

    public Member findMember(String memberId) {

        for (Member member : members) {

            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }

        return null;
    }

    // ---------------- BORROW BOOK ----------------

    public String borrowBook(String memberId, String bookId) {

        if (memberId == null || memberId.trim().isEmpty()) {
            return "Please enter Member ID.";
        }

        if (bookId == null || bookId.trim().isEmpty()) {
            return "Please enter Book ID.";
        }

        Member member = findMember(memberId);

        if (member == null) {
            return "Member not found.";
        }

        Book book = findBook(bookId);

        if (book == null) {
            return "Book not found.";
        }

        if (!book.isAvailable()) {
            return "Book is not available.";
        }

        book.setAvailable(false);

        BorrowingRecord record =
                new BorrowingRecord(
                        memberId,
                        bookId,
                        LocalDate.now().toString()
                );

        records.add(record);

        return "Book borrowed successfully.";
    }

    // ---------------- RETURN BOOK ----------------

    public String returnBook(String memberId, String bookId) {

        Book book = findBook(bookId);

        if (book == null) {
            return "Book not found.";
        }

        for (BorrowingRecord record : records) {

            if (record.getMemberId().equals(memberId)
                    && record.getBookId().equals(bookId)
                    && record.getStatus().equals("Borrowed")) {

                record.returnBook(LocalDate.now().toString());

                book.setAvailable(true);

                return "Book returned successfully.";
            }
        }

        return "Borrowing record not found.";
    }

    // ---------------- RECORDS ----------------

    public String getAllRecords() {

        if (records.isEmpty()) {
            return "No borrowing records available.";
        }

        StringBuilder result = new StringBuilder();

        for (BorrowingRecord record : records) {

            result.append(record.toString());
            result.append("\n\n");
        }

        return result.toString();
    }

    public ArrayList<BorrowingRecord> getRecords() {
        return records;
    }

    // ---------------- TEST DATA ----------------

    public void loadSampleData() {

        addBook(new Book(
                "B001",
                "Harry Potter",
                "J.K. Rowling",
                "Fantasy",
                1997
        ));

        addBook(new Book(
                "B002",
                "The Hobbit",
                "J.R.R. Tolkien",
                "Fantasy",
                1937
        ));

        addBook(new Book(
                "B003",
                "Clean Code",
                "Robert C. Martin",
                "Programming",
                2008
        ));

        addMember(new Member(
                "M001",
                "Kamal",
                "kamal@gmail.com",
                "0711111111"
        ));

        addMember(new Member(
                "M002",
                "Nimal",
                "nimal@gmail.com",
                "0722222222"
        ));
    }
}