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
    private static Color[] cols;
    
    public static void main(String[] args) {
        Globals.frameHeight = 650;
        Globals.frameWidth = 1000;
        Globals.minR = -1.75;
        Globals.maxR = 1.0;
        Globals.minI = -1.0;
        Globals.maxI = 1.0;
        Globals.maxIt = 2000;
        cols = new Color[(int)Globals.maxIt];
        for(int i = 0;i < (2 * Globals.maxIt / 5.0);i++){
            cols[i] = new Color(255, (int)(255 * ((i * 5.0) / (2.0 * Globals.maxIt))), 0);
        }
        for(int i = (int) (2 * Globals.maxIt / 5.0);i < (3 * Globals.maxIt / 5);i++){
            cols[i] = new Color((int)(255 - ((255 * ((i - (2.0 * Globals.maxIt / 5))) / (Globals.maxIt / 5.0)))), 255, 0);
        }
        for(int i = (int) (3 * Globals.maxIt / 5.0);i < (4 * Globals.maxIt / 5);i++){
            cols[i] = new Color(0, 255, (int)(255.0 * (5.0 * i / (Globals.maxIt * 4.0))));
        }
        for(int i = (int)(4 * Globals.maxIt / 5.0);i < Globals.maxIt;i++){
            cols[i] = new Color(0, (int)(255 - ((255 * ((i - (4.0 * Globals.maxIt / 5))) / (Globals.maxIt / 5.0)))), 255);
        }
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
        double greeT = (204.0 / 255.0);
        for(int i = 0;i < Globals.frameHeight;i++){
            for(int r = 0;r < Globals.frameWidth;r++){
                CxNum z = new CxNum(0.0, 0.0);//these are always the starting values!
                CxNum c = new CxNum(
                        Globals.minR + (r * (Globals.maxR - Globals.minR) / (double)Globals.frameWidth), 
                        Globals.minI + (i * (Globals.maxI - Globals.minI) / (double)Globals.frameHeight));
                int j = 0;
                while(z.abs() < 2 && j < Globals.maxIt){
                    z = math.ComplexComputation.mFunction(z, c);
                    j++;
                }
                if(z.abs() <= 2){
                    board[i][r] = Color.blue;
                }
                else{
                    board[i][r] = cols[j - 1];
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
