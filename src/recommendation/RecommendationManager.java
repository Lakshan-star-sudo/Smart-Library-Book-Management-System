package recommendation;

import java.util.ArrayList;
import java.util.List;

public class RecommendationManager {

    /**
     * Recommend books based on the selected category.
     *
     * @param category selected book category
     * @param books list of books in the format:
     *              "Book Title|Category"
     * @return list of recommended book titles
     */
    public List<String> recommendBooks(String category, List<String> books) {

        List<String> recommendations = new ArrayList<>();

        if (category == null || category.trim().isEmpty()) {
            return recommendations;
        }

        for (String book : books) {

            if (book == null || book.trim().isEmpty()) {
                continue;
            }

            String[] bookDetails = book.split("\\|");

            if (bookDetails.length >= 2) {

                String title = bookDetails[0].trim();
                String bookCategory = bookDetails[1].trim();

                if (bookCategory.equalsIgnoreCase(category.trim())) {
                    recommendations.add(title);
                }
            }
        }

        return recommendations;
    }
}