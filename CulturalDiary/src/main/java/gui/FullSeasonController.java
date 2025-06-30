package gui;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import series.season.SeasonModel;
import series.series.SeriesModel;

import java.io.IOException;

public class FullSeasonController {
    @FXML
    private TextField genre;

    @FXML
    private TextField cast;

    @FXML
    private TextField yearOfRelease;

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

    private SeasonModel currentSeason;

    private SeriesModel currentSeries;

    private String previousScreen;

    @FXML
    private StackPane stackPane;

    public void openSeason(SeasonModel season, SeriesModel series, String previousScreen) {
        genre.setText(season.getGenre());
        cast.setText(season.getCastAsString());
        yearOfRelease.setText(String.valueOf(season.getYearSeason()));
        if (season.getSeasonReview() != null) {
            score.setText(String.valueOf(season.getSeasonReview().getScore()));
            consumptionDate.setText(season.getSeasonReview().getConsumptionDate());
            comment.setText(season.getSeasonReview().getComment());
        } else {
            score.setText("Temporada não avaliada");
            consumptionDate.setText("Temporada não avaliada");
            comment.setText("Temporada não avaliada");
        }
        watched.setSelected(season.isWatched());

        setCurrentSeries(series);
        setCurrentSeason(season);
        setPreviousScreen(previousScreen);
    }

    @FXML
    public void onBtnReviewSeasonAction() throws IOException {
        if (currentSeason.isWatched()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SeasonReviewScreen.fxml"));
            Parent root = loader.load();

            SeasonReviewController seasonReviewController = loader.getController();
            seasonReviewController.setSeasonAndSeriesAndPreviousScreen(currentSeason, currentSeries, previousScreen);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullSeriesScreen.fxml"));
        Parent root = loader.load();

        FullSeriesController fullSeriesController = loader.getController();
        fullSeriesController.openSeries(currentSeries, previousScreen);

        Stage stage = (Stage) btnReview.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
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

    public SeasonModel getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(SeasonModel currentSeason) {
        this.currentSeason = currentSeason;
    }

    public SeriesModel getCurrentSeries() {
        return currentSeries;
    }

    public void setCurrentSeries(SeriesModel currentSeries) {
        this.currentSeries = currentSeries;
    }
}
