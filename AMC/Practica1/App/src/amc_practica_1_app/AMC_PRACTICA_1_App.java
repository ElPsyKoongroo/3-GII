/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package amc_practica_1_app;
import Clases.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class AMC_PRACTICA_1_App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<Punto> puntos = new ArrayList<>();
        
        puntos.add(new Punto(2,2));
        puntos.add(new Punto(2,8));
        puntos.add(new Punto(4,5));
        puntos.add(new Punto(5,6));
        puntos.add(new Punto(6,5));
        puntos.add(new Punto(7,8));
        puntos.add(new Punto(6,2));
        puntos.add(new Punto(1,5));
        puntos.add(new Punto(9,5));
        puntos.add(new Punto(5,9));
        puntos.add(new Punto(4,2));
        puntos.add(new Punto(8,3));
        puntos.add(new Punto(2,7));
        puntos.add(new Punto(9,8));
        
        ArrayList<Punto> resultado;
        
        resultado = Algoritmo.SolucionExhaustiva(puntos);
        
        for (int i = 0; i < 3; i++)
        {
            System.out.println("Punto " + (i+1) + ": " + resultado.get(i));
        }
    }
    
}
