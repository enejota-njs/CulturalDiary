package culturaldiary.media.model;

import culturaldiary.book.controller.BookController;
import culturaldiary.movie.controller.MovieController;
import culturaldiary.series.controller.SeriesController;

public class MediaModel {
    private BookController bookController = new BookController();
    private MovieController movieController = new MovieController();
    private SeriesController seriesController = new SeriesController();

    public SeriesController getSeriesController() {
        return seriesController;
    }

    public void setSeriesController(SeriesController seriesController) {
        this.seriesController = seriesController;
    }

    public MovieController getMovieController() {
        return movieController;
    }

    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    public BookController getBookController() {
        return bookController;
    }

    public void setBookController(BookController bookController) {
        this.bookController = bookController;
    }
}
