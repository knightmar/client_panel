package fr.knightmar.client_tcp_rust;

import fr.knightmar.client_tcp_rust.scenes.ConnectPane;
import fr.knightmar.client_tcp_rust.scenes.SendMessagePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGui extends Application {
    private static Scene currentScene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        int width = 600;
        int height = 400;


        Pane connectPane = new ConnectPane(width, height);
        Scene connexionScene = new Scene(connectPane, width, height);


        primaryStage.setTitle("First JavaFX Application");
        currentScene = connexionScene;
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

}

