/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_practica4;

import static pcd_practica4.Constants.*;

/**
 *
 * @author Sergio
 */
public class Consumidor extends Thread {

    ColaLenta lacola;

    public Consumidor(ColaLenta c) {
        this.lacola = c;
    }

    public void Consumir() throws Exception {
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < USOS; ++i) {
            System.out.println("Desacola n: " + i);
            Object random = lacola.Desacola();
            Thread.sleep(rand.nextInt(MIN_WAIT, MAX_WAIT));
        }
        System.out.println("El consumidor acabo sus " + USOS + " usos. Deteniendo el programa...");
        System.exit(1);
    }

    @Override
    public void run() {
        try {
            this.Consumir();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion: " + e);
        }
    }
}
