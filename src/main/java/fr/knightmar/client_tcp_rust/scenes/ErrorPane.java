package fr.knightmar.client_tcp_rust.scenes;

import fr.knightmar.client_tcp_rust.MainGui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static javafx.geometry.Pos.CENTER;

public class ErrorPane extends BorderPane {
    public ErrorPane(int width, int height, String errorTitle , String errorMessage) {
        VBox vbox = new VBox();
        Text errorLabel = new Text(errorMessage);
        Text errorTitleLabel = new Text("Error : " + errorTitle);
        Button homeButton = new Button("Back");

        errorTitleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        errorLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        errorLabel.setLayoutX(width / 2d);
        errorLabel.setLayoutY(height / 2d);

        VBox.setMargin(errorTitleLabel, new javafx.geometry.Insets(0, 0, 200, 0));
        VBox.setMargin(errorLabel, new javafx.geometry.Insets(0, 0, 10, 0));

        vbox.getChildren().addAll(errorTitleLabel, errorLabel, homeButton);
        vbox.setAlignment(CENTER);
        homeButton.setOnAction(arg0 -> {
            Pane appPane = new ConnectPane(width, height);
            Scene appScene = new Scene(appPane, width, height);
            MainGui.setScene(appScene);
        });

        this.setCenter(vbox);
    }
}

