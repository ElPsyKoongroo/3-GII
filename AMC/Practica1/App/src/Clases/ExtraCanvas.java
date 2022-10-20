package Clases;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class ExtraCanvas extends Canvas {
    private ArrayList<Punto> puntos;
    private ArrayList<Punto> solucion;
    private int size_mult = 2;
    private double mult = 1;
    
    public ExtraCanvas(Dimension dimension){
        super();
        this.setSize(dimension);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void representa(int[] contadores){
        repaint();
    }

    public void addSolucion(ArrayList<Punto> s) {
        this.solucion = s;
    }

    public void addPuntos(ArrayList<Punto> s) {
        this.puntos = s;
    }
    
    public void drawSolution(){
        Graphics g = this.getGraphics();
        g.setColor(Color.RED);
        for(Punto p : this.solucion){
            g.fillOval((int)p.x, (int)p.y, 5*size_mult, 5*size_mult);
        }
    }

    public void zoomIn(double q){
        this.mult += q;
        zoom();
    }

    public void zoomOut(double q){
        this.mult -= q;
        zoom();
    }

    private void resetCanvas(){
        Graphics g = this.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1000, 1000);
    }

    private void zoom(){
        resetCanvas();

        if(this.mult == 0) {
            drawPoints();
            drawSolution();
            return ;
        }

        Graphics g = this.getGraphics();
        System.out.println("Actual zoom = " + mult);

        int x_offset = (500 - (int) (solucion.get(0).x * mult)); 
        int y_offset = (500 - (int) (solucion.get(0).y * mult)); 
        
        for (Punto p : this.puntos) {
            this.drawPoint((int)(p.x*mult+x_offset), (int)(p.y*mult + y_offset), Color.BLACK);
        }


        for(int i = 1; i<3; i++){
            this.drawPoint(
                (int)(mult*this.solucion.get(i).x + x_offset),
                (int)(mult*this.solucion.get(i).y + y_offset),
                Color.RED
                );
        }

        //Fixed center
        this.drawPoint(500, 500, Color.RED);
    }

    public void drawPoints(){
        for (Punto p : this.puntos) {
            this.drawPoint((int)p.x, (int)p.y, Color.BLACK);
        }
    }

    private void drawPoint(int x, int y, Color c){
        Graphics g = this.getGraphics();
        g.setColor(c);
        g.fillOval(x, y, 5*this.size_mult, 5*this.size_mult);
    }

}