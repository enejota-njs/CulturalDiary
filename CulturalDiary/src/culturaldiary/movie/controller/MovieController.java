package culturaldiary.movie.controller;

import culturaldiary.movie.model.MovieModel;
import culturaldiary.media.model.ReviewModel;
import culturaldiary.movie.model.repository.MovieRepository;
import culturaldiary.movie.view.MovieView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Scanner;

public class MovieController {
    MovieView movieView = new MovieView();
    MovieModel movieModel;
    MovieRepository movieRepository = new MovieRepository();
    ArrayList<MovieModel> listOfMovies = movieRepository.getListOfMovies();

    Scanner input = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public void registerMovie() {
        String temporaryTitle = validateNewString(1);
        String temporaryGenre = validateNewString(2);
        int temporaryYearOfRelease = validateNewYear();
        String temporaryDurationTime = validateNewDurationTime();
        String temporaryDirection = validateNewString(5);
        String temporaryScreenplay = validateNewString(6);
        ArrayList<String> temporaryCast = validateNewCast();
        String temporaryOriginalTitle = validateNewString(9);
        String temporaryWhereToWatch = validateNewString(10);

        movieModel = new MovieModel(temporaryTitle, temporaryGenre, temporaryYearOfRelease, temporaryDurationTime,
                temporaryDirection, temporaryScreenplay, temporaryCast, temporaryOriginalTitle, temporaryWhereToWatch);

        movieRepository.addMovie(movieModel);

        int index = movieModel.getMovieIndex();
        movieView.registrationMsg(11, index);

        displayAdditionalRegisterOptions();

    }

    public boolean displayAdditionalRegisterOptions() {
        movieView.additionalRegistrationMsg();
        movieView.chooseAnOptionMsg();

        String menuOption = input.nextLine().trim();

        if (menuOption.isEmpty()) {
            movieView.emptyValueMsg();
            return displayAdditionalRegisterOptions();
        }

        if (menuOption.equals("1")
                || menuOption.equalsIgnoreCase("abrir")
                || menuOption.equalsIgnoreCase("abrir filme")){
            openMovie(1, 2);
        }
        else if (menuOption.equals("2")
                || menuOption.equalsIgnoreCase("avaliar")
                || menuOption.equalsIgnoreCase("avaliar filme")) {
            openMovie(1, 1);
        } else if (menuOption.equals("3")
                || menuOption.equalsIgnoreCase("cadastrar")
                || menuOption.equalsIgnoreCase("cadastrar filme")) {
            registerMovie();
        } else if (menuOption.equals("4")
                || menuOption.equalsIgnoreCase("menu")
                || menuOption.equalsIgnoreCase("menu inicial")){
            return true;
        }
        else {
            movieView.invalidValueMsg();
            return displayAdditionalRegisterOptions();
        }
        return true;
    }

    public boolean searchMovie() {
        if (listOfMovies.isEmpty()) {
            movieView.emptyListMsg();
        }
        else {
            movieView.searchMsg(1);
            movieView.chooseAnOptionMsg();
            String searchOption = input.nextLine().trim();

            if (searchOption.isEmpty()) {
                movieView.emptyValueMsg();
                return searchMovie();
            }

            if (searchOption.equals("1")
                    || searchOption.equalsIgnoreCase("titulo")
                    || searchOption.equalsIgnoreCase("título")
                    || searchOption.equals("2")
                    || searchOption.equalsIgnoreCase("diretor")
                    || searchOption.equals("3")
                    || searchOption.equalsIgnoreCase("ator no elenco")
                    || searchOption.equalsIgnoreCase("ator")
                    || searchOption.equalsIgnoreCase("elenco")
                    || searchOption.equals("4")
                    || searchOption.equalsIgnoreCase("genero")
                    || searchOption.equalsIgnoreCase("gênero")
                    || searchOption.equals("5")
                    || searchOption.equalsIgnoreCase("ano")
                    || searchOption.equalsIgnoreCase("ano de lançamento")
                    || searchOption.equalsIgnoreCase("ano de lancamento")) {

                String searchInformation;
                do {
                    movieView.searchMsg(2);
                    searchInformation = input.nextLine().trim();

                    if (searchInformation.isEmpty()) {
                        movieView.emptyValueMsg();
                    }
                } while (searchInformation.isEmpty());

                boolean movieFound = false;
                for (MovieModel movie : listOfMovies) {
                    boolean match = false;

                    if ((searchOption.equals("1")
                            || searchOption.equalsIgnoreCase("titulo")
                            || searchOption.equalsIgnoreCase("título"))
                            && movie.getTitle().toLowerCase().contains(searchInformation)) {
                        match = true;
                    }
                    else if ((searchOption.equals("2")
                            || searchOption.equalsIgnoreCase("diretor"))
                            && movie.getDirection().toLowerCase().contains(searchInformation)) {
                        match = true;
                    }
                    else if (searchOption.equals("3")
                            || searchOption.equalsIgnoreCase("ator no elenco")
                            || searchOption.equalsIgnoreCase("ator")
                            || searchOption.equalsIgnoreCase("elenco")) {

                        for (String actor : movie.getCast()) {
                            if (actor.toLowerCase().contains(searchInformation.toLowerCase())) {
                                match = true;
                                break;
                            }
                        }
                    }
                    else if ((searchOption.equals("4")
                            || searchOption.equalsIgnoreCase("genero")
                            || searchOption.equalsIgnoreCase("gênero"))
                            && movie.getGenre().toLowerCase().contains(searchInformation)) {
                        match = true;
                    }
                    else if (searchOption.equals("5")
                            || searchOption.equalsIgnoreCase("ano")
                            || searchOption.equalsIgnoreCase("ano de lançamento")
                            || searchOption.equalsIgnoreCase("ano de lancamento")) {
                        try {
                            if (movie.getYearOfRelease() == Integer.parseInt(searchInformation)) {
                                match = true;
                            }
                        } catch (NumberFormatException x) {
                            movieView.invalidValueMsg();
                        }
                    }

                    if (match) {
                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }
                        movieView.movieInformation(movie);
                    }
                }

                if (!movieFound) {
                    movieView.searchMsg(3);
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
                movieView.invalidValueMsg();
                return searchMovie();
            }
        }
        return true;
    }

    public boolean displayAdditionalSearchOptions() {
        movieView.additionalSearchMsg();

        movieView.chooseAnOptionMsg();
        String optionMenu = input.nextLine().trim();

        if (optionMenu.isEmpty()) {
            movieView.emptyValueMsg();
            return displayAdditionalSearchOptions();
        }

        if (optionMenu.equals("1")
                || optionMenu.equalsIgnoreCase("abrir")
                || optionMenu.equalsIgnoreCase("abrir filme")) {
            openMovie(2, 2);
        } else if (optionMenu.equals("2")
                || optionMenu.equalsIgnoreCase("avaliar")
                || optionMenu.equalsIgnoreCase("avaliar filme")) {
            openMovie(2, 1);
        } else if (optionMenu.equals("3")
                || optionMenu.equalsIgnoreCase("buscar")
                || optionMenu.equalsIgnoreCase("buscar filme")) {
            searchMovie();
        } else if (optionMenu.equals("4")
                || optionMenu.equalsIgnoreCase("menu")
                || optionMenu.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            movieView.invalidValueMsg();
            return displayAdditionalSearchOptions();
        }
        return true;
    }

    public void listMovie() {
        if (listOfMovies.isEmpty()) {
            movieView.emptyListMsg();
        } else {
            movieView.headerForMovie();
            for (MovieModel movie: listOfMovies) {
                movieView.movieInformation(movie);
            }
            displayAdditionalListOptions();
        }
    }

    public boolean displayAdditionalListOptions() {
        movieView.additionalListMsg();
        movieView.chooseAnOptionMsg();

        String optionMenu = input.nextLine().trim();

        if (optionMenu.isEmpty()) {
            movieView.emptyValueMsg();
            return displayAdditionalListOptions();
        }

        if (optionMenu.equals("1")
                || optionMenu.equalsIgnoreCase("abrir")
                || optionMenu.equalsIgnoreCase("abrir filme")) {
            openMovie(3, 2);
        } else if (optionMenu.equals("2")
                || optionMenu.equalsIgnoreCase("avaliar")
                || optionMenu.equalsIgnoreCase("avaliar filme")) {
            openMovie(3, 1);
        } else if (optionMenu.equals("3")
                || optionMenu.equalsIgnoreCase("ordenar")
                || optionMenu.equalsIgnoreCase("ordenar filme")) {
            sortList();
        } else if (optionMenu.equals("4")
                || optionMenu.equalsIgnoreCase("filtrar")
                || optionMenu.equalsIgnoreCase("filtrar filme")) {
            filterList();
        } else if (optionMenu.equals("5")
                || optionMenu.equalsIgnoreCase("listar")
                || optionMenu.equalsIgnoreCase("listar filme")) {
            listMovie();
        } else if (optionMenu.equals("6")
                || optionMenu.equalsIgnoreCase("menu")
                || optionMenu.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            movieView.invalidValueMsg();
            return displayAdditionalListOptions();
        }
        return true;
    }

    public boolean filterList() {
        movieView.filterMsg(1);

        movieView.chooseAnOptionMsg();
        String filterOption = input.nextLine().trim();

        if (filterOption.isEmpty()) {
            movieView.emptyValueMsg();
            return filterList();
        }

        if (filterOption.equals("1")
                || filterOption.equalsIgnoreCase("gênero")
                || filterOption.equalsIgnoreCase("genero")) {

            String genreOption;
            do {
                movieView.filterMsg(2);
                genreOption = input.nextLine().trim();

                if (genreOption.isEmpty()) {
                    movieView.emptyValueMsg();
                }
            } while (genreOption.isEmpty());


            boolean genreFound = false;
            for (MovieModel movie : listOfMovies) {
                if (movie.getGenre().toLowerCase().contains(genreOption)) {
                    if (!genreFound) {
                        movieView.headerForMovie();
                        genreFound = true;
                    }
                    movieView.movieInformation(movie);
                }
            }

            if (!genreFound) {
                movieView.filterMsg(4);
            }

            displayAdditionalListOptions();
        } else if (filterOption.equals("2")
                || filterOption.equalsIgnoreCase("ano")
                || filterOption.equalsIgnoreCase("ano de lançamento")
                || filterOption.equalsIgnoreCase("ano de lancamento")) {

            String yearOption;
            do {
                movieView.filterMsg(3);
                yearOption = input.nextLine().trim();

                if (yearOption.isEmpty()) {
                    movieView.emptyValueMsg();
                }
            } while (yearOption.isEmpty());


            boolean yearFound = false;

            for (MovieModel movie : listOfMovies) {
                try {
                    if (String.valueOf(movie.getYearOfRelease()).equalsIgnoreCase(yearOption)) {
                        if (!yearFound) {
                            movieView.headerForMovie();
                            yearFound = true;
                        }
                        movieView.movieInformation(movie);
                    }
                } catch (Exception e) {
                }
            }

            if (!yearFound) {
                movieView.filterMsg(4);
            }

            displayAdditionalListOptions();
        } else if (filterOption.equals("3")
                || filterOption.equalsIgnoreCase("menu")
                || filterOption.equalsIgnoreCase("menu inicial")) {
            return true;
        }
        else {
            movieView.invalidValueMsg();
            return filterList();
        }
        return true;
    }

    public boolean sortList() {
        ArrayList<MovieModel> listOfReviewedMovies = new ArrayList<MovieModel>();
        ArrayList<MovieModel> listOfUnreviewedMovies = new ArrayList<MovieModel>();

        for (MovieModel movie : listOfMovies) {
            if (movie.getMovieReview() != null) {
                listOfReviewedMovies.add(movie);
            } else {
                listOfUnreviewedMovies.add(movie);
            }
        }

        ArrayList<MovieModel> highlyEvaluatedMovies = new ArrayList<MovieModel>(listOfReviewedMovies);
        ArrayList<MovieModel> poorlyEvaluatedMovies = new ArrayList<MovieModel>(listOfReviewedMovies);

        if (listOfReviewedMovies.isEmpty()) {
            movieView.orderingMsg(1);
            displayAdditionalListOptions();
        } else {
            String sortOption;
            do {
                movieView.orderingMsg(2);
                movieView.chooseAnOptionMsg();
                sortOption = input.nextLine().trim();

                if (sortOption.isEmpty()) {
                    movieView.emptyValueMsg();
                }
            } while (sortOption.isEmpty());

            if (sortOption.equals("1")
                    || sortOption.equalsIgnoreCase("bem")
                    || sortOption.equalsIgnoreCase("bem avaliado")) {
                highlyEvaluatedMovies.sort(Comparator.comparing(movieModel -> movieModel.getMovieReview().getScore(), Comparator.reverseOrder()));

                movieView.headerForMovie();
                for (MovieModel movie : highlyEvaluatedMovies) {
                    movieView.movieInformation(movie);
                }
                for (MovieModel movie : listOfUnreviewedMovies) {
                    movieView.movieInformation(movie);
                }

                displayAdditionalListOptions();

            } else if (sortOption.equals("2")
                    || sortOption.equalsIgnoreCase("mal")
                    || sortOption.equalsIgnoreCase("mal avaliado")) {
                poorlyEvaluatedMovies.sort(Comparator.comparing(movieModel -> movieModel.getMovieReview().getScore()));

                movieView.headerForMovie();
                for (MovieModel movie : poorlyEvaluatedMovies) {
                    movieView.movieInformation(movie);
                }
                for (MovieModel movie : listOfUnreviewedMovies) {
                    movieView.movieInformation(movie);
                }

                displayAdditionalListOptions();

            } else if (sortOption.equals("3")
                    || sortOption.equalsIgnoreCase("menu")
                    || sortOption.equalsIgnoreCase("menu inicial")) {
                return true;
            } else {
                movieView.invalidValueMsg();
                return sortList();
            }
        }
        return true;
    }

    public void openMovie(int a, int b) {
        if (b == 1) {
            additionalOptionsForOpeningMovie(1);
        } else if (b == 2) {
            additionalOptionsForOpeningMovie(2);
        }

        if (a == 1) {
            displayAdditionalRegisterOptions();
        } else if (a == 2) {
            displayAdditionalSearchOptions();
        } else {
            displayAdditionalListOptions();
        }
    }

    public void additionalOptionsForOpeningMovie(int a) {
        int movieOption = -1;
        boolean validInput = false;

        while (!validInput) {
            movieView.evaluationMsg(10);
            String movieOptionString = input.nextLine();

            if (movieOptionString.isEmpty()) {
                movieView.emptyValueMsg();
                continue;
            }

            try {
                movieOption = Integer.parseInt(movieOptionString);
                validInput = true;
            } catch (NumberFormatException e) {
                movieView.evaluationMsg(12);
            }
        }

        boolean movieFound = false;

        switch (a) {
            case 1 : {
                for (MovieModel movie : listOfMovies) {
                    if (movie.getMovieIndex() == movieOption) {
                        checkMovieReview(movie);
                        movieFound = true;
                    }
                }
                break;
            }
            case 2 : {
                for (MovieModel movie : listOfMovies) {
                    if (movie.getMovieIndex() == movieOption) {
                        movieView.additionalMovieInformation(movie);
                        movieFound = true;
                    }
                }
                break;
            }
            default:
                break;
        }

        if (!movieFound) {
            movieView.evaluationMsg(11);
        }
    }

    public boolean checkMovieReview(MovieModel movie) {

        if (!movie.isEvaluatedMovie()) {

            String watched;
            do {
                movieView.evaluationMsg(1);
                watched = input.nextLine().trim();

                if (watched.isEmpty()) {
                    movieView.emptyValueMsg();
                }
            } while (watched.isEmpty());

            if (watched.equalsIgnoreCase("s")
                    || watched.equalsIgnoreCase("sim")) {
                evaluateMovie(movie);
            }
            else if (watched.equalsIgnoreCase("n")
                    || watched.equalsIgnoreCase("não")
                    || watched.equalsIgnoreCase("nao")){
                movieView.evaluationMsg(2);
                return true;
            } else {
                movieView.evaluationMsg(9);
                return checkMovieReview(movie);
            }
        } else {
            String evaluateAgain;
            do {
                movieView.evaluationMsg(3);
                evaluateAgain = input.nextLine().trim();

                if (evaluateAgain.isEmpty()) {
                    movieView.emptyValueMsg();
                }
            } while (evaluateAgain.isEmpty());


            if (evaluateAgain.equalsIgnoreCase("s")
                    || evaluateAgain.equalsIgnoreCase("sim")) {
                evaluateMovie(movie);
            } else if (evaluateAgain.equalsIgnoreCase("n")
                    || evaluateAgain.equalsIgnoreCase("não")
                    || evaluateAgain.equalsIgnoreCase("nao")) {
                return true;
            }
            else {
                movieView.invalidValueMsg();
                return checkMovieReview(movie);
            }
        }
        return true;
    }

    public void evaluateMovie(MovieModel movie) {
        String temporaryConsumptionDate;
        String temporaryComment;
        float temporaryScore = 0.0f;

        while (true) {
            movieView.evaluationMsg(4);
            String scoreString = input.nextLine().trim();

            if (scoreString.isEmpty()) {
                movieView.emptyValueMsg();
                continue;
            }

            try {
                temporaryScore = Float.parseFloat(scoreString);
                if (temporaryScore < 1 || temporaryScore > 5) {
                    movieView.evaluationMsg(5);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                movieView.evaluationMsg(12);
            }
        }

        temporaryConsumptionDate = validateNewDate();

        do {
            movieView.evaluationMsg(7);
            temporaryComment = input.nextLine().trim();

            if (temporaryComment.isEmpty()) {
                movieView.emptyValueMsg();
            }
        } while (temporaryComment.isEmpty());

        movieView.evaluationMsg(8);

        ReviewModel movieReview = new ReviewModel(true, temporaryScore, temporaryConsumptionDate, temporaryComment);
        movie.setMovieReview(movieReview);
        movie.setEvaluatedMovie(true);
    }

    public String validateNewString(int a) {
        String value;

        do {
            movieView.registrationMsg(a, 0);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                movieView.emptyValueMsg();
            }
        } while (value.isEmpty());

        return value;
    }

    public int validateNewYear() {
        int value = 0;
        String stringValue;
        int currentYear = calendar.get(Calendar.YEAR);

        do {
            movieView.registrationMsg(3, 0);
            stringValue = input.nextLine().trim();
            if (stringValue.isEmpty()) {
                movieView.emptyValueMsg();
            }
        } while (stringValue.isEmpty());

        try {
            value = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            movieView.invalidYearMsg(1);
            return validateNewYear();
        }

        if (value < 1700 || value > currentYear) {
            movieView.invalidYearMsg(2);
            return validateNewYear();
        }

        return value;
    }

    public ArrayList<String> validateNewCast() {
        ArrayList<String> value = new ArrayList<String>();
        int amount = 0;
        String amountString;

        do {
            movieView.registrationMsg(7, 0);
            amountString = input.nextLine().trim();
            if (amountString.isEmpty()) {
                movieView.emptyValueMsg();
            }
        } while (amountString.isEmpty());

        try {
            amount = Integer.parseInt(amountString);
        } catch (NumberFormatException e) {
            movieView.invalidValueMsg();
            return validateNewCast();
        }

        if (amount < 1) {
            movieView.registrationMsg(12, 0);
            return validateNewCast();
        }

        String person;
        for (int i = 1; i <= amount; i++) {
            do {
                movieView.registrationMsg(8, i);
                person = input.nextLine().trim();
                if (person.isEmpty()) {
                    movieView.emptyValueMsg();
                }
            } while (person.isEmpty());
            value.add(person);
        }

        return value;
    }

    public String validateNewDurationTime() {
        String value;

        do {
            movieView.registrationMsg(4, 0);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                movieView.emptyValueMsg();
            }
        } while (value.isEmpty());

        String[] parts = value.split(":");
        if (parts.length != 2) {
            movieView.registrationMsg(13,0);
            return validateNewDurationTime();
        }

        String stringHour = parts[0];
        String stringMinute = parts[1];

        int hour;
        int minute;
        try {
            hour = Integer.parseInt(stringHour);
            minute = Integer.parseInt(stringMinute);
        } catch (Exception e) {
            movieView.registrationMsg(13, 0);
            return validateNewDurationTime();
        }

        if (hour <= 23 && hour >= 0 && minute <= 59 && minute >= 0) {
            return (stringHour + "h" + stringMinute + "min");
        } else {
            movieView.registrationMsg(13,0);
            return validateNewDurationTime();
        }
    }

    public String validateNewDate() {
        String stringValue;

        do {
            movieView.evaluationMsg(6);
            stringValue = input.nextLine().trim();

            if (stringValue.isEmpty()) {
                movieView.emptyValueMsg();
            }
        } while (stringValue.isEmpty());

        String[] parts = stringValue.split("/");
        if (parts.length != 3) {
            movieView.invalidDateMsg(2, 0);
            return validateNewDate();
        }

        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
            movieView.invalidDateMsg(2, 0);
            return validateNewDate();
        }

        int valid = validateExistingDate(day, month, year);

        if (valid == 0 || valid == 2) {
            return validateNewDate();
        }

        int yearOfPublication = movieModel.getYearOfRelease();

        try {
            int yearInt = Integer.parseInt(year);

            if (yearInt < yearOfPublication) {
                movieView.invalidDateMsg(3, yearOfPublication);
                return validateNewDate();
            }
        } catch (NumberFormatException e) {
            movieView.invalidDateMsg(2, 0);
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
                movieView.invalidDateMsg(2, 0);
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
                movieView.invalidDateMsg(1, 0);
                return 0;
            }

            return 1;

        } catch (Exception e) {
            movieView.invalidDateMsg(2, 0);
            return 2;
        }
    }
}