package fr.core.os;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import fr.core.api.VButton;
import fr.core.lib.CoreColors;
import fr.core.lib.Lib;
import fr.core.os.system.Address;
import fr.core.os.system.SystemInfo;
import fr.core.os.system.SystemOS;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CoreOS extends Application {

    CoreVersions version = CoreVersions.SWAMP;
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);
    public static boolean IS_MAC = (OS.indexOf("mac") >= 0);
    public static boolean IS_UNIX = (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    public static boolean IS_SOLARIS = (OS.indexOf("sunos") >= 0);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage fenetre) throws FileNotFoundException {
        fenetre.setTitle("CoreOS - version "+version.getName());
        fenetre.setResizable(false);

        /* chargement de l'icon */
        try {
            fenetre.getIcons().add(new Image(CoreOS.class.getResourceAsStream("/resources/os.png")));
        } catch (Exception e) {
            Lib.displayMessage("la ressources ne peut pas être chargée !");
        }

        /* inilialisation des objets */
        Button address = new Button();
        Button system = new Button();
        Button os = new Button();
        Button user = new Button();
        Button java = new Button();
        Button infos = new Button();
        HBox panneauBoutons = new HBox();
        Label affichage = new Label();
        VBox panneau = new VBox();

        MenuBar toolBar = new MenuBar();
        toolBar.setTranslateY(-201);

        Menu osName = new Menu(version.getName()+" Version");
        osName.setDisable(true);
        Menu toolMenu = new Menu("Operating System");
        MenuItem addressItem = new MenuItem("Address");
        MenuItem osItem = new MenuItem("OS");
        MenuItem systemItem = new MenuItem("System");
        addressItem.setStyle("-fx-focus-color:white");
        addressItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
                affichage.setText(Address.getAddress());
            }
        });
        osItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
                affichage.setText(Address.getAddress());
                affichage.setText(SystemOS.getNameOS()+"\n"+SystemOS.getArchOS()+"\n"+SystemOS.getVersionOS());
            }
        });

        systemItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
                affichage.setText(Address.getAddress());
                affichage.setText(SystemInfo.getCores()+"\n"+SystemInfo.getFreeMemory()+"\n"+SystemInfo.getMaxMemory()+"\n"+SystemInfo.getTotalMemory());
            }
        });

        Menu javaMenu = new Menu("Java VM");
        MenuItem javaItem = new MenuItem("Java");
        javaItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
                affichage.setText(Address.getAddress());
                affichage.setText(SystemOS.getJavaName()+"\n"+SystemOS.getJavaVersion()+"\n"+SystemOS.getJavaVM());
            }
        });

        Menu appMenu = new Menu("About");
        MenuItem appItem = new MenuItem("Application");
        appItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                final Stage dialog = new Stage();
                dialog.getIcons().add(new Image(CoreOS.class.getResourceAsStream("/resources/os.png")));
                dialog.setTitle("Core OS - "+appItem.getText());
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(fenetre);
                dialog.setResizable(false);
                dialog.centerOnScreen();

                CoreVersions coreVersions = version;
                Label info = new Label();
                String app = "Core OS - "+version.getName();
                String build = "Version - "+version.getVersion();
                String copyright = "(c) "+version.getDate()+" LAURENT Pierre-Allain";

                info.setStyle("-fx-text-fill: #efefef; -fx-font: 16 arial;");
                info.setText(app+"\n"+build+"\n"+copyright);
                info.setPadding(new Insets(25, 12, 15, 12));

                Circle circle = new Circle(25,25,50);
                circle.setStroke(Color.WHITE);
                circle.setStrokeWidth(2);
                Image im = new Image(coreVersions.getPath(),false);
                circle.setFill(new ImagePattern(im));
                circle.setEffect(new DropShadow(+15d, 0d, +2d, Color.DARKGREY));
                
                HBox dialogVbox = new HBox(20);
                dialogVbox.setPadding(new Insets(45, 12, 15, 12));
                dialogVbox.setStyle("-fx-background-color: #303030;");
                dialogVbox.getChildren().add(circle);
                dialogVbox.getChildren().add(info);
                Scene dialogScene = new Scene(dialogVbox, 400, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });

        toolMenu.getItems().addAll(addressItem, osItem, systemItem);
        javaMenu.getItems().addAll(javaItem);
        appMenu.getItems().addAll(appItem);
        toolBar.getMenus().addAll(osName, toolMenu, javaMenu, appMenu);
        VBox VBarBox =new VBox(toolBar);


        if (IS_WINDOWS) {
            Image windows = new Image(CoreOS.class.getResourceAsStream("/resources/windows.jpg"));
            BackgroundImage windowsBackground = new BackgroundImage(windows, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background windows_background = new Background(windowsBackground);
            panneau.setBackground(windows_background);
            fenetre.setWidth(windows.getWidth());
            fenetre.setHeight(windows.getHeight());
            fenetre.centerOnScreen();

            address.setStyle(CoreColors.OCEAN);
            system.setStyle(CoreColors.OCEAN);
            os.setStyle(CoreColors.OCEAN);
            user.setStyle(CoreColors.OCEAN);
            infos.setStyle(CoreColors.OCEAN);
        } else if (IS_MAC) {
            Image macos = new Image(CoreOS.class.getResourceAsStream("/resources/macos.jpg"));
            BackgroundImage macosBackground = new BackgroundImage(macos, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background macos_background = new Background(macosBackground);
            panneau.setBackground(macos_background);
            fenetre.setWidth(macos.getWidth());
            fenetre.setHeight(macos.getHeight());
            fenetre.centerOnScreen();

            address.setStyle(CoreColors.BLUE_GRAY);
            system.setStyle(CoreColors.BLUE_GRAY);
            os.setStyle(CoreColors.BLUE_GRAY);
            user.setStyle(CoreColors.BLUE_GRAY);
            infos.setStyle(CoreColors.BLUE_GRAY);
        } else if (IS_UNIX) {
            Image unix = new Image(CoreOS.class.getResourceAsStream("/resources/linux.jpg"));
            BackgroundImage unixBackground = new BackgroundImage(unix, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background unix_background = new Background(unixBackground);
            panneau.setBackground(unix_background);
            fenetre.setWidth(unix.getWidth());
            fenetre.setHeight(unix.getHeight());
            fenetre.centerOnScreen();

            address.setStyle(CoreColors.ABYSS);
            system.setStyle(CoreColors.ABYSS);
            os.setStyle(CoreColors.ABYSS);
            user.setStyle(CoreColors.ABYSS);
            infos.setStyle(CoreColors.ABYSS);
            java.setStyle(CoreColors.ABYSS);
        } else if (IS_SOLARIS) {
            Image solaris = new Image(CoreOS.class.getResourceAsStream("/resources/solaris.png"));
            BackgroundImage solarisBackground = new BackgroundImage(solaris, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background solaris_background = new Background(solarisBackground);
            panneau.setBackground(solaris_background);
            fenetre.setWidth(solaris.getWidth());
            fenetre.setHeight(solaris.getHeight());
            fenetre.centerOnScreen();
        } else {
            System.out.println("Your OS is not support!!");
        }

        affichage.setStyle("-fx-font: 15 arial; -fx-background-color: none");
        /* activer ou d�sactiver */
        address.setDisable(false);
        system.setDisable(false);
        os.setDisable(false);
        user.setDisable(false);
        java.setDisable(false);

        /* boutton address */
        address.setText("IP Address");
        address.setTranslateY(100);
        address.setOnAction((ActionEvent event) -> {
            address.setDisable(true);
            system.setDisable(false);
            os.setDisable(false);
            user.setDisable(false);
            java.setDisable(false);
            affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
            affichage.setText(Address.getAddress());
        });

        /* boutton system */
        system.setText("System Informations");
        system.setTranslateY(100);
        system.setOnAction((ActionEvent event) -> {
            address.setDisable(false);
            system.setDisable(true);
            os.setDisable(false);
            user.setDisable(false);
            java.setDisable(false);
            affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
            affichage.setText(SystemInfo.getCores()+"\n"+SystemInfo.getFreeMemory()+"\n"+SystemInfo.getMaxMemory()+"\n"+SystemInfo.getTotalMemory());
        });

        /* boutton os */
        os.setText("OS Informations");
        os.setTranslateY(100);
        os.setOnAction((ActionEvent event) -> {
            address.setDisable(false);
            system.setDisable(false);
            os.setDisable(true);
            user.setDisable(false);
            java.setDisable(false);
            affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
            affichage.setText(SystemOS.getNameOS()+"\n"+SystemOS.getArchOS()+"\n"+SystemOS.getVersionOS());
        });

        /* boutton user */
        user.setText("User Informations");
        user.setTranslateY(100);
        user.setOnAction((ActionEvent event) -> {
            address.setDisable(false);
            system.setDisable(false);
            os.setDisable(false);
            user.setDisable(true);
            java.setDisable(false);
            affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
            affichage.setText(SystemOS.getUserOS()+"\n"+SystemOS.getUserCountry()+"\n"+SystemOS.getUserLanguage()+"\n"+SystemOS.getUserHome());
        });

        /* boutton user */
        java.setText("Java Informations");
        java.setTranslateY(100);
        java.setOnAction((ActionEvent event) -> {
            address.setDisable(false);
            system.setDisable(false);
            os.setDisable(false);
            user.setDisable(false);
            java.setDisable(true);
            affichage.setStyle("-fx-font: 15 arial; -fx-background-color: #cfcdcd9f;");
            affichage.setText(SystemOS.getJavaName()+"\n"+SystemOS.getJavaVersion()+"\n"+SystemOS.getJavaVM());
        });
        
        Hyperlink mapsLink = new Hyperlink();
        mapsLink.setText("Web");
        mapsLink.setTranslateY(100);
        mapsLink.setOnAction(e -> {
            if(Desktop.isDesktopSupported())
            {
                try {
                	double lat = 25.2442756;
                	double lon = 75.0346269;
                    Desktop.getDesktop().browse(new URI("https://www.google.fr/maps/@"+lat+","+lon+""));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        /* affichage du chrono */
        affichage.setPrefSize(450, 80);
        affichage.setTranslateY(50);
        affichage.setAlignment(Pos.CENTER);

        /* param�tres du panneau */
        panneau.getChildren().add(VBarBox);
        panneau.getChildren().add(affichage);
        panneau.getChildren().add(panneauBoutons);
        panneau.setAlignment(Pos.CENTER);

        panneauBoutons.setAlignment(Pos.CENTER);
        panneauBoutons.setSpacing(30);
        panneauBoutons.setPadding(new Insets(15, 12, 15, 12));
        panneauBoutons.getChildren().add(address);
        panneauBoutons.getChildren().add(system);
        panneauBoutons.getChildren().add(os);
        panneauBoutons.getChildren().add(user);
        panneauBoutons.getChildren().add(java);
        panneauBoutons.getChildren().add(mapsLink);
        fenetre.setScene(new Scene(panneau, 1000, 900));
        panneau.getStylesheets().add("/fr/core/os/Core.css");
        panneau.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.Q) {
                Platform.exit();
            }
        });

        fenetre.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0); // arr�t de l'application
        });
        fenetre.show();
    }
}