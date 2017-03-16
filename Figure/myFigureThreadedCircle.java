package TestProjectJavaFX.Figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static java.lang.Thread.sleep;

public class myFigureThreadedCircle extends Circle {
    private Random rnd;
    private int sceneHeight;
    private int sceneWidth;
    public myFigureThreadedCircle(int r, int width, int height){
        super(r);
        this.sceneHeight = height;
        this.sceneWidth = width;
        super.setTranslateX(Math.abs(new Random().nextInt()%sceneWidth));
        rnd = new Random();
        super.setFill(new Color(rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255));
        start();
    }
    private void start(){
        int step = 30;
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean vis = true;
                int tmp = 0;
                try {
                    int tHeight = sceneHeight;
                    for (int i = 0,j = 0; i < 6000; j++ ) {
                        if (j == step)
                            break;
                        if (i <= sceneHeight) {
                            sceneHeight = tHeight;
                            setTranslateY(i);
                            i += step;
                        } else if (i > sceneHeight) {
                            sceneHeight = 0;
                            i -= step;
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