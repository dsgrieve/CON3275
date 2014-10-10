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
package con3275;

import con3275.demo1.ReflectionDemo;
import con3275.demo2.EffectChainingDemo;
import con3275.demo3.FadeTransitionDemo;
import con3275.demo4.ContentDemo;
import con3275.demo5.BreadcrumbDemo;
import con3275.demo6.FXMLDemo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by dgrieve on 9/15/14.
 */
public class DemoApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Demo[] demoSuppliers = new Demo[] {
                new ReflectionDemo(),
                new EffectChainingDemo(),
                new FadeTransitionDemo(),
//                new ContentDemo(),
                new BreadcrumbDemo(),
                new FXMLDemo()
        };

        Pagination pagination = new Pagination(demoSuppliers.length);
        pagination.setStyle("-fx-font-size: 28;");
        pagination.setPageFactory(index -> demoSuppliers[index].getDemoNode());

        StackPane stackPane = new StackPane(pagination);
        Scene scene = new Scene(stackPane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
