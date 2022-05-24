package fr.knightmar.client_tcp_rust.scenes;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import static javafx.geometry.Pos.CENTER;

public class ConnectPane extends Pane {
    public ConnectPane(int width, int height) {

        TextField ip = new TextField();

        ip.setPromptText("IP");
        ip.setAlignment(CENTER);
        ip.setMaxWidth(width / 3.0);
        ip.setTranslateY(100);
        ip.setTranslateX(width / 2.0 - ip.getMaxWidth() / 2.0);

        Button connexion_button = new Button("Connect");
        connexion_button.setAlignment(CENTER);
        connexion_button.setTranslateX(width / 2.0 - 50);
        connexion_button.setTranslateY(200);

        connexion_button.setOnAction(arg0 -> {
            System.out.println("hello world");
        });
        this.getChildren().addAll(connexion_button, ip);
    }
}
