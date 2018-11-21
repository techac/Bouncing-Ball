import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Frame extends Application {
    double ht,res;
    Simulation startSimulation;
    Scene simulateWindow,mainScene;
    @Override
    public void start(Stage primaryStage){
        Label setHeight= new Label("Height in meters");
        Label setE = new Label("Cofficient of Restitution");
        Label display_height = new Label("0.000");
        Slider height = new Slider(0,50,0);
        height.setMaxWidth(280);
        Label display_e = new Label("1.000");
        Slider e_value = new Slider(0,1,1);
        e_value.setMaxWidth(280);
        e_value.prefWidth(300);
        Button submit = new Button("Start simulation");
        VBox layout = new VBox(20);
        layout.getChildren().addAll(setHeight,display_height,height,setE,display_e,e_value,submit);
        layout.setAlignment(Pos.CENTER);
        mainScene=new Scene(layout,300,300);
        primaryStage.setScene(mainScene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        height.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                display_height.textProperty().setValue(

                        //String.valueOf((double) height.getValue()));
                String.format("%.3f",(double) height.getValue()));



            }
        });
        e_value.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                display_e.textProperty().setValue(
                        //String.valueOf((double) e_value.getValue()));
                        String.format("%.3f",(double) e_value.getValue()));


            }
        });

        submit.setOnAction(e->{

            ht=height.getValue();
            double oht = ht;
            res=e_value.getValue();
            Ball circle = new Ball();
            Group root = new Group();
            root.getChildren().addAll(circle.getCircle());
            Color col = Color.web("#80dfff");
            simulateWindow = new Scene(root,600,600,col);
            VBox result = new VBox(10);
            Button back = new Button("GO BACK");
            back.setAlignment(Pos.CENTER);
            back.setOnAction(ew->{
                height.setValue(0.0);
                e_value.setValue(1.0);
                primaryStage.setScene(mainScene);
            });
            // back.setLayoutX(simulateWindow.getWidth()- 50);
            // back.setLayoutY(simulateWindow.getHeight());
            Label title = new Label("JUMP HEIGHTS");
            // title.setFont(new Double(30.0));
            final double MAX_FONT_SIZE = 30.0; // define max font size you need
            title.setFont(new Font(MAX_FONT_SIZE)); // set to Label
            title.setAlignment(Pos.CENTER);
            result.getChildren().add(title);
            result.setLayoutX(0.75*(simulateWindow.getWidth()));
            result.setLayoutY(0.1*(simulateWindow.getHeight()));
            // root.getChildren().addAll(back);
            double ht=height.getValue();
            double ev=e_value.getValue();
            Label arr[] = new Label[10];
            for(int i=0;i<10;i++)
            {
                arr[i]= new Label((String.format("%.3f",(double) ht))+ " m");
                arr[i].setVisible(false);
                arr[i].setStyle("-fx-border-color: blue;");
                arr[i].setFont(new Font(30.0));
                arr[i].setAlignment(Pos.CENTER);
                result.getChildren().add(arr[i]);
                ht*=ev;
            }
            root.getChildren().add(result);
            result.getChildren().add(back);
            startSimulation = new Simulation(oht,res);
            startSimulation.simulate(circle.getCircle(),simulateWindow,oht);
            startSimulation.setArr(arr);
            startSimulation.setResult(result,simulateWindow);
            primaryStage.setScene(simulateWindow);
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

        });
        primaryStage.setTitle("Boucing Ball Simulation");

        primaryStage.show();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
