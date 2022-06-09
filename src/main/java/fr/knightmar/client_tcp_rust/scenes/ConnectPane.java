package fr.knightmar.client_tcp_rust.scenes;

import fr.knightmar.client_tcp_rust.MainGui;
import fr.knightmar.client_tcp_rust.connexion.ConnexionManager;
import fr.knightmar.client_tcp_rust.utils.ConnexionManagerInstanceSaver;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.InetAddress;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public class ConnectPane extends BorderPane {


    public ConnectPane(int width, int height) {
        TextField ip = new TextField();
        Label title = new Label("Give the ip of the server to connect to :");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ip.setPromptText("IP");
        ip.setAlignment(CENTER);
        ip.setMaxWidth(width / 3.0);

        Button connexion_button = new Button("Connect");
        connexion_button.setAlignment(CENTER);


        connexion_button.setOnAction(arg0 -> {
                    try {
                        InetAddress ipAddres = InetAddress.getByName(ip.getText());
                        if (!ipAddres.isReachable(1000)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Network error");
                            alert.setHeaderText("Can't connect to the server");
                            alert.setContentText("The server is not reachable, bad ip or server is down");
                        } else {
                            System.out.println("Connecting to " + ipAddres.getHostAddress());
                            Thread thread = new Thread(() -> {
                                try {
                                    ConnexionManager connexionManager = new ConnexionManager(InetAddress.getByName(ip.getText()), 7878);
                                    setConnexionManager(connexionManager);

                                } catch (IOException e) {
                                    sendAlert();
                                }
                            });
                            thread.start();

                            Pane appPane = new ManageServerPane(width, height);
                            Scene appScene = new Scene(appPane, width, height);
                            MainGui.setScene(appScene);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

        );

        VBox vbox = new VBox();
        vbox.getChildren().addAll(ip, connexion_button);

        vbox.setAlignment(CENTER);

        VBox.setMargin(ip, new javafx.geometry.Insets(50, 0, 50, 0));


        this.setCenter(vbox);
        vbox.setMaxWidth(ip.getMaxWidth() / 2);

        this.setTop(title);

        setAlignment(title, TOP_CENTER);
    }

    public static void setConnexionManager(ConnexionManager connexionManager) {
        ConnexionManagerInstanceSaver.connexionManager = connexionManager;
    }

    public static void sendAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Network error");
        alert.setHeaderText("Can't connect to the server");
        alert.setContentText("The server is not reachable, bad ip or server is down");
        alert.showAndWait();
    }
}
