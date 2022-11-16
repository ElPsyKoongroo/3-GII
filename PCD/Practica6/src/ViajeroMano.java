/**
 *
 *
* @author ElPsy
 *
 *
*/

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ViajeroMano  implements Runnable
{
    private final int MAX_RANDOM_TIME = 3000;
    private final int MIN_RANDOM_TIME = 1000;
    private Semaphore rayos;
    private Semaphore perro;
    private Semaphore cuidador;
    private MiCanvas canvas;
    public ViajeroMano(Semaphore _rayos, Semaphore _perro, Semaphore _cuidador, MiCanvas _canvas)
    {
        rayos = _rayos;
        perro = _perro;
        cuidador = _cuidador;
        canvas = _canvas;
    }

    @Override
    public void run()
    {
        try
        {
            int id = this.canvas.nuevoPasajeroMano();
            rayos.acquire();
            System.out.println("Entra viajero mano a rayos " + Thread.currentThread());
            this.canvas.entraRayosMano(id);
            int time = ThreadLocalRandom.current().nextInt(MIN_RANDOM_TIME, MAX_RANDOM_TIME);
            Thread.sleep(time);

            perro.acquire();
            this.canvas.pasajeroManoPerro();
            System.out.println("Entra viajero mano a perro "  + Thread.currentThread());
            rayos.release();
            System.out.println("sale viajero mano de rayos "  + Thread.currentThread());

            time = ThreadLocalRandom.current().nextInt(MIN_RANDOM_TIME, MAX_RANDOM_TIME);
            Thread.sleep(time);
            this.canvas.pasajeroSalePerro(id);

            System.out.println("Releases avaliable: " + cuidador.availablePermits());
            while (cuidador.availablePermits() > 0){}

            cuidador.release();
            
            System.out.println("Cuidador a perro");
        }
        catch(Exception e)
        {
            System.out.println("Ha ocurrido un error en un viajeroMano: " + e + "   "  + Thread.currentThread());
        }
    }
}
