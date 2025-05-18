package culturaldiary.series.series;

import culturaldiary.series.season.SeasonModel;

import java.util.ArrayList;

/**
 * Model class representing a series.
 * Contains attributes and methods related to a series.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.0
 */

public class SeriesModel {
    private String title; // Título
    private int yearOfRelease; // Ano de lançamento
    private int yearOfConclusion; // Ano de conclusão
    private String originalTitle; // Título original
    private String whereToWatch; // Onde assisti
    private ArrayList<SeasonModel> listOfSeasons; // Lista de temporadas
    private float seriesReview; // Nota média
    private int seriesIndex; // Índice da série

    public SeriesModel(String title, int yearOfRelease, int yearOfConclusion, String originalTitle, String whereToWatch, ArrayList<SeasonModel> listOfSeasons, int seriesIndex) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.yearOfConclusion = yearOfConclusion;
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
        this.listOfSeasons = listOfSeasons;
        this.seriesReview = 0;
        this.seriesIndex = seriesIndex;
    } // Construtor

    // Métodos Getters e Setters
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

    public ArrayList<SeasonModel> getListOfSeasons() {
        return listOfSeasons;
    }

    public void setListOfSeasons(ArrayList<SeasonModel> listOfSeasons) {
        this.listOfSeasons = listOfSeasons;
    }

    public float getSeriesReview() {
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
