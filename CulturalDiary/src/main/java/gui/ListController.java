package gui;

import book.BookController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import book.BookModel;
import javafx.stage.Stage;
import review.ReviewModel;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ListController {
    // =================================================================================================================

    BookController bookController = BookController.getInstance();

    @FXML
    private CheckBox checkBoxGenreBook;

    @FXML
    private CheckBox checkBoxYearOfPublicationBook;

    @FXML
    private CheckBox checkBoxTopRatedBook;

    @FXML
    private CheckBox checkBoxLowRatedBook;

    @FXML
    private ComboBox<String> comboBoxGenreBook;

    @FXML
    private TextField txtYearOfPublicationBook;

    @FXML
    private TableView<BookModel> tvBook;

    @FXML
    private TableColumn<BookModel, String> tcTitleBook;

    @FXML
    private TableColumn<BookModel, String> tcAuthorBook;

    @FXML
    private TableColumn<BookModel, String> tcGenreBook;

    @FXML
    private TableColumn<BookModel, String> tcYearOfPublicationBook;

    @FXML
    private TableColumn<BookModel, String> tcScoreBook;

    @FXML
    private Button btnReturnOpenBook;

    private ObservableList<BookModel> observableListBook = FXCollections.observableArrayList(bookController.getListOfBooks());

    @FXML
    public void onBtnApplyFiltersBookAction() {
        if (checkBoxGenreBook.isSelected()) {
            if (comboBoxGenreBook.getValue() != null) {
                bookController.filterListOfBooksByGenre(comboBoxGenreBook.getValue());
                tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
            }
        } else if (checkBoxYearOfPublicationBook.isSelected()) {
            if (!txtYearOfPublicationBook.getText().trim().isEmpty()) {
                bookController.filterListOfBooksByYearOfPublication(txtYearOfPublicationBook.getText());
                tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
            }
        } else if (checkBoxTopRatedBook.isSelected()) {
            bookController.sortListByTopRated();
            tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
        } else if (checkBoxLowRatedBook.isSelected()) {
            bookController.sortListByLowRated();
            tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
        }
    }

    @FXML
    public void onBtnResetFiltersBookAction() {
        tvBook.setItems(observableListBook);
    }

    public void onBtnOpenBookAction(BookModel book) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullBookScreen.fxml"));
        Parent root = loader.load();

        FullBookController fullBookController = loader.getController();
        fullBookController.openBook(book, "list screen");

        Stage stage = (Stage) tvBook.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public void initializeSettingsBooks() {
        comboBoxGenreBook.getItems().addAll("Ação", "Animação", "Aventura", "Comédia", "Dança", "Documentário", "Drama", "Faroeste", "Fantasia",
                "Ficção Científica", "Guerra", "Mistério", "Musical", "Suspense", "Romance", "Terror");

        tcTitleBook.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcAuthorBook.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcGenreBook.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tcYearOfPublicationBook.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
        tcScoreBook.setCellValueFactory(cellData -> {
            ReviewModel review = cellData.getValue().getBookReview();
            if (review != null) {
                return new SimpleStringProperty(review.getScoreString());
            } else {
                return new SimpleStringProperty("Vazio");
            }
        });

        tvBook.setItems(observableListBook);

        checkBoxGenreBook.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            comboBoxGenreBook.setDisable(!isNowSelected);
        });

        checkBoxYearOfPublicationBook.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            txtYearOfPublicationBook.setDisable(!isNowSelected);
        });

        setupExclusiveCheckBox(checkBoxGenreBook, checkBoxYearOfPublicationBook, checkBoxTopRatedBook, checkBoxLowRatedBook);
        setupExclusiveCheckBox(checkBoxYearOfPublicationBook, checkBoxGenreBook, checkBoxTopRatedBook, checkBoxLowRatedBook);
        setupExclusiveCheckBox(checkBoxTopRatedBook, checkBoxGenreBook, checkBoxYearOfPublicationBook, checkBoxLowRatedBook);
        setupExclusiveCheckBox(checkBoxLowRatedBook, checkBoxGenreBook, checkBoxYearOfPublicationBook, checkBoxTopRatedBook);

        tvBook.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                BookModel book = tvBook.getSelectionModel().getSelectedItem();
                if (book != null) {
                    try {
                        onBtnOpenBookAction(book);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    // ============================================================================================================================================

    public void initialize() {
        initializeSettingsBooks();
    }

    @FXML
    public void onBtnReturnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/MenuScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
        stage.show();
    }

    private void setupExclusiveCheckBox(CheckBox main, CheckBox... others) {
        main.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            for (CheckBox cb : others) {
                cb.setDisable(isSelected);
                if (isSelected) cb.setSelected(false);
            }
        });
    }

}
