package pixel_warriors.weaponanimation;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class WeaponAnimation {

    private final Path path = new Path();
    private boolean isAnimated = false;

    public WeaponAnimation() {
    }

    public WeaponAnimation(int x, int y) {
        path.getElements().add(new MoveTo(x, y));
        CubicCurveTo curve = new CubicCurveTo();
        curve.setControlX1(125);
        curve.setControlY1(-30);
        curve.setControlX2(235);
        curve.setControlY2(-30);
        curve.setX(290);
        curve.setY(40);
        path.getElements().add(curve);
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    private void setIsAnimated(boolean isAnimated) {
        this.isAnimated = isAnimated;
    }

    public void atkAnim(Label weapon) {
        setIsAnimated(true);
        weapon.setVisible(true);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(500));
        pathTransition.setPath(path);
        pathTransition.setNode(weapon);

        RotateTransition rotateTransition = new RotateTransition(pathTransition.getDuration(), weapon);
        rotateTransition.setFromAngle(-50);
        rotateTransition.setToAngle(60);

        ParallelTransition parallelTransition = new ParallelTransition(pathTransition, rotateTransition);
        parallelTransition.play();

        parallelTransition.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                weapon.setVisible(false);
                setIsAnimated(false);
            }
        });

    }

    public void enemyAtkAnim(Label enemyWeapon) {
        setIsAnimated(true);
        enemyWeapon.setVisible(true);

        TranslateTransition transition = new TranslateTransition(Duration.millis(500), enemyWeapon);
        transition.setFromX(0);
        transition.setToX(-220);
        transition.play();

        transition.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enemyWeapon.setVisible(false);
                setIsAnimated(false);
            }
        });

    }
}
