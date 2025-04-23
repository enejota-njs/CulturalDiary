package culturaldiary.series.season;

import culturaldiary.review.ReviewModel;

import java.util.ArrayList;

public class SeasonModel {
    private String genre; // Gênero
    private ArrayList<String> cast; // Elenco
    private int yearSeason; // Ano de lançamento
    private ReviewModel seasonReview; // Avaliação
    private boolean evaluatedSeason; // Temporada avaliada
    private boolean watched; // Assistido
    private int seasonIndex; // Índice da temporada

    public SeasonModel(String genre, ArrayList<String> cast, int yearSeason, boolean watched, int seasonIndex) {
        this.genre = genre;
        this.cast = cast;
        this.yearSeason = yearSeason;
        this.seasonReview = null;
        this.evaluatedSeason = false;
        this.watched = watched;
        this.seasonIndex = seasonIndex;
    } // Construtor

    // Métodos Getters e Setters
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

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public int getSeasonIndex() {
        return seasonIndex;
    }

    public void setSeasonIndex(int seasonIndex) {
        this.seasonIndex = seasonIndex;
    }
}
