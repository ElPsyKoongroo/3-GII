/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
* @author ElPsy
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class MiCanvas extends Canvas {

    private ArrayList<Integer> cola_pasajero_mano;
    private ArrayList<Integer> cola_pasajero_maleta;
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    private int rayoMano = 0;
    private int contador_mano = 1;
    private int contador_maleta = 2;
    private int[] rayoMaleta = {0,0};
    private Image Rika;
    private Image Percy;

    // 0  -> Perro libre
    // -2 -> Esperando al cuidador
    // -1 -> Cuidador
    // _  -> Pasajero
    private int[] perros = {0,0};

    public MiCanvas(){
        super();
        this.cola_pasajero_maleta = new ArrayList<>();
        this.cola_pasajero_mano = new ArrayList<>();
        this.Rika = new ImageIcon("src/Rika.png").getImage();
        this.Percy = new ImageIcon("src/Percy.png").getImage();
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g){
        Image img = createImage(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Graphics og = img.getGraphics();

        og.setFont(new Font("Helvetica", Font.BOLD, 20));

        og.setColor(Color.LIGHT_GRAY);
        og.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        og.setColor(Color.ORANGE);
        og.drawImage(this.Rika, 20, 120, 120, 140, null); // Rika (suele gomitar)
        og.drawImage(this.Percy, 20, 420, 150, 120, null); //Percy
        
        

        og.setColor(Color.BLACK);
        og.drawString("Rika", 25, 110);
        og.drawString("Percy", 25, 410);

        og.setColor(Color.GREEN);
        og.fillRect(200, 120, 60, 60);  //Rayos X mano
        og.fillRect(200, 320, 60, 60);  //Rayos X maleta
        og.fillRect(200, 420, 60, 60);  //Rayos X maleta


        // Pasajero manos
        og.setColor(Color.MAGENTA);
        int offset = 0;
        for(int pasajero_mano : this.cola_pasajero_mano) {
            this.pintaPasajero(og, 300 + offset, 120, pasajero_mano);
            offset += 50;
        }

        // Pasajero maleta
        og.setColor(Color.RED);
        offset = 0;
        for(int pasajero_maleta : this.cola_pasajero_maleta) {
            this.pintaPasajero(og, 300 + offset, 370, pasajero_maleta);
            offset += 50;
        }

        // Pasajero maleta en rayos X
        for(int i = 0; i<2; i++){
            if (this.rayoMaleta[i] != 0){
                this.pintaPasajero(og, 210, 330 + i*100, this.rayoMaleta[i]);
            }
        }

        // Pasajero mano en rayos X
        og.setColor(Color.MAGENTA);
        if(this.rayoMano != 0) this.pintaPasajero(og, 210, 130, this.rayoMano); 
        
        // Quien esta con los perros ?
        for(int i = 0; i<2; i++){
            if(this.perros[i] == -1) {
                og.setColor(Color.YELLOW);
                og.fillRect(20, 250 + i*300, 40, 40);
            } else if (this.perros[i] > 0) {
                this.pintaPasajero(og, 20, 250+i*300, this.perros[i]);
            } /*else if (this.perros[i] == -2) {
                og.setColor(Color.PINK);
                og.fillRect(20, 170 + i*200, 40, 40);
            }*/
        }

        og.setColor(Color.BLACK);
        pintaLeyenda(og);

        g.drawImage(img, 0, 0, null);

    }

    public synchronized int nuevoPasajeroMaleta(){
        int aux = contador_maleta;
        contador_maleta += 2;
        this.cola_pasajero_maleta.add(aux);
        repaint();
        return aux;
    }

    public synchronized int nuevoPasajeroMano(){
        int aux = contador_mano;
        contador_mano += 2;
        this.cola_pasajero_mano.add(aux);
        repaint();
        return aux;
    }
    
    public synchronized void entraRayosMano(int id){
        this.cola_pasajero_mano.remove((Object)id);
        this.rayoMano = id;
        repaint();
    }

    public synchronized void entraRayosMaleta(int id){
        this.cola_pasajero_maleta.remove((Object)id);

        if (this.rayoMaleta[0] == 0){
            this.rayoMaleta[0] = id;
        }
        else {
            this.rayoMaleta[1] = id;
        }

        repaint();
    }

    public synchronized void pasajeroManoPerro(){
        int aux = this.rayoMano;
        this.rayoMano = 0;
        if (this.perros[0] == 0) 
            this.perros[0] = aux;
        else
            this.perros[1] = aux;

        repaint();
    }

    public synchronized void pasajeroMaletaPerro(int id){
        int posicion = rayoMaleta[0] == id ? 0:1;
        this.rayoMaleta[posicion] = 0;
        if (this.perros[0] == 0) 
            this.perros[0] = id;
        else
            this.perros[1] = id;
        repaint();
    }

    public synchronized void pasajeroSalePerro(int id){
        if(this.perros[1] == id){
            System.out.println("Saliendo de Percy");
            this.perros[1] = -2;
        }
        else this.perros[0] = -2;
        repaint();
    }

    public synchronized void cuidadorSale(){
        if(this.perros[0] == -1) this.perros[0] = 0;
        else this.perros[1] = 0;
        repaint();
    }

    public synchronized void cuidadorEntra(){
        if(this.perros[1] == -2) this.perros[1] = -1;
        else this.perros[0] = -1;
        repaint();
    }

    private synchronized void pintaPasajero(Graphics g, int x, int y, int id){
        if(id%2 == 0){
            id /= 2;
            g.setColor(Color.RED);
        }
        else {
            id = (id+1)/2;
            g.setColor(Color.MAGENTA);
        }

        g.fillRect(x, y, 40, 40);

        g.setColor(Color.BLUE);
        g.drawString(""+id, x+5, y+20);

    }

    private synchronized void pintaLeyenda(Graphics g){
        g.drawString("Rojo -> PasajeroMaleta", 500, 600);
        g.drawString("Magenta -> PasajeroMano", 500, 625);
        g.drawString("Amarillo -> Cuidador", 500, 650);
        g.drawString("Verde -> Maquina Rayos X", 500, 675);
    }
}
