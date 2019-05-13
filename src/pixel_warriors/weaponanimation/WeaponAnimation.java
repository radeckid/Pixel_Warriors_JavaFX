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

    private boolean isAnimated = false;
    private int duration;

    public boolean isAnimated() {
        return isAnimated;
    }

    public int getDuration() {
        return duration;
    }

    private void setIsAnimated(boolean isAnimated) {
        this.isAnimated = isAnimated;
    }

    public Path getPath(int fromX, int fromY, int toX, int toY, int conX1, int conY1, int conX2, int conY2) {
        Path path = new Path();
        path.getElements().add(new MoveTo(fromX, fromY));
        CubicCurveTo curve = new CubicCurveTo();
        curve.setControlX1(conX1);  //125
        curve.setControlY1(conY1);  //-30
        curve.setControlX2(conX2);  //235
        curve.setControlY2(conY2);  //-30
        curve.setX(toX);  //290
        curve.setY(toY);   //40
        path.getElements().add(curve);

        return path;
    }

    public void atkAnim(Label weapon, int fromAngle, int toAngle, int duration, Path path) {
        setIsAnimated(true);

        PathTransition pathTransition = new PathTransition();

        this.duration = duration;

        pathTransition.setDuration(Duration.millis(duration));
        pathTransition.setPath(path);
        pathTransition.setNode(weapon);

        RotateTransition rotateTransition = new RotateTransition(pathTransition.getDuration(), weapon);
        rotateTransition.setFromAngle(fromAngle);
        rotateTransition.setToAngle(toAngle);

        ParallelTransition parallelTransition = new ParallelTransition(pathTransition, rotateTransition);

        weapon.setVisible(true);

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
