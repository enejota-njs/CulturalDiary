package culturaldiary.series.series;

/**
 * View class for displaying series-related information to the user.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.1
 */
public class SeriesView {
    static final String BOLD = "\033[1m";
    static final String RESET = "\033[0m";

    /**
     * Displays a message indicating that the specified field is empty.
     *
     * @param name The name of the field that is empty.
     */
    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio.");
    } // Exibe uma mensagem informando que o valor não pode ser vazio

    /**
     * Displays a message indicating that the input must be an integer value.
     */
    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    } // Exibe uma mensagem informando que o valor do ano deve ser um número inteiro

    /**
     * Displays a message indicating that the entered year is invalid.
     *
     * @param currentYear The current year used for validation.
     */
    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    } // Exibe uma mensagem informando que o ano precisa estar dentro de um intervalo válido

    /**
     * Displays a message prompting the user to try again.
     */
    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    } // Exibe uma mensagem sugerindo que o usuário tente novamente

    /**
     * Displays a message indicating that the start year cannot be greater than the end year.
     */
    public void invalidYearsMessage() {
        System.out.println("\nO ano de conclusão não pode ser anterior ao ano de lançamento.");
    } // Exibe uma mensagem informando que o ano de conclusão não pode ser antes do ano de lançamento

    /**
     * Displays a message indicating that the cast member at the specified index is empty.
     *
     * @param index The index of the cast member that is empty.
     */
    public void emptyCastMessage(int index) {
        System.out.println("\nO elenco da " + index + "° temporada está vazio.");
    } // Exibe uma mensagem informando que o elenco da temporada especificada está vazio

    /**
     * Displays a message indicating that the year of the season at the specified index is invalid.
     *
     * @param index The index of the season with the invalid year.
     */
    public void invalidSeasonYearMessage(int index) {
        System.out.println("\nO ano da " + index + "° temporada está fora do período da série");
    } // Exibe uma mensagem informando que o ano da temporada está fora do período da série

    /**
     * Displays a message confirming that the series with the specified title has been registered.
     *
     * @param title The title of the registered series.
     */
    public void registeredSeriesMessage(String title) {
        System.out.println("\n" + title + " cadastrado.");
    } // Exibe uma mensagem confirmando o cadastro da série

    /**
     * Displays a message indicating that the input is invalid.
     */
    public void invalidMessage() {
        System.out.println("\nInválido.");
    } // Exibe uma mensagem genérica de erro, informando que a entrada foi inválida

    /**
     * Displays a message indicating that required information is missing or empty.
     */
    public void emptyInformationMessage() {
        System.out.println("\nA informação está vazia.");
    } // Exibe uma mensagem informando que a informação está vazia

    /**
     * Centers the given text within a field of the specified width.
     *
     * @param text The text to be centered.
     * @param width The total width of the field.
     * @return A string with the text centered within the specified width.
     */
    public String centerHeader(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        return " ".repeat(left) + text + " ".repeat(right);
    } // Centraliza o texto com base na largura fornecida, adicionando espaços à esquerda e à direita

    /**
     * Displays the header for the series section.
     */
    public void headerForSeries() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-45s | %-12s | %-12s | %-12s | %-12s |\n",
                BOLD + centerHeader("Índice", 8) + RESET,
                BOLD + centerHeader("Título", 45) + RESET,
                BOLD + centerHeader("Início", 12) + RESET,
                BOLD + centerHeader("Fim", 12) + RESET,
                BOLD + centerHeader("Temporadas", 12) + RESET,
                BOLD + centerHeader("Nota média", 12) + RESET);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    } // Exibe o cabeçalho formatado com os nomes das colunas para a lista de séries

    /**
     * Displays information about the specified series.
     *
     * @param series The series object containing the information to display.
     */
    public void seriesInformation(SeriesModel series) {
        String temporaryIndex = Integer.toString(series.getSeriesIndex());
        String temporaryTitle = series.getTitle();
        float temporaryReview = series.getSeriesReview();

        if (temporaryIndex.length() > 8) {
            temporaryIndex = temporaryIndex.substring(0, 5) + "...";
        }

        if (temporaryTitle.length() > 45) {
            temporaryTitle = temporaryTitle.substring(0, 42) + "...";
        }

        String temporaryScore;
        if (temporaryReview == 0) {
            temporaryScore = "Vazio";
        } else {
            temporaryScore = String.valueOf(temporaryReview);
        }

        int number;
        if (series.getListOfSeasons() != null) {
            number = series.getListOfSeasons().size();
        } else {
            number = 0;
        }

        System.out.printf("| %-8s | %-45s | %-12d | %-12d | %-12d | %-12s |\n", temporaryIndex, temporaryTitle,
                series.getYearOfRelease(), series.getYearOfConclusion(), number, temporaryScore);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    } // Exibe as informações detalhadas de uma série formatadas em uma linha

    /**
     * Displays a message indicating that the date format is invalid.
     */
    public void invalidDateFormatMessage() {
        System.out.println("\nFormato de data inválida.");
    } // Exibe uma mensagem informando que o formato da data está inválido

    /**
     * Displays a message indicating that the entered date is invalid.
     */
    public void invalidDateMessage() {
        System.out.println("\nData inválida.");
    } // Exibe uma mensagem informando que a data é inválida

    /**
     * Displays a message indicating that future dates are not allowed.
     */
    public void invalidFutureDatesMessage() {
        System.out.println("\nNão é permitido inserir datas futuras.");
    } // Exibe uma mensagem informando que não é permitido inserir datas futuras

    /**
     * Displays a message indicating that the provided year period is invalid.
     *
     * @param a The year period value to validate.
     */
    public void invalidYearPeriodMessage(int a) {
        System.out.println(String.format("\nInválido. O ano de lançamento da temporada é %d.", a));
    } // Exibe uma mensagem informando que o ano de lançamento da temporada é inválido, com base no ano fornecido

    /**
     * Displays a message indicating that the season has not been watched yet.
     */
    public void unwatchedSeasonMessage() {
        System.out.println("\nEssa temporada ainda não foi assistida.");
    } // Exibe uma mensagem informando que a temporada ainda não foi assistida

    /**
     * Displays a message indicating that the season has already been evaluated.
     */
    public void messageOfSeasonAlreadyEvaluated() {
        System.out.println("\nEssa temporada já foi avaliada.");
    } // Exibe uma mensagem informando que a temporada já foi avaliada

    /**
     * Displays a message indicating that the season has not been rated yet.
     */
    public void unratedSeasonMessage() {
        System.out.println("\nEssa temporada ainda não foi avaliada.");
    } // Exibe uma mensagem informando que a temporada ainda não foi avaliada

    /**
     * Displays a message indicating that the entered date does not exist.
     */
    public void nonExistentDateMessage() {
        System.out.println("\nData inexistente.");
    } // Exibe uma mensagem informando que a data é inexistente

    /**
     * Displays a message confirming that the evaluation has been registered.
     */
    public void registeredEvaluationMessage() {
        System.out.println("\nAvaliação cadastrada.");
    } // Exibe uma mensagem informando que a avaliação foi cadastrada com sucesso

    /**
     * Displays a message indicating that no series were found.
     */
    public void noSeriesFoundMessage() {
        System.out.println("\nNenhuma série encontrada.");
    } // Exibe uma mensagem informando que nenhuma série foi encontrada

    /**
     * Displays a message indicating that the provided score is invalid.
     */
    public void invalidScoreMessage() {
        System.out.println("\nO valor precisa estar entre 1 e 5.");
    } // Exibe uma mensagem informando que o valor da avaliação deve estar entre 1 e 5

    /**
     * Displays a message indicating that the entered number is invalid.
     */

    public void invalidNumberMessage() {
        System.out.println("\nPor favor, digite um número válido.");
    } // Exibe uma mensagem informando que o valor inserido não é um número válido

    /**
     * Displays a message indicating that no season was found.
     */
    public void noSeasonFoundMessage() {
        System.out.println("\nNenhuma temporada encontrada.");
    } // Exibe uma mensagem informando que nenhuma temporada foi encontrada

    /**
     * Displays a message indicating that a watched season cannot be marked as unwatched.
     */
    public void wrongWatchedMessage() {
        System.out.println("\nNão pode dizer que não assistiu uma temporada que já marcou como assistida.");
    } // Exibe uma mensagem informando que não é possível indicar que não assistiu uma temporada já marcada como assistida

    /**
     * Displays a message confirming that the watched status has been updated.
     */
    public void updatedWatchedMessage() {
        System.out.println("\nSituação de visualização atualizada.");
    } // Exibe uma mensagem informando que a situação de visualização foi atualizada

    /**
     * Displays a message indicating that the watched input is invalid.
     */
    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    } // Exibe uma mensagem informando que a resposta de visualização deve ser "Sim" ou "Não"

    /**
     * Displays a message indicating that the list is empty.
     */
    public void emptyListMessage() {
        System.out.println("\nA lista de séries está vazia.");
    } // Exibe uma mensagem informando que a lista de séries está vazia

    /**
     * Displays a message indicating that the evaluated list is empty.
     */
    public void emptyEvaluatedListMessage() {
        System.out.println("\nA lista de séries avaliadas está vazia.");
    } // Exibe uma mensagem informando que a lista de séries avaliadas está vazia

    /**
     * Displays detailed information about the specified series.
     *
     * @param series The series object containing the detailed information to display.
     */
    public void fullSeriesInformation(SeriesModel series) {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-23s -> %s\n", "Série n°", series.getSeriesIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-23s -> %s\n", "Título", series.getTitle());
        System.out.printf("| %-23s -> %d\n", "Ano de lançamento", series.getYearOfRelease());
        System.out.printf("| %-23s -> %d\n", "Ano da última temporada", series.getYearOfConclusion());
        System.out.printf("| %-23s -> %s\n", "Título original", series.getOriginalTitle());
        System.out.printf("| %-23s -> %s\n", "Onde assitir", series.getWhereToWatch());
        System.out.printf("| %-23s -> %d\n", "Número de temporadas", series.getListOfSeasons().size());

        if (series.getSeriesReview() == 0) {
            System.out.printf("| %-23s -> %s\n", "Nota média", "Série não avaliada");
        } else {
            System.out.printf("| %-23s -> %f\n", "Nota média", series.getSeriesReview());
        }
        System.out.println("+-----------------------+");
    } // Exibe informações detalhadas sobre a série, incluindo título, ano de lançamento, número de temporadas, e nota média
}