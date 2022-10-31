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
    private int point_size = 3;
    private double mult = 0;

    private double scale_x = 0.0;
    private double scale_y = 0.0;
    private double offset_x;
    private double offset_y;
    private double zoom_x;
    private double zoom_y;

    public ExtraCanvas(Dimension dimension) {
        super();
        this.setSize(dimension);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void addSolucion(ArrayList<Punto> s) {
        this.solucion = s;
    }

    public void addPuntos(ArrayList<Punto> s) {
        this.puntos = s;
    }

    public void drawSolution() {
        for (Punto p : this.solucion) {
            this.drawPoint((int) p.x, (int) p.y, Color.RED);
        }
    }

    public void zoomIn(double q) {
        this.mult += q;
        this.scale_x *= 1.15;
        this.scale_y *= 1.15;

        zoom();
    }

    public void zoomOut(double q) {
        this.mult -= q;
        this.scale_x *= 0.85;
        this.scale_y *= 0.85;

        zoom();
    }

    public void resetZoom() {
        this.zoom_x = 0;
        this.zoom_y = 0;
        this.mult = 0;
    }

    public void setRange(double range_x, double range_y, double min_x, double min_y) {
        this.scale_x = this.getHeight() / range_x;
        this.scale_y = this.getHeight() / range_y;
        this.offset_x = min_x;
        this.offset_y = min_y;
    }

    private void fixSize() {
        if (this.mult >= 10) {
            this.size_mult = 5;
        } else if (this.mult < 5) {
            this.size_mult = 2;
        } else {
            this.size_mult = 4;
        }
    }

    public void resetCanvas() {
        Graphics g = this.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getHeight(), this.getHeight());
    }

    private void zoom() {
        resetCanvas();

        System.out.println("Actual zoom = " + mult);
        if (this.mult == 0) {
            this.zoom_x = 0;
            this.zoom_y = 0;
            drawPoints();
            drawSolution();
            return;
        }

        Graphics g = this.getGraphics();

        this.zoom_x = (int) (this.getWidth() / 2 - (int) ((solucion.get(0).x - this.offset_x) * this.scale_x));
        this.zoom_y = (int) (this.getHeight() / 2 - (int) ((solucion.get(0).y - this.offset_y) * this.scale_y));

        for (Punto p : this.puntos) {
            this.drawPoint((int) (p.x), (int) (p.y), Color.BLACK);
        }

        //Puntos de la solucion 
        for (Punto p : this.solucion) {
            this.drawPoint((int) (p.x), (int) (p.y), Color.RED);
        }

        for (int i = 0; i < 2; i++) {
            this.drawLine(
                    (int) this.solucion.get(i).x,
                    (int) this.solucion.get(i).y,
                    (int) this.solucion.get(i + 1).x,
                    (int) this.solucion.get(i + 1).y,
                    Color.RED
            );

        }

    }

    public void drawPoints() {
        for (Punto p : this.puntos) {
            this.drawPoint((int) p.x, (int) p.y, Color.BLACK);
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2, Color c) {
        Graphics g = this.getGraphics();
        g.setColor(c);
        g.drawLine(
                (int) ((x1 - offset_x) * this.scale_x + this.zoom_x),
                (int) ((y1 - offset_y) * this.scale_y + this.zoom_y),
                (int) ((x2 - offset_x) * this.scale_x + this.zoom_x),
                (int) ((y2 - offset_y) * this.scale_y + this.zoom_y)
        );
    }

    private void drawPoint(int x, int y, Color c) {
        Graphics g = this.getGraphics();
        g.setColor(c);
        g.fillOval((int) ((x - offset_x) * this.scale_x + this.zoom_x), (int) ((y - offset_y) * this.scale_y + this.zoom_y), point_size * this.size_mult, point_size * this.size_mult);
    }
}
