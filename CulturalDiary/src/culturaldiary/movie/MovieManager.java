package culturaldiary.movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MovieManager {
    private static ArrayList<Movie> listOfMovies = new ArrayList<Movie>();

    private Scanner input = new Scanner(System.in);

    public void registerMovie() {
        String temporaryTitle;
        String temporaryGenre;
        int temporaryYearOfRelease;
        String temporaryDurationTime;
        String temporaryDirection;
        String temporaryScreenplay;
        ArrayList<String> temporaryCast = new ArrayList<String>();
        String temporaryOriginalTitle;
        String temporaryWhereToWatch;

        System.out.print("\nTítulo: ");
        temporaryTitle = input.nextLine().trim();

        System.out.print("Gênero: ");
        temporaryGenre = input.nextLine().trim();

        System.out.print("Ano de lançamento: ");
        temporaryYearOfRelease = input.nextInt();
        input.nextLine();

        System.out.print("Tempo de duração: ");
        temporaryDurationTime = input.nextLine().trim();

        System.out.print("Direção: ");
        temporaryDirection = input.nextLine().trim();

        System.out.print("Roteiro: ");
        temporaryScreenplay = input.nextLine().trim();

        System.out.print("\nQuantidade de pessoas no elenco: ");
        int castPeople = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= castPeople; i++) {
            System.out.printf("%d° pessoa: ", i);
            String person = input.nextLine().trim();
            temporaryCast.add(person);
        }

        System.out.print("\nTítulo original: ");
        temporaryOriginalTitle = input.nextLine().trim();

        System.out.print("Onde assitir: ");
        temporaryWhereToWatch = input.nextLine().trim();

        Movie movie = new Movie(temporaryTitle, temporaryGenre, temporaryYearOfRelease, temporaryDurationTime,
                temporaryDirection, temporaryScreenplay, temporaryCast,
                temporaryOriginalTitle, temporaryWhereToWatch);

        listOfMovies.add(movie);

        System.out.println("\n+------------------+");
        System.out.println("| Filme cadastrado |");
        System.out.println("+------------------+");

        displayAdditionalRegisterOptions();
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
            registerMovie();
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

    public void searchMovie() {

        if (listOfMovies.isEmpty()) {
            System.out.println("\n+-------------------------+");
            System.out.println("| Nenhum filme cadastrado |");
            System.out.println("+-------------------------+");
        }
        else {
            System.out.println("\n+-------------------------+");
            System.out.printf("| %-23s |\n", "Buscar por");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 1 ] - Título");
            System.out.printf("| %-23s |\n", "[ 2 ] - Diretor");
            System.out.printf("| %-23s |\n", "[ 3 ] - Ator");
            System.out.printf("| %-23s |\n", "[ 4 ] - Gênero");
            System.out.printf("| %-23s |\n", "[ 5 ] - Ano");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 6 ] - Avaliar");
            System.out.println("+-------------------------+");
            System.out.printf("| %-23s |\n", "[ 7 ] - Cancelar");
            System.out.println("+-------------------------+");

            System.out.print("\nEscolha uma opção: ");
            String searchOption = input.nextLine().trim();

            if (searchOption.equals("1")
                    || searchOption.equalsIgnoreCase("titulo")
                    || searchOption.equalsIgnoreCase("título")
                    || searchOption.equals("2")
                    || searchOption.equalsIgnoreCase("diretor")
                    || searchOption.equals("3")
                    || searchOption.equalsIgnoreCase("ator")
                    || searchOption.equals("4")
                    || searchOption.equalsIgnoreCase("genero")
                    || searchOption.equalsIgnoreCase("gênero")
                    || searchOption.equals("5")
                    || searchOption.equalsIgnoreCase("ano")
                    || searchOption.equalsIgnoreCase("ano de lançamento")
                    || searchOption.equalsIgnoreCase("ano de lancamento")) {

                System.out.print("\nEscreva a informação do filme: ");
                String movieSearchInformation = input.nextLine().trim();

                boolean movieFound = false;

                for (Movie m : listOfMovies) {

                    if ((searchOption.equals("1")
                            || searchOption.equalsIgnoreCase("titulo")
                            || searchOption.equalsIgnoreCase("título"))
                            && m.getTitle().equalsIgnoreCase(movieSearchInformation)) {
                        m.displayInformation();
                        m.displayReview();
                        movieFound = true;
                    }
                    else if ((searchOption.equals("2")
                            || searchOption.equalsIgnoreCase("diretor"))
                            && m.getDirection().equalsIgnoreCase(movieSearchInformation)) {
                        m.displayInformation();
                        m.displayReview();
                        movieFound = true;
                    }
                    else if (searchOption.equals("3")
                            || searchOption.equalsIgnoreCase("ator")) {

                        for (String c : m.getCast()) {
                            if (c.equalsIgnoreCase(movieSearchInformation)) {
                                m.displayInformation();
                                m.displayReview();
                                movieFound = true;
                            }
                        }
                    }
                    else if ((searchOption.equals("4")
                            || searchOption.equalsIgnoreCase("genero")
                            || searchOption.equalsIgnoreCase("gênero"))
                            && m.getGenre().equalsIgnoreCase(movieSearchInformation)) {
                        m.displayInformation();
                        m.displayReview();
                        movieFound = true;
                    }
                    else if (searchOption.equals("5")
                            || searchOption.equalsIgnoreCase("ano")
                            || searchOption.equalsIgnoreCase("ano de lançamento")
                            || searchOption.equalsIgnoreCase("ano de lancamento")) {
                        try {
                            if (m.getYearOfRelease() == Integer.parseInt(movieSearchInformation)) {
                                m.displayInformation();
                                m.displayReview();
                                movieFound = true;
                            }
                        } catch (NumberFormatException x) {
                        }
                    }
                }

                if (!movieFound) {
                    System.out.println("\n+-------------------------+");
                    System.out.println("| Nenhum filme encontrado |");
                    System.out.println("+-------------------------+");

                    while (true) {
                        System.out.println("\n[ 1 ] - Buscar novamente");
                        System.out.println("[ 2 ] - Voltar");

                        System.out.print("\nEscolha uma opção: ");
                        String searchAgainOption = input.nextLine().trim();

                        if (searchAgainOption.equals("1")
                                || searchAgainOption.equalsIgnoreCase("buscar")
                                || searchAgainOption.equalsIgnoreCase("buscar novamente")) {
                            searchMovie();
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
            else if (searchOption.equals("6")
                || searchOption.equalsIgnoreCase("avaliar")) {
                openMovieReviewer(1);
            }
            else if (searchOption.equals("7")
                || searchOption.equalsIgnoreCase("cancelar")) {
                return;
            }
            else {
                System.out.println("\n+----------+");
                System.out.println("| Inválido |");
                System.out.println("+----------+");
                searchMovie();
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
            openMovieReviewer(1);
        }
        else if (searchOrReviewOption.equals("2")
                || searchOrReviewOption.equalsIgnoreCase("buscar")
                || searchOrReviewOption.equalsIgnoreCase("buscar novamente")) {
            searchMovie();
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

    public void listMovie() {

        if (listOfMovies.isEmpty()) {
            System.out.println("\n+-------------------------+");
            System.out.println("| Nenhum filme cadastrado |");
            System.out.println("+-------------------------+");
        } else {
            for (Movie m : listOfMovies) {
                m.displayInformation();
                m.displayReview();
            }
            displayAdditionalListOptions(listOfMovies);
        }
    }

    public void displayAdditionalListOptions(ArrayList<Movie> currentList) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")
                || listTypeOption.equalsIgnoreCase("avaliar")) {
            openMovieReviewer(2);
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
        ArrayList<Movie> listOfReviewedMovies = new ArrayList<Movie>();

        for (Movie m : listOfMovies) {
            if (m.getMovieReview() != null) {
                listOfReviewedMovies.add(m);
            }
        }

        ArrayList<Movie> highlyEvaluatedMovies = new ArrayList<Movie>(listOfReviewedMovies);
        ArrayList<Movie> poorlyEvaluatedMovies = new ArrayList<Movie>(listOfReviewedMovies);

        if (listOfReviewedMovies.isEmpty()) {
            System.out.println("\n+-----------------------+");
            System.out.println("| Nenhum filme avaliado |");
            System.out.println("+-----------------------+");
            displayAdditionalListOptions(listOfMovies);
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

            if (!listOfReviewedMovies.isEmpty()) {
                if (chosenOrder.equals("1")
                        || chosenOrder.equalsIgnoreCase("bem")
                        || chosenOrder.equalsIgnoreCase("bem avaliado")) {
                    highlyEvaluatedMovies.sort(Comparator.comparing(movie -> movie.getMovieReview().getScore(), Comparator.reverseOrder()));

                    for (Movie m : highlyEvaluatedMovies) {
                        m.displayInformation();
                        m.displayReview();
                    }

                    displayAdditionalListOptions(highlyEvaluatedMovies);
                } else if (chosenOrder.equals("2")
                        || chosenOrder.equalsIgnoreCase("mal")
                        || chosenOrder.equalsIgnoreCase("mal avaliado")) {
                    poorlyEvaluatedMovies.sort(Comparator.comparing(movie -> movie.getMovieReview().getScore()));

                    for (Movie m : poorlyEvaluatedMovies) {
                        m.displayInformation();
                        m.displayReview();
                    }

                    displayAdditionalListOptions(poorlyEvaluatedMovies);
                } else if (chosenOrder.equals("3")
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

    public void filterList(ArrayList<Movie> currentList) {
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

            boolean movieByGenreFound = false;
            for ( Movie m : currentList) {
                if (m.getGenre().equalsIgnoreCase(chosenGenre)) {
                    m.displayInformation();
                    m.displayReview();
                    movieByGenreFound = true;
                }
            }

            if (!movieByGenreFound) {
                System.out.println("\n+-------------------------+");
                System.out.println("| Nenhum filme encontrado |");
                System.out.println("+-------------------------+");
            }

            displayAdditionalListOptions(currentList);
        }
        else if (chosenFilter.equals("2")
                || chosenFilter.equalsIgnoreCase("ano")
                || chosenFilter.equalsIgnoreCase("ano de lançamento")
                || chosenFilter.equalsIgnoreCase("ano de lancamento")) {

            System.out.print("\nDigite o ano de lançamento: ");
            String chosenYearOfRelease = input.nextLine().trim();

            boolean movieByYearFound = false;
            for ( Movie m : currentList) {
                try {
                    if (m.getYearOfRelease() == Integer.parseInt(chosenYearOfRelease)) {
                        m.displayInformation();
                        m.displayReview();
                        movieByYearFound = true;
                    }
                } catch (NumberFormatException x) {
                }
            }

            if (!movieByYearFound) {
                System.out.println("\n+-------------------------+");
                System.out.println("| Nenhum filme encontrado |");
                System.out.println("+-------------------------+");
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

    public void openMovieReviewer(int n) {
        System.out.print("\nEscreva o n° do filme: ");
        int chosenMovie = input.nextInt();
        input.nextLine();

        boolean chosenMovieFound = false;
        for (Movie m : listOfMovies) {
            if (m.getMovieIndex() == chosenMovie) {
                m.ckeckMovieReview();
                chosenMovieFound = true;
            }
        }

        if (!chosenMovieFound) {
            System.out.println("\n+----------------------+");
            System.out.println("| Filme não encontrado |");
            System.out.println("+----------------------+");
        }

        if (n == 1) {
            displayAdditionalSearchOptions();
        }
        else {
            displayAdditionalListOptions(listOfMovies);
        }
    }

    public static ArrayList<Movie> getListOfMovies() {
        return listOfMovies;
    }

    public static void setListOfMovies(ArrayList<Movie> listOfMovies) {
        MovieManager.listOfMovies = listOfMovies;
    }
}