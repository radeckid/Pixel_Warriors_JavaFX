package pixel_warriors;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class ExitPrompt {

    public void makePrompt(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Juz uciekasz?!");
        alert.setHeaderText("Wylogować czy wyjść całkowicie?");
        alert.setContentText("Wybierz jedną z opcji:");

        alert.initOwner(stage);

        ButtonType buttonTypeOne = new ButtonType("Wyloguj");
        ButtonType buttonTypeTwo = new ButtonType("Wyjdź z gry");
        ButtonType buttonTypeThree = new ButtonType("Anuluj");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            stage.close();
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.makeLoginDialog();
        } else if (result.get() == buttonTypeThree) {
            alert.close();
        } else {
            stage.close();
        }

    }
}
