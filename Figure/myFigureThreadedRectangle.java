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
    public myFigureThreadedRectangle(int width, int height, int sceneWidth){
        super(width, width);
        super.setTranslateX(Math.abs(new Random().nextInt()%sceneWidth));
        this.width = width;
        this.height = height;
        super.setFill(new Color(rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255));
        start();
    }
    private void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean vis = true;

                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread());
                        sleep(500);
                        myFigureThreadedRectangle.super.setTranslateY(15*i);
                    }
                }catch(InterruptedException ae){
                    ae.printStackTrace();
                }
                finally{
                    myFigureThreadedRectangle.super.setVisible(false);
                }
            }
        }).start();
    }
}
