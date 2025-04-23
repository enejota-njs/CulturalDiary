package culturaldiary.series.series;

import culturaldiary.review.ReviewModel;
import culturaldiary.series.season.SeasonController;
import culturaldiary.series.season.SeasonModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

public class SeriesController {
    SeriesModel seriesModel;
    SeriesView seriesView = new SeriesView();
    SeriesRepository seriesRepository = new SeriesRepository();
    private ArrayList<SeriesModel> listOfSeries = seriesRepository.getListOfSeries();

    SeasonController seasonController = new SeasonController();

    Calendar calendar = Calendar.getInstance();

    public boolean registerSeries(String title, String yearOfReleaseString, String yearOfConclusionString, String originalTitle, String whereToWatch, String[][] listOfSeasonString) {
        title = title.trim();
        yearOfReleaseString = yearOfReleaseString.trim();
        yearOfConclusionString = yearOfConclusionString.trim();
        originalTitle = originalTitle.trim();
        whereToWatch = whereToWatch.trim();

        boolean validTitle = validateTitle(title);
        boolean validYearOfRelease = validateYearOfRelease(yearOfReleaseString);
        boolean validYearOfConclusion = validateYearOfConclusion(yearOfConclusionString);
        boolean validOriginalTitle = validateOriginalTitle(originalTitle);
        boolean validWhereToWatch = validateWhereToWatch(whereToWatch);

        if (validTitle == false || validYearOfRelease == false || validYearOfConclusion == false || validOriginalTitle == false || validWhereToWatch == false) {
            seriesView.tryAgainMessage();
            return false;
        }

        try {
            int yearOfRelease = Integer.parseInt(yearOfReleaseString);
            int yearOfConclusion = Integer.parseInt(yearOfConclusionString);

            if (yearOfConclusion < yearOfRelease) {
                seriesView.invalidYearsMessage();
                return false;
            }

            ArrayList<SeasonModel> listOfSeasons = new ArrayList<SeasonModel>();

            for (int season = 0; season <= listOfSeasonString.length-1; season++) {
                String genreSeason = listOfSeasonString[season][0];
                String castStringSeason = listOfSeasonString[season][1];
                String yearStringSeason = listOfSeasonString[season][2];
                String watchedStringSeason = listOfSeasonString[season][3];

                boolean validSeason = seasonController.validateSeason(genreSeason, castStringSeason, yearStringSeason, watchedStringSeason, season+1);

                if (!validSeason) {
                    return false;
                }

                String[] castPeople = castStringSeason.split(",\\s*");

                ArrayList<String> castSeason = new ArrayList<String>();
                for (String personal : castPeople) {
                    if (!personal.isEmpty()) {
                        castSeason.add(personal.trim());
                    }
                }

                if (castSeason.isEmpty()) {
                    seriesView.emptyCastMessage(season+1);
                    return false;
                }

                int yearSeason = Integer.parseInt(yearStringSeason);

                if (yearSeason < yearOfRelease || yearSeason > yearOfConclusion) {
                    seriesView.invalidSeasonYearMessage(season+1);
                    return false;
                }

                Set<String> positiveResponsesWatched = Set.of(
                        "sim", "s", "assisti", "sim assisti", "assisti sim", "s assisti", "assisti s", "já assisti", "ja assisti", "já", "ja"
                );

                Set<String> negativeResponsesWatched = Set.of(
                        "não", "nao", "n", "não assisti", "nao assisti", "n assisti",
                        "assisti não", "assisti nao", "assisti n"
                );

                boolean watchedSeason = false;
                if (positiveResponsesWatched.contains(watchedStringSeason.toLowerCase())) { watchedSeason = true; }
                else if (negativeResponsesWatched.contains(watchedStringSeason.toLowerCase())) { watchedSeason = false; }

                SeasonModel seasonModel = new SeasonModel(genreSeason, castSeason, yearSeason, watchedSeason, season+1);
                listOfSeasons.add(seasonModel);
            }

            seriesModel = new SeriesModel(title, yearOfRelease, yearOfConclusion, originalTitle, whereToWatch, listOfSeasons);
            seriesRepository.addSeries(seriesModel);

            seriesView.registeredSeriesMessage(title);

            return true;
        } catch (Exception e) {
            seriesView.invalidMessage();
            return false;
        }
    }

    public boolean validateTitle(String title) {
        return validateNewString(title, "Título");
    }

    public boolean validateYearOfRelease(String yearOfRelease) {
        return validateNewYear(yearOfRelease, "Ano de lançamento");
    }

    public boolean validateYearOfConclusion(String yearOfConclusion) {
        return validateNewYear(yearOfConclusion, "Ano de encerramento");
    }

    public boolean validateOriginalTitle(String originalTitle) {
        return validateNewString(originalTitle, "Título original");
    }

    public boolean validateNewString(String value, String name) {
        if (value.isEmpty()) {
            seriesView.emptyValueMessage(name);
            return false;
        }
        return true;
    }

    public boolean validateWhereToWatch(String whereToWatch) {
        return validateNewString(whereToWatch, "Onde assistir");
    }

    public boolean validateNewYear(String value, String name) {

        if (validateNewString(value, name)) {
            int valueInt = 0;
            int currentYear = calendar.get(Calendar.YEAR);

            try {
                valueInt = Integer.parseInt(value);
            } catch (Exception e) {
                seriesView.integerMessage();
                return false;
            }

            if (valueInt < 1700 || valueInt > currentYear) {
                seriesView.invalidYearMessage(currentYear);
                return false;
            }

            return true;
        }

        return false;
    }

    public boolean searchSeriesByTitle(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean seriesFound = false;
            if (!listOfSeries.isEmpty()) {

                for (SeriesModel series : listOfSeries) {
                    if (series.getTitle().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!seriesFound) {
                            seriesView.headerForSeries();
                            seriesFound = true;
                        }
                        seriesView.seriesInformation(series);
                    }
                }
            }
            if (!seriesFound) { seriesView.noSeriesFoundMessage(); }
            return true;
        }

        return false;
    }

    public boolean listSeries() {
        try {
            if (listOfSeries.isEmpty()) {
                seriesView.emptyListMessage();
            } else {
                seriesView.headerForSeries();
                for (SeriesModel series : listOfSeries) {
                    seriesView.seriesInformation(series);
                }
            }
            return true;
        } catch (Exception e) {
            seriesView.invalidMessage();
            return false;
        }
    }

    public boolean filterListOfSeriesByGenre(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {

            boolean seriesFound = false;
            if (!listOfSeries.isEmpty()) {

                for (SeriesModel series : listOfSeries) {
                    for (SeasonModel season : series.getListOfSeasons()) {
                        if (season.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {
                            if (!seriesFound) {
                                seriesView.headerForSeries();
                                seriesFound = true;
                            }

                            seriesView.seriesInformation(series);
                        }
                    }
                }
            }

            if (!seriesFound) { seriesView.noSeriesFoundMessage(); }
            return true;
        }
        return false;
    }

    public boolean filterListOfSeriesByYearOfRelease(String value) {
        value = value.trim();

        if (validateNewInputString(value) && validateNewInputInt(value)) {

            boolean seriesFound = false;
            if (!listOfSeries.isEmpty()) {

                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value);
                } catch (Exception e) {
                    seriesView.invalidMessage();
                    return false;
                }

                for (SeriesModel series : listOfSeries) {
                    if (series.getYearOfRelease() == valueInt) {

                        if (!seriesFound) {
                            seriesView.headerForSeries();
                            seriesFound = true;
                        }

                        seriesView.seriesInformation(series);
                    }
                }

            }
            if (!seriesFound) { seriesView.noSeriesFoundMessage(); }
            return true;
        }
        return false;
    }

    public boolean sortListByTopRated() {
        try {
            if (!listOfSeries.isEmpty()) {
                ArrayList<SeriesModel> listOfReviewedSeries = new ArrayList<SeriesModel>();

                for (SeriesModel series : listOfSeries) {
                    if (series.getSeriesReview() != 0) {
                        listOfReviewedSeries.add(series);
                    }
                }

                ArrayList<SeriesModel> highlyRatedSeries = new ArrayList<SeriesModel>(listOfReviewedSeries);

                if (!highlyRatedSeries.isEmpty()){
                    highlyRatedSeries.sort(Comparator.comparing(seriesModel -> seriesModel.getSeriesReview(), Comparator.reverseOrder()));
                } else {
                    seriesView.emptyEvaluatedListMessage();
                    return true;
                }

                seriesView.headerForSeries();
                for (SeriesModel series : highlyRatedSeries) {
                    seriesView.seriesInformation(series);
                }

            } else {
                seriesView.emptyListMessage();
            }

            return true;
        } catch (Exception e) {
            seriesView.invalidMessage();
            return false;
        }
    }

    public boolean sortListByLowRated() {
        try {
            if (!listOfSeries.isEmpty()) {
                ArrayList<SeriesModel> listOfReviewedSeries = new ArrayList<SeriesModel>();

                for (SeriesModel series : listOfSeries) {
                    if (series.getSeriesReview() != 0) {
                        listOfReviewedSeries.add(series);
                    }
                }

                ArrayList<SeriesModel> poorlyRatedSeries = new ArrayList<SeriesModel>(listOfReviewedSeries);

                if (!poorlyRatedSeries.isEmpty()){
                    poorlyRatedSeries.sort(Comparator.comparing(seriesModel -> seriesModel.getSeriesReview()));
                } else {
                    seriesView.emptyEvaluatedListMessage();
                    return true;
                }

                seriesView.headerForSeries();
                for (SeriesModel series : poorlyRatedSeries) {
                    seriesView.seriesInformation(series);
                }

            } else {
                seriesView.emptyListMessage();
            }

            return true;
        } catch (Exception e) {
            seriesView.invalidMessage();
            return false; }
    }

    public boolean openSeries(int index) {
        try {
            SeriesModel series;
            try {
                series = listOfSeries.get(index-1);
            } catch (Exception e) {
                seriesView.noSeriesFoundMessage();
                return false;
            }

            seriesView.fullSeriesInformation(series);
            for (SeasonModel season : series.getListOfSeasons()) {
                seasonController.openSeason(season);
            }

            return true;
        }   catch (Exception e) {
            seriesView.invalidMessage();
            return false;
        }
    }

    public boolean changeSeasonViewingStatus(int indexSeries, int indexSeason, String value) {
        SeriesModel series;
        SeasonModel season;

        try {
            series = listOfSeries.get(indexSeries-1);
        } catch (Exception e) {
            seriesView.noSeriesFoundMessage();
            return false;
        }

        try {
            season = series.getListOfSeasons().get(indexSeason-1);
        } catch (Exception e) {
            seriesView.noSeasonFoundMessage();
            return false;
        }

        value = value.trim();

        if (series == null || season == null) {
            seriesView.invalidMessage();
            return false;
        }

        boolean validWatched = validateNewWatched(value);

        if (!validWatched) {
            seriesView.tryAgainMessage();
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
            } else if ((season.isWatched() == false) && negativeResponsesWatched.contains(value.toLowerCase())){
                watched = false;
            }
            else if ((season.isWatched() == true) && negativeResponsesWatched.contains(value.toLowerCase())) {
                seriesView.wrongWatchedMessage();
                return false;
            }

            season.setWatched(watched);
            seriesView.updatedWatchedMessage();

            return true;
        } catch (Exception e) {
            seriesView.invalidMessage();
            return false;
        }
    }

    public boolean evaluateSeason(int indexSeries, int indexSeason, String score, String consumptionDate, String comment) {
        try {
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            SeriesModel series;
            SeasonModel season;

            try {
                series = listOfSeries.get(indexSeries-1);
            } catch (Exception e) {
                seriesView.noSeriesFoundMessage();
                return false;
            }

            try {
                season = series.getListOfSeasons().get(indexSeason-1);
            } catch (Exception e) {
                seriesView.noSeasonFoundMessage();
                return false;
            }

            if (!checkSeasonReview(season)) {
                if (season.isWatched()) {
                    boolean validScore = validateNewScore(score);
                    boolean validConsumptionDate = validateNewDate(season, consumptionDate);
                    boolean validComment = validateNewString(comment, "Comentários");

                    if (validScore == false || validConsumptionDate == false || validComment == false) {
                        seriesView.tryAgainMessage();
                        return false;
                    }

                    float scoreFloat = 0f;
                    try {
                        scoreFloat = Float.parseFloat(score);
                    } catch (Exception e) {
                        seriesView.invalidMessage();
                        return false;
                    }

                    ReviewModel reviewModel = new ReviewModel(scoreFloat, consumptionDate, comment);
                    season.setSeasonReview(reviewModel);
                    season.setEvaluatedSeason(true);

                    seriesView.registeredEvaluationMessage();

                    updateAverage(series);
                    return true;
                } else {
                    seriesView.unwatchedSeasonMessage();
                    return false;
                }
            } else {
                seriesView.messageOfSeasonAlreadyEvaluated();
                return false;
            }
        } catch (Exception e) {
            seriesView.invalidMessage();
            return false;
        }
    }

    public boolean evaluateSeasonAgain(int indexSeries, int indexSeason, String score, String consumptionDate, String comment) {
        try {
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            SeriesModel series = listOfSeries.get(indexSeries-1);
            SeasonModel season = series.getListOfSeasons().get(indexSeason-1);

            if (checkSeasonReview(season)) {
                season.setEvaluatedSeason(false);
                return evaluateSeason(indexSeries, indexSeason, score, consumptionDate, comment);
            } else {
                seriesView.unratedSeasonMessage();
                return false;
            }
        } catch (Exception e) {
            seriesView.invalidMessage();
            return false;
        }
    }

    public boolean checkSeasonReview(SeasonModel season) {
        if (season.isEvaluatedSeason()) {
            return true;
        }
        return false;
    }

    public boolean updateAverage(SeriesModel series) {
        float sum = 0;
        int count = 0;

        ReviewModel review;
        for (SeasonModel season : series.getListOfSeasons()) {
            review = season.getSeasonReview();
            if (review != null) {
                sum += review.getScore();
                count++;
            }
        }

        if (count != 0) {
            series.setSeriesReview(sum/count);
            return true;
        } else {
            return false;
        }
    }

    public boolean validateNewDate(SeasonModel season, String value) {
        value = value.trim();

        if (validateNewInputString(value)) {
            String[] parts = value.split("/");
            if (parts.length != 3) {
                seriesView.invalidDateFormatMessage();
                return false;
            }

            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
                seriesView.invalidDateMessage();
                return false;
            }

            boolean valid = validateExistingDate(day, month, year);
            if (!valid) { return false; }

            try {
                int yearInt = Integer.parseInt(year);

                if (yearInt < season.getYearSeason()) {
                    seriesView.invalidYearPeriodMessage(season.getYearSeason());
                    return false;
                }
            } catch (NumberFormatException e) {
                seriesView.invalidDateMessage();
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
                seriesView.nonExistentDateMessage();
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
                seriesView.invalidFutureDatesMessage();
                return false;
            }

            return true;

        } catch (Exception e) {
            seriesView.invalidDateMessage();
            return false;
        }
    }

    public boolean validateNewScore(String value) {
        value = value.trim();

        if (validateNewInputString(value)) {
            try {
                float score = Float.parseFloat(value);
                if (score < 1 || score > 5) {
                    seriesView.invalidScoreMessage();
                    return false;
                }
                return true;
            } catch (Exception e) {
                seriesView.invalidNumberMessage();
                return false;
            }
        }

        return false;
    }

    public boolean validateNewInputInt(String value) {
        try {
            int valueInt = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            seriesView.integerMessage();
            return false;
        }
    }

    public boolean validateNewInputString(String value) {
        if (value.isEmpty()) {
            seriesView.emptyInformationMessage();
            return false;
        }
        return true;
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
                seriesView.invalidWatchedMessage();
            }
        }

        return false;
    }

    public ArrayList<SeriesModel> getListOfSeries() {
        return listOfSeries;
    }

    public void setListOfSeries(ArrayList<SeriesModel> listOfSeries) {
        this.listOfSeries = listOfSeries;
    }
}
