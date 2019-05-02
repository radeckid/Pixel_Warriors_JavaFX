package pixel_warriors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("pixelwarriors.fxml"));
        primaryStage.setScene(new Scene((Parent) loader.load(), 800, 725));
        primaryStage.setMinHeight(765);
        primaryStage.setMinWidth(816);
        primaryStage.getIcons().add(new Image((this.getClass().getResource("images/etc/Icona_gif.gif").toString())));
        primaryStage.setTitle("Pixel Warriors");
        Controller controller = loader.getController();

//        LoginDialog loginDialog = new LoginDialog();
//        loginDialog.primaryController = controller;
//        loginDialog.pStage = primaryStage;
//        loginDialog.makeLoginDialog();
       primaryStage.show();  //TODO do wyrzucenia jak logowanie włączymy
    }

    public static void main(String[] args) {
        launch(args);
    }
}
