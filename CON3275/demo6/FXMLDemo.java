package CON3275.demo6;

import CON3275.Demo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLStreamHandler;
import java.nio.charset.Charset;

public class FXMLDemo implements Demo {

    @Override
    public Node getDemoNode() {
        return new FXMLPane() {
            {
                getStylesheets().add("CON3275/demo6/demo.css");
            }
        };
    }
}
