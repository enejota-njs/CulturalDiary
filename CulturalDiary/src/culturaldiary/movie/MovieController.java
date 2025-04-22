package culturaldiary.movie;

import culturaldiary.review.ReviewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

public class MovieController {
    MovieView movieView = new MovieView();
    MovieModel movieModel;
    MovieRepository movieRepository = new MovieRepository();
    private ArrayList<MovieModel> listOfMovies = movieRepository.getListOfMovies();

    Calendar calendar = Calendar.getInstance();

    public boolean registerMovie(String title, String genre, String yearOfReleaseString, String durationTime, String direction,
                                 String screenplay, String castString, String originalTitle, String whereToWatch, String watchedString) {

        title = title.trim();
        genre = genre.trim();
        yearOfReleaseString = yearOfReleaseString.trim();
        durationTime = durationTime.trim();
        direction = direction.trim();
        screenplay = screenplay.trim();
        castString = castString.trim();
        originalTitle = originalTitle.trim();
        whereToWatch = whereToWatch.trim();
        watchedString = watchedString.trim();

        boolean validTitle = validateTitle(title);
        boolean validGenre = validateGenre(genre);
        boolean validYearOfRelease = validateYearOfRelease(yearOfReleaseString);
        boolean validDurationTime = validateDurationTime(durationTime);
        boolean validDirection = validateDirection(direction);
        boolean validScreeplay = validateScreenplay(screenplay);
        boolean validCast = validateCast(castString);
        boolean validOriginalTitle = validateOriginalTitle(originalTitle);
        boolean validWhereToWatch = validateWhereToWatch(whereToWatch);
        boolean validWatched = validateWatched(watchedString);

        if (validTitle == false || validGenre == false || validYearOfRelease == false || validDurationTime == false ||
        validDirection == false || validScreeplay == false || validCast == false || validOriginalTitle == false ||
        validWhereToWatch == false || validWatched == false) {
            movieView.tryAgainMessage();
            return false;
        }

        try {
            int yearOfRelease = Integer.parseInt(yearOfReleaseString);

            String[] castPeople = castString.split(",\\s*");

            ArrayList<String> cast = new ArrayList<String>();
            for (String personal : castPeople) {
                if (!personal.isEmpty()) {
                    cast.add(personal.trim());
                }
            }

            if (cast.isEmpty()) {
                movieView.emptyCastMessage();
                return false;
            }

            Set<String> positiveResponsesWatched = Set.of(
                    "sim", "s", "assisti", "sim assisti", "assisti sim", "s assisti", "assisti s", "já assisti", "ja assisti", "já", "ja"
            );

            Set<String> negativeResponsesWatched = Set.of(
                    "não", "nao", "n", "não assisti", "nao assisti", "n assisti",
                    "assisti não", "assisti nao", "assisti n"
            );

            boolean watched = false;
            if (positiveResponsesWatched.contains(watchedString.toLowerCase())) { watched = true; }
            else if (negativeResponsesWatched.contains(watchedString.toLowerCase())) { watched = false; }

            movieModel = new MovieModel(title.trim(), genre.trim(), yearOfRelease, durationTime.trim(), direction.trim(), screenplay.trim(), cast, originalTitle.trim(), whereToWatch.trim(), watched);
            movieRepository.addMovie(movieModel);

            movieView.registeredMovieMessage(title);

            return true;
        } catch (Exception e) {
            movieView.invalidMessage();
            return false;
        }
    }

    public boolean validateTitle(String title) {
        return validateNewString(title, "Título");
    }

    public boolean validateGenre(String genre) {
        return validateNewString(genre, "Gênero");
    }

    public boolean validateYearOfRelease(String yearOfRelease) {
        return validateNewYear(yearOfRelease);
    }

    public boolean validateDurationTime(String durationTime) {
        return validateNewTime(durationTime);
    }

    public boolean validateDirection(String direction) {
        return validateNewString(direction, "Direção");
    }

    public boolean validateScreenplay(String screenplay) {
        return validateNewString(screenplay, "Roteiro");
    }

    public boolean validateCast(String cast) {
        return validateNewCast(cast);
    }

    public boolean validateOriginalTitle(String originalTitle) {
        return validateNewString(originalTitle, "Título original");
    }

    public boolean validateWhereToWatch(String whereToWatch) {
        return validateNewString(whereToWatch, "Onde assistir");
    }

    public boolean validateWatched(String watched) {
        return validateNewWatched(watched);
    }

    public boolean validateNewTime(String value) {
        if (validateNewInputString(value)) {
            String[] parts = value.split(":");
            if (parts.length != 2) {
                movieView.invalidTimeMessage();
                return false;
            }

            String stringHour = parts[0];
            String stringMinute = parts[1];

            int hour;
            int minute;
            try {
                hour = Integer.parseInt(stringHour);
                minute = Integer.parseInt(stringMinute);
            } catch (Exception e) {
                movieView.invalidTimeMessage();
                return false;
            }

            if (hour <= 23 && hour >= 0 && minute <= 59 && minute >= 0) {
                return true;
            } else {
                movieView.invalidTimeMessage();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validateNewYear(String value) {
        if (validateNewString(value, "Ano de lançamento")) {
            int valueInt = 0;
            int currentYear = calendar.get(Calendar.YEAR);

            try {
                valueInt = Integer.parseInt(value);
            } catch (Exception e) {
                movieView.integerMessage();
                return false;
            }

            if (valueInt < 1700 || valueInt > currentYear) {
                movieView.invalidYearMessage(currentYear);
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean validateNewCast(String value) {
        if (validateNewString(value, "Elenco")) {
            if (value.matches("[\\p{L}, ]*")) {
                return true;
            } else {
                movieView.invalidCastMessage();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validateNewWatched(String value) {
        if (validateNewString(value, "Visualização")) {

            Set<String> validAnswers = Set.of(
                    "sim", "s",
                    "não", "nao", "n",
                    "não assisti", "nao assisti", "n assisti", "assisti nao", "assisti não", "assisti n",
                    "sim assisti", "assisti sim", "s assisti", "assisti s", "assisti", "já assisti", "ja assisti", "já", "ja"
            );

            if (validAnswers.contains(value.toLowerCase())) {
                return true;
            } else {
                movieView.invalidWatchedMessage();
            }
        }

        return false;
    }

    public boolean validateNewString(String value, String name) {
        if (value.isEmpty()) {
            movieView.emptyValueMessage(name);
            return false;
        }
        return true;
    }

    public boolean validateNewInputString(String value) {
        if (value.isEmpty()) {
            movieView.emptyInformationMessage();
            return false;
        }
        return true;
    }

    public boolean searchMovieByTitle(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean movieFound = false;
            if (!listOfMovies.isEmpty()) {

                for (MovieModel movie : listOfMovies) {
                    if (movie.getTitle().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }
                        movieView.movieInformation(movie);
                    }
                }
            }
            if (!movieFound) { movieView.noMovieFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean searchMovieByDirection(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean movieFound = false;
            if (!listOfMovies.isEmpty()) {

                for (MovieModel movie : listOfMovies) {
                    if (movie.getDirection().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }
                        movieView.movieInformation(movie);
                    }
                }
            }
            if (!movieFound) { movieView.noMovieFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean searchMovieByActorInTheCast(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean movieFound = false;
            if (!listOfMovies.isEmpty()) {
                for (MovieModel movie : listOfMovies) {
                    ArrayList<String> cast = movie.getCast();
                    boolean foundInThisMovie = false;

                    for (String actor : cast) {
                        if (actor.toLowerCase().contains(value.toLowerCase().trim())) {
                            foundInThisMovie = true;
                            break;
                        }
                    }

                    if (foundInThisMovie) {
                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }
                        movieView.movieInformation(movie);
                    }
                }
            }

            if (!movieFound) { movieView.noMovieFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean searchMovieByGenre(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean movieFound = false;
            if (!listOfMovies.isEmpty()) {

                for (MovieModel movie : listOfMovies) {
                    if (movie.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }
                        movieView.movieInformation(movie);
                    }
                }
            }
            if (!movieFound) { movieView.noMovieFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean searchMovieByYearOfRelease(String value) {
        value = value.trim();

        if (validateNewInputString(value) && validateNewInputInt(value)) {

            boolean movieFound = false;
            if (!listOfMovies.isEmpty()) {

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value);
                } catch (Exception e) {
                    movieView.invalidMessage();
                    return false;
                }


                for (MovieModel movie : listOfMovies) {
                    if (movie.getYearOfRelease() == valueInt) {

                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }

                        movieView.movieInformation(movie);
                    }
                }

            }
            if (!movieFound) { movieView.noMovieFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean listMovies() {
        try {
            if (listOfMovies.isEmpty()) {
                movieView.emptyListMessage();
            } else {
                movieView.headerForMovie();
                for (MovieModel movie : listOfMovies) {
                    movieView.movieInformation(movie);
                }
            }
            return true;
        } catch (Exception e) {
            movieView.invalidMessage();
            return false;
        }
    }

    public boolean filterListOfMoviesByGenre(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean movieFound = false;
            if (!listOfMovies.isEmpty()) {

                for (MovieModel movie : listOfMovies) {
                    if (movie.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }

                        movieView.movieInformation(movie);
                    }
                }

            }
            if (!movieFound) { movieView.noMovieFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean filterListOfMoviesByYearOfRelease(String value) {
        value = value.trim();

        if (validateNewInputString(value) && validateNewInputInt(value)) {

            boolean movieFound = false;
            if (!listOfMovies.isEmpty()) {

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value);
                } catch (Exception e) {
                    movieView.invalidMessage();
                    return false;
                }

                for (MovieModel movie : listOfMovies) {
                    if (movie.getYearOfRelease() == valueInt) {

                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;
                        }

                        movieView.movieInformation(movie);
                    }
                }

            }
            if (!movieFound) { movieView.noMovieFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean sortListByTopRated() {
        try {
            if (!listOfMovies.isEmpty()) {
                ArrayList<MovieModel> listOfReviewedMovies = new ArrayList<MovieModel>();

                for (MovieModel movie : listOfMovies) {
                    if (movie.getMovieReview() != null) {
                        listOfReviewedMovies.add(movie);
                    }
                }

                ArrayList<MovieModel> highlyRatedMovies = new ArrayList<MovieModel>(listOfReviewedMovies);

                if (!highlyRatedMovies.isEmpty()){
                    highlyRatedMovies.sort(Comparator.comparing(movieModel -> movieModel.getMovieReview().getScore(), Comparator.reverseOrder()));
                } else {
                    movieView.emptyEvaluatedListMessage();
                    return true;
                }

                movieView.headerForMovie();
                for (MovieModel movie : highlyRatedMovies) {
                    movieView.movieInformation(movie);
                }

            } else {
                movieView.emptyListMessage();
            }

            return true;
        } catch (Exception e) {
            movieView.invalidMessage();
            return false;
        }
    }

    public boolean sortListByLowRated() {
        try {
            if (!listOfMovies.isEmpty()) {
                ArrayList<MovieModel> listOfReviewedMovies = new ArrayList<MovieModel>();

                for (MovieModel movie : listOfMovies) {
                    if (movie.getMovieReview() != null) {
                        listOfReviewedMovies.add(movie);
                    }
                }

                ArrayList<MovieModel> poorlyRatedMovies = new ArrayList<MovieModel>(listOfReviewedMovies);

                if (!poorlyRatedMovies.isEmpty()){
                    poorlyRatedMovies.sort(Comparator.comparing(movieModel -> movieModel.getMovieReview().getScore()));
                } else {
                    movieView.emptyEvaluatedListMessage();
                    return true;
                }

                movieView.headerForMovie();
                for (MovieModel movie : poorlyRatedMovies) {
                    movieView.movieInformation(movie);
                }

            } else {
                movieView.emptyListMessage();
            }

            return true;
        } catch (Exception e) {
            movieView.invalidMessage();
            return false; }
    }

    public boolean openMovie(int index) {
        try {
            MovieModel movie;
            try {
                movie = listOfMovies.get(index-1);
            } catch (Exception e) {
                movieView.noMovieFoundMessage();
                return false;
            }

            movieView.fullMovieInformation(movie);
            return true;
        }   catch (Exception e) {
            movieView.invalidMessage();
            return false;
        }
    }

    public boolean changeMovieViewingStatus(int index, String value) {
        MovieModel movie;

        try {
            movie = listOfMovies.get(index-1);
        } catch (Exception e) {
            movieView.noMovieFoundMessage();
            return false;
        }

        value = value.trim();

        if (movie == null) {
            movieView.invalidMessage();
            return false;
        }

        boolean validRead = validateNewWatched(value);

        if (!validRead) {
            movieView.tryAgainMessage();
            return false;
        }

        try {
            Set<String> positiveResponsesWatched = Set.of(
                    "sim", "s", "assisti", "sim assisti", "assisti sim", "s assisti", "assisti s", "já assisti", "ja assisti", "já", "ja"
            );

            Set<String> negativeResponsesWatched = Set.of(
                    "não", "nao", "n", "não assisti", "nao assisti", "n assisti",
                    "assisti não", "assisti nao", "assisti n"
            );

            boolean watched = false ;
            if (positiveResponsesWatched.contains(value.toLowerCase())) {
                watched = true;
            } else if ((movie.isWatched() == false) && negativeResponsesWatched.contains(value.toLowerCase())){
                watched = false;
            }
            else if ((movie.isWatched() == true) && negativeResponsesWatched.contains(value.toLowerCase())) {
                movieView.wrongWatchedMessage();
                return false;
            }

            movie.setWatched(watched);
            movieView.updatedWatchedMessage();

            return true;
        } catch (Exception e) {
            movieView.invalidMessage();
            return false;
        }
    }

    public boolean evaluateMovie(int index, String score, String consumptionDate, String comment) {
        try {
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            MovieModel movie;
            try {
                movie = listOfMovies.get(index-1);
            } catch (Exception e) {
                movieView.noMovieFoundMessage();
                return false;
            }

            if (!checkMovieReview(movie)) {
                if (movie.isWatched()) {
                    boolean validScore = validateNewScore(score);
                    boolean validConsumptionDate = validateNewDate(movie, consumptionDate);
                    boolean validComment = validateNewString(comment, "Comentários");

                    if (validScore == false || validConsumptionDate == false || validComment == false) {
                        movieView.tryAgainMessage();
                        return false;
                    }

                    float scoreFloat = 0f;
                    try {
                        scoreFloat = Float.parseFloat(score);
                    } catch (Exception e) {
                        movieView.invalidMessage();
                        return false;
                    }

                    ReviewModel reviewModel = new ReviewModel(scoreFloat, consumptionDate, comment);
                    movie.setMovieReview(reviewModel);
                    movie.setEvaluatedMovie(true);

                    movieView.registeredEvaluationMessage();
                    return true;
                } else {
                    movieView.unwatchedMovieMessage();
                    return false;
                }
            } else {
                movieView.messageOfMovieAlreadyEvaluated();
                return false;
            }
        } catch (Exception e) {
            movieView.invalidMessage();
            return false;
        }
    }

    public boolean evaluateMovieAgain(int index, String score, String consumptionDate, String comment) {
        try {
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            MovieModel movie = listOfMovies.get(index-1);

            if (checkMovieReview(movie)) {
                movie.setEvaluatedMovie(false);
                return evaluateMovie(index, score, consumptionDate, comment);
            } else {
                movieView.unratedMovieMessage();
                return false;
            }
        } catch (Exception e) {
            movieView.invalidMessage();
            return false;
        }
    }

    public boolean validateNewScore(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {
            try {
                float score = Float.parseFloat(value);
                if (score < 1 || score > 5) {
                    movieView.invalidScoreMessage();
                    return false;
                }
                return true;
            } catch (Exception e) {
                movieView.invalidNumberMessage();
                return false;
            }
        }

        return false;
    }

    public boolean validateNewDate(MovieModel movie, String value) {
        value = value.trim();

        if (validateNewInputString(value)) {
            String[] parts = value.split("/");
            if (parts.length != 3) {
                movieView.invalidDateFormatMessage();
                return false;
            }

            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
                movieView.invalidDateMessage();
                return false;
            }

            boolean valid = validateExistingDate(day, month, year);
            if (!valid) { return false; }

            try {
                int yearInt = Integer.parseInt(year);

                if (yearInt < movie.getYearOfRelease()) {
                    movieView.invalidYearPeriodMessage(movie.getYearOfRelease());
                    return false;
                }
            } catch (NumberFormatException e) {
                movieView.invalidDateMessage();
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
                movieView.nonExistentDateMessage();
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
                movieView.invalidFutureDatesMessage();
                return false;
            }

            return true;

        } catch (Exception e) {
            movieView.invalidDateMessage();
            return false;
        }
    }

    public boolean checkMovieReview(MovieModel movie) {
        if (movie.isEvaluatedMovie()) {
            return true;
        }
        return false;
    }

    public boolean validateNewInputInt(String value) {
        try {
            int valueInt = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            movieView.integerMessage();
            return false;
        }
    }

    public ArrayList<MovieModel> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(ArrayList<MovieModel> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }
}