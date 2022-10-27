/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**
 *
 * @author ElPsy
 */
public class ControladorPrincipal {
    private FramePrincipal fPrincipal;
    private ActionListener aL_RadioButtons;
    private ActionListener aL_All;
    private ExtraCanvas canvas;
    
    boolean puntosAleatorios;
    boolean metodoAlgoritmo; //false = exh, true = DyV
    
    public ControladorPrincipal()
    {
        fPrincipal = new FramePrincipal();
        this.canvas = new ExtraCanvas(new Dimension(800,800));
        this.fPrincipal.add(this.canvas);
        
        aL_RadioButtons = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformedRadioButtons(e);
            }
        };
        
        aL_All = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformedAll(e);
            }
        };
        
        ConfigureFrame();
 
    }

    private void addListeners() {
        fPrincipal.rB_Exh.addActionListener(this.aL_RadioButtons);
        fPrincipal.rB_DyV.addActionListener(this.aL_RadioButtons);
        
        fPrincipal.rB_PuntosAle.addActionListener(this.aL_RadioButtons);
        fPrincipal.rB_PuntosFic.addActionListener(this.aL_RadioButtons);
        
        fPrincipal.b_Calcular.addActionListener(this.aL_All);
        System.out.println("Action listeners added!");
    }
    
    private void ConfigureFrame()
    {
        puntosAleatorios = true;
        fPrincipal.rB_PuntosAle.setSelected(true);
        
        metodoAlgoritmo = true;
        fPrincipal.rB_DyV.setSelected(true);
        
        fPrincipal.rBG_TipoAlgoritmo.add(fPrincipal.rB_DyV);
        fPrincipal.rBG_TipoAlgoritmo.add(fPrincipal.rB_Exh);
        
        
        fPrincipal.setLocationRelativeTo(null);
        fPrincipal.setVisible(true);
        
        addListeners();
    }
    
    public void actionPerformedRadioButtons(ActionEvent e){
        switch(e.getActionCommand())
        {
            case "rB_Exhaust_Comm":
            {
                this.metodoAlgoritmo = false;
                break;
            }
            case "rB_DyV_Comm":
            {
                this.metodoAlgoritmo = true;
                break;
            }
            case "rB_PuntosAle_Comm":
            {
                break;
            }
            case "rB_PuntosFic_Comm":
            {
                break;
            }
        }
    }
    
    public void actionPerformedAll(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "b_Calcular_Comm": {
                CalcularPuntos();
                break;
            }
        }
    }

    private void CalcularPuntos()
    {
        System.out.println("Pipo");
        ArrayList<Punto> puntos = new ArrayList<Punto>();
        
        try{
            puntos = LeerPuntos("d657.tsp");
        }
        catch(Exception e){
            System.exit(1);
        }
        QuickSort.Ordena(puntos, 0, puntos.size()-1);
        double min_y = Double.MAX_VALUE;
        double max_y = Double.MIN_VALUE;
        
        for (Punto punto : puntos) {
            if (punto.y < min_y) min_y = punto.y;
            if (punto.y > max_y) max_y = punto.y;
        }
        
        double rango_x = puntos.get(puntos.size()-1).x-puntos.get(0).x;
        double rango_y = max_y - min_y;
        
        this.canvas.setRange(rango_x, rango_y, puntos.get(0).x, min_y);
        
        
        ArrayList<Punto> solucion = new ArrayList<Punto>();
        if (this.metodoAlgoritmo){
            solucion = Algoritmo.DivideVenceras(puntos);
        } else {
            solucion = Algoritmo.SolucionExhaustiva(puntos);
        }
                
        System.out.println("Done!");
        
        this.canvas.addPuntos(puntos);
        this.canvas.drawPoints();
        
        this.canvas.addSolucion(solucion);
        this.canvas.drawSolution();
        this.canvas.zoomIn(0);
        
    }
    
    private ArrayList<Punto> LeerPuntos(String path) throws FileNotFoundException
    {
        Reader reader = new Reader(path);
        return reader.getPuntos();
    }
}
