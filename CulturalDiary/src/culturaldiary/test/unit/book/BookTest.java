package culturaldiary.test.unit.book;

import culturaldiary.review.ReviewModel;
import culturaldiary.book.BookController;
import culturaldiary.book.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    BookController bookController = new BookController();

    @BeforeEach
    void list() {
        ArrayList<BookModel> books = new ArrayList<BookModel>();

        books.add(new BookModel("Harry Potter 1", "Marina Costa", "Editora Aurora", "9788598743998", 2001, "Fantasia", true, true));
        books.add(new BookModel("Harry Potter 2", "Lucas Antunes", "Estrela Guia", "8532530988", 1995, "Aventura", false, true));
        books.add(new BookModel("Harry Potter 3", "Paulo Henrique", "Mundo Literário", "9781400034719", 2003, "Juvenil", true, true));
        books.add(new BookModel("Harry Potter 4", "Renata Lopes", "Letras & Magia", "9788532530140", 2000, "Mistério", false, true));
        books.add(new BookModel("Harry Potter 5", "Amanda Rocha", "Editora Cristal", "9788598743012", 2004, "Fantasia", true, true));
        books.add(new BookModel("Harry Potter 6", "Thiago Martins", "Nova Essência", "8532530997", 1998, "Ficção", false, true));
        books.add(new BookModel("Harry Potter 7", "Fernanda Dias", "Companhia das Letras", "9788598743098", 2007, "Drama", true, true));
        books.add(new BookModel("The Witcher 1", "Igor Nunes", "Espada & Magia", "9788535900014", 2005, "Fantasia", true, false));
        books.add(new BookModel("The Witcher 2", "Mariana Soares", "Selo Vermelho", "8532048725", 2007, "Aventura", false, false));
        books.add(new BookModel("The Witcher 3", "Bruno Vieira", "Estúdio Letras", "9788598743104", 2009, "RPG", true, false));
        books.add(new BookModel("The Witcher 4", "Juliana Lopes", "Editora Runa", "9788532530122", 2011, "Fantasia Épica", false, true));
        books.add(new BookModel("The Witcher 5", "Carlos Tavares", "Nova Magia", "8571234599", 2013, "Ação", true, true));
        books.add(new BookModel("The Witcher 6", "Luciana Campos", "Editora Golem", "9788598743128", 2015, "Mitologia", true, false));
        books.add(new BookModel("The Witcher 7", "Roberto Lima", "Lendária Press", "9788535900036", 2017, "Fantasia Sombria", false, true));
        books.add(new BookModel("As Crônicas de Nárnia 1", "C. S. Lewis", "Editora Aliança", "9788535900043", 1950, "Fantasia", true, true));
        books.add(new BookModel("As Crônicas de Nárnia 2", "C. S. Lewis", "Mundo Mágico", "8532048701", 1951, "Aventura", false, false));
        books.add(new BookModel("As Crônicas de Nárnia 3", "C. S. Lewis", "Editora Galáxia", "9788598743159", 1952, "Infantil", true, false));
        books.add(new BookModel("As Crônicas de Nárnia 4", "C. S. Lewis", "Porto das Letras", "8532530148", 1953, "Fantasia Épica", false, true));
        books.add(new BookModel("As Crônicas de Nárnia 5", "C. S. Lewis", "Universo Editorial", "8571234581", 1954, "Clássico", true, true));
        books.add(new BookModel("As Crônicas de Nárnia 6", "C. S. Lewis", "Editora Esperança", "9788598743166", 1955, "Literatura Infantojuvenil", true, false));
        books.add(new BookModel("As Crônicas de Nárnia 7", "C. S. Lewis", "Leitura Viva", "9788535900021", 1956, "Fantasia", false, true));

        ArrayList<ReviewModel> reviews = new ArrayList<ReviewModel>();

        reviews.add(new ReviewModel(4.3f, "19/05/2002", "Excelente livro, recomendo!"));
        reviews.add(new ReviewModel(3.7f, "14/11/1998", "História interessante."));
        reviews.add(new ReviewModel(4.8f, "28/02/2005", "Leitura agradável."));
        reviews.add(new ReviewModel(2.9f, "23/06/2002", "Bom, mas poderia ser melhor."));
        reviews.add(new ReviewModel(5.0f, "10/07/2004", "Muito bom, gostei bastante."));
        reviews.add(new ReviewModel(4.1f, "17/09/2001", "Amei o livro, muito emocionante!"));
        reviews.add(new ReviewModel(3.5f, "01/12/2006", "Não foi tão bom quanto eu esperava."));

        for (int i = 0; i < 7; i++) {
            books.get(i).setBookReview(reviews.get(i));
        }

        bookController.setListOfBooks(books);
    }

    @Test
    void creatingBook() {
        BookModel bookModel = new BookModel("Harry Potter 1", "Marina Costa", "Editora Aurora", "9788598743998", 2001, "Fantasia", true, true);
    }

    @Test
    void registeringBooks() {
        BookController bookController = new BookController();

        assertFalse(bookController.registerBook("    ", "Isabel Moura", "Página Branca", "8591234560", "2016", "Romance", "s", "s li"));
        assertTrue(bookController.registerBook("O Segredo da Colina", "Lúcio Andrade", "Editora Sol", "8571234567", "2018", "Mistério", "sim", "não li"));
        assertTrue(bookController.registerBook("Códigos do Vento", "Aline Torres", "Livros Horizonte", "9788598743021", "2021", "Ficção Científica", "tenho", "li sim"));
        assertFalse(bookController.registerBook("Terras de Fogo", "Rafael Sampaio", "", "9788535909111", "ano2020", "Fantasia", "tenho", "li"));
        assertFalse(bookController.registerBook("A Última Aurora", "Juliana Braga", "Editora Norte", "8599999999", "2022", "", "tenho", "li"));
        assertTrue(bookController.registerBook("As Sombras de Valéria", "Carlos Nunes", "Folha Nova", "8532048716", "2019", "Suspense", "n tenho", "n"));
        assertTrue(bookController.registerBook("Horizonte Infinito", "Márcio Luz", "Vento Leste", "1234567890", "2020", "Aventura", "sim", "li"));
        assertFalse(bookController.registerBook("Fragmentos do Amanhã", "Lucas Vieira", "Editora Essência", "9780000000000", "20242", "Filosofia", "n", "li n"));
        assertFalse(bookController.registerBook("Luzes em Saturno", "Ana Cecília", "Estação 9", "9781234567890", "2023", "Sci-Fi", "possivelmente", "s"));
        assertFalse(bookController.registerBook("Caminho das Estações", "Eduardo Leal", "Nova Trajetória", "8532123456", "2015", "Poesia", "sim", "leitura incompleta"));
    }

    @Test
    void searchingBookByTitle() {
        assertTrue(bookController.searchBookByTitle("Harry   "));
        assertTrue(bookController.searchBookByTitle("   1"));
        assertTrue(bookController.searchBookByTitle("Potter"));
        assertTrue(bookController.searchBookByTitle("O Pequeno Príncipe  "));
        assertTrue(bookController.searchBookByTitle("sertão "));
        assertFalse(bookController.searchBookByTitle(""));
        assertTrue(bookController.searchBookByTitle("Dom Casmurro"));
        assertFalse(bookController.searchBookByTitle("     "));
        assertTrue(bookController.searchBookByTitle("nárnia"));
        assertFalse(bookController.searchBookByTitle("   "));
    }

    @Test
    void searchingBookByAuthor() {
        assertTrue(bookController.searchBookByAuthor("J.K. Rowling"));
        assertFalse(bookController.searchBookByAuthor(""));
        assertTrue(bookController.searchBookByAuthor("Lopes  "));
        assertFalse(bookController.searchBookByAuthor(" "));
        assertFalse(bookController.searchBookByAuthor("     "));
        assertTrue(bookController.searchBookByAuthor("Ana Cecília"));
        assertFalse(bookController.searchBookByAuthor("   "));
        assertTrue(bookController.searchBookByAuthor("Lewis    "));
        assertTrue(bookController.searchBookByAuthor("Aline Torres"));
        assertTrue(bookController.searchBookByAuthor("soares"));
    }

    @Test
    void searchingBookByGenre() {
        assertTrue(bookController.searchBookByGenre("rpg"));
        assertFalse(bookController.searchBookByGenre("     "));
        assertFalse(bookController.searchBookByGenre(" "));
        assertTrue(bookController.searchBookByGenre("Sci-Fi"));
        assertTrue(bookController.searchBookByGenre("genero inventado"));
        assertTrue(bookController.searchBookByGenre("   fantasia"));
        assertTrue(bookController.searchBookByGenre("épica"));
        assertTrue(bookController.searchBookByGenre("Fantasia"));
        assertTrue(bookController.searchBookByGenre("Poesia"));
        assertTrue(bookController.searchBookByGenre("literatura"));
    }

    @Test
    void searchingBookByYearOfPublication() {
        assertFalse(bookController.searchBookByYearOfPublication("200u0"));
        assertFalse(bookController.searchBookByYearOfPublication("169d9"));
        assertTrue(bookController.searchBookByYearOfPublication("2025"));
        assertTrue(bookController.searchBookByYearOfPublication("2005"));
        assertTrue(bookController.searchBookByYearOfPublication("2007"));
        assertTrue(bookController.searchBookByYearOfPublication("19925"));
        assertTrue(bookController.searchBookByYearOfPublication("1699"));
        assertFalse(bookController.searchBookByYearOfPublication("   "));
        assertFalse(bookController.searchBookByYearOfPublication("ano2020"));
        assertFalse(bookController.searchBookByYearOfPublication(""));
    }

    @Test
    void searchingBookByIsbn() {
        assertTrue(bookController.searchBookByIsbn("   8532048701   "));
        assertFalse(bookController.searchBookByIsbn("                "));
        assertFalse(bookController.searchBookByIsbn("asdasd2222"));
        assertFalse(bookController.searchBookByIsbn("9788123   "));
        assertFalse(bookController.searchBookByIsbn("   04701   "));
        assertTrue(bookController.searchBookByIsbn("9788535900036"));
        assertTrue(bookController.searchBookByIsbn("9788598743128  "));
        assertFalse(bookController.searchBookByIsbn(""));
        assertTrue(bookController.searchBookByIsbn("9788535900021"));
        assertTrue(bookController.searchBookByIsbn("9788598711998"));
    }

    @Test
    void listingBooks() {
        assertTrue(bookController.listBooks());
    }

    @Test
    void filteringByBookGenre() {
        assertTrue(bookController.filterListOfBooksByGenre("rpg"));
        assertFalse(bookController.filterListOfBooksByGenre("     "));
        assertFalse(bookController.filterListOfBooksByGenre(" "));
        assertTrue(bookController.filterListOfBooksByGenre("Sci-Fi"));
        assertTrue(bookController.filterListOfBooksByGenre("genero inventado"));
        assertTrue(bookController.filterListOfBooksByGenre("   fantasia"));
        assertTrue(bookController.filterListOfBooksByGenre("épica"));
        assertTrue(bookController.filterListOfBooksByGenre("Fantasia"));
        assertTrue(bookController.filterListOfBooksByGenre("Poesia"));
        assertTrue(bookController.filterListOfBooksByGenre("literatura"));
    }

    @Test
    void filteringBookByYearOfPublication() {
        assertFalse(bookController.filterListOfBooksByYearOfPublication("200u0"));
        assertFalse(bookController.filterListOfBooksByYearOfPublication("169d9"));
        assertTrue(bookController.filterListOfBooksByYearOfPublication("2025"));
        assertTrue(bookController.filterListOfBooksByYearOfPublication("2005"));
        assertTrue(bookController.filterListOfBooksByYearOfPublication("2007"));
        assertTrue(bookController.filterListOfBooksByYearOfPublication("19925"));
        assertTrue(bookController.filterListOfBooksByYearOfPublication("1699"));
        assertFalse(bookController.filterListOfBooksByYearOfPublication("   "));
        assertFalse(bookController.filterListOfBooksByYearOfPublication("ano2020"));
        assertFalse(bookController.filterListOfBooksByYearOfPublication(""));
    }

    @Test
    void sortingListByTopRated() {
        assertTrue(bookController.sortListByTopRated());
    }

    @Test
    void sortingListByLowRated() {
        assertTrue(bookController.sortListByLowRated());
    }

    @Test
    void openingBook() {
        BookModel book = bookController.getListOfBooks().get(0);
        assertTrue(bookController.openBook(book));
        assertFalse(bookController.openBook(null));
    }

    @Test
    void changingBookReadingStatus() {
        BookModel book1 = bookController.getListOfBooks().get(0);
        BookModel book2 = bookController.getListOfBooks().get(7);

        assertTrue(bookController.changeBookReadingStatus(book1, "não li"));
        assertTrue(bookController.changeBookReadingStatus(book2, "não li"));
        assertTrue(bookController.changeBookReadingStatus(book1, "li"));
        assertTrue(bookController.changeBookReadingStatus(book2, "li"));
        assertFalse(bookController.changeBookReadingStatus(book1, "  "));
        assertFalse(bookController.changeBookReadingStatus(null, "li"));
        assertFalse(bookController.changeBookReadingStatus(null, "   "));
    }

    @Test
    void evaluatingBook() {
        BookModel book1 = bookController.getListOfBooks().get(17);
        BookModel book2 = bookController.getListOfBooks().get(13);
        BookModel book3 = bookController.getListOfBooks().get(20);

        assertTrue(bookController.evaluateBook(book1, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertTrue(bookController.evaluateBook(book1, "4.3gh", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBook(null, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBook(book2, "4.3", "19/05/1700", "Excelente livro, recomendo!"));
        assertTrue(bookController.evaluateBook(book3, "3.7", "14/11/1998", "História interessante."));
        assertFalse(bookController.evaluateBook(book2, "4.8", "28/02/2005", "Leitura agradável."));

    }

    @Test
    void evaluatingBookAgain() {
        BookModel book1 = bookController.getListOfBooks().get(0);
        BookModel book2 = bookController.getListOfBooks().get(1);
        BookModel book3 = bookController.getListOfBooks().get(2);

        assertTrue(bookController.evaluateBookAgain(book1, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBookAgain(book1, "4.3gh", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBookAgain(null, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBookAgain(book2, "4.3", "19/05/1700", "Excelente livro, recomendo!"));
        assertTrue(bookController.evaluateBookAgain(book3, "3.7", "14/11/1998", "História interessante."));
        assertFalse(bookController.evaluateBookAgain(book2, "4.8", "28/02/2005", "Leitura agradável."));
    }

    //esse teste acima dando erro
}