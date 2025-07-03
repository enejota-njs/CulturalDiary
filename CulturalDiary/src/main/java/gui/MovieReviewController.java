package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movie.MovieController;
import movie.MovieModel;

import java.io.IOException;

public class MovieReviewController {
    MovieController movieController = MovieController.getInstance();

    @FXML
    private TextField txtScore;

    @FXML
    private TextField txtConsumptionDate;

    @FXML
    private TextArea txtComment;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnReview;

    @FXML
    private Label lblScoreMovie;

    @FXML
    private Label lblConsumptionDateMovie;

    @FXML
    private Label lblCommentMovie;

    private MovieModel movie;

    private String previousScreen;

    @FXML
    public void onBtnEvaluateMovieAction() throws IOException {
        String score = txtScore.getText();
        lblScoreMovie.setVisible(!movieController.validateNewScore(score.trim()));

        String consumptionDate = txtConsumptionDate.getText();
        lblConsumptionDateMovie.setVisible(!movieController.validateNewDate(movie, consumptionDate.trim()));

        String comment = txtComment.getText();
        lblCommentMovie.setVisible(comment.trim().isEmpty());

        boolean validReview = movieController.evaluateMovie(movie.getMovieIndex(), score, consumptionDate, comment);

        if (validReview) {
            onBtnReturnAction();
        }
    }

    @FXML
    private void onBtnReturnAction() throws IOException {
        txtScore.clear();
        txtConsumptionDate.clear();
        txtComment.clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullMovieScreen.fxml"));
        Parent root = loader.load();

        FullMovieController fullMovieController = loader.getController();
        fullMovieController.openMovie(movie, previousScreen);

        Stage stage = (Stage) btnReview.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie, String screen) {
        this.movie = movie;
        setPreviousScreen(screen);
    }

    public String getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(String previousScreen) {
        this.previousScreen = previousScreen;
    }
}
