package CON3275;

import CON3275.demo1.ReflectionDemo;
import CON3275.demo2.EffectChainingDemo;
import CON3275.demo3.FadeTransitionDemo;
import CON3275.demo4.ContentDemo;
import CON3275.demo5.BreadcrumbDemo;
import CON3275.demo6.FXMLDemo;
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
