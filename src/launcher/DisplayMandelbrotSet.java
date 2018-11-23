package launcher;

import graphics.Component;
import graphics.CustomColorScheme;
import math.ComplexComputation;
import math.CxNum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

/**
 * @author achafos
 */
public class DisplayMandelbrotSet {
    
    public static int numBoard[][];
    public static Color board[][];
    public static JFrame frame;
    public static Component display;
    public static Thread generator;
    public static boolean setUp;
    
    public static void main(String[] args) {
        frame = new JFrame();
        setFrameSize(false);
        Globals.minR = -1.75;
        Globals.maxR = 1.0;
        Globals.minI = -1.0;
        Globals.maxI = 1.0;
        Globals.maxIt = 2000;
        Globals.textBoxVisible = true;
        Globals.textBoxFadingOut = false;
        setUp = false;
        
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

        
        board = new Color[Globals.frameHeight][Globals.frameWidth];
        numBoard = new int[Globals.frameHeight][Globals.frameWidth];
        
        display = new Component();
        startGenerator();
        setupFrame();
        setUp = true;
        Globals.frameHeight = 650;
        Globals.frameWidth = 1000;
    }
    
    public static void startGenerator(){
        generator = new Thread(() -> generate());
        generator.start();
    }
    
    public static void generate(){
        //i is imaginary;r is real
        Globals.max = Globals.frameHeight * Globals.frameWidth;
        Globals.progress = 0;
        //display.updating = true;

        Globals.textBoxVisible = true;
        for(int i = 0;i < Globals.frameHeight;i++){
            for(int r = 0;r < Globals.frameWidth;r++){
                CxNum c = new CxNum(
                        Globals.minR + (r * (Globals.maxR - Globals.minR) / (double)Globals.frameWidth), 
                        Globals.minI + (i * (Globals.maxI - Globals.minI) / (double)Globals.frameHeight));
                int j = ComplexComputation.getMandelbrotValue(c, Globals.maxIt);
                if(j == Globals.maxIt){
                    board[i][r] = Globals.cols.getColor(Globals.maxIt);
                    numBoard[i][r] = Globals.maxIt;
                }
                else{
                    board[i][r] = Globals.cols.getColor(j);
                    numBoard[i][r] = j;
                }
                Globals.progress++;
            }
            display.updateImage(board[i], i);
        }
        Globals.textBoxVisible = false;
        Globals.textBoxFadingOut = true;
        display.updating = false;
    }
    
    public static void setFrameSize(boolean fullScreen){
        if(fullScreen){
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle bounds = env.getMaximumWindowBounds();
            Globals.frameWidth = bounds.width;
            System.out.println(Globals.frameWidth);
            Globals.frameHeight = bounds.height;
            System.out.println(Globals.frameHeight);
        }
        else{
            Globals.frameWidth = 1000;
            Globals.frameHeight = 650;
        }
        frame.setSize(Globals.frameWidth + 3, Globals.frameHeight + 25);
        frame.setMinimumSize(new Dimension(Globals.frameWidth + 3, Globals.frameHeight + 25));
    }
    
    public static void setFrameSize(int width, int height){
        System.out.println("This was called");
        Globals.frameWidth = width;
        Globals.frameHeight = height;
        //frame.setSize(Globals.frameWidth + 3, Globals.frameHeight + 25);
        //frame.setMinimumSize(new Dimension(Globals.frameWidth + 3, Globals.frameHeight + 25));
        display.updateBounds();
        board = new Color[Globals.frameHeight][Globals.frameWidth];
        numBoard = new int[Globals.frameHeight][Globals.frameWidth];
        startGenerator();
    }
    
    public static void changeFrameSize(ComponentEvent e){
        int newWidth = e.getComponent().getWidth();
        int newHeight = e.getComponent().getHeight();
        setFrameSize(newWidth, newHeight);
    }
    
    public static void setupFrame(){
        frame.setTitle("Mandelbrot Explorer V. 1.0");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.timer.start();
        frame.getContentPane().add(display);
        frame.addMouseListener(display);
        frame.addMouseMotionListener(display);;
        frame.pack();
        frame.setVisible(true);
    }
    
    
    
    
    
}
