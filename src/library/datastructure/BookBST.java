package library.datastructure;

import java.util.ArrayList;
import java.util.List;
import library.model.Book;

public class BookBST {

    private BSTNode root;

    public BookBST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean insert(Book book) {

        if (book == null) {
            return false;
        }

        if (root == null) {
            root = new BSTNode(book);
            return true;
        }

        BSTNode current = root;

        while (true) {

            if (book.getBookId() < current.book.getBookId()) {

                if (current.left == null) {
                    current.left = new BSTNode(book);
                    return true;
                }

                current = current.left;

            } else if (book.getBookId() > current.book.getBookId()) {

                if (current.right == null) {
                    current.right = new BSTNode(book);
                    return true;
                }

                current = current.right;

            } else {
                return false;
            }
        }
    }

    public Book search(int bookId) {

        BSTNode current = root;

        while (current != null) {

            if (bookId == current.book.getBookId()) {
                return current.book;
            }

            if (bookId < current.book.getBookId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean delete(int bookId) {

        if (search(bookId) == null) {
            return false;
        }

        root = deleteNode(root, bookId);
        return true;
    }

    private BSTNode deleteNode(BSTNode node, int bookId) {

        if (node == null) {
            return null;
        }

        if (bookId < node.book.getBookId()) {

            node.left = deleteNode(node.left, bookId);

        } else if (bookId > node.book.getBookId()) {

            node.right = deleteNode(node.right, bookId);

        } else {

            // Case 1: No children
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: Only right child
            if (node.left == null) {
                return node.right;
            }

            // Case 2: Only left child
            if (node.right == null) {
                return node.left;
            }

            // Case 3: Two children
            BSTNode successor = findMinNode(node.right);

            node.book = successor.book;

            node.right = deleteNode(
                    node.right,
                    successor.book.getBookId()
            );
        }

        return node;
    }

    private BSTNode findMinNode(BSTNode node) {

        BSTNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public List<Book> inorderTraversal() {

        List<Book> books = new ArrayList<>();
        inorder(root, books);
        return books;
    }

    private void inorder(BSTNode node, List<Book> books) {

        if (node != null) {
            inorder(node.left, books);
            books.add(node.book);
            inorder(node.right, books);
        }
    }

    public List<Book> preorderTraversal() {

        List<Book> books = new ArrayList<>();
        preorder(root, books);
        return books;
    }

    private void preorder(BSTNode node, List<Book> books) {

        if (node != null) {
            books.add(node.book);
            preorder(node.left, books);
            preorder(node.right, books);
        }
    }

    public List<Book> postorderTraversal() {

        List<Book> books = new ArrayList<>();
        postorder(root, books);
        return books;
    }

    private void postorder(BSTNode node, List<Book> books) {

        if (node != null) {
            postorder(node.left, books);
            postorder(node.right, books);
            books.add(node.book);
        }
    }

    public Book findMin() {
        if (root == null) {
            return null;
        }

        BSTNode minNode = findMinNode(root);
        return minNode.book;
    }

    public Book findMax() {
        if (root == null) {
            return null;
        }

        BSTNode current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.book;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BSTNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}