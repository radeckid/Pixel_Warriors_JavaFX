package pixel_warriors.weaponanimation;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
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
        path.getElements().add(new MoveTo(70, 40));
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

}
