/*
*
* @author ElPsy
 */
package Clases;

import Constants.Values;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class ExtraCanvas extends Canvas {

    private ArrayList<Punto> puntos;
    private ArrayList<Punto> solucion;
    private ArrayList<Integer> prev;
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
        this.solucion = new ArrayList<>();
        this.puntos = new ArrayList<>();
        this.prev = new ArrayList<>();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(Values.DEFAULT_WIDTH, Values.DEFAULT_HEIGHT);
        Graphics og = img.getGraphics();
        resetCanvas(og);
        if (this.puntos.size() != 0) {
            this.drawPoints(og);
            if (this.mult == 0 && !this.solucion.isEmpty()) {
                this.drawSolution(og);
                this.drawSolutionLines(og);
            } else if (!this.prev.isEmpty()) {
                this.drawDijkstraLines(og);
            } else {
                this.zoom(og);
            }
        }
        g.drawImage(img, 0, 0, null);
    }

    public void paint_by_steps(int left_limit, int right_limit) {
        Image img = createImage(Values.DEFAULT_WIDTH, Values.DEFAULT_HEIGHT);
        Graphics og = img.getGraphics();
        resetCanvas(og);
        this.drawPoints(og);
        this.drawSolution(og);
        this.drawSolutionLines(og);
        this.drawLine(og, (int) left_limit, 1000, (int) left_limit, 0, Color.GREEN);
        this.drawLine(og, (int) (int) right_limit, 1000, (int) (int) right_limit, 0, Color.GREEN);
        this.getGraphics().drawImage(img, 0, 0, null);

    }

    public void addSolucion(ArrayList<Punto> s) {
        this.solucion = s;
    }

    public void addPuntos(ArrayList<Punto> s) {
        this.puntos = s;
    }

    public void setPointSize(int ps) {
        this.point_size = ps;
    }

    public void drawSolution(Graphics g) {
        for (Punto p : this.solucion) {
            this.drawPoint(g, (int) p.x, (int) p.y, Color.RED);
        }

    }

    public void addPrevs(ArrayList<Integer> _prevs) {
        this.prev = _prevs;
    }

    private void drawDijkstraLines(Graphics og) {

        for (int i = (prev.size() - 1); i > 0; i--) {
            
            Punto destino = this.puntos.get(i);
            Punto previo = this.puntos.get(prev.get(i));
            
            this.drawLine(
                    og,
                    (int) destino.x,
                    (int) destino.y,
                    (int) previo.x,
                    (int) previo.y,
                    Color.BLUE
            );
            
        }
        return;
    }

    public void dijstra_step_by_step(Arista a){
        Image img = createImage(Values.DEFAULT_WIDTH, Values.DEFAULT_HEIGHT);
        Graphics og = img.getGraphics();
        resetCanvas(og);
        try{
        Thread.sleep(10);
        } catch (Exception e_){}
        this.drawPoints(og);
        for (int i = (prev.size() - 1); i > 0; i--) {
            
            

            Punto destino = new Punto(0, 0);
            Punto previo = new Punto(0, 0);
            
            try {
                destino = this.puntos.get(i);
                previo = this.puntos.get(prev.get(i));
                System.out.println(prev.get(i));
            } catch (IndexOutOfBoundsException _a){
                continue;
            }

            
            this.drawLine(
                    og,
                    (int) destino.x,
                    (int) destino.y,
                    (int) previo.x,
                    (int) previo.y,
                    Color.BLACK
            );
            
        }

        this.drawLine(
            og, 
            (int) a.getPuntoInicio().x, 
            (int) a.getPuntoInicio().y, 
            (int) a.getPuntoFin().x, 
            (int) a.getPuntoFin().y, 
            Color.RED
        );
        this.getGraphics().drawImage(img, 0, 0, null);

        return;
    }

    public void zoomIn(double q) {
        this.mult += q;
        this.scale_x *= 1.15;
        this.scale_y *= 1.15;

        repaint();
    }

    public void zoomOut(double q) {
        this.mult -= q;
        this.scale_x *= 0.85;
        this.scale_y *= 0.85;

        repaint();
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

    public void resetCanvas(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getHeight(), this.getHeight());
    }

    private void zoom(Graphics g) {

        this.zoom_x = (int) (this.getWidth() / 2 - (int) ((solucion.get(0).x - this.offset_x) * this.scale_x));
        this.zoom_y = (int) (this.getHeight() / 2 - (int) ((solucion.get(0).y - this.offset_y) * this.scale_y));

        for (Punto p : this.puntos) {
            this.drawPoint(g, (int) (p.x), (int) (p.y), Color.BLACK);
        }

        // Puntos de la solucion
        for (Punto p : this.solucion) {
            this.drawPoint(g, (int) (p.x), (int) (p.y), Color.RED);
        }

        this.drawSolutionLines(g);
    }

    private void drawSolutionLines(Graphics g) {
        for (int i = 0; i < 2; i++) {
            this.drawLine(
                    g,
                    (int) this.solucion.get(i).x,
                    (int) this.solucion.get(i).y,
                    (int) this.solucion.get(i + 1).x,
                    (int) this.solucion.get(i + 1).y,
                    Color.RED);

        }
    }

    public void drawPoints(Graphics g) {
        for (Punto p : this.puntos) {
            this.drawPoint(g, (int) p.x, (int) p.y, Color.BLACK);
        }
    }

    public void drawLine(Graphics g, int x1, int y1, int x2, int y2, Color c) {
        g.setColor(c);
        int pointOffset = (int) (point_size * this.size_mult / 2);
        g.drawLine(
                (int) ((x1 - offset_x) * this.scale_x + this.zoom_x) + pointOffset,
                (int) ((y1 - offset_y) * this.scale_y + this.zoom_y) + pointOffset,
                (int) ((x2 - offset_x) * this.scale_x + this.zoom_x) + pointOffset,
                (int) ((y2 - offset_y) * this.scale_y + this.zoom_y) + pointOffset);
    }

    private void drawPoint(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillOval((int) ((x - offset_x) * this.scale_x + this.zoom_x),
                (int) ((y - offset_y) * this.scale_y + this.zoom_y), point_size * this.size_mult,
                point_size * this.size_mult);
    }
}
