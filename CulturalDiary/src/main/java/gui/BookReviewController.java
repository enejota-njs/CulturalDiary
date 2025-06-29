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
    BookController bookController = BookController.getInstance();

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
    private Label lblScoreBook;

    @FXML
    private Label lblConsumptionDateBook;

    @FXML
    private Label lblCommentBook;

    private BookModel book;

    @FXML
    public void onBtnEvaluateBookAction() throws IOException {
        String score = txtScore.getText();
        lblScoreBook.setVisible(!bookController.validateNewScore(score.trim()));

        String consumptionDate = txtConsumptionDate.getText();
        lblConsumptionDateBook.setVisible(!bookController.validateNewDate(book, consumptionDate.trim()));

        String comment = txtComment.getText();
        lblCommentBook.setVisible(comment.trim().isEmpty());

        boolean validReview = bookController.evaluateBook(book.getBookIndex(), score, consumptionDate, comment);

        if (validReview) {
            onBtnReturnAction();
        }
    }

    @FXML
    private void onBtnReturnAction() throws IOException {
        txtScore.clear();
        txtConsumptionDate.clear();
        txtComment.clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullBookScreen.fxml"));
        Parent root = loader.load();

        FullBookController fullBookController = loader.getController();
        fullBookController.openBook(book, "list screen");

        Stage stage = (Stage) btnReview.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }
}
