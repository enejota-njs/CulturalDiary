package series.season;

import review.ReviewModel;

import java.util.ArrayList;

/**
 * Model class representing a season.
 * Contains attributes and methods related to the series season.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.1
 */
public class SeasonModel {
    private String genre; // Gênero
    private ArrayList<String> cast; // Elenco
    private String castAsString;
    private int yearSeason; // Ano de lançamento
    private ReviewModel seasonReview; // Avaliação
    private boolean evaluatedSeason; // Temporada avaliada
    private boolean watched; // Assistido
    private int seasonIndex; // Índice da temporada

    /**
     * Creates a SeasonModel with genre, cast, year, watched status, and index.
     *
     * @param genre The genre of the season.
     * @param cast The list of cast members.
     * @param yearSeason The release year of the season.
     * @param watched Indicates if the season has been watched.
     * @param seasonIndex The index or number of the season.
     */
    public SeasonModel(String genre, ArrayList<String> cast, int yearSeason, boolean watched, int seasonIndex) {
        this.genre = genre;
        this.cast = cast;
        this.yearSeason = yearSeason;
        this.seasonReview = null;
        this.evaluatedSeason = false;
        this.watched = watched;
        this.seasonIndex = seasonIndex;
        setCastAsString();
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

    public void setCastAsString() {
        this.castAsString = String.join(", ", this.cast);
    }

    public String getCastAsString() {
        return this.castAsString;
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
