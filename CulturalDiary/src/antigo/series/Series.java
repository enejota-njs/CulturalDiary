package antigo.series;

import java.util.ArrayList;

public class Series {
    static private int seriesCounter = 1;

    private String title;
    private int yearOfRelease;
    private int yearOfConclusion;
    private String originalTitle;
    private String whereToWatch;
    private ArrayList<Season> listOfSeasons;
    private float seriesReview;
    private int seriesIndex;

    public Series(String title, int yearOfRelease, int yearOfConclusion, String originalTitle,
                  String whereToWatch, ArrayList<Season> listOfSeasons) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.yearOfConclusion = yearOfConclusion;
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
        this.listOfSeasons = listOfSeasons;
        this.seriesReview = -1;
        this.seriesIndex = seriesCounter++;
    }

    /*public void displayInformation() {
        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Série n°", this.getSeriesIndex());
        System.out.println("+-----------------------+");
        System.out.printf("| %-20s -> %s\n", "Título", this.getTitle());
        System.out.printf("| %-20s -> %d\n", "Ano de Lançamento", this.getYearOfRelease());
        System.out.printf("| %-20s -> %d\n", "Ano de Encerramento", this.getYearOfConclusion());
        System.out.printf("| %-20s -> %s\n", "Título original", this.getOriginalTitle());
        System.out.printf("| %-20s -> %s\n", "Onde assitir", this.getWhereToWatch());
        System.out.println("+-----------------------+");

        for (Season s : this.listOfSeasons) {
            s.displayInformation();
            s.displayReview();
        }
    }*/

    public void displayInformation() {
        int count = 0;
        for (Season s : this.listOfSeasons) {
            count++;
        }

        System.out.println("\n+-----------------------+");
        System.out.printf("| %-20s -> %s, %s, %d\n", "Série n° " + this.getSeriesIndex(), this.getTitle(), this.getYearOfRelease(), count);
        System.out.println("+-----------------------+");
    }

    public void displayReview() {
        seriesReview = getSeriesReview();

        if (seriesReview == -1) {
            System.out.println("+-----------------------+");
            System.out.printf("| %-20s -> %s\n", "Avaliação geral", "Série não avaliada");
            System.out.println("+-----------------------+");
        } else {
            System.out.println("+-----------------------+");
            System.out.printf("| %-20s -> %.2f\n", "Nota média", seriesReview);
            System.out.println("+-----------------------+");
        }
        System.out.println("\n##################################################");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public int getYearOfConclusion() {
        return yearOfConclusion;
    }

    public void setYearOfConclusion(int yearOfConclusion) {
        this.yearOfConclusion = yearOfConclusion;
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

    public ArrayList<Season> getListOfSeasons() {
        return listOfSeasons;
    }

    public void setListOfSeasons(ArrayList<Season> listOfSeasons) {
        this.listOfSeasons = listOfSeasons;
    }

    public static int getSeriesCounter() {
        return seriesCounter;
    }

    public static void setSeriesCounter(int seriesCounter) {
        Series.seriesCounter = seriesCounter;
    }

    public float getSeriesReview() {
        float seriesAverage = 0;
        int seasonsWithReview = 0;

        for (Season s : listOfSeasons) {
            if (s.getSeasonReview() != null) {
                float temporaryScore = s.getSeasonReview().getScore();
                seriesAverage += temporaryScore;
                seasonsWithReview++;
            }
        }

        if (seasonsWithReview == 0) {
            seriesReview = -1;
        } else {
            seriesReview = seriesAverage / seasonsWithReview;
        }

        return seriesReview;
    }

    public void setSeriesReview(float seriesReview) {
        this.seriesReview = seriesReview;
    }

    public int getSeriesIndex() {
        return seriesIndex;
    }

    public void setSeriesIndex(int seriesIndex) {
        this.seriesIndex = seriesIndex;
    }
}
