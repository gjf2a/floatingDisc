import javafx.scene.shape.Shape;

public class DiscLocation {
    private double x, y;
    private double goalX, goalY;
    private double velocity;

    public DiscLocation(double startX, double startY, double goalX, double goalY, double velocity) {
        this.x = startX;
        this.y = startY;
        this.goalX = goalX - startX;
        this.goalY = goalY - startY;
        this.velocity = velocity;
    }

    public boolean done() {
        return x == goalX && y == goalY;
    }

    public void update(Shape shape) {
        double xDist = goalX - shape.getTranslateX();
        double yDist = goalY - shape.getTranslateY();
        if (yDist == 0.0 || Math.random() < 0.5) {
            double xTravel = Math.min(velocity, xDist);
            shape.setTranslateX(shape.getTranslateX() + xTravel);
            x = shape.getTranslateX();
        } else {
            double yTravel = Math.min(velocity, yDist);
            shape.setTranslateY(shape.getTranslateY() + yTravel);
            y = shape.getTranslateY();
        }
    }
}
