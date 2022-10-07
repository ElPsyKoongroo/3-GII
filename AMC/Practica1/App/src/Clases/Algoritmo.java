/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.util.ArrayList;

/**
 *
 * @author ElPsy
 */
public class Algoritmo
{
    public static ArrayList<Punto> SolucionExhaustiva(ArrayList<Punto> puntos)
    {
        double distanciaMinima = Double.MAX_VALUE;
        ArrayList<Punto> puntosMinimos = new ArrayList<>();
        
        for (int i = 0; i < puntos.size(); i++)
        {
            for (int j = 0; j < puntos.size(); j++)
            {
                if(j==i) continue;
                
                for (int k = 0; k < puntos.size(); k++)
                {
                    if(i==k || j==k) continue;
                    
                    double distancia = puntos.get(i).Distancia3(puntos.get(j), puntos.get(k));
                    
                    if(distancia < distanciaMinima)
                    {
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
}
