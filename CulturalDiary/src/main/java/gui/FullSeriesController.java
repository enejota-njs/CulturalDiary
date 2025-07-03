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
    private TextField title; // Campo para título da série

    @FXML
    private TextField originalTitle; // Campo para título original

    @FXML
    private TextField yearOfRelease; // Campo para ano de lançamento

    @FXML
    private TextField yearOfConclusion; // Campo para ano de conclusão

    @FXML
    private TextField scoreSeries; // Campo para nota da série

    @FXML
    private TextField whereToWatch; // Campo para onde assistir

    @FXML
    private TableView<SeasonModel> tvSeason; // Tabela das temporadas

    @FXML
    private TableColumn<SeasonModel, String> tcGenreSeason; // Coluna gênero da temporada

    @FXML
    private TableColumn<SeasonModel, String> tcCastSeason; // Coluna elenco da temporada

    @FXML
    private TableColumn<SeasonModel, String> tcYearSeason; // Coluna ano da temporada

    @FXML
    private TableColumn<SeasonModel, String> tcScoreSeason; // Coluna nota da temporada

    private SeriesModel currentSeries; // Série atual

    private String previousScreen; // Tela anterior

    private ObservableList<SeasonModel> observableListSeasons; // Lista observável das temporadas para a tabela

    public void openSeries(SeriesModel series, String screen) {
        title.setText(series.getTitle()); // Preenche título
        yearOfRelease.setText(String.valueOf(series.getYearOfRelease())); // Preenche ano de lançamento
        yearOfConclusion.setText(String.valueOf(series.getYearOfConclusion())); // Preenche ano de conclusão
        originalTitle.setText(series.getOriginalTitle()); // Preenche título original
        whereToWatch.setText(series.getWhereToWatch()); // Preenche onde assistir

        if (series.getSeriesReview() != 0) { // Se tiver avaliação
            scoreSeries.setText(String.valueOf(series.getSeriesReview())); // Preenche nota
        } else {
            scoreSeries.setText("Série não avaliada"); // Mensagem padrão
        }

        observableListSeasons = FXCollections.observableArrayList(series.getListOfSeasons()); // Cria lista observável das temporadas
        tvSeason.setItems(observableListSeasons); // Atualiza tabela de temporadas

        setCurrentSeries(series); // Atualiza série atual
        setPreviousScreen(screen); // Atualiza tela anterior
    }

    @FXML
    public void onBtnReturnAction(ActionEvent event) throws IOException {
        if (previousScreen.equals("search screen"))  { // Verifica se veio da tela de busca
            Parent root = FXMLLoader.load(getClass().getResource("/gui/SearchScreen.fxml")); // Carrega tela de busca
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Pega janela atual
            stage.setScene(new Scene(root)); // Define nova cena
            stage.centerOnScreen(); // Centraliza janela
            stage.setTitle("Diário Cultural"); // Define título
        } else if (previousScreen.equals("list screen")) { // Verifica se veio da tela de lista
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ListScreen.fxml")); // Carrega tela de lista
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.setTitle("Diário Cultural");
        }
    }

    public void initializeSettingsSeasons() {
        tcGenreSeason.setCellValueFactory(new PropertyValueFactory<>("genre")); // Configura coluna gênero
        tcCastSeason.setCellValueFactory(new PropertyValueFactory<>("castAsString")); // Configura coluna elenco
        tcYearSeason.setCellValueFactory(new PropertyValueFactory<>("yearSeason")); // Configura coluna ano
        tcScoreSeason.setCellValueFactory(cellData -> { // Configura coluna nota
            ReviewModel review = cellData.getValue().getSeasonReview();
            if (review != null) {
                return new SimpleStringProperty(review.getScoreString()); // Nota da avaliação
            } else {
                return new SimpleStringProperty("Vazio"); // Texto padrão se sem avaliação
            }
        });

        tvSeason.setOnMouseClicked(event -> { // Evento de clique na tabela
            if (event.getClickCount() == 2) { // Duplo clique
                SeasonModel season = tvSeason.getSelectionModel().getSelectedItem(); // Pega temporada selecionada
                if (season != null) {
                    try {
                        onBtnOpenSeasonAction(season); // Abre detalhes da temporada
                    } catch (IOException e) {
                        throw new RuntimeException(e); // Trata erro de IO
                    }
                }
            }
        });
    }

    public void onBtnOpenSeasonAction(SeasonModel season) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullSeasonScreen.fxml")); // Carrega tela da temporada
        Parent root = loader.load();

        FullSeasonController fullSeasonController = loader.getController(); // Pega controlador da tela
        fullSeasonController.openSeason(season, currentSeries, previousScreen); // Passa temporada, série e tela anterior

        Stage stage = (Stage) tvSeason.getScene().getWindow(); // Pega janela atual
        stage.setScene(new Scene(root)); // Define nova cena
        stage.centerOnScreen(); // Centraliza janela
        stage.setTitle("Diário Cultural"); // Define título
    }

    public void initialize() {
        initializeSettingsSeasons(); // Inicializa as configurações das Temporadas
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
