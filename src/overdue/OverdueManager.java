package overdue;

import library.manager.BorrowingManager;
import library.manager.BorrowingRecord;

import java.util.ArrayList;
import java.util.List;

public class OverdueManager {

    private final BorrowingManager borrowingManager;

    public OverdueManager(
            BorrowingManager borrowingManager
    ) {

        this.borrowingManager =
                borrowingManager;
    }

    // =========================
    // FIND OVERDUE RECORDS
    // =========================

    public List<BorrowingRecord> findOverdueBooks() {

        List<BorrowingRecord> overdueRecords =
                new ArrayList<>();

        for (BorrowingRecord record :
                borrowingManager.getRecords()) {

            if (record.isOverdue()) {

                overdueRecords.add(
                        record
                );
            }
        }

        return overdueRecords;
    }


    // =========================
    // OVERDUE COUNT
    // =========================

    public int getOverdueCount() {

        return findOverdueBooks()
                .size();
    }


    // =========================
    // OVERDUE TEXT
    // =========================

    public String getOverdueReport() {

        List<BorrowingRecord> overdueRecords =
                findOverdueBooks();

        if (overdueRecords.isEmpty()) {

            return "No overdue books found.";
        }


        StringBuilder result =
                new StringBuilder();


        for (BorrowingRecord record :
                overdueRecords) {

            result.append(
                    "Member ID: "
                            + record.getMemberId()
            );

            result.append(
                    " | Book ID: "
                            + record.getBookId()
            );

            result.append(
                    " | Borrow Date: "
                            + record.getBorrowDate()
            );

            result.append(
                    " | Due Date: "
                            + record.getDueDate()
            );

            result.append(
                    "\n"
            );
        }


        return result.toString();
    }
}