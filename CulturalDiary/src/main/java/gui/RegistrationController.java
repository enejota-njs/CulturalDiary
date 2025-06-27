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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    BookController bookController = BookController.getInstance();

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtYearOfPublication;

    @FXML
    private ComboBox<String> comboBoxGenre;

    @FXML
    private CheckBox checkBoxHasCopy;

    @FXML
    private CheckBox checkBoxRead;

    @FXML
    private Label lblErrorTitle;

    @FXML
    private Label lblErrorAuthor;

    @FXML
    private Label lblErrorPublisher;

    @FXML
    private Label lblErrorIsbn;

    @FXML
    private Label lblErrorYearOfPublication;

    @FXML
    private Label lblErrorGenre;

    @FXML
    private StackPane stackPaneSucess;

    @FXML
    private Label lblSucess;

    @FXML
    public void onBtnRegisterBookAction() {
        String title = txtTitle.getText();
        lblErrorTitle.setVisible(!bookController.validateTitle(title.trim()));

        String author = txtAuthor.getText();
        lblErrorAuthor.setVisible(!bookController.validateAuthor(author.trim()));

        String publisher = txtPublisher.getText();
        lblErrorPublisher.setVisible(!bookController.validatePublisher(publisher.trim()));

        String isbn = txtIsbn.getText();
        lblErrorIsbn.setVisible(!bookController.validateIsbn(isbn.trim()));

        String yearOfPublication = txtYearOfPublication.getText();
        lblErrorYearOfPublication.setVisible(!bookController.validateYearOfPublication(yearOfPublication.trim()));

        String validGenre = comboBoxGenre.getValue();
        String genre = validGenre == null ? "" : validGenre;
        lblErrorGenre.setVisible(!bookController.validateGenre(genre.trim()));

        String hasCopy = checkBoxHasCopy.isSelected() ? "Sim" : "Não";
        String read = checkBoxRead.isSelected() ? "Sim" : "Não";

        boolean validRegistration = bookController.registerBook(title, author, publisher, isbn, yearOfPublication, genre, hasCopy, read);

        if (validRegistration) {
            txtTitle.clear();
            txtAuthor.clear();
            txtPublisher.clear();
            txtIsbn.clear();
            txtYearOfPublication.clear();
            comboBoxGenre.getSelectionModel().clearSelection();
            checkBoxHasCopy.setSelected(false);
            checkBoxRead.setSelected(false);

            displayRegistrationMessage("Livro cadastrado com sucesso!");
        }
    }

    @FXML
    private void onBtnReturnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/MenuScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Diário Cultural");
        stage.show();
    }

    public void displayRegistrationMessage(String text) {
        lblSucess.setText(text);
        stackPaneSucess.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> stackPaneSucess.setVisible(false));
        pause.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxGenre.getItems().addAll("Ação", "Animação", "Aventura", "Comédia", "Dança", "Documentário", "Drama", "Faroeste", "Fantasia",
                "Ficção Científica", "Guerra", "Mistério", "Musical", "Suspense", "Romance", "Terror");
    }
}
