
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ElPsy
 */
public class MiCanvas extends Canvas {
    
    private int[] contadores = {0,0};
    
    public MiCanvas(Dimension dimension){
        super();
        this.setSize(dimension);
        this.setBackground(Color.CYAN);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void representa(int[] contadores){
        this.contadores=contadores;
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        Image img = createImage(500, 500);
        Graphics og = img.getGraphics();
        
        og.setColor(Color.DARK_GRAY);
        og.drawString("1: " + this.contadores[0], 100, 100);
        og.drawString("2: " + this.contadores[1], 100, 200);
        
        g.drawImage(img, 0, 0, null);
        
    }
}
