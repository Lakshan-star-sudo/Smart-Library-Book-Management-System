package library.manager;

public class BorrowingRecord {

    private String memberId;
    private String bookId;
    private String borrowDate;
    private String returnDate;
    private String status;

    public BorrowingRecord(String memberId,
                           String bookId,
                           String borrowDate) {

        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
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

    public void returnBook(String returnDate) {
        this.returnDate = returnDate;
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