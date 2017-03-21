package TestProjectJavaFX.main;
import TestProjectJavaFX.figure.MyFigureThreadedCircle;
import TestProjectJavaFX.figure.MyFigureThreadedRectangle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    private BorderPane bp;
    private GridPane pane;
    private GridPane grid1;
    private Button closeButton;
    private Button tn;
    private Scene scene;
    private Text scenetitle;
    private Random rnd = new Random();
    public final void start(Stage primaryStage){
            System.out.println();
            primaryStage.setTitle("FigureMoving via JavaFX");
            primaryStage.setMaxHeight(1000);
            primaryStage.setMaxWidth(1000);
            primaryStage.setResizable(false);
            bp = new BorderPane();
            pane = new GridPane();
            grid1 = new GridPane();
            pane.setPadding(new Insets(5, 5, 5, 5));
            grid1.setPadding(new Insets(5, 5, 5, 5));
            grid1.setHgap(5);
            grid1.setFocusTraversable(true);
            bp.setCenter(pane);
            bp.setBottom(grid1);
            scenetitle = new Text("Welcome to figure moving");
            scenetitle.setFont(Font.font("TimesNewRoman", FontWeight.NORMAL, 20));
            pane.getChildren().add(scenetitle);
            scene = new Scene(bp, 500, 275);
            tn = new Button("Add figure");
            closeButton = new Button("Close window");
            closeButton.setOnAction(actionEvent -> System.exit(1));
            tn.setOnAction(new EventHandler<ActionEvent>() {
                private int i = 0;
                private boolean b;
                @Override
                public void handle(ActionEvent event) {
                    grid1.requestFocus();
                    b = new Random().nextBoolean();
                    scenetitle.setTranslateX(++i*40);
                    scenetitle.setFill(new Color(rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255));
                    if (b)
                        pane.getChildren().add(new MyFigureThreadedCircle(10, (int) scene.getWidth(), (int)scene.getHeight()));
                    else pane.getChildren().add(new MyFigureThreadedRectangle(20, 20, (int)scene.getWidth(), (int)scene.getHeight()));
                }
            });
            grid1.add(tn, 0, 0);
            grid1.add(closeButton, 1, 0);
            
            bp.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.ENTER))
                        pane.getChildren().add(new MyFigureThreadedCircle(10, (int) scene.getWidth(), (int)scene.getHeight()));
                    else if (event.getCode().equals(KeyCode.ESCAPE)) {
                        Platform.exit();
                    }
                    else if (event.getCode().equals(KeyCode.SPACE))
                        pane.getChildren().add(new MyFigureThreadedRectangle(20, 20, (int)scene.getWidth(), (int)scene.getHeight()));
                }
            });

            /*Button newWindow = new Button("New window");
            grid1.add(newWindow, 2, 0);
            newWindow.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    new NewWindow();
                }
            });               */
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    private Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread th, Throwable ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    };
    public static void main(String[] args) {
        launch(args);
    }
}
