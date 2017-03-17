package TestProjectJavaFX.testedpack;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewWindow {
    public NewWindow(){
        Stage stage1 = new Stage();
        BorderPane bpNew = new BorderPane();
        Scene scene1 = new Scene(bpNew, 500, 275);
        GridPane gpNew = new GridPane();
        bpNew.setCenter(gpNew);
        Image img = new Image("https://avatanplus.com/files/resources/mid/57d993e5eb5e515729e9ba4c.png");
        ImageView imgView = new ImageView(img);
        stage1.setHeight(img.getHeight());
        stage1.setWidth(img.getWidth());
        gpNew.add(imgView, 0, 1);
        scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ESCAPE)) {
                    System.out.println("FF");
                    stage1.close();
                }
            }
        });
        stage1.setScene(scene1);
        stage1.show();
    }
}
