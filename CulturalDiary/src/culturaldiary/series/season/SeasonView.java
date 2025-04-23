package culturaldiary.series.season;

public class SeasonView {
    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio.");
    } // Exibe mensagem de erro quando um valor não é fornecido

    public void invalidCastMessage() {
        System.out.println("\nNo elenco só pode conter letras, números e vírgulas.");
    } // Exibe mensagem de erro caso o elenco contenha caracteres inválidos

    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    } // Exibe mensagem de erro caso o valor do ano não seja um número inteiro

    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    } // Exibe mensagem de erro caso o ano não esteja dentro do intervalo permitido

    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    } // Exibe mensagem de erro caso a resposta para "visualização" não seja válida

    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    } // Exibe mensagem de erro solicitando que o usuário tente novamente

    public void errorMessageInSeason(int index) {
        System.out.println("\n" + index + "° temporada incorreta.");
    } // Exibe mensagem de erro indicando que a temporada especificada está incorreta

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