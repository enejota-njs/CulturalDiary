package culturaldiary.book.model;

import culturaldiary.media.model.ReviewModel;

public class BookModel {
    private static int bookCounter = 1;

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private int yearOfPublication;
    private String genre;
    private boolean hasCopy;
    private int bookIndex;
    private ReviewModel bookReview;
    private boolean evaluatedBook;

    public BookModel(String title, String author, String publisher,
                     String isbn, int yearOfPublication, String genre, boolean hasCopy) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
        this.hasCopy = hasCopy;
        this.bookIndex = bookCounter++;
        this.bookReview = null;
        this.evaluatedBook = false;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isHasCopy() {
        return hasCopy;
    }

    public void setHasCopy(boolean hasCopy) {
        this.hasCopy = hasCopy;
    }

    public int getBookIndex() {
        return bookIndex;
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
    }

    public ReviewModel getBookReview() {
        return bookReview;
    }

    public void setBookReview(ReviewModel bookReview) {
        this.bookReview = bookReview;
    }

    public boolean isEvaluatedBook() {
        return evaluatedBook;
    }

    public void setEvaluatedBook(boolean evaluatedBook) {
        this.evaluatedBook = evaluatedBook;
    }
}
