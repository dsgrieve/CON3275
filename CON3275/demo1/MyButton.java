package CON3275.demo1;

import javafx.css.CssMetaData;
import javafx.css.ParsedValue;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyButton extends Button {

    public MyButton(String text) {
        super(text);
    }

    private StyleableProperty<Effect> effect = new SimpleStyleableObjectProperty<Effect>(EFFECT) {
        @Override
        protected void invalidated() {
            setEffect(get());
        }
    };

    // Parse out "reflection(offset, fraction, topOpacity, bottomOpacity)"
    private static class MyEffectConverter extends StyleConverter<String,Effect> {

        @Override
        public Effect convert(ParsedValue<String, Effect> value, Font font) {

            String string = value.getValue();
            Effect effect = null;

            if("reflection(".regionMatches(true,0,string,0,"reflection(".length())) {
                String s = string.substring("reflection(".length(), string.lastIndexOf(')'));
                String[] args = s.split(",");
                double offset = valueOf(args[0], 0d);
                double fraction = valueOf(args[1], 1d);
                double topOpacity = valueOf(args[2], 1d);
                double bottomOpacity = valueOf(args[3], 0d);
                effect = new Reflection(offset, fraction, topOpacity, bottomOpacity);
            }
            return effect;
        }

        private double valueOf(String s, double def) {
            try {
                Double d = Double.valueOf(s);
                return d.doubleValue();
            } catch (NumberFormatException nfe) {
                return def;
            }
        }
    }

    private static final MyEffectConverter myEffectConverter = new MyEffectConverter();

    private static final CssMetaData<MyButton, Effect> EFFECT =
            new CssMetaData<MyButton, Effect>("-my-effect", myEffectConverter) {

                @Override
                public boolean isSettable(MyButton styleable) {
                    return true;
                }

                @Override
                public StyleableProperty<Effect> getStyleableProperty(MyButton styleable) {
                    return styleable.effect;
                }
            };


    private static List<CssMetaData<? extends Styleable, ?>> CSS_META_DATA;

    static {
        List<CssMetaData<? extends Styleable, ?>> cssMetaData = new ArrayList<>(Button.getClassCssMetaData());
        cssMetaData.add(EFFECT);
        CSS_META_DATA = Collections.unmodifiableList(cssMetaData);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return CSS_META_DATA;
    }

}
