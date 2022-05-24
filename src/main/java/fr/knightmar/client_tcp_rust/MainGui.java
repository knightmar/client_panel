package fr.knightmar.client_tcp_rust;

import fr.knightmar.client_tcp_rust.scenes.ConnectPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGui extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        int width = 600;
        int height = 400;


        Pane connectPane = new ConnectPane(width, height);
        Scene scene = new Scene(connectPane, width, height);

        primaryStage.setTitle("First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

