/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_practica3;

import static pcd_practica3.Constants.*;

/**
 *
 * @author ElPsy
 */
public class Productor extends Thread {

    private ColaLenta lacola;
    private int tries = 0;
    private final int max_tries = 3;

    public Productor(ColaLenta c) {
        this.lacola = c;
    }

    public void Producir() throws Exception {
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < USOS; ++i) {
            int random = (int) (rand.nextInt(10));
            System.out.println("Acola");
            try {
                lacola.Acola(random);
            } catch (Exception e) {
                System.out.println(e + " en el productor: " + Thread.currentThread().getName());
                return;
            }
            Thread.sleep(rand.nextInt(MIN_WAIT, MAX_WAIT));
        }
    }

    @Override
    public void run() {
        try {
            this.Producir();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion: " + e);
        }
    }
}
