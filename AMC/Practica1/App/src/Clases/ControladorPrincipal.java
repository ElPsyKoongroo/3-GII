/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author usuario
 */
public class ControladorPrincipal
{
    private FramePrincipal fPrincipal;
    private ActionListener aL_RadioButtons;
    private ActionListener aL_All;
    
    boolean puntosAleatorios;
    boolean metodoAlgoritmo; //false = exh, true = DyV
    
    public ControladorPrincipal()
    {
        fPrincipal = new FramePrincipal();
        aL_RadioButtons = (ActionEvent e) -> actionPerformedRadioButtons(e);
        aL_All = (ActionEvent e) -> actionPerformedAll(e);
        
        ConfigureFrame();
    }

    private void addListeners() {
        fPrincipal.rB_Exh.addActionListener(aL_RadioButtons);
        fPrincipal.rB_DyV.addActionListener(aL_RadioButtons);
        
        fPrincipal.rB_PuntosAle.addActionListener(aL_RadioButtons);
        fPrincipal.rB_PuntosFic.addActionListener(aL_RadioButtons);
    }
    
    private void ConfigureFrame()
    {
        puntosAleatorios = true;
        fPrincipal.rB_PuntosAle.setSelected(true);
        
        metodoAlgoritmo = true;
        fPrincipal.rB_DyV.setSelected(true);
        
        fPrincipal.setLocationRelativeTo(null);
        fPrincipal.setVisible(true);
        
        addListeners();
    }
    
    public void actionPerformedRadioButtons(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "rB_Exhaust_Comm":
            {
                break;
            }
            case "rB_DyV_Comm":
            {
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
    
    public void actionPerformedAll(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "b_Calcular_Comm":
            {
                CalcularPuntos();
            }
        }
    }

    private void CalcularPuntos()
    {
        System.out.println("Pipo");
        ArrayList<Punto> puntos = new ArrayList<>();
        
        try{
            puntos = LeerPuntos("puntos.tsp");
        }
        catch(Exception e){
            System.exit(1);
        }
        QuickSort.Ordena(puntos, 0, puntos.size()-1);
        
        ArrayList<Punto> DyB;
        DyB = Algoritmo.DivideVenceras(puntos);
        
        fPrincipal.canvasPuntos = new ExtraCanvas(fPrincipal.getSize());
        
        ((ExtraCanvas)fPrincipal.canvasPuntos).addPuntos(puntos);
        ((ExtraCanvas)fPrincipal.canvasPuntos).drawPoints();
        
        ((ExtraCanvas)fPrincipal.canvasPuntos).addSolucion(DyB);
        ((ExtraCanvas)fPrincipal.canvasPuntos).drawSolution();
    }
    
    private ArrayList LeerPuntos(String path) throws FileNotFoundException
    {
        Reader reader = new Reader(path);
        return reader.getPuntos();
    }
}
