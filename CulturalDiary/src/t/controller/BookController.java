package t.controller;

import t.model.BookModel;
import t.model.ReviewModel;
import t.model.repository.BookRepository;
import t.view.BookView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Calendar;

public class BookController {
    BookView bookView = new BookView();
    BookModel bookModel;
    BookRepository bookRepository = new BookRepository();
    ArrayList<BookModel> listOfBooks = bookRepository.getListOfBooks();

    Scanner input = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public void registerBook() {
        String temporaryTitle = validateNewString(1);
        String temporaryAuthor = validateNewString(2);
        String temporaryPublisher = validateNewString(3);
        String temporaryIsbn = validateNewIsbn();
        int temporaryYearOfPublication = validateNewYear();
        String temporaryGenre = validateNewString(6);
        boolean temporaryHasCopy = validateNewBoolean();

        bookModel = new BookModel(temporaryTitle, temporaryAuthor, temporaryPublisher,
                                  temporaryIsbn, temporaryYearOfPublication, temporaryGenre,
                                  temporaryHasCopy);

        bookRepository.addBook(bookModel);

        int index = bookModel.getBookIndex();
        bookView.registrationMessages(8, index);

        displayAdditionalRegisterOptions();
    }

    public String validateNewString(int a) {
        String value;

        do {
            bookView.registrationMessages(a, 0);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                bookView.emptyValue();
            }
        } while (value.isEmpty());

        return value;
    }

    public String validateNewIsbn() {
        String value;

        do {
            bookView.registrationMessages(4, 0);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                bookView.emptyValue();
            }
        } while (value.isEmpty());

        String[] parts = value.split("-");
        if (parts.length != 5) {
            bookView.invalidIsbn(2);
            return validateNewIsbn();
        }

        String prefix = parts[0];
        String group = parts[1];
        String registrant = parts[2];
        String publication = parts[3];
        String checkDigit = parts[4];

        if (!(prefix.equals("978") || prefix.equals("979")) ||
                group.length() < 1 || group.length() > 5 ||
                registrant.length() < 1 || registrant.length() > 7 ||
                publication.length() < 1 || publication.length() > 6 ||
                checkDigit.length() != 1 || !checkDigit.matches("\\d")) {

            bookView.invalidIsbn(2);
            return validateNewIsbn();
        }

        String fullIsbn = prefix + group + registrant + publication + checkDigit;
        if (fullIsbn.length() != 13 || !fullIsbn.matches("\\d{13}")) {
            bookView.invalidIsbn(2);
            return validateNewIsbn();
        };

        for (BookModel b : listOfBooks) {
            if (b.getIsbn().equalsIgnoreCase(value)) {
                bookView.invalidIsbn(1);
                return validateNewIsbn();
            }
        }

        return value;
    }

    public int validateNewYear() {
        int value = 0;
        String stringValue;
        int currentYear = calendar.get(Calendar.YEAR);

        do {
            bookView.registrationMessages(5, 0);
            stringValue = input.nextLine().trim();
            if (stringValue.isEmpty()) {
                bookView.emptyValue();
            }
        } while (stringValue.isEmpty());

        try {
            value = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            bookView.invalidYear(1);
            return validateNewYear();
        }

        if (value < 1700 || value > currentYear) {
            bookView.invalidYear(2);
            return validateNewYear();
        }

        return value;
    }

    public boolean validateNewBoolean() {
        String stringValue;

        do {
            bookView.registrationMessages(7, 0);
            stringValue = input.nextLine().trim();

            if (stringValue.isEmpty()) {
                bookView.emptyValue();
                continue;
            }

            if (stringValue.equalsIgnoreCase("sim") || stringValue.equalsIgnoreCase("s")) {
                return true;
            } else if (stringValue.equalsIgnoreCase("não") || stringValue.equalsIgnoreCase("nao") || stringValue.equalsIgnoreCase("n")) {
                return false;
            } else {
                bookView.invalidHasCopy();
            }

        } while (true);
    }

    public boolean displayAdditionalRegisterOptions() {
        bookView.additionalRegisterOptions();
        bookView.chooseAnOption();

        String option = input.nextLine().trim();

        if (option.isEmpty()) {
            bookView.emptyValue();
            return displayAdditionalRegisterOptions();
        }

        if (option.equals("1")
                || option.equalsIgnoreCase("abrir")
                || option.equalsIgnoreCase("abrir livro")){
            openBook(1, 1);
        }
        else if (option.equals("2")
                || option.equalsIgnoreCase("avaliar")
                || option.equalsIgnoreCase("avaliar livro")) {
            openBook(1, 0);
        } else if (option.equals("3")
                || option.equalsIgnoreCase("cadastrar")
                || option.equalsIgnoreCase("cadastrar livro")) {
           registerBook();
        } else if (option.equals("4")
                || option.equalsIgnoreCase("menu")
                || option.equalsIgnoreCase("menu inicial")){
            return true;
        }
        else {
             bookView.invalidMessage();
             return displayAdditionalRegisterOptions();
        }
        return true;
    }

    public boolean searchBook() {
        if (listOfBooks.isEmpty()) {
            bookView.emptyList();
        }
        else {
            bookView.searchMessages(1);
            bookView.chooseAnOption();
            String searchOption = input.nextLine().trim();

            if (searchOption.isEmpty()) {
                bookView.emptyValue();
                return searchBook();
            }

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

                String bookSearchInformation;
                do {
                    bookView.searchMessages(2);
                    bookSearchInformation = input.nextLine().trim();

                    if (bookSearchInformation.isEmpty()) {
                        bookView.emptyValue();
                    }
                } while (bookSearchInformation.isEmpty());


                boolean bookFound = false;
                for (BookModel b : listOfBooks) {
                    boolean match = false;

                    if ((searchOption.equals("1")
                            || searchOption.equalsIgnoreCase("titulo")
                            || searchOption.equalsIgnoreCase("título"))
                            && b.getTitle().equalsIgnoreCase(bookSearchInformation)) {
                        match = true;
                    }
                    else if ((searchOption.equals("2")
                            || searchOption.equalsIgnoreCase("autor"))
                            && b.getAuthor().equalsIgnoreCase(bookSearchInformation)) {
                        match = true;
                    }
                    else if ((searchOption.equals("3")
                            || searchOption.equalsIgnoreCase("genero")
                            || searchOption.equalsIgnoreCase("gênero"))
                            && b.getGenre().equalsIgnoreCase(bookSearchInformation)) {
                        match = true;
                    }
                    else if (searchOption.equals("4")
                            || searchOption.equalsIgnoreCase("ano")
                            || searchOption.equalsIgnoreCase("ano de publicação")
                            || searchOption.equalsIgnoreCase("ano de publicaçao")
                            || searchOption.equalsIgnoreCase("ano de publicacão")
                            || searchOption.equalsIgnoreCase("ano de publicacao")) {
                        try {
                            if (b.getYearOfPublication() == Integer.parseInt(bookSearchInformation)) {
                                match = true;
                            }
                        } catch (NumberFormatException x) {
                        }
                    }
                    else if ((searchOption.equals("5")
                            || searchOption.equalsIgnoreCase("isbn"))
                            && b.getIsbn().equalsIgnoreCase(bookSearchInformation)) {
                        match = true;
                    }

                    if (match) {
                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }
                        bookView.bookInformation(b);
                    }
                }

                if (!bookFound) {
                    bookView.searchMessages(3);
                    displayAdditionalSearchOptions();
                }
                else {
                    displayAdditionalSearchOptions();
                }
            } else if (searchOption.equals("6")
                    || searchOption.equalsIgnoreCase("menu")
                    || searchOption.equalsIgnoreCase("menu inicial")) {
                return true;
            }
            else {
                bookView.invalidMessage();
                return searchBook();
            }
        }
        return true;
    }

    public boolean displayAdditionalSearchOptions() {
        bookView.additionalSearchOptions();

        bookView.chooseAnOption();
        String option = input.nextLine().trim();

        if (option.isEmpty()) {
            bookView.emptyValue();
            return displayAdditionalSearchOptions();
        }

        if (option.equals("1")
                || option.equalsIgnoreCase("abrir")
                || option.equalsIgnoreCase("abrir livro")) {
            openBook(2, 1);
        } else if (option.equals("2")
                || option.equalsIgnoreCase("avaliar")
                || option.equalsIgnoreCase("avaliar livro")) {
            openBook(2, 0);
        } else if (option.equals("3")
                || option.equalsIgnoreCase("buscar")
                || option.equalsIgnoreCase("buscar livro")) {
            searchBook();
        } else if (option.equals("4")
                || option.equalsIgnoreCase("menu")
                || option.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            bookView.invalidMessage();
            return displayAdditionalSearchOptions();
        }
        return true;
    }

    public void listBook() {
        if (bookRepository.getListOfBooks().isEmpty()) {
            bookView.emptyList();
        } else {
            bookView.headerForBook();
            for (BookModel book : listOfBooks) {
                bookView.bookInformation(book);
            }
            displayAdditionalListOptions();
        }
    }

    public boolean displayAdditionalListOptions() {
        bookView.additionalListOptions();
        bookView.chooseAnOption();

        String option = input.nextLine().trim();

        if (option.isEmpty()) {
            bookView.emptyValue();
            return displayAdditionalListOptions();
        }

        if (option.equals("1")
                || option.equalsIgnoreCase("abrir")
                || option.equalsIgnoreCase("abrir livro")) {
            openBook(3, 1);
        } else if (option.equals("2")
                || option.equalsIgnoreCase("avaliar")
                || option.equalsIgnoreCase("avaliar livro")) {
            openBook(3, 0);
        } else if (option.equals("3")
                || option.equalsIgnoreCase("ordenar")
                || option.equalsIgnoreCase("ordenar livro")) {
            sortList();
        } else if (option.equals("4")
                || option.equalsIgnoreCase("filtrar")
                || option.equalsIgnoreCase("filtrar livro")) {
            filterList();
        } else if (option.equals("5")
                || option.equalsIgnoreCase("listar")
                || option.equalsIgnoreCase("listar livro")) {
            listBook();
        } else if (option.equals("6")
                || option.equalsIgnoreCase("menu")
                || option.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            bookView.invalidMessage();
            return displayAdditionalListOptions();
        }
        return true;
    }

    public boolean sortList() {
        ArrayList<BookModel> listOfReviewedBooks = new ArrayList<BookModel>();
        ArrayList<BookModel> listOfUnreviewedBooks = new ArrayList<BookModel>();

        for (BookModel b : listOfBooks) {
            if (b.getBookReview() != null) {
                listOfReviewedBooks.add(b);
            } else {
                listOfUnreviewedBooks.add(b);
            }
        }

        ArrayList<BookModel> highlyEvaluatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);
        ArrayList<BookModel> poorlyEvaluatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);

        if (listOfReviewedBooks.isEmpty()) {
            bookView.orderingMessage(0);
            displayAdditionalListOptions();
        } else {
            String chosenOrder;
            do {
                bookView.orderingMessage(1);
                bookView.chooseAnOption();
                chosenOrder = input.nextLine().trim();

                if (chosenOrder.isEmpty()) {
                    bookView.emptyValue();
                }
            } while (chosenOrder.isEmpty());

            if (chosenOrder.equals("1")
                    || chosenOrder.equalsIgnoreCase("bem")
                    || chosenOrder.equalsIgnoreCase("bem avaliado")) {
                highlyEvaluatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore(), Comparator.reverseOrder()));

                bookView.headerForBook();
                for (BookModel b : highlyEvaluatedBooks) {
                    bookView.bookInformation(b);
                }
                for (BookModel b : listOfUnreviewedBooks) {
                    bookView.bookInformation(b);
                }

                displayAdditionalListOptions();

            } else if (chosenOrder.equals("2")
                    || chosenOrder.equalsIgnoreCase("mal")
                    || chosenOrder.equalsIgnoreCase("mal avaliado")) {
                poorlyEvaluatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore()));

                bookView.headerForBook();
                for (BookModel b : poorlyEvaluatedBooks) {
                    bookView.bookInformation(b);
                }
                for (BookModel b : listOfUnreviewedBooks) {
                    bookView.bookInformation(b);
                }

                displayAdditionalListOptions();

            } else if (chosenOrder.equals("3")
                    || chosenOrder.equalsIgnoreCase("menu")
                    || chosenOrder.equalsIgnoreCase("menu inicial")) {
                return true;
            } else {
                bookView.invalidMessage();
                return sortList();
            }
        }
        return true;
    }

    public boolean filterList() {
        bookView.filterOptions(0);

        bookView.chooseAnOption();
        String chosenFilter = input.nextLine().trim();

        if (chosenFilter.isEmpty()) {
            bookView.emptyValue();
            return filterList();
        }

        if (chosenFilter.equals("1")
                || chosenFilter.equalsIgnoreCase("gênero")
                || chosenFilter.equalsIgnoreCase("genero")) {

            String chosenGenre;
            do {
                bookView.filterOptions(1);
                chosenGenre = input.nextLine().trim();

                if (chosenGenre.isEmpty()) {
                    bookView.emptyValue();
                }
            } while (chosenGenre.isEmpty());


            boolean bookByGenreFound = false;
            for (BookModel book : listOfBooks) {
                if (book.getGenre().equalsIgnoreCase(chosenGenre)) {
                    if (!bookByGenreFound) {
                        bookView.headerForBook();
                        bookByGenreFound = true;
                    }
                    bookView.bookInformation(book);
                }
            }

            if (!bookByGenreFound) {
                bookView.filterOptions(3);
            }

            displayAdditionalListOptions();
        } else if (chosenFilter.equals("2")
                || chosenFilter.equalsIgnoreCase("ano")
                || chosenFilter.equalsIgnoreCase("ano de publicação")
                || chosenFilter.equalsIgnoreCase("ano de publicaçao")
                || chosenFilter.equalsIgnoreCase("ano de publicacão")
                || chosenFilter.equalsIgnoreCase("ano de publicacao")) {

            String chosenYearOfPublication;
            do {
                bookView.filterOptions(2);
                chosenYearOfPublication = input.nextLine().trim();

                if (chosenYearOfPublication.isEmpty()) {
                    bookView.emptyValue();
                }
            } while (chosenYearOfPublication.isEmpty());


            boolean bookByYearFound = false;

            for (BookModel book : listOfBooks) {
                try {
                    if (String.valueOf(book.getYearOfPublication()).equalsIgnoreCase(chosenYearOfPublication)) {
                        if (!bookByYearFound) {
                            bookView.headerForBook();
                            bookByYearFound = true;
                        }
                        bookView.bookInformation(book);
                    }
                } catch (Exception e) {
                }
            }

            if (!bookByYearFound) {
                bookView.filterOptions(3);
            }

            displayAdditionalListOptions();
        } else if (chosenFilter.equals("3")
                || chosenFilter.equalsIgnoreCase("menu")
                || chosenFilter.equalsIgnoreCase("menu inicial")) {
            return true;
        }
        else {
            bookView.invalidMessage();
            return filterList();
        }
        return true;
    }

    public void openBookReviewer(int a) {
        int chosenBook = -1;
        boolean validInput = false;

        while (!validInput) {
            bookView.reviewMessages(0);
            String chosenBookString = input.nextLine();

            if (chosenBookString.isEmpty()) {
                bookView.emptyValue();
                continue;
            }

            try {
                chosenBook = Integer.parseInt(chosenBookString);
                validInput = true;
            } catch (NumberFormatException e) {
                bookView.reviewMessages(2);
            }
        }

        boolean chosenBookFound = false;
        for (BookModel b : listOfBooks) {
            if (b.getBookIndex() == chosenBook) {
                checkBookReview(b);
                chosenBookFound = true;
            }
        }

        if (!chosenBookFound) {
            bookView.reviewMessages(1);
        }
    }

    public boolean checkBookReview(BookModel book) {

        if (!book.isEvaluatedBook()) {

            String reading;
            do {
                bookView.evaluationMessages(0);
                reading = input.nextLine().trim();

                if (reading.isEmpty()) {
                    bookView.emptyValue();
                }
            } while (reading.isEmpty());

            if (reading.equalsIgnoreCase("s")
                    || reading.equalsIgnoreCase("sim")
                    || reading.equalsIgnoreCase("li")
                    || reading.equalsIgnoreCase("ja")
                    || reading.equalsIgnoreCase("já")) {
                evaluateBook(book);
            }
            else if (reading.equalsIgnoreCase("n")
                    || reading.equalsIgnoreCase("não")
                    || reading.equalsIgnoreCase("nao")
                    || reading.equalsIgnoreCase("n li")
                    || reading.equalsIgnoreCase("não li")
                    || reading.equalsIgnoreCase("nao li")){
                bookView.evaluationMessages(1);
                return true;
            } else {
                bookView.evaluationMessages(8);
                return checkBookReview(book);
            }
        } else {
            String evaluateAgain;
            do {
                bookView.evaluationMessages(2);
                evaluateAgain = input.nextLine().trim();

                if (evaluateAgain.isEmpty()) {
                    bookView.emptyValue();
                }
            } while (evaluateAgain.isEmpty());


            if (evaluateAgain.equalsIgnoreCase("s")
                    || evaluateAgain.equalsIgnoreCase("sim")
                    || evaluateAgain.equalsIgnoreCase("quero")) {
                evaluateBook(book);
            } else if (evaluateAgain.equalsIgnoreCase("n")
                    || evaluateAgain.equalsIgnoreCase("não")
                    || evaluateAgain.equalsIgnoreCase("nao")
                    || evaluateAgain.equalsIgnoreCase("não quero")
                    || evaluateAgain.equalsIgnoreCase("nao quero")) {
                return true;
            }
            else {
                bookView.invalidMessage();
                return checkBookReview(book);
            }
        }
        return true;
    }

    public void evaluateBook(BookModel book) {
        String temporaryConsumptionDate;
        String temporaryComment;
        float temporaryScore = 0.0f;

        while (true) {
            bookView.evaluationMessages(3);
            String scoreString = input.nextLine().trim();

            if (scoreString.isEmpty()) {
                bookView.emptyValue();
                continue;
            }

            try {
                temporaryScore = Float.parseFloat(scoreString);
                if (temporaryScore < 1 || temporaryScore > 5) {
                    bookView.evaluationMessages(4);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                bookView.evaluationMessages(4);
            }
        }

        temporaryConsumptionDate = validateNewDate();

        do {
            bookView.evaluationMessages(6);
            temporaryComment = input.nextLine().trim();

            if (temporaryComment.isEmpty()) {
                bookView.emptyValue();
            }
        } while (temporaryComment.isEmpty());

        bookView.evaluationMessages(7);

        ReviewModel bookReview = new ReviewModel(true, temporaryScore, temporaryConsumptionDate, temporaryComment);
        book.setBookReview(bookReview);
        book.setEvaluatedBook(true);
    }

    public String validateNewDate() {
        String stringValue;
        int currentYear = calendar.get(Calendar.YEAR);

        do {
            bookView.evaluationMessages(5);
            stringValue = input.nextLine().trim();

            if (stringValue.isEmpty()) {
                bookView.emptyValue();
            }
        } while (stringValue.isEmpty());

        String[] parts = stringValue.split("/");
        if (parts.length != 3) {
            bookView.invalidDate(2, 0);
            return validateNewDate();
        }

        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
            bookView.invalidDate(2, 0);
            return validateNewDate();
        }

        int valid = validateExistingDate(day, month, year);

        if (valid == 0 || valid == 2) {
            return validateNewDate();
        }

        int yearOfPublication = bookModel.getYearOfPublication();

        try {
            int yearInt = Integer.parseInt(year);

            if (yearInt < yearOfPublication) {
                bookView.invalidDate(3, yearOfPublication);
                return validateNewDate();
            }
        } catch (NumberFormatException e) {
            bookView.invalidDate(2, 0);
            return validateNewDate();
        }

        return stringValue;
    }

    public int validateExistingDate(String day, String month, String year) {
        try {
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);

            if (m < 1 || m > 12) {
                bookView.invalidDate(2, 0);
                return 2;
            }

            m = m - 1;

            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.set(y, m, d);
            cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            if (cal.after(today)) {
                bookView.invalidDate(1, 0);
                return 0;
            }

            return 1;

        } catch (Exception e) {
            bookView.invalidDate(2, 0);
            return 2;
        }
    }

    public void openBook(int a, int b) {
        if (b == 0) {
            openBookReviewer(a);
        } else if (b == 1) {
            displayMoreInformation(a);
        }

        if (a == 1) {
            displayAdditionalRegisterOptions();
        } else if (a == 2) {
            displayAdditionalSearchOptions();
        } else {
            displayAdditionalListOptions();
        }
    }

    public void displayMoreInformation(int a) {
        int chosenBook = -1;
        boolean validInput = false;

        while (!validInput) {
            bookView.reviewMessages(0);
            String chosenBookString = input.nextLine();

            if (chosenBookString.isEmpty()) {
                bookView.emptyValue();
                continue;
            }

            try {
                chosenBook = Integer.parseInt(chosenBookString);
                validInput = true;
            } catch (NumberFormatException e) {
                bookView.reviewMessages(2);
            }
        }

        boolean chosenBookFound = false;
        for (BookModel b : listOfBooks) {
            if (b.getBookIndex() == chosenBook) {
                bookView.moreInformation(b);
                chosenBookFound = true;
            }
        }

        if (!chosenBookFound) {
            bookView.reviewMessages(1);
        }
    }
}