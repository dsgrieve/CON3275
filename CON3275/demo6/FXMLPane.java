package CON3275.demo6;

import javafx.css.CssMetaData;
import javafx.css.ParsedValue;
import javafx.css.SimpleStyleableStringProperty;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.StyleableStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class FXMLPane extends StackPane {

    public FXMLPane() {
        super();
        getStyleClass().setAll("fxml-pane");
    }

    private final StyleableStringProperty fxml = new SimpleStyleableStringProperty(CSS_FXML, FXMLPane.this, "fxml", null) {
        @Override protected void invalidated() {
            String value = get();
            if (value != null) {
                try {
                    URL url = URI.create(value).toURL();
                    Node node = (Node)url.getContent();
                    getChildren().setAll(node);
                } catch (IOException  e) {
                    System.err.println(e);
                }
            }
        }
    };

    private static class DataUriConverter extends StyleConverter<String, String> {
        @Override
        public String convert(ParsedValue<String, String> value, Font font) {
            try {
                String data = value.getValue();
                String uri = "fxml:," + URLEncoder.encode(data, "UTF-8");
                return uri;
            } catch (UnsupportedEncodingException e) {
                System.err.println(e);
            }
            return null;
        }
    }

    private static final CssMetaData<FXMLPane, String> CSS_FXML =
            new CssMetaData<FXMLPane, String>("-my-fxml", new DataUriConverter()) {
                @Override
                public boolean isSettable(FXMLPane styleable) {
                    return true;
                }

                @Override
                public StyleableProperty<String> getStyleableProperty(FXMLPane styleable) {
                    return styleable.fxml;
                }
            };

    private static final List<CssMetaData<? extends Styleable, ?>> CSS_META_DATA;
    static {
        List<CssMetaData<? extends Styleable, ?>> list = new ArrayList<>(StackPane.getClassCssMetaData());
        list.add(CSS_FXML);
        CSS_META_DATA = Collections.unmodifiableList(list);
    }

    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return CSS_META_DATA;
    }



    private static final URLStreamHandler FXML_STREAM_HANDLER = new URLStreamHandler() {
        @Override
        protected URLConnection openConnection(URL url) throws IOException {
            return new FXMLConnection(url);
        }
    };

    private static class FXMLConnection extends URLConnection {

        FXMLConnection(URL url) {
            super(url);
        }

        @Override
        public void connect() throws IOException {
        }

        @Override
        public InputStream getInputStream() throws IOException {
            String file = url.getFile();
            String data = file.substring(file.indexOf(',') + 1);
            return new ByteArrayInputStream(URLDecoder.decode(data, "UTF-8").getBytes());
        }

        @Override
        public Object getContent() throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(Charset.forName("UTF-8"));
                Object obj = loader.load(getInputStream());
                return obj;
            } catch (javafx.fxml.LoadException e) {
                System.err.println(e.getCause());
            }
            return new Rectangle(50,50);
        }
    }

    static {
        URL.setURLStreamHandlerFactory(protocol -> {
            if ("fxml".equals(protocol)) {
                return FXML_STREAM_HANDLER;
            }
            return null;
        });
    }

}
