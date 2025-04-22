package culturaldiary.series.season;

import java.util.Calendar;
import java.util.Set;

public class SeasonController {
    SeasonView seasonView = new SeasonView();

    Calendar calendar = Calendar.getInstance();

    public boolean validateSeason(String genre, String castString, String yearSeasonString, String watchedString, int index) {
        genre = genre.trim();
        castString = castString.trim();
        yearSeasonString = yearSeasonString.trim();
        watchedString = watchedString.trim();

        boolean validGenre = validateGenre(genre, index);
        boolean validCastString = validateCast(castString, index);
        boolean validYearSeason = validateYearSeason(yearSeasonString, index);
        boolean validWatched = validateWatched(watchedString, index);

        if (validGenre == false || validCastString == false || validYearSeason == false || validWatched == false) {
            seasonView.errorMessageInSeason(index);
            seasonView.tryAgainMessage();
            return false;
        } else {
            return true;
        }
    }

    public boolean validateGenre(String genre, int index) {
        return validateNewString(genre, "Gênero da " + index + "° temporada");
    }

    public boolean validateCast(String cast, int index) {
        return validateNewCast(cast, index);
    }

    public boolean validateNewCast(String value, int index) {
        if (validateNewString(value, "Elenco da " + index + "° temporada")) {
            if (value.matches("[\\p{L}, ]*")) {
                return true;
            } else {
                seasonView.invalidCastMessage();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validateYearSeason(String yearOfRelease, int index) {
        return validateNewYear(yearOfRelease, index);
    }

    public boolean validateNewYear(String value, int index) {
        if (validateNewString(value, "Ano de lançamento da " + index + "° temporada")) {
            int valueInt = 0;
            int currentYear = calendar.get(Calendar.YEAR);

            try {
                valueInt = Integer.parseInt(value);
            } catch (Exception e) {
                seasonView.integerMessage();
                return false;
            }

            if (valueInt < 1700 || valueInt > currentYear) {
                seasonView.invalidYearMessage(currentYear);
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean validateWatched(String watched, int index) {
        return validateNewWatched(watched, index);
    }

    public boolean validateNewWatched(String value, int index) {
        if (validateNewString(value, "Visualização da " + index + "° temporada")) {

            Set<String> validAnswers = Set.of(
                    "sim", "s",
                    "não", "nao", "n",
                    "não assisti", "nao assisti", "n assisti", "assisti nao", "assisti não", "assisti n",
                    "sim assisti", "assisti sim", "s assisti", "assisti s", "assisti", "já assisti", "ja assisti", "já", "ja"
            );

            if (validAnswers.contains(value.toLowerCase())) {
                return true;
            } else {
                seasonView.invalidWatchedMessage();
            }
        }

        return false;
    }

    public boolean validateNewString(String value, String name) {
        if (value.isEmpty()) {
            seasonView.emptyValueMessage(name);
            return false;
        }
        return true;
    }

    public void openSeason(SeasonModel season) {
        seasonView.fullSeasonInformation(season);
    }
}