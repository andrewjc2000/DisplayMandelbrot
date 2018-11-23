package fx;

import graphics.CustomColorScheme;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import launcher.Globals;
import math.ComplexComputation;
import math.CxNum;

import java.util.ArrayList;


public class MainApp extends Application {

    private static MandelbrotImageWriter writer;

    public static void main(String[] args) {
        Globals.frameWidth = 1000;
        Globals.frameHeight = 650;
        Globals.topLeft = new CxNum(-1.75, -1);
        Globals.bottomRight = new CxNum(1, 1);
        Globals.maxIt = 20;

        ArrayList<java.awt.Color> as = new ArrayList<>();
        as.add(java.awt.Color.red);
        //as.add(new java.awt.Color(255, 0, 255));
        //as.add(new java.awt.Color(255, 127, 255));
        //as.add(new java.awt.Color(127, 127, 127));
        as.add(java.awt.Color.blue);

        ArrayList<Integer> is = new ArrayList<>();
        //is.add(500);
        //is.add(1000);
        //is.add(1500);

        Globals.cols = new CustomColorScheme(as, is, 20);

        writer = new MandelbrotImageWriter(Globals.frameWidth, Globals.frameHeight, Globals.topLeft, Globals.bottomRight,
                Globals.cols);
        writer.calculatePixels(cxNum -> ComplexComputation.getMandelbrotValue(cxNum, Globals.maxIt));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mandelbrot Set Explorer v. 1.2");
        Group root = new Group();
        Canvas mainCanvas = new Canvas(Globals.frameWidth, Globals.frameHeight);
        GraphicsContext gContext = mainCanvas.getGraphicsContext2D();
        draw(gContext);
        root.getChildren().add(mainCanvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void draw(GraphicsContext g){
        g.drawImage(writer.getImage(), 0, 0);
    }
}
