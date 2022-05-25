package fr.knightmar.client_tcp_rust.scenes;

import fr.knightmar.client_tcp_rust.connexion.ConnexionManager;
import fr.knightmar.client_tcp_rust.utils.ConnexionManagerInstanceSaver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.InetAddress;

import static javafx.geometry.Pos.CENTER;

public class SendMessagePane extends Pane {

    private static ConnexionManager connexionManager;

    public SendMessagePane(int width, int height) {
        TextField message = new TextField();

        message.setPromptText("Message");
        message.setAlignment(CENTER);
        message.setMaxWidth(width / 3.0);
        message.setTranslateY(100);
        message.setTranslateX(width / 2.0 - message.getMaxWidth() / 2.0);

        Button send_button = new Button("Send");
        send_button.setAlignment(CENTER);
        send_button.setTranslateX(width / 2.0 - 50);
        send_button.setTranslateY(200);

        send_button.setOnAction(event -> {
            Thread thread = new Thread(() -> {
                try {
                    connexionManager = ConnexionManagerInstanceSaver.connexionManager;

                    synchronized (connexionManager) {
                        connexionManager.sendMessage(message.getText());
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        });
        this.getChildren().addAll(send_button, message);

    }



}
