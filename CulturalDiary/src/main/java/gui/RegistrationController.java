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
import series.season.SeasonController;
import series.series.SeriesController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private StackPane stackPaneBook;

    @FXML
    private Label lblBook;

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

            displayRegistrationMessage(stackPaneBook, lblBook, "Livro cadastrado com sucesso!");
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
    private StackPane stackPaneMovie;

    @FXML
    private Label lblMovie;

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

            displayRegistrationMessage(stackPaneMovie, lblMovie,"Filme cadastrado com sucesso!");
        }
    }

    // ==========================================================================================================

    SeriesController seriesController = SeriesController.getInstance();
    SeasonController seasonController = SeasonController.getInstance();

    @FXML
    private TextField txtTitleSeries;

    @FXML
    private TextField txtOriginalTitleSeries;

    @FXML
    private TextField txtWhereToWatchSeries;

    @FXML
    private TextField txtYearOfReleaseSeries;

    @FXML
    private TextField txtYearOfConclusionSeries;

    @FXML
    private TextField txtYearOfSeason;

    @FXML
    private ComboBox<String> comboBoxGenreSeason;

    @FXML
    private TextField txtCastSeason;

    @FXML
    private CheckBox checkBoxWatchedSeason;

    @FXML
    private Label lblErrorTitleSeries;

    @FXML
    private Label lblErrorOriginalTitleSeries;

    @FXML
    private Label lblErrorWhereToWatchSeries;

    @FXML
    private Label lblErrorYearOfReleaseSeries;

    @FXML
    private Label lblErrorYearOfConclusionSeries;

    @FXML
    private Label lblErrorYearOfSeason;

    @FXML
    private Label lblErrorGenreSeason;

    @FXML
    private Label lblErrorCastSeason;

    @FXML
    private TabPane tabPaneRegistration;

    @FXML
    private Tab tabSeason;

    @FXML
    private Tab tabSeries;

    @FXML
    private StackPane stackPaneSeries;

    @FXML
    private Label lblSeries;

    @FXML
    private StackPane stackPaneSeason;

    @FXML
    private Label lblSeason;

    private List<String[]> arrayOfSeasons = new ArrayList<>();

    public boolean validateSeriesInformation() {
        String title = txtTitleSeries.getText();
        boolean validTitle = seriesController.validateTitle(title.trim());
        lblErrorTitleSeries.setVisible(!validTitle);

        String originalTitle = txtOriginalTitleSeries.getText();
        boolean validOriginalTitle = seriesController.validateOriginalTitle(originalTitle.trim());
        lblErrorOriginalTitleSeries.setVisible(!validOriginalTitle);

        String whereToWactch = txtWhereToWatchSeries.getText();
        boolean validWhereToWatch = seriesController.validateWhereToWatch(whereToWactch.trim());
        lblErrorWhereToWatchSeries.setVisible(!validWhereToWatch);

        String yearOfRelease = txtYearOfReleaseSeries.getText();
        boolean validYearOfRelease = seriesController.validateYearOfRelease(yearOfRelease.trim());
        lblErrorYearOfReleaseSeries.setVisible(!validYearOfRelease);

        String yearOfConclusion = txtYearOfConclusionSeries.getText();
        boolean validYearOfConslusion = seriesController.validateYearOfConclusion(yearOfConclusion.trim());
        lblErrorYearOfConclusionSeries.setVisible(!validYearOfConslusion);

        if (!validTitle || !validOriginalTitle || !validWhereToWatch || !validYearOfRelease || !validYearOfConslusion) {
            return false;
        }

        int yearOfReleaseInt = Integer.parseInt(yearOfRelease.trim());
        int yearOfConclusionInt = Integer.parseInt(yearOfConclusion.trim());

        if (yearOfConclusionInt < yearOfReleaseInt) {
            displayRegistrationMessage(stackPaneSeries, lblSeries, "Ano de lançamento e conclusão inválidos!");
            return false;
        }

        return true;
    }

    @FXML
    public void onBtnRegisterSeasonsAction() {
        if (validateSeriesInformation()) {
            tabSeason.setDisable(false);
            tabPaneRegistration.getSelectionModel().select(tabSeason);
            tabSeries.setDisable(true);
        }
    }

    @FXML
    public void onBtnRegisterSeasonAction() {
        String validGenre = comboBoxGenreSeason.getValue();
        String genre = validGenre == null ? "" : validGenre;
        boolean validGenreSeason = seasonController.validateGenre(genre.trim(), 1);
        lblErrorGenreSeason.setVisible(!validGenreSeason);

        String yearOfSeason = txtYearOfSeason.getText();
        boolean validYearOfSeason = seasonController.validateYearSeason(yearOfSeason.trim(), 1);
        lblErrorYearOfSeason.setVisible(!validYearOfSeason);

        String cast = txtCastSeason.getText();
        boolean validCast = seasonController.validateCast(cast.trim(), 1);
        lblErrorCastSeason.setVisible(!validCast);

        String watched = checkBoxWatchedSeason.isSelected() ? "Sim" : "Não";

        if (validGenreSeason && validYearOfSeason && validCast) {
            String yearOfRelease = txtYearOfReleaseSeries.getText();
            String yearOfConclusion = txtYearOfConclusionSeries.getText();

            int yearOfReleaseInt = Integer.parseInt(yearOfRelease.trim());
            int yearOfConclusionInt = Integer.parseInt(yearOfConclusion.trim());
            int yearOfSeasonInt = Integer.parseInt(yearOfSeason.trim());

            if (yearOfSeasonInt < yearOfReleaseInt || yearOfSeasonInt > yearOfConclusionInt) {
                displayRegistrationMessage(stackPaneSeason, lblSeason, "Ano da temporada incompatível!");
                return;
            }

            arrayOfSeasons.add(new String[] {genre, cast, yearOfSeason, watched});

            displayRegistrationMessage(stackPaneSeason, lblSeason, "Temporada cadastrada com sucesso!");

            txtYearOfSeason.clear();
            comboBoxGenreSeason.getSelectionModel().clearSelection();
            txtCastSeason.clear();
            checkBoxWatchedSeason.setSelected(false);
        }
    }

    @FXML
    public void onBtnRegisterSeriesAction() {
        if (!arrayOfSeasons.isEmpty()) {
            String[][] listOfSeasons = new String[arrayOfSeasons.size()][];

            for (int i = 0; i < arrayOfSeasons.size(); i++) {
                listOfSeasons[i] = arrayOfSeasons.get(i);
            }

            seriesController.registerSeries(txtTitleSeries.getText(), txtYearOfReleaseSeries.getText(), txtYearOfConclusionSeries.getText(),
                    txtOriginalTitleSeries.getText(), txtWhereToWatchSeries.getText(), listOfSeasons);

            txtYearOfSeason.clear();
            comboBoxGenreSeason.getSelectionModel().clearSelection();
            txtCastSeason.clear();
            checkBoxWatchedSeason.setSelected(false);

            txtTitleSeries.clear();
            txtOriginalTitleSeries.clear();
            txtYearOfReleaseSeries.clear();
            txtYearOfConclusionSeries.clear();
            txtWhereToWatchSeries.clear();

            arrayOfSeasons.clear();
            tabSeries.setDisable(false);
            tabPaneRegistration.getSelectionModel().select(tabSeries);
            tabSeason.setDisable(true);

            displayRegistrationMessage(stackPaneSeries, lblSeries, "Série cadastrada com sucesso!");
        } else {
            displayRegistrationMessage(stackPaneSeason, lblSeason, "Lista de temporadas vazia!");
        }
    }

    @FXML
    public void onBtnReturnTabSeriesAction() {
        txtTitleSeries.clear();
        txtOriginalTitleSeries.clear();
        txtYearOfReleaseSeries.clear();
        txtYearOfConclusionSeries.clear();
        txtWhereToWatchSeries.clear();

        arrayOfSeasons.clear();
        tabSeries.setDisable(false);
        tabPaneRegistration.getSelectionModel().select(tabSeries);
        tabSeason.setDisable(true);
    }

    // ==========================================================================================================

    public void displayRegistrationMessage(StackPane stackPane, Label lbl, String text) {
        lbl.setText(text);
        stackPane.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> stackPane.setVisible(false));
        pause.play();
    }

    @FXML
    private void onBtnReturnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/MenuScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
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

        comboBoxGenreSeason.getItems().addAll("Ação", "Animação", "Aventura", "Comédia", "Dança", "Documentário", "Drama", "Faroeste", "Fantasia",
                "Ficção Científica", "Guerra", "Mistério", "Musical", "Suspense", "Romance", "Terror");
    }
}
