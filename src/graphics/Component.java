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
    public boolean updating;
    
    public Component(){
        timer = new Timer(10, this);
        pic = new BufferedImage(1000, 650, BufferedImage.TYPE_INT_ARGB);
        updating = true;
        font = new Font("Monospaced", Font.BOLD, 64);
        selector = new Color(0, 0, 255, 127);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!updating){
            g.drawImage(pic, 0, 0, null);
            g.setColor(selector);
            g.fillRect(Globals.mouseX - 53, Globals.mouseY - 75, 100, 100);
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
    
    public void updateImage(boolean arr[][]){
        int black = new Color(0, 0, 0).getRGB();
        int white = new Color(255, 255, 255).getRGB();
        for(int y = 0;y < 650;y++){
            for(int x = 0;x < 1000;x++){
                pic.setRGB(x, y, (arr[y][x] ? black : white));
            }
        }
        updating = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double minR = Globals.minR + (((e.getX() - 53) / (double)Globals.frameWidth) * (Globals.maxR - Globals.minR));
        double maxR = minR + (.1 * (Globals.maxR - Globals.minR));
        double minI = Globals.minI + (((e.getY() - 75) / (double)Globals.frameHeight) * (Globals.maxI - Globals.minI));
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
