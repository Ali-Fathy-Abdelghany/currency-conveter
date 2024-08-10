package application.currencyconveter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("App.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        stage.setTitle("The Currency Converter");
        Image logo = new Image("application/currencyconveter/logo.png");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}