package waitinglist;

import library.manager.BookManager;
import library.manager.MemberManager;
import library.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaitingListManager {

    private static final String DATA_FILE =
            "waitinglist.dat";

    private final Map<Integer, List<Integer>>
            waitingLists;

    private final BookManager bookManager;
    private final MemberManager memberManager;


    public WaitingListManager(
            BookManager bookManager,
            MemberManager memberManager
    ) {

        this.bookManager = bookManager;
        this.memberManager = memberManager;

        waitingLists = new HashMap<>();

        loadWaitingLists();
    }



    // ADD TO WAITING LIST
    public String addToWaitingList(
            int bookId,
            int memberId
    ) {

        if (bookId <= 0 || memberId <= 0) {
            return "Book ID and Member ID must be positive numbers.";
        }


        Book book =
                bookManager.searchBook(bookId);

        if (book == null) {
            return "Book ID " + bookId + " does not exist.";
        }


        if (memberManager.searchMember(memberId) == null) {
            return "Member ID " + memberId + " does not exist.";
        }


        // Waiting list only makes sense for unavailable books
        if (book.isAvailable()) {

            return "Book \"" + book.getTitle()
                    + "\" is currently available.\n"
                    + "There is no need to join the waiting list.";
        }


        waitingLists.putIfAbsent(
                bookId,
                new ArrayList<>()
        );


        List<Integer> members =
                waitingLists.get(bookId);


        if (members.contains(memberId)) {

            return "Member " + memberId
                    + " is already in the waiting list "
                    + "for Book " + bookId + ".";
        }


        members.add(memberId);

        saveWaitingLists();


        return "Member " + memberId
                + " added to the waiting list "
                + "for Book " + bookId
                + " successfully.";
    }



    // GET WAITING LIST
    public List<Integer> getWaitingList(
            int bookId
    ) {

        List<Integer> members =
                waitingLists.get(bookId);

        if (members == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(members);
    }



    // REMOVE MEMBER
    public String removeFromWaitingList(
            int bookId,
            int memberId
    ) {

        List<Integer> members =
                waitingLists.get(bookId);


        if (members == null) {

            return "There is no waiting list "
                    + "for Book " + bookId + ".";
        }


        boolean removed =
                members.remove(
                        Integer.valueOf(memberId)
                );


        if (!removed) {

            return "Member " + memberId
                    + " is not in the waiting list "
                    + "for Book " + bookId + ".";
        }


        if (members.isEmpty()) {
            waitingLists.remove(bookId);
        }


        saveWaitingLists();


        return "Member " + memberId
                + " removed from the waiting list "
                + "for Book " + bookId + ".";
    }



    // NEXT MEMBER
    public Integer getNextMember(
            int bookId
    ) {

        List<Integer> members =
                waitingLists.get(bookId);


        if (members != null
                && !members.isEmpty()) {

            return members.get(0);
        }


        return null;
    }



    // HAS WAITING LIST
    public boolean hasWaitingList(
            int bookId
    ) {

        List<Integer> members =
                waitingLists.get(bookId);

        return members != null
                && !members.isEmpty();
    }



    // MEMBER POSITION
    public int getMemberPosition(
            int bookId,
            int memberId
    ) {

        List<Integer> members =
                waitingLists.get(bookId);


        if (members == null) {
            return -1;
        }


        int index =
                members.indexOf(memberId);


        if (index < 0) {
            return -1;
        }


        // User-friendly position: 1, 2, 3...
        return index + 1;
    }



    // TOTAL WAITING REQUESTS
    public int getTotalWaitingCount() {

        int total = 0;


        for (List<Integer> members
                : waitingLists.values()) {

            total += members.size();
        }


        return total;
    }


    // Number of different books
    // which currently have waiting members
    public int getWaitingBookCount() {

        return waitingLists.size();
    }



    // MEMBER WAITING COUNT
    public int getWaitingCountForMember(
            int memberId
    ) {

        int count = 0;


        for (List<Integer> members
                : waitingLists.values()) {

            if (members.contains(memberId)) {
                count++;
            }
        }


        return count;
    }



    private void saveWaitingLists() {

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(
                                        DATA_FILE
                                )
                        )
        ) {

            output.writeObject(
                    waitingLists
            );

        } catch (IOException e) {

            System.err.println(
                    "Could not save waiting list: "
                            + e.getMessage()
            );
        }
    }


    @SuppressWarnings("unchecked")
    private void loadWaitingLists() {

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(
                                        DATA_FILE
                                )
                        )
        ) {

            Map<Integer, List<Integer>>
                    savedLists =
                    (Map<Integer, List<Integer>>)
                            input.readObject();


            waitingLists.clear();

            waitingLists.putAll(
                    savedLists
            );

        } catch (FileNotFoundException e) {


        } catch (
                IOException
                | ClassNotFoundException e
        ) {

            System.err.println(
                    "Could not load waiting list: "
                            + e.getMessage()
            );
        }
    }
}