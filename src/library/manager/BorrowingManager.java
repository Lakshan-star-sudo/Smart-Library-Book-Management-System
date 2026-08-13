package library.manager;

import library.model.Book;
import library.model.Member;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;

public class BorrowingManager {

    private static final String DATA_FILE =
            "borrowings.dat";

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

        loadRecords();
    }


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

        return book != null
                && book.isAvailable();
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


        boolean updated =
                bookManager.updateAvailability(
                        bookId,
                        false
                );

        if (!updated) {
            return "Could not update book availability.";
        }


        BorrowingRecord record =
                new BorrowingRecord(
                        String.valueOf(memberId),
                        String.valueOf(bookId),
                        LocalDate.now().toString()
                );


        records.add(record);

        saveRecords();


        return "Book borrowed successfully.";
    }



    // RETURN BOOK
    public String returnBook(
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


        // Search latest active borrowing
        for (int i = records.size() - 1;
             i >= 0;
             i--) {

            BorrowingRecord record =
                    records.get(i);


            boolean sameMember =
                    record.getMemberId()
                            .equals(
                                    String.valueOf(memberId)
                            );


            boolean sameBook =
                    record.getBookId()
                            .equals(
                                    String.valueOf(bookId)
                            );


            boolean active =
                    record.getStatus()
                            .equalsIgnoreCase(
                                    "Borrowed"
                            );


            if (sameMember
                    && sameBook
                    && active) {


                boolean updated =
                        bookManager.updateAvailability(
                                bookId,
                                true
                        );


                if (!updated) {
                    return "Could not update book availability.";
                }


                record.returnBook(
                        LocalDate.now().toString()
                );


                saveRecords();


                return "Book returned successfully.";
            }
        }


        return "Active borrowing record not found.";
    }



    // GET ALL RECORDS
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



    // GET RECORD LIST
    public ArrayList<BorrowingRecord> getRecords() {

        return new ArrayList<>(
                records
        );
    }



    // ACTIVE BORROWINGS
    public int getActiveBorrowingCount() {

        int count = 0;


        for (BorrowingRecord record : records) {

            if (record.getStatus()
                    .equalsIgnoreCase(
                            "Borrowed"
                    )) {

                count++;
            }
        }


        return count;
    }



    // TOTAL BORROWED
    public int getTotalBorrowingCount() {

        return records.size();
    }



    // TOTAL RETURNED
    public int getReturnedBorrowingCount() {

        int count = 0;


        for (BorrowingRecord record : records) {

            if (record.getStatus()
                    .equalsIgnoreCase(
                            "Returned"
                    )) {

                count++;
            }
        }


        return count;
    }



    // SAVE RECORDS
    private void saveRecords() {

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(
                                        DATA_FILE
                                )
                        )
        ) {

            output.writeObject(
                    records
            );

        } catch (IOException e) {

            System.err.println(
                    "Could not save borrowing records: "
                            + e.getMessage()
            );
        }
    }

    // LOAD RECORDS
    @SuppressWarnings("unchecked")
    private void loadRecords() {

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(
                                        DATA_FILE
                                )
                        )
        ) {

            ArrayList<BorrowingRecord> savedRecords =
                    (ArrayList<BorrowingRecord>)
                            input.readObject();


            records.addAll(
                    savedRecords
            );

        } catch (
                java.io.FileNotFoundException e
        ) {

            // First run - no records yet.

        } catch (
                IOException
                | ClassNotFoundException e
        ) {

            System.err.println(
                    "Could not load borrowing records: "
                            + e.getMessage()
            );
        }
    }
}