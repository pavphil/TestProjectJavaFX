package TestProjectJavaFX.Figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static java.lang.Thread.sleep;

public class myFigureThreadedCircle extends Circle {
    private Random rnd;
    private int height;
    public myFigureThreadedCircle(int r, int width, int height){
        super(r);
        this.height = height;
        super.setTranslateX(Math.abs(new Random().nextInt()%width));
        rnd = new Random();
        super.setFill(new Color(rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255, rnd.nextDouble()%255));
        start();
    }
    private void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean vis = true;
                int tmp = 0;
                try {
                    int tHeight = height;
                    for (int i = 0; i < 6000; ) {
                        if (i <= height) {
                            height = tHeight;
                            setTranslateY(i);
                            i += 30;
                        }
                        else if (i > height) {
                            height = 0;
                            i-= 30;
                            setTranslateY(i);
                        }
                        sleep(500);
                    }
                }catch(InterruptedException ae){
                    ae.printStackTrace();
                }
                finally{
                    //setVisible(false);
                }
            }
        }).start();
    }
}