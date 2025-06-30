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
import series.season.SeasonModel;
import series.series.SeriesController;
import series.series.SeriesModel;

import java.io.IOException;

public class SeasonReviewController {
    SeriesController seriesController = SeriesController.getInstance();

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
    private Label lblScoreSeason;

    @FXML
    private Label lblConsumptionDateSeason;

    @FXML
    private Label lblCommentSeason;

    private SeriesModel series;

    private SeasonModel season;

    private String previousScreen;

    @FXML
    public void onBtnEvaluateSeasonAction() throws IOException {
        String score = txtScore.getText();
        lblScoreSeason.setVisible(!seriesController.validateNewScore(score.trim()));

        String consumptionDate = txtConsumptionDate.getText();
        lblConsumptionDateSeason.setVisible(!seriesController.validateNewDate(season, consumptionDate.trim()));

        String comment = txtComment.getText();
        lblCommentSeason.setVisible(comment.trim().isEmpty());

        boolean validReview = seriesController.evaluateSeason(series.getSeriesIndex(),season.getSeasonIndex(), score, consumptionDate, comment);

        if (validReview) {
            onBtnReturnAction();
        }
    }

    @FXML
    private void onBtnReturnAction() throws IOException {
        txtScore.clear();
        txtConsumptionDate.clear();
        txtComment.clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullSeasonScreen.fxml"));
        Parent root = loader.load();

        FullSeasonController fullSeasonController = loader.getController();
        fullSeasonController.openSeason(season, series, previousScreen);

        Stage stage = (Stage) btnReview.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public SeasonModel getSeason() {
        return season;
    }

    public void setSeasonAndSeriesAndPreviousScreen(SeasonModel season, SeriesModel series, String previousScreen) {
        this.season = season;
        this.series = series;
        this.previousScreen = previousScreen;
    }
}
