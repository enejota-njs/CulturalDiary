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
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextField isbn;

    @FXML
    private TextField yearOfPublication;

    @FXML
    private TextField genre;

    @FXML
    private TextField score;

    @FXML
    private TextField consumptionDate;

    @FXML
    private TextArea comment;

    @FXML
    private CheckBox hasCopy;

    @FXML
    private CheckBox read;

    @FXML
    private Button btnReview;

    private BookModel currentBook;

    private String previousScreen;

    @FXML
    private StackPane stackPane;

    public void openBook(BookModel book, String screen) {
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        isbn.setText(book.getIsbn());
        yearOfPublication.setText(String.valueOf(book.getYearOfPublication()));
        genre.setText(book.getGenre());
        if (book.getBookReview() != null) {
            score.setText(String.valueOf(book.getBookReview().getScore()));
            consumptionDate.setText(book.getBookReview().getConsumptionDate());
            comment.setText(book.getBookReview().getComment());
        } else {
            score.setText("Livro não avaliado");
            consumptionDate.setText("Livro não avaliado");
            comment.setText("Livro não avaliado");
        }
        hasCopy.setSelected(book.isHasCopy());
        read.setSelected(book.isRead());

        setCurrentBook(book);
        setPreviousScreen(screen);
    }

    @FXML
    public void onBtnReviewBookAction() throws IOException {
        if (currentBook.isRead()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BookReviewScreen.fxml"));
            Parent root = loader.load();

            BookReviewController bookReviewController = loader.getController();
            bookReviewController.setBook(currentBook, previousScreen);

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

    public BookModel getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(BookModel currentBook) {
        this.currentBook = currentBook;
    }
}
