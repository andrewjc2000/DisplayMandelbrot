/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Color;
import java.util.ArrayList;
import launcher.Globals;

public class CustomColorScheme {

    private final ArrayList<Color> val;
    
    public CustomColorScheme(ArrayList<Color> cols, ArrayList<Integer> pos, int size){
        
        val = new ArrayList<>();
        
        //first and last color values are implied to be first and last so no positions
        if(cols.size() != pos.size() + 2){
            throw new RuntimeException("CustomColorScheme Error: ArrayList size mismatch");
        }
        
        if(cols.size() == 2){
            int rDif = cols.get(1).getRed() - cols.get(0).getRed();
            int gDif = cols.get(1).getGreen() - cols.get(0).getGreen();
            int bDif = cols.get(1).getBlue() - cols.get(0).getBlue();
            int r = cols.get(0).getRed();
            int g = cols.get(0).getGreen();
            int b = cols.get(0).getBlue();
            for(double i = 0;i < size;i++){
                val.add(
                    new Color(
                        (int)(r + (rDif * (i / (1.0 * (size - 1))))),
                        (int)(g + (gDif * (i / (1.0 * (size - 1))))),
                        (int)(b + (bDif * (i / (1.0 * (size - 1)))))
                    )
                );
            }
        }
        else{
            ArrayList<Integer> positions = pos;
            positions.add(0, 0);
            positions.add(size - 1);
            for(int i = 0;i < pos.size() - 1;i++){
                int rDif = cols.get(i + 1).getRed() - cols.get(i).getRed();
                int gDif = cols.get(i + 1).getGreen() - cols.get(i).getGreen();
                int bDif = cols.get(i + 1).getBlue() - cols.get(i).getBlue();
                int r = cols.get(i).getRed();
                int g = cols.get(i).getGreen();
                int b = cols.get(i).getBlue();
                double mSize = pos.get(i + 1) - pos.get(i);
                for(int j = 0;j < mSize;j++){
                    val.add(
                        new Color(
                            (int)(r + (rDif * (j / mSize))),
                            (int)(g + (gDif * (j / mSize))),
                            (int)(b + (bDif * (j / mSize)))
                        )
                    );
                }
            }
            val.add(cols.get(cols.size() - 1));
        }
        
        //for(int i = 0;i < pos.get(1);i++){
        //    
        //}
        
    }
    
    public void print(){
        for(int i = 0;i < val.size();i++){
            System.out.println(
                val.get(i).getRed() + ", " + 
                val.get(i).getGreen() + ", " + 
                val.get(i).getBlue()
            );
        }
        System.out.println(val.size());
    }
    
    public Color getColor(int position){
        return val.get(position);
    }
    
    
}
