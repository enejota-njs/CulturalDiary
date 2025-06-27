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
        stage.setTitle("Cadastro de Mídia");
    }

    @FXML
    public void onBtnSearchAction() {
        System.out.println("Buscar");
    }

    @FXML
    public void onBtnListAction() {
        System.out.println("Listar");
    }

    @FXML
    public void onBtnExitAction(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
