package CON3275.demo4;

import CON3275.Demo;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by dgrieve on 9/15/14.
 */
public class ContentDemo implements Demo {

    @Override
    public Node getDemoNode() {
        ContentPane contentPane = new ContentPane(new Label("'The Node'"));
        contentPane.setContentLocation(ContentPane.ContentLocation.AFTER);

        RadioButton beforeButton = new RadioButton("Before");
        beforeButton.setSelected(false);
        beforeButton.setOnAction(evt -> contentPane.setContentLocation(ContentPane.ContentLocation.BEFORE));

        RadioButton afterButton = new RadioButton("After");
        afterButton.setSelected(true);
        afterButton.setOnAction(evt -> contentPane.setContentLocation(ContentPane.ContentLocation.AFTER));

        RadioButton noneButton = new RadioButton("None");
        noneButton.setSelected(false);
        noneButton.setOnAction(evt -> contentPane.setContentLocation(null));

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().setAll(beforeButton, afterButton, noneButton);

        HBox buttonBox = new HBox(5, beforeButton, afterButton, noneButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(contentPane);
        BorderPane.setAlignment(contentPane, Pos.CENTER);
        borderPane.setBottom(buttonBox);
        BorderPane.setAlignment(buttonBox, Pos.BOTTOM_CENTER);

        borderPane.getStylesheets().add("CON3275/demo4/demo.css");

        return borderPane;
    }
}
