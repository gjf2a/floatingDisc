import javafx.scene.shape.Shape;

public class DiscLocation {
    private double x, y;
    private double goalX, goalY;
    private double velocity;

    public DiscLocation(double startX, double startY, double goalX, double goalY, double velocity) {
        this.x = startX;
        this.y = startY;
        this.goalX = goalX;
        this.goalY = goalY;
        this.velocity = velocity;
    }

    public boolean done() {
        return x == goalX && y == goalY;
    }

    public void update(Shape shape) {
        double xDist = goalX - x;
        double yDist = goalY - y;
        if (yDist == 0.0 || Math.random() < 0.5) {
            double xTravel = Math.min(velocity, xDist);
            x += xTravel;
        } else {
            double yTravel = Math.min(velocity, yDist);
            y += yTravel;
        }
        shape.setTranslateX(x);
        shape.setTranslateY(y);
    }
}
