package t.model;

import t.controller.BookController;

public class MediaModel {
    private BookController bookController = new BookController();

    public BookController getBookController() {
        return bookController;
    }

    public void setBookController(BookController bookController) {
        this.bookController = bookController;
    }
}
