package Clases;

import java.util.ArrayList;
import Clases.Punto;

public class QuickSort {
    public static void Ordena(ArrayList<Punto> puntos, int primero, int ultimo) {
        Punto pivote;
        int posicion;
        
        if (primero < ultimo) {
            pivote = puntos.get(ultimo);
            posicion = partition(puntos, primero, ultimo, pivote);
            Ordena(puntos, primero, posicion -1);
            Ordena(puntos, posicion + 1, ultimo);
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
}
