package culturaldiary.series.season;

/**
 * View class for displaying season-related information to the user.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.0
 */
public class SeasonView {
    /**
     * Displays a message indicating that the given field has an empty value.
     *
     * @param name The name of the field with the empty value.
     */
    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio.");
    } // Exibe mensagem de erro quando um valor não é fornecido

    /**
     * Displays a message indicating an invalid cast value.
     */
    public void invalidCastMessage() {
        System.out.println("\nNo elenco só pode conter letras, números e vírgulas.");
    } // Exibe mensagem de erro caso o elenco contenha caracteres inválidos

    /**
     * Displays a message indicating that the value must be an integer.
     */
    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    } // Exibe mensagem de erro caso o valor do ano não seja um número inteiro

    /**
     * Displays a message indicating an invalid year.
     *
     * @param currentYear The current valid year used as a reference.
     */
    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    } // Exibe mensagem de erro caso o ano não esteja dentro do intervalo permitido

    /**
     * Displays a message indicating an invalid watched status.
     */
    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    } // Exibe mensagem de erro caso a resposta para "visualização" não seja válida

    /**
     * Displays a message prompting the user to try again.
     */
    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    } // Exibe mensagem de erro solicitando que o usuário tente novamente

    /**
     * Displays an error message related to a specific season.
     *
     * @param index The index or number of the season with the error.
     */
    public void errorMessageInSeason(int index) {
        System.out.println("\n" + index + "° temporada incorreta.");
    } // Exibe mensagem de erro indicando que a temporada especificada está incorreta

    /**
     * Displays the full information of the given season.
     *
     * @param season The SeasonModel whose information will be displayed.
     */
    public void fullSeasonInformation(SeasonModel season) {
        int index = season.getSeasonIndex();

        // Exibe o número da temporada, gênero, elenco e ano de lançamento
        System.out.printf("| %-23s -> %s\n", "Temporada n°", index);
        System.out.printf("| %-23s -> %s\n", "Gênero", season.getGenre());
        System.out.printf("| %-23s -> %s\n", "Elenco", season.getCastAsString());
        System.out.printf("| %-23s -> %d\n", "Ano de lançamento", season.getYearSeason());

        // Se a temporada não foi avaliada, exibe "Temporada não avaliada", caso contrário exibe a avaliação
        if (season.getSeasonReview() == null) {
            System.out.printf("| %-23s -> %s\n", "Avaliação", "Temporada não avaliada");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-23s -> %.2f\n", "Nota", season.getSeasonReview().getScore());
            System.out.printf("| %-23s -> %s\n", "Data de visualização", season.getSeasonReview().getConsumptionDate());
            System.out.printf("| %-23s -> %s\n", "Comentários", season.getSeasonReview().getComment());
            System.out.println("+-----------------------+");
        }
    } // Exibe as informações completas sobre a temporada, incluindo a avaliação, se disponível
}