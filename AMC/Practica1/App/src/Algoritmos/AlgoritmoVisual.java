/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import Clases.ExtraCanvas;
import Clases.Punto;

/**
 *
* @author ElPsy
 */
public class AlgoritmoVisual {

    private static final int START = 0;
    private static final int END = 1;
    private static ArrayList<Punto> mejoresPuntos;
    private static double mejorDistancia;
    private ExtraCanvas canvas;

    public AlgoritmoVisual(ExtraCanvas _canvas) {
        this.canvas = _canvas;
    }

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

    /**
     *
     * @param puntos
     * @return Puntos i,j,k que forman la mejor solucion
     */
    public static ArrayList<Punto> SolucionExhaustiva(ArrayList<Punto> puntos) {
        double distanciaMinima = Double.MAX_VALUE;
        ArrayList<Punto> puntosMinimos = new ArrayList<>();

        for (int i = 0; i < puntos.size(); i++) {
            for (int j = 0; j < puntos.size(); j++) {
                if (j == i) {
                    continue;
                }

                for (int k = 0; k < puntos.size(); k++) {
                    if (i == k || j == k) {
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

    private double CalculaFixed(ArrayList<Punto> puntos, int start, int end) {
        double distanciaMinima = Double.MAX_VALUE;
        //System.out.println((end - start));
        if (end - start + 1 < 3) {
            return distanciaMinima;
        }
        ++end;
        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                if (j == i) {
                    continue;
                }

                for (int k = i + 1; k < end; k++) {
                    if (j == k) {
                        continue;
                    }

                    ArrayList<Punto> calculating = new ArrayList<>();

                    calculating.add(puntos.get(i));
                    calculating.add(puntos.get(j));
                    calculating.add(puntos.get(k));
                    this.canvas.addSolucion(calculating);
                    this.canvas.addPuntos(puntos);
                    this.canvas.paint_by_steps((int)puntos.get(start).x, (int)puntos.get(end).x);

                
                    //this.canvas.drawLine(this.canvas.getGraphics() ,(int) puntos.get(start).x, 1000, (int) puntos.get(start).x, -1000, Color.GREEN);
                    //this.canvas.drawLine(this.canvas.getGraphics(), (int) (int) puntos.get(end).x, 1000, (int) (int) puntos.get(end).x, -1000, Color.GREEN);

                    try {
                        Thread.sleep(100);
                    } catch(Exception e){
                        System.out.println("No pude dormir bien");
                    }
                    

                    double distancia = puntos.get(i).Distancia3(puntos.get(j), puntos.get(k));

                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        if (distanciaMinima < mejorDistancia) {
                            mejorDistancia = distanciaMinima;
                            mejoresPuntos.clear();
                            mejoresPuntos.add(puntos.get(i));
                            mejoresPuntos.add(puntos.get(j));
                            mejoresPuntos.add(puntos.get(k));
                        }
                    }
                }
            }
        }
        return distanciaMinima;
    }

    private int[] GetPointsBetween(ArrayList<Punto> puntos, Double start, Double end) {
        int start_index = -1;
        while (puntos.get(++start_index).x < start) {
        };

        int end_index = start_index - 1;
        boolean drc = false;
        while (puntos.get(++end_index).x <= end && (drc = end_index + 1 < puntos.size())) {
        };
        if (!drc) {
            --end_index;
        }
        // while (puntos.get(++end_index).x <= end && end_index+1 < puntos.size()) {};

        int indices[] = {start_index, end_index};
        return indices;
    }

    private boolean PuntosRepetidos(ArrayList<Punto> puntos, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            if (puntos.get(i).x != puntos.get(i + 1).x) {
                return false;
            };
        }
        return true;
    }

    /**
     * Para usar este algoritmo es necesario que los puntos esten ordenados por
     * la coordenada X
     *
     * @param puntos
     * @return
     */
    public ArrayList<Punto> DivideVenceras(ArrayList<Punto> puntos) {
        mejorDistancia = Double.MAX_VALUE;
        mejoresPuntos = new ArrayList<>();
        this.canvas.addPuntos(puntos);
        DivideVenceras(puntos, puntos.get(0).x, puntos.get(puntos.size() - 1).x, 0);
        return mejoresPuntos;
    }

    private double DivideVenceras(ArrayList<Punto> puntos, double start, double end, int it) {

        double mitad = (start + end) / 2;
        int indices[] = GetPointsBetween(puntos, start, end);

        if (indices[END] - indices[START] + 1 < 3) {
            return -1;
        }

        if (PuntosRepetidos(puntos, indices[START], indices[END])) {
            return -1;
        }

        if (indices[END] - indices[START] + 1 <= 6) {
            return CalculaFixed(puntos, indices[START], indices[END]);
        }

        double izq = DivideVenceras(puntos, start, mitad, it + 1);
        double drc = DivideVenceras(puntos, mitad, end, it + 1);

        if (izq == -1 && drc == -1) {
            return CalculaFixed(puntos, indices[START], indices[END]);
        }

        double distanciaMin = izq;
        if (izq == -1) {
            distanciaMin = drc;
        } else if (drc != -1 && drc < izq) {
            distanciaMin = drc;
        }

        int[] new_indices = GetPointsBetween(puntos, mitad - mejorDistancia, mitad + mejorDistancia);
        if (distanciaMin < end - start) {
            double aux = CalculaFixed(puntos, new_indices[START], new_indices[END]);
            return aux < distanciaMin ? aux : distanciaMin;
        }

        double aux = CalculaFixed(puntos, indices[START], indices[END]);
        return aux < distanciaMin ? aux : distanciaMin;

    }
}
