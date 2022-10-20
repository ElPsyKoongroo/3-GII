
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
        Image img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();
        Font contador1Font = new Font("Arial", Font.BOLD, 20);
        
        og.setFont(contador1Font);
        og.setColor(Color.DARK_GRAY);
        og.drawString("Contador 1:    " + this.contadores[0], 100, 100);
        og.drawString("Contador 2:    " + this.contadores[1], 100, 200);
        
        
        og.setColor(Color.ORANGE);
        og.fillRect(50, 80, 25, 25);
        
        og.setColor(Color.RED);
        og.fillOval(50, 170, 25, 35);
        
        g.drawImage(img, 0, 0, null);
        
    }
}
