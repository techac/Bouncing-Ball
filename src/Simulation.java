
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Simulation {
    int time = 25;
    double dy = 2;
    double g = 0.98, e = 0;
    double height;
    double previousHeight,diffHeight=0;
    Label arr[];
    int idx;
    VBox result ;
    Scene simulateWindow;

    public Simulation(double ht,double res)
    {
        idx=0;
        height=ht;
        e=res;

    }
    public void setArr(Label[] arr)
    {
        this.arr  = arr;
    }
    public void setResult(VBox result,Scene simulateWindow)
    {
        this.result = result;
        this.simulateWindow = simulateWindow;
    }
    public void simulate(Circle ball, Scene simulateWindow, double height) {
        //System.out.println(height);
    ball.setLayoutY(simulateWindow.getHeight()-(height*10)-10);

        previousHeight= simulateWindow.getHeight();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(time),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        simulateWindow.heightProperty().addListener(new ChangeListener<Number>() {
                            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                                diffHeight = simulateWindow.getHeight()  -previousHeight;
                                dy=2;

                                ball.setLayoutY(simulateWindow.getHeight() - height*10-10);
                            }
                        });
                       simulateWindow.widthProperty().addListener(new ChangeListener<Number>(){
                            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth){
                                result.setLayoutX(0.85*(simulateWindow.getWidth()));
                                result.setLayoutY(0.05*(simulateWindow.getHeight()));
                            }
                        });

                        if(height!=0) {
                            if (ball.getLayoutY() + ball.getRadius() + dy > simulateWindow.getHeight()) {
                                dy = -dy;
                                dy = dy * e;
                                if (idx < 10)
                                    arr[idx++].setVisible(true);

                            } else {
                                dy += g;
                            }
                            ball.setLayoutY((ball.getLayoutY() + dy));
                        }
                        else
                        {
                            for(int i=0;i<10;i++)
                            {
                                arr[i].setVisible(true);
                            }
                        }
                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        simulateWindow.getStylesheets().add
        (Frame.class.getResource("Label.css").toExternalForm());
    }

}
