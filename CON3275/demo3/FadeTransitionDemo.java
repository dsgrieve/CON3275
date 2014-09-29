package CON3275.demo3;

import CON3275.Demo;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by dgrieve on 9/15/14.
 */
public class FadeTransitionDemo implements Demo {

    @Override
    public Node getDemoNode() {
        MyButton myButton = new MyButton("JavaOne Demo");
        myButton.setOpacity(0);
        myButton.setStyle("-my-fade-transition: \"0, 1, 2000\";");
        return myButton;
    }

}
