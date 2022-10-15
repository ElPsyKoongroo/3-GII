/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package amc_practica_1_app;
import Clases.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *
 * @author ElPsy 
 */

public class AMC_PRACTICA_1_App {
    
    public static void main(String[] args) {

         
        ArrayList<Punto> in = new ArrayList<Punto>(); 
        try {
            Reader reader = new Reader("d657.tsp");
            in = reader.getPuntos();

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e);
        }
        
        in = Algoritmo.GeneraPuntos(20_000, 1000, 0);
        QuickSort.Ordena(in, 0, in.size()-1);
        try{
            long start = System.currentTimeMillis();
            ArrayList<Punto> DyB = Algoritmo.DivideVenceras(in);
            long end = System.currentTimeMillis();
            System.out.println((end-start) + " ms");
            for (Punto p : DyB) {
                System.out.println(p);
            }
        } catch (Exception e) {
            System.exit(-1);
        }

        // for (Punto p : exhaust) {
        //    System.out.println(p); 
        // }
         
        // System.out.println("\n\n\n");
    }
    
}
