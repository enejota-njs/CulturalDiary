package unit;

import book.BookController;
import book.BookModel;
import review.ReviewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    ArrayList<BookModel> books =  new ArrayList<BookModel>();

    @BeforeEach
    void list() {
        books.add(new BookModel("Harry Potter 1", "Marina Costa", "Editora Aurora", "9788598743998", 2001, "Fantasia", true, true, books.size() + 1));
        books.add(new BookModel("Harry Potter 2", "Lucas Antunes", "Estrela Guia", "8532530988", 1995, "Aventura", false, true, books.size() + 1));
        books.add(new BookModel("Harry Potter 3", "Paulo Henrique", "Mundo Literário", "9781400034719", 2003, "Juvenil", true, true, books.size() + 1));
        books.add(new BookModel("Harry Potter 4", "Renata Lopes", "Letras & Magia", "9788532530140", 2000, "Mistério", false, true, books.size() + 1));
        books.add(new BookModel("Harry Potter 5", "Amanda Rocha", "Editora Cristal", "9788598743012", 2004, "Fantasia", true, true, books.size() + 1));
        books.add(new BookModel("Harry Potter 6", "Thiago Martins", "Nova Essência", "8532530997", 1998, "Ficção", false, true, books.size() + 1));
        books.add(new BookModel("Harry Potter 7", "Fernanda Dias", "Companhia das Letras", "9788598743098", 2007, "Drama", true, true, books.size() + 1));
        books.add(new BookModel("The Witcher 1", "Igor Nunes", "Espada & Magia", "9788535900014", 2005, "Fantasia", true, false, books.size() + 1));
        books.add(new BookModel("The Witcher 2", "Mariana Soares", "Selo Vermelho", "8532048725", 2007, "Aventura", false, false, books.size() + 1));
        books.add(new BookModel("The Witcher 3", "Bruno Vieira", "Estúdio Letras", "9788598743104", 2009, "RPG", true, false, books.size() + 1));
        books.add(new BookModel("The Witcher 4", "Juliana Lopes", "Editora Runa", "9788532530122", 2011, "Fantasia Épica", false, true, books.size() + 1));
        books.add(new BookModel("The Witcher 5", "Carlos Tavares", "Nova Magia", "8571234599", 2013, "Ação", true, true, books.size() + 1));
        books.add(new BookModel("The Witcher 6", "Luciana Campos", "Editora Golem", "9788598743128", 2015, "Mitologia", true, false, books.size() + 1));
        books.add(new BookModel("The Witcher 7", "Roberto Lima", "Lendária Press", "9788535900036", 2017, "Fantasia Sombria", false, true, books.size() + 1));
        books.add(new BookModel("As Crônicas de Nárnia 1", "C. S. Lewis", "Editora Aliança", "9788535900043", 1950, "Fantasia", true, true, books.size() + 1));
        books.add(new BookModel("As Crônicas de Nárnia 2", "C. S. Lewis", "Mundo Mágico", "8532048701", 1951, "Aventura", false, false, books.size() + 1));
        books.add(new BookModel("As Crônicas de Nárnia 3", "C. S. Lewis", "Editora Galáxia", "9788598743159", 1952, "Infantil", true, false, books.size() + 1));
        books.add(new BookModel("As Crônicas de Nárnia 4", "C. S. Lewis", "Porto das Letras", "8532530148", 1953, "Fantasia Épica", false, true, books.size() + 1));
        books.add(new BookModel("As Crônicas de Nárnia 5", "C. S. Lewis", "Universo Editorial", "8571234581", 1954, "Clássico", true, true, books.size() + 1));
        books.add(new BookModel("As Crônicas de Nárnia 6", "C. S. Lewis", "Editora Esperança", "9788598743166", 1955, "Literatura Infantojuvenil", true, false, books.size() + 1));
        books.add(new BookModel("As Crônicas de Nárnia 7", "C. S. Lewis", "Leitura Viva", "9788535900021", 1956, "Fantasia", false, true, books.size() + 1));

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
            books.get(i).setEvaluatedBook(true);
        }
    } // Criando lista para usar nos testes

    @Test
    void creatingBook() {
        BookModel bookModel = new BookModel("Harry Potter 1", "Marina Costa", "Editora Aurora", "9788598743998", 2001, "Fantasia", true, true, books.size() + 1);
    } // Criando livros

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
    } // Registrando livros

    @Test
    void searchingBookByTitle() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

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
    } // Buscando livros por título

    @Test
    void searchingBookByAuthor() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

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
    } // Buscando livros por autor

    @Test
    void searchingBookByGenre() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

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
    } // Buscando livros por gênero

    @Test
    void searchingBookByYearOfPublication() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

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
    } // Buscando livros por ano de publicação

    @Test
    void searchingBookByIsbn() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

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
    } // Buscando livros por ISBN

    @Test
    void listingBooks() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

        assertTrue(bookController.listBooks());
    } // Listando livros

    @Test
    void filteringByBookGenre() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

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
    } // Filtrando livros por gênero

    @Test
    void filteringBookByYearOfPublication() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

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
    } // Filtrando livros por ano de publicação

    @Test
    void sortingListByTopRated() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

        assertTrue(bookController.sortListByTopRated());
    } // Ordenando livros do melhor avaliado ao pior

    @Test
    void sortingListByLowRated() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

        assertTrue(bookController.sortListByLowRated());
    } // Ordenando livros do pior avaliado ao melhor

    @Test
    void openingBook() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

        assertTrue(bookController.openBook(1));
        assertFalse(bookController.openBook(33));
    } // Abrindo livros

    @Test
    void changingBookReadingStatus() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

        assertFalse(bookController.changeBookReadingStatus(1, "não li"));
        assertTrue(bookController.changeBookReadingStatus(8, "não li"));
        assertTrue(bookController.changeBookReadingStatus(1, "li"));
        assertTrue(bookController.changeBookReadingStatus(8, "li"));
        assertFalse(bookController.changeBookReadingStatus(1, "  "));
        assertFalse(bookController.changeBookReadingStatus(99, "li"));
        assertFalse(bookController.changeBookReadingStatus(-2, "   "));
    } // Mudando situação de leitura

    @Test
    void evaluatingBook() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

        assertFalse(bookController.evaluateBook(1, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBook(19, "4.3gh", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBook(33, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertTrue(bookController.evaluateBook(19, "4.3", "19/05/1999", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBook(20, "3.7", "14/11/1998", "História interessante."));
        assertTrue(bookController.evaluateBook(21, "4.8", "28/02/2006", "Leitura agradável."));
        assertFalse(bookController.evaluateBook(20, "4.8", "28/02/2006", "Leitura agradável."));
    } // Avaliando livros

    @Test
    void evaluatingBookAgain() {
        BookController bookController = new BookController();
        bookController.setListOfBooks(books);

        assertTrue(bookController.evaluateBookAgain(1, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBookAgain(1, "4.3gh", "19/05/2002", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBookAgain(81, "4.3", "19/05/2002", "Excelente livro, recomendo!"));
        assertTrue(bookController.evaluateBookAgain(2, "4.3", "19/01/2025", "Excelente livro, recomendo!"));
        assertFalse(bookController.evaluateBookAgain(3, "3.7", "14/11/1998", "História interessante."));
        assertFalse(bookController.evaluateBookAgain(21, "4.8", "28/02/2005", "Leitura agradável."));
        assertFalse(bookController.evaluateBookAgain(21, "3.5", "01/12/2006", "Não foi tão bom quanto eu esperava."));
    } // Avaliando livros novamente
}