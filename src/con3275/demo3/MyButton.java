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
package con3275.demo3;

import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.util.Duration;

public class MyButton extends Button {

    public MyButton(String text) {
        super(text);
    }

    private StyleableObjectProperty<String> fadeTransition = new SimpleStyleableObjectProperty<String>(FADE_TRANSITION) {

        FadeTransition transition = null;

        @Override
        protected void invalidated() {

            String string = get();
            String[] args = string.split(",");

            double fromValue = toDouble(args[0], 1d);
            double toValue = toDouble(args[1], 0d);
            Duration duration = toDuration(args[2], Duration.ZERO);

            if (transition != null) {
                transition.stop();
            } else {
                transition = new FadeTransition();
                transition.setNode(MyButton.this);
            }

            transition.setFromValue(fromValue);
            transition.setToValue(toValue);
            transition.setDuration(duration);
            transition.setDelay(Duration.seconds(1));
            transition.playFromStart();
        }

        private double toDouble(String s, double def) {
            try {
                Double d = Double.valueOf(s);
                return d.doubleValue();
            } catch (NumberFormatException nfe) {
                return def;
            }
        }

        private Duration toDuration(String s, Duration def) {
            try {
                double ms = Double.valueOf(s);
                return Duration.millis(ms);
            } catch (NumberFormatException nfe) {
                return def;
            }
        }
    };

    private static final CssMetaData<MyButton, String> FADE_TRANSITION =
            new CssMetaData<MyButton, String>("-my-fade-transition", StyleConverter.getStringConverter()) {

                @Override
                public boolean isSettable(MyButton styleable) {
                    return true;
                }

                @Override
                public StyleableProperty<String> getStyleableProperty(MyButton styleable) {
                    return styleable.fadeTransition;
                }
            };


    private static List<CssMetaData<? extends Styleable, ?>> CSS_META_DATA;

    static {
        List<CssMetaData<? extends Styleable, ?>> cssMetaData = new ArrayList<>(Button.getClassCssMetaData());
        cssMetaData.add(FADE_TRANSITION);
        CSS_META_DATA = Collections.unmodifiableList(cssMetaData);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return CSS_META_DATA;
    }

}
