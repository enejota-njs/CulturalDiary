package culturaldiary.series.season;

public class SeasonView {
    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio.");
    }

    public void invalidCastMessage() {
        System.out.println("\nNo elenco só pode conter letras, números e vírgulas.");
    }

    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    }

    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    }

    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    }

    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    }

    public void errorMessageInSeason(int index) {
        System.out.println("\n" + index + "° temporada incorreta.");
    }

    public void fullSeasonInformation(SeasonModel season) {
        int index = season.getSeasonIndex();

        System.out.printf("| %-23s -> %s\n", "Temporada n°", index);
        System.out.printf("| %-23s -> %s\n", "Gênero", season.getGenre());
        System.out.printf("| %-23s -> %s\n", "Elenco", season.getCastAsString());
        System.out.printf("| %-23s -> %d\n", "Ano de lançamento", season.getYearSeason());

        if (season.getSeasonReview() == null) {
            System.out.printf("| %-23s -> %s\n", "Avaliação", "Temporada não avaliada");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-23s -> %.2f\n", "Nota", season.getSeasonReview().getScore());
            System.out.printf("| %-23s -> %s\n", "Data de visualização", season.getSeasonReview().getConsumptionDate());
            System.out.printf("| %-23s -> %s\n", "Comentários", season.getSeasonReview().getComment());
            System.out.println("+-----------------------+");
        }
    }
}