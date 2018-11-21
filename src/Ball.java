
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball  {
    private Circle circle;
    double y;
    public Circle getCircle() {
        return circle;
    }

    Ball(){
        circle= new Circle(10, Color.RED);
        circle.setStroke(Color.BLACK);
        circle.getStroke();
        circle.relocate(150,25);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}