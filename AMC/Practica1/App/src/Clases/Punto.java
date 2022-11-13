/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.*;

/**
 *
* @author ElPsy
 */

public class Punto {
    public double x, y;

    public Punto(double a, double b) {
        x = a;
        y = b;
    }

    public double Distancia(Punto a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2) + Math.pow(a.y - this.y, 2));
    }

    /**
     * 
     * @param a
     * @param b
     * @return Devuelve la suma de las diastancias this|a + this|b
     */
    public double Distancia3(Punto a, Punto b) {
        return this.Distancia(a) + a.Distancia(b);
    }

    @Override
    public String toString() {
        return String.format("X: %f, Y: %f", x, y);
    }

}
