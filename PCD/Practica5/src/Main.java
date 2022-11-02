/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/*
 * Conventions:
 *  snake_case -> Atributos de clase / Atributos privados 
 *  CamelCase  -> Nombre de clases
 *  camelCase  -> Metodos publicos
 *  CAPTICAL   -> constants
 */

/**
 *
 * @author ElPsy
 */
public class Main {
    private final static int N_VEHICULOS = 10;
	private final static int MAX_WAIT = 2000;
	private final static int MIN_WAIT = 1000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {      
        Tunel tunelsito = new Tunel();
        Thread[] vehiculos = new Thread[N_VEHICULOS];

        for(int i = 0; i<N_VEHICULOS; i++){
            int numero_random = (int) (Math.random() * (100 - 0 + 1) + 0);
            if (numero_random < 50) vehiculos[i] = new Coche(tunelsito);
            else vehiculos[i] = new Furgo(tunelsito);
            vehiculos[i].start();

            int sleep_time = (int) Math.random() * (MAX_WAIT - MIN_WAIT + 1) + MIN_WAIT;
            Thread.sleep(sleep_time);
        }

        for(int i = 0; i<N_VEHICULOS; i++){
            vehiculos[i].join();
        }
    }
    
}
