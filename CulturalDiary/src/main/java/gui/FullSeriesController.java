package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import review.ReviewModel;
import series.season.SeasonModel;
import series.series.SeriesModel;

import java.io.IOException;

public class FullSeriesController {
    @FXML
    private TextField title;

    @FXML
    private TextField originalTitle;

    @FXML
    private TextField yearOfRelease;

    @FXML
    private TextField yearOfConclusion;

    @FXML
    private TextField scoreSeries;

    @FXML
    private TextField whereToWatch;

    @FXML
    private TableView<SeasonModel> tvSeason;

    @FXML
    private TableColumn<SeasonModel, String> tcGenreSeason;

    @FXML
    private TableColumn<SeasonModel, String> tcCastSeason;

    @FXML
    private TableColumn<SeasonModel, String> tcYearSeason;

    @FXML
    private TableColumn<SeasonModel, String> tcScoreSeason;

    private SeriesModel currentSeries;

    private String previousScreen;

    private ObservableList<SeasonModel> observableListSeasons;

    public void openSeries(SeriesModel series, String screen) {
        title.setText(series.getTitle());
        yearOfRelease.setText(String.valueOf(series.getYearOfRelease()));
        yearOfConclusion.setText(String.valueOf(series.getYearOfConclusion()));
        originalTitle.setText(series.getOriginalTitle());
        whereToWatch.setText(series.getWhereToWatch());
        if (series.getSeriesReview() != 0) {
            scoreSeries.setText(String.valueOf(series.getSeriesReview()));
        } else {
            scoreSeries.setText("Série não avaliada");
        }
        observableListSeasons = FXCollections.observableArrayList(series.getListOfSeasons());
        tvSeason.setItems(observableListSeasons);

        setCurrentSeries(series);
        setPreviousScreen(screen);
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

    public void initializeSettingsSeasons() {
        tcGenreSeason.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tcCastSeason.setCellValueFactory(new PropertyValueFactory<>("castAsString"));
        tcYearSeason.setCellValueFactory(new PropertyValueFactory<>("yearSeason"));
        tcScoreSeason.setCellValueFactory(cellData -> {
            ReviewModel review = cellData.getValue().getSeasonReview();
            if (review != null) {
                return new SimpleStringProperty(review.getScoreString());
            } else {
                return new SimpleStringProperty("Vazio");
            }
        });

        tvSeason.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                SeasonModel season = tvSeason.getSelectionModel().getSelectedItem();
                if (season != null) {
                    try {
                        onBtnOpenSeasonAction(season);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void onBtnOpenSeasonAction(SeasonModel season) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullSeasonScreen.fxml"));
        Parent root = loader.load();

        FullSeasonController fullSeasonController = loader.getController();
        fullSeasonController.openSeason(season, currentSeries, previousScreen);

        Stage stage = (Stage) tvSeason.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public void initialize() {
        initializeSettingsSeasons();
    }

    public String getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(String previousScreen) {
        this.previousScreen = previousScreen;
    }

    public SeriesModel getCurrentSeries() {
        return currentSeries;
    }

    public void setCurrentSeries(SeriesModel currentSeries) {
        this.currentSeries = currentSeries;
    }
}
