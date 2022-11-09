import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
* @author ElPsy
 */

public class ViajeroMaleta extends Thread {
    
    private Semaphore rayos;
    private Semaphore perro;
    private Semaphore cuidador;
    private final int MAX_RANDOM_TIME = 3000;
    private final int MIN_RANDOM_TIME = 1000;
    private MiCanvas canvas;

    public ViajeroMaleta(Semaphore _rayos, Semaphore _perro, Semaphore _cuidador, MiCanvas _canvas) {
        rayos = _rayos;
        perro = _perro;
        cuidador = _cuidador;
        canvas = _canvas;
    }

    @Override
    public void run(){

        try {
            int id = this.canvas.nuevoPasajeroMaleta();
            rayos.acquire();
            System.out.println("Entra viajero maleta a rayos " + Thread.currentThread());
            this.canvas.entraRayosMaleta(id);
            int time = ThreadLocalRandom.current().nextInt(MIN_RANDOM_TIME, MAX_RANDOM_TIME);
                Thread.sleep(time);


            perro.acquire();
            this.canvas.pasajeroMaletaPerro(id);
            System.out.println("Entra viajero maleta a perro " + Thread.currentThread());
            rayos.release();
            System.out.println("sale viajero maleta de rayos " + Thread.currentThread());

            time = ThreadLocalRandom.current().nextInt(MIN_RANDOM_TIME, MAX_RANDOM_TIME);
            Thread.sleep(time);
            this.canvas.pasajeroSalePerro(id);

            System.out.println("Releases avaliable: " + cuidador.availablePermits());
            while (cuidador.availablePermits() != 0 && cuidador.availablePermits() != -1){}
            cuidador.release();

            System.out.println("Cuidador a perro " + Thread.currentThread());
        }
        catch(Exception e) {
            System.out.println("Ha ocurrido un error en un viajeroMaleta: " + e + "   " + Thread.currentThread());
        }
    }
}
