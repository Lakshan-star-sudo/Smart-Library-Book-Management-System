package overdue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OverdueManager {

    public List<String> findOverdueBooks(List<String> borrowedBooks) {

        List<String> overdueBooks = new ArrayList<>();

        LocalDate today = LocalDate.now();

        for (String record : borrowedBooks) {

            if (record == null || record.trim().isEmpty()) {
                continue;
            }

            String[] details = record.split("\\|");

            if (details.length >= 3) {

                String bookTitle = details[0].trim();
                LocalDate dueDate = LocalDate.parse(details[2].trim());

                if (dueDate.isBefore(today)) {
                    overdueBooks.add(bookTitle);
                }
            }
        }

        return overdueBooks;
    }
}