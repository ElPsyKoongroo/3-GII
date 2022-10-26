/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package amc_practica_1_app;
import Clases.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.awt.Button;

/**
 *
 * @author ElPsy 
 */

public class AMC_PRACTICA_1_App {
    
    public static void main(String[] args) {

        Visualizer neovi = new Visualizer();
        neovi.setSize(1000, 1000);
        neovi.setVisible(true);
        
        Button zoomButton = new java.awt.Button();
        zoomButton.setLabel("ZOOM IN");
        zoomButton.setSize(30, 15);
        
        ArrayList<Punto> in = new ArrayList<Punto>(); 
        try {
            Reader reader = new Reader("puntos.tsp");
            in = reader.getPuntos();

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e);
        }
        
        ExtraCanvas caravana = new ExtraCanvas(neovi.getSize());
        neovi.add(caravana);

        caravana.addPuntos(in);
        caravana.drawPoints();



        //in = Algoritmo.GeneraPuntos(10_000, 100, 0);
        QuickSort.Ordena(in, 0, in.size()-1);
        ArrayList<Punto> DyB;
        //long start = System.currentTimeMillis();
        DyB = Algoritmo.DivideVenceras(in);
        //long end = System.currentTimeMillis();
        //System.out.println((end-start) + " ms");
        //System.out.println(DyB.get(0).Distancia3(DyB.get(1), DyB.get(2)));
      

        
        caravana.addSolucion(DyB);
        caravana.drawSolution();
        for (Punto p : DyB) {
            System.out.println(p);
        }

        Scanner s = new Scanner(System.in);
        String input = "";
        while ((input = s.nextLine()) != null){
            String z[] = input.split(" ");
            if (z[0].equals("in")) {
                caravana.zoomIn(Double.parseDouble(z[1]));
            } else if (z[0].equals("out")) {
                caravana.zoomOut(Double.parseDouble(z[1]));
            } else if (z[0].equals("exit")) break;
        }

        s.close();
        System.exit(0);
    }
    
}
