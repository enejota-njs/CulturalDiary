package book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import review.ReviewModel;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.reflect.Type;

/**
 * Class responsible for controlling operations related to books.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.1
 */
public class BookController {
    private static BookController instance;

    private BookController() {
        openFile();
    }

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }

    private ArrayList<BookModel> listOfBooks = new ArrayList<BookModel>(); // Lista de livros
    BookView bookView = new BookView(); // Visual da interface
    BookModel bookModel; // Modelo do livro

    Calendar calendar = Calendar.getInstance(); // Calendário atual

    Gson gson = new Gson(); // Instância do Gson para manipulação de JSON
    File repository = new File("src/main/java/book/repository/");  // Diretório do repositório
    File file = new File(repository, "book_file.json"); // Arquivo JSON dentro do repositório


    /**
     * Registers a book in the system.
     *
     * @param title Book title.
     * @param author Book author.
     * @param publisher Book publisher.
     * @param isbn Book ISBN code.
     * @param yearOfPublicationString Year of publication (as string).
     * @param genre Book genre.
     * @param hasCopyString "yes" if a copy is available, "no" otherwise.
     * @param readString "yes" if the book was read, "no" otherwise.
     * @return {@code true} if the book was added; {@code false} if invalid data.
     */
    public boolean registerBook(String title, String author, String publisher,
                                String isbn, String yearOfPublicationString, String genre, String hasCopyString, String readString) {

        title = title.trim();
        author = author.trim();
        publisher = publisher.trim();
        isbn = isbn.trim();
        yearOfPublicationString = yearOfPublicationString.trim();
        genre = genre.trim();
        hasCopyString = hasCopyString.trim();
        readString = readString.trim();

        boolean validTitle = validateTitle(title); // Valida título
        boolean validAuthor = validateAuthor(author); // Valida autor
        boolean validPublisher = validatePublisher(publisher); // Valida editora
        boolean validIsbn = validateIsbn(isbn); // Valida ISBN
        boolean validYearOfPublication = validateYearOfPublication(yearOfPublicationString); // Valida ano
        boolean validGenre = validateGenre(genre); // Valida gênero
        boolean validHasCopy = validateHasCopy(hasCopyString); // Valida se possui cópia
        boolean validRead = validateRead(readString); // Valida se foi lido

        if (validTitle == false || validAuthor == false || validPublisher == false || validIsbn == false || validYearOfPublication == false
                || validGenre == false || validHasCopy == false || validRead == false) {
            bookView.tryAgainMessage(); // Mensagem de erro
            return false;
        }

        try {
            int yearOfPublication = Integer.parseInt(yearOfPublicationString); // Converte ano

            Set<String> positiveResponsesHasCopy = Set.of(
                    "sim", "s", "tenho", "sim tenho", "tenho sim", "s tenho", "tenho s"
            ); // Respostas positivas para cópia

            Set<String> negativeResponsesHasCopy = Set.of(
                    "não", "nao", "n", "não tenho", "nao tenho", "n tenho",
                    "tenho não", "tenho nao", "tenho n"
            ); // Respostas negativas para cópia

            boolean hasCopy = false;
            if (positiveResponsesHasCopy.contains(hasCopyString.toLowerCase())) { hasCopy = true; }
            else if (negativeResponsesHasCopy.contains(hasCopyString.toLowerCase())) { hasCopy = false; }

            Set<String> positiveResponsesRead = Set.of(
                    "sim", "s", "li", "sim li", "li sim", "s li", "li s", "já li", "ja li", "já", "ja"
            ); // Respostas positivas para leitura

            Set<String> negativeResponsesRead = Set.of(
                    "não", "nao", "n", "não li", "nao li", "n li",
                    "li não", "li nao", "li n"
            ); // Respostas negativas para leitura

            boolean read = false;
            if (positiveResponsesRead.contains(readString.toLowerCase())) { read = true; }
            else if (negativeResponsesRead.contains(readString.toLowerCase())) { read = false; }

            bookModel = new BookModel(title.trim(), author.trim(), publisher.trim(), isbn.trim(), yearOfPublication, genre.trim(), hasCopy, read, listOfBooks.size() + 1); // Cria livro
            listOfBooks.add(bookModel); // Adiciona livro
            saveFile();

            bookView.registeredBookMessage(title); // Mensagem de sucesso

            return true;
        } catch (Exception e) {
            bookView.invalidMessage(); // Mensagem de erro
            return false;
        }
    } // Registra livro

    /**
     * Checks if a new title is valid.
     *
     * @param title New book title.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validateTitle(String title) {
        return validateNewString(title, "Título");
    } // Valida título

    /**
     * Checks if a new author is valid.
     *
     * @param author New book author.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validateAuthor(String author) {
        return validateNewString(author, "Autor");
    } // Valida autor

    /**
     * Checks if a new publisher is valid.
     *
     * @param publisher New book publisher.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validatePublisher(String publisher) {
        return validateNewString(publisher, "Editora");
    } // Valida editora

    /**
     * Checks if a new ISBN is valid.
     *
     * @param isbn New book ISBN.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validateIsbn(String isbn) {
        return validateNewIsbn(isbn);
    } // Valida ISBN

    /**
     * Checks if a new publication year is valid.
     *
     * @param yearOfPublication New book publication year.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validateYearOfPublication(String yearOfPublication) {
        return validateNewYear(yearOfPublication);
    } // Valida ano de publicação

    /**
     * Checks if a new genre is valid.
     *
     * @param genre New book genre.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validateGenre(String genre) {
        return validateNewString(genre, "Gênero");
    } // Valida gênero

    /**
     * Checks if the 'has copy' status is valid.
     *
     * @param hasCopy 'Has copy' status of the new book.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validateHasCopy(String hasCopy) {
        return validateNewHasCopy(hasCopy);
    } // Valida se possui cópia

    /**
     * Checks if the 'read' status is valid.
     *
     * @param read 'Read' status of the new book.
     * @return {@code true} if valid; {@code false} otherwise.
     */
    public boolean validateRead(String read) {
        return validateNewRead(read);
    } // Valida se foi lido

    /**
     * Checks if a new string has any content.
     *
     * @param value The new string to check.
     * @param name The attribute that this string refers to.
     * @return {@code true} if the string has content; {@code false} otherwise.
     */
    public boolean validateNewString(String value, String name) {
        if (value.isEmpty()) {
            bookView.emptyValueMessage(name);
            return false;
        }
        return true;
    } // Valida string não vazia

    /**
     * Checks if the ISBN already exists.
     *
     * @param value The ISBN to be checked.
     * @return {@code true} if the ISBN exists; {@code false} otherwise.
     */
    public boolean validateNewIsbn(String value) {
        if (validateNewString(value, "Isbn")) {
            // Verifica se o valor é composto apenas por números e tem 10 ou 13 caracteres
            if (value.matches("\\d+") && (value.length() == 10 || value.length() == 13)) {

                // Para ISBN de 13 caracteres, verifica se começa com "978" ou "979"
                if (value.length() == 13 && !(value.startsWith("978") || value.startsWith("979"))) {
                    bookView.nonExistentIsbnMessage(); // Exibe mensagem de erro
                    return false;
                }

                // Verifica se o ISBN já foi registrado
                for (BookModel book : listOfBooks) {
                    if (book.getIsbn().equalsIgnoreCase(value)) {
                        bookView.registeredIsbnMessage(); // Exibe mensagem de ISBN já registrado
                        return false;
                    }
                }

                return true;
            }
            else {
                bookView.nonExistentIsbnMessage(); // Exibe mensagem de erro se o ISBN for inválido
                return false;
            }
        }

        return false;
    } // Valida ISBN

    /**
     * Checks if the year exists and is not in the future.
     *
     * @param value The year to be checked.
     * @return {@code true} if the year is valid and not in the future; {@code false} otherwise.
     */
    public boolean validateNewYear(String value) {

        if (validateNewString(value, "Ano de publicação")) {
            int valueInt = 0;
            int currentYear = calendar.get(Calendar.YEAR); // Obtém o ano atual

            try {
                valueInt = Integer.parseInt(value); // Tenta converter o valor para inteiro
            } catch (Exception e) {
                bookView.integerMessage(); // Exibe mensagem de erro se não for um número
                return false;
            }

            // Verifica se o ano é válido (entre 1700 e o ano atual)
            if (valueInt < 1700 || valueInt > currentYear) {
                bookView.invalidYearMessage(currentYear); // Exibe mensagem de ano inválido
                return false;
            }

            return true;
        }

        return false;
    } // Valida ano de publicação

    /**
     * Checks if the 'has copy' status is correct.
     *
     * @param value The 'has copy' status to be checked.
     * @return {@code true} if the status is valid; {@code false} otherwise.
     */
    public boolean validateNewHasCopy(String value) {

        if (validateNewString(value, "Exemplar")) {

            // Respostas válidas para ter ou não exemplar
            Set<String> validAnswers = Set.of(
                    "sim", "s",
                    "não", "nao", "n",
                    "não tenho", "nao tenho", "n tenho", "tenho nao", "tenho não", "tenho n",
                    "sim tenho", "tenho sim", "s tenho", "tenho s", "tenho"
            );

            // Verifica se a resposta está na lista de respostas válidas
            if (validAnswers.contains(value.toLowerCase())) {
                return true;
            } else {
                bookView.invalidHasCopyMessage();
            }
        }

        return false;
    } // Valida exemplar

    /**
     * Validates if the 'read' status is correct.
     *
     * @param value The 'read' status to be validated ("yes" or "no").
     * @return {@code true} if the status is valid; {@code false} otherwise.
     */
    public boolean validateNewRead(String value) {

        if (validateNewString(value, "Leitura")) {

            // Respostas válidas para leitura
            Set<String> validAnswers = Set.of(
                    "sim", "s",
                    "não", "nao", "n",
                    "não li", "nao li", "n li", "li nao", "li não", "li n",
                    "sim li", "li sim", "s li", "li s", "li", "já li", "ja li", "já", "ja"
            );

            // Verifica se a resposta está na lista de respostas válidas
            if (validAnswers.contains(value.toLowerCase())) {
                return true;
            } else {
                bookView.invalidReadMessage();
            }
        }

        return false;
    } // Valida leitura

    /**
     * Searches for a book by its title.
     *
     * @param value The title of the book to search for.
     * @return {@code true} if the search was successful, {@code false} if an error occurred.
     */
    public boolean searchBookByTitle(String value) {
        value = value.trim(); // Remove espaços no início e no fim da string

        if (validateNewInputString(value)) { // Valida a entrada do título

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia

                // Itera sobre os livros procurando pelo título
                for (BookModel book : listOfBooks) {
                    if (book.getTitle().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook(); // Exibe cabeçalho da lista de livros encontrados
                            bookFound = true;
                        }

                        bookView.bookInformation(book); // Exibe informações do livro
                    }
                }

            }
            if (!bookFound) { bookView.noBookFoundMessage(); } // Exibe mensagem se nenhum livro for encontrado
            return true;
        }

        return false;
    } // Busca livro pelo título

    /**
     * Searches for a book by its author.
     *
     * @param value The author of the book to search for.
     * @return {@code true} if the search was performed successfully, {@code false} if an error occurred.
     */
    public boolean searchBookByAuthor(String value) {
        value = value.trim(); // Remove espaços no início e no fim da string

        if (validateNewInputString(value)) { // Valida a entrada do autor

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia

                // Itera sobre os livros procurando pelo autor
                for (BookModel book : listOfBooks) {
                    if (book.getAuthor().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook(); // Exibe cabeçalho da lista de livros encontrados
                            bookFound = true;
                        }

                        bookView.bookInformation(book); // Exibe informações do livro
                    }
                }
            }
            if (!bookFound) { bookView.noBookFoundMessage(); } // Exibe mensagem se nenhum livro for encontrado
            return true;
        }

        return false;
    } // Busca livro pelo autor

    /**
     * Searches for a book by its genre.
     *
     * @param value The genre of the book to search for.
     * @return {@code true} if the search was performed successfully, {@code false} if an error occurred.
     */
    public boolean searchBookByGenre(String value) {
        value = value.trim(); // Remove espaços no início e no fim da string

        if (validateNewInputString(value)) { // Valida a entrada do gênero

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia

                // Itera sobre os livros procurando pelo gênero
                for (BookModel book : listOfBooks) {
                    if (book.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook(); // Exibe cabeçalho da lista de livros encontrados
                            bookFound = true;
                        }

                        bookView.bookInformation(book); // Exibe informações do livro
                    }
                }

            }
            if (!bookFound) { bookView.noBookFoundMessage(); } // Exibe mensagem se nenhum livro for encontrado
            return true;
        }

        return false;
    } // Busca livro pelo gênero

    /**
     * Searches for a book by its year of publication.
     *
     * @param value The year of publication of the book to search for.
     * @return {@code true} if the search was performed successfully, {@code false} if an error occurred.
     */
    public boolean searchBookByYearOfPublication(String value) {
        value = value.trim(); // Remove espaços no início e no fim da string

        if (validateNewInputString(value) && validateNewInputInt(value)) { // Valida a entrada do ano de publicação

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value); // Tenta converter o valor para inteiro
                } catch (Exception e) {
                    bookView.invalidMessage(); // Exibe mensagem de erro se a conversão falhar
                    return false;
                }

                // Itera sobre os livros procurando pelo ano de publicação
                for (BookModel book : listOfBooks) {
                    if (book.getYearOfPublication() == valueInt) {

                        if (!bookFound) {
                            bookView.headerForBook(); // Exibe cabeçalho da lista de livros encontrados
                            bookFound = true;
                        }

                        bookView.bookInformation(book); // Exibe informações do livro
                    }
                }

            }
            if (!bookFound) { bookView.noBookFoundMessage(); } // Exibe mensagem se nenhum livro for encontrado
            return true;
        }

        return false;
    } // Busca livro pelo ano de publicação

    /**
     * Searches for a book by its ISBN.
     *
     * @param value The ISBN of the book to search for.
     * @return {@code true} if the search was performed successfully, {@code false} if an error occurred.
     */
    public boolean searchBookByIsbn(String value) {
        value = value.trim(); // Remove espaços no início e no fim da string

        if (validateNewInputString(value)) { // Valida a entrada do ISBN
            boolean validValue = value.matches("\\d+"); // Verifica se o ISBN contém apenas números

            if ((validValue) && (value.length() == 13 || value.length() == 10)) { // Verifica se o comprimento do ISBN é válido
                boolean bookFound = false;
                if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia

                    // Itera sobre os livros procurando pelo ISBN
                    for (BookModel book : listOfBooks) {
                        if (book.getIsbn().equalsIgnoreCase(value.trim())) {

                            if (!bookFound) {
                                bookView.headerForBook(); // Exibe cabeçalho da lista de livros encontrados
                                bookFound = true;
                            }

                            bookView.bookInformation(book); // Exibe informações do livro
                        }
                    }

                }
                if (!bookFound) {
                    bookView.noBookFoundMessage(); // Exibe mensagem se nenhum livro for encontrado
                }
                return true;
            }
            bookView.nonExistentIsbnMessage(); // Exibe mensagem de erro se o ISBN for inválido
            return false;
        }
        return false;
    } // Busca livro pelo ISBN

    /**
     * Displays the list of books.
     *
     * @return {@code true} if the list is displayed (even if empty); {@code false} if an error occurred.
     */
    public boolean listBooks() {
        try {
            if (listOfBooks.isEmpty()) {
                bookView.emptyListMessage(); // Exibe mensagem se a lista de livros estiver vazia
            } else {
                bookView.headerForBook(); // Exibe cabeçalho da lista de livros
                for (BookModel book : listOfBooks) {
                    bookView.bookInformation(book); // Exibe informações de cada livro
                }
            }
            return true;
        } catch (Exception e) {
            bookView.invalidMessage(); // Exibe mensagem de erro caso ocorra uma exceção
            return false;
        }
    } // Lista todos os livros

    /**
     * Filters the list of books by genre.
     *
     * @param value The genre to filter the books by.
     * @return {@code true} if the filter was applied successfully; {@code false} if an error occurred.
     */
    public boolean filterListOfBooksByGenre(String value) {
        value = value.trim(); // Remove espaços no início e no fim da string

        if (validateNewInputString(value)) { // Valida a entrada do gênero

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia

                // Itera sobre os livros filtrando pelo gênero
                for (BookModel book : listOfBooks) {
                    if (book.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook(); // Exibe cabeçalho da lista de livros encontrados
                            bookFound = true;
                        }

                        bookView.bookInformation(book); // Exibe informações do livro
                    }
                }

            }
            if (!bookFound) { bookView.noBookFoundMessage(); } // Exibe mensagem se nenhum livro for encontrado
            return true;
        }

        return false;
    } // Filtra livros por gênero

    /**
     * Filters the list of books by year.
     *
     * @param value The year to filter the books by.
     * @return {@code true} if the filtering was performed successfully; {@code false} if an error occurred.
     */
    public boolean filterListOfBooksByYearOfPublication(String value) {
        value = value.trim(); // Remove espaços no início e no fim da string

        if (validateNewInputString(value) && validateNewInputInt(value)) { // Valida a entrada e verifica se é um número válido

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value); // Tenta converter o valor para inteiro
                } catch (Exception e) {
                    bookView.invalidMessage(); // Exibe mensagem de erro se a conversão falhar
                    return false;
                }

                // Itera sobre os livros filtrando pelo ano de publicação
                for (BookModel book : listOfBooks) {
                    if (book.getYearOfPublication() == valueInt) {

                        if (!bookFound) {
                            bookView.headerForBook(); // Exibe cabeçalho da lista de livros encontrados
                            bookFound = true;
                        }

                        bookView.bookInformation(book); // Exibe informações do livro
                    }
                }

            }
            if (!bookFound) { bookView.noBookFoundMessage(); } // Exibe mensagem se nenhum livro for encontrado
            return true;
        }

        return false;
    } // Filtra livros por ano de publicação

    /**
     * Sorts the list of books by their rating, displaying the highest-rated books first.
     *
     * @return {@code true} if the sorting and display were successful; {@code false} if an error occurred.
     */
    public boolean sortListByTopRated() {
        try {
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia
                ArrayList<BookModel> listOfReviewedBooks = new ArrayList<BookModel>();

                // Adiciona livros com avaliação na lista listOfReviewedBooks
                for (BookModel book : listOfBooks) {
                    if (book.getBookReview() != null) {
                        listOfReviewedBooks.add(book);
                    }
                }

                ArrayList<BookModel> highlyRatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);

                if (!highlyRatedBooks.isEmpty()) {
                    // Ordena os livros com avaliações pela pontuação (do maior para o menor)
                    highlyRatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore(), Comparator.reverseOrder()));
                } else {
                    bookView.emptyEvaluatedListMessage(); // Exibe mensagem caso não haja livros avaliados
                    return true;
                }

                bookView.headerForBook(); // Exibe cabeçalho da lista de livros
                for (BookModel book : highlyRatedBooks) {
                    bookView.bookInformation(book); // Exibe informações do livro
                }

            } else {
                bookView.emptyListMessage(); // Exibe mensagem caso a lista de livros esteja vazia
            }

            return true;
        } catch (Exception e) {
            bookView.invalidMessage(); // Exibe mensagem de erro caso ocorra uma exceção
            return false;
        }
    } // Ordena lista de livros pelos mais bem avaliados

    /**
     * Sorts the list of books by their rating, displaying the lowest-rated books first.
     *
     * @return {@code true} if the sorting and display were successful; {@code false} if an error occurred.
     */
    public boolean sortListByLowRated() {
        try {
            if (!listOfBooks.isEmpty()) { // Verifica se a lista de livros não está vazia
                ArrayList<BookModel> listOfReviewedBooks = new ArrayList<BookModel>();

                // Adiciona livros com avaliação na lista listOfReviewedBooks
                for (BookModel book : listOfBooks) {
                    if (book.getBookReview() != null) {
                        listOfReviewedBooks.add(book);
                    }
                }

                ArrayList<BookModel> poorlyRatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);

                if (!poorlyRatedBooks.isEmpty()) {
                    // Ordena os livros com avaliações pela pontuação (do menor para o maior)
                    poorlyRatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore()));
                } else {
                    bookView.emptyEvaluatedListMessage(); // Exibe mensagem caso não haja livros avaliados
                    return true;
                }

                bookView.headerForBook(); // Exibe cabeçalho da lista de livros
                for (BookModel book : poorlyRatedBooks) {
                    bookView.bookInformation(book); // Exibe informações do livro
                }

            } else {
                bookView.emptyListMessage(); // Exibe mensagem caso a lista de livros esteja vazia
            }

            return true;
        } catch (Exception e) {
            bookView.invalidMessage(); // Exibe mensagem de erro caso ocorra uma exceção
            return false;
        }
    } // Ordena lista de livros pelos piores avaliados

    /**
     * Opens a book from the list by its index.
     *
     * @param index The index of the book to be opened.
     * @return {@code true} if the book was successfully opened; {@code false} if an error occurred.
     */
    public boolean openBook(int index) {
        try {
            BookModel book;
            try {
                // Tenta obter o livro com o índice fornecido (index - 1, pois o índice começa do 0)
                book = listOfBooks.get(index - 1);
            } catch (Exception e) {
                // Caso o índice seja inválido ou não exista o livro, exibe mensagem
                bookView.noBookFoundMessage();
                return false;
            }

            bookView.fullBookInformation(book); // Exibe as informações completas do livro
            return true;
        } catch (Exception e) {
            bookView.invalidMessage(); // Exibe mensagem de erro caso ocorra uma exceção
            return false;
        }
    } // Abre o livro pela posição fornecida e exibe suas informações completas

    /**
     * Alters the reading status of a book.
     *
     * @param index The index of the book in the list.
     * @param value The new reading status to be set for the book.
     * @return {@code true} if the operation was successful; {@code false} if an error occurred.
     */
    public boolean changeBookReadingStatus(int index, String value) {
        BookModel book;

        try {
            // Tenta obter o livro com o índice fornecido (index - 1, pois o índice começa do 0)
            book = listOfBooks.get(index - 1);
        } catch (Exception e) {
            // Caso o índice seja inválido ou não exista o livro, exibe mensagem
            bookView.noBookFoundMessage();
            return false;
        }

        value = value.trim(); // Remove espaços extras

        if (book == null) {
            // Verifica se o livro é nulo (não encontrado)
            bookView.invalidMessage();
            return false;
        }

        boolean validRead = validateNewRead(value); // Valida o valor de leitura fornecido

        if (!validRead) {
            // Se a validação não for bem-sucedida, solicita ao usuário tentar novamente
            bookView.tryAgainMessage();
            return false;
        }

        try {
            // Conjunto de respostas afirmativas para o status de leitura
            Set<String> positiveResponsesRead = Set.of(
                    "sim", "s", "li", "sim li", "li sim", "s li", "li s", "já li", "ja li", "já", "ja"
            );

            // Conjunto de respostas negativas para o status de leitura
            Set<String> negativeResponsesRead = Set.of(
                    "não", "nao", "n", "não li", "nao li", "n li",
                    "li não", "li nao", "li n"
            );

            boolean read = false;
            if (positiveResponsesRead.contains(value.toLowerCase())) {
                // Se a resposta for afirmativa, define o status como "lido"
                read = true;
            } else if ((book.isRead() == false) && negativeResponsesRead.contains(value.toLowerCase())) {
                // Se o livro não foi lido e a resposta for negativa, define como "não lido"
                read = false;
            } else if ((book.isRead() == true) && negativeResponsesRead.contains(value.toLowerCase())) {
                // Se o livro já foi lido e a resposta for negativa, exibe mensagem de erro
                bookView.wrongReadMessage();
                return false;
            }

            book.setRead(read); // Atualiza o status de leitura do livro
            bookView.updatedReadMessage(); // Exibe mensagem de confirmação da atualização
            saveFile();

            return true;
        } catch (Exception e) {
            // Exibe mensagem de erro caso ocorra alguma exceção
            bookView.invalidMessage();
            return false;
        }
    } // Altera o status de leitura de um livro baseado na resposta do usuário

    /**
     * Rates a book.
     *
     * @param index The index of the book in the list.
     * @param score The rating score of the book.
     * @param consumptionDate The date the book was read.
     * @param comment Comments about the book.
     * @return {@code true} if the book was successfully rated; {@code false} if an error occurred.
     */
    public boolean evaluateBook(int index, String score, String consumptionDate, String comment) {
        try {
            // Limpa espaços em branco das entradas
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            BookModel book;
            try {
                // Tenta acessar o livro com o índice fornecido (index - 1, pois começa em 0)
                book = listOfBooks.get(index - 1);
            } catch (Exception e) {
                // Caso o índice fornecido não seja válido, exibe mensagem e retorna false
                bookView.noBookFoundMessage();
                return false;
            }

            // Verifica se o livro já foi avaliado
            if (!checkBookReview(book)) {
                // Se o livro foi lido, prossegue com a avaliação
                if (book.isRead()) {
                    // Valida a pontuação, data de consumo e comentário
                    boolean validScore = validateNewScore(score);
                    boolean validConsumptionDate = validateNewDate(book, consumptionDate);
                    boolean validComment = validateNewString(comment, "Comentários");

                    // Se algum dado for inválido, solicita ao usuário tentar novamente
                    if (validScore == false || validConsumptionDate == false || validComment == false) {
                        bookView.tryAgainMessage();
                        return false;
                    }

                    // Converte a pontuação para float
                    float scoreFloat = 0f;
                    try {
                        scoreFloat = Float.parseFloat(score);
                    } catch (Exception e) {
                        // Se ocorrer erro na conversão da pontuação, exibe mensagem de erro
                        bookView.invalidMessage();
                        return false;
                    }

                    // Cria um novo objeto de avaliação e atribui ao livro
                    ReviewModel reviewModel = new ReviewModel(scoreFloat, consumptionDate, comment);
                    book.setBookReview(reviewModel);
                    book.setEvaluatedBook(true);

                    // Exibe mensagem confirmando que a avaliação foi registrada
                    bookView.registeredEvaluationMessage();
                    saveFile();
                    return true;
                } else {
                    // Se o livro não foi lido, exibe mensagem
                    bookView.unreadBookMessage();
                    return false;
                }
            } else {
                // Se o livro já foi avaliado, exibe mensagem informando que ele já possui avaliação
                bookView.messageOfBookAlreadyEvaluated();
                return false;
            }
        } catch (Exception e) {
            // Exibe mensagem de erro caso alguma exceção seja lançada
            bookView.invalidMessage();
            return false;
        }
    } // Avalia o livro

    /**
     * Re-evaluates a book that has already been rated.
     *
     * @param index The index of the book in the list.
     * @param score The new rating score for the book.
     * @param consumptionDate The new consumption (reading) date of the book.
     * @param comment New comments about the book.
     * @return {@code true} if the book was successfully re-evaluated; {@code false} if an error occurred.
     */
    public boolean evaluateBookAgain(int index, String score, String consumptionDate, String comment) {
        try {
            // Remove espaços extras do score, data de consumo e comentário
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            // Obtém o livro da lista baseado no índice (ajustando para o índice correto)
            BookModel book = listOfBooks.get(index - 1);

            // Verifica se o livro já foi avaliado
            if (checkBookReview(book)) {
                // Marca o livro como não avaliado novamente antes de fazer a avaliação
                book.setEvaluatedBook(false);
                // Chama o método de avaliação do livro
                return evaluateBook(index, score, consumptionDate, comment);
            } else {
                // Exibe uma mensagem caso o livro não tenha avaliação
                bookView.unratedBookMessage();
                return false;
            }
        } catch (Exception e) {
            // Exibe uma mensagem de erro caso ocorra uma exceção
            bookView.invalidMessage();
            return false;
        }

    } // Avalia o livro novamente

    /**
     * Checks if a book has already been rated.
     *
     * @param book The book to be checked.
     * @return {@code true} if the book has already been rated; {@code false} if it has not.
     */
    public boolean checkBookReview(BookModel book) {
        // Verifica se o livro foi avaliado
        if (book.isEvaluatedBook()) {
            return true;
        }
        return false;
    } // Verifica se o livro já foi avaliado

    /**
     * Validates if a string is not empty.
     *
     * @param value The string to be validated.
     * @return {@code true} if the string is not empty; {@code false} if the string is empty.
     */
    public boolean validateNewInputString(String value) {
        if (value.isEmpty()) {
            bookView.emptyInformationMessage(); // Exibe mensagem de informação vazia
            return false;
        }
        return true;
    } // Valida se a string de entrada não está vazia

    /**
     * Validates if a number is an integer.
     *
     * @param value The number to be validated.
     * @return {@code true} if the number is an integer; {@code false} if it is not.
     */
    public boolean validateNewInputInt(String value) {
        try {
            int valueInt = Integer.parseInt(value); // Tenta converter a string para inteiro
            return true;
        } catch (Exception e) {
            bookView.integerMessage();
            return false;
        }
    } // Valida se a string pode ser convertida para um número inteiro

    /**
     * Validates if a new score is valid.
     *
     * @param value The new score to be validated.
     * @return {@code true} if the score is valid; {@code false} if it is not.
     */
    public boolean validateNewScore(String value) {
        value = value.trim(); // Remove espaços extras do valor

        if (validateNewInputString(value)) { // Verifica se a string não está vazia
            try {
                float score = Float.parseFloat(value); // Tenta converter a string para float
                if (score < 1 || score > 5) {
                    bookView.invalidScoreMessage(); // Exibe mensagem de pontuação inválida
                    return false;
                }
                return true;
            } catch (Exception e) {
                bookView.invalidNumberMessage(); // Exibe mensagem de número inválido
                return false;
            }
        }

        return false;
    } // Valida se o valor informado é uma pontuação válida entre 1 e 5

    /**
     * Validates if a new reading date is valid.
     *
     * @param book The book associated with the reading date.
     * @param value The new reading date to be validated.
     * @return {@code true} if the reading date is valid; {@code false} if it is not.
     */
    public boolean validateNewDate(BookModel book, String value) {
        value = value.trim(); // Remove espaços extras do valor

        if (validateNewInputString(value)) { // Verifica se a string não está vazia
            String[] parts = value.split("/"); // Divide a string da data em partes

            if (parts.length != 3) {
                bookView.invalidDateFormatMessage(); // Exibe mensagem de formato inválido
                return false;
            }

            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
                bookView.invalidDateMessage(); // Exibe mensagem de data inválida
                return false;
            }

            boolean valid = validateExistingDate(day, month, year); // Valida se a data realmente existe
            if (!valid) { return false; }

            try {
                int yearInt = Integer.parseInt(year); // Converte o ano para inteiro

                if (yearInt < book.getYearOfPublication()) {
                    bookView.invalidYearPeriodMessage(book.getYearOfPublication()); // Exibe mensagem se o ano for anterior ao de publicação
                    return false;
                }
            } catch (NumberFormatException e) {
                bookView.invalidDateMessage(); // Exibe mensagem de data inválida se o ano não for um número
                return false;
            }

            return true;
        }

        return false;
    } // Valida se a data informada é válida e compatível com o ano de publicação do livro

    /**
     * Validates if a date is valid.
     *
     * @param day The day of the date.
     * @param month The month of the date.
     * @param year The year of the date.
     * @return {@code true} if the date is valid; {@code false} if it is not.
     */
    public boolean validateExistingDate(String day, String month, String year) {
        try {
            int d = Integer.parseInt(day); // Converte o dia para inteiro
            int m = Integer.parseInt(month); // Converte o mês para inteiro
            int y = Integer.parseInt(year); // Converte o ano para inteiro

            if (m < 1 || m > 12) {
                bookView.nonExistentDateMessage(); // Exibe mensagem se o mês for inválido
                return false;
            }

            m = m - 1; // Ajusta o mês para o formato do Calendar (0 a 11)

            Calendar cal = Calendar.getInstance();
            cal.setLenient(false); // Desativa o modo leniente para validar a data corretamente
            cal.set(y, m, d); // Define a data no objeto Calendar
            cal.getTime(); // Verifica se a data é válida

            cal.set(Calendar.HOUR_OF_DAY, 0); // Zera as horas
            cal.set(Calendar.MINUTE, 0); // Zera os minutos
            cal.set(Calendar.SECOND, 0); // Zera os segundos
            cal.set(Calendar.MILLISECOND, 0); // Zera os milissegundos

            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0); // Zera as horas de hoje
            today.set(Calendar.MINUTE, 0); // Zera os minutos de hoje
            today.set(Calendar.SECOND, 0); // Zera os segundos de hoje
            today.set(Calendar.MILLISECOND, 0); // Zera os milissegundos de hoje

            if (cal.after(today)) {
                bookView.invalidFutureDatesMessage(); // Exibe mensagem se a data for no futuro
                return false;
            }

            return true;

        } catch (Exception e) {
            bookView.invalidDateMessage(); // Exibe mensagem de data inválida em caso de exceção
            return false;
        }
    } // Valida se a data é existente e não é no futuro

    /**
     * Opens the file and uploads it if it exists and is not empty.
     */
    public void openFile() {
        // Verifica se o diretório do repositório existe; se não existir, cria o diretório
        if (!repository.exists()) {
            repository.mkdirs();
        }

        // Verifica se o arquivo existe
        if (!file.exists()) {
            try {
                // Tenta criar um novo arquivo
                file.createNewFile();
            } catch (IOException e) {
                // Imprime a stack trace em caso de erro na criação do arquivo
                e.printStackTrace();
            }
        } else {
            // Se o arquivo existir e tiver conteúdo, realiza o upload
            if (file != null && file.length() > 0) {
                uploadFile();
            }
        }
    } // Abri um arquivo do repositório

    /**
     * Uploads the file and loads its contents into the list of books.
     */
    public void uploadFile() {
        try (FileReader reader = new FileReader(file)) {
            // Define o tipo da lista para desserialização do JSON
            Type typeList = new TypeToken<ArrayList<BookModel>>() {}.getType();

            // Carrega a lista de livros a partir do conteúdo JSON do arquivo
            listOfBooks = gson.fromJson(reader, typeList);
        } catch (IOException e) {
            // Imprime a pilha de erros caso ocorra problema na leitura do arquivo
            e.printStackTrace();
        }
    } // Carrega um arquivo do repositório

    /**
     * Saves the list of books to the file in JSON format.
     */
    public void saveFile() {
        try (FileWriter writer = new FileWriter(file)) {
            // Converte a lista de livros para JSON e escreve no arquivo
            gson.toJson(listOfBooks, writer);
        } catch (IOException e) {
            // Imprime a pilha de erros caso ocorra problema na escrita do arquivo
            e.printStackTrace();
        }
    } // Salva um arquivo no repositório

    public ArrayList<BookModel> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(ArrayList<BookModel> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }
}