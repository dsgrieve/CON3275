/* The MIT License (MIT)
 *
 * Copyright (c) 2014 David S. Grieve
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package con3275.demo5;

import con3275.Demo;
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
        vbox.getStylesheets().add("con3275/demo5/demo.css");
        return vbox;
    }
}
