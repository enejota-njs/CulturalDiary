package gui;

import book.BookController;
import book.BookModel;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BookReviewController {
    BookController bookController = BookController.getInstance(); // Controlador principal dos livros (singleton)

    @FXML
    private TextField txtScore; // Campo de texto para nota

    @FXML
    private TextField txtConsumptionDate; // Campo de texto para data de consumo

    @FXML
    private TextArea txtComment; // Área de texto para comentário

    @FXML
    private Button btnReturn; // Botão de voltar

    @FXML
    private Button btnReview; // Botão para enviar avaliação

    @FXML
    private Label lblScoreBook; // Rótulo da nota

    @FXML
    private Label lblConsumptionDateBook; // Rótulo da data de consumo

    @FXML
    private Label lblCommentBook; // Rótulo do comentário

    private BookModel book; // Livro sendo avaliado

    private String previousScreen; // Tela anterior para navegação


    @FXML
    public void onBtnEvaluateBookAction() throws IOException {
        String score = txtScore.getText();
        lblScoreBook.setVisible(!bookController.validateNewScore(score.trim())); // Mostra erro se a nota for inválida

        String consumptionDate = txtConsumptionDate.getText();
        lblConsumptionDateBook.setVisible(!bookController.validateNewDate(book, consumptionDate.trim())); // Mostra erro se a data for inválida

        String comment = txtComment.getText();
        lblCommentBook.setVisible(comment.trim().isEmpty()); // Mostra erro se o comentário estiver vazio

        boolean validReview = bookController.evaluateBook(book.getBookIndex(), score, consumptionDate, comment); // Avalia o livro

        if (validReview) {
            onBtnReturnAction(); // Volta para a tela anterior se a avaliação for válida
        }
    }


    @FXML
    private void onBtnReturnAction() throws IOException {
        txtScore.clear(); // Limpa campo da nota
        txtConsumptionDate.clear(); // Limpa campo da data
        txtComment.clear(); // Limpa campo do comentário

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullBookScreen.fxml")); // Carrega FXML da tela completa do livro
        Parent root = loader.load();

        FullBookController fullBookController = loader.getController(); // Pega controlador da nova tela
        fullBookController.openBook(book, previousScreen); // Abre livro na nova tela, passando tela anterior

        Stage stage = (Stage) btnReview.getScene().getWindow(); // Pega a janela atual
        stage.setScene(new Scene(root)); // Define nova cena
        stage.centerOnScreen(); // Centraliza a janela
        stage.setTitle("Diário Cultural"); // Define título da janela
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book, String screen) {
        this.book = book;
        setPreviousScreen(screen);
    }

    public String getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(String previousScreen) {
        this.previousScreen = previousScreen;
    }
}
