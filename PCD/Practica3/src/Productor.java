/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 

/**
 *
 * @author ElPsy
 */
public class Productor extends Thread {

    ColaLenta lacola;

    public Productor(ColaLenta c) {
        this.lacola = c;
    }

    public void Producir() throws Exception {
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < 100; ++i) {
            int random = (int) (rand.nextInt(10));
            System.out.println("Acola");
            lacola.Acola(random);
            Thread.sleep(rand.nextInt(100, 1000));
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
