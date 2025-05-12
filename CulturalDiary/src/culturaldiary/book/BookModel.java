package culturaldiary.book;

import culturaldiary.review.ReviewModel;

/**
 * Model class representing a book.
 * Contains attributes and methods related to a book entity.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.0
 */
public class BookModel {
    private static int bookCounter = 1;

    private String title; // Título do livro
    private String author; // Autor do livro
    private String publisher; // Editora do livro
    private String isbn; // Número ISBN do livro
    private int yearOfPublication; // Ano de publicação do livro
    private String genre; // Gênero do livro
    private boolean hasCopy; // Indica se o livro tem cópia disponível
    private int bookIndex; // Índice do livro na lista
    private ReviewModel bookReview; // Avaliação do livro
    private boolean evaluatedBook; // Indica se o livro foi avaliado
    private boolean read; // Indica se o livro foi lido

    public BookModel(String title, String author, String publisher,
                     String isbn, int yearOfPublication, String genre, boolean hasCopy, boolean read) {
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
        this.read = read;
    } // Construtor

    // Métodos Getters e Setters
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

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
