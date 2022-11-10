/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;
import Algoritmos.Dijkstra;
import java.util.ArrayList;

import Algoritmos.Generador;
import Controlador.ControladorPrincipal;
import Clases.*;

/**
 *
* @author ElPsy
 */

public class AMC_PRACTICA_1_App {
    
    public static void main(String[] args){
        //ControladorPrincipal f = new ControladorPrincipal();
        prueba();
    }
    
    public static void prueba(){
        ArrayList<Punto> a = Generador.GeneraPuntos(500, 800, 0);
        ArrayList<Arista> b = Generador.GeneraAristas(a);
        
        Dijkstra c = new Dijkstra(b, a);
        c.CalculaBien();
        
        for (int i : c.prev)
        {
            System.out.println(i);
        }
    }
}
