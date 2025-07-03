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
    private TextField genre; // Campo para gênero

    @FXML
    private TextField cast; // Campo para elenco

    @FXML
    private TextField yearOfRelease; // Campo para ano de lançamento

    @FXML
    private TextField score; // Campo para nota

    @FXML
    private TextField consumptionDate; // Campo para data de consumo

    @FXML
    private TextArea comment; // Área para comentário

    @FXML
    private CheckBox watched; // Checkbox para indicar se assistido

    @FXML
    private Button btnReview; // Botão para avaliar

    private SeasonModel currentSeason; // Temporada atual

    private SeriesModel currentSeries; // Série atual

    private String previousScreen; // Tela anterior

    @FXML
    private StackPane stackPane; // Container para mensagens ou sobreposição

    public void openSeason(SeasonModel season, SeriesModel series, String previousScreen) {
        genre.setText(season.getGenre()); // Preenche gênero
        cast.setText(season.getCastAsString()); // Preenche elenco
        yearOfRelease.setText(String.valueOf(season.getYearSeason())); // Preenche ano da temporada

        if (season.getSeasonReview() != null) { // Se houver avaliação
            score.setText(String.valueOf(season.getSeasonReview().getScore())); // Preenche nota
            consumptionDate.setText(season.getSeasonReview().getConsumptionDate()); // Preenche data
            comment.setText(season.getSeasonReview().getComment()); // Preenche comentário
        } else {
            score.setText("Temporada não avaliada"); // Padrão se não avaliado
            consumptionDate.setText("Temporada não avaliada");
            comment.setText("Temporada não avaliada");
        }

        watched.setSelected(season.isWatched()); // Marca se assistido

        setCurrentSeries(series); // Atualiza série atual
        setCurrentSeason(season); // Atualiza temporada atual
        setPreviousScreen(previousScreen); // Atualiza tela anterior
    }

    @FXML
    public void onBtnReviewSeasonAction() throws IOException {
        if (currentSeason.isWatched()) { // Verifica se a temporada foi assistida
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SeasonReviewScreen.fxml")); // Carrega tela de avaliação da temporada
            Parent root = loader.load();

            SeasonReviewController seasonReviewController = loader.getController(); // Pega controlador
            seasonReviewController.setSeasonAndSeriesAndPreviousScreen(currentSeason, currentSeries, previousScreen); // Passa temporada, série e tela anterior

            Stage stage = (Stage) btnReview.getScene().getWindow(); // Pega janela atual
            stage.setScene(new Scene(root)); // Define nova cena
            stage.centerOnScreen(); // Centraliza janela
            stage.setTitle("Diário Cultural"); // Define título
        } else {
            displayMessage(stackPane); // Mostra mensagem se não assistida
        }
    }

    @FXML
    public void onBtnReturnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullSeriesScreen.fxml")); // Carrega tela completa da série
        Parent root = loader.load();

        FullSeriesController fullSeriesController = loader.getController(); // Pega controlador da tela
        fullSeriesController.openSeries(currentSeries, previousScreen); // Abre série na nova tela, passando a tela anterior

        Stage stage = (Stage) btnReview.getScene().getWindow(); // Pega janela atual
        stage.setScene(new Scene(root)); // Define nova cena
        stage.centerOnScreen(); // Centraliza janela
        stage.setTitle("Diário Cultural"); // Define título da janela
    }

    public void displayMessage(StackPane stackPane) {
        stackPane.setVisible(true); // Mostra a mensagem

        PauseTransition pause = new PauseTransition(Duration.seconds(3)); // Pausa de 3 segundos
        pause.setOnFinished(event -> stackPane.setVisible(false)); // Esconde a mensagem após pausa
        pause.play(); // Inicia a pausa
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
