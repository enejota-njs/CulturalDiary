package antigo.book;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class BookManager {
    private static ArrayList<Book> listOfBooks = new ArrayList<Book>();

    private Scanner input = new Scanner(System.in);

    public void registerBook() {
        String temporaryTitle;
        String temporaryAuthor;
        String temporaryPublisher;
        String temporaryIsbn;
        int temporaryYearOfPublication;
        String temporaryGenre;
        boolean temporaryHasCopy;

        System.out.print("\nTítulo: ");
        temporaryTitle = input.nextLine().trim();

        System.out.print("Autor: ");
        temporaryAuthor = input.nextLine().trim();

        System.out.print("Editora: ");
        temporaryPublisher = input.nextLine().trim();

        while (true) {
            System.out.print("ISBN: ");
            temporaryIsbn = input.nextLine().trim();

            boolean identicalBook = false;
            if (!listOfBooks.isEmpty()) {
                for (Book b : listOfBooks) {
                    if (b.getIsbn().equalsIgnoreCase(temporaryIsbn)) {
                        identicalBook = true;
                    }
                }
            }

            if (identicalBook) {
                System.out.println("\n+--------------------+");
                System.out.println("| ISBN já cadastrado |");
                System.out.println("+--------------------+");
                System.out.println();
            }
            else break;
        }

        System.out.print("Ano de publicação: ");
        temporaryYearOfPublication = input.nextInt();
        input.nextLine();

        System.out.print("Gênero: ");
        temporaryGenre = input.nextLine().trim();

        System.out.print("Tem exemplar ? ");
        String copy = input.nextLine().trim();
        temporaryHasCopy = copy.equalsIgnoreCase("s")
                || copy.equalsIgnoreCase("sim")
                || copy.equalsIgnoreCase("tenho");

        Book book = new Book(temporaryTitle, temporaryAuthor, temporaryPublisher, temporaryIsbn,
                temporaryYearOfPublication, temporaryGenre, temporaryHasCopy);

        listOfBooks.add(book);

        System.out.println("\n+------------------+");
        System.out.println("| Livro cadastrado |");
        System.out.println("+------------------+");

        displayAdditionalRegisterOptions();
    }

    public void displayAdditionalRegisterOptions() {
        System.out.println("\n[ 1 ] - Cadastrar novamente ");
        System.out.println("[ 2 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String registerAgain = input.nextLine().trim();

        if (registerAgain.equals("1")
                || registerAgain.equalsIgnoreCase("cadastrar")
                || registerAgain.equalsIgnoreCase("cadastrar novamente")
                || registerAgain.equalsIgnoreCase("novamente")) {
            registerBook();
        } else if (registerAgain.equals("2")
                || registerAgain.equalsIgnoreCase("voltar")) {
            return;
        } else {
            System.out.println("\n+----------+");
            System.out.println("| Inválido |");
            System.out.println("+----------+");
            displayAdditionalRegisterOptions();
        }
    }

    public void searchBook() {

        if (listOfBooks.isEmpty()) {
            System.out.println("\n+-------------------------+");
            System.out.println("| Nenhum livro cadastrado |");
            System.out.println("+-------------------------+");
        }
        else {
            System.out.println("\n+-------------------------+");
            System.out.printf("| %-23s |\n", "Buscar por");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 1 ] - Título");
            System.out.printf("| %-23s |\n", "[ 2 ] - Autor");
            System.out.printf("| %-23s |\n", "[ 3 ] - Gênero");
            System.out.printf("| %-23s |\n", "[ 4 ] - Ano");
            System.out.printf("| %-23s |\n", "[ 5 ] - ISBN");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 6 ] - Avaliar");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 7 ] - Cancelar");
            System.out.println("+-------------------------+");

            System.out.print("\nEscolha uma opção: ");
            String searchOption = input.nextLine().trim();

            if (searchOption.equals("1")
                    || searchOption.equalsIgnoreCase("titulo")
                    || searchOption.equalsIgnoreCase("título")
                    || searchOption.equals("2")
                    || searchOption.equalsIgnoreCase("autor")
                    || searchOption.equals("3")
                    || searchOption.equalsIgnoreCase("genero")
                    || searchOption.equalsIgnoreCase("gênero")
                    || searchOption.equals("4")
                    || searchOption.equalsIgnoreCase("ano")
                    || searchOption.equalsIgnoreCase("ano de publicação")
                    || searchOption.equalsIgnoreCase("ano de publicaçao")
                    || searchOption.equalsIgnoreCase("ano de publicacão")
                    || searchOption.equalsIgnoreCase("ano de publicacao")
                    || searchOption.equals("5")
                    || searchOption.equalsIgnoreCase("isbn")) {

                System.out.print("\nEscreva a informação do livro: ");
                String bookSearchInformation = input.nextLine().trim();

                boolean bookFound = false;
                for (Book b : listOfBooks) {
                    if ((searchOption.equals("1")
                            || searchOption.equalsIgnoreCase("titulo")
                            || searchOption.equalsIgnoreCase("título"))
                            && b.getTitle().equalsIgnoreCase(bookSearchInformation)) {
                        b.displayInformation();
                        b.displayReview();
                        bookFound = true;
                    }
                    else if ((searchOption.equals("2")
                            || searchOption.equalsIgnoreCase("autor"))
                            && b.getAuthor().equalsIgnoreCase(bookSearchInformation)) {
                        b.displayInformation();
                        b.displayReview();
                        bookFound = true;
                    }
                    else if ((searchOption.equals("3")
                            || searchOption.equalsIgnoreCase("genero")
                            || searchOption.equalsIgnoreCase("gênero"))
                            && b.getGenre().equalsIgnoreCase(bookSearchInformation)) {
                        b.displayInformation();
                        b.displayReview();
                        bookFound = true;
                    }
                    else if (searchOption.equals("4")
                            || searchOption.equalsIgnoreCase("ano")
                            || searchOption.equalsIgnoreCase("ano de publicação")
                            || searchOption.equalsIgnoreCase("ano de publicaçao")
                            || searchOption.equalsIgnoreCase("ano de publicacão")
                            || searchOption.equalsIgnoreCase("ano de publicacao")) {

                        try {
                            if (b.getYearOfPublication() == Integer.parseInt(bookSearchInformation)) {
                                b.displayInformation();
                                b.displayReview();
                                bookFound = true;
                            }
                        } catch (NumberFormatException x) {
                        }
                    }
                    else if ((searchOption.equals("5")
                            || searchOption.equalsIgnoreCase("isbn"))
                            && b.getIsbn().equalsIgnoreCase(bookSearchInformation)) {
                        b.displayInformation();
                        b.displayReview();
                        bookFound = true;
                    }

                }

                if (!bookFound) {
                    System.out.println("\n+-------------------------+");
                    System.out.println("| Nenhum livro encontrado |");
                    System.out.println("+-------------------------+");

                    while (true) {
                        System.out.println("\n[ 1 ] - Buscar novamente");
                        System.out.println("[ 2 ] - Voltar");

                        System.out.print("\nEscolha uma opção: ");
                        String searchAgainOption = input.nextLine().trim();

                        if (searchAgainOption.equals("1")
                                || searchAgainOption.equalsIgnoreCase("buscar")
                                || searchAgainOption.equalsIgnoreCase("buscar novamente")) {
                            searchBook();
                            break;
                        } else if (searchAgainOption.equals("2")
                                || searchAgainOption.equalsIgnoreCase("voltar")) {
                            break;
                        } else {
                            System.out.println("\n+----------+");
                            System.out.println("| Inválido |");
                            System.out.println("+----------+");
                        }
                    }
                }
                else {
                    displayAdditionalSearchOptions();
                }
            }
            else if (searchOption.equals("6")
                    || searchOption.equalsIgnoreCase("avaliar")) {
                openBookReviewer(1);
            }
            else if (searchOption.equals("7")
                    || searchOption.equalsIgnoreCase("cancelar")) {
                return;
            }
            else {
                System.out.println("\n+----------+");
                System.out.println("| Inválido |");
                System.out.println("+----------+");
                searchBook();
            }
        }
    }

    public void displayAdditionalSearchOptions() {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Buscar novamente");
        System.out.println("[ 3 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String searchOrReviewOption = input.nextLine().trim();

        if (searchOrReviewOption.equals("1")
                || searchOrReviewOption.equalsIgnoreCase("avaliar")) {
            openBookReviewer(1);
        }
        else if (searchOrReviewOption.equals("2")
                || searchOrReviewOption.equalsIgnoreCase("buscar")
                || searchOrReviewOption.equalsIgnoreCase("buscar novamente")) {
            searchBook();
        }
        else if (searchOrReviewOption.equals("3")
                || searchOrReviewOption.equalsIgnoreCase("voltar")) {
            return;
        }
        else {
            System.out.println("\n+----------+");
            System.out.println("| Inválido |");
            System.out.println("+----------+");
            displayAdditionalSearchOptions();
        }
    }

    public void listBook() {

        if (listOfBooks.isEmpty()) {
            System.out.println("\n+-------------------------+");
            System.out.println("| Nenhum livro cadastrado |");
            System.out.println("+-------------------------+");
        } else {
            for (Book b : listOfBooks) {
                b.displayInformation();
                b.displayReview();
            }
            displayAdditionalListOptions(listOfBooks);
        }
    }

    public void displayAdditionalListOptions(ArrayList<Book> currentList) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")
                || listTypeOption.equalsIgnoreCase("avaliar")) {
            openBookReviewer(2);
        } else if (listTypeOption.equals("2")
                || listTypeOption.equalsIgnoreCase("ordenar")) {
            sortList();
        } else if (listTypeOption.equals("3")
                || listTypeOption.equalsIgnoreCase("filtrar")) {
            filterList(currentList);
        }
        else if (listTypeOption.equals("4")
                || listTypeOption.equalsIgnoreCase("voltar")) {
            return;
        } else {
            System.out.println("\n+----------+");
            System.out.println("| Inválido |");
            System.out.println("+----------+");
            displayAdditionalListOptions(currentList);
        }
    }

    public void sortList() {
        ArrayList<Book> listOfReviewedBooks = new ArrayList<Book>();

        for (Book b : listOfBooks) {
            if (b.getBookReview() != null) {
                listOfReviewedBooks.add(b);
            }
        }

        ArrayList<Book> highlyEvaluatedBooks = new ArrayList<Book>(listOfReviewedBooks);
        ArrayList<Book> poorlyEvaluatedBooks = new ArrayList<Book>(listOfReviewedBooks);

        if (listOfReviewedBooks.isEmpty()) {
            System.out.println("\n+-----------------------+");
            System.out.println("| Nenhum livro avaliado |");
            System.out.println("+-----------------------+");
            displayAdditionalListOptions(listOfBooks);
        } else {
            if (!listOfReviewedBooks.isEmpty()) {
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Ordenar por");
                System.out.println("+-------------------------+");
                System.out.printf("| %-23s |\n", "[ 1 ] - Bem avaliado");
                System.out.printf("| %-23s |\n", "[ 2 ] - Mal avaliado");
                System.out.printf("| %-23s |\n", "[ 3 ] - Cancelar");
                System.out.println("+-------------------------+");

                System.out.print("\nEscolha uma opção: ");
                String chosenOrder = input.nextLine().trim();

                if (chosenOrder.equals("1")
                        || chosenOrder.equalsIgnoreCase("bem")
                        || chosenOrder.equalsIgnoreCase("bem avaliado")) {
                    highlyEvaluatedBooks.sort(Comparator.comparing(book -> book.getBookReview().getScore(), Comparator.reverseOrder()));

                    for (Book b : highlyEvaluatedBooks) {
                        b.displayInformation();
                        b.displayReview();
                    }

                    displayAdditionalListOptions(highlyEvaluatedBooks);
                } else if (chosenOrder.equals("2")
                        || chosenOrder.equalsIgnoreCase("mal")
                        || chosenOrder.equalsIgnoreCase("mal avaliado")) {
                    poorlyEvaluatedBooks.sort(Comparator.comparing(book -> book.getBookReview().getScore()));

                    for (Book b : poorlyEvaluatedBooks) {
                        b.displayInformation();
                        b.displayReview();
                    }

                    displayAdditionalListOptions(poorlyEvaluatedBooks);
                } else if (chosenOrder.equals("3")
                        || chosenOrder.equalsIgnoreCase("cancelar")) {
                    return;
                } else {
                    System.out.println("\n+----------+");
                    System.out.println("| Inválido |");
                    System.out.println("+----------+");
                    sortList();
                }
            }
        }
    }

    public void filterList(ArrayList<Book> currentList) {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Filtrar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Gênero");
        System.out.printf("| %-23s |\n", "[ 2 ] - Ano");
        System.out.printf("| %-23s |\n", "[ 3 ] - Cancelar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String chosenFilter = input.nextLine().trim();

        if (chosenFilter.equals("1")
                || chosenFilter.equalsIgnoreCase("gênero")
                || chosenFilter.equalsIgnoreCase("genero")) {

            System.out.print("\nDigite o gênero: ");
            String chosenGenre = input.nextLine().trim();

            boolean bookByGenreFound = false;
            for ( Book b : currentList) {
                if (b.getGenre().equalsIgnoreCase(chosenGenre)) {
                    b.displayInformation();
                    b.displayReview();
                    bookByGenreFound = true;
                }
            }

            if (!bookByGenreFound) {
                System.out.println("\n+-------------------------+");
                System.out.println("| Nenhum livro encontrado |");
                System.out.println("+-------------------------+");
            }

            displayAdditionalListOptions(currentList);
        }
        else if (chosenFilter.equals("2")
                || chosenFilter.equalsIgnoreCase("ano")
                || chosenFilter.equalsIgnoreCase("ano de publicação")
                || chosenFilter.equalsIgnoreCase("ano de publicaçao")
                || chosenFilter.equalsIgnoreCase("ano de publicacão")
                || chosenFilter.equalsIgnoreCase("ano de publicacao")) {

            System.out.print("\nDigite o ano de publicação: ");
            String chosenYearOfPublication = input.nextLine().trim();

            boolean bookByYearFound = false;
            for ( Book b : currentList) {
                try {
                    if (b.getYearOfPublication() == Integer.parseInt(chosenYearOfPublication)) {
                        b.displayInformation();
                        b.displayReview();
                        bookByYearFound = true;
                    }
                } catch (NumberFormatException x) {
                }
            }

            if (!bookByYearFound) {
                System.out.println("\n+-------------------------+");
                System.out.println("| Nenhum livro encontrado |");
                System.out.println("+-------------------------+");
            }
            displayAdditionalListOptions(currentList);
        }
        else if (chosenFilter.equals("3")
                || chosenFilter.equalsIgnoreCase("cancelar")) {
            return;
        }
        else {
            System.out.println("\n+----------+");
            System.out.println("| Inválido |");
            System.out.println("+----------+");
            filterList(currentList);
        }
    }

    public void openBookReviewer(int n) {
        System.out.print("\nEscreva o n° do livro: ");
        int chosenBook = input.nextInt();
        input.nextLine();

        boolean chosenBookFound = false;
        for (Book b : listOfBooks) {
            if (b.getBookIndex() == chosenBook) {
                b.checkBookReview();
                chosenBookFound = true;
            }
        }

        if (!chosenBookFound) {
            System.out.println("\n+----------------------+");
            System.out.println("| Livro não encontrado |");
            System.out.println("+----------------------+");
            displayAdditionalListOptions(listOfBooks);
        }

        if (n == 1) {
            displayAdditionalSearchOptions();
        }
        else {
            displayAdditionalListOptions(listOfBooks);
        }
    }

    public static ArrayList<Book> getListOfBooks() {
        return listOfBooks;
    }

    public static void setListOfBooks(ArrayList<Book> listOfBooks) {
        BookManager.listOfBooks = listOfBooks;
    }
}