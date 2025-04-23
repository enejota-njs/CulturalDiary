package culturaldiary.movie;

import java.util.ArrayList;

public class MovieRepository {
    private ArrayList<MovieModel> listOfMovies = new ArrayList<MovieModel>(); // Lista de livros

    public void addMovie(MovieModel movie) {
        listOfMovies.add(movie);
    } // Adiciona um novo livro à lista

    // Método Getter e Setter
    public ArrayList<MovieModel> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(ArrayList<MovieModel> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }
}
