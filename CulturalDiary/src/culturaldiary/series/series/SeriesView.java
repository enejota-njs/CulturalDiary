package culturaldiary.series.series;

public class SeriesView {
    static final String BOLD = "\033[1m";
    static final String RESET = "\033[0m";

    public void emptyValueMessage(String name) {
        System.out.println("\n" + name + " não pode ficar vazio.");
    } // Exibe uma mensagem informando que o valor não pode ser vazio

    public void integerMessage() {
        System.out.println("\nO valor do ano precisa ser um número inteiro.");
    } // Exibe uma mensagem informando que o valor do ano deve ser um número inteiro

    public void invalidYearMessage(int currentYear) {
        System.out.println("\nO valor do ano precisa estar entre 1700 e " + currentYear + ".");
    } // Exibe uma mensagem informando que o ano precisa estar dentro de um intervalo válido

    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    } // Exibe uma mensagem sugerindo que o usuário tente novamente

    public void invalidYearsMessage() {
        System.out.println("\nO ano de conclusão não pode ser anterior ao ano de lançamento.");
    } // Exibe uma mensagem informando que o ano de conclusão não pode ser antes do ano de lançamento

    public void emptyCastMessage(int index) {
        System.out.println("\nO elenco da " + index + "° temporada está vazio.");
    } // Exibe uma mensagem informando que o elenco da temporada especificada está vazio

    public void invalidSeasonYearMessage(int index) {
        System.out.println("\nO ano da " + index + "° temporada está fora do período da série");
    } // Exibe uma mensagem informando que o ano da temporada está fora do período da série

    public void registeredSeriesMessage(String title) {
        System.out.println("\n" + title + " cadastrado.");
    } // Exibe uma mensagem confirmando o cadastro da série

    public void invalidMessage() {
        System.out.println("\nInválido.");
    } // Exibe uma mensagem genérica de erro, informando que a entrada foi inválida

    public void emptyInformationMessage() {
        System.out.println("\nA informação está vazia.");
    } // Exibe uma mensagem informando que a informação está vazia

    public String centerHeader(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        return " ".repeat(left) + text + " ".repeat(right);
    } // Centraliza o texto com base na largura fornecida, adicionando espaços à esquerda e à direita

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
    } // Exibe as informações detalhadas de uma série formatadas em uma linha

    public void invalidDateFormatMessage() {
        System.out.println("\nFormato de data inválida.");
    } // Exibe uma mensagem informando que o formato da data está inválido

    public void invalidDateMessage() {
        System.out.println("\nData inválida.");
    } // Exibe uma mensagem informando que a data é inválida

    public void invalidFutureDatesMessage() {
        System.out.println("\nNão é permitido inserir datas futuras.");
    } // Exibe uma mensagem informando que não é permitido inserir datas futuras

    public void invalidYearPeriodMessage(int a) {
        System.out.println(String.format("\nInválido. O ano de lançamento da temporada é %d.", a));
    } // Exibe uma mensagem informando que o ano de lançamento da temporada é inválido, com base no ano fornecido

    public void unwatchedSeasonMessage() {
        System.out.println("\nEssa temporada ainda não foi assistida.");
    } // Exibe uma mensagem informando que a temporada ainda não foi assistida

    public void messageOfSeasonAlreadyEvaluated() {
        System.out.println("\nEssa temporada já foi avaliada.");
    } // Exibe uma mensagem informando que a temporada já foi avaliada

    public void unratedSeasonMessage() {
        System.out.println("\nEssa temporada ainda não foi avaliada.");
    } // Exibe uma mensagem informando que a temporada ainda não foi avaliada

    public void nonExistentDateMessage() {
        System.out.println("\nData inexistente.");
    } // Exibe uma mensagem informando que a data é inexistente

    public void registeredEvaluationMessage() {
        System.out.println("\nAvaliação cadastrada.");
    } // Exibe uma mensagem informando que a avaliação foi cadastrada com sucesso

    public void noSeriesFoundMessage() {
        System.out.println("\nNenhuma série encontrada.");
    } // Exibe uma mensagem informando que nenhuma série foi encontrada

    public void invalidScoreMessage() {
        System.out.println("\nO valor precisa estar entre 1 e 5.");
    } // Exibe uma mensagem informando que o valor da avaliação deve estar entre 1 e 5

    public void invalidNumberMessage() {
        System.out.println("\nPor favor, digite um número válido.");
    } // Exibe uma mensagem informando que o valor inserido não é um número válido

    public void noSeasonFoundMessage() {
        System.out.println("\nNenhuma temporada encontrada.");
    } // Exibe uma mensagem informando que nenhuma temporada foi encontrada

    public void wrongWatchedMessage() {
        System.out.println("\nNão pode dizer que não assistiu uma temporada que já marcou como assistida.");
    } // Exibe uma mensagem informando que não é possível indicar que não assistiu uma temporada já marcada como assistida

    public void updatedWatchedMessage() {
        System.out.println("\nSituação de visualização atualizada.");
    } // Exibe uma mensagem informando que a situação de visualização foi atualizada

    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    } // Exibe uma mensagem informando que a resposta de visualização deve ser "Sim" ou "Não"

    public void emptyListMessage() {
        System.out.println("\nA lista de séries está vazia.");
    } // Exibe uma mensagem informando que a lista de séries está vazia

    public void emptyEvaluatedListMessage() {
        System.out.println("\nA lista de séries avaliadas está vazia.");
    } // Exibe uma mensagem informando que a lista de séries avaliadas está vazia

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
    } // Exibe informações detalhadas sobre a série, incluindo título, ano de lançamento, número de temporadas, e nota média

}
