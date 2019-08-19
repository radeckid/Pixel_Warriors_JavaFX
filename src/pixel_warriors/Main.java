package pixel_warriors;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private static Stage stage;
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/pixelwarriors.fxml"));
        stage = primaryStage;

        primaryStage.setScene(new Scene( (Parent) loader.load(), 800, 725));
        primaryStage.setMinHeight(765);
        primaryStage.setMinWidth(816);
        primaryStage.getIcons().add(new Image((this.getClass().getResource("images/etc/Icona_gif.gif").toString())));
        primaryStage.setTitle("Pixel Warriors");

        controller = loader.getController();

        LoginDialog loginDialog = new LoginDialog();
        LoginDialog.primaryController = getController();
        LoginDialog.pStage = getStage();
//        loginDialog.makeLoginDialog();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                controller.musicPlay(false);
                ExitPrompt exitPrompt = new ExitPrompt();
                exitPrompt.makePrompt(getStage());
            }
        });

        primaryStage.show();  //TODO do wyrzucenia jak logowanie włączymy

    }

    public static Stage getStage() {
        return stage;
    }

    public static Controller getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
