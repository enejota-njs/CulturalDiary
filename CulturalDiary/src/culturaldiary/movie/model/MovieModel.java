package culturaldiary.movie.model;

import culturaldiary.media.model.ReviewModel;

import java.util.ArrayList;

public class MovieModel {
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
    private int movieIndex;
    private ReviewModel movieReview;
    private boolean evaluatedMovie;


    public MovieModel(String title, String genre, int yearOfRelease, String durationTime,
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
        this.movieIndex = movieCounter++;
        this.movieReview = null;
        this.evaluatedMovie = false;
    }

    public static int getMovieCounter() {
        return movieCounter;
    }

    public static void setMovieCounter(int movieCounter) {
        MovieModel.movieCounter = movieCounter;
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
}
