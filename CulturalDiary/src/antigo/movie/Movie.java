package antigo.movie;

import antigo.media.MediaReview;

import java.util.ArrayList;
import java.util.Scanner;

public class Movie {
    private static int movieCounter = 1;

    private String title;
    private String genre;
    private int yearOfRelease;
    private String durationTime;
    private String direction;
    private String screenplay;
    private ArrayList<String> cast;
    private String originalTitle;
    private String whereToWatch;
    private MediaReview movieReview;
    private boolean evaluatedMovie;
    private int movieIndex;

    Scanner input = new Scanner(System.in);

    public Movie(String title, String genre, int yearOfRelease, String durationTime,
                 String direction, String screenplay, ArrayList<String> cast,
                 String originalTitle, String whereToWatch) {
        this.title = title;
        this.genre = genre;
        this.yearOfRelease = yearOfRelease;
        this.durationTime = durationTime;
        this.direction = direction;
        this.screenplay = screenplay;
        this.cast = cast;
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
        this.movieReview = null;
        this.evaluatedMovie = false;
        this.movieIndex = movieCounter++;
    }

    public void displayInformation() {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Filme n°", this.getMovieIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Título", this.getTitle());
        System.out.printf("| %-20s -> %s\n", "Gênero", this.getGenre());
        System.out.printf("| %-20s -> %d\n", "Ano de Lançamento", this.getYearOfRelease());
        System.out.printf("| %-20s -> %s\n", "Tempo de duração", this.getDurationTime());
        System.out.printf("| %-20s -> %s\n", "Direção", this.getDirection());
        System.out.printf("| %-20s -> %s\n", "Roteiro", this.getScreenplay());
        System.out.printf("| %-20s -> %s\n", "Elenco", this.getCastAsString());
        System.out.printf("| %-20s -> %s\n", "Título original", this.getOriginalTitle());
        System.out.printf("| %-20s -> %s\n", "Onde assitir", this.getWhereToWatch());
        System.out.println("+-----------------------+");
    }

    public void displayReview() {
        if (movieReview == null) {
            System.out.println("+-----------------------+");
            System.out.printf("| %-20s -> %s\n", "Avaliação", "Filme não avaliado");
            System.out.println("+-----------------------+");
        } else {
            System.out.println("+-----------------------+");
            System.out.printf("| %-20s -> %s\n", "Já foi assistido?", movieReview.isConsumed() ? "Sim" : "Não");
            System.out.printf("| %-20s -> %.2f\n", "Nota", movieReview.getScore());
            System.out.printf("| %-20s -> %s\n", "Data de visualização", movieReview.getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", movieReview.getComment());
            System.out.println("+-----------------------+");
        }
        System.out.println("\n##################################################");
    }

    public void ckeckMovieReview() {

        if (!this.evaluatedMovie) {
            System.out.print("\nVocê já assistiu esse filmes ? ");
            String watched = input.nextLine().trim();

            if (watched.equalsIgnoreCase("s")
                    || watched.equalsIgnoreCase("sim")
                    || watched.equalsIgnoreCase("assisti")
                    || watched.equalsIgnoreCase("ja")
                    || watched.equalsIgnoreCase("já")) {
                evaluateMovie();
            }
            else {
                System.out.println("\n+----------------------------------+");
                System.out.println("| Esse filme não pode ser avaliado |");
                System.out.println("+----------------------------------+");
            }
        } else {
            System.out.println("\nEsse filme já foi avaliado");
            System.out.print("Deseja avaliar novamente ? ");
            String evaluateAgain = input.nextLine().trim();

            if (evaluateAgain.equalsIgnoreCase("s")
                    || evaluateAgain.equalsIgnoreCase("sim")
                    || evaluateAgain.equalsIgnoreCase("quero")) {
                evaluateMovie();
            } else if (evaluateAgain.equalsIgnoreCase("n")
                    || evaluateAgain.equalsIgnoreCase("não")
                    || evaluateAgain.equalsIgnoreCase("nao")
                    || evaluateAgain.equalsIgnoreCase("não quero")
                    || evaluateAgain.equalsIgnoreCase("nao quero")) {
                return;
            }
            else {
                System.out.println("\n+----------+");
                System.out.println("| Inválido |");
                System.out.println("+----------+");
                ckeckMovieReview();
            }
        }
    }

    public void evaluateMovie() {
        float temporaryScore;
        String temporaryConsumptionDate;
        String temporaryComment;

        while (true) {
            System.out.print("\nDê uma nota entre 1 e 5: ");
            temporaryScore = input.nextFloat();
            input.nextLine();

            if (temporaryScore < 1 || temporaryScore > 5) {
                System.out.println("\n+-----------------------------------+");
                System.out.println("| O valor precisa estar entre 1 e 5 |");
                System.out.println("+-----------------------------------+");
                continue;
            }
            break;
        }

        System.out.print("Data de visualização: ");
        temporaryConsumptionDate = input.nextLine();

        System.out.print("Comentários: ");
        temporaryComment = input.nextLine();

        System.out.println("\n+----------------------+");
        System.out.println("| Avaliação cadastrada |");
        System.out.println("+----------------------+");

        movieReview = new MediaReview(true, temporaryScore, temporaryConsumptionDate, temporaryComment);
        this.evaluatedMovie = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getScreenplay() {
        return screenplay;
    }

    public void setScreenplay(String screenplay) {
        this.screenplay = screenplay;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public String getCastAsString() {
        return String.join(", ", cast);
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getWhereToWatch() {
        return whereToWatch;
    }

    public void setWhereToWatch(String whereToWatch) {
        this.whereToWatch = whereToWatch;
    }

    public MediaReview getMovieReview() {
        return movieReview;
    }

    public void setMovieReview(MediaReview movieReview) {
        this.movieReview = movieReview;
    }

    public static int getMovieCounter() {
        return movieCounter;
    }

    public static void setMovieCounter(int movieCounter) {
        Movie.movieCounter = movieCounter;
    }

    public boolean isEvaluatedMovie() {
        return evaluatedMovie;
    }

    public void setEvaluatedMovie(boolean evaluatedMovie) {
        this.evaluatedMovie = evaluatedMovie;
    }

    public int getMovieIndex() {
        return movieIndex;
    }

    public void setMovieIndex(int movieIndex) {
        this.movieIndex = movieIndex;
    }
}
