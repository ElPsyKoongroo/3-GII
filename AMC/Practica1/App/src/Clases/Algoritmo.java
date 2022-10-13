/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ElPsy
 */
public class Algoritmo
{
    
    public static void QuickSort(ArrayList<Punto> puntos, int primero, int ultimo) {
        Punto pivote;
        int posicion;
        
        if (primero < ultimo) {
            pivote = puntos.get(ultimo);
            posicion = partition(puntos, primero, ultimo, pivote);
            QuickSort(puntos, primero, posicion -1);
            QuickSort(puntos, posicion + 1, ultimo);
        }
    }
    
    private static int partition(ArrayList<Punto> puntos, int primero, int ultimo, Punto pivote){
        int i = primero;
        int j = primero;
        
        while (i <= ultimo) {
            if (puntos.get(i).x > pivote.x)
                ++i;
            else {
                intercambia(puntos, i, j);
                i++;
                j++;
            }
        }
        return j-1;
    }
    private static void intercambia(ArrayList<Punto> puntos, int i, int j){
        Punto aux = puntos.get(i);
        puntos.set(i, puntos.get(j));
        puntos.set(j, aux);
    }
    
    public static ArrayList<Punto> SolucionExhaustiva(int numPuntos, double maximo, double minimo)
    {
        Random rand = new Random();
        
        ArrayList<Punto> puntos = new ArrayList<Punto>();
        
        for (int i = 0; i < numPuntos; i++) {
            double x = rand.nextDouble() * (maximo - minimo) + minimo;
            double y = rand.nextDouble() * (maximo - minimo) + minimo;
            
            puntos.add(new Punto(x,y));
        }
        
        return SolucionExhaustiva(puntos);
    }
    public static ArrayList<Punto> SolucionExhaustiva(ArrayList<Punto> puntos)
    {
        double distanciaMinima = Double.MAX_VALUE;
        ArrayList<Punto> puntosMinimos = new ArrayList<>();
        
        for (int i = 0; i < puntos.size(); i++) {
            for (int j = 0; j < puntos.size(); j++) {
                if(j==i) continue;
                
                for (int k = 0; k < puntos.size(); k++) {
                    if(i==k || j==k) continue;
                    
                    double distancia = puntos.get(i).Distancia3(puntos.get(j), puntos.get(k));
                    
                    if(distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        
                        puntosMinimos.clear();
                        puntosMinimos.add(puntos.get(i));
                        puntosMinimos.add(puntos.get(j));
                        puntosMinimos.add(puntos.get(k));
                    }
                }
            }
        }       
        return puntosMinimos;
    }
    
    //public static ArrayList<Punto> Solucion
}
