package culturaldiary.test.test;

import culturaldiary.book.BookController;
import culturaldiary.movie.MovieController;
import culturaldiary.series.series.SeriesController;

import java.util.Scanner;

public class Test {
    static BookController bookController = new BookController();
    static MovieController movieController = new MovieController();
    static SeriesController seriesController = new SeriesController();

    static Scanner input = new Scanner(System.in);

    // MÍDIAS =========================================
    public void controlMedia(int chosenMethod) {
        int chosenMedia;

        switch (chosenMethod) {
            case 1 :
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Cadastro de Mídia");
                System.out.println("+-------------------------+");

                chosenMedia = displayMethodMenu();

                switch (chosenMedia) {
                    case 0 :
                        controlMedia(1);
                        break;
                    case 1 :
                        registerBook();
                        break;
                    case 2 :
                        registerMovie();
                        break;
                    case 3 :
                        registerSeries();
                        break;
                    default :
                        break;
                }
                break;
            case 2 :
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Busca de Mídia");
                System.out.println("+-------------------------+");

                chosenMedia = displayMethodMenu();

                switch (chosenMedia) {
                    case 0 :
                        controlMedia(2);
                        break;
                    case 1 :
                        searchBook();
                        break;
                    case 2 :
                        searchMovie();
                        break;
                    case 3 :
                        searchSeries();
                        break;
                    default :
                        break;
                }
                break;
            case 3 :
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Lista de Mídia");
                System.out.println("+-------------------------+");

                chosenMedia = displayMethodMenu();

                switch (chosenMedia) {
                    case 0 :
                        controlMedia(3);
                        break;
                    case 1 :
                        listBook();
                        break;
                    case 2 :
                        listMovie();
                        break;
                    case 3 :
                        listSeries();
                        break;
                    default :
                        break;
                }
                break;
            default :
                break;
        }
    } // Faz os controles das funções das mídas
    public int displayMethodMenu() {
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Livro");
        System.out.printf("| %-23s |\n", "[ 2 ] - Filme");
        System.out.printf("| %-23s |\n", "[ 3 ] - Série");
        System.out.printf("| %-23s |\n", "[ 4 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String mediaOption = input.nextLine().trim();

        if (mediaOption.equals("1")) {
            return 1;
        } else if (mediaOption.equals("2")) {
            return 2;
        } else if (mediaOption.equals("3")) {
            return 3;
        } else if (mediaOption.equals("4")) {
            return 4;
        } else {
            System.out.println("\nInválido.");
            return 0;
        }
    } // Exibe o menu secundário
    // ================================================

    // LIVROS =========================================
    public void registerBook() {
        System.out.print("\nTítulo: ");
        String title1 = input.nextLine().trim();

        System.out.print("Autor: ");
        String author1 = input.nextLine().trim();

        System.out.print("Editora: ");
        String publisher1 = input.nextLine().trim();

        System.out.print("ISBN: ");
        String isbn1 = input.nextLine().trim();

        System.out.print("Ano de publicação: ");
        String yearOfPublication1 = input.nextLine();

        System.out.print("Gênero: ");
        String genre1 = input.nextLine().trim();

        System.out.print("Tem exemplar ? ");
        String hasCopy1 = input.nextLine().trim();

        System.out.print("Já leu ? ");
        String read1 = input.nextLine().trim();

        bookController.registerBook(title1, author1, publisher1, isbn1, yearOfPublication1, genre1, hasCopy1, read1);
    } // Registra livros
    public int searchBook() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Buscar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Título");
        System.out.printf("| %-23s |\n", "[ 2 ] - Autor");
        System.out.printf("| %-23s |\n", "[ 3 ] - Gênero");
        System.out.printf("| %-23s |\n", "[ 4 ] - Ano");
        System.out.printf("| %-23s |\n", "[ 5 ] - ISBN");
        System.out.printf("| %-23s |\n", "[ 6 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String menuOption = input.nextLine().trim();

        String information;

        switch (menuOption) {
            case "1" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByTitle(information);
                additionalBookSearchOptions();
                break;
            case "2" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByAuthor(information);
                additionalBookSearchOptions();
                break;
            case "3" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByGenre(information);
                additionalBookSearchOptions();
                break;
            case "4" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByYearOfPublication(information);
                additionalBookSearchOptions();
                break;
            case "5" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByIsbn(information);
                additionalBookSearchOptions();
                break;
            case "6" :
                return 0;
            default :
                System.out.println("\nInválido.");
                return searchBook();
        }

        return 0;
    } // Busca livros
    public int additionalBookSearchOptions() {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Abrir");
        System.out.println("[ 3 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String searchOrReviewOption = input.nextLine().trim();

        if (searchOrReviewOption.equals("1")) {
            evaluateBook(0);
        }
        else if (searchOrReviewOption.equals("2")) {
            openBook();
        } else if (searchOrReviewOption.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return additionalBookSearchOptions();
        }

        return 0;
    } // Exibe menu adicional de busca
    public int evaluateBook(int a) {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return evaluateBook(a);
        }

        System.out.print("\nDê uma nota entre 1 e 5: ");
        String score = input.nextLine();

        System.out.print("Data de leitura: ");
        String consumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        String comment = input.nextLine();

        bookController.evaluateBook(index, score, consumptionDate, comment);

        if (a == 0) {
            return additionalBookSearchOptions();
        } else if (a == 1) {
            return additionalBookListOptions();
        }

        return 0;
    } // Avalia livros
    public void listBook() {
        bookController.listBooks();
        additionalBookListOptions();
    } // Lista livros
    public int additionalBookListOptions() {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")) {
            evaluateBook(1);
        } else if (listTypeOption.equals("2")) {
            sortBooklist();
        } else if (listTypeOption.equals("3")) {
            filterBookList();
        }
        else if (listTypeOption.equals("4")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return additionalBookListOptions();
        }

        return 0;
    } // Exibe menu adicional de lista
    public int sortBooklist() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Ordenar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Bem avaliado");
        System.out.printf("| %-23s |\n", "[ 2 ] - Mal avaliado");
        System.out.printf("| %-23s |\n", "[ 3 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String chosenOrder = input.nextLine().trim();

        if (chosenOrder.equals("1")) {
            bookController.sortListByTopRated();
        } else if (chosenOrder.equals("2")) {
            bookController.sortListByLowRated();
        } else if (chosenOrder.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return sortBooklist();
        }

        return additionalBookListOptions();
    } // Ordena livros
    public int filterBookList() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Filtrar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Gênero");
        System.out.printf("| %-23s |\n", "[ 2 ] - Ano");
        System.out.printf("| %-23s |\n", "[ 3 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String chosenFilter = input.nextLine().trim();

        if (chosenFilter.equals("1")) {
            System.out.print("\nDigite o gênero: ");
            String chosenGenre = input.nextLine();

            bookController.filterListOfBooksByGenre(chosenGenre);
        } else if (chosenFilter.equals("2")) {
            System.out.print("\nDigite o ano de publicação: ");
            String chosenYearOfPublication = input.nextLine();

            bookController.filterListOfBooksByYearOfPublication(chosenYearOfPublication);
        } else if (chosenFilter.equals("3")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return filterBookList();
        }

        return additionalBookListOptions();
    } // Filtra livros
    public int openBook() {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return openBook();
        }

        bookController.openBook(index);
        return additionalBookSearchOptions();
    } // Abre livros
    // =================================================

    // FILMES ==========================================
    public void registerMovie() {
        System.out.print("\nTítulo: ");
        String title = input.nextLine().trim();

        System.out.print("Gênero: ");
        String genre = input.nextLine().trim();

        System.out.print("Ano de lançamento: ");
        String yearOfRelease = input.nextLine();

        System.out.print("Tempo de duração: ");
        String durationTime = input.nextLine().trim();

        System.out.print("Direção: ");
        String direction = input.nextLine().trim();

        System.out.print("Roteiro: ");
        String screenplay = input.nextLine().trim();

        System.out.print("Eleco (separador por vírgulas): ");
        String cast = input.nextLine();

        System.out.print("Título original: ");
        String originalTitle = input.nextLine().trim();

        System.out.print("Onde assitir: ");
        String whereToWatch = input.nextLine().trim();

        System.out.print("Já assistiu ? ");
        String watched = input.nextLine().trim();

        movieController.registerMovie(title, genre, yearOfRelease, durationTime, screenplay, direction, cast, originalTitle, whereToWatch, watched);
    } // Registra filmes
    public int searchMovie() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Buscar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Título");
        System.out.printf("| %-23s |\n", "[ 2 ] - Diretor");
        System.out.printf("| %-23s |\n", "[ 3 ] - Ator");
        System.out.printf("| %-23s |\n", "[ 4 ] - Gênero");
        System.out.printf("| %-23s |\n", "[ 5 ] - Ano");
        System.out.printf("| %-23s |\n", "[ 6 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String menuOption = input.nextLine().trim();

        String information;

        switch (menuOption) {
            case "1" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByTitle(information);
                additionalMovieSearchOptions();
                break;
            case "2" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByDirection(information);
                additionalMovieSearchOptions();
                break;
            case "3" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByActorInTheCast(information);
                additionalMovieSearchOptions();
                break;
            case "4" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByGenre(information);
                additionalMovieSearchOptions();
                break;
            case "5" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByYearOfRelease(information);
                        additionalMovieSearchOptions();
                break;
            case "6" :
                return 0;
            default :
                System.out.println("\nInválido.");
                return searchMovie();
        }

        return 0;
    } // Busca filmes
    public int additionalMovieSearchOptions() {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Abrir");
        System.out.println("[ 3 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String searchOrReviewOption = input.nextLine().trim();

        if (searchOrReviewOption.equals("1")) {
            evaluateMovie(0);
        }
        else if (searchOrReviewOption.equals("2")) {
            openMovie();
        } else if (searchOrReviewOption.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return additionalMovieSearchOptions();
        }

        return 0;
    } // Exibe menu adicional de busca
    public int evaluateMovie(int a) {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return evaluateMovie(a);
        }

        System.out.print("\nDê uma nota entre 1 e 5: ");
        String score = input.nextLine();

        System.out.print("Data de leitura: ");
        String consumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        String comment = input.nextLine();

        movieController.evaluateMovie(index, score, consumptionDate, comment);

        if (a == 0) {
            return additionalMovieSearchOptions();
        } else if (a == 1) {
            return additionalMovieListOptions();
        }

        return 0;
    } // Avalia filmes
    public void listMovie() {
        movieController.listMovies();
        additionalMovieListOptions();
    } // Lista filmes
    public int additionalMovieListOptions() {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")) {
            evaluateMovie(1);
        } else if (listTypeOption.equals("2")) {
            sortMovielist();
        } else if (listTypeOption.equals("3")) {
            filterMovieList();
        }
        else if (listTypeOption.equals("4")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return additionalMovieListOptions();
        }

        return 0;
    } // Exibe menu adicional de lista
    public int sortMovielist() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Ordenar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Bem avaliado");
        System.out.printf("| %-23s |\n", "[ 2 ] - Mal avaliado");
        System.out.printf("| %-23s |\n", "[ 3 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String chosenOrder = input.nextLine().trim();

        if (chosenOrder.equals("1")) {
            movieController.sortListByTopRated();
        } else if (chosenOrder.equals("2")) {
            movieController.sortListByLowRated();
        } else if (chosenOrder.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return sortMovielist();
        }

        return additionalMovieListOptions();
    } // Ordena filmes
    public int filterMovieList() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Filtrar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Gênero");
        System.out.printf("| %-23s |\n", "[ 2 ] - Ano");
        System.out.printf("| %-23s |\n", "[ 3 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String chosenFilter = input.nextLine().trim();

        if (chosenFilter.equals("1")) {
            System.out.print("\nDigite o gênero: ");
            String chosenGenre = input.nextLine();

            movieController.filterListOfMoviesByGenre(chosenGenre);
        } else if (chosenFilter.equals("2")) {
            System.out.print("\nDigite o ano de lançamento: ");
            String chosenYearOfRelease = input.nextLine();

            movieController.filterListOfMoviesByYearOfRelease(chosenYearOfRelease);
        } else if (chosenFilter.equals("3")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return filterMovieList();
        }

        return additionalMovieListOptions();
    } // Filtra filmes
    public int openMovie() {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return openMovie();
        }

        movieController.openMovie(index);
        return additionalMovieSearchOptions();
    } // Abre filmes
    // =================================================

    // SÉRIES ==========================================
    public void registerSeries() {
        System.out.print("\nTítulo: ");
        String title = input.nextLine().trim();

        System.out.print("Ano de lançamento: ");
        String yearOfRelease = input.nextLine();

        System.out.print("Ano de conclusão: ");
        String yearOfConclusion = input.nextLine().trim();

        System.out.print("Título original: ");
        String originalTitle = input.nextLine().trim();

        System.out.print("Onde assitir: ");
        String whereToWatch = input.nextLine().trim();

        System.out.print("\nQuantidade de temporadas: ");
        String numberOfSeasonsString = input.nextLine().trim();

        int numberOfSeasons;
        try {
            numberOfSeasons = Integer.parseInt(numberOfSeasonsString);
        } catch (Exception e) {
            System.out.println("\nInválido");
            return;
        }

        String[][] listOfSeasons = new String[numberOfSeasons][4];

        for (int i = 0; i < numberOfSeasons; i++) {
            System.out.println("\nTemporada " + (i + 1) + ":");

            System.out.print("\nGênero: ");
            String genre = input.nextLine().trim();

            System.out.print("Elenco (separado por vírgulas): ");
            String cast = input.nextLine();

            System.out.print("Ano da temporada: ");
            String year = input.nextLine();

            System.out.print("Já assistiu ? ");
            String watched = input.nextLine().trim();

            listOfSeasons[i][0] = genre;
            listOfSeasons[i][1] = cast;
            listOfSeasons[i][2] = year;
            listOfSeasons[i][3] = watched;
        }

        seriesController.registerSeries(title, yearOfRelease, yearOfConclusion, originalTitle, whereToWatch, listOfSeasons);
    } // Registra séries
    public int searchSeries() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Buscar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Título");
        System.out.printf("| %-23s |\n", "[ 2 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String menuOption = input.nextLine().trim();

        String information;

        switch (menuOption) {
            case "1" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                seriesController.searchSeriesByTitle(information);
                additionalSeriesSearchOptions();
                break;
            case "2" :
                return 0;
            default :
                System.out.println("\nInválido.");
                return searchSeries();
        }

        return 0;
    } // Busca séries
    public int additionalSeriesSearchOptions() {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Abrir");
        System.out.println("[ 3 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String searchOrReviewOption = input.nextLine().trim();

        if (searchOrReviewOption.equals("1")) {
            evaluateSeries(0);
        }
        else if (searchOrReviewOption.equals("2")) {
            openSeries();
        } else if (searchOrReviewOption.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return additionalSeriesSearchOptions();
        }

        return 0;
    } // Exibe menu adicional de busca
    public int evaluateSeries(int a) {
        int indexSeries;
        System.out.print("\nDigite o índice da série: ");
        String indexSeriesString = input.nextLine();

        int indexSeason;
        System.out.print("Digite o índice da temporada: ");
        String indexSeasonString = input.nextLine();

        try {
            indexSeries = Integer.parseInt(indexSeriesString);
            indexSeason = Integer.parseInt(indexSeasonString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return evaluateSeries(a);
        }

        System.out.print("\nDê uma nota entre 1 e 5: ");
        String score = input.nextLine();

        System.out.print("Data de leitura: ");
        String consumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        String comment = input.nextLine();

        seriesController.evaluateSeason(indexSeries, indexSeason, score, consumptionDate, comment);

        if (a == 0) {
            return additionalSeriesSearchOptions();
        } else if (a == 1) {
            return additionalSeriesListOptions();
        }

        return 0;
    } // Avalia séries
    public void listSeries() {
        seriesController.listSeries();
        additionalSeriesListOptions();
    } // Lista séries
    public int additionalSeriesListOptions() {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")) {
            evaluateSeries(1);
        } else if (listTypeOption.equals("2")) {
            sortSerieslist();
        } else if (listTypeOption.equals("3")) {
            filterSeriesList();
        }
        else if (listTypeOption.equals("4")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return additionalSeriesListOptions();
        }

        return 0;
    } // Exibe menu adicional de lista
    public int sortSerieslist() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Ordenar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Bem avaliado");
        System.out.printf("| %-23s |\n", "[ 2 ] - Mal avaliado");
        System.out.printf("| %-23s |\n", "[ 3 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String chosenOrder = input.nextLine().trim();

        if (chosenOrder.equals("1")) {
            seriesController.sortListByTopRated();
        } else if (chosenOrder.equals("2")) {
            seriesController.sortListByLowRated();
        } else if (chosenOrder.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return sortSerieslist();
        }

        return additionalSeriesListOptions();
    } // Ordena séries
    public int filterSeriesList() {
        System.out.println("\n+-------------------------+");
        System.out.printf("| %-23s |\n", "Filtrar por");
        System.out.println("+-------------------------+");
        System.out.printf("| %-23s |\n", "[ 1 ] - Gênero");
        System.out.printf("| %-23s |\n", "[ 2 ] - Ano");
        System.out.printf("| %-23s |\n", "[ 3 ] - Voltar");
        System.out.println("+-------------------------+");

        System.out.print("\nEscolha uma opção: ");
        String chosenFilter = input.nextLine().trim();

        if (chosenFilter.equals("1")) {
            System.out.print("\nDigite o gênero: ");
            String chosenGenre = input.nextLine();

            seriesController.filterListOfSeriesByGenre(chosenGenre);
        } else if (chosenFilter.equals("2")) {
            System.out.print("\nDigite o ano de lançamento: ");
            String chosenYearOfRelease = input.nextLine();

            seriesController.filterListOfSeriesByYearOfRelease(chosenYearOfRelease);
        } else if (chosenFilter.equals("3")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return filterSeriesList();
        }

        return additionalSeriesListOptions();
    } // Filtra séries
    public int openSeries() {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return openSeries();
        }

        seriesController.openSeries(index);
        return additionalSeriesSearchOptions();
    } // Abre séries
    // =================================================
}
