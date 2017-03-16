package TestProjectJavaFX.Figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by pavlovf on 16.03.2017.
 */
public class myFigureThreadedRectangle extends Rectangle {
    private int width, height;
    private Random rnd = new Random();
    private int sceneHeight;
    public myFigureThreadedRectangle(int width, int height, int sceneWidth, int sceneHeight){
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean vis = true;
                try {
                    int tHeight = sceneHeight;
                    for (int i = 0, j = 0; i < 6000; j++ ) {
                        if (j == step)
                            break;
                        if (i <= sceneHeight) {
                            sceneHeight = tHeight;
                            setTranslateY(i);
                            i += step;
                        }
                        else if (i > sceneHeight) {
                            sceneHeight = 0;
                            i-= step;
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
