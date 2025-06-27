package gui;

import book.BookController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
    private Button btnBack;

    @FXML
    private Button btnRegister;

    @FXML
    public void registerBook() {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        String isbn = txtIsbn.getText();
        String yearOfPublication = txtYearOfPublication.getText();
        String genre = "Drama";
        String hasCopy = checkBoxHasCopy.isSelected() ? "Sim" : "Não";
        String read = checkBoxRead.isSelected() ? "Sim" : "Não";

        bookController.registerBook(title, author, publisher, isbn, yearOfPublication, genre, hasCopy, read);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
