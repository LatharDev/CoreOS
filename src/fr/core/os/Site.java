package fr.core.os;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Site extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage fenetre) {

        fenetre.getIcons().add(new Image(CoreOS.class.getResourceAsStream("/resources/favicon.png")));
        WebView webView = new WebView();
        fenetre.setResizable(false);
        webView.getEngine().load("https://www.google.fr/");

        fenetre.setTitle("Novariel");
        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);

        fenetre.setScene(scene);
        fenetre.show();

    }
}
