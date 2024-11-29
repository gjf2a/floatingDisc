import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Window;

public class Controller {
    Circle[] circles;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            Scene scene = go.getScene();
            Window window = scene.getWindow();
            circles = new Circle[2];
            for (int i = 0; i < circles.length; i++) {
                circles[i] = new Circle(0.0,0.0, 10.0, Color.AQUA);
                circles[i].setTranslateX(Math.random() * window.getWidth());
                circles[i].setTranslateY(Math.random() * window.getHeight());
                Pane pane = (Pane)scene.getRoot();
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
        Window window = scene.getWindow();
        movingCircle = (int)(Math.random() * circles.length);
        Circle target = circles[movingCircle];
        double endX = Math.random() * window.getWidth();
        double endY = Math.random() * window.getHeight();
        discState = new DiscLocation(target.getTranslateX(), target.getTranslateY(), endX, endY, 2.0);
        timer.start();
    }

    private AnimationTimer timer = new AnimationTimer() {

        private final long FRAMES_PER_SEC = 50L;
        private final long INTERVAL = 1000000000L / FRAMES_PER_SEC;
        private long last = 0;

        @Override
        public void handle(long now) {
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
