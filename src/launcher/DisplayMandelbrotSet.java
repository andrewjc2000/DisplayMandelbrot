/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import graphics.Component;
import graphics.CustomColorScheme;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import math.CxNum;

public class DisplayMandelbrotSet {
    
    public static int numBoard[][];
    public static Color board[][];
    public static JFrame frame;
    public static Component display;
    //private static Color[] cols;
    public static Thread generator;
    
    public static void main(String[] args) {
        setFrameSize(false);
        Globals.minR = -1.75;
        Globals.maxR = 1.0;
        Globals.minI = -1.0;
        Globals.maxI = 1.0;
        Globals.maxIt = 2000;
        Globals.textBoxVisible = true;
        Globals.textBoxFadingOut = false;
        /*cols = new Color[(int)Globals.maxIt];
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
        }*/
        
        ArrayList<Color> as = new ArrayList<>();
        as.add(Color.white);
        as.add(new Color(255, 0, 255));
        as.add(new Color(255, 127, 255));
        as.add(new Color(127, 127, 127));
        as.add(Color.black);
        
        ArrayList<Integer> is = new ArrayList<>();
        is.add(500);
        is.add(1000);
        is.add(1500);
        
        Globals.cols = new CustomColorScheme(as, is, 2000);
        
        Globals.cols.print();
        
        board = new Color[Globals.frameHeight][Globals.frameWidth];
        numBoard = new int[Globals.frameHeight][Globals.frameWidth];
        
        display = new Component();
        startGenerator();
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
    
    public static void startGenerator(){
        generator = new Thread(){
            @Override
            public void run(){
                generate();
            }
        };
        generator.start();
    }
    
    public static void generate(){
        //i is imaginary;r is real
        Globals.max = Globals.frameHeight * Globals.frameWidth;
        Globals.progress = 0;
        //display.updating = true;
        
        //double redY = (102.0 / 255.0);
        //double ylG = (153.0 / 255.0);
        //double greeT = (204.0 / 255.0);
        Globals.textBoxVisible = true;
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
                    board[i][r] = Globals.cols.getColor(Globals.maxIt - 1);
                    numBoard[i][r] = Globals.maxIt;
                }
                else{
                    board[i][r] = Globals.cols.getColor(j - 1);
                    numBoard[i][r] = j;
                }
                Globals.progress++;
            }
            display.updateImage(board[i], i);
        }
        Globals.textBoxVisible = false;
        Globals.textBoxFadingOut = true;
        //Globals.maxIt = ((3 * Globals.maxIt) / 2);
        display.updating = false;
    }
    
    public static void setFrameSize(boolean fullScreen){
        frame = new JFrame();
        if(fullScreen){
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            frame.setUndecorated(true);
            Globals.frameWidth = frame.getBounds().width;
            Globals.frameHeight = frame.getBounds().height;
            System.out.println(frame.getBounds());
        }
        else{
            Globals.frameWidth = 1000;
            Globals.frameHeight = 650;
            frame.setSize(Globals.frameWidth + 3, Globals.frameHeight + 25);
            frame.setMinimumSize(new Dimension(Globals.frameWidth + 3, Globals.frameHeight + 25));
        }
    }
    
    public static void setupFrame(){
        frame.setTitle("Mandelbrot Explorer V. 1.0");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.timer.start();
        frame.getContentPane().add(display);
        frame.addMouseListener(display);
        frame.addMouseMotionListener(display);
        frame.pack();
        frame.setVisible(true);
        //System.out.println(frame.getContentPane().getSize());
    }
    
    
    
    
    
}
