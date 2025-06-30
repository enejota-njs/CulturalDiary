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
import movie.MovieController;
import movie.MovieModel;
import review.ReviewModel;
import javafx.scene.control.cell.PropertyValueFactory;
import series.series.SeriesController;
import series.series.SeriesModel;

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

    MovieController movieController = MovieController.getInstance();

    @FXML
    private CheckBox checkBoxGenreMovie;

    @FXML
    private CheckBox checkBoxYearOfReleaseMovie;

    @FXML
    private CheckBox checkBoxTopRatedMovie;

    @FXML
    private CheckBox checkBoxLowRatedMovie;

    @FXML
    private ComboBox<String> comboBoxGenreMovie;

    @FXML
    private TextField txtYearOfReleaseMovie;

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
    public void onBtnApplyFiltersMovieAction() {
        if (checkBoxGenreMovie.isSelected()) {
            if (comboBoxGenreMovie.getValue() != null) {
                movieController.filterListOfMoviesByGenre(comboBoxGenreMovie.getValue());
                tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
            }
        } else if (checkBoxYearOfReleaseMovie.isSelected()) {
            if (!txtYearOfReleaseMovie.getText().trim().isEmpty()) {
                movieController.filterListOfMoviesByYearOfRelease(txtYearOfReleaseMovie.getText());
                tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
            }
        } else if (checkBoxTopRatedMovie.isSelected()) {
            movieController.sortListByTopRated();
            tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
        } else if (checkBoxLowRatedMovie.isSelected()) {
            movieController.sortListByLowRated();
            tvMovie.setItems(FXCollections.observableArrayList(movieController.getReserveListOfMovies()));
        }
    }

    @FXML
    public void onBtnResetFiltersMovieAction() {
        tvMovie.setItems(observableListMovie);
    }

    public void onBtnOpenMovieAction(MovieModel movie) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullMovieScreen.fxml"));
        Parent root = loader.load();

        FullMovieController fullMovieController = loader.getController();
        fullMovieController.openMovie(movie, "list screen");

        Stage stage = (Stage) tvMovie.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public void initializeSettingsMovies() {
        comboBoxGenreMovie.getItems().addAll("Ação", "Animação", "Aventura", "Comédia", "Dança", "Documentário", "Drama", "Faroeste", "Fantasia",
                "Ficção Científica", "Guerra", "Mistério", "Musical", "Suspense", "Romance", "Terror");

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

        checkBoxGenreMovie.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            comboBoxGenreMovie.setDisable(!isNowSelected);
        });

        checkBoxYearOfReleaseMovie.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            txtYearOfReleaseMovie.setDisable(!isNowSelected);
        });

        setupExclusiveCheckBox(checkBoxGenreMovie, checkBoxYearOfReleaseMovie, checkBoxTopRatedMovie, checkBoxLowRatedMovie);
        setupExclusiveCheckBox(checkBoxYearOfReleaseMovie, checkBoxGenreMovie,checkBoxTopRatedMovie, checkBoxLowRatedMovie);
        setupExclusiveCheckBox(checkBoxTopRatedMovie, checkBoxGenreMovie, checkBoxYearOfReleaseMovie, checkBoxLowRatedMovie);
        setupExclusiveCheckBox(checkBoxLowRatedMovie, checkBoxGenreMovie, checkBoxYearOfReleaseMovie, checkBoxTopRatedMovie);

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

    // ============================================================================================================================================

    SeriesController seriesController = SeriesController.getInstance();

    @FXML
    private CheckBox checkBoxGenreSeries;

    @FXML
    private CheckBox checkBoxYearOfReleaseSeries;

    @FXML
    private CheckBox checkBoxTopRatedSeries;

    @FXML
    private CheckBox checkBoxLowRatedSeries;

    @FXML
    private ComboBox<String> comboBoxGenreSeries;

    @FXML
    private TextField txtYearOfReleaseSeries;

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
    public void onBtnApplyFiltersSeriesAction() {
        if (checkBoxGenreSeries.isSelected()) {
            if (comboBoxGenreSeries.getValue() != null) {
                seriesController.filterListOfSeriesByGenre(comboBoxGenreSeries.getValue());
                tvSeries.setItems(FXCollections.observableArrayList(seriesController.getReserveListOfSeries()));
            }
        } else if (checkBoxYearOfReleaseSeries.isSelected()) {
            if (!txtYearOfReleaseSeries.getText().trim().isEmpty()) {
                seriesController.filterListOfSeriesByYearOfRelease(txtYearOfReleaseSeries.getText());
                tvSeries.setItems(FXCollections.observableArrayList(seriesController.getReserveListOfSeries()));
            }
        } else if (checkBoxTopRatedSeries.isSelected()) {
            seriesController.sortListByTopRated();
            tvSeries.setItems(FXCollections.observableArrayList(seriesController.getReserveListOfSeries()));
        } else if (checkBoxLowRatedSeries.isSelected()) {
            seriesController.sortListByLowRated();
            tvSeries.setItems(FXCollections.observableArrayList(seriesController.getReserveListOfSeries()));
        }
    }

    @FXML
    public void onBtnResetFiltersSeriesAction() {
        tvSeries.setItems(observableListSeries);
    }

    public void onBtnOpenSeriesAction(SeriesModel series) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FullSeriesScreen.fxml"));
        Parent root = loader.load();

        FullSeriesController fullSeriesController = loader.getController();
        fullSeriesController.openSeries(series, "list screen");

        Stage stage = (Stage) tvSeries.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    public void initializeSettingsSeries() {
        comboBoxGenreSeries.getItems().addAll("Ação", "Animação", "Aventura", "Comédia", "Dança", "Documentário", "Drama", "Faroeste", "Fantasia",
                "Ficção Científica", "Guerra", "Mistério", "Musical", "Suspense", "Romance", "Terror");

        tcTitleSeries.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcStartDateSeries.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
        tcEndDateSeries.setCellValueFactory(new PropertyValueFactory<>("yearOfConclusion"));
        tcNumberOfSeasonsSeries.setCellValueFactory(new PropertyValueFactory<>("numberOfSeasons"));
        tcScoreSeries.setCellValueFactory(new PropertyValueFactory<>("seriesReview"));

        tvSeries.setItems(observableListSeries);

        checkBoxGenreSeries.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            comboBoxGenreSeries.setDisable(!isNowSelected);
        });

        checkBoxYearOfReleaseSeries.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            txtYearOfReleaseSeries.setDisable(!isNowSelected);
        });

        setupExclusiveCheckBox(checkBoxGenreSeries, checkBoxYearOfReleaseSeries, checkBoxTopRatedSeries, checkBoxLowRatedSeries);
        setupExclusiveCheckBox(checkBoxYearOfReleaseSeries, checkBoxGenreSeries,checkBoxTopRatedSeries, checkBoxLowRatedSeries);
        setupExclusiveCheckBox(checkBoxTopRatedSeries, checkBoxGenreSeries, checkBoxYearOfReleaseSeries, checkBoxLowRatedSeries);
        setupExclusiveCheckBox(checkBoxLowRatedSeries, checkBoxGenreSeries, checkBoxYearOfReleaseSeries, checkBoxTopRatedSeries);

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

    // ============================================================================================================================================

    public void initialize() {
        initializeSettingsBooks();
        initializeSettingsMovies();
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