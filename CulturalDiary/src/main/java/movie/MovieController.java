package movie;

import book.BookController;
import book.BookModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import review.ReviewModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

/**
 * Controller class for managing movie-related operations.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.1
 */
public class MovieController {
    private static MovieController instance;

    private MovieController() { openFile();}

    public static MovieController getInstance() {
        if (instance == null) {
            instance = new MovieController();
        }
        return instance;
    }

    private ArrayList<MovieModel> listOfMovies = new ArrayList<MovieModel>(); // Recupera a lista de filmes do repositório
    private ArrayList<MovieModel> reserveListOfMovies = new ArrayList<>();
    MovieView movieView = new MovieView(); // Instancia a visualização (interface) dos filmes
    MovieModel movieModel; // Declara um modelo de filme (não instanciado ainda)

    Calendar calendar = Calendar.getInstance(); // Obtém uma instância do calendário com a data/hora atual

    Gson gson = new Gson(); // Instância do Gson para manipulação de JSON
    File repository = new File("src/main/java/movie/repository/"); // Diretório do repositório de filmes
    File file = new File(repository, "movie_file.json"); // Arquivo JSON dentro do repositório de filmes

    /**
     * Registers a movie with the given information.
     *
     * @param title Movie title
     * @param genre Movie genre
     * @param yearOfReleaseString Year of release as a string
     * @param durationTime Duration of the movie
     * @param direction Director's name
     * @param screenplay Screenplay
     * @param castString Cast as a single string
     * @param originalTitle Original movie title
     * @param whereToWatch Platform or location to watch
     * @param watchedString Watched status as a string
     * @return {@code true} if the movie is successfully registered; {@code false} otherwise
     */
    public boolean registerMovie(String title, String genre, String yearOfReleaseString, String durationTime, String direction,
                                 String screenplay, String castString, String originalTitle, String whereToWatch, String watchedString) {

        // Remove espaços em branco no início e fim das strings de entrada
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

        // Valida os campos de entrada
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

        // Se qualquer um dos campos for inválido, pede para tentar novamente e retorna falso
        if (validTitle == false || validGenre == false || validYearOfRelease == false || validDurationTime == false ||
                validDirection == false || validScreeplay == false || validCast == false || validOriginalTitle == false ||
                validWhereToWatch == false || validWatched == false) {
            movieView.tryAgainMessage();  // Exibe uma mensagem pedindo para tentar novamente
            return false;
        }

        try {
            // Converte o ano de lançamento para inteiro
            int yearOfRelease = Integer.parseInt(yearOfReleaseString);

            // Divide o elenco baseado na vírgula e espaços
            String[] castPeople = castString.split(",\\s*");

            // Cria uma lista com os membros do elenco
            ArrayList<String> cast = new ArrayList<String>();
            for (String personal : castPeople) {
                if (!personal.isEmpty()) {
                    cast.add(personal.trim());
                }
            }

            // Se o elenco estiver vazio, exibe uma mensagem de erro
            if (cast.isEmpty()) {
                movieView.emptyCastMessage();  // Exibe uma mensagem de elenco vazio
                return false;
            }

            // Define as respostas positivas e negativas para a questão de "assistido"
            Set<String> positiveResponsesWatched = Set.of(
                    "sim", "s", "assisti", "sim assisti", "assisti sim", "s assisti", "assisti s", "já assisti", "ja assisti", "já", "ja"
            );

            Set<String> negativeResponsesWatched = Set.of(
                    "não", "nao", "n", "não assisti", "nao assisti", "n assisti",
                    "assisti não", "assisti nao", "assisti n"
            );

            // Determina se o filme foi assistido ou não
            boolean watched = false;
            if (positiveResponsesWatched.contains(watchedString.toLowerCase())) { watched = true; }
            else if (negativeResponsesWatched.contains(watchedString.toLowerCase())) { watched = false; }

            // Cria um modelo de filme e o adiciona ao repositório
            movieModel = new MovieModel(title.trim(), genre.trim(), yearOfRelease, durationTime.trim(), direction.trim(), screenplay.trim(), cast, originalTitle.trim(), whereToWatch.trim(), watched, listOfMovies.size() + 1);
            listOfMovies.add(movieModel);
            saveFile();

            // Exibe uma mensagem de sucesso ao registrar o filme
            movieView.registeredMovieMessage(title);

            return true;
        } catch (Exception e) {
            // Se ocorrer um erro durante o processo, exibe uma mensagem de erro
            movieView.invalidMessage();  // Exibe uma mensagem indicando que houve um erro
            return false;
        }
    } // Registra filme

    /**
     * Validates the movie title.
     *
     * @param title The title to validate.
     * @return {@code true} if the title is valid; {@code false} otherwise.
     */
    public boolean validateTitle(String title) {
        return validateNewString(title, "Título");
    }  // Valida o título do filme

    /**
     * Validates the movie genre.
     *
     * @param genre The genre to validate.
     * @return {@code true} if the genre is valid; {@code false} otherwise.
     */
    public boolean validateGenre(String genre) {
        return validateNewString(genre, "Gênero");
    }  // Valida o gênero do filme

    /**
     * Validates the year of release.
     *
     * @param yearOfRelease The year to validate as a string.
     * @return {@code true} if the year is valid; {@code false} otherwise.
     */
    public boolean validateYearOfRelease(String yearOfRelease) {
        return validateNewYear(yearOfRelease);
    }  // Valida o ano de lançamento do filme

    /**
     * Validates the movie duration time.
     *
     * @param durationTime The duration to validate.
     * @return {@code true} if the duration is valid; {@code false} otherwise.
     */
    public boolean validateDurationTime(String durationTime) {
        return validateNewTime(durationTime);
    }  // Valida o tempo de duração do filme

    /**
     * Validates the director's name.
     *
     * @param direction The director's name to validate.
     * @return {@code true} if the name is valid; {@code false} otherwise.
     */
    public boolean validateDirection(String direction) {
        return validateNewString(direction, "Direção");
    }  // Valida a direção do filme

    /**
     * Validates the screenplay information.
     *
     * @param screenplay The screenplay to validate.
     * @return {@code true} if the screenplay is valid; {@code false} otherwise.
     */
    public boolean validateScreenplay(String screenplay) {
        return validateNewString(screenplay, "Roteiro");
    }  // Valida o roteiro do filme

    /**
     * Validates the cast information.
     *
     * @param cast The cast to validate.
     * @return {@code true} if the cast is valid; {@code false} otherwise.
     */
    public boolean validateCast(String cast) {
        return validateNewCast(cast);
    }  // Valida o elenco do filme

    /**
     * Validates the original title of the movie.
     *
     * @param originalTitle The original title to validate.
     * @return {@code true} if the original title is valid; {@code false} otherwise.
     */
    public boolean validateOriginalTitle(String originalTitle) {
        return validateNewString(originalTitle, "Título original");
    }  // Valida o título original do filme

    /**
     * Validates the platform or place to watch the movie.
     *
     * @param whereToWatch The platform or location to validate.
     * @return {@code true} if the information is valid; {@code false} otherwise.
     */
    public boolean validateWhereToWatch(String whereToWatch) {
        return validateNewString(whereToWatch, "Onde assistir");
    }  // Valida onde o filme pode ser assistido

    /**
     * Validates the watched status.
     *
     * @param watched The watched status to validate.
     * @return {@code true} if the status is valid; {@code false} otherwise.
     */
    public boolean validateWatched(String watched) {
        return validateNewWatched(watched);
    }  // Valida se o filme foi assistido

    /**
     * Validates the new time value.
     *
     * @param value The time value to validate.
     * @return {@code true} if the time is valid; {@code false} otherwise.
     */
    public boolean validateNewTime(String value) {
        // Verifica se a entrada é uma string válida
        if (validateNewInputString(value)) {
            // Divide a string de tempo nas partes hora e minuto
            String[] parts = value.split(":");

            // Verifica se a divisão gerou exatamente duas partes (hora e minuto)
            if (parts.length != 2) {
                movieView.invalidTimeMessage(); // Exibe mensagem de erro se o formato estiver errado
                return false;
            }

            String stringHour = parts[0];  // Parte da hora
            String stringMinute = parts[1]; // Parte do minuto

            int hour;
            int minute;

            // Tenta converter as partes em inteiros
            try {
                hour = Integer.parseInt(stringHour);
                minute = Integer.parseInt(stringMinute);
            } catch (Exception e) {
                movieView.invalidTimeMessage(); // Exibe mensagem de erro se a conversão falhar
                return false;
            }

            // Verifica se a hora e o minuto estão dentro dos limites válidos
            if (hour <= 23 && hour >= 0 && minute <= 59 && minute >= 0) {
                return true;
            } else {
                movieView.invalidTimeMessage(); // Exibe mensagem de erro se o tempo for inválido
                return false;
            }
        } else {
            return false;
        }
    }  // Valida o formato de tempo (HH:MN)

    /**
     * Validates the new year value.
     *
     * @param value The year value to validate.
     * @return {@code true} if the year is valid; {@code false} otherwise.
     */
    public boolean validateNewYear(String value) {
        // Verifica se a string de entrada é válida
        if (validateNewString(value, "Ano de lançamento")) {
            int valueInt = 0;
            int currentYear = calendar.get(Calendar.YEAR);  // Obtém o ano atual

            try {
                // Tenta converter o valor para inteiro
                valueInt = Integer.parseInt(value);
            } catch (Exception e) {
                // Exibe mensagem de erro caso a conversão falhe
                movieView.integerMessage();
                return false;
            }

            // Verifica se o ano está dentro do intervalo válido (1700 até o ano atual)
            if (valueInt < 1700 || valueInt > currentYear) {
                movieView.invalidYearMessage(currentYear); // Exibe mensagem de erro se o ano não for válido
                return false;
            }

            return true;
        }

        return false;
    }  // Valida o ano de lançamento do filme

    /**
     * Validates the new cast information.
     *
     * @param value The cast information to validate.
     * @return {@code true} if the cast is valid; {@code false} otherwise.
     */
    public boolean validateNewCast(String value) {
        // Verifica se a string de entrada é válida
        if (validateNewString(value, "Elenco")) {

            // Verifica se o valor contém apenas letras, espaços e vírgulas
            if (value.matches("[\\p{L}, ]*")) {
                return true;
            } else {
                // Exibe mensagem de erro caso o valor contenha caracteres inválidos
                movieView.invalidCastMessage();
                return false;
            }
        } else {
            return false;
        }
    }  // Valida o elenco do filme

    /**
     * Validates the new watched status.
     *
     * @param value The watched status to validate.
     * @return {@code true} if the status is valid; {@code false} otherwise.
     */
    public boolean validateNewWatched(String value) {
        // Verifica se a string de entrada para a visualização é válida
        if (validateNewString(value, "Visualização")) {

            // Conjunto com todas as respostas válidas para a questão de visualização
            Set<String> validAnswers = Set.of(
                    "sim", "s",
                    "não", "nao", "n",
                    "não assisti", "nao assisti", "n assisti", "assisti nao", "assisti não", "assisti n",
                    "sim assisti", "assisti sim", "s assisti", "assisti s", "assisti", "já assisti", "ja assisti", "já", "ja"
            );

            // Verifica se a resposta fornecida está no conjunto de respostas válidas
            if (validAnswers.contains(value.toLowerCase())) {
                return true;
            } else {
                // Exibe mensagem de erro caso a resposta não seja válida
                movieView.invalidWatchedMessage();
            }
        }

        return false;
    }  // Valida a resposta sobre se o filme foi assistido ou não

    /**
     * Validates a new string value based on its name.
     *
     * @param value The string value to validate.
     * @param name The name or type of the value.
     * @return {@code true} if the value is valid; {@code false} otherwise.
     */
    public boolean validateNewString(String value, String name) {
        // Verifica se o valor fornecido está vazio
        if (value.isEmpty()) {
            // Exibe uma mensagem informando que o valor está vazio, passando o nome do campo
            movieView.emptyValueMessage(name);
            return false;
        }
        return true;
    }  // Valida se o valor fornecido não está vazio

    /**
     * Validates a new input string.
     *
     * @param value The input string to validate.
     * @return {@code true} if the input is valid; {@code false} otherwise.
     */
    public boolean validateNewInputString(String value) {
        // Verifica se o valor fornecido está vazio
        if (value.isEmpty()) {
            // Exibe uma mensagem informando que a informação está vazia
            movieView.emptyInformationMessage();
            return false;
        }
        return true;
    }  // Valida se o valor fornecido não está vazio

    /**
     * Searches for a movie by its title.
     *
     * @param value The title to search for.
     * @return {@code true} if the movie is found or not found; {@code false} if an error occurs.
     */
    public boolean searchMovieByTitle(String value) {
        // Remove espaços em branco no início e fim do valor fornecido
        value = value.trim();

        // Verifica se o valor fornecido não está vazio
        if (validateNewInputString(value)) {

            boolean movieFound = false;  // Flag para verificar se algum filme foi encontrado
            // Verifica se a lista de filmes não está vazia
            if (!listOfMovies.isEmpty()) {

                // Itera sobre todos os filmes na lista
                for (MovieModel movie : listOfMovies) {
                    // Verifica se o título do filme contém o valor fornecido, ignorando diferenças de maiúsculas/minúsculas
                    if (movie.getTitle().toLowerCase().contains(value.toLowerCase().trim())) {

                        // Se for o primeiro filme encontrado, exibe o cabeçalho
                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;  // Marca que um filme foi encontrado
                        }
                        // Exibe as informações do filme encontrado
                        movieView.movieInformation(movie);
                    }
                }
            }
            // Se nenhum filme foi encontrado, exibe uma mensagem informando
            if (!movieFound) {
                movieView.noMovieFoundMessage();
            }
            return true;
        }

        return false;
    } // Busca filme por título

    /**
     * Searches for a movie by director's name.
     *
     * @param value The director's name to search for.
     * @return {@code true} if the movie is found or not found; {@code false} if an error occurs.
     */
    public boolean searchMovieByDirection(String value) {
        // Remove espaços em branco no início e fim do valor fornecido
        value = value.trim();

        // Verifica se o valor fornecido não está vazio
        if (validateNewInputString(value)) {

            boolean movieFound = false;  // Flag para verificar se algum filme foi encontrado
            // Verifica se a lista de filmes não está vazia
            if (!listOfMovies.isEmpty()) {

                // Itera sobre todos os filmes na lista
                for (MovieModel movie : listOfMovies) {
                    // Verifica se o nome do diretor do filme contém o valor fornecido, ignorando diferenças de maiúsculas/minúsculas
                    if (movie.getDirection().toLowerCase().contains(value.toLowerCase().trim())) {

                        // Se for o primeiro filme encontrado, exibe o cabeçalho
                        if (!movieFound) {
                            movieView.headerForMovie();
                            movieFound = true;  // Marca que um filme foi encontrado
                        }
                        // Exibe as informações do filme encontrado
                        movieView.movieInformation(movie);
                    }
                }
            }
            // Se nenhum filme foi encontrado, exibe uma mensagem informando
            if (!movieFound) {
                movieView.noMovieFoundMessage();
            }
            return true;
        }

        return false;
    } // Busca filme por nome do diretor

    /**
     * Searches for a movie by actor in the cast.
     *
     * @param value The actor's name to search for.
     * @return {@code true} if the movie is found or not found; {@code false} if an error occurs.
     */
    public boolean searchMovieByActorInTheCast(String value) {
        // Remove espaços em branco no início e fim do valor fornecido
        value = value.trim();

        // Verifica se o valor fornecido não está vazio
        if (validateNewInputString(value)) {

            boolean movieFound = false;  // Flag para verificar se algum filme foi encontrado
            // Verifica se a lista de filmes não está vazia
            if (!listOfMovies.isEmpty()) {
                // Itera sobre todos os filmes na lista
                for (MovieModel movie : listOfMovies) {
                    ArrayList<String> cast = movie.getCast();  // Obtém o elenco do filme
                    boolean foundInThisMovie = false;  // Flag para verificar se o ator foi encontrado neste filme

                    // Itera sobre o elenco do filme
                    for (String actor : cast) {
                        // Verifica se o ator contém o nome fornecido, ignorando diferenças de maiúsculas/minúsculas
                        if (actor.toLowerCase().contains(value.toLowerCase().trim())) {
                            foundInThisMovie = true;  // Marca que o ator foi encontrado
                            break;  // Interrompe o loop, pois não é necessário continuar verificando o restante do elenco
                        }
                    }

                    // Se o ator foi encontrado no elenco, exibe as informações do filme
                    if (foundInThisMovie) {
                        if (!movieFound) {
                            movieView.headerForMovie();  // Exibe o cabeçalho para os filmes encontrados
                            movieFound = true;  // Marca que um filme foi encontrado
                        }
                        movieView.movieInformation(movie);  // Exibe as informações do filme
                    }
                }
            }

            // Se nenhum filme foi encontrado, exibe uma mensagem informando
            if (!movieFound) {
                movieView.noMovieFoundMessage();
            }
            return true;
        }

        return false;
    } // Busca filme por ator no elenco

    /**
     * Searches for a movie by genre.
     *
     * @param value The genre to search for.
     * @return {@code true} if the movie is found or not found; {@code false} if an error occurs.
     */
    public boolean searchMovieByGenre(String value) {
        // Remove espaços em branco no início e fim do valor fornecido
        value = value.trim();

        // Verifica se o valor fornecido não está vazio
        if (validateNewInputString(value)) {

            boolean movieFound = false;  // Flag para verificar se algum filme foi encontrado
            // Verifica se a lista de filmes não está vazia
            if (!listOfMovies.isEmpty()) {
                // Itera sobre todos os filmes na lista
                for (MovieModel movie : listOfMovies) {
                    // Verifica se o gênero do filme contém o valor fornecido, ignorando diferenças de maiúsculas/minúsculas
                    if (movie.getGenre().toLowerCase().contains(value.toLowerCase().trim())) {

                        if (!movieFound) {
                            movieView.headerForMovie();  // Exibe o cabeçalho para os filmes encontrados
                            movieFound = true;  // Marca que um filme foi encontrado
                        }
                        movieView.movieInformation(movie);  // Exibe as informações do filme
                    }
                }
            }

            // Se nenhum filme foi encontrado, exibe uma mensagem informando
            if (!movieFound) {
                movieView.noMovieFoundMessage();
            }
            return true;
        }

        return false;
    } // Busca filme por gênero

    /**
     * Searches for a movie by year of release.
     *
     * @param value The year to search for.
     * @return {@code true} if the movie is found or not found; {@code false} if an error occurs.
     */
    public boolean searchMovieByYearOfRelease(String value) {
        // Remove espaços em branco no início e fim do valor fornecido
        value = value.trim();

        // Verifica se o valor fornecido não está vazio e se é um número inteiro válido
        if (validateNewInputString(value) && validateNewInputInt(value)) {

            boolean movieFound = false;  // Flag para verificar se algum filme foi encontrado
            // Verifica se a lista de filmes não está vazia
            if (!listOfMovies.isEmpty()) {

                int valueInt = 0;  // Variável para armazenar o ano de lançamento como inteiro
                try {
                    // Converte o valor fornecido para inteiro
                    valueInt = Integer.parseInt(value);
                } catch (Exception e) {
                    movieView.invalidMessage();  // Exibe mensagem de erro se a conversão falhar
                    return false;
                }

                // Itera sobre todos os filmes na lista
                for (MovieModel movie : listOfMovies) {
                    // Verifica se o ano de lançamento do filme corresponde ao valor fornecido
                    if (movie.getYearOfRelease() == valueInt) {

                        if (!movieFound) {
                            movieView.headerForMovie();  // Exibe o cabeçalho para os filmes encontrados
                            movieFound = true;  // Marca que um filme foi encontrado
                        }

                        movieView.movieInformation(movie);  // Exibe as informações do filme
                    }
                }

            }
            // Se nenhum filme foi encontrado, exibe uma mensagem informando
            if (!movieFound) {
                movieView.noMovieFoundMessage();
            }
            return true;
        }

        return false;
    } // Busca filme por ano de lançamento

    /**
     * Lists all movies.
     *
     * @return {@code true} if the list is shown successfully; {@code false} if an error occurs.
     */
    public boolean listMovies() {
        try {
            // Verifica se a lista de filmes está vazia
            if (listOfMovies.isEmpty()) {
                movieView.emptyListMessage();  // Exibe mensagem de lista vazia
            } else {
                movieView.headerForMovie();  // Exibe o cabeçalho para os filmes
                // Itera sobre todos os filmes na lista e exibe suas informações
                for (MovieModel movie : listOfMovies) {
                    movieView.movieInformation(movie);
                }
            }
            return true;
        } catch (Exception e) {
            movieView.invalidMessage();  // Exibe mensagem de erro caso ocorra uma exceção
            return false;
        }
    } // Lista todos os filmes

    /**
     * Filters the list of movies by genre.
     *
     * @param value The genre to filter by.
     * @return {@code true} if filtering is successful; {@code false} if an error occurs.
     */
    public boolean filterListOfMoviesByGenre(String value) {
        reserveListOfMovies.clear();
        value = value.trim(); // Remove espaços em branco extras ao redor do valor fornecido

        if (validateNewInputString(value)) { // Valida se o valor fornecido não está vazio

            boolean movieFound = false; // Variável para controlar se algum filme foi encontrado
            if (!listOfMovies.isEmpty()) { // Verifica se a lista de filmes não está vazia

                for (MovieModel movie : listOfMovies) { // Itera sobre a lista de filmes
                    if (movie.getGenre().toLowerCase().equals(value.toLowerCase().trim())) { // Verifica se o gênero do filme contém o valor fornecido

                        if (!movieFound) { // Se não encontrou nenhum filme ainda
                            movieView.headerForMovie(); // Exibe o cabeçalho
                            movieFound = true; // Marca que um filme foi encontrado
                        }

                        movieView.movieInformation(movie); // Exibe as informações do filme
                        reserveListOfMovies.add(movie);
                    }
                }

            }
            if (!movieFound) {
                movieView.noMovieFoundMessage(); // Caso nenhum filme tenha sido encontrado
            }
            return true;
        }

        return false;
    } // Filtra a lista de filmes pelo gênero

    /**
     * Filters the list of movies by year of release.
     *
     * @param value The year to filter by.
     * @return {@code true} if filtering is successful; {@code false} if an error occurs.
     */
    public boolean filterListOfMoviesByYearOfRelease(String value) {
        reserveListOfMovies.clear();
        value = value.trim(); // Remove espaços em branco extras ao redor do valor fornecido

        if (validateNewInputString(value) && validateNewInputInt(value)) { // Valida se o valor fornecido não está vazio e é um número válido

            boolean movieFound = false; // Variável para controlar se algum filme foi encontrado
            if (!listOfMovies.isEmpty()) { // Verifica se a lista de filmes não está vazia

                int valueInt = 0; // Variável para armazenar o ano de lançamento
                try {
                    valueInt = Integer.parseInt(value); // Converte o valor para inteiro
                } catch (Exception e) {
                    movieView.invalidMessage(); // Exibe mensagem de erro se a conversão falhar
                    return false; // Retorna falso se a conversão falhar
                }

                for (MovieModel movie : listOfMovies) { // Itera sobre a lista de filmes
                    if (movie.getYearOfRelease() == valueInt) { // Verifica se o ano de lançamento do filme corresponde ao valor fornecido

                        if (!movieFound) { // Se não encontrou nenhum filme ainda
                            movieView.headerForMovie(); // Exibe o cabeçalho
                            movieFound = true; // Marca que um filme foi encontrado
                        }

                        movieView.movieInformation(movie); // Exibe as informações do filme
                        reserveListOfMovies.add(movie);
                    }
                }

            }
            if (!movieFound) {
                movieView.noMovieFoundMessage(); // Caso nenhum filme tenha sido encontrado
            }
            return true;
        }

        return false;
    } // Filtra a lista de filmes pelo ano de lançamento

    /**
     * Sorts the list of movies by top rated.
     *
     * @return {@code true} if sorting is successful; {@code false} if an error occurs.
     */
    public boolean sortListByTopRated() {
        reserveListOfMovies.clear();

        try {
            if (!listOfMovies.isEmpty()) { // Verifica se a lista de filmes não está vazia
                ArrayList<MovieModel> listOfReviewedMovies = new ArrayList<MovieModel>(); // Cria uma lista para filmes que possuem avaliação

                for (MovieModel movie : listOfMovies) { // Itera sobre todos os filmes
                    if (movie.getMovieReview() != null) { // Verifica se o filme possui avaliação
                        listOfReviewedMovies.add(movie); // Adiciona o filme à lista de filmes avaliados
                    }
                }

                ArrayList<MovieModel> highlyRatedMovies = new ArrayList<MovieModel>(listOfReviewedMovies); // Cria uma nova lista para os filmes avaliados

                if (!highlyRatedMovies.isEmpty()) { // Se existem filmes avaliados
                    highlyRatedMovies.sort(Comparator.comparing(movieModel -> movieModel.getMovieReview().getScore(), Comparator.reverseOrder())); // Ordena os filmes pela avaliação de forma decrescente
                    setReserveListOfMovies(highlyRatedMovies);
                } else {
                    movieView.emptyEvaluatedListMessage(); // Exibe mensagem caso não haja filmes avaliados
                    return true; // Retorna true, pois o processo foi realizado
                }

                movieView.headerForMovie(); // Exibe o cabeçalho para os filmes
                for (MovieModel movie : highlyRatedMovies) { // Exibe os filmes ordenados
                    movieView.movieInformation(movie);
                }

            } else {
                movieView.emptyListMessage(); // Exibe mensagem se a lista de filmes estiver vazia
            }

            return true;
        } catch (Exception e) {
            movieView.invalidMessage(); // Exibe mensagem de erro se houver exceção
            return false;
        }
    } // Ordena a lista de filmes pela avaliação, exibindo os mais bem avaliados

    /**
     * Sorts the list of movies by lowest rating.
     *
     * @return {@code true} if sorting is successful; {@code false} if an error occurs.
     */
    public boolean sortListByLowRated() {
        reserveListOfMovies.clear();

        try {
            if (!listOfMovies.isEmpty()) { // Verifica se a lista de filmes não está vazia
                ArrayList<MovieModel> listOfReviewedMovies = new ArrayList<MovieModel>(); // Cria uma lista para filmes com avaliação

                for (MovieModel movie : listOfMovies) { // Itera sobre todos os filmes
                    if (movie.getMovieReview() != null) { // Verifica se o filme possui avaliação
                        listOfReviewedMovies.add(movie); // Adiciona o filme à lista de filmes avaliados
                    }
                }

                ArrayList<MovieModel> poorlyRatedMovies = new ArrayList<MovieModel>(listOfReviewedMovies); // Cria uma nova lista para os filmes com avaliação

                if (!poorlyRatedMovies.isEmpty()) { // Se existem filmes avaliados
                    poorlyRatedMovies.sort(Comparator.comparing(movieModel -> movieModel.getMovieReview().getScore())); // Ordena os filmes pela avaliação de forma crescente (pior avaliação primeiro)
                    setReserveListOfMovies(poorlyRatedMovies);
                } else {
                    movieView.emptyEvaluatedListMessage(); // Exibe mensagem caso não haja filmes avaliados
                    return true; // Retorna true, pois o processo foi realizado
                }

                movieView.headerForMovie(); // Exibe o cabeçalho para os filmes
                for (MovieModel movie : poorlyRatedMovies) { // Exibe os filmes ordenados
                    movieView.movieInformation(movie);
                }

            } else {
                movieView.emptyListMessage(); // Exibe mensagem se a lista de filmes estiver vazia
            }

            return true;
        } catch (Exception e) {
            movieView.invalidMessage(); // Exibe mensagem de erro se houver exceção
            return false;
        }
    } // Ordena a lista de filmes pela avaliação, exibindo os filmes com as piores avaliações primeiro

    /**
     * Opens the movie at the specified index.
     *
     * @param index The index of the movie to open.
     * @return {@code true} if the movie is opened successfully; {@code false} if an error occurs.
     */
    public boolean openMovie(int index) {
        try {
            MovieModel movie; // Declaração da variável que armazenará o filme
            try {
                movie = listOfMovies.get(index-1); // Tenta acessar o filme na posição fornecida (index - 1 devido à indexação começar do 0)
            } catch (Exception e) { // Se ocorrer um erro ao acessar o filme (como índice inválido)
                movieView.noMovieFoundMessage(); // Exibe mensagem informando que o filme não foi encontrado
                return false;
            }

            movieView.fullMovieInformation(movie); // Exibe as informações completas do filme
            return true;
        } catch (Exception e) {
            movieView.invalidMessage(); // Exibe mensagem de erro se houver algum outro problema
            return false;
        }
    } // Abre um filme a partir de um índice, exibindo suas informações completas

    /**
     * Changes the viewing status of a movie at the given index.
     *
     * @param index The index of the movie.
     * @param value The new viewing status.
     * @return {@code true} if the status is changed successfully; {@code false} if an error occurs.
     */
    public boolean changeMovieViewingStatus(int index, String value) {
        MovieModel movie;

        try {
            movie = listOfMovies.get(index-1); // Tenta acessar o filme na posição fornecida (index - 1 devido à indexação começar do 0)
        } catch (Exception e) { // Se ocorrer um erro ao acessar o filme (como índice inválido)
            movieView.noMovieFoundMessage(); // Exibe mensagem informando que o filme não foi encontrado
            return false;
        }

        value = value.trim(); // Remove espaços extras do valor

        if (movie == null) { // Verifica se o filme é nulo
            movieView.invalidMessage(); // Exibe mensagem de erro
            return false;
        }

        boolean validWatched = validateNewWatched(value); // Valida a resposta sobre o status de visualização

        if (!validWatched) { // Se a resposta não for válida
            movieView.tryAgainMessage(); // Exibe mensagem pedindo para tentar novamente
            return false;
        }

        try {
            // Conjuntos de respostas positivas e negativas para marcar como assistido
            Set<String> positiveResponsesWatched = Set.of(
                    "sim", "s", "assisti", "sim assisti", "assisti sim", "s assisti", "assisti s", "já assisti", "ja assisti", "já", "ja"
            );

            Set<String> negativeResponsesWatched = Set.of(
                    "não", "nao", "n", "não assisti", "nao assisti", "n assisti",
                    "assisti não", "assisti nao", "assisti n"
            );

            boolean watched = false; // Inicializa a variável para o status de visualização

            // Verifica se a resposta é positiva, marcando como assistido
            if (positiveResponsesWatched.contains(value.toLowerCase())) {
                watched = true;
            }
            // Se o filme não foi assistido e a resposta for negativa, marca como não assistido
            else if ((movie.isWatched() == false) && negativeResponsesWatched.contains(value.toLowerCase())) {
                watched = false;
            }
            // Se o filme já foi assistido e a resposta for negativa, exibe erro
            else if ((movie.isWatched() == true) && negativeResponsesWatched.contains(value.toLowerCase())) {
                movieView.wrongWatchedMessage(); // Exibe mensagem de erro para status incoerente
                return false;
            }

            movie.setWatched(watched); // Atualiza o status de visualização do filme
            movieView.updatedWatchedMessage(); // Exibe mensagem indicando que o status foi atualizado
            saveFile();
            return true;
        } catch (Exception e) {
            movieView.invalidMessage(); // Exibe mensagem de erro caso algo de errado aconteça
            return false;
        }
    } // Muda o status de visualização de um filme

    /**
     * Evaluates a movie with a score, consumption date, and comment.
     *
     * @param index The index of the movie.
     * @param score The score given to the movie.
     * @param consumptionDate The date the movie was watched.
     * @param comment Additional comments about the movie.
     * @return {@code true} if the evaluation is successful; {@code false} if an error occurs.
     */
    public boolean evaluateMovie(int index, String score, String consumptionDate, String comment) {
        try {
            // Remover espaços extras das entradas
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            MovieModel movie;
            try {
                // Tenta acessar o filme pelo índice (index-1 devido à indexação começar do 0)
                movie = listOfMovies.get(index - 1);
            } catch (Exception e) { // Se ocorrer erro ao acessar o filme (índice inválido)
                movieView.noMovieFoundMessage(); // Exibe mensagem de filme não encontrado
                return false;
            }

            // Verifica se o filme já possui avaliação
            if (!checkMovieReview(movie)) {
                if (movie.isWatched()) { // Só permite avaliação se o filme foi assistido
                    boolean validScore = validateNewScore(score); // Valida a pontuação fornecida
                    boolean validConsumptionDate = validateNewDate(movie, consumptionDate); // Valida a data de consumo
                    boolean validComment = validateNewString(comment, "Comentários"); // Valida o comentário

                    // Se qualquer validação falhar, solicita que o usuário tente novamente
                    if (validScore == false || validConsumptionDate == false || validComment == false) {
                        movieView.tryAgainMessage(); // Exibe mensagem solicitando nova tentativa
                        return false; // Retorna falso, indicando falha na avaliação
                    }

                    // Converte a pontuação (score) para um valor numérico (float)
                    float scoreFloat = 0f;
                    try {
                        scoreFloat = Float.parseFloat(score);
                    } catch (Exception e) { // Se ocorrer erro na conversão para float
                        movieView.invalidMessage(); // Exibe mensagem de erro
                        return false;
                    }

                    // Cria um novo objeto de avaliação com os dados fornecidos
                    ReviewModel reviewModel = new ReviewModel(scoreFloat, consumptionDate, comment);
                    movie.setMovieReview(reviewModel); // Define a avaliação no filme
                    movie.setEvaluatedMovie(true); // Marca o filme como avaliado

                    movieView.registeredEvaluationMessage(); // Exibe mensagem informando que a avaliação foi registrada
                    saveFile();
                    return true;
                } else {
                    movieView.unwatchedMovieMessage(); // Se o filme não foi assistido, exibe mensagem de erro
                    return false;
                }
            } else {
                movieView.messageOfMovieAlreadyEvaluated(); // Se o filme já foi avaliado, exibe mensagem de erro
                return false;
            }
        } catch (Exception e) { // Captura erros inesperados
            movieView.invalidMessage(); // Exibe mensagem de erro
            return false;
        }
    } // Avalia um filme

    /**
     * Updates the evaluation of a movie.
     *
     * @param index The index of the movie.
     * @param score The new score.
     * @param consumptionDate The new consumption date.
     * @param comment The new comment.
     * @return {@code true} if the update is successful; {@code false} if an error occurs.
     */
    public boolean evaluateMovieAgain(int index, String score, String consumptionDate, String comment) {
        try {
            // Remove espaços extras das entradas de dados
            score = score.trim();
            consumptionDate = consumptionDate.trim();
            comment = comment.trim();

            // Tenta acessar o filme pelo índice fornecido
            MovieModel movie = listOfMovies.get(index-1);

            // Verifica se o filme já foi avaliado
            if (checkMovieReview(movie)) {
                // Se o filme já foi avaliado, marca como não avaliado para permitir uma nova avaliação
                movie.setEvaluatedMovie(false);
                // Chama a função de avaliação novamente para o filme, passando os novos dados
                return evaluateMovie(index, score, consumptionDate, comment);
            } else {
                // Se o filme não foi avaliado anteriormente, exibe uma mensagem informando isso
                movieView.unratedMovieMessage();
                return false; // Retorna falso, indicando que a avaliação não pode ser feita
            }
        } catch (Exception e) {
            // Captura qualquer erro inesperado
            movieView.invalidMessage(); // Exibe uma mensagem de erro genérica
            return false; // Retorna falso, indicando falha no processo
        }
    } // Avalia um filme novamente

    /**
     * Validates a new score value.
     *
     * @param value The score to validate.
     * @return {@code true} if the score is valid; {@code false} otherwise.
     */
    public boolean validateNewScore(String value) {
        // Remove espaços extras no valor de entrada
        value = value.trim();

        // Verifica se o valor de entrada não está vazio
        if (validateNewInputString(value)) {
            try {
                // Tenta converter o valor para um número de ponto flutuante (float)
                float score = Float.parseFloat(value);

                // Verifica se a pontuação está dentro do intervalo válido (de 1 a 5)
                if (score < 1 || score > 5) {
                    // Se a pontuação estiver fora do intervalo, exibe mensagem de erro
                    movieView.invalidScoreMessage();
                    return false;
                }
                // Se a pontuação for válida, retorna true
                return true;
            } catch (Exception e) {
                // Se ocorrer um erro na conversão para número, exibe mensagem de erro
                movieView.invalidNumberMessage();
                return false;
            }
        }

        return false; // Retorna falso caso a validação do valor de entrada falhe
    } // Valida um novo score

    /**
     * Validates a new date for a movie.
     *
     * @param movie The movie to validate the date against.
     * @param value The date value to validate.
     * @return {@code true} if the date is valid; {@code false} otherwise.
     */
    public boolean validateNewDate(MovieModel movie, String value) {
        // Remove espaços extras do valor fornecido
        value = value.trim();

        // Verifica se a entrada não está vazia
        if (validateNewInputString(value)) {
            // Tenta dividir a data em partes (dia, mês, ano) utilizando o delimitador "/"
            String[] parts = value.split("/");

            // Verifica se a data tem exatamente 3 partes (dia, mês, ano)
            if (parts.length != 3) {
                movieView.invalidDateFormatMessage(); // Exibe mensagem de formato inválido
                return false;
            }

            // Extrai o dia, mês e ano das partes
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            // Verifica se o dia ou mês têm mais de 2 dígitos e se o ano tem 4 dígitos
            if (day.length() > 2 || month.length() > 2 || year.length() != 4) {
                movieView.invalidDateMessage(); // Exibe mensagem de erro caso o formato não seja válido
                return false;
            }

            // Valida a data (verifica se a data é válida em termos de dia, mês e ano)
            boolean valid = validateExistingDate(day, month, year);
            if (!valid) {
                return false;
            }

            try {
                // Tenta converter o ano para um inteiro
                int yearInt = Integer.parseInt(year);

                // Verifica se o ano da data é anterior ao ano de lançamento do filme
                if (yearInt < movie.getYearOfRelease()) {
                    movieView.invalidYearPeriodMessage(movie.getYearOfRelease()); // Exibe mensagem de erro se o ano for inválido
                    return false;
                }
            } catch (NumberFormatException e) {
                // Se ocorrer um erro ao converter o ano para inteiro, exibe mensagem de erro
                movieView.invalidDateMessage();
                return false;
            }


            return true;
        }

        return false;
    } // Valida uma nova data

    /**
     * Validates an existing date given day, month, and year.
     *
     * @param day The day part of the date.
     * @param month The month part of the date.
     * @param year The year part of the date.
     * @return {@code true} if the date is valid; {@code false} otherwise.
     */
    public boolean validateExistingDate(String day, String month, String year) {
        try {
            // Converte o dia, mês e ano fornecidos em inteiros
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);

            // Verifica se o mês fornecido está entre 1 e 12 (meses válidos)
            if (m < 1 || m > 12) {
                movieView.nonExistentDateMessage(); // Exibe mensagem caso o mês não seja válido
                return false;
            }

            // Ajusta o mês para a convenção do Calendar (mês começa em 0, janeiro = 0)
            m = m - 1;

            // Cria uma instância de Calendar para validar a data
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false); // Desativa o comportamento leniente, evitando datas inválidas
            cal.set(y, m, d); // Define a data com o ano, mês e dia fornecidos
            cal.getTime(); // Verifica se a data é válida (lança uma exceção caso não seja)

            // Ajusta a hora da data para 00:00:00 para comparações de data apenas
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            // Cria uma instância de Calendar para representar a data de hoje
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0); // Ajusta a hora para 00:00:00
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            // Verifica se a data fornecida é no futuro em relação à data atual
            if (cal.after(today)) {
                movieView.invalidFutureDatesMessage(); // Exibe mensagem caso a data seja no futuro
                return false;
            }

            return true;

        } catch (Exception e) {
            // Em caso de erro durante a validação (data inválida), exibe mensagem de erro
            movieView.invalidDateMessage();
            return false;
        }
    } // Valida existência de data

    /**
     * Checks if a movie has a review.
     *
     * @param movie The movie to check.
     * @return {@code true} if the movie has a review; {@code false} otherwise.
     */
    public boolean checkMovieReview(MovieModel movie) {
        // Verifica se o filme já foi avaliado (ou seja, se a propriedade 'evaluatedMovie' é verdadeira)
        if (movie.isEvaluatedMovie()) {
            return true;
        }
        return false;
    } // Verifica se um filme já foi avaliado

    /**
     * Validates if the input string is a valid integer.
     *
     * @param value The input string to validate.
     * @return {@code true} if the input is a valid integer; {@code false} otherwise.
     */
    public boolean validateNewInputInt(String value) {
        try {
            // Tenta converter o valor de entrada para um número inteiro
            int valueInt = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            // Se ocorrer uma exceção durante a conversão (caso o valor não seja um número inteiro válido)
            movieView.integerMessage();  // Exibe uma mensagem de erro informando que o valor não é um número inteiro
            return false;
        }
    } // Valida uma nova entrada de um número inteiro

    /**
     * Opens the file, creating directory and file if needed.
     */
    public void openFile() {
        if (!repository.exists()) { // Verifica se o diretório não existe
            repository.mkdirs(); // Cria o diretório
        }

        if (!file.exists()){ // Verifica se o arquivo não existe
            try {
                file.createNewFile(); // Tenta criar o arquivo
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (file != null && file.length() > 0) {  // Se o arquivo existe e não está vazio
                uploadFile(); // Carrega os dados do arquivo
            }
        }
    } // Abre um arquivo do repositório

    /**
     * Loads the data from the file into the list of movies.
     */
    public void uploadFile() {
        try (FileReader reader = new FileReader(file)) {  // Lê o conteúdo do arquivo JSON
            Type typeList = new TypeToken<ArrayList<MovieModel>>() {}.getType();  // Define o tipo da lista
            listOfMovies = gson.fromJson(reader, typeList);  // Converte o JSON para lista de filmes
        } catch (IOException e) {
            e.printStackTrace();  // Imprime erro caso ocorra falha na leitura
        }
    } // Carrega um arquivo do repositório

    /**
     * Saves the list of movies to the file in JSON format.
     */
    public void saveFile() {
        try (FileWriter writer = new FileWriter(file)) { // Abre o arquivo para escrita
            gson.toJson(listOfMovies, writer); // Converte a lista de filmes para JSON e grava no arquivo
        } catch (IOException e) {
            e.printStackTrace(); // Imprime erro caso ocorra falha na escrita
        }
    } // Salva um arquivo no repositório

    public ArrayList<MovieModel> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(ArrayList<MovieModel> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    public ArrayList<MovieModel> getReserveListOfMovies() {
        return reserveListOfMovies;
    }

    public void setReserveListOfMovies(ArrayList<MovieModel> reserveListOfMovies) {
        this.reserveListOfMovies = reserveListOfMovies;
    }
}