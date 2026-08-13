package library.manager;

import library.model.Book;
import library.model.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class BorrowingManager {

    private final BookManager bookManager;
    private final MemberManager memberManager;
    private final ArrayList<BorrowingRecord> records;

    public BorrowingManager(
            BookManager bookManager,
            MemberManager memberManager
    ) {
        this.bookManager = bookManager;
        this.memberManager = memberManager;
        this.records = new ArrayList<>();
    }


    // FIND BOOK
    public Book findBook(int bookId) {
        return bookManager.searchBook(bookId);
    }


    // FIND MEMBER
    public Member findMember(int memberId) {
        return memberManager.searchMember(memberId);
    }


    // CHECK AVAILABILITY
    public boolean checkAvailability(int bookId) {

        Book book = findBook(bookId);

        return book != null && book.isAvailable();
    }

    // BORROW BOOK
    public String borrowBook(
            int memberId,
            int bookId
    ) {

        if (memberId <= 0) {
            return "Please enter a valid Member ID.";
        }

        if (bookId <= 0) {
            return "Please enter a valid Book ID.";
        }

        Member member =
                memberManager.searchMember(memberId);

        if (member == null) {
            return "Member not found.";
        }

        Book book =
                bookManager.searchBook(bookId);

        if (book == null) {
            return "Book not found.";
        }

        if (!book.isAvailable()) {
            return "Book is not available.";
        }

        book.setAvailable(false);

        BorrowingRecord record =
                new BorrowingRecord(
                        String.valueOf(memberId),
                        String.valueOf(bookId),
                        LocalDate.now().toString()
                );

        records.add(record);

        return "Book borrowed successfully.";
    }


    // RETURN BOOK
    public String returnBook(
            int memberId,
            int bookId
    ) {

        Book book =
                bookManager.searchBook(bookId);

        if (book == null) {
            return "Book not found.";
        }

        for (BorrowingRecord record : records) {

            if (record.getMemberId().equals(
                    String.valueOf(memberId))
                    && record.getBookId().equals(
                    String.valueOf(bookId))
                    && record.getStatus().equals(
                    "Borrowed")) {

                record.returnBook(
                        LocalDate.now().toString()
                );

                book.setAvailable(true);

                return "Book returned successfully.";
            }
        }

        return "Borrowing record not found.";
    }

    // RECORDS
    public String getAllRecords() {

        if (records.isEmpty()) {
            return "No borrowing records available.";
        }

        StringBuilder result =
                new StringBuilder();

        for (BorrowingRecord record : records) {

            result.append(
                    record.toString()
            );

            result.append(
                    "\n\n"
            );
        }

        return result.toString();
    }

    public ArrayList<BorrowingRecord> getRecords() {
        return records;
    }
}