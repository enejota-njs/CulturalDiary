package culturaldiary.series.series;

public class SeriesView {
    static final String BOLD = "\033[1m";
    static final String RESET = "\033[0m";

    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio.");
    }

    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    }

    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    }

    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    }

    public void invalidYearsMessage() {
        System.out.println("\nO ano de conclusão não pode ser anterior ao ano de lançamento.");
    }

    public void emptyCastMessage(int index) {
        System.out.println("\nO elenco da " + index + "° temporada está vazio.");
    }

    public void invalidSeasonYearMessage(int index) {
        System.out.println("\nO ano da " + index + "° temporada está fora do período da série");
    }

    public void registeredSeriesMessage(String title) {
        System.out.println("\n" + title + " cadastrado.");
    }

    public void invalidMessage() {
        System.out.println("\nInválido.");
    }

    public void emptyInformationMessage() {
        System.out.println("\nA informação está vazia.");
    }

    public String centerHeader(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

    public void headerForSeries() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-45s | %-12s | %-12s | %-12s | %-12s |\n",
                BOLD + centerHeader("Índice", 8) + RESET,
                BOLD + centerHeader("Título", 45) + RESET,
                BOLD + centerHeader("Início", 12)+ RESET,
                BOLD + centerHeader("Fim", 12) + RESET,
                BOLD + centerHeader("Temporadas", 12) + RESET,
                BOLD + centerHeader("Nota média", 12) + RESET);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    public void seriesInformation(SeriesModel series) {
        String temporaryIndex = Integer.toString(series.getSeriesIndex());
        String temporaryTitle = series.getTitle();
        float temporaryReview = series.getSeriesReview();

        if (temporaryIndex.length() > 8) {
            temporaryIndex = temporaryIndex.substring(0,  5) + "...";
        }

        if (temporaryTitle.length() > 45) {
            temporaryTitle = temporaryTitle.substring(0,  42) + "...";
        }

        String temporaryScore;
        if (temporaryReview == 0) {
            temporaryScore = "Vazio";
        }
        else {
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

    }

    public void invalidDateFormatMessage() {
        System.out.println("\nFormato de data inválida.");
    }

    public void invalidDateMessage() {
        System.out.println("\nData inválida.");
    }

    public void invalidFutureDatesMessage() {
        System.out.println("\nNão é permitido inserir datas futuras.");
    }

    public void invalidYearPeriodMessage(int a) {
        System.out.println(String.format("\nInválido. O ano de lançamento da temporada é %d.", a));
    }

    public void unwatchedSeasonMessage() {
        System.out.println("\nEssa temporada ainda não foi assistida.");
    }

    public void messageOfSeasonAlreadyEvaluated() {
        System.out.println("\nEssa temporada já foi avaliada.");
    }

    public void unratedSeasonMessage() {
        System.out.println("\nEssa temporada ainda não foi avaliada.");
    }

    public void nonExistentDateMessage() {
        System.out.println("\nData inexistente.");
    }

    public void registeredEvaluationMessage() {
        System.out.println("\nAvaliação cadastrada.");
    }

    public void noSeriesFoundMessage() {
        System.out.println("\nNenhuma série encontrada.");
    }

    public void invalidScoreMessage() {
        System.out.println("\nO valor precisa estar entre 1 e 5.");
    }

    public void invalidNumberMessage() {
        System.out.println("\nPor favor, digite um número válido.");
    }

    public void noSeasonFoundMessage() {
        System.out.println("\nNenhuma temporada encontrada.");
    }

    public void wrongWatchedMessage() {
        System.out.println("\nNão pode dizer que não assistiu um temporada que já marcou como assistida.");
    }

    public void updatedWatchedMessage() {
        System.out.println("\nSituação de visualização atualizada.");
    }

    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    }

    public void emptyListMessage() {
        System.out.println("\nA lista de séries está vazia.");
    }

    public void emptyEvaluatedListMessage() {
        System.out.println("\nA lista de séries avaliadas está vazia.");
    }

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
        }
        else {
            System.out.printf("| %-23s -> %f\n", "Nota média", series.getSeriesReview());
        }
        System.out.println("+-----------------------+");
    }
}
