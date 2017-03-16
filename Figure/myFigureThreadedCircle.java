package TestProjectJavaFX.Figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static java.lang.Thread.sleep;

public class myFigureThreadedCircle extends Circle {
    private Random rnd;
    public myFigureThreadedCircle(int r, int width){
        super(r);
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
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread());
                        sleep(500);
                        myFigureThreadedCircle.super.setTranslateY(15*i);
                    }
                }catch(InterruptedException ae){
                    ae.printStackTrace();
                }
                finally{
                    myFigureThreadedCircle.super.setVisible(false);
                }
            }
        }).start();
    }
}