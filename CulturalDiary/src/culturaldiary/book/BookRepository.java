package culturaldiary.book;

import java.util.ArrayList;

public class BookRepository {
    private ArrayList<BookModel> listOfBooks = new ArrayList<BookModel>(); // Lista que armazena os objetos BookModel (livros)

    public void addBook(BookModel book) {
        listOfBooks.add(book); // Adiciona um novo livro à lista de livros
    }

    //Método Getter e Setter
    public ArrayList<BookModel> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(ArrayList<BookModel> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }
}
