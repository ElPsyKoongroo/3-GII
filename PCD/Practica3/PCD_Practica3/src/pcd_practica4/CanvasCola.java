/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_practica4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author ElPsy
 */
public class CanvasCola extends Canvas {
    
    private int head;
    private int tail;
    private int capacidad;
    private int numelementos;
    private Object[] datos;
    private String mensaje;
    private boolean printMensaje;
    
    
    public CanvasCola(Dimension dimension ,int capacidad) throws Exception{
        super();
        this.setSize(dimension);
        this.setBackground(Color.BLACK);
        if (capacidad > 10) throw new Exception("La capacidad no puede ser superior a 10");
        this.capacidad = capacidad;
        this.mensaje = "Normal";
        this.printMensaje = false;
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void avisa(String mensaje){
        this.printMensaje = true;
        this.mensaje = mensaje;
        repaint();
    }
    
    public void representa(Object[] buf, int head, int tail, int numele) throws Exception {
        datos = buf;
        this.head = head;
        this.tail = tail;
        if (numele > 10) throw new Exception("El numero de elementos no puede ser superior a 10");
        this.numelementos = numele;
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        Image img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();
        Font fuenteNumeros = new Font("Arial", Font.BOLD, 30);
        Font fuenteMensaje = new Font("Arial", Font.BOLD, 40);
        
        
        og.setColor(Color.BLACK);
        og.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        og.setFont(fuenteNumeros);
        og.setColor(Color.WHITE);
        
        int xInitPos = 10;
        int yInitPos = 200;
        
        og.drawLine(xInitPos, yInitPos, xInitPos + this.capacidad * 50, yInitPos);
        og.drawLine(xInitPos, yInitPos + 50, xInitPos + this.capacidad * 50, yInitPos + 50);
        
        for (int i = 0; i <= this.capacidad; i++)
        {
            og.drawLine(xInitPos + i * 50,yInitPos, xInitPos + i * 50, yInitPos + 50);
        }
        
        og.setColor(Color.RED);
        
        int printedElements = 0;
        
        for (int i = head; printedElements != this.numelementos ; i = (i+1)%this.capacidad)
        {
            og.drawString((Integer.toString(((int)this.datos[i]))), xInitPos + 15 + 50 * i, yInitPos + 35);
            printedElements++;
        }
        
        if(printMensaje)
        {
            og.setColor(Color.BLUE);
            og.setFont(fuenteMensaje);
            og.drawString(mensaje, xInitPos + 50, yInitPos - 50);
        }
        
        
        //og.setColor(Color.ORANGE);
        //og.fillRect(50, 80, 25, 25);
        
        //og.setColor(Color.RED);
        //og.fillOval(50, 170, 25, 35);
        
        this.printMensaje = false;
        
        g.drawImage(img, 0, 0, null);
        
    }
}
