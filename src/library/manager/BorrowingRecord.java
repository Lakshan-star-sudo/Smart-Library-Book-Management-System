package library.manager;

import java.io.Serializable;
import java.time.LocalDate;

public class BorrowingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String memberId;
    private String bookId;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String status;

    public BorrowingRecord(
            String memberId,
            String bookId,
            String borrowDate
    ) {

        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;

        this.dueDate =
                LocalDate.parse(borrowDate)
                        .plusDays(14)
                        .toString();

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

    public String getDueDate() {

        if (dueDate == null
                || dueDate.trim().isEmpty()) {

            dueDate =
                    LocalDate.parse(borrowDate)
                            .plusDays(14)
                            .toString();
        }

        return dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void returnBook(
            String returnDate
    ) {

        this.returnDate = returnDate;
        this.status = "Returned";
    }

    public boolean isOverdue() {

        if (!status.equalsIgnoreCase("Borrowed")) {
            return false;
        }

        LocalDate due =
                LocalDate.parse(
                        getDueDate()
                );

        return due.isBefore(
                LocalDate.now()
        );
    }

    @Override
    public String toString() {

        return "Member ID: " + memberId
                + " | Book ID: " + bookId
                + " | Borrow Date: " + borrowDate
                + " | Due Date: " + getDueDate()
                + " | Return Date: " + returnDate
                + " | Status: " + status;
    }
}