package culturaldiary.series.season.model;

import culturaldiary.media.model.ReviewModel;

import java.util.ArrayList;

public class SeasonModel {
    private String genre;
    private ArrayList<String> cast;
    private int yearSeason;
    private ReviewModel seasonReview;
    private boolean evaluatedSeason;
    private int seasonIndex;

    public SeasonModel(String genre, ArrayList<String> cast, int yearSeason, int seasonIndex) {
        this.genre = genre;
        this.cast = cast;
        this.yearSeason = yearSeason;
        this.seasonReview = null;
        this.evaluatedSeason = false;
        this.seasonIndex = seasonIndex;
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

    public int getYearSeason() {
        return yearSeason;
    }

    public void setYearSeason(int yearSeason) {
        this.yearSeason = yearSeason;
    }

    public ReviewModel getSeasonReview() {
        return seasonReview;
    }

    public void setSeasonReview(ReviewModel seasonReview) {
        this.seasonReview = seasonReview;
    }

    public boolean isEvaluatedSeason() {
        return evaluatedSeason;
    }

    public void setEvaluatedSeason(boolean evaluatedSeason) {
        this.evaluatedSeason = evaluatedSeason;
    }

    public int getSeasonIndex() {
        return seasonIndex;
    }

    public void setSeasonIndex(int seasonIndex) {
        this.seasonIndex = seasonIndex;
    }
}
