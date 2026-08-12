import java.time.LocalDate;

public class Borrowing {

    private String memberId;
    private String bookId;
    private String borrowDate;
    private String returnDate;
    private String status;

    public Borrowing(String memberId, String bookId) {

        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = LocalDate.now().toString();
        this.returnDate = "-";
        this.status = "Borrowed";
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now().toString();
        this.status = "Returned";
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId +
                " | Book ID: " + bookId +
                " | Borrow Date: " + borrowDate +
                " | Return Date: " + returnDate +
                " | Status: " + status;
    }
}