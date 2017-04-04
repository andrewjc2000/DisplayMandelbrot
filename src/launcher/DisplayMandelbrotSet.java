/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import javax.swing.JFrame;
import math.ComplexComputation;
import math.CxNum;

public class DisplayMandelbrotSet extends JFrame {
    
    public static boolean board[][];
    
    public static void main(String[] args) {
        board = new boolean[650][1000];
        
        generate();
        
        for(int i = 0;i < 650;i++){
            for(int j = 0;j < 1000;j++){
                System.out.print(board[i][j] ? "." : " ");
            }
            System.out.println();
        }
    }
    
    
    
    public static void generate(){
        //i is imaginary;r is real
        for(int i = 0;i < 650;i++){
            for(int r = 0;r < 1000;r++){
                CxNum z = new CxNum(0.0, 0.0);//these are always the starting values!
                CxNum c = new CxNum(-1.75 + (r * 2.75 / 1000.0), -1 + (i * 2 / 650.0));
                int j = 0;
                while(z.abs() < 2 && j < 1000){
                    z = math.ComplexComputation.mFunction(z, c);
                    j++;
                }
                if(z.abs() <= 2){
                    board[i][r] = true;
                }
                else{
                    board[i][r] = false;
                }
            }
        }
    }
    
    
    
    
    
}
