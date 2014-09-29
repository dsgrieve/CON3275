package CON3275.demo5;

import CON3275.Demo;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * Created by dgrieve on 9/15/14.
 */
public class BreadcrumbDemo implements Demo {

    @Override
    public Node getDemoNode() {

        BreadcrumbBar breadcrumbBar = new BreadcrumbBar();

        ListView<String> samplePaths = new ListView<>();
        samplePaths.getItems().addAll(
                "javafx",
                "javafx/scene",
                "javafx/scene/chart",
                "javafx/scene/control",
                "javafx/scene/control/cell"
        );

        breadcrumbBar.pathProperty().bind(samplePaths.getSelectionModel().selectedItemProperty());
        samplePaths.getSelectionModel().selectFirst();

        VBox vbox = new VBox(5, breadcrumbBar, samplePaths);
        vbox.getStylesheets().add("CON3275/demo5/demo.css");
        return vbox;
    }
}
