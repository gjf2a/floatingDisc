import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {
    Circle[] circles;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            Scene scene = go.getScene();
            Pane pane = (Pane)scene.getRoot();
            circles = new Circle[5];
            for (int i = 0; i < circles.length; i++) {
                circles[i] = new Circle(0, 0, 10.0, Color.AQUA);
                circles[i].setTranslateX(randomCoord(pane.getWidth()));
                circles[i].setTranslateY(randomCoord(pane.getHeight()));
                pane.getChildren().add(circles[i]);
            }
        });
    }

    @FXML
    Button go;
    DiscLocation discState;
    int movingCircle;

    @FXML
    public void launch() {
        Scene scene = go.getScene();
        Pane pane = (Pane)scene.getRoot();

        movingCircle = (int)(Math.random() * circles.length);
        Circle target = circles[movingCircle];
        double endX = randomCoord(pane.getWidth());
        double endY = randomCoord(pane.getHeight());
        discState = new DiscLocation(target.getTranslateX(), target.getTranslateY(), endX, endY, 2.0);
        timer.start();
    }

    private double randomCoord(double dimension) {
        return Math.random() * dimension;
    }

    private final AnimationTimer timer = new AnimationTimer() {

        private long last = 0;

        @Override
        public void handle(long now) {
            long FRAMES_PER_SEC = 50L;
            long INTERVAL = 1000000000L / FRAMES_PER_SEC;
            if (now - last > INTERVAL) {
                last = now;
                if (discState.done()) {
                    stop();
                } else {
                    discState.update(circles[movingCircle]);
                }
            }
        }
    };
}
