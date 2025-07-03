package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/MenuScreen.fxml")); // Carrega o FXML da interface
            stage.setScene(new Scene(root));
            stage.setResizable(false); // Desativa redimensionamento
            stage.centerOnScreen(); // Centraliza a janela
            stage.setTitle("Diário Cultural");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Mostra erro no console
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}