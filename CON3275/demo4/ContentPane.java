package CON3275.demo4;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentPane extends Region {

    public static enum ContentLocation { BEFORE, AFTER }

    private Node node;
    private Text text;

    public ContentPane(Node node) {
        super();
        getStyleClass().addAll(node.getStyleClass());
        this.node = node;
        this.text = new Text();
        getChildren().addAll(node, text);
    }

    private static final PseudoClass PSEUDO_CLASS_BEFORE = PseudoClass.getPseudoClass("before");
    private static final PseudoClass PSEUDO_CLASS_AFTER = PseudoClass.getPseudoClass("after");

    //
    // contentLocation property
    //
    private ObjectProperty<ContentLocation> contentLocation = new SimpleObjectProperty<ContentLocation>(this, "contentLocation", null) {
        @Override
        protected void invalidated() {
            pseudoClassStateChanged(PSEUDO_CLASS_BEFORE, get() == ContentLocation.BEFORE);
            pseudoClassStateChanged(PSEUDO_CLASS_AFTER, get() == ContentLocation.AFTER);
            if (get() == null) {
                getChildren().setAll(node);
            } else {
                getChildren().setAll(node, text);
            }
            requestLayout();
        }
    };

    public ObjectProperty<ContentLocation> contentLocationProperty() {
        return contentLocation;
    }

    public void setContentLocation(ContentLocation location) {
        contentLocationProperty().set(location);
    }

    public ContentLocation getContentLocation() {
        return contentLocationProperty().get();
    }

    //
    // content property
    //
    private StyleableObjectProperty<String> content = new SimpleStyleableObjectProperty<String>(CONTENT) {
        @Override protected void invalidated() {
            text.setText(get());
            requestLayout();
        }
    };

    public ObjectProperty<String> contentProperty() {
        return (ObjectProperty<String>)content;
    }

    public void setContent(String content) {
        contentProperty().set(content);
    }

    public String getContent() {
        return contentProperty().get();
    }

    private static final CssMetaData<ContentPane, String> CONTENT =
            new CssMetaData<ContentPane, String>("content", StyleConverter.getStringConverter()) {

                @Override
                public boolean isSettable(ContentPane styleable) {
                    return !styleable.contentProperty().isBound();
                }

                @Override
                public StyleableProperty<String> getStyleableProperty(ContentPane styleable) {
                    return styleable.content;
                }
            };


    private static List<CssMetaData<? extends Styleable, ?>> CSS_META_DATA;

    static {
        List<CssMetaData<? extends Styleable, ?>> cssMetaData = new ArrayList<>(Region.getClassCssMetaData());
        cssMetaData.add(CONTENT);
        CSS_META_DATA = Collections.unmodifiableList(cssMetaData);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return CSS_META_DATA;
    }

    @Override
    protected void layoutChildren() {

        double nodeWidth = node.prefWidth(Region.USE_COMPUTED_SIZE);
        double nodeHeight = node.prefHeight(Region.USE_COMPUTED_SIZE);
        double textWidth = text.prefWidth(Region.USE_COMPUTED_SIZE);
        double textHeight = text.prefHeight(Region.USE_COMPUTED_SIZE);
        double contentHeight = Math.max(nodeHeight, textHeight);

        ContentLocation location = getContentLocation();
        if (location == ContentLocation.AFTER) {
            layoutInArea(node, 0, 0, nodeWidth, contentHeight, 0, HPos.LEFT, VPos.CENTER);
            layoutInArea(text, nodeWidth + 3, 0, textWidth, contentHeight, 0, HPos.LEFT, VPos.CENTER);
        } else if (location == ContentLocation.BEFORE) {
            layoutInArea(text, 0, 0, textWidth, contentHeight, 0, HPos.LEFT, VPos.CENTER);
            layoutInArea(node, textWidth + 3, 0, nodeWidth, contentHeight, 0, HPos.LEFT, VPos.CENTER);
        } else {
            layoutInArea(node, 0, 0, nodeWidth, contentHeight, 0, HPos.LEFT, VPos.CENTER);
        }
    }

    @Override
    protected double computeMinWidth(double height) {
        return computePrefWidth(height);
    }

    @Override
    protected double computeMinHeight(double width) {
        return computePrefHeight(width);
    }

    @Override
    protected double computePrefWidth(double height) {
        double pref0 = node.prefWidth(height);
        double pref1 = text.prefWidth(height);
        return pref0 + pref1;
    }

    @Override
    protected double computePrefHeight(double width) {
        double pref0 = node.prefHeight(width);
        double pref1 = text.prefHeight(width);
        return Math.max(pref0, pref1);
    }

    @Override
    protected double computeMaxWidth(double height) {
        return computePrefWidth(height);
    }

    @Override
    protected double computeMaxHeight(double width) {
        return computePrefHeight(width);
    }
}
