package pixel_warriors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pixel_warriors.weaponanimation.WeaponAnimation;

import java.net.URL;
import java.util.ResourceBundle;

public class FightController implements Initializable {

    //fight panel
    @FXML
    private Button firstAtkBtn, secondAtkBtn, thirdAtkBtn, surrBtn;
    @FXML
    private Label weaponAnimLabel, enemyWeaponLabel;
    @FXML
    private ImageView weaponAnimImg, enemyWeaponImg; //TODO ustawiamy img przy rozpoczeciu misji
    private WeaponAnimation animation = new WeaponAnimation(70, 40);
    private WeaponAnimation enemyAnimation = new WeaponAnimation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void fightButtons(ActionEvent event) {

        if (event.getSource().equals(firstAtkBtn)) {
            if (!animation.isAnimated()) {
                animation.atkAnim(weaponAnimLabel);
            }
        } else if (event.getSource().equals(secondAtkBtn)) {
            if (!animation.isAnimated()) {
                enemyAnimation.enemyAtkAnim(enemyWeaponLabel);
            }
        } else if (event.getSource().equals(thirdAtkBtn)) {
            if (!animation.isAnimated()) {

            }
        } else if (event.getSource().equals(surrBtn)) {
            LoginDialog.primaryController.disableButtons(false, false, false, false, false, false, false);
            LoginDialog.primaryController.setPanels(false, false, false, false, true, false, false, false, false);
        }
    }

}
