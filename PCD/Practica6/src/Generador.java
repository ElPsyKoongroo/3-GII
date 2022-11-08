/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
* @author ElPsy
 */

 import java.util.concurrent.Semaphore;
 import java.util.concurrent.ThreadLocalRandom;

public class Generador {

    public static final int N_HILOS = 20;
    public static final int PROB_VIAJERO = 70;
    public static final int PASAJEROS_DELAY = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Semaphore chikene = new Semaphore(2);
        Semaphore cuidador = new Semaphore(-1);
        Semaphore rayoMaleta = new Semaphore(2);
        Semaphore rayoMano = new Semaphore(1);
        /*
        MiCanvas 
        */ 

        Thread[] Viajeros = new Thread[N_HILOS];
        Thread Cuidador = new Cuidador(cuidador, chikene);
        Cuidador.start();
        
        for(int i = 0; i< N_HILOS; ++i){
            int aux = ThreadLocalRandom.current().nextInt() % 100;
            if (aux >= PROB_VIAJERO) {
                ViajeroMano viajeroMano = new ViajeroMano(rayoMano, chikene, cuidador);
                Viajeros[i] = new Thread(viajeroMano);
                Viajeros[i].start();
            } else {
                Viajeros[i] = new ViajeroMaleta(rayoMaleta, chikene, cuidador);
                Viajeros[i].start();
            }
            Thread.sleep(PASAJEROS_DELAY);
        }

        for(Thread viajero : Viajeros){
            viajero.join();
        }

        Cuidador.interrupt();
        System.exit(0);
        

    }
    
}
