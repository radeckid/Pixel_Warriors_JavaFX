package pixel_warriors.htmlhelp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pixel_warriors.LoginDialog;

import java.io.IOException;

public class ShowRules {

    private Stage stage;
    private static ShowRules instance;
    private ShowRules() {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ShowRules getInstance() {
        if (instance == null) {
            synchronized (ShowRules.class) {
                if (instance == null) {
                    instance = new ShowRules();
                }
            }
        }
        return instance;
    }

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/htmlView.fxml"));

        stage = new Stage();
        stage.setTitle("Zasady gry");
        stage.setScene(new Scene((Parent) loader.load()));
        stage.getIcons().add(new Image("pixel_warriors/images/etc/Icona_gif.gif"));
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        if (LoginDialog.pStage.isShowing()) {
            stage.setX(LoginDialog.pStage.getX() + 50);
            stage.setY(LoginDialog.pStage.getY() + 50);
        }
    }

    public void showStage() {
        if (!stage.isShowing()) {
            stage.show();
        }
    }

}
