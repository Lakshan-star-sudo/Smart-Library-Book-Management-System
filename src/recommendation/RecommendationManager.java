package recommendation;

import library.manager.BookManager;
import library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RecommendationManager {

    private final BookManager bookManager;

    public RecommendationManager(
            BookManager bookManager
    ) {
        this.bookManager = bookManager;
    }

    // Recommend available books
    // based on selected category
    public List<Book> recommendBooks(
            String category
    ) {

        List<Book> recommendations =
                new ArrayList<>();

        if (category == null
                || category.trim().isEmpty()) {

            return recommendations;
        }

        for (Book book :
                bookManager.getAllBooks()) {

            if (book.getCategory() == null) {
                continue;
            }

            if (book.getCategory()
                    .equalsIgnoreCase(
                            category.trim()
                    )
                    && book.isAvailable()) {

                recommendations.add(book);
            }
        }

        return recommendations;
    }

    // Get categories directly
    // from books in the library
    public List<String> getCategories() {

        List<String> categories =
                new ArrayList<>();

        for (Book book :
                bookManager.getAllBooks()) {

            String category =
                    book.getCategory();

            if (category == null
                    || category.trim().isEmpty()) {

                continue;
            }

            boolean exists = false;

            for (String existing :
                    categories) {

                if (existing.equalsIgnoreCase(
                        category.trim()
                )) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                categories.add(
                        category.trim()
                );
            }
        }

        return categories;
    }
}