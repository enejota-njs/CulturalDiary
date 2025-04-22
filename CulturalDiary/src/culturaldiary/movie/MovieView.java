package culturaldiary.movie;

import culturaldiary.review.ReviewModel;

public class MovieView {
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

    public void emptyInformationMessage() {
        System.out.println("\nA informação está vazia.");
    }

    public void invalidTimeMessage() {
        System.out.println("\nHorário inválido.");
    }

    public void invalidWatchedMessage() {
        System.out.println("\nNa pergunta da visualização, digite Sim ou Não.");
    }

    public void tryAgainMessage() {
        System.out.println("\nTente novamente.");
    }

    public void invalidCastMessage() {
        System.out.println("\nNo elenco só pode conter letras, números e vírgulas.");
    }

    public void emptyCastMessage() {
        System.out.println("\nO elenco está vazio.");
    }

    public void invalidMessage() {
        System.out.println("\nInválido.");
    }

    public void registeredMovieMessage(String title) {
        System.out.println("\n" + title + " cadastrado.");
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
    }

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

    }

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
    }

    public void emptyEvaluatedListMessage() {
        System.out.println("\nA lista de filmes avaliados está vazia.");
    }

    public void emptyListMessage() {
        System.out.println("\nA lista de filmes está vazia.");
    }

    public void invalidNumberMessage() {
        System.out.println("\nPor favor, digite um número válido.");
    }

    public void invalidDateFormatMessage() {
        System.out.println("\nFormato de data inválida.");
    }

    public void invalidDateMessage() {
        System.out.println("\nData inválida.");
    }

    public void messageOfMovieAlreadyEvaluated() {
        System.out.println("\nEsse filme já foi avaliado.");
    }

    public void nonExistentDateMessage() {
        System.out.println("\nData inexistente.");
    }

    public void unratedMovieMessage() {
        System.out.println("\nEsse filme ainda não foi avaliado.");
    }

    public void registeredEvaluationMessage() {
        System.out.println("\nAvaliação cadastrada.");
    }

    public void unwatchedMovieMessage() {
        System.out.println("\nEsse filme ainda não foi assistido.");
    }

    public void invalidFutureDatesMessage() {
        System.out.println("\nNão é permitido inserir datas futuras.");
    }

    public void invalidYearPeriodMessage(int a) {
        System.out.println(String.format("\nInválido. O ano de lançamento do filme é %d.", a));
    }

    public void noMovieFoundMessage() {
        System.out.println("\nNenhum filme encontrado.");
    }

    public void wrongWatchedMessage() {
        System.out.println("\nNão pode dizer que não assistiu um filme que já marcou como assistido.");
    }

    public void updatedWatchedMessage() {
        System.out.println("\nSituação de visualização atualizada.");
    }

    public void invalidScoreMessage() {
        System.out.println("\nO valor precisa estar entre 1 e 5.");
    }
}