package CON3275.demo6;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dgrieve on 9/23/14.
 */
public class FXMLDemoController {

    public void initialize() {
        System.out.println("FXMLController");
    }

    @FXML
    private Button button;

    @FXML
    protected void buttonAction(javafx.event.ActionEvent event) {
        button.setStyle("-fx-base: red;");
    }
}
