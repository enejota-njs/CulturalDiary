package culturaldiary.book.model.repository;

import culturaldiary.book.model.BookModel;

import java.util.ArrayList;

public class BookRepository {
    private ArrayList<BookModel> listOfBooks = new ArrayList<BookModel>();

    public void addBook(BookModel book) {
        listOfBooks.add(book);
    }

    public ArrayList<BookModel> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(ArrayList<BookModel> listOfBooks) {
            this.listOfBooks = listOfBooks;
    }
}