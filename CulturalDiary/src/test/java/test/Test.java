package test;

import book.BookController;
import movie.MovieController;
import series.series.SeriesController;

import java.util.Scanner;

public class Test {
    static Scanner input = new Scanner(System.in);

    // MÍDIAS =========================================
    public void controlMedia(int chosenMethod, BookController bookController, MovieController movieController, SeriesController seriesController) {
        int chosenMedia;

        switch (chosenMethod) {
            case 1 :
                System.out.println("\n+-------------------------+");
                System.out.printf("| %-23s |\n", "Cadastro de Mídia");
                System.out.println("+-------------------------+");

                chosenMedia = displayMethodMenu();

                switch (chosenMedia) {
                    case 0 :
                        controlMedia(1, bookController, movieController, seriesController);
                        break;
                    case 1 :
                        registerBook(bookController);
                        break;
                    case 2 :
                        registerMovie(movieController);
                        break;
                    case 3 :
                        registerSeries(seriesController);
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
                        controlMedia(2, bookController, movieController, seriesController);
                        break;
                    case 1 :
                        searchBook(bookController);
                        break;
                    case 2 :
                        searchMovie(movieController);
                        break;
                    case 3 :
                        searchSeries(seriesController);
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
                        controlMedia(3, bookController, movieController, seriesController);
                        break;
                    case 1 :
                        listBook(bookController);
                        break;
                    case 2 :
                        listMovie(movieController);
                        break;
                    case 3 :
                        listSeries(seriesController);
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
    public void registerBook(BookController bookController) {
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
    public int searchBook(BookController bookController) {
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
                additionalBookSearchOptions(bookController);
                break;
            case "2" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByAuthor(information);
                additionalBookSearchOptions(bookController);
                break;
            case "3" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByGenre(information);
                additionalBookSearchOptions(bookController);
                break;
            case "4" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByYearOfPublication(information);
                additionalBookSearchOptions(bookController);
                break;
            case "5" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                bookController.searchBookByIsbn(information);
                additionalBookSearchOptions(bookController);
                break;
            case "6" :
                return 0;
            default :
                System.out.println("\nInválido.");
                return searchBook(bookController);
        }

        return 0;
    } // Busca livros
    public int additionalBookSearchOptions(BookController bookController) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Abrir");
        System.out.println("[ 3 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String searchOrReviewOption = input.nextLine().trim();

        if (searchOrReviewOption.equals("1")) {
            evaluateBook(0, bookController);
        }
        else if (searchOrReviewOption.equals("2")) {
            openBook(bookController);
        } else if (searchOrReviewOption.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return additionalBookSearchOptions(bookController);
        }

        return 0;
    } // Exibe menu adicional de busca
    public int evaluateBook(int a, BookController bookController) {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return evaluateBook(a, bookController);
        }

        System.out.print("\nDê uma nota entre 1 e 5: ");
        String score = input.nextLine();

        System.out.print("Data de leitura: ");
        String consumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        String comment = input.nextLine();

        bookController.evaluateBook(index, score, consumptionDate, comment);

        if (a == 0) {
            return additionalBookSearchOptions(bookController);
        } else if (a == 1) {
            return additionalBookListOptions(bookController);
        }

        return 0;
    } // Avalia livros
    public void listBook(BookController bookController) {
        bookController.listBooks();
        additionalBookListOptions(bookController);
    } // Lista livros
    public int additionalBookListOptions(BookController bookController) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")) {
            evaluateBook(1, bookController);
        } else if (listTypeOption.equals("2")) {
            sortBooklist(bookController);
        } else if (listTypeOption.equals("3")) {
            filterBookList(bookController);
        }
        else if (listTypeOption.equals("4")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return additionalBookListOptions(bookController);
        }

        return 0;
    } // Exibe menu adicional de lista
    public int sortBooklist(BookController bookController) {
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
            return sortBooklist(bookController);
        }

        return additionalBookListOptions(bookController);
    } // Ordena livros
    public int filterBookList(BookController bookController) {
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
            return filterBookList(bookController);
        }

        return additionalBookListOptions(bookController);
    } // Filtra livros
    public int openBook(BookController bookController) {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return openBook(bookController);
        }

        bookController.openBook(index);
        return additionalBookSearchOptions(bookController);
    } // Abre livros
    // =================================================

    // FILMES ==========================================
    public void registerMovie(MovieController movieController) {
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
    public int searchMovie(MovieController movieController) {
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
                additionalMovieSearchOptions(movieController);
                break;
            case "2" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByDirection(information);
                additionalMovieSearchOptions(movieController);
                break;
            case "3" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByActorInTheCast(information);
                additionalMovieSearchOptions(movieController);
                break;
            case "4" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByGenre(information);
                additionalMovieSearchOptions(movieController);
                break;
            case "5" :
                System.out.print("\nEscreva a informação: ");
                information = input.nextLine();
                movieController.searchMovieByYearOfRelease(information);
                        additionalMovieSearchOptions(movieController);
                break;
            case "6" :
                return 0;
            default :
                System.out.println("\nInválido.");
                return searchMovie(movieController);
        }

        return 0;
    } // Busca filmes
    public int additionalMovieSearchOptions(MovieController movieController) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Abrir");
        System.out.println("[ 3 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String searchOrReviewOption = input.nextLine().trim();

        if (searchOrReviewOption.equals("1")) {
            evaluateMovie(0, movieController);
        }
        else if (searchOrReviewOption.equals("2")) {
            openMovie(movieController);
        } else if (searchOrReviewOption.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return additionalMovieSearchOptions(movieController);
        }

        return 0;
    } // Exibe menu adicional de busca
    public int evaluateMovie(int a, MovieController movieController) {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return evaluateMovie(a, movieController);
        }

        System.out.print("\nDê uma nota entre 1 e 5: ");
        String score = input.nextLine();

        System.out.print("Data de leitura: ");
        String consumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        String comment = input.nextLine();

        movieController.evaluateMovie(index, score, consumptionDate, comment);

        if (a == 0) {
            return additionalMovieSearchOptions(movieController);
        } else if (a == 1) {
            return additionalMovieListOptions(movieController);
        }

        return 0;
    } // Avalia filmes
    public void listMovie(MovieController movieController) {
        movieController.listMovies();
        additionalMovieListOptions(movieController);
    } // Lista filmes
    public int additionalMovieListOptions(MovieController movieController) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")) {
            evaluateMovie(1, movieController);
        } else if (listTypeOption.equals("2")) {
            sortMovielist(movieController);
        } else if (listTypeOption.equals("3")) {
            filterMovieList(movieController);
        }
        else if (listTypeOption.equals("4")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return additionalMovieListOptions(movieController);
        }

        return 0;
    } // Exibe menu adicional de lista
    public int sortMovielist(MovieController movieController) {
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
            return sortMovielist(movieController);
        }

        return additionalMovieListOptions(movieController);
    } // Ordena filmes
    public int filterMovieList(MovieController movieController) {
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
            return filterMovieList(movieController);
        }

        return additionalMovieListOptions(movieController);
    } // Filtra filmes
    public int openMovie(MovieController movieController) {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return openMovie(movieController);
        }

        movieController.openMovie(index);
        return additionalMovieSearchOptions(movieController);
    } // Abre filmes
    // =================================================

    // SÉRIES ==========================================
    public void registerSeries(SeriesController seriesController) {
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
    public int searchSeries(SeriesController seriesController) {
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
                additionalSeriesSearchOptions(seriesController);
                break;
            case "2" :
                return 0;
            default :
                System.out.println("\nInválido.");
                return searchSeries(seriesController);
        }

        return 0;
    } // Busca séries
    public int additionalSeriesSearchOptions(SeriesController seriesController) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Abrir");
        System.out.println("[ 3 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String searchOrReviewOption = input.nextLine().trim();

        if (searchOrReviewOption.equals("1")) {
            evaluateSeries(0, seriesController);
        }
        else if (searchOrReviewOption.equals("2")) {
            openSeries(seriesController);
        } else if (searchOrReviewOption.equals("3")) {
            return 0;
        }
        else {
            System.out.println("\nInválido.");
            return additionalSeriesSearchOptions(seriesController);
        }

        return 0;
    } // Exibe menu adicional de busca
    public int evaluateSeries(int a, SeriesController seriesController) {
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
            return evaluateSeries(a, seriesController);
        }

        System.out.print("\nDê uma nota entre 1 e 5: ");
        String score = input.nextLine();

        System.out.print("Data de leitura: ");
        String consumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        String comment = input.nextLine();

        seriesController.evaluateSeason(indexSeries, indexSeason, score, consumptionDate, comment);

        if (a == 0) {
            return additionalSeriesSearchOptions(seriesController);
        } else if (a == 1) {
            return additionalSeriesListOptions(seriesController);
        }

        return 0;
    } // Avalia séries
    public void listSeries(SeriesController seriesController) {
        seriesController.listSeries();
        additionalSeriesListOptions(seriesController);
    } // Lista séries
    public int additionalSeriesListOptions(SeriesController seriesController) {
        System.out.println("\n[ 1 ] - Avaliar");
        System.out.println("[ 2 ] - Ordenar");
        System.out.println("[ 3 ] - Filtrar");
        System.out.println("[ 4 ] - Voltar");

        System.out.print("\nEscolha uma opção: ");
        String listTypeOption = input.nextLine().trim();

        if (listTypeOption.equals("1")) {
            evaluateSeries(1, seriesController);
        } else if (listTypeOption.equals("2")) {
            sortSerieslist(seriesController);
        } else if (listTypeOption.equals("3")) {
            filterSeriesList(seriesController);
        }
        else if (listTypeOption.equals("4")) {
            return 0;
        } else {
            System.out.println("\nInválido.");
            return additionalSeriesListOptions(seriesController);
        }

        return 0;
    } // Exibe menu adicional de lista
    public int sortSerieslist(SeriesController seriesController) {
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
            return sortSerieslist(seriesController);
        }

        return additionalSeriesListOptions(seriesController);
    } // Ordena séries
    public int filterSeriesList(SeriesController seriesController) {
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
            return filterSeriesList(seriesController);
        }

        return additionalSeriesListOptions(seriesController);
    } // Filtra séries
    public int openSeries(SeriesController seriesController) {
        int index;
        System.out.print("\nDigite o índice: ");
        String indexString = input.nextLine();

        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            System.out.println("\nInválido.");
            return openSeries(seriesController);
        }

        seriesController.openSeries(index);
        return additionalSeriesSearchOptions(seriesController);
    } // Abre séries
    // =================================================
}