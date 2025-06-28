package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnList;

    @FXML
    public void onBtnRegisterAction() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/RegistrationScreen.fxml"));
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    @FXML
    public void onBtnSearchAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/SearchScreen.fxml"));
        Stage stage = (Stage) btnSearch.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    @FXML
    public void onBtnListAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/ListScreen.fxml"));
        Stage stage = (Stage) btnList.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Diário Cultural");
    }

    @FXML
    public void onBtnExitAction(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
