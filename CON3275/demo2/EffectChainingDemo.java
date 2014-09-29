package CON3275.demo2;

import CON3275.Demo;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by dgrieve on 9/15/14.
 */
public class EffectChainingDemo implements Demo {

    @Override
    public Node getDemoNode() {
        MyButton myButton = new MyButton("JavaOne Demo");
        myButton.setStyle("-my-effect: \"reflection(0, 1, .8, .1) | box-blur(5, 5, 3)\";");
        return myButton;
    }

}
