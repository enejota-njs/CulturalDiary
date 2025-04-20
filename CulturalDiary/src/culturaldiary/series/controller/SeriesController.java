package culturaldiary.series.controller;

import culturaldiary.media.model.ReviewModel;
import culturaldiary.series.model.SeriesModel;
import culturaldiary.series.model.repository.SeriesRepository;
import culturaldiary.series.season.controller.SeasonController;
import culturaldiary.series.season.model.SeasonModel;
import culturaldiary.series.view.SeriesView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Scanner;

public class SeriesController {
    SeriesView seriesView = new SeriesView();
    SeriesModel seriesModel;
    SeriesRepository seriesRepository = new SeriesRepository();
    ArrayList<SeriesModel> listOfSeries = seriesRepository.getListOfSeries();
    SeasonController seasonController = new SeasonController();

    Scanner input = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public void registerSeries() {
        String temporaryTitle = validateNewString(1);
        int temporaryYearOfRelease = validateNewYear(2, 1700);
        int temporaryYearOfConclusion = validateNewYear( 3, temporaryYearOfRelease);
        String temporaryOriginalTitle = validateNewString(4);
        String temporaryWhereToWatch = validateNewString(5);
        ArrayList<SeasonModel> temporaryListOfSeasons = validateNewList(temporaryYearOfRelease, temporaryYearOfConclusion);

        seriesModel = new SeriesModel(temporaryTitle, temporaryYearOfRelease, temporaryYearOfConclusion,
                temporaryOriginalTitle, temporaryWhereToWatch, temporaryListOfSeasons);

        seriesRepository.addSeries(seriesModel);

        int index = seriesModel.getSeriesIndex();
        seriesView.registrationMsg(7, index);

        displayAdditionalRegisterOptions();
    }

    public boolean displayAdditionalRegisterOptions() {
        seriesView.additionalRegistrationMsg();
        seriesView.chooseAnOptionMsg();

        String menuOption = input.nextLine().trim();

        if (menuOption.isEmpty()) {
            seriesView.emptyValueMsg();
            return displayAdditionalRegisterOptions();
        }

        if (menuOption.equals("1")
                || menuOption.equalsIgnoreCase("abrir")
                || menuOption.equalsIgnoreCase("abrir série")
                || menuOption.equalsIgnoreCase("abrir serie")){
            openSeries(1, 2);
        }
        else if (menuOption.equals("2")
                || menuOption.equalsIgnoreCase("avaliar")
                || menuOption.equalsIgnoreCase("avaliar série")
                || menuOption.equalsIgnoreCase("avaliar serie")) {
            openSeries(1, 1);
        } else if (menuOption.equals("3")
                || menuOption.equalsIgnoreCase("cadastrar")
                || menuOption.equalsIgnoreCase("cadastrar série")
                || menuOption.equalsIgnoreCase("cadastrar serie")) {
            registerSeries();
        } else if (menuOption.equals("4")
                || menuOption.equalsIgnoreCase("menu")
                || menuOption.equalsIgnoreCase("menu inicial")){
            return true;
        }
        else {
            seriesView.invalidValueMsg();
            return displayAdditionalRegisterOptions();
        }
        return true;
    }

    public boolean searchSeries() {
        if (listOfSeries.isEmpty()) {
            seriesView.emptyListMsg();
        }
        else {
            seriesView.searchMsg(1);
            seriesView.chooseAnOptionMsg();
            String searchOption = input.nextLine().trim();

            if (searchOption.isEmpty()) {
                seriesView.emptyValueMsg();
                return searchSeries();
            }

            if (searchOption.equals("1")
                    || searchOption.equalsIgnoreCase("titulo")
                    || searchOption.equalsIgnoreCase("título")) {

                String searchInformation;
                do {
                    seriesView.searchMsg(2);
                    searchInformation = input.nextLine().toLowerCase().trim();

                    if (searchInformation.isEmpty()) {
                        seriesView.emptyValueMsg();
                    }
                } while (searchInformation.isEmpty());


                boolean seriesFound = false;
                for (SeriesModel series : listOfSeries) {
                    if (series.getTitle().toLowerCase().contains(searchInformation)) {
                        if (!seriesFound) {
                            seriesView.headerForSeries();
                            seriesFound = true;
                        }
                        seriesView.seriesInformation(series);
                    }
                }

                if (!seriesFound) {
                    seriesView.searchMsg(3);
                }

                displayAdditionalSearchOptions();

            } else if (searchOption.equals("2")
                    || searchOption.equalsIgnoreCase("menu")
                    || searchOption.equalsIgnoreCase("menu inicial")) {
                return true;
            }
            else {
                seriesView.invalidValueMsg();
                return searchSeries();
            }
        }
        return true;
    }

    public boolean displayAdditionalSearchOptions() {
        seriesView.additionalSearchMsg();

        seriesView.chooseAnOptionMsg();
        String optionMenu = input.nextLine().trim();

        if (optionMenu.isEmpty()) {
            seriesView.emptyValueMsg();
            return displayAdditionalSearchOptions();
        }

        if (optionMenu.equals("1")
                || optionMenu.equalsIgnoreCase("abrir")
                || optionMenu.equalsIgnoreCase("abrir série")
                || optionMenu.equalsIgnoreCase("abrir serie")) {
            openSeries(2, 2);
        } else if (optionMenu.equals("2")
                || optionMenu.equalsIgnoreCase("avaliar")
                || optionMenu.equalsIgnoreCase("avaliar série")
                || optionMenu.equalsIgnoreCase("avaliar serie")) {
            openSeries(2, 1);
        } else if (optionMenu.equals("3")
                || optionMenu.equalsIgnoreCase("buscar")
                || optionMenu.equalsIgnoreCase("buscar série")
                || optionMenu.equalsIgnoreCase("buscar serie")) {
            searchSeries();
        } else if (optionMenu.equals("4")
                || optionMenu.equalsIgnoreCase("menu")
                || optionMenu.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            seriesView.invalidValueMsg();
            return displayAdditionalSearchOptions();
        }
        return true;
    }

    public void listSeries() {
        if (listOfSeries.isEmpty()) {
            seriesView.emptyListMsg();
        } else {
            seriesView.headerForSeries();
            for (SeriesModel series : listOfSeries) {
                seriesView.seriesInformation(series);
            }
            displayAdditionalListOptions();
        }
    }

    public boolean displayAdditionalListOptions() {
        seriesView.additionalListMsg();
        seriesView.chooseAnOptionMsg();

        String optionMenu = input.nextLine().trim();

        if (optionMenu.isEmpty()) {
            seriesView.emptyValueMsg();
            return displayAdditionalListOptions();
        }

        if (optionMenu.equals("1")
                || optionMenu.equalsIgnoreCase("abrir")
                || optionMenu.equalsIgnoreCase("abrir série")
                || optionMenu.equalsIgnoreCase("abrir serie")) {
            openSeries(3, 2);
        } else if (optionMenu.equals("2")
                || optionMenu.equalsIgnoreCase("avaliar")
                || optionMenu.equalsIgnoreCase("avaliar série")
                || optionMenu.equalsIgnoreCase("avaliar serie")) {
            openSeries(3, 1);
        } else if (optionMenu.equals("3")
                || optionMenu.equalsIgnoreCase("ordenar")
                || optionMenu.equalsIgnoreCase("ordenar série")
                || optionMenu.equalsIgnoreCase("ordenar serie")) {
            sortList();
        } else if (optionMenu.equals("4")
                || optionMenu.equalsIgnoreCase("filtrar")
                || optionMenu.equalsIgnoreCase("filtrar série")
                || optionMenu.equalsIgnoreCase("filtrar serie")) {
            filterList();
        } else if (optionMenu.equals("5")
                || optionMenu.equalsIgnoreCase("listar")
                || optionMenu.equalsIgnoreCase("listar série")
                || optionMenu.equalsIgnoreCase("listar serie")) {
            listSeries();
        } else if (optionMenu.equals("6")
                || optionMenu.equalsIgnoreCase("menu")
                || optionMenu.equalsIgnoreCase("menu inicial")) {
            return true;
        } else {
            seriesView.invalidValueMsg();
            return displayAdditionalListOptions();
        }
        return true;
    }

    public boolean filterList() {
        seriesView.filterMsg(1);

        seriesView.chooseAnOptionMsg();
        String filterOption = input.nextLine().trim();

        if (filterOption.isEmpty()) {
            seriesView.emptyValueMsg();
            return filterList();
        }

        if (filterOption.equals("1")
                || filterOption.equalsIgnoreCase("gênero")
                || filterOption.equalsIgnoreCase("genero")) {

            String genreOption;
            do {
                seriesView.filterMsg(2);
                genreOption = input.nextLine().trim();

                if (genreOption.isEmpty()) {
                    seriesView.emptyValueMsg();
                }
            } while (genreOption.isEmpty());


            boolean genreFound = false;
            for (SeriesModel series : listOfSeries) {
                boolean genreFoundInSeries = false;


                for (SeasonModel season : series.getListOfSeasons()) {
                    if (season.getGenre().toLowerCase().contains(genreOption)) {
                        genreFoundInSeries = true;
                        break;
                    }
                }


                if (genreFoundInSeries) {
                    if (!genreFound) {
                        seriesView.headerForSeries();
                        genreFound = true;
                    }
                    seriesView.seriesInformation(series);
                }
            }

            if (!genreFound) {
                seriesView.filterMsg(4);
            }

            displayAdditionalListOptions();
        } else if (filterOption.equals("2")
                || filterOption.equalsIgnoreCase("ano")
                || filterOption.equalsIgnoreCase("ano de lançamento")
                || filterOption.equalsIgnoreCase("ano de lancamento")) {

            String yearOption;
            do {
                seriesView.filterMsg(3);
                yearOption = input.nextLine().trim();

                if (yearOption.isEmpty()) {
                    seriesView.emptyValueMsg();
                }
            } while (yearOption.isEmpty());

            boolean yearFound = false;
            try {
                int year = Integer.parseInt(yearOption);

                for (SeriesModel series : listOfSeries) {
                    try {
                        int releaseYear = series.getYearOfRelease();
                        int conclusionYear = series.getYearOfConclusion();

                        if (year >= releaseYear && year <= conclusionYear) {
                            if (!yearFound) {
                                seriesView.headerForSeries();
                                yearFound = true;
                            }
                            seriesView.seriesInformation(series);
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (NumberFormatException e) {

            }

            if (!yearFound) {
                seriesView.filterMsg(4);
            }

            displayAdditionalListOptions();
        } else if (filterOption.equals("3")
                || filterOption.equalsIgnoreCase("menu")
                || filterOption.equalsIgnoreCase("menu inicial")) {
            return true;
        }
        else {
            seriesView.invalidValueMsg();
            return filterList();
        }
        return true;
    }

    public boolean sortList() {
        ArrayList<SeriesModel> listOfReviewedSeries = new ArrayList<SeriesModel>();
        ArrayList<SeriesModel> listOfUnreviewedSeries = new ArrayList<SeriesModel>();

        for (SeriesModel series : listOfSeries) {
            if (series.getSeriesReview() != 0) {
                listOfReviewedSeries.add(series);
            } else {
                listOfUnreviewedSeries.add(series);
            }
        }

        ArrayList<SeriesModel> highlyEvaluatedSeries = new ArrayList<SeriesModel>(listOfReviewedSeries);
        ArrayList<SeriesModel> poorlyEvaluatedSeries = new ArrayList<SeriesModel>(listOfReviewedSeries);

        if (listOfReviewedSeries.isEmpty()) {
            seriesView.orderingMsg(1);
            displayAdditionalListOptions();
        } else {
            String sortOption;
            do {
                seriesView.orderingMsg(2);
                seriesView.chooseAnOptionMsg();
                sortOption = input.nextLine().trim();

                if (sortOption.isEmpty()) {
                    seriesView.emptyValueMsg();
                }
            } while (sortOption.isEmpty());

            if (sortOption.equals("1")
                    || sortOption.equalsIgnoreCase("bem")
                    || sortOption.equalsIgnoreCase("bem avaliado")) {
                highlyEvaluatedSeries.sort(Comparator.comparing(seriesModel -> seriesModel.getSeriesReview(), Comparator.reverseOrder()));

                seriesView.headerForSeries();
                for (SeriesModel series : highlyEvaluatedSeries) {
                    seriesView.seriesInformation(series);
                }
                for (SeriesModel series : listOfUnreviewedSeries) {
                    seriesView.seriesInformation(series);
                }

                displayAdditionalListOptions();

            } else if (sortOption.equals("2")
                    || sortOption.equalsIgnoreCase("mal")
                    || sortOption.equalsIgnoreCase("mal avaliado")) {
                poorlyEvaluatedSeries.sort(Comparator.comparing(seriesModel -> seriesModel.getSeriesReview()));

                seriesView.headerForSeries();
                for (SeriesModel series : poorlyEvaluatedSeries) {
                    seriesView.seriesInformation(series);
                }
                for (SeriesModel series : listOfUnreviewedSeries) {
                    seriesView.seriesInformation(series);
                }

                displayAdditionalListOptions();

            } else if (sortOption.equals("3")
                    || sortOption.equalsIgnoreCase("menu")
                    || sortOption.equalsIgnoreCase("menu inicial")) {
                return true;
            } else {
                seriesView.invalidValueMsg();
                return sortList();
            }
        }
        return true;
    }

    public void openSeries(int a, int b) {
        if (b == 1) {
            additionalOptionsForOpeningSeries(1);
        } else if (b == 2) {
            additionalOptionsForOpeningSeries(2);
        }

        if (a == 1) {
            displayAdditionalRegisterOptions();
        } else if (a == 2) {
            displayAdditionalSearchOptions();
        } else {
            displayAdditionalListOptions();
        }
    }

    public void additionalOptionsForOpeningSeries(int a) {
        int seriesOption = -1;
        boolean validInput = false;

        while (!validInput) {
            seriesView.evaluationMsg(1);
            String seriesOptionString = input.nextLine();

            if (seriesOptionString.isEmpty()) {
                seriesView.emptyValueMsg();
                continue;
            }

            try {
                seriesOption = Integer.parseInt(seriesOptionString);
                validInput = true;
            } catch (NumberFormatException e) {
                seriesView.evaluationMsg(3);
            }
        }

        boolean seriesFound = false;
        switch (a) {
            case 1 : {
                for (SeriesModel series : listOfSeries) {
                    if (series.getSeriesIndex() == seriesOption) {
                        checkSeriesReview(series);
                        seriesFound = true;
                    }
                }
                break;
            }
            case 2 : {
                for (SeriesModel series : listOfSeries) {
                    if (series.getSeriesIndex() == seriesOption) {

                        seriesView.additionalSeriesInformation(series, series.getListOfSeasons().size());

                        for (SeasonModel season : series.getListOfSeasons()) {
                            seasonController.openSeasonInformation(season);
                        }

                        seriesFound = true;
                    }
                }
                break;
            }
            default:
                break;
        }

        if (!seriesFound) {
            seriesView.evaluationMsg(2);
        }
    }

    public boolean checkSeriesReview(SeriesModel series) {
        String seasonNumberString;
        int seasonNumber;

        do {
            seriesView.evaluationMsg(4);
            seasonNumberString = input.nextLine().trim();

            if (seasonNumberString.isEmpty()) {
                seriesView.emptyValueMsg();
            }
        } while (seasonNumberString.isEmpty());

        try {
            seasonNumber = Integer.parseInt(seasonNumberString);
        } catch (Exception e) {
            seriesView.evaluationMsg(3);
            return checkSeriesReview(series);
        }

        boolean seasonFound = false;

        for (SeasonModel season : series.getListOfSeasons()) {
            if (season.getSeasonIndex() == seasonNumber) {
                seasonController.checkSeasonReview(season);
                seasonFound = true;
            }
        }

        if (!seasonFound) {
            seriesView.evaluationMsg(5);
        } else {
            updateAverage(series);
        }

        return true;
    }

    public void updateAverage(SeriesModel series) {
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
        }
    }

    public String validateNewString(int a) {
        String value;

        do {
            seriesView.registrationMsg(a, 0);
            value = input.nextLine().trim();
            if (value.isEmpty()) {
                seriesView.emptyValueMsg();
            }
        } while (value.isEmpty());

        return value;
    }

    public int validateNewYear(int a, int start) {
        int value = 0;
        String stringValue;
        int currentYear = calendar.get(Calendar.YEAR);

        do {
            seriesView.registrationMsg(a, 0);
            stringValue = input.nextLine().trim();
            if (stringValue.isEmpty()) {
                seriesView.emptyValueMsg();
            }
        } while (stringValue.isEmpty());

        try {
            value = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            seriesView.invalidYearMsg(1, 0, 0);
            return validateNewYear(a, start);
        }

        if (value < start || value > currentYear) {
            seriesView.invalidYearMsg(2, start, currentYear);
            return validateNewYear(a, start);
        }

        return value;
    }

    public ArrayList<SeasonModel> validateNewList(int start, int end) {
        ArrayList<SeasonModel> value = new ArrayList<SeasonModel>();
        String numberOfSeasonsString;
        int numberOfSeasons;
        
        do {
            seriesView.registrationMsg(6, 0);
            numberOfSeasonsString = input.nextLine().trim();
            if (numberOfSeasonsString.isEmpty()) {
                seriesView.emptyValueMsg();
            }
        } while (numberOfSeasonsString.isEmpty());

        try {
            numberOfSeasons = Integer.parseInt(numberOfSeasonsString);
        } catch (NumberFormatException e) {
            seriesView.invalidInt();
            return validateNewList(start, end);
        }

        if (numberOfSeasons > 0) {
            for (int i = 1; i <= numberOfSeasons; i++) {
                SeasonModel season = seasonController.registerSeason(start, end, i);
                value.add(season);
            }
        } else {
            seriesView.registrationMsg(8,0);
            return validateNewList(start, end);
        }

        return value;
    }
}