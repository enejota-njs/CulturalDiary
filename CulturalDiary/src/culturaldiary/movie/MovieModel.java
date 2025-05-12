package culturaldiary.movie;

import culturaldiary.review.ReviewModel;

import java.util.ArrayList;

/**
 * Model class representing a movie.
 * Contains attributes and methods related to a movie entity.
 *
 * @author Nathan de Jesus dos Santos
 * @version 1.0
 */
public class MovieModel {
    private static int movieCounter = 1;

    private String title; // Título do filme
    private String genre; // Gênero do filme
    private int yearOfRelease; // Ano de lançamento
    private String durationTime; // Duração do filme
    private String direction; // Diretor do filme
    private String screenplay; // Roteirista do filme
    private ArrayList<String> cast; // Elenco do filme
    private String originalTitle; // Título original
    private String whereToWatch; // Onde assistir
    private int movieIndex; // Índice do filme
    private ReviewModel movieReview; // Avaliação do filme
    private boolean evaluatedMovie; // Se o filme foi avaliado
    private boolean watched; // Se o filme foi assistido


    public MovieModel(String title, String genre, int yearOfRelease, String durationTime,
                      String direction, String screenplay, ArrayList<String> cast,
                      String originalTitle, String whereToWatch, boolean watched) {
        this.title = title;
        this.genre = genre;
        this.yearOfRelease = yearOfRelease;
        this.durationTime = durationTime;
        this.direction = direction;
        this.screenplay = screenplay;
        this.cast = cast;
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
        this.movieIndex = movieCounter++;
        this.movieReview = null;
        this.evaluatedMovie = false;
        this.watched = watched;
    } // Constutor

    // Métodos Getters e Setters
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

    public int getMovieIndex() {
        return movieIndex;
    }

    public void setMovieIndex(int movieIndex) {
        this.movieIndex = movieIndex;
    }

    public ReviewModel getMovieReview() {
        return movieReview;
    }

    public void setMovieReview(ReviewModel movieReview) {
        this.movieReview = movieReview;
    }

    public boolean isEvaluatedMovie() {
        return evaluatedMovie;
    }

    public void setEvaluatedMovie(boolean evaluatedMovie) {
        this.evaluatedMovie = evaluatedMovie;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
