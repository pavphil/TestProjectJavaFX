package TestProjectJavaFX.Figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by pavlovf on 16.03.2017.
 */
public class MyFigureThreadedRectangle extends Rectangle {
    private int width, height;
    private Random rnd = new Random();
    private int sceneHeight;
    public MyFigureThreadedRectangle(int width, int height, int sceneWidth, int sceneHeight){
        super(width, width);
        super.setTranslateX(Math.abs(new Random().nextInt()%sceneWidth));
        this.width = width;
        this.height = height;
        this.sceneHeight = sceneHeight;
        super.setFill(new Color(rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255));
        start();
    }
    private void start(){
        int step = 30;
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
