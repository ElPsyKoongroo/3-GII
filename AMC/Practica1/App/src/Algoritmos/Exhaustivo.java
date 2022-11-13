/**
*
* @author ElPsy
*/
package Algoritmos;

import java.util.ArrayList;
import Clases.Punto;

public class Exhaustivo {
     /**
     *
     * @param puntos
     * @return Puntos i,j,k que forman la mejor solucion
     */
    public static ArrayList<Punto> Calcula(ArrayList<Punto> puntos) {
        double distanciaMinima = Double.MAX_VALUE;
        ArrayList<Punto> puntosMinimos = new ArrayList<>();

        for (int i = 0; i < puntos.size(); i++) {
            for (int j = 0; j < puntos.size(); j++) {
                if (j == i) {
                    continue;
                }

                for (int k = i + 1; k < puntos.size(); k++) {
                    if (j == k) {
                        continue;
                    }

                    double distancia = puntos.get(i).Distancia3(puntos.get(j), puntos.get(k));

                    if (distancia < distanciaMinima) {
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
