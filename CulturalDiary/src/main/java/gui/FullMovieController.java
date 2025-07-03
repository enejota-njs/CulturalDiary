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
    private TextField title; // Campo para título

    @FXML
    private TextField genre; // Campo para gênero

    @FXML
    private TextField yearOfRelease; // Campo para ano de lançamento

    @FXML
    private TextField durationTime; // Campo para duração

    @FXML
    private TextField direction; // Campo para direção

    @FXML
    private TextArea screenplay; // Área para roteiro

    @FXML
    private TextField cast; // Campo para elenco

    @FXML
    private TextField originalTitle; // Campo para título original

    @FXML
    private TextField whereToWatch; // Campo para onde assistir

    @FXML
    private TextField score; // Campo para nota

    @FXML
    private TextField consumptionDate; // Campo para data de consumo

    @FXML
    private TextArea comment; // Área para comentário

    @FXML
    private CheckBox watched; // Checkbox se foi assistido

    @FXML
    private Button btnReview; // Botão para avaliar

    private MovieModel currentMovie; // Filme atual

    private String previousScreen; // Tela anterior

    @FXML
    private StackPane stackPane; // Container para mensagens ou sobreposição

    public void openMovie(MovieModel movie, String screen) {
        title.setText(movie.getTitle()); // Preenche título
        genre.setText(movie.getGenre()); // Preenche gênero
        yearOfRelease.setText(String.valueOf(movie.getYearOfRelease())); // Preenche ano de lançamento
        durationTime.setText(movie.getDurationTime()); // Preenche duração
        direction.setText(movie.getDirection()); // Preenche direção
        screenplay.setText(movie.getScreenplay()); // Preenche roteiro
        cast.setText(movie.getCastAsString()); // Preenche elenco
        originalTitle.setText(movie.getOriginalTitle()); // Preenche título original
        whereToWatch.setText(movie.getWhereToWatch()); // Preenche onde assistir

        if (movie.getMovieReview() != null) { // Se houver avaliação
            score.setText(String.valueOf(movie.getMovieReview().getScore())); // Preenche nota
            consumptionDate.setText(movie.getMovieReview().getConsumptionDate()); // Preenche data
            comment.setText(movie.getMovieReview().getComment()); // Preenche comentário
        } else {
            score.setText("Filme não avaliado"); // Padrão se não avaliado
            consumptionDate.setText("Filme não avaliado");
            comment.setText("Filme não avaliado");
        }

        watched.setSelected(movie.isWatched()); // Marca se foi assistido

        setCurrentMovie(movie); // Atualiza filme atual
        setPreviousScreen(screen); // Atualiza tela anterior
    }

    @FXML
    public void onBtnReviewMovieAction() throws IOException {
        if (currentMovie.isWatched()) { // Verifica se o filme foi assistido
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MovieReviewScreen.fxml")); // Carrega tela de avaliação
            Parent root = loader.load();

            MovieReviewController movieReviewController = loader.getController(); // Pega o controlador
            movieReviewController.setMovie(currentMovie, previousScreen); // Passa o filme e a tela anterior

            Stage stage = (Stage) btnReview.getScene().getWindow(); // Pega a janela atual
            stage.setScene(new Scene(root)); // Define nova cena
            stage.centerOnScreen(); // Centraliza a janela
            stage.setTitle("Diário Cultural"); // Define título
        } else {
            displayMessage(stackPane); // Mostra mensagem se o filme não foi assistido
        }
    }

    @FXML
    public void onBtnReturnAction(ActionEvent event) throws IOException {
        if (previousScreen.equals("search screen"))  { // Verifica se veio da tela de busca
            Parent root = FXMLLoader.load(getClass().getResource("/gui/SearchScreen.fxml")); // Carrega tela de busca
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Pega a janela atual
            stage.setScene(new Scene(root)); // Define nova cena
            stage.centerOnScreen(); // Centraliza a janela
            stage.setTitle("Diário Cultural"); // Define título
        } else if (previousScreen.equals("list screen")) { // Verifica se veio da tela de lista
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ListScreen.fxml")); // Carrega tela de lista
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.setTitle("Diário Cultural");
        }
    }

    public void displayMessage(StackPane stackPane) {
        stackPane.setVisible(true); // Exibe a mensagem

        PauseTransition pause = new PauseTransition(Duration.seconds(3)); // Espera 3 segundos
        pause.setOnFinished(event -> stackPane.setVisible(false)); // Oculta após o tempo
        pause.play(); // Inicia a contagem
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
