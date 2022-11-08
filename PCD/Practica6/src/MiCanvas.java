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

public class MiCanvas extends Canvas {

    private ArrayList<ViajeroMano> cola_pasajero_mano;
    private ArrayList<ViajeroMaleta> cola_pasajero_maleta;

    public MiCanvas(){
        super();
        this.cola_pasajero_maleta = new ArrayList<>();
        this.cola_pasajero_mano = new ArrayList<>();

    }

    @Override
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g){

        Image img = createImage(this.getWidth(), this.getHeight());
        Graphics og = img.getGraphics();
    }
    
}
