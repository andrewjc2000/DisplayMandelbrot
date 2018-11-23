package graphics;

import java.awt.Color;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

/**
 * @author achafos
 */
public class CustomColorScheme {

    private final ArrayList<Color> cols;
    private final ArrayList<Integer> indeces;
    private final int upperBound, size;

    /*
        END BOUND IS INCLUSIVE; YOU CAN GRAB A COLOR FROM IT NO PROBLEM
     */
    public CustomColorScheme(ArrayList<Color> cols, ArrayList<Integer> pos, int upperBound){
        if (cols.size() != pos.size() + 2 && (cols.size() != pos.size())) {
            throw new InputMismatchException("Integer index array must have either exactly the same # of elements"
                    + " as the Color array or exactly 2 less."
            );
        } else if (cols.size() == pos.size() && (pos.get(0) != 0 || pos.get(pos.size() - 1) != upperBound)) {
            throw new InvalidParameterException("The index array must begin with 0 and end with upperBound if " +
                    "its size equals that of the Color array");
        }

        for (int i = 0; i < pos.size() - 1; i++) {
            if (pos.get(i) == null || pos.get(i).intValue() >= pos.get(i + 1).intValue()) {
                throw new InvalidParameterException("Index Array must be ascending");
            }
        }

        if (pos.size() != cols.size()) {
            pos.add(0, 0);
            pos.add(upperBound);
        }

        this.cols = cols;
        this.indeces = pos;
        this.upperBound = upperBound;
        this.size = cols.size();
        
    }

    @Override
    public String toString(){
        String s = "";
        for (int i = 0;i < size; i++){
            s += indeces.get(i) + ": " + cols.get(i).toString() + "\n";
        }
        return s;
    }
    
    public Color getColor(int position){//input here should go from 0 to upperBound

        if (position < 0 || position > upperBound) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int i = Collections.binarySearch(indeces, position);

        if(i >= 0){
            return cols.get(i);
        } else {
            i = -(i + 2);
        }

        int rDif = cols.get(i + 1).getRed() - cols.get(i).getRed();
        int gDif = cols.get(i + 1).getGreen() - cols.get(i).getGreen();
        int bDif = cols.get(i + 1).getBlue() - cols.get(i).getBlue();
        int r = cols.get(i).getRed();
        int g = cols.get(i).getGreen();
        int b = cols.get(i).getBlue();
        double percentage =
                ((position - indeces.get(i)) * 1.0) /
                        (1.0 * (indeces.get(i + 1) - indeces.get(i)));

        return new Color (
            (int)(r + (rDif * percentage)),
            (int)(g + (gDif * percentage)),
            (int)(b + (bDif * percentage))
        );

    }

    public Color getColor(double positionAsDecimal){
        return getColor((int)(positionAsDecimal * size));
    }
    
    
}
