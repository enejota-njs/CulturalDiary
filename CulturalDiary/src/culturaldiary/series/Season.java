package culturaldiary.series;

import culturaldiary.media.MediaReview;

import java.util.ArrayList;
import java.util.Scanner;

public class Season {

    private String genre;
    private ArrayList<String> cast;
    private int yearSeason;
    private MediaReview seasonReview;
    private boolean evaluatedSeason;
    private int seasonIndex;

    Scanner input = new Scanner(System.in);

    public Season(String genre, ArrayList<String> cast, int yearSeason, int seasonIndex) {
        this.genre = genre;
        this.cast = cast;
        this.yearSeason = yearSeason;
        this.seasonIndex = seasonIndex;
        this.seasonReview = null;
        this.evaluatedSeason = false;
    }

    public void displayInformation() {
        System.out.printf("| %-20s -> %s\n", "Temporada n°", this.getSeasonIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Gênero", this.getGenre());
        System.out.printf("| %-20s -> %s\n", "Elenco", this.getCastAsString());
        System.out.printf("| %-20s -> %d\n", "Ano", this.getYearSeason());
        System.out.println("+-----------------------+");
    }

    public void displayReview() {
        if (seasonReview == null) {
            System.out.printf("| %-20s -> %s\n", "Avaliação", "Temporada não avaliada");
            System.out.println("+-----------------------+");
        } else {
            System.out.printf("| %-20s -> %s\n", "Já foi assistido?", seasonReview.isConsumed() ? "Sim" : "Não");
            System.out.printf("| %-20s -> %.2f\n", "Nota", seasonReview.getScore());
            System.out.printf("| %-20s -> %s\n", "Data de visualização", seasonReview.getConsumptionDate());
            System.out.printf("| %-20s -> %s\n", "Comentários", seasonReview.getComment());
            System.out.println("+-----------------------+");
        }
    }

    public void ckeckSeasonReview() {

        if (!this.evaluatedSeason) {
            System.out.print("\nVocê já assistiu essa temporada ? ");
            String watched = input.nextLine().trim();

            if (watched.equalsIgnoreCase("s")
                    || watched.equalsIgnoreCase("sim")
                    || watched.equalsIgnoreCase("assisti")
                    || watched.equalsIgnoreCase("ja")
                    || watched.equalsIgnoreCase("já")) {
                evaluateSeason();
            }
            else {
                System.out.println("\n+--------------------------------------+");
                System.out.println("| Essa temporada não pode ser avaliada |");
                System.out.println("+--------------------------------------+");
            }
        } else {
            System.out.println("\nEssa temporada já foi avaliado");
            System.out.print("Deseja avaliar novamente ? ");
            String evaluateAgain = input.nextLine().trim();

            if (evaluateAgain.equalsIgnoreCase("s")
                    || evaluateAgain.equalsIgnoreCase("sim")
                    || evaluateAgain.equalsIgnoreCase("quero")) {
                evaluateSeason();
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
                ckeckSeasonReview();
            }
        }
    }

    public void evaluateSeason() {
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

        seasonReview = new MediaReview(true, temporaryScore, temporaryConsumptionDate, temporaryComment);
        this.evaluatedSeason = true;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public int getSeasonIndex() {
        return seasonIndex;
    }

    public void setSeasonIndex(int seasonIndex) {
        this.seasonIndex = seasonIndex;
    }

    public MediaReview getSeasonReview() {
        return seasonReview;
    }

    public void setSeasonReview(MediaReview seasonReview) {
        this.seasonReview = seasonReview;
    }

    public boolean isEvaluatedSeason() {
        return evaluatedSeason;
    }

    public void setEvaluatedSeason(boolean evaluatedSeason) {
        this.evaluatedSeason = evaluatedSeason;
    }

    public int getYearSeason() {
        return yearSeason;
    }

    public void setYearSeason(int yearSeason) {
        this.yearSeason = yearSeason;
    }
}
