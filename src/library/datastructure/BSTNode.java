package library.datastructure;

import library.model.Book;

class BSTNode {

    Book book;
    BSTNode left;
    BSTNode right;

    BSTNode(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
    }
}