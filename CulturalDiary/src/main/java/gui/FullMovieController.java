package gui;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import movie.MovieModel;

import java.io.IOException;

public class FullMovieController {
    @FXML
    private TextField title;

    @FXML
    private TextField genre;

    @FXML
    private TextField yearOfRelease;

    @FXML
    private TextField durationTime;

    @FXML
    private TextField direction;

    @FXML
    private TextArea screenplay;

    @FXML
    private TextField cast;

    @FXML
    private TextField originalTitle;

    @FXML
    private TextField whereToWatch;

    @FXML
    private TextField score;

    @FXML
    private TextField consumptionDate;

    @FXML
    private TextArea comment;

    @FXML
    private CheckBox watched;

    @FXML
    private Button btnReview;

    private MovieModel currentMovie;

    private String previousScreen;

    @FXML
    private StackPane stackPane;

    public void openMovie(MovieModel movie, String screen) {
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        yearOfRelease.setText(String.valueOf(movie.getYearOfRelease()));
        durationTime.setText(movie.getDurationTime());
        direction.setText(movie.getDirection());
        screenplay.setText(movie.getScreenplay());
        cast.setText(movie.getCastAsString());
        originalTitle.setText(movie.getOriginalTitle());
        whereToWatch.setText(movie.getWhereToWatch());
        if (movie.getMovieReview() != null) {
            score.setText(String.valueOf(movie.getMovieReview().getScore()));
            consumptionDate.setText(movie.getMovieReview().getConsumptionDate());
            comment.setText(movie.getMovieReview().getComment());
        } else {
            score.setText("Filme não avaliado");
            consumptionDate.setText("Filme não avaliado");
            comment.setText("Filme não avaliado");
        }
        watched.setSelected(movie.isWatched());

        setCurrentMovie(movie);
        setPreviousScreen(screen);
    }

    @FXML
    public void onBtnReviewMovieAction() throws IOException {
        if (currentMovie.isWatched()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MovieReviewScreen.fxml"));
            Parent root = loader.load();

            MovieReviewController movieReviewController = loader.getController();
            movieReviewController.setMovie(currentMovie, previousScreen);

            Stage stage = (Stage) btnReview.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.setTitle("Diário Cultural");
        } else {
            displayMessage(stackPane);
        }
    }

    @FXML
    public void onBtnReturnAction(ActionEvent event) throws IOException {
        if (previousScreen.equals("search screen"))  {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/SearchScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.setTitle("Diário Cultural");
        } else if (previousScreen.equals("list screen")) {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ListScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.setTitle("Diário Cultural");
        }
    }

    public void displayMessage(StackPane stackPane) {
        stackPane.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> stackPane.setVisible(false));
        pause.play();
    }


    public String getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(String previousScreen) {
        this.previousScreen = previousScreen;
    }

    public MovieModel getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(MovieModel currentMovie) {
        this.currentMovie = currentMovie;
    }
}
