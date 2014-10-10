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

import com.sun.javafx.css.PseudoClassState;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * BreadcrumbBar
 */
public class BreadcrumbBar extends HBox {

    private String delimiter = "/";
    private List<Button> buttons = new ArrayList<>();

    public BreadcrumbBar() {
        super(0);
        getStyleClass().setAll("breadcrumb-bar");
        setFillHeight(true);
        setAlignment(Pos.CENTER_LEFT);
    }

    public StringProperty pathProperty() { return path; } 
    private StringProperty path = new SimpleStringProperty(BreadcrumbBar.this, "path", null) {
    
        @Override protected void invalidated() {

            String value = get();
            String[] parts = value != null ? value.split(delimiter) : new String[0];
            String pathSoFar = "";
            for (int i = 0; i < Math.max(parts.length, buttons.size()); i++) {
                if (i < parts.length) {
                    pathSoFar += (i == 0) ? parts[i] : delimiter + parts[i];
                    final String currentPath = pathSoFar;
                    Button button = null;
                    if (i < buttons.size()) {
                        button = buttons.get(i);
                    } else {
                        button = new Button(parts[i]);
                        button.setMaxHeight(Double.MAX_VALUE);
                        buttons.add(button);
                        getChildren().add(button);
                    }
                    button.pseudoClassStateChanged(last_child_pseudo_class, parts.length > 1 && i == parts.length - 1);
                    button.pseudoClassStateChanged(first_child_pseudo_class, parts.length > 1 && i == 0);
                    button.pseudoClassStateChanged(only_child_pseudo_class, parts.length == 1);
                    button.setVisible(true);
                    button.setText(parts[i]);
                    button.setOnAction(event -> System.err.println(currentPath));
                    System.err.println("button: " + button.getText() + " pseudo-class state: " + button.getPseudoClassStates());
                } else {
                    // don't need this button for now
                    buttons.get(i).setVisible(false);
                }
            }
        }
    };

    private static final PseudoClass first_child_pseudo_class = PseudoClass.getPseudoClass("first-child");
    private static final PseudoClass last_child_pseudo_class = PseudoClass.getPseudoClass("last-child");
    private static final PseudoClass only_child_pseudo_class = PseudoClass.getPseudoClass("only-child");

}
