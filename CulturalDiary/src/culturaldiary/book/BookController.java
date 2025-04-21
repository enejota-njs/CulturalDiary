package culturaldiary.book;

import culturaldiary.review.ReviewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

public class BookController {
    BookView bookView = new BookView();
    BookModel bookModel;
    BookRepository bookRepository = new BookRepository();
    private ArrayList<BookModel> listOfBooks = bookRepository.getListOfBooks();

    Calendar calendar = Calendar.getInstance();

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

        boolean validTitle = validateTitle(title);
        boolean validAuthor = validateAuthor(author);
        boolean validPublisher = validatePublisher(publisher);
        boolean validIsbn = validateIsbn(isbn);
        boolean validYearOfPublication = validateYearOfPublication(yearOfPublicationString);
        boolean validGenre = validateGenre(genre);
        boolean validHasCopy = validateHasCopy(hasCopyString);
        boolean validRead = validadeNewRead(readString);

        if (validTitle == false || validAuthor == false || validPublisher == false || validIsbn == false || validYearOfPublication == false
                || validGenre == false || validHasCopy == false || validRead == false) {
            bookView.tryAgainMessage();
            return false;
        }

        try {
            int yearOfPublication = Integer.parseInt(yearOfPublicationString);

            Set<String> positiveResponsesHasCopy = Set.of(
                    "sim", "s", "tenho", "sim tenho", "tenho sim", "s tenho", "tenho s"
            );

            Set<String> negativeResponsesHasCopy = Set.of(
                    "não", "nao", "n", "não tenho", "nao tenho", "n tenho",
                    "tenho não", "tenho nao", "tenho n"
            );

            boolean hasCopy = false;
            if (positiveResponsesHasCopy.contains(hasCopyString)) { hasCopy = true; }
            else if (negativeResponsesHasCopy.contains(hasCopyString)) { hasCopy = false; }

            Set<String> positiveResponsesRead = Set.of(
                    "sim", "s", "li", "sim li", "li sim", "s li", "li s"
            );

            Set<String> negativeResponsesRead = Set.of(
                    "não", "nao", "n", "não li", "nao li", "n li",
                    "li não", "li nao", "li n"
            );

            boolean read = false;
            if (positiveResponsesRead.contains(readString)) { read = true; }
            else if (negativeResponsesRead.contains(readString)) { read = false; }

            bookModel = new BookModel(title.trim(), author.trim(), publisher.trim(), isbn.trim(), yearOfPublication, genre.trim(), hasCopy, read);
            bookRepository.addBook(bookModel);

            bookView.registeredBookMessage(title);

            return true;
        } catch (Exception e) {
            bookView.invalidMessage();
            return false;
        }
    }

    public boolean validateTitle(String title) {
        return validateNewString(title, "Título");
    }

    public boolean validateAuthor(String author) {
        return validateNewString(author, "Autor");
    }

    public boolean validatePublisher(String publisher) {
        return validateNewString(publisher, "Editora");
    }

    public boolean validateIsbn(String isbn) {
        return validateNewIsbn(isbn);
    }

    public boolean validateYearOfPublication(String yearOfPublication) {
        return validateNewYear(yearOfPublication);
    }

    public boolean validateGenre(String genre) {
        return validateNewString(genre, "Gênero");
    }

    public boolean validateHasCopy(String hasCopy) {
        return validateNewHasCopy(hasCopy);
    }

    public boolean validateNewString(String value, String name) {
        if (value.isEmpty()) {
            bookView.emptyValueMessage(name);
            return false;
        }
        return true;
    }

    public boolean validateNewIsbn(String value) {
        if (validateNewString(value, "Isbn")) {
            if (value.matches("\\d+") && (value.length() == 10 || value.length() == 13)) {

                if (value.length() == 13 && !(value.startsWith("978") || value.startsWith("979"))) {
                    bookView.nonExistentIsbnMessage();
                    return false;
                }

                for (BookModel book : listOfBooks) {
                    if (book.getIsbn().equalsIgnoreCase(value)) {
                        bookView.registeredIsbnMessage();
                        return false;
                    }
                }

                return true;
            }
            else {
                bookView.nonExistentIsbnMessage();
                return false;
            }
        }

        return false;
    }

    public boolean validateNewYear(String value) {
        if (validateNewString(value, "Ano de publicação")) {
            int valueInt = 0;
            int currentYear = calendar.get(Calendar.YEAR);

            try {
                valueInt = Integer.parseInt(value);
            } catch (Exception e) {
                bookView.integerMessage();
                return false;
            }

            if (valueInt < 1700 || valueInt > currentYear) {
                bookView.invalidYearMessage(currentYear);
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean validateNewHasCopy(String value) {
        if (validateNewString(value, "Exemplar")) {

            Set<String> validAnswers = Set.of(
                    "sim", "s",
                    "não", "nao", "n",
                    "não tenho", "nao tenho", "n tenho", "tenho nao", "tenho não", "tenho n",
                    "sim tenho", "tenho sim", "s tenho", "tenho s", "tenho"
            );

            if (validAnswers.contains(value)) {
                return true;
            } else {
                bookView.invalidHasCopyMessage();
            }
        }

        return false;
    }

    public boolean validadeNewRead(String value) {
        if (validateNewString(value, "Leitura")) {

            Set<String> validAnswers = Set.of(
                    "sim", "s",
                    "não", "nao", "n",
                    "não li", "nao li", "n li", "li nao", "li não", "li n",
                    "sim li", "li sim", "s li", "li s", "li"
            );

            if (validAnswers.contains(value)) {
                return true;
            } else {
                bookView.invalidReadMessage();
            }
        }

        return false;
    }

    public boolean searchBookByTitle(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) {


                for (BookModel book : listOfBooks) {
                    if (book.getTitle().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }

                        bookView.bookInformation(book);
                    }
                }



            }
            if (!bookFound) { bookView.noBookFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean searchBookByAuthor(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) {

                for (BookModel book : listOfBooks) {
                    if (book.getAuthor().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }

                        bookView.bookInformation(book);
                    }
                }



            }
            if (!bookFound) { bookView.noBookFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean searchBookByGenre(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) {


                for (BookModel book : listOfBooks) {
                    if (book.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }

                        bookView.bookInformation(book);
                    }
                }



            }
            if (!bookFound) { bookView.noBookFoundMessage(); }
            return true;
        }

        return false;

    }

    public boolean searchBookByYearOfPublication(String value) {
        value = value.trim();

        if (validateNewInputString(value) && validateNewInputInt(value)) {

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) {

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value);
                } catch (Exception e) {
                    bookView.invalidMessage();
                    return false;
                }


                for (BookModel book : listOfBooks) {
                    if (book.getYearOfPublication() == valueInt) {

                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }

                        bookView.bookInformation(book);
                    }
                }

            }
            if (!bookFound) { bookView.noBookFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean searchBookByIsbn(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {
            boolean validValue = value.matches("\\d+");

            if ((validValue) && (value.length() == 13 || value.length() == 10)) {
                boolean bookFound = false;
                if (!listOfBooks.isEmpty()) {

                    for (BookModel book : listOfBooks) {
                        if (book.getIsbn().equalsIgnoreCase(value.trim())) {

                            if (!bookFound) {
                                bookView.headerForBook();
                                bookFound = true;
                            }

                            bookView.bookInformation(book);
                        }
                    }

                }
                if (!bookFound) {
                    bookView.noBookFoundMessage();
                }
                return true;
            }
            bookView.nonExistentIsbnMessage();
            return false;

        }
        return false;
    }

    public boolean listBooks() {
        try {
            if (listOfBooks.isEmpty()) {
                bookView.emptyListMessage();
            } else {
                bookView.headerForBook();
                for (BookModel book : listOfBooks) {
                    bookView.bookInformation(book);
                }
            }
            return true;
        } catch (Exception e) {
            bookView.invalidMessage();
            return false;
        }
    }

    public boolean filterListOfBooksByGenre(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) {

                for (BookModel book : listOfBooks) {
                    if (book.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }

                        bookView.bookInformation(book);
                    }
                }



            }
            if (!bookFound) { bookView.noBookFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean filterListOfBooksByYearOfPublication(String value) {
        value = value.trim();

        if (validateNewInputString(value) && validateNewInputInt(value)) {

            boolean bookFound = false;
            if (!listOfBooks.isEmpty()) {

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value);
                } catch (Exception e) {
                    bookView.invalidMessage();
                    return false;
                }


                for (BookModel book : listOfBooks) {
                    if (book.getYearOfPublication() == valueInt) {

                        if (!bookFound) {
                            bookView.headerForBook();
                            bookFound = true;
                        }

                        bookView.bookInformation(book);
                    }
                }

            }
            if (!bookFound) { bookView.noBookFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean sortListByTopRated() {
        try {
            if (!listOfBooks.isEmpty()) {
                ArrayList<BookModel> listOfReviewedBooks = new ArrayList<BookModel>();

                for (BookModel book : listOfBooks) {
                    if (book.getBookReview() != null) {
                        listOfReviewedBooks.add(book);
                    }
                }

                ArrayList<BookModel> highlyRatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);

                if (!highlyRatedBooks.isEmpty()){
                    highlyRatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore(), Comparator.reverseOrder()));
                } else {
                    bookView.emptyEvaluatedListMessage();
                    return true;
                }

                bookView.headerForBook();
                for (BookModel book : highlyRatedBooks) {
                    bookView.bookInformation(book);
                }

            } else {
                bookView.emptyListMessage();
            }

            return true;
        } catch (Exception e) {
            bookView.invalidMessage();
            return false;
        }
    }

    public boolean sortListByLowRated() {
        try {
            if (!listOfBooks.isEmpty()) {
                ArrayList<BookModel> listOfReviewedBooks = new ArrayList<BookModel>();

                for (BookModel book : listOfBooks) {
                    if (book.getBookReview() != null) {
                        listOfReviewedBooks.add(book);
                    }
                }

                ArrayList<BookModel> poorlyRatedBooks = new ArrayList<BookModel>(listOfReviewedBooks);

                if (!poorlyRatedBooks.isEmpty()){
                    poorlyRatedBooks.sort(Comparator.comparing(bookModel -> bookModel.getBookReview().getScore()));
                } else {
                    bookView.emptyEvaluatedListMessage();
                    return true;
                }

                bookView.headerForBook();
                for (BookModel book : poorlyRatedBooks) {
                    bookView.bookInformation(book);
                }

            } else {
                bookView.emptyListMessage();
            }

            return true;
        } catch (Exception e) {
            bookView.invalidMessage();
            return false; }
    }

    public boolean openBook(BookModel book) {
        try {
            bookView.fullBookInformation(book);
            return true;
        }   catch (Exception e) {
            bookView.invalidMessage();
            return false;
        }
    }

    public boolean changeBookReadingStatus(BookModel book , String value) {
        value = value.trim();

        if (book == null) {
            bookView.invalidMessage();
            return false;
        }

        boolean validRead = validadeNewRead(value);

        if (!validRead) {
            bookView.tryAgainMessage();
            return false;
        }

        try {
            Set<String> positiveResponsesRead = Set.of(
                    "sim", "s", "li", "sim li", "li sim", "s li", "li s"
            );

            Set<String> negativeResponsesRead = Set.of(
                    "não", "nao", "n", "não li", "nao li", "n li",
                    "li não", "li nao", "li n"
            );

            boolean read = false;
            if (positiveResponsesRead.contains(value)) {
                read = true;
            } else if (negativeResponsesRead.contains(value)) {
                read = false;
            }

            book.setRead(read);
            bookView.updatedReadMessage();

            return true;
        } catch (Exception e) {
            bookView.invalidMessage();
            return false;
        }
    }

    public boolean evaluateBook(BookModel book, String score, String consumptionDate, String comment) {
        try {
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            if (!checkBookReview(book)) {
                if (book.isRead()) {
                    boolean validScore = validateNewScore(score);
                    boolean validConsumptionDate = validateNewDate(book, consumptionDate);
                    boolean validComment = validateNewString(comment, "Comentários");

                    if (validScore == false || validConsumptionDate == false || validComment == false) {
                        bookView.tryAgainMessage();
                        return false;
                    }

                    float scoreFloat = 0f;
                    try {
                        scoreFloat = Float.parseFloat(score);
                    } catch (Exception e) {
                        bookView.invalidMessage();
                        return false;
                    }

                    ReviewModel reviewModel = new ReviewModel(scoreFloat, consumptionDate, comment);
                    book.setBookReview(reviewModel);
                    book.setEvaluatedBook(true);

                    bookView.registeredEvaluationMessage();
                    return true;
                } else {
                    bookView.unreadBookMessage();
                    return true;
                }
            } else {
                bookView.messageOfBookAlreadyEvaluated();
                return true;
            }
        } catch (Exception e) {
            bookView.invalidMessage();
            return false;
        }
    }

    public boolean evaluateBookAgain(BookModel book, String score, String consumptionDate, String comment) {
        try {
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            if (checkBookReview(book)) {
                book.setEvaluatedBook(false);
                return evaluateBook(book, score, consumptionDate, comment);
            } else {
                bookView.unratedBookMessage();
                return true;
            }
        } catch (Exception e) {
            bookView.invalidMessage();
            return false;
        }
    }

    public boolean checkBookReview(BookModel book) {
        if (book.isEvaluatedBook()) {
            return true;
        }
        return false;
    }

    public boolean validateNewInputString(String value) {
        if (value.isEmpty()) {
            bookView.emptyInformationMessage();
            return false;
        }
        return true;
    }

    public boolean validateNewInputInt(String value) {
        try {
            int valueInt = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            bookView.integerMessage();
            return false;
        }
    }

    public boolean validateNewScore(String value) {
        if (validateNewInputString(value)) {
            try {
                float score = Float.parseFloat(value);
                if (score < 1 || score > 5) {
                    bookView.invalidScoreMessage();
                    return false;
                }
                return true;
            } catch (Exception e) {
                bookView.invalidNumberMessage();
                return false;
            }
        }

        return false;
    }

    public boolean validateNewDate(BookModel book, String value) {
        if (validateNewInputString(value)) {
            String[] parts = value.split("/");
            if (parts.length != 3) {
                bookView.invalidDateFormatMessage();
                return false;
            }

            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
                bookView.invalidDateMessage();
                return false;
            }

            boolean valid = validateExistingDate(day, month, year);
            if (!valid) { return false; }

            try {
                int yearInt = Integer.parseInt(year);

                if (yearInt < book.getYearOfPublication()) {
                    bookView.invalidYearPeriodMessage(book.getYearOfPublication());
                    return false;
                }
            } catch (NumberFormatException e) {
                bookView.invalidDateMessage();
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean validateExistingDate(String day, String month, String year) {
        try {
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);

            if (m < 1 || m > 12) {
                bookView.nonExistentDateMessage();
                return false;
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
                bookView.invalidFutureDatesMessage();
                return false;
            }

            return true;

        } catch (Exception e) {
            bookView.invalidDateMessage();
            return false;
        }
    }

    public ArrayList<BookModel> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(ArrayList<BookModel> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }
}