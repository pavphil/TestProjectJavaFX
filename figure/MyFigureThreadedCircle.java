package TestProjectJavaFX.figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static java.lang.Thread.sleep;

public class MyFigureThreadedCircle extends Circle {
    private Random rnd;
    private int sceneHeight;
    private int sceneWidth;
    public MyFigureThreadedCircle(int r, int width, int height){
        super(r);
        this.sceneHeight = height;
        this.sceneWidth = width;
        setTranslateX(Math.abs(new Random().nextInt()%sceneWidth));
        rnd = new Random();
        setFill(new Color(rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255));
        start();
    }
    private void start(){
        int steps = 30;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int movStep = 40;
                try {
                    int tHeight = sceneHeight;
                    for (int i = 0, j = 0; j < steps; j++) {
                        if (i <= sceneHeight) {
                            sceneHeight = tHeight;
                            setTranslateY(i);
                            i += movStep;
                        } else if (i >= sceneHeight) {
                            sceneHeight = 0;
                            i -= movStep;
                            setTranslateY(i);
                        }
                        sleep(500);
                    }
                }catch(InterruptedException ae){
                    ae.printStackTrace();
                }
                finally{
                    setVisible(false);
                }
            }
        }).start();
    }
}