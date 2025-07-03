package gui;

import book.BookModel;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class FullBookController {
    @FXML
    private TextField title; // Campo para título do livro

    @FXML
    private TextField author; // Campo para autor

    @FXML
    private TextField publisher; // Campo para editora

    @FXML
    private TextField isbn; // Campo para ISBN

    @FXML
    private TextField yearOfPublication; // Campo para ano de publicação

    @FXML
    private TextField genre; // Campo para gênero

    @FXML
    private TextField score; // Campo para nota

    @FXML
    private TextField consumptionDate; // Campo para data de consumo

    @FXML
    private TextArea comment; // Área para comentário

    @FXML
    private CheckBox hasCopy; // Checkbox para indicar se possui cópia

    @FXML
    private CheckBox read; // Checkbox para indicar se já leu

    @FXML
    private Button btnReview; // Botão para enviar avaliação

    private BookModel currentBook; // Livro atual

    private String previousScreen; // Tela anterior

    @FXML
    private StackPane stackPane; // Container para empilhar elementos visuais

    public void openBook(BookModel book, String screen) {
        title.setText(book.getTitle()); // Preenche título
        author.setText(book.getAuthor()); // Preenche autor
        publisher.setText(book.getPublisher()); // Preenche editora
        isbn.setText(book.getIsbn()); // Preenche ISBN
        yearOfPublication.setText(String.valueOf(book.getYearOfPublication())); // Preenche ano de publicação
        genre.setText(book.getGenre()); // Preenche gênero

        if (book.getBookReview() != null) { // Se houver avaliação
            score.setText(String.valueOf(book.getBookReview().getScore())); // Preenche nota
            consumptionDate.setText(book.getBookReview().getConsumptionDate()); // Preenche data de consumo
            comment.setText(book.getBookReview().getComment()); // Preenche comentário
        } else {
            score.setText("Livro não avaliado"); // Mensagem padrão se não avaliado
            consumptionDate.setText("Livro não avaliado");
            comment.setText("Livro não avaliado");
        }

        hasCopy.setSelected(book.isHasCopy()); // Marca se possui cópia
        read.setSelected(book.isRead()); // Marca se já leu

        setCurrentBook(book); // Atualiza livro atual
        setPreviousScreen(screen); // Atualiza tela anterior
    }


    @FXML
    public void onBtnReviewBookAction() throws IOException {
        if (currentBook.isRead()) { // Verifica se o livro foi lido
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BookReviewScreen.fxml")); // Carrega tela de avaliação
            Parent root = loader.load();

            BookReviewController bookReviewController = loader.getController(); // Pega o controlador da tela de avaliação
            bookReviewController.setBook(currentBook, previousScreen); // Passa o livro e a tela anterior

            Stage stage = (Stage) btnReview.getScene().getWindow(); // Pega a janela atual
            stage.setScene(new Scene(root)); // Define a nova cena
            stage.centerOnScreen(); // Centraliza a janela
            stage.setTitle("Diário Cultural"); // Define título da janela
        } else {
            displayMessage(stackPane); // Mostra mensagem se o livro não foi lido
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
        pause.play(); // Inicia o tempo
    }

    public String getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(String previousScreen) {
        this.previousScreen = previousScreen;
    }

    public BookModel getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(BookModel currentBook) {
        this.currentBook = currentBook;
    }
}
