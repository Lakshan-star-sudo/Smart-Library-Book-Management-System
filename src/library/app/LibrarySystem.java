package library.app;

import library.datastructure.BookBST;
import library.manager.BookManager;
import library.manager.BorrowingManager;
import library.manager.MemberManager;

public class LibrarySystem {

    private final BookBST bookBST;
    private final BookManager bookManager;
    private final BorrowingManager borrowingManager;
    private final MemberManager memberManager;

    public LibrarySystem() {

        bookBST = new BookBST();

        bookManager =
                new BookManager(bookBST);

        memberManager =
                new MemberManager();

        borrowingManager =
                new BorrowingManager(
                        bookManager,
                        memberManager
                );
    }

    public BookBST getBookBST() {
        return bookBST;
    }

    public BookManager getBookManager() {
        return bookManager;
    }

    public MemberManager getMemberManager() {
        return memberManager;
    }

    public BorrowingManager getBorrowingManager() {
        return borrowingManager;
    }
}