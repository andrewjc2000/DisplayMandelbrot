/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.Timer;
import launcher.DisplayMandelbrotSet;
import launcher.Globals;

public class Component extends JComponent implements ActionListener, MouseListener,
    MouseMotionListener {
    
    public final Timer timer;
    private final BufferedImage pic;
    private final Font font;
    private final Color selector;
    private final int boxWidth, boxHeight;
    public boolean updating;
    
    public Component(){
        timer = new Timer(10, this);
        pic = new BufferedImage(Globals.frameWidth, Globals.frameHeight, BufferedImage.TYPE_INT_ARGB);
        updating = true;
        font = new Font("Monospaced", Font.BOLD, 64);
        selector = new Color(0, 0, 255, 127);
        boxWidth = (int)Math.round(0.1 * Globals.frameWidth);
        boxHeight = (int)Math.round(0.1 * Globals.frameHeight);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!updating){
            g.drawImage(pic, 0, 0, null);
            g.setColor(selector);
            g.fillRect(Globals.mouseX - 3 - (boxWidth / 2), Globals.mouseY - 25 - (boxHeight / 2), boxWidth, boxHeight);
        }
        else{
            g.setColor(Color.white);
            g.fillRect(0, 0, Globals.frameWidth, Globals.frameHeight);
            g.setColor(Color.black);
            g.setFont(font);
            g.drawString("Progress: " + (int)Math.round(100 * ((double)Globals.progress / (double)Globals.max)) + "%", 200, 100);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public void updateImage(Color arr[][]){
        for(int y = 0;y < Globals.frameHeight;y++){
            for(int x = 0;x < Globals.frameWidth;x++){
                pic.setRGB(x, y, arr[y][x].getRGB());
            }
        }
        updating = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double minR = Globals.minR + (((e.getX() - 3 - (boxWidth / 2)) / (double)Globals.frameWidth) * (Globals.maxR - Globals.minR));
        double maxR = minR + (.1 * (Globals.maxR - Globals.minR));
        double minI = Globals.minI + (((e.getY() - 25 - (boxHeight / 2)) / (double)Globals.frameHeight) * (Globals.maxI - Globals.minI));
        double maxI = minI + (.1 * (Globals.maxI - Globals.minI));
        Globals.minR = minR;
        Globals.maxR = maxR;
        Globals.minI = minI;
        Globals.maxI = maxI;
        updating = true;
        DisplayMandelbrotSet.generate();
        
        updateImage(DisplayMandelbrotSet.board);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Globals.mouseX = e.getX();
        Globals.mouseY = e.getY();
    }
    
}
