package pixel_warriors.htmlhelp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pixel_warriors.Main;

import java.io.IOException;

public class ShowRules {

    private Stage stage;

    public ShowRules() {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/htmlView.fxml"));

        stage = new Stage();
        stage.setTitle("Zasady gry");
        stage.setScene(new Scene((Parent) loader.load(), 800, 500));
        stage.getIcons().add(new Image("pixel_warriors/images/etc/Icona_gif.gif"));
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        if (Main.getStage().isShowing()) {
            stage.setX(Main.getStage().getX() + 50);
            stage.setY(Main.getStage().getY() + 50);
        }
    }

    public void showStage() {
        if (stage.isShowing()) {
            stage.close();
        } else if (!stage.isShowing()) {
            stage.show();
        }
    }

}
