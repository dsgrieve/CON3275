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
package con3275.demo4;

import con3275.Demo;
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

        borderPane.getStylesheets().add("con3275/demo4/demo.css");

        return borderPane;
    }
}
