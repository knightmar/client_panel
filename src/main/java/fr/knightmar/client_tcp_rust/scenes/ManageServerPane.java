package fr.knightmar.client_tcp_rust.scenes;

import fr.knightmar.client_tcp_rust.connexion.ConnexionManager;
import fr.knightmar.client_tcp_rust.utils.ConnexionManagerInstanceSaver;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static javafx.geometry.Pos.CENTER;

public class ManageServerPane extends BorderPane {

    private static boolean isConnected = true;
    private static TextArea logs;

    public ManageServerPane(int width, int height) throws IOException, InterruptedException {
        Text title = new Text("Manage the server");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        logs = new TextArea();

        logs.setEditable(false);
        logs.setWrapText(true);
        logs.setMinWidth((int) ((width / 7.0) * 4));
        logs.setMinHeight((int) ((height / 7.0) * 5));

        HBox hbox = new HBox();
        TextField command = new TextField();
        Button send_command = new Button("▶");
        command.setMinWidth((int) ((width / 7.0) * 4));
        send_command.setMinWidth((int) ((width / 7.0) * 0.3));
        hbox.getChildren().addAll(command, send_command);

        VBox vbox = new VBox();
        VBox.setMargin(title, new Insets(0, 0, 10, 0));
        vbox.setMaxWidth((int) ((width / 7.0) * 4));
        vbox.setAlignment(CENTER);
        vbox.getChildren().addAll(title, logs, hbox);
        this.setCenter(vbox);

        send_command.setOnAction(event -> {
            System.out.println("button");
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("Sending command : " + command.getText());
                    ConnexionManager connexionManager = ConnexionManagerInstanceSaver.getConnexionManager();
                    connexionManager.sendMessage(command.getText());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        });

        Thread.sleep(1000);

    }

    public static void start() throws IOException {
        Thread thread = new Thread(() -> {
            System.out.println("started");
            String previous_line = "";

            while (isConnected) {
                try {
                    BufferedReader reader = ConnexionManagerInstanceSaver.getConnexionManager().getIn();
                    String line = reader.readLine();
                    if (!Objects.equals(line, previous_line)) {
                        previous_line = line;
                        logs.appendText(previous_line + "\n");
                        logs.end();
                    } else {
                        System.out.println("nop");
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
}
