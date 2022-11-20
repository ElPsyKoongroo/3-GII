import java.awt.Frame;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
* @author ElPsy
 */
public class Generador {

    private static final int MAX_ESPERA = 2000;
    private static final int MIN_ESPERA = 1000;
    private static final int NUM_VEHICULOS = 10;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Frame frame = new Frame();
        frame.setSize(800, 500);
        MiCanvas canvas = new MiCanvas();
        frame.add(canvas);
        canvas.setVisible(true);
        frame.setVisible(true);
        
        Tunel tunelsito = new Tunel(canvas);
        Thread vehiculos[] = new Thread[NUM_VEHICULOS];
        

        for(int i = 0; i<NUM_VEHICULOS; i++){
            int posibilidad = (int) (Math.random() * (100 - 0 + 1) + 0);
            if (posibilidad > 50){
                vehiculos[i] = new Coche(tunelsito);
            } else {
                vehiculos[i] = new Thread(new Furgo(tunelsito));
            }
            vehiculos[i].start();
            int sleep_duration = (int) (Math.random() * (MAX_ESPERA - MIN_ESPERA + 1) + MIN_ESPERA);
            Thread.sleep(sleep_duration);
        }

        for(Thread t : vehiculos) {
            t.join();
        }
    }
    
}
