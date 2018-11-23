package fx;

import graphics.CustomColorScheme;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import math.CxNum;
import java.util.ConcurrentModificationException;
import java.util.function.Function;

public class MandelbrotImageWriter {

    private CustomColorScheme colorScheme;
    private WritableImage image;
    private int width, height;
    private CxNum topLeft, bottomRight;
    private boolean writing;

    public WritableImage getImage(){
        return image;
    }

    public MandelbrotImageWriter(int initialWidth, int initialHeight, CxNum topLeft, CxNum bottomRight, CustomColorScheme colorScheme) {
        image = new WritableImage(initialWidth, initialHeight);
        width = initialWidth;
        height = initialHeight;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.colorScheme = colorScheme;
        writing = false;
    }

    public void changeColorScheme(CustomColorScheme colorScheme, Function<CxNum, Integer> getPixel){
        if (writing) throw new ConcurrentModificationException();
        this.colorScheme = colorScheme;
        calculatePixels(getPixel);
    }

    public void changeDimensions(int width, int height, Function<CxNum, Integer> getPixel) {
        if (writing) throw new ConcurrentModificationException();
        image = new WritableImage(width, height);
        this.width = width;
        this.height = height;
        calculatePixels(getPixel);
    }

    public void changeBounds(CxNum topLeft, CxNum bottomRight, Function<CxNum, Integer> getPixel){
        if (writing) throw new ConcurrentModificationException();
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        calculatePixels(getPixel);
    }

    public void calculatePixels(Function<CxNum, Integer> method){
        writing = true;
        Color col;
        java.awt.Color iCol;
        for (double x = 0;x < width; x++){
            for (double y = 0;y < height; y++){
                 iCol = colorScheme.getColor(
                        method.apply(new CxNum(topLeft.r + (x / width) * (bottomRight.r - topLeft.r),
                                topLeft.i + (y / height) *  (bottomRight.i - topLeft.i)
                                )
                        )
                 );
                 col = Color.rgb(iCol.getRed(), iCol.getGreen(), iCol.getBlue());
                 image.getPixelWriter().setColor((int)x, (int)y, col);
            }
        }
        writing = false;
    }
}
