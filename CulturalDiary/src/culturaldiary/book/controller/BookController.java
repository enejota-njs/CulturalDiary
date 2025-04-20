package culturaldiary.book.controller;

import culturaldiary.book.model.BookModel;
import culturaldiary.book.model.repository.BookRepository;
import culturaldiary.book.view.BookView;
import culturaldiary.media.model.ReviewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Scanner;

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
        bookView.registrationMsg(8, index);

        displayAdditionalRegisterOptions();
    }

    public boolean displayAdditionalRegisterOptions() {
        bookView.additionalRegistrationMsg();
        bookView.chooseAnOptionMsg();

        String menuOption = input.nextLine().trim();

        if (menuOption.isEmpty()) {
            bookView.emptyValueMsg();
            return displayAdditionalRegisterOptions();
        }

        if (menuOption.equals("1")
                || menuOption.equalsIgnoreCase("abrir")
                || menuOption.equalsIgnoreCase("abrir livro")){
            openBook(1, 2);
        }
        else if (menuOption.equals("2")
                || menuOption.equalsIgnoreCase("avaliar")
                || menuOption.equalsIgnoreCase("avaliar livro")) {
            openBook(1, 1);
        } else if (menuOption.equals("3")
                || menuOption.equalsIgnoreCase("cadastrar")
                || menuOption.equalsIgnoreCase("cadastrar livro")) {
            registerBook();
        } else if (menuOption.equals("4")
                || menuOption.equalsIgnoreCase("menu")
                || menuOption.equalsIgnoreCase("menu inicial")){
            return true;
        }
        else {
            bookView.invalidValueMsg();
            return displayAdditionalRegisterOptions();
        }
        return true;
    }

    public boolean searchBook() {
        if (listOfBooks.isEmpty()) {
            bookView.emptyListMsg();
        }
        else {
            bookView.searchMsg(1);
            bookView.chooseAnOptionMsg();
            String searchOption = input.nextLine().trim();

            if (searchOption.isEmpty()) {
                bookView.emptyValueMsg();
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

                String searchInformation;
                do {
                    bookView.searchMsg(2);
                    searchInformation = input.nextLine().toLowerCase().trim();

                    if (searchInformation.isEmpty()) {
                        bookView.emptyValueMsg();
                    }
                } while (searchInformation.isEmpty());


                boolean bookFound = false;
                for (BookModel book : listOfBooks) {
                    boolean match = false;

                    if ((searchOption.equals("1")
                            || searchOption.equalsIgnoreCase("titulo")
                            || searchOption.equalsIgnoreCase("título"))
                            && book.getTitle().toLowerCase().contains(searchInformation)) {
                        match = true;
                    }
                    else if ((searchOption.equals("2")
                            || searchOption.equalsIgnoreCase("autor"))
                            && book.getAuthor().toLowerCase().contains(searchInformation)) {
                        match = true;
                    }
                    else if ((searchOption.equals("3")
                            || searchOption.equalsIgnoreCase("genero")
                            || searchOption.equalsIgnoreCase("gênero"))
                            && book.getGenre().toLowerCase().contains(searchInformation)) {
                        match = true;
                    }
                    else if (searchOption.equals("4")
                            || searchOption.equalsIgnoreCase("ano")
                            || searchOption.equalsIgnoreCase("ano de publicação")
                            || searchOption.equalsIgnoreCase("ano de publicaçao")
                            || searchOption.equalsIgnoreCase("ano de publicacão")
                            || searchOption.equalsIgnoreCase("ano de publicacao")) {
                        try {
                            if (book.getYearOfPublication() == Integer.parseInt(searchInformation)) {
                                match = true;
                            }
                        } catch (NumberFormatException x) {
                        }
                    }
                    else if ((searchOption.equals("5")
                            || searchOption.equalsIgnoreCase("isbn"))
                            && book.getIsbn().equalsIgnoreCase(searchInformation)) {
                        match = true;
                    }

                    if (match) {
                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }
                        bookView.bookInformation(book);
                    }
                }

                if (!bookFound) {
                    bookView.searchMsg(3);
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
                bookView.invalidValueMsg();
                return searchBook();
            }
        }
        return true;
    }

    public boolean displayAdditionalSearchOptions() {
        bookView.additionalSearchMsg();

        bookView.chooseAnOptionMsg();
        String optionMenu = input.nextLine().trim();

        if (optionMenu.isEmpty()) {
            bookView.emptyValueMsg();
            return displayAdditionalSearchOptions();
        }

        if (optionMenu.equals("1")
                || optionMenu.equalsIgnoreCase("abrir")
                || optionMenu.equalsIgnoreCase("abrir livro")) {
            openBook(2, 2);
        } else if (optionMenu.equals("2")
                || optionMenu.equalsIgnoreCase("avaliar")
                || optionMenu.equalsIgnoreCase("avaliar livro")) {
            openBook(2, 1);
        } else if (optionMenu.equals("3")
                || optionMenu.equalsIgnoreCase("buscar")
                || optionMenu.equalsIgnoreCase("buscar livro")) {
            searchBook();
        } else if (optionMenu.equals("4")
                || optionMenu.equalsIgnoreCase("menu")
                || optionMenu.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            bookView.invalidValueMsg();
            return displayAdditionalSearchOptions();
        }
        return true;
    }

    public void listBook() {
        if (listOfBooks.isEmpty()) {
            bookView.emptyListMsg();
        } else {
            bookView.headerForBook();
            for (BookModel book : listOfBooks) {
                bookView.bookInformation(book);
            }
            displayAdditionalListOptions();
        }
    }

    public boolean displayAdditionalListOptions() {
        bookView.additionalListMsg();
        bookView.chooseAnOptionMsg();

        String optionMenu = input.nextLine().trim();

        if (optionMenu.isEmpty()) {
            bookView.emptyValueMsg();
            return displayAdditionalListOptions();
        }

        if (optionMenu.equals("1")
                || optionMenu.equalsIgnoreCase("abrir")
                || optionMenu.equalsIgnoreCase("abrir livro")) {
            openBook(3, 2);
        } else if (optionMenu.equals("2")
                || optionMenu.equalsIgnoreCase("avaliar")
                || optionMenu.equalsIgnoreCase("avaliar livro")) {
            openBook(3, 1);
        } else if (optionMenu.equals("3")
                || optionMenu.equalsIgnoreCase("ordenar")
                || optionMenu.equalsIgnoreCase("ordenar livro")) {
            sortList();
        } else if (optionMenu.equals("4")
                || optionMenu.equalsIgnoreCase("filtrar")
                || optionMenu.equalsIgnoreCase("filtrar livro")) {
            filterList();
        } else if (optionMenu.equals("5")
                || optionMenu.equalsIgnoreCase("listar")
                || optionMenu.equalsIgnoreCase("listar livro")) {
            listBook();
        } else if (optionMenu.equals("6")
                || optionMenu.equalsIgnoreCase("menu")
                || optionMenu.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            bookView.invalidValueMsg();
            return displayAdditionalListOptions();
        }
        return true;
    }

    public boolean filterList() {
        bookView.filterMsg(1);

        bookView.chooseAnOptionMsg();
        String filterOption = input.nextLine().trim();

        if (filterOption.isEmpty()) {
            bookView.emptyValueMsg();
            return filterList();
        }

        if (filterOption.equals("1")
                || filterOption.equalsIgnoreCase("gênero")
                || filterOption.equalsIgnoreCase("genero")) {

            String genreOption
                    ;
            do {
                bookView.filterMsg(2);
                genreOption = input.nextLine().trim();

                if (genreOption.isEmpty()) {
                    bookView.emptyValueMsg();
                }
            } while (genreOption.isEmpty());


            boolean genreFound = false;
            for (BookModel book : listOfBooks) {
                if (book.getGenre().toLowerCase().contains(genreOption)) {
                    if (!genreFound) {
                        bookView.headerForBook();
                        genreFound = true;
                    }
                    bookView.bookInformation(book);
                }
            }

            if (!genreFound) {
                bookView.filterMsg(4);
            }

            displayAdditionalListOptions();
        } else if (filterOption.equals("2")
                || filterOption.equalsIgnoreCase("ano")
                || filterOption.equalsIgnoreCase("ano de publicação")
                || filterOption.equalsIgnoreCase("ano de publicaçao")
                || filterOption.equalsIgnoreCase("ano de publicacão")
                || filterOption.equalsIgnoreCase("ano de publicacao")) {

            String yearOption;
            do {
                bookView.filterMsg(3);
                yearOption = input.nextLine().trim();

                if (yearOption.isEmpty()) {
                    bookView.emptyValueMsg();
                }
            } while (yearOption.isEmpty());


            boolean yearFound = false;

            for (BookModel book : listOfBooks) {
                try {
                    if (String.valueOf(book.getYearOfPublication()).equalsIgnoreCase(yearOption)) {
                        if (!yearFound) {
                            bookView.headerForBook();
                            yearFound = true;
                        }
                        bookView.bookInformation(book);
                    }
                } catch (Exception e) {
                }
            }

            if (!yearFound) {
                bookView.filterMsg(4);
            }

            displayAdditionalListOptions();
        } else if (filterOption.equals("3")
                || filterOption.equalsIgnoreCase("menu")
                || filterOption.equalsIgnoreCase("menu inicial")) {
            return true;
        }
        else {
            bookView.invalidValueMsg();
            return filterList();
        }
        return true;
    }

    public boolean sortList() {
        ArrayList<BookModel> listOfReviewedBooks = new ArrayList<BookModel>();
        ArrayList<BookModel> listOfUnreviewedBooks = new ArrayList<BookModel>();

        for (BookModel book : listOfBooks) {
            if (book.getBookReview() != null) {
                listOfReviewedBooks.add(book);
            } else {
                listOfUnreviewedBooks.add(book);
            }
        }

        ArrayList<BookModel> highlyEvaluatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);
        ArrayList<BookModel> poorlyEvaluatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);

        if (listOfReviewedBooks.isEmpty()) {
            bookView.orderingMsg(1);
            displayAdditionalListOptions();
        } else {
            String sortOption;
            do {
                bookView.orderingMsg(2);
                bookView.chooseAnOptionMsg();
                sortOption = input.nextLine().trim();

                if (sortOption.isEmpty()) {
                    bookView.emptyValueMsg();
                }
            } while (sortOption.isEmpty());

            if (sortOption.equals("1")
                    || sortOption.equalsIgnoreCase("bem")
                    || sortOption.equalsIgnoreCase("bem avaliado")) {
                highlyEvaluatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore(), Comparator.reverseOrder()));

                bookView.headerForBook();
                for (BookModel book : highlyEvaluatedBooks) {
                    bookView.bookInformation(book);
                }
                for (BookModel book : listOfUnreviewedBooks) {
                    bookView.bookInformation(book);
                }

                displayAdditionalListOptions();

            } else if (sortOption.equals("2")
                    || sortOption.equalsIgnoreCase("mal")
                    || sortOption.equalsIgnoreCase("mal avaliado")) {
                poorlyEvaluatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore()));

                bookView.headerForBook();
                for (BookModel book : poorlyEvaluatedBooks) {
                    bookView.bookInformation(book);
                }
                for (BookModel book : listOfUnreviewedBooks) {
                    bookView.bookInformation(book);
                }

                displayAdditionalListOptions();

            } else if (sortOption.equals("3")
                    || sortOption.equalsIgnoreCase("menu")
                    || sortOption.equalsIgnoreCase("menu inicial")) {
                return true;
            } else {
                bookView.invalidValueMsg();
                return sortList();
            }
        }
        return true;
    }

    public void openBook(int a, int b) {
        if (b == 1) {
            additionalOptionsForOpeningBook(1);
        } else if (b == 2) {
            additionalOptionsForOpeningBook(2);
        }

        if (a == 1) {
            displayAdditionalRegisterOptions();
        } else if (a == 2) {
            displayAdditionalSearchOptions();
        } else {
            displayAdditionalListOptions();
        }
    }

    public void additionalOptionsForOpeningBook(int a) {
        int bookOption = -1;
        boolean validInput = false;

        while (!validInput) {
            bookView.evaluationMsg(10);
            String bookOptionString = input.nextLine();

            if (bookOptionString.isEmpty()) {
                bookView.emptyValueMsg();
                continue;
            }

            try {
                bookOption = Integer.parseInt(bookOptionString);
                validInput = true;
            } catch (NumberFormatException e) {
                bookView.evaluationMsg(12);
            }
        }

        boolean bookFound = false;

        switch (a) {
            case 1 : {
                for (BookModel book : listOfBooks) {
                    if (book.getBookIndex() == bookOption) {
                        checkBookReview(book);
                        bookFound = true;
                    }
                }
                break;
            }
            case 2 : {
                for (BookModel book : listOfBooks) {
                    if (book.getBookIndex() == bookOption) {
                        bookView.additionalBookInformation(book);
                        bookFound = true;
                    }
                }
                break;
            }
            default:
                break;
        }

        if (!bookFound) {
            bookView.evaluationMsg(11);
        }
    }

    public boolean checkBookReview(BookModel book) {

        if (!book.isEvaluatedBook()) {

            String reading;
            do {
                bookView.evaluationMsg(1);
                reading = input.nextLine().trim();

                if (reading.isEmpty()) {
                    bookView.emptyValueMsg();
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
                bookView.evaluationMsg(2);
                return true;
            } else {
                bookView.evaluationMsg(9);
                return checkBookReview(book);
            }
        } else {
            String evaluateAgain;
            do {
                bookView.evaluationMsg(3);
                evaluateAgain = input.nextLine().trim();

                if (evaluateAgain.isEmpty()) {
                    bookView.emptyValueMsg();
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
                bookView.invalidValueMsg();
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
            bookView.evaluationMsg(4);
            String scoreString = input.nextLine().trim();

            if (scoreString.isEmpty()) {
                bookView.emptyValueMsg();
                continue;
            }

            try {
                temporaryScore = Float.parseFloat(scoreString);
                if (temporaryScore < 1 || temporaryScore > 5) {
                    bookView.evaluationMsg(5);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                bookView.evaluationMsg(5);
            }
        }

        temporaryConsumptionDate = validateNewDate();

        do {
            bookView.evaluationMsg(7);
            temporaryComment = input.nextLine().trim();

            if (temporaryComment.isEmpty()) {
                bookView.emptyValueMsg();
            }
        } while (temporaryComment.isEmpty());

        bookView.evaluationMsg(8);

        ReviewModel bookReview = new ReviewModel(true, temporaryScore, temporaryConsumptionDate, temporaryComment);
        book.setBookReview(bookReview);
        book.setEvaluatedBook(true);
    }

    public String validateNewString(int a) {
        String value;

        do {
            bookView.registrationMsg(a, 0);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                bookView.emptyValueMsg();
            }
        } while (value.isEmpty());

        return value;
    }

    public String validateNewIsbn() {
        String value;

        do {
            bookView.registrationMsg(4, 0);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                bookView.emptyValueMsg();
            }
        } while (value.isEmpty());

        String[] parts = value.split("-");
        if (parts.length != 5) {
            bookView.invalidIsbnMsg(2);
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

            bookView.invalidIsbnMsg(2);
            return validateNewIsbn();
        }

        String fullIsbn = prefix + group + registrant + publication + checkDigit;
        if (fullIsbn.length() != 13 || !fullIsbn.matches("\\d{13}")) {
            bookView.invalidIsbnMsg(2);
            return validateNewIsbn();
        };

        for (BookModel book : listOfBooks) {
            if (book.getIsbn().equalsIgnoreCase(value)) {
                bookView.invalidIsbnMsg(1);
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
            bookView.registrationMsg(5, 0);
            stringValue = input.nextLine().trim();
            if (stringValue.isEmpty()) {
                bookView.emptyValueMsg();
            }
        } while (stringValue.isEmpty());

        try {
            value = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            bookView.invalidYearMsg(1);
            return validateNewYear();
        }

        if (value < 1700 || value > currentYear) {
            bookView.invalidYearMsg(2);
            return validateNewYear();
        }

        return value;
    }

    public boolean validateNewBoolean() {
        String stringValue;

        do {
            bookView.registrationMsg(7, 0);
            stringValue = input.nextLine().trim();

            if (stringValue.isEmpty()) {
                bookView.emptyValueMsg();
                continue;
            }

            if (stringValue.equalsIgnoreCase("sim") || stringValue.equalsIgnoreCase("s")) {
                return true;
            } else if (stringValue.equalsIgnoreCase("não") || stringValue.equalsIgnoreCase("nao") || stringValue.equalsIgnoreCase("n")) {
                return false;
            } else {
                bookView.invalidHasCopyMsg();
            }

        } while (true);
    }

    public String validateNewDate() {
        String stringValue;

        do {
            bookView.evaluationMsg(6);
            stringValue = input.nextLine().trim();

            if (stringValue.isEmpty()) {
                bookView.emptyValueMsg();
            }
        } while (stringValue.isEmpty());

        String[] parts = stringValue.split("/");
        if (parts.length != 3) {
            bookView.invalidDateMsg(2, 0);
            return validateNewDate();
        }

        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
            bookView.invalidDateMsg(2, 0);
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
                bookView.invalidDateMsg(3, yearOfPublication);
                return validateNewDate();
            }
        } catch (NumberFormatException e) {
            bookView.invalidDateMsg(2, 0);
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
                bookView.invalidDateMsg(2, 0);
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
                bookView.invalidDateMsg(1, 0);
                return 0;
            }

            return 1;

        } catch (Exception e) {
            bookView.invalidDateMsg(2, 0);
            return 2;
        }
    }
}