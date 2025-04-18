package antigo.series;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SeriesManager {
    private static ArrayList<Series>  listOfSeries= new ArrayList<Series>();

    Scanner input = new Scanner(System.in);

    public void registerSeries() {
        String temporaryTitle;
        int temporaryYearOfRelease;
        int temporaryYearOfConclusion;
        String temporaryOriginalTitle;
        String temporaryWhereToWatch;
        ArrayList<Season> temporaryListOfSeasons = new ArrayList<Season>();

        System.out.print("\nTítulo: ");
        temporaryTitle = input.nextLine().trim();

        System.out.print("Ano de lançamento: ");
        temporaryYearOfRelease = input.nextInt();
        input.nextLine();

        System.out.print("Ano de encerramento: ");
        temporaryYearOfConclusion = input.nextInt();
        input.nextLine();

        System.out.print("Título original: ");
        temporaryOriginalTitle = input.nextLine().trim();

        System.out.print("Onde assistir: ");
        temporaryWhereToWatch = input.nextLine().trim();

        System.out.print("\nQuantidade de temporadas: ");
        int numberOfSeasons = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= numberOfSeasons; i++) {
            System.out.printf("\n%d° temporada:", i);
            Season season = registerSeason(i);
            temporaryListOfSeasons.add(season);
        }

        Series series = new Series(temporaryTitle, temporaryYearOfRelease, temporaryYearOfConclusion, temporaryOriginalTitle, temporaryWhereToWatch, temporaryListOfSeasons);

        listOfSeries.add(series);

        System.out.println("\n+------------------+");
        System.out.println("| Série cadastrada |");
        System.out.println("+------------------+");

        displayAdditionalRegisterOptions();
    }

    public Season registerSeason(int temporaryIndex) {
        String temporaryGenre;
        ArrayList<String> temporaryCast = new ArrayList<String>();
        int temporaryYearSeason;

        System.out.print("\nGênero: ");
        temporaryGenre = input.nextLine().trim();

        System.out.print("Ano: ");
        temporaryYearSeason = input.nextInt();
        input.nextLine();

        System.out.print("\nQuantidade de pessoas no elenco: ");
        int castPeople = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= castPeople; i++) {
            System.out.printf("%d° pessoa: ", i);
            String person = input.nextLine().trim();
            temporaryCast.add(person);
        }

        Season season = new Season(temporaryGenre, temporaryCast, temporaryYearSeason, temporaryIndex);
        return season;
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
            registerSeries();
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

    public void searchSeries() {

        if (listOfSeries.isEmpty()) {
            System.out.println("\n+--------------------------+");
            System.out.println("| Nenhuma série cadastrada |");
            System.out.println("+--------------------------+");
        }
        else {
            System.out.println("\n+-------------------------+");
            System.out.printf("| %-23s |\n", "Buscar por");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 1 ] - Título");
            System.out.printf("| %-23s |\n", "[ 2 ] - Título original");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 3 ] - Avaliar");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 4 ] - Cancelar");
            System.out.println("+-------------------------+");

            System.out.print("\nEscolha uma opção: ");
            String searchOption = input.nextLine().trim();

            if (searchOption.equals("1")
                    || searchOption.equalsIgnoreCase("titulo")
                    || searchOption.equalsIgnoreCase("título")
                    || searchOption.equals("2")
                    || searchOption.equalsIgnoreCase("título original")
                    || searchOption.equalsIgnoreCase("titulo original")
                    || searchOption.equalsIgnoreCase("original")) {

                System.out.print("\nEscreva a informação do filme: ");
                String seriesSearchInformation = input.nextLine().trim();

                boolean seriesFound = false;

                for (Series s : listOfSeries) {
                    if ((searchOption.equals("1")
                            || searchOption.equalsIgnoreCase("titulo")
                            || searchOption.equalsIgnoreCase("título"))
                            && s.getTitle().equalsIgnoreCase(seriesSearchInformation)) {
                        s.displayInformation();
                        s.displayReview();
                        seriesFound = true;
                    }
                    else if ((searchOption.equals("2")
                            || searchOption.equalsIgnoreCase("título original")
                            || searchOption.equalsIgnoreCase("titulo original")
                            || searchOption.equalsIgnoreCase("original"))
                            && s.getOriginalTitle().equalsIgnoreCase(seriesSearchInformation)) {
                        s.displayInformation();
                        s.displayReview();
                        seriesFound = true;
                    }
                }

                if (!seriesFound) {
                    System.out.println("\n+--------------------------+");
                    System.out.println("| Nenhuma série encontrada |");
                    System.out.println("+--------------------------+");

                    while (true) {
                        System.out.println("\n[ 1 ] - Buscar novamente");
                        System.out.println("[ 2 ] - Voltar");

                        System.out.print("\nEscolha uma opção: ");
                        String searchAgainOption = input.nextLine().trim();

                        if (searchAgainOption.equals("1")
                                || searchAgainOption.equalsIgnoreCase("buscar")
                                || searchAgainOption.equalsIgnoreCase("buscar novamente")) {
                            searchSeries();
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
                } else {
                    displayAdditionalSearchOptions();
                }
            }
            else if (searchOption.equals("3")
                    || searchOption.equalsIgnoreCase("avaliar")) {
                openSeriesReviewer(1);
            }
            else if (searchOption.equals("4")
                    || searchOption.equalsIgnoreCase("cancelar")) {
                return;
            }
            else {
                System.out.println("\n+----------+");
                System.out.println("| Inválido |");
                System.out.println("+----------+");
                searchSeries();
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
            openSeriesReviewer(1);
        }
        else if (searchOrReviewOption.equals("2")
                || searchOrReviewOption.equalsIgnoreCase("buscar")
                || searchOrReviewOption.equalsIgnoreCase("buscar novamente")) {
            searchSeries();
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

    public void listSeries() {

        if (listOfSeries.isEmpty()) {
            System.out.println("\n+--------------------------+");
            System.out.println("| Nenhuma série cadastrada |");
            System.out.println("+--------------------------+");
        } else {
            for (Series s : listOfSeries) {
                s.displayInformation();
                s.displayReview();
            }
            displayAdditionalListOptions(listOfSeries);
        }
    }

    public void displayAdditionalListOptions(ArrayList<Series> currentList) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")
                || listTypeOption.equalsIgnoreCase("avaliar")) {
            openSeriesReviewer(2);
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
        ArrayList<Series> listOfReviewedSeries = new ArrayList<Series>();

        for (Series s : listOfSeries) {
            if (s.getSeriesReview() != -1) {
                listOfReviewedSeries.add(s);
            }
        }

        ArrayList<Series> highlyEvaluatedSeries = new ArrayList<Series>(listOfReviewedSeries);
        ArrayList<Series> poorlyEvaluatedSeries = new ArrayList<Series>(listOfReviewedSeries);

        if (listOfReviewedSeries.isEmpty()) {
            System.out.println("\n+------------------------+");
            System.out.println("| Nenhuma série avaliada |");
            System.out.println("+------------------------+");
            displayAdditionalListOptions(listOfSeries);
        } else {
            System.out.println("\n+-------------------------+");
            System.out.printf("| %-23s |\n", "Ordenar por");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 1 ] - Bem avaliado");
            System.out.printf("| %-23s |\n", "[ 2 ] - Mal avaliado");
            System.out.printf("| %-23s |\n", "[ 3 ] - Cancelar");
            System.out.println("+-------------------------+");

            System.out.print("\nEscolha uma opção: ");
            String chosenOrder = input.nextLine().trim();

            if (!listOfReviewedSeries.isEmpty()) {
                if (chosenOrder.equals("1")
                        || chosenOrder.equalsIgnoreCase("bem")
                        || chosenOrder.equalsIgnoreCase("bem avaliado")) {
                    highlyEvaluatedSeries.sort(Comparator.comparing(series -> series.getSeriesReview(), Comparator.reverseOrder()));

                    for (Series s : highlyEvaluatedSeries) {
                        s.displayInformation();
                        s.displayReview();
                    }

                    displayAdditionalListOptions(highlyEvaluatedSeries);
                } else if (chosenOrder.equals("2")
                        || chosenOrder.equalsIgnoreCase("mal")
                        || chosenOrder.equalsIgnoreCase("mal avaliado")) {
                    poorlyEvaluatedSeries.sort(Comparator.comparing(series -> series.getSeriesReview()));

                    for (Series s : poorlyEvaluatedSeries) {
                        s.displayInformation();
                        s.displayReview();
                    }

                    displayAdditionalListOptions(poorlyEvaluatedSeries);
                }
                else if (chosenOrder.equals("3")
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

    public void filterList(ArrayList<Series> currentList) {
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

            boolean seriesByGenreFound = false;
            for ( Series s : currentList) {
                boolean found = s.getListOfSeasons().stream().anyMatch(
                        t -> t.getGenre().equalsIgnoreCase(chosenGenre));

                if (found) {
                    s.displayInformation();
                    s.displayReview();
                    seriesByGenreFound = true;
                }
            }

            if (!seriesByGenreFound) {
                System.out.println("\n+--------------------------+");
                System.out.println("| Nenhuma série encontrada |");
                System.out.println("+--------------------------+");
            }

            displayAdditionalListOptions(currentList);
        }
        else if (chosenFilter.equals("2")
                || chosenFilter.equalsIgnoreCase("ano")
                || chosenFilter.equalsIgnoreCase("ano de lançamento")
                || chosenFilter.equalsIgnoreCase("ano de lancamento")) {

            System.out.print("\nDigite o ano de lançamento: ");
            String chosenYearOfRelease = input.nextLine().trim();

            boolean seriesByYearFound = false;
            for ( Series s : currentList) {
                try {
                    if (s.getYearOfRelease() == Integer.parseInt(chosenYearOfRelease)) {
                        s.displayInformation();
                        s.displayReview();
                        seriesByYearFound = true;
                    }
                } catch (NumberFormatException x) {
                }
            }

            if (!seriesByYearFound) {
                System.out.println("\n+--------------------------+");
                System.out.println("| Nenhuma série encontrada |");
                System.out.println("+--------------------------+");
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

    public void openSeriesReviewer(int n) {
        System.out.print("\nEscreva o n° da série: ");
        int chosenSeries = input.nextInt();
        input.nextLine();

        boolean chosenSeriesFound = false;
        boolean chosenSeasonFound = false;
        for (Series s : listOfSeries) {
            if (s.getSeriesIndex() == chosenSeries) {
                chosenSeriesFound = true;
                System.out.print("Escreva o n° da temporada: ");
                int chosenSeason = input.nextInt();
                input.nextLine();
                for (Season t : s.getListOfSeasons()) {
                    if (t.getSeasonIndex() == chosenSeason) {
                        t.ckeckSeasonReview();
                        chosenSeasonFound = true;
                    }
                }
            }
        }

        if (!chosenSeriesFound) {
            System.out.println("\n+----------------------+");
            System.out.println("| Série não encontrada |");
            System.out.println("+----------------------+");
        }
        else {
            if (!chosenSeasonFound) {
                System.out.println("\n+--------------------------+");
                System.out.println("| Temporada não encontrada |");
                System.out.println("+--------------------------+");
            }
        }

        if (n == 1) {
            displayAdditionalSearchOptions();
        }
        else {
            displayAdditionalListOptions(listOfSeries);
        }
    }

    public static ArrayList<Series> getListOfSeries() {
        return listOfSeries;
    }

    public static void setListOfSeries(ArrayList<Series> listOfSeries) {
        SeriesManager.listOfSeries = listOfSeries;
    }
}
