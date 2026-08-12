package waitinglist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaitingListManager {

    private final Map<Integer, List<Integer>> waitingLists;

    public WaitingListManager() {
        waitingLists = new HashMap<>();
    }

    // Add a member to a book's waiting list
    public void addToWaitingList(int bookId, int memberId) {

        waitingLists.putIfAbsent(bookId, new ArrayList<>());

        List<Integer> members = waitingLists.get(bookId);

        if (!members.contains(memberId)) {
            members.add(memberId);
            System.out.println("Member " + memberId
                    + " added to waiting list for Book " + bookId);
        } else {
            System.out.println("Member is already in the waiting list.");
        }
    }

    // View waiting list for a specific book
    public List<Integer> getWaitingList(int bookId) {

        return waitingLists.getOrDefault(bookId, new ArrayList<>());
    }

    // Remove a member from the waiting list
    public void removeFromWaitingList(int bookId, int memberId) {

        List<Integer> members = waitingLists.get(bookId);

        if (members != null && members.remove(Integer.valueOf(memberId))) {

            System.out.println("Member " + memberId
                    + " removed from waiting list.");

            if (members.isEmpty()) {
                waitingLists.remove(bookId);
            }

        } else {
            System.out.println("Member not found in waiting list.");
        }
    }

    // Get the next member waiting for a book
    public Integer getNextMember(int bookId) {

        List<Integer> members = waitingLists.get(bookId);

        if (members != null && !members.isEmpty()) {
            return members.get(0);
        }

        return null;
    }

    // Check whether a book has a waiting list
    public boolean hasWaitingList(int bookId) {

        List<Integer> members = waitingLists.get(bookId);

        return members != null && !members.isEmpty();
    }
}