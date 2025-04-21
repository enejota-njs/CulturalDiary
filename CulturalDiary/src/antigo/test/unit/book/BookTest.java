package antigo.test.unit.book;

import antigo.book.controller.BookController;
import antigo.book.model.BookModel;
import antigo.book.view.BookView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    @BeforeEach
    void setUp() {
        BookController controller = new BookController();
        BookModel model1 = new BookModel("Harry Potter e o Cálice de Fogo", "J.K. Rowling", "Rocco",
                "9788532530813", 2017, "Infantil, Fantasia", false);

        BookModel model2 = new BookModel("O Senhor dos Anéis: A Sociedade do Anel", "J.R.R. Tolkien", "Martins Fontes",
                "9788578270698", 2001, "Fantasia, Aventura", false);

        BookModel model3 = new BookModel("1984", "George Orwell", "Companhia das Letras",
                "8535914849", 2009, "Distopia, Ficção Científica", true);

        BookModel model4 = new BookModel("Dom Casmurro", "Machado de Assis", "Editora Ática",
                "9788508101079", 1997, "Romance, Clássico", false);

        BookModel model5 = new BookModel("A Culpa é das Estrelas", "John Green", "Intrínseca",
                "9788580572261", 2012, "Romance, Jovem Adulto", true);

        BookModel model6 = new BookModel("O Código Da Vinci", "Dan Brown", "Sextante",
                "8575422397", 2004, "Suspense, Mistério", true);

        BookModel model7 = new BookModel("Pequeno Príncipe", "Antoine de Saint-Exupéry", "Agir",
                "9788522005237", 2009, "Infantil, Filosófico", false);

        BookView view = new BookView();
    }

    @Test
    void registerBookTest() {
        BookModel model = new BookModel("A Revolução dos Bichos", "George Orwell", "Companhia das Letras",
                "9788535909555", 2007, "Fábula, Política", true);

    }

}
