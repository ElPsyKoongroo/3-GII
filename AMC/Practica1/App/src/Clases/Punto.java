/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.util.*;

/**
 *
 * @author usuario
 */
public class Punto
{
    public double x,y;
    public Punto(double a, double b)
    {
        x = a;
        y = b;
    }
    
    public double Distancia(Punto a)
    {
        return Math.sqrt(Math.pow(a.x-x, 2) + Math.pow(a.y-y, 2));
    }
    public double Distancia3(Punto a, Punto b)
    {
        return this.Distancia(a) + a.Distancia(b);
    }
    
    @Override
    public String toString()
    {
        return String.format("X: %f, Y: %f", x, y);
    }
    
}
