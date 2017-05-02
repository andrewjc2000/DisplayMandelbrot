/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Color;
import java.util.ArrayList;
import launcher.Globals;

public class SpecificColor {
    
    public final Color val;
    
    public SpecificColor(int specific){
        
        double fraction = (1.0 * specific) / (1.0 * Globals.maxIt);
        int startColor = (int)Math.floor((Globals.cols.size() - 1) * fraction);
        
        //300 
        //r 0 to 149 (red to green)
        //g 150 to 299 (green to blue)
        //b 300 (blue)
        
        
        if(fraction == 1.0){
            val = Globals.cols.get(Globals.cols.size() - 1);
        }
        else{
        
            
            
            
            val = Color.black;
        }
        
    }
    
    
}
