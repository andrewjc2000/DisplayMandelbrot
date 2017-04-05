/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Component extends JComponent implements ActionListener {
    
    public final Timer timer;
    private final BufferedImage pic;
    private boolean updating;
    
    public Component(){
        timer = new Timer(10, this);
        pic = new BufferedImage(1000, 650, BufferedImage.TYPE_INT_ARGB);
        updating = false;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!updating){
            repaint();
        }
    }
    
    public void updateImage(boolean arr[][]){
        updating = true;
        for(int y = 0;y < 650;y++){
            for(int x = 0;x < 1000;x++){
                
            }
        }
    }
    
}
