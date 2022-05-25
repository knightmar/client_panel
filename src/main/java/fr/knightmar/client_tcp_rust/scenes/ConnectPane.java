package fr.knightmar.client_tcp_rust.scenes;

import fr.knightmar.client_tcp_rust.MainGui;
import fr.knightmar.client_tcp_rust.connexion.ConnexionManager;
import fr.knightmar.client_tcp_rust.utils.ConnexionManagerInstanceSaver;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.InetAddress;

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
            try {
                Thread thread = new Thread(() -> {
                    try {
                        ConnexionManager connexionManager = new ConnexionManager(InetAddress.getByName(ip.getText()), 7878);
                        setConnexionManager(connexionManager);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                thread.start();

                Pane appPane = new SendMessagePane(width, height);
                Scene appScene = new Scene(appPane, width, height);
                MainGui.setScene(appScene);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        this.getChildren().addAll(connexion_button, ip);
    }

    public static void setConnexionManager(ConnexionManager connexionManager) {
        ConnexionManagerInstanceSaver.connexionManager = connexionManager;
    }
}
