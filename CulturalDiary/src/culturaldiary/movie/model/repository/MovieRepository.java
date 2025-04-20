package culturaldiary.movie.model.repository;

import culturaldiary.movie.model.MovieModel;

import java.util.ArrayList;

public class MovieRepository {
    private ArrayList<MovieModel> listOfMovies = new ArrayList<MovieModel>();

    public void addMovie(MovieModel movie) {
        listOfMovies.add(movie);
    }

    public ArrayList<MovieModel> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(ArrayList<MovieModel> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }
}