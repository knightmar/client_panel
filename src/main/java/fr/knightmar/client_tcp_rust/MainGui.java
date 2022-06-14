package fr.knightmar.client_tcp_rust;

import fr.knightmar.client_tcp_rust.scenes.ConnectPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGui extends Application {
    private static Scene currentScene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        int width = 1400;
        int height = 800;

        Pane connectPane = new ConnectPane(width, height);
        Scene connexionScene = new Scene(connectPane, width, height);

        primaryStage.setTitle("First JavaFX Application");
        currentScene = connexionScene;
        primaryStage.setScene(currentScene);
        primaryStage.show();

        System.out.println(primaryStage.getScene().getRoot());

        stage.setOnCloseRequest((arg) -> {
            try {
                stop();
                System.exit(0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

}

