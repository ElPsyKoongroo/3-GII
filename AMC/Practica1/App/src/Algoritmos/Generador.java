/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import java.util.ArrayList;
import java.util.Random;

import Clases.Arista;
import Clases.Punto;

/**
 *
* @author ElPsy
 */
public class Generador {
    /**
     *
     * @param numPuntos el numero de puntos aleatorios para generar
     * @param maximo distancia maxima
     * @param minimo distancia minima
     * @return Puntos i,j,k que forman solucion
     */
    public static ArrayList<Punto> GeneraPuntos(int numPuntos, double maximo, double minimo) {
        Random rand = new Random();

        ArrayList<Punto> puntos = new ArrayList<Punto>();

        for (int i = 0; i < numPuntos; i++) {
            double x = rand.nextDouble() * (maximo - minimo) + minimo;
            double y = rand.nextDouble() * (maximo - minimo) + minimo;

            puntos.add(new Punto(x, y));
        }

        return puntos;
    }  

    public static ArrayList<Arista> GeneraAristas(ArrayList<Punto> puntos){
		
		ArrayList<Arista> aristas = new ArrayList<>();

        for(int i = 0; i<puntos.size(); i++){
            for(int j = i+1; j<puntos.size(); j++){
				aristas.add(new Arista(puntos.get(i), puntos.get(j)));
            }
        }
        return aristas; 
    }
}
