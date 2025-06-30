package gui;

import book.BookController;
import book.BookModel;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import movie.MovieController;
import movie.MovieModel;
import review.ReviewModel;
import series.series.SeriesController;
import series.series.SeriesModel;

import java.io.IOException;

public class SearchController {
    BookController bookController = BookController.getInstance();

    @FXML
    private CheckBox checkBoxTitleBook;

    @FXML
    private CheckBox checkBoxAuthorBook;

    @FXML
    private CheckBox checkBoxGenreBook;

    @FXML
    private CheckBox checkBoxYearOfPublicationBook;

    @FXML
    private CheckBox checkBoxIsbnBook;

    @FXML
    private TextField txtSearchBook;

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

    private ObservableList<BookModel> observableListBook = FXCollections.observableArrayList(bookController.getListOfBooks());

    @FXML
    public void onBtnSearchBookAction() {
        if (!txtSearchBook.getText().trim().isEmpty()) {
            if (checkBoxTitleBook.isSelected()) {
                bookController.searchBookByTitle(txtSearchBook.getText());
                tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
            } else if (checkBoxAuthorBook.isSelected()) {
                bookController.searchBookByAuthor(txtSearchBook.getText());
                tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
            } else if (checkBoxGenreBook.isSelected()) {
                bookController.searchBookByGenre(txtSearchBook.getText());
                tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
            } else if (checkBoxYearOfPublicationBook.isSelected()) {
                bookController.searchBookByYearOfPublication(txtSearchBook.getText());
                tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
            } else if (checkBoxIsbnBook.isSelected()) {
                bookController.searchBookByIsbn(txtSearchBook.getText());
                tvBook.setItems(FXCollections.observableArrayList(bookController.getReserveListOfBooks()));
            }
        }
    }

    public void onBtnOpenBookAction(BookModel book) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullBookScreen.fxml"));
        Parent root = loader.load();

        FullBookController fullBookController = loader.getController();
        fullBookController.openBook(book, "search screen");

        Stage stage = (Stage) tvBook.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public void initializeSettingsBooks() {
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

        setupExclusiveCheckBox(checkBoxTitleBook, checkBoxAuthorBook, checkBoxGenreBook, checkBoxYearOfPublicationBook, checkBoxIsbnBook);
        setupExclusiveCheckBox(checkBoxAuthorBook, checkBoxTitleBook, checkBoxGenreBook, checkBoxYearOfPublicationBook, checkBoxIsbnBook);
        setupExclusiveCheckBox(checkBoxGenreBook, checkBoxTitleBook, checkBoxAuthorBook, checkBoxYearOfPublicationBook, checkBoxIsbnBook);
        setupExclusiveCheckBox(checkBoxYearOfPublicationBook, checkBoxTitleBook, checkBoxAuthorBook, checkBoxGenreBook, checkBoxIsbnBook);
        setupExclusiveCheckBox(checkBoxIsbnBook, checkBoxTitleBook, checkBoxAuthorBook, checkBoxGenreBook, checkBoxYearOfPublicationBook);

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
    // ==========================================================================================================================================

    MovieController movieController = MovieController.getInstance();

    @FXML
    private CheckBox checkBoxTitleMovie;

    @FXML
    private CheckBox checkBoxDirectionMovie;

    @FXML
    private CheckBox checkBoxActorInCastMovie;

    @FXML
    private CheckBox checkBoxGenreMovie;

    @FXML
    private CheckBox checkBoxYearOfReleaseMovie;

    @FXML
    private TextField txtSearchMovie;

    @FXML
    private TableView<MovieModel> tvMovie;

    @FXML
    private TableColumn<MovieModel, String> tcTitleMovie;

    @FXML
    private TableColumn<MovieModel, String> tcDirectionMovie;

    @FXML
    private TableColumn<MovieModel, String> tcGenreMovie;

    @FXML
    private TableColumn<MovieModel, String> tcYearOfReleaseMovie;

    @FXML
    private TableColumn<MovieModel, String> tcScoreMovie;

    private ObservableList<MovieModel> observableListMovie = FXCollections.observableArrayList(movieController.getListOfMovies());

    @FXML
    public void onBtnSearchMovieAction() {
        if (!txtSearchMovie.getText().trim().isEmpty()) {
            if (checkBoxTitleMovie.isSelected()) {
                movieController.searchMovieByTitle(txtSearchMovie.getText());
                tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
            } else if (checkBoxDirectionMovie.isSelected()) {
                movieController.searchMovieByDirection(txtSearchMovie.getText());
                tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
            } else if (checkBoxActorInCastMovie.isSelected()) {
                movieController.searchMovieByActorInTheCast(txtSearchMovie.getText());
                tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
            } else if (checkBoxGenreMovie.isSelected()) {
                movieController.searchMovieByGenre(txtSearchMovie.getText());
                tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
            } else if (checkBoxYearOfReleaseMovie.isSelected()) {
                movieController.searchMovieByYearOfRelease(txtSearchMovie.getText());
                tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
            }
        }
    }

    public void onBtnOpenMovieAction(MovieModel movie) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullMovieScreen.fxml"));
        Parent root = loader.load();

        FullMovieController fullMovieController = loader.getController();
        fullMovieController.openMovie(movie, "search screen");

        Stage stage = (Stage) tvMovie.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public void initializeSettingsMovie() {
        tcTitleMovie.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcDirectionMovie.setCellValueFactory(new PropertyValueFactory<>("direction"));
        tcGenreMovie.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tcYearOfReleaseMovie.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
        tcScoreMovie.setCellValueFactory(cellData -> {
            ReviewModel review = cellData.getValue().getMovieReview();
            if (review != null) {
                return new SimpleStringProperty(review.getScoreString());
            } else {
                return new SimpleStringProperty("Vazio");
            }
        });

        tvMovie.setItems(observableListMovie);

        setupExclusiveCheckBox(checkBoxTitleMovie, checkBoxDirectionMovie, checkBoxActorInCastMovie, checkBoxGenreMovie, checkBoxYearOfReleaseMovie);
        setupExclusiveCheckBox(checkBoxDirectionMovie, checkBoxTitleMovie, checkBoxActorInCastMovie, checkBoxGenreMovie, checkBoxYearOfReleaseMovie);
        setupExclusiveCheckBox(checkBoxActorInCastMovie, checkBoxTitleMovie, checkBoxDirectionMovie, checkBoxGenreMovie, checkBoxYearOfReleaseMovie);
        setupExclusiveCheckBox(checkBoxGenreMovie, checkBoxTitleMovie, checkBoxDirectionMovie, checkBoxActorInCastMovie, checkBoxYearOfReleaseMovie);
        setupExclusiveCheckBox(checkBoxYearOfReleaseMovie, checkBoxTitleMovie, checkBoxDirectionMovie, checkBoxActorInCastMovie, checkBoxGenreMovie);

        tvMovie.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                MovieModel movie = tvMovie.getSelectionModel().getSelectedItem();
                if (movie != null) {
                    try {
                        onBtnOpenMovieAction(movie);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    // ==========================================================================================================================================

    SeriesController seriesController = SeriesController.getInstance();

    @FXML
    private CheckBox checkBoxTitleSeries;

    @FXML
    private TextField txtSearchSeries;

    @FXML
    private TableView<SeriesModel> tvSeries;

    @FXML
    private TableColumn<SeriesModel, String> tcTitleSeries;

    @FXML
    private TableColumn<SeriesModel, String> tcStartDateSeries;

    @FXML
    private TableColumn<SeriesModel, String> tcEndDateSeries;

    @FXML
    private TableColumn<SeriesModel, String> tcNumberOfSeasonsSeries;

    @FXML
    private TableColumn<SeriesModel, String> tcScoreSeries;

    private ObservableList<SeriesModel> observableListSeries = FXCollections.observableArrayList(seriesController.getListOfSeries());

    @FXML
    public void onBtnSearchSeriesAction() {
        if (!txtSearchSeries.getText().trim().isEmpty()) {
            if (checkBoxTitleSeries.isSelected()) {
                seriesController.searchSeriesByTitle(txtSearchSeries.getText());
                tvSeries.setItems(FXCollections.observableArrayList(seriesController.getReserveListOfSeries()));
            }
        }
    }

    public void onBtnOpenSeriesAction(SeriesModel series) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullSeriesScreen.fxml"));
        Parent root = loader.load();

        FullSeriesController fullSeriesController = loader.getController();
        fullSeriesController.openSeries(series, "search screen");

        Stage stage = (Stage) tvSeries.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public void initializeSettingsSeries() {
        tcTitleSeries.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcStartDateSeries.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
        tcEndDateSeries.setCellValueFactory(new PropertyValueFactory<>("yearOfConclusion"));
        tcNumberOfSeasonsSeries.setCellValueFactory(new PropertyValueFactory<>("numberOfSeasons"));
        tcScoreSeries.setCellValueFactory(new PropertyValueFactory<>("seriesReview"));

        tvSeries.setItems(observableListSeries);

        tvSeries.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                SeriesModel series = tvSeries.getSelectionModel().getSelectedItem();
                if (series != null) {
                    try {
                        onBtnOpenSeriesAction(series);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    // ==========================================================================================================================================

    public void initialize() {
        initializeSettingsBooks();
        initializeSettingsMovie();
        initializeSettingsSeries();
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
