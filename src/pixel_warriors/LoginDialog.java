package pixel_warriors;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pixel_warriors.connectiondb.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class LoginDialog {
    private Dialog<ButtonType> dialog;
    private String headerText = "Witaj wojowniku!";
    static Stage pStage;
    static Controller primaryController;
    private Connection connection;

    LoginDialog() {
        connection = ConnectionDB.getInstance().getConnection();
    }

    public void makeLoginDialog() {
        dialog = new Dialog<>();
        dialog.setTitle("Pixel Warriors");

        dialog.setHeaderText(headerText);
        dialog.setGraphic(new ImageView(this.getClass().getResource("images\\etc\\padlock2.gif").toString()));

        //Set windows icon
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("images\\etc\\padlock2.gif").toString()));

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 100, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Login");
        PasswordField password = new PasswordField();
        password.setPromptText("Hasło");

        grid.add(new Label("Login:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Hasło:"), 0, 1);
        grid.add(password, 1, 1);

        //Enable/Disable login button
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Username field focus
        Platform.runLater(() -> username.requestFocus());

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            try {
                dbConntect(username.getText(), password.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void dbConntect(String user, String pass) throws SQLException {

        String sql = "SELECT Nick, Password, IDPLayer, statistics.Level FROM players, statistics WHERE players.IDPLayer=statistics.IDPLayer_Statistic AND Nick='" + user + "' AND Password='" + pass + "';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            String characterName = resultSet.getString(3);
            String characterLvl = resultSet.getString(4);
            primaryController.setUserNameLabel("Zalogowano: " + characterName);
            primaryController.setLvl_Indicator((characterLvl));
            primaryController.musicPlay(true);
            pStage.show();
        } else if (resultSet.next() == false) {
            headerText = "Błędny login lub hasło!";
            makeLoginDialog();
        }
    }
}
