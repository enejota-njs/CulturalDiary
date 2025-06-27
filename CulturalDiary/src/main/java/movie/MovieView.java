package movie;

import review.ReviewModel;

/**
 * View class for displaying movie-related information to the user.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.1
 */
public class MovieView {
    static final String BOLD = "\033[1m";
    static final String RESET = "\033[0m";

    /**
     * Displays a message indicating that a required value is empty.
     *
     * @param name The name of the field that is empty.
     */
    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio.");
    } // Exibe uma mensagem de erro quando o valor de um campo está vazio

    /**
     * Displays a message indicating that an integer value is required.
     */
    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    } // Exibe uma mensagem de erro quando o valor inserido não é um número inteiro

    /**
     * Displays a message indicating the year is invalid.
     *
     * @param currentYear The current year used for validation.
     */
    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    } // Exibe uma mensagem de erro caso o ano esteja fora do intervalo válido (1700 até o ano atual)

    /**
     * Displays a message indicating that required information is missing or empty.
     */
    public void emptyInformationMessage() {
        System.out.println("\nA informação está vazia.");
    } // Exibe uma mensagem de erro quando a informação está vazia

    /**
     * Displays a message indicating that the provided time is invalid.
     */
    public void invalidTimeMessage() {
        System.out.println("\nHorário inválido.");
    } // Exibe uma mensagem de erro quando o horário inserido é inválido

    /**
     * Displays a message indicating that the watched status is invalid.
     */
    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    } // Exibe uma mensagem de erro quando a resposta para a pergunta sobre visualização não é válida

    /**
     * Displays a message prompting the user to try again.
     */
    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    } // Exibe uma mensagem para tentar novamente

    /**
     * Displays a message indicating that the cast information is invalid.
     */

    public void invalidCastMessage() {
        System.out.println("\nNo elenco só pode conter letras, números e vírgulas.");
    } // Exibe uma mensagem de erro quando o elenco contém caracteres inválidos

    /**
     * Displays a message indicating that the cast information is empty.
     */
    public void emptyCastMessage() {
        System.out.println("\nO elenco está vazio.");
    } // Exibe uma mensagem de erro quando o elenco está vazio

    /**
     * Displays a generic invalid input message.
     */
    public void invalidMessage() {
        System.out.println("\nInválido.");
    } // Exibe uma mensagem genérica de erro

    /**
     * Displays a message confirming the movie was registered successfully.
     *
     * @param title The title of the registered movie.
     */
    public void registeredMovieMessage(String title) {
        System.out.println("\n" + title + " cadastrado.");
    } // Exibe uma mensagem confirmando que o filme foi registrado com sucesso

    /**
     * Centers the given text within a specified width.
     *
     * @param text The text to center.
     * @param width The total width for centering.
     * @return The centered text string.
     */
    public String centerHeader(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        return " ".repeat(left) + text + " ".repeat(right);
    } // Centraliza o texto dentro de uma largura específica

    /**
     * Prints the header for the movie list.
     */
    public void headerForMovie() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-38s | %-28s | %-28s | %-10s | %-8s |\n",
                BOLD + centerHeader("Índice", 8) + RESET,
                BOLD + centerHeader("Título", 38) + RESET,
                BOLD + centerHeader("Direção", 28)+ RESET,
                BOLD + centerHeader("Gênero", 28) + RESET,
                BOLD + centerHeader("Ano", 10) + RESET,
                BOLD + centerHeader("Nota", 8) + RESET);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    } // Exibe o cabeçalho formatado para a lista de filmes

    /**
     * Displays information about a given movie.
     *
     * @param movie The movie to display information for.
     */
    public void movieInformation(MovieModel movie) {
        String temporaryMovieIndex = Integer.toString(movie.getMovieIndex());
        String temporaryMovieTitle = movie.getTitle();
        String temporaryMovieDirection = movie.getDirection();
        String temporaryMovieGenre = movie.getGenre();
        ReviewModel temporaryReview = movie.getMovieReview();

        if (temporaryMovieIndex.length() > 8) {
            temporaryMovieIndex = temporaryMovieIndex.substring(0,  5) + "...";
        }

        if (temporaryMovieTitle.length() > 38) {
            temporaryMovieTitle = temporaryMovieTitle.substring(0,  35) + "...";
        }

        if (temporaryMovieDirection.length() > 28) {
            temporaryMovieDirection = temporaryMovieDirection.substring(0,  25) + "...";
        }

        if (temporaryMovieGenre.length() > 28) {
            temporaryMovieGenre = temporaryMovieGenre.substring(0,  25) + "...";
        }

        String temporaryScore;
        if (temporaryReview == null) {
            temporaryScore = "Vazio";
        }
        else {
            temporaryScore = String.valueOf(temporaryReview.getScore());
        }
        System.out.printf("| %-8s | %-38s | %-28s | %-28s | %-10d | %-8s |\n", temporaryMovieIndex, temporaryMovieTitle,
                temporaryMovieDirection, temporaryMovieGenre, movie.getYearOfRelease(), temporaryScore);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    } // Exibe as informações de um filme com formatação, cortando os valores longos

    /**
     * Displays full detailed information about the given movie.
     *
     * @param movie The movie to display details for.
     */
    public void fullMovieInformation(MovieModel movie) {
        int index = movie.getMovieIndex();

        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Filme n°", index);
        System.out.println("+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Título", movie.getTitle());
        System.out.printf("| %-20s -> %s\n", "Gênero", movie.getGenre());
        System.out.printf("| %-20s -> %d\n", "Ano de Lançamento", movie.getYearOfRelease());
        System.out.printf("| %-20s -> %s\n", "Tempo de duração", movie.getDurationTime());
        System.out.printf("| %-20s -> %s\n", "Direção", movie.getDirection());
        System.out.printf("| %-20s -> %s\n", "Roteiro", movie.getScreenplay());
        System.out.printf("| %-20s -> %s\n", "Elenco", movie.getCastAsString());
        System.out.printf("| %-20s -> %s\n", "Título original", movie.getOriginalTitle());
        System.out.printf("| %-20s -> %s\n", "Onde assitir", movie.getWhereToWatch());

        if (movie.getMovieReview() == null) {
            System.out.printf("| %-20s -> %s\n", "Avaliação", "Filme não avaliado");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-20s -> %.2f\n", "Nota", movie.getMovieReview().getScore());
            System.out.printf("| %-20s -> %s\n", "Data de visualização", movie.getMovieReview().getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", movie.getMovieReview().getComment());
            System.out.println("+-----------------------+");
        }
    } // Exibe as informações completas de um filme, incluindo a avaliação se disponível

    /**
     * Displays a message indicating the evaluated movies list is empty.
     */
    public void emptyEvaluatedListMessage() {
        System.out.println("\nA lista de filmes avaliados está vazia.");
    } // Exibe uma mensagem indicando que a lista de filmes avaliados está vazia

    /**
     * Displays a message indicating the movie list is empty.
     */
    public void emptyListMessage() {
        System.out.println("\nA lista de filmes está vazia.");
    } // Exibe uma mensagem indicando que a lista de filmes está vazia

    /**
     * Displays a message indicating an invalid number was entered.
     */
    public void invalidNumberMessage() {
        System.out.println("\nPor favor, digite um número válido.");
    } // Exibe uma mensagem quando um valor inválido é inserido no lugar de um número

    /**
     * Displays a message indicating the date format is invalid.
     */
    public void invalidDateFormatMessage() {
        System.out.println("\nFormato de data inválida.");
    } // Exibe uma mensagem de erro quando a data inserida tem um formato inválido

    /**
     * Displays a message indicating the date is invalid.
     */
    public void invalidDateMessage() {
        System.out.println("\nData inválida.");
    } // Exibe uma mensagem de erro quando a data é inválida

    /**
     * Displays a message indicating the movie has already been evaluated.
     */
    public void messageOfMovieAlreadyEvaluated() {
        System.out.println("\nEsse filme já foi avaliado.");
    } // Exibe uma mensagem indicando que o filme já foi avaliado

    /**
     * Displays a message indicating the date does not exist.
     */
    public void nonExistentDateMessage() {
        System.out.println("\nData inexistente.");
    } // Exibe uma mensagem indicando que a data informada não existe

    /**
     * Displays a message indicating the movie has not been rated yet.
     */
    public void unratedMovieMessage() {
        System.out.println("\nEsse filme ainda não foi avaliado.");
    } // Exibe uma mensagem indicando que o filme ainda não foi avaliado

    /**
     * Displays a message indicating the evaluation was registered successfully.
     */
    public void registeredEvaluationMessage() {
        System.out.println("\nAvaliação cadastrada.");
    } // Exibe uma mensagem indicando que a avaliação foi cadastrada com sucesso

    /**
     * Displays a message indicating the movie has not been watched.
     */
    public void unwatchedMovieMessage() {
        System.out.println("\nEsse filme ainda não foi assistido.");
    } // Exibe uma mensagem indicando que o filme ainda não foi assistido

    /**
     * Displays a message indicating the date is in the future and invalid.
     */
    public void invalidFutureDatesMessage() {
        System.out.println("\nNão é permitido inserir datas futuras.");
    } // Exibe uma mensagem indicando que não é permitido inserir datas no futuro

    /**
     * Displays a message indicating the year is outside the valid period.
     *
     * @param a The invalid year value.
     */
    public void invalidYearPeriodMessage(int a) {
        System.out.println(String.format("\nInválido. O ano de lançamento do filme é %d.", a));
    } // Exibe uma mensagem indicando que o ano de lançamento informado é inválido

    /**
     * Displays a message indicating no movie was found.
     */
    public void noMovieFoundMessage() {
        System.out.println("\nNenhum filme encontrado.");
    } // Exibe uma mensagem indicando que nenhum filme foi encontrado

    /**
     * Displays a message indicating an invalid value for the watched status.
     */
    public void wrongWatchedMessage() {
        System.out.println("\nNão pode dizer que não assistiu um filme que já marcou como assistido.");
    } // Exibe uma mensagem indicando que não é possível marcar como não assistido um filme já marcado como assistido

    /**
     * Shows a message for the updated watched status.
     */
    public void updatedWatchedMessage() {
        System.out.println("\nSituação de visualização atualizada.");
    } // Exibe uma mensagem indicando que a situação de visualização do filme foi atualizada

    /**
     * Shows a message for an invalid score.
     */
    public void invalidScoreMessage() {
        System.out.println("\nO valor precisa estar entre 1 e 5.");
    } // Exibe uma mensagem indicando que o valor da avaliação precisa estar entre 1 e 5
}