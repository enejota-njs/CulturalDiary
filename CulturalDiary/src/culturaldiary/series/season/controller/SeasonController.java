package culturaldiary.series.season.controller;

import culturaldiary.media.model.ReviewModel;
import culturaldiary.series.season.model.SeasonModel;
import culturaldiary.series.season.view.SeasonView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class SeasonController {
    SeasonView seasonView = new SeasonView();
    SeasonModel seasonModel;

    Scanner input = new Scanner(System.in);

    public SeasonModel registerSeason(int start, int end, int index) {
        String temporaryGenre = validateNewString(index);
        ArrayList<String> temporaryCast = validateNewCast(index);
        int temporaryYear = validateNewYear(start, end, index);

        seasonModel = new SeasonModel(temporaryGenre, temporaryCast, temporaryYear, index);

        return seasonModel;
    }

    public void openSeasonInformation(SeasonModel season) {
        seasonView.seasonInformation(season);
    }

    public boolean checkSeasonReview(SeasonModel season) {
        if (!season.isEvaluatedSeason()) {

            String watched;
            do {
                seasonView.evaluationMsg(1);
                watched = input.nextLine().trim();

                if (watched.isEmpty()) {
                    seasonView.emptyValueMsg();
                }
            } while (watched.isEmpty());

            if (watched.equalsIgnoreCase("s")
                    || watched.equalsIgnoreCase("sim")) {
                evaluateSeason(season);
            }
            else if (watched.equalsIgnoreCase("n")
                    || watched.equalsIgnoreCase("não")
                    || watched.equalsIgnoreCase("nao")){
                seasonView.evaluationMsg(2);
                return true;
            } else {
                seasonView.evaluationMsg(9);
                return checkSeasonReview(season);
            }
        } else {
            String evaluateAgain;
            do {
                seasonView.evaluationMsg(3);
                evaluateAgain = input.nextLine().trim();

                if (evaluateAgain.isEmpty()) {
                    seasonView.emptyValueMsg();
                }
            } while (evaluateAgain.isEmpty());


            if (evaluateAgain.equalsIgnoreCase("s")
                    || evaluateAgain.equalsIgnoreCase("sim")) {
                evaluateSeason(season);
            } else if (evaluateAgain.equalsIgnoreCase("n")
                    || evaluateAgain.equalsIgnoreCase("não")
                    || evaluateAgain.equalsIgnoreCase("nao")) {
                return true;
            }
            else {
                seasonView.invalidValueMsg();
                return checkSeasonReview(season);
            }
        }
        return true;
    }

    public void evaluateSeason(SeasonModel season) {
        String temporaryConsumptionDate;
        String temporaryComment;
        float temporaryScore = 0.0f;

        while (true) {
            seasonView.evaluationMsg(4);
            String scoreString = input.nextLine().trim();

            if (scoreString.isEmpty()) {
                seasonView.emptyValueMsg();
                continue;
            }

            try {
                temporaryScore = Float.parseFloat(scoreString);
                if (temporaryScore < 1 || temporaryScore > 5) {
                    seasonView.evaluationMsg(5);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                seasonView.evaluationMsg(10);
            }
        }

        temporaryConsumptionDate = validateNewDate(season);

        do {
            seasonView.evaluationMsg(7);
            temporaryComment = input.nextLine().trim();

            if (temporaryComment.isEmpty()) {
                seasonView.emptyValueMsg();
            }
        } while (temporaryComment.isEmpty());

        seasonView.evaluationMsg(8);

        ReviewModel movieReview = new ReviewModel(true, temporaryScore, temporaryConsumptionDate, temporaryComment);
        season.setSeasonReview(movieReview);
        season.setEvaluatedSeason(true);
    }

    public String validateNewString(int c) {
        String value;

        do {
            seasonView.registrationMsg(1, 0, c);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                seasonView.emptyValueMsg();
            }
        } while (value.isEmpty());

        return value;
    }

    public ArrayList<String> validateNewCast(int c) {
        ArrayList<String> value = new ArrayList<String>();
        int amount = 0;
        String amountString;

        do {
            seasonView.registrationMsg(2, 0, c);
            amountString = input.nextLine().trim();
            if (amountString.isEmpty()) {
                seasonView.emptyValueMsg();
            }
        } while (amountString.isEmpty());

        try {
            amount = Integer.parseInt(amountString);
        } catch (NumberFormatException e) {
            seasonView.invalidValueMsg();
            return validateNewCast(c);
        }

        if (amount < 1) {
            seasonView.registrationMsg(5, 0, 0);
            return validateNewCast(c);
        }

        String person;
        for (int i = 1; i <= amount; i++) {
            do {
                seasonView.registrationMsg(3, i, c);
                person = input.nextLine().trim();
                if (person.isEmpty()) {
                    seasonView.emptyValueMsg();
                }
            } while (person.isEmpty());
            value.add(person);
        }

        return value;
    }

    public int validateNewYear(int start, int end, int c) {
        int value = 0;
        String stringValue;

        do {
            seasonView.registrationMsg(4, 0, c);
            stringValue = input.nextLine().trim();
            if (stringValue.isEmpty()) {
                seasonView.emptyValueMsg();
            }
        } while (stringValue.isEmpty());

        try {
            value = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            seasonView.invalidYearMsg(1, 0, 0);
            return validateNewYear(start, end, c);
        }

        if (value < start || value > end) {
            seasonView.invalidYearMsg(2, start, end);
            return validateNewYear(start, end, c);
        }

        return value;
    }

    public String validateNewDate(SeasonModel season) {
        String stringValue;

        do {
            seasonView.evaluationMsg(6);
            stringValue = input.nextLine().trim();

            if (stringValue.isEmpty()) {
                seasonView.emptyValueMsg();
            }
        } while (stringValue.isEmpty());

        String[] parts = stringValue.split("/");
        if (parts.length != 3) {
            seasonView.invalidDateMsg(2, 0);
            return validateNewDate(season);
        }

        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
            seasonView.invalidDateMsg(2, 0);
            return validateNewDate(season);
        }

        int valid = validateExistingDate(day, month, year);

        if (valid == 0 || valid == 2) {
            return validateNewDate(season);
        }

        int yearOfRelease = season.getYearSeason();

        try {
            int yearInt = Integer.parseInt(year);

            if (yearInt < yearOfRelease) {
                seasonView.invalidDateMsg(3, yearOfRelease);
                return validateNewDate(season);
            }
        } catch (NumberFormatException e) {
            seasonView.invalidDateMsg(2, 0);
            return validateNewDate(season);
        }

        return stringValue;
    }

    public int validateExistingDate(String day, String month, String year) {
        try {
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);

            if (m < 1 || m > 12) {
                seasonView.invalidDateMsg(2, 0);
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
                seasonView.invalidDateMsg(1, 0);
                return 0;
            }

            return 1;

        } catch (Exception e) {
            seasonView.invalidDateMsg(2, 0);
            return 2;
        }
    }
}