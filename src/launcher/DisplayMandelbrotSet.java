/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import graphics.Component;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import math.ComplexComputation;
import math.CxNum;

public class DisplayMandelbrotSet {
    
    public static Color board[][];
    public static JFrame frame;
    public static Component display;
    
    public static void main(String[] args) {
        Globals.frameHeight = 650;
        Globals.frameWidth = 1000;
        Globals.minR = -1.75;
        Globals.maxR = 1.0;
        Globals.minI = -1.0;
        Globals.maxI = 1.0;
        board = new Color[650][1000];
        display = new Component();
        generate();
        display.updateImage(board);
        setupFrame();
        //generate();
        //display.updateImage(board);
        
        /*for(int i = 0;i < 650;i++){
            for(int j = 0;j < 1000;j++){
                System.out.print(board[i][j] ? "." : " ");
            }
            System.out.println();
        }*/
    }
    
    
    
    public static void generate(){
        //i is imaginary;r is real
        Globals.max = Globals.frameHeight * Globals.frameWidth;
        Globals.progress = 0;
        display.updating = true;
        double redY = (102.0 / 255.0);
        double ylG = (153.0 / 255.0);
        for(int i = 0;i < Globals.frameHeight;i++){
            for(int r = 0;r < Globals.frameWidth;r++){
                CxNum z = new CxNum(0.0, 0.0);//these are always the starting values!
                CxNum c = new CxNum(
                        Globals.minR + (r * (Globals.maxR - Globals.minR) / (double)Globals.frameWidth), 
                        Globals.minI + (i * (Globals.maxI - Globals.minI) / (double)Globals.frameHeight));
                int j = 0;
                while(z.abs() < 2 && j < 2000){
                    z = math.ComplexComputation.mFunction(z, c);
                    j++;
                }
                if(z.abs() <= 2){
                    board[i][r] = Color.black;
                }
                else{
                    Color col;
                    double scaled = (j / 2000.0);
                    if(scaled < redY){
                        col = new Color(255, (int)(scaled * 5), 0);
                    }
                    //else if(scaled >= redY && scaled < ylG){
                    //    col = new Color(255 -, 255, 0);
                    //}
                    else{
                        col = new Color(0, 0, 255);
                    }
                    board[i][r] = col;
                }
                Globals.progress++;
            }
        }
    }
    
    public static void setupFrame(){
        frame = new JFrame();
        frame.setTitle("Mandelbrot Explorer V. 1.0");
        frame.setSize(Globals.frameWidth, Globals.frameHeight);
        frame.setResizable(false);
        frame.setSize(Globals.frameWidth, Globals.frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(Globals.frameWidth, Globals.frameHeight));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.timer.start();
        frame.getContentPane().add(display);
        frame.addMouseListener(display);
        frame.addMouseMotionListener(display);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    
    
    
}
