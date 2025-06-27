package gui;

import book.BookController;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import movie.MovieController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    // ==========================================================================================================

    BookController bookController = BookController.getInstance();

    @FXML
    private TextField txtTitleBook;

    @FXML
    private TextField txtAuthorBook;

    @FXML
    private TextField txtPublisherBook;

    @FXML
    private TextField txtIsbnBook;

    @FXML
    private TextField txtYearOfPublicationBook;

    @FXML
    private ComboBox<String> comboBoxGenreBook;

    @FXML
    private CheckBox checkBoxHasCopyBook;

    @FXML
    private CheckBox checkBoxReadBook;

    @FXML
    private Label lblErrorTitleBook;

    @FXML
    private Label lblErrorAuthorBook;

    @FXML
    private Label lblErrorPublisherBook;

    @FXML
    private Label lblErrorIsbnBook;

    @FXML
    private Label lblErrorYearOfPublicationBook;

    @FXML
    private Label lblErrorGenreBook;

    @FXML
    public void onBtnRegisterBookAction() {
        String title = txtTitleBook.getText();
        lblErrorTitleBook.setVisible(!bookController.validateTitle(title.trim()));

        String author = txtAuthorBook.getText();
        lblErrorAuthorBook.setVisible(!bookController.validateAuthor(author.trim()));

        String publisher = txtPublisherBook.getText();
        lblErrorPublisherBook.setVisible(!bookController.validatePublisher(publisher.trim()));

        String isbn = txtIsbnBook.getText();
        lblErrorIsbnBook.setVisible(!bookController.validateIsbn(isbn.trim()));

        String yearOfPublication = txtYearOfPublicationBook.getText();
        lblErrorYearOfPublicationBook.setVisible(!bookController.validateYearOfPublication(yearOfPublication.trim()));

        String validGenre = comboBoxGenreBook.getValue();
        String genre = validGenre == null ? "" : validGenre;
        lblErrorGenreBook.setVisible(!bookController.validateGenre(genre.trim()));

        String hasCopy = checkBoxHasCopyBook.isSelected() ? "Sim" : "Não";
        String read = checkBoxReadBook.isSelected() ? "Sim" : "Não";

        boolean validRegistration = bookController.registerBook(title, author, publisher, isbn, yearOfPublication, genre, hasCopy, read);

        if (validRegistration) {
            txtTitleBook.clear();
            txtAuthorBook.clear();
            txtPublisherBook.clear();
            txtIsbnBook.clear();
            txtYearOfPublicationBook.clear();
            comboBoxGenreBook.getSelectionModel().clearSelection();
            checkBoxHasCopyBook.setSelected(false);
            checkBoxReadBook.setSelected(false);

            displayRegistrationMessage("Livro cadastrado com sucesso!");
        }
    }

    // ==========================================================================================================

    MovieController movieController = MovieController.getInstance();

    @FXML
    private TextField txtTitleMovie;

    @FXML
    private TextField txtYearOfReleaseMovie;

    @FXML
    private TextField txtDurationTimeMovie;

    @FXML
    private TextField txtDirectionMovie;

    @FXML
    private TextField txtCastMovie;

    @FXML
    private TextField txtOriginalTitleMovie;

    @FXML
    private TextField txtWhereToWatchMovie;

    @FXML
    private TextArea txtScreenplayMovie;

    @FXML
    private ComboBox<String> comboBoxGenreMovie;

    @FXML
    private CheckBox checkBoxWatchedMovie;

    @FXML
    private Label lblErrorTitleMovie;

    @FXML
    private Label lblErrorYearOfReleaseMovie;

    @FXML
    private Label lblErrorDurationTimeMovie;

    @FXML
    private Label lblErrorDirectionMovie;

    @FXML
    private Label lblErrorCastMovie;

    @FXML
    private Label lblErrorOriginalTitleMovie;

    @FXML
    private Label lblErrorWhereToWatchMovie;

    @FXML
    private Label lblErrorScreenplayMovie;

    @FXML
    private Label lblErrorGenreMovie;

    @FXML
    public void onBtnRegisterMovieAction() {
        String title = txtTitleMovie.getText();
        lblErrorTitleMovie.setVisible(!movieController.validateTitle(title.trim()));

        String yearOfRelease = txtYearOfReleaseMovie.getText();
        lblErrorYearOfReleaseMovie.setVisible(!movieController.validateYearOfRelease(yearOfRelease.trim()));

        String durationTime = txtDurationTimeMovie.getText();
        lblErrorDurationTimeMovie.setVisible(!movieController.validateDurationTime(durationTime.trim()));

        String direction = txtDirectionMovie.getText();
        lblErrorDirectionMovie.setVisible(!movieController.validateDirection(direction.trim()));

        String cast = txtCastMovie.getText();
        lblErrorCastMovie.setVisible(!movieController.validateCast(cast.trim()));

        String originalTitle = txtOriginalTitleMovie.getText();
        lblErrorOriginalTitleMovie.setVisible(!movieController.validateOriginalTitle(originalTitle.trim()));

        String whereToWatch = txtWhereToWatchMovie.getText();
        lblErrorWhereToWatchMovie.setVisible(!movieController.validateWhereToWatch(whereToWatch.trim()));

        String screenplay = txtScreenplayMovie.getText();
        lblErrorScreenplayMovie.setVisible(!movieController.validateScreenplay(screenplay.trim()));

        String validGenre = comboBoxGenreMovie.getValue();
        String genre = validGenre == null ? "" : validGenre;
        lblErrorGenreMovie.setVisible(!movieController.validateGenre(genre.trim()));

        String watched = checkBoxWatchedMovie.isSelected() ? "Sim" : "Não";

        boolean validRegistration = movieController.registerMovie(title, genre, yearOfRelease, durationTime,
                direction, screenplay, cast, originalTitle, whereToWatch, watched);

        if (validRegistration) {
            txtTitleMovie.clear();
            comboBoxGenreMovie.getSelectionModel().clearSelection();
            txtYearOfReleaseMovie.clear();
            txtDurationTimeMovie.clear();
            txtDirectionMovie.clear();
            txtScreenplayMovie.clear();
            txtCastMovie.clear();
            txtOriginalTitleMovie.clear();
            txtWhereToWatchMovie.clear();
            checkBoxWatchedMovie.setSelected(false);

            displayRegistrationMessage("Filme cadastrado com sucesso!");
        }
    }

    // ==========================================================================================================

    @FXML
    private StackPane stackPaneSucess;

    @FXML
    private Label lblSucess;

    public void displayRegistrationMessage(String text) {
        lblSucess.setText(text);
        stackPaneSucess.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> stackPaneSucess.setVisible(false));
        pause.play();
    }

    @FXML
    private void onBtnReturnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/MenuScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Diário Cultural");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxGenreBook.getItems().addAll("Ação", "Animação", "Aventura", "Comédia", "Dança", "Documentário", "Drama", "Faroeste", "Fantasia",
                "Ficção Científica", "Guerra", "Mistério", "Musical", "Suspense", "Romance", "Terror");

        comboBoxGenreMovie.getItems().addAll("Ação", "Animação", "Aventura", "Comédia", "Dança", "Documentário", "Drama", "Faroeste", "Fantasia",
                "Ficção Científica", "Guerra", "Mistério", "Musical", "Suspense", "Romance", "Terror");
    }
}
