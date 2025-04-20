package culturaldiary.series.model;

import culturaldiary.series.season.model.SeasonModel;

import java.util.ArrayList;

public class SeriesModel {
    static private int seriesCounter = 1;

    private String title;
    private int yearOfRelease;
    private int yearOfConclusion;
    private String originalTitle;
    private String whereToWatch;
    private ArrayList<SeasonModel> listOfSeasons;
    private float seriesReview;
    private int seriesIndex;

    public SeriesModel(String title, int yearOfRelease, int yearOfConclusion,
                       String originalTitle, String whereToWatch, ArrayList<SeasonModel> listOfSeasons) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.yearOfConclusion = yearOfConclusion;
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
        this.listOfSeasons = listOfSeasons;
        this.seriesReview = 0;
        this.seriesIndex = seriesCounter++;
    }

    public static int getSeriesCounter() {
        return seriesCounter;
    }

    public static void setSeriesCounter(int seriesCounter) {
        SeriesModel.seriesCounter = seriesCounter;
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