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

import java.io.IOException;

import static javafx.geometry.Pos.CENTER;

public class ManageServerPane extends BorderPane {

    private static ConnexionManager connexionManager;

    public ManageServerPane(int width, int height) {
        Text title = new Text("Manage the server");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        TextArea logs = new TextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec magna tortor. Fusce porttitor leo sit amet magna feugiat rhoncus. Nulla vulputate, sapien eget viverra commodo, est mauris consectetur ligula, sed egestas erat mauris vel velit. Pellentesque nisl lorem, porta eget scelerisque a, molestie nec libero. Nunc eget iaculis sem. Quisque sed bibendum ligula. Nulla a turpis nulla. Fusce hendrerit ipsum quis nulla posuere, malesuada tincidunt nisi consequat. Cras maximus risus ac libero finibus lacinia. Curabitur nec volutpat tortor, porta consequat neque. Aenean malesuada sem eu felis interdum sodales. Nam venenatis risus quis eros vulputate, vitae tincidunt mi commodo.\n" +
                "\n" +
                "In lacinia ante arcu, sit amet ornare erat dignissim quis. Proin pretium tortor nec mollis dapibus. Suspendisse sit amet nisl est. Nam ipsum nulla, efficitur vel rhoncus a, ullamcorper a ante. Donec dictum dictum tortor. Nam a sem tortor. Vivamus gravida aliquet tortor vitae posuere.\n" +
                "\n" +
                "Ut risus urna, cursus a laoreet eu, tincidunt nec lorem. Phasellus velit enim, aliquet fermentum odio in, lobortis sollicitudin massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent sit amet dignissim velit. Pellentesque posuere et nulla bibendum mollis. Phasellus in urna nec odio tincidunt varius. Integer rutrum sed felis ut cursus. In dignissim mattis molestie. Praesent suscipit orci eget luctus efficitur. Maecenas vitae faucibus leo, in accumsan urna. Morbi a eros urna. Vestibulum massa enim, viverra non quam ac, hendrerit lobortis augue. Vivamus efficitur consequat ante at tempor. Ut eu pellentesque odio. Donec dictum mollis ante, a vestibulum velit.\n" +
                "\n" +
                "Nam porta est condimentum nunc aliquam, vitae maximus orci pretium. Phasellus id fringilla tellus. Integer quis lorem eu velit congue consectetur. Donec nec pretium sem. Nam et magna quam. Duis tincidunt congue velit quis tincidunt. Nullam turpis nisi, semper ac magna id, dapibus pulvinar neque. Nam rutrum erat tincidunt tincidunt pretium. Aliquam condimentum metus nunc, a commodo quam pretium et. Maecenas tincidunt ipsum nulla, at facilisis tortor convallis quis. Pellentesque et eleifend nisi. Mauris finibus tortor id metus vehicula, at porttitor mi tempor. Aliquam erat volutpat. Aliquam nec ipsum tortor. Pellentesque sapien nunc, iaculis at augue sit amet, vestibulum rhoncus nisi.\n" +
                "\n" +
                "Pellentesque eu enim imperdiet, fringilla leo ac, tristique felis. Aliquam tempor nunc ut nisl aliquet, sit amet tempus felis commodo. Suspendisse vulputate scelerisque ipsum, ut dictum neque maximus ac. Curabitur sed dictum purus, non cursus nulla. Vivamus semper sit amet felis sed dictum. Nunc consequat lorem id dapibus tincidunt. Suspendisse augue risus, euismod sed fermentum nec, consectetur pulvinar sapien. Phasellus pharetra lacus dignissim vulputate viverra. Fusce quis eros et lectus pulvinar euismod. Phasellus eros nisi, mollis maximus metus vel, efficitur laoreet dui. Pellentesque faucibus fringilla nunc eget feugiat.");
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
            Thread thread = new Thread(() -> {
                try {
                    connexionManager = ConnexionManagerInstanceSaver.connexionManager;

                    synchronized (connexionManager) {
                        connexionManager.sendMessage(command.getText());
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        });
    }
}
