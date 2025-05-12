package culturaldiary.movie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import culturaldiary.review.ReviewModel;

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
 * @version 1.0
 */
public class MovieController {
    MovieView movieView = new MovieView(); // Instancia a visualização (interface) dos filmes
    MovieModel movieModel; // Declara um modelo de filme (não instanciado ainda)
    private ArrayList<MovieModel> listOfMovies = new ArrayList<MovieModel>(); // Recupera a lista de filmes do repositório

    Calendar calendar = Calendar.getInstance(); // Obtém uma instância do calendário com a data/hora atual

    Gson gson = new Gson();
    File repository = new File("src/culturaldiary/movie/repository/");
    File file = new File(repository,"movie_file.json");

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
            movieModel = new MovieModel(title.trim(), genre.trim(), yearOfRelease, durationTime.trim(), direction.trim(), screenplay.trim(), cast, originalTitle.trim(), whereToWatch.trim(), watched);
            listOfMovies.add(movieModel);

            // Exibe uma mensagem de sucesso ao registrar o filme
            movieView.registeredMovieMessage(title);

            return true;
        } catch (Exception e) {
            // Se ocorrer um erro durante o processo, exibe uma mensagem de erro
            movieView.invalidMessage();  // Exibe uma mensagem indicando que houve um erro
            return false;
        }
    } // Registra filme

    public boolean validateTitle(String title) {
        return validateNewString(title, "Título");
    }  // Valida o título do filme

    public boolean validateGenre(String genre) {
        return validateNewString(genre, "Gênero");
    }  // Valida o gênero do filme

    public boolean validateYearOfRelease(String yearOfRelease) {
        return validateNewYear(yearOfRelease);
    }  // Valida o ano de lançamento do filme

    public boolean validateDurationTime(String durationTime) {
        return validateNewTime(durationTime);
    }  // Valida o tempo de duração do filme

    public boolean validateDirection(String direction) {
        return validateNewString(direction, "Direção");
    }  // Valida a direção do filme

    public boolean validateScreenplay(String screenplay) {
        return validateNewString(screenplay, "Roteiro");
    }  // Valida o roteiro do filme

    public boolean validateCast(String cast) {
        return validateNewCast(cast);
    }  // Valida o elenco do filme

    public boolean validateOriginalTitle(String originalTitle) {
        return validateNewString(originalTitle, "Título original");
    }  // Valida o título original do filme

    public boolean validateWhereToWatch(String whereToWatch) {
        return validateNewString(whereToWatch, "Onde assistir");
    }  // Valida onde o filme pode ser assistido

    public boolean validateWatched(String watched) {
        return validateNewWatched(watched);
    }  // Valida se o filme foi assistido

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

    public boolean validateNewString(String value, String name) {
        // Verifica se o valor fornecido está vazio
        if (value.isEmpty()) {
            // Exibe uma mensagem informando que o valor está vazio, passando o nome do campo
            movieView.emptyValueMessage(name);
            return false;
        }
        return true;
    }  // Valida se o valor fornecido não está vazio

    public boolean validateNewInputString(String value) {
        // Verifica se o valor fornecido está vazio
        if (value.isEmpty()) {
            // Exibe uma mensagem informando que a informação está vazia
            movieView.emptyInformationMessage();
            return false;
        }
        return true;
    }  // Valida se o valor fornecido não está vazio

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

    public boolean filterListOfMoviesByGenre(String value) {
        value = value.trim(); // Remove espaços em branco extras ao redor do valor fornecido

        if (validateNewInputString(value)) { // Valida se o valor fornecido não está vazio

            boolean movieFound = false; // Variável para controlar se algum filme foi encontrado
            if (!listOfMovies.isEmpty()) { // Verifica se a lista de filmes não está vazia

                for (MovieModel movie : listOfMovies) { // Itera sobre a lista de filmes
                    if (movie.getGenre().toLowerCase().contains(value.toLowerCase().trim())) { // Verifica se o gênero do filme contém o valor fornecido

                        if (!movieFound) { // Se não encontrou nenhum filme ainda
                            movieView.headerForMovie(); // Exibe o cabeçalho
                            movieFound = true; // Marca que um filme foi encontrado
                        }

                        movieView.movieInformation(movie); // Exibe as informações do filme
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

    public boolean filterListOfMoviesByYearOfRelease(String value) {
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

    public boolean sortListByTopRated() {
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

    public boolean sortListByLowRated() {
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

            return true;
        } catch (Exception e) {
            movieView.invalidMessage(); // Exibe mensagem de erro caso algo de errado aconteça
            return false;
        }
    } // Muda o status de visualização de um filme

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

    public boolean checkMovieReview(MovieModel movie) {
        // Verifica se o filme já foi avaliado (ou seja, se a propriedade 'evaluatedMovie' é verdadeira)
        if (movie.isEvaluatedMovie()) {
            return true;
        }
        return false;
    } // Verifica se um filme já foi avaliado

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

    public void openFile() {
        if (!repository.exists()) {
            repository.mkdirs();
        }

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (file != null && file.length() > 0) {
                uploadFile();
            }
        }
    }

    public void uploadFile() {
        try (FileReader reader = new FileReader(file)) {
            Type typeList = new TypeToken<ArrayList<MovieModel>>() {}.getType();
            listOfMovies = gson.fromJson(reader, typeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(listOfMovies, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MovieModel> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(ArrayList<MovieModel> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }
}