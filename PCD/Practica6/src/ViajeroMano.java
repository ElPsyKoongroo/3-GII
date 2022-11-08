/*
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
    public ViajeroMano(Semaphore _rayos, Semaphore _perro, Semaphore _cuidador)
    {
        rayos = _rayos;
        perro = _perro;
        cuidador = _cuidador;
    }

    @Override
    public void run()
    {
        try
        {
            rayos.acquire();
            System.out.println("Entra viajero mano a rayos");
            int time = ThreadLocalRandom.current().nextInt() % (MAX_RANDOM_TIME - MIN_RANDOM_TIME) + MIN_RANDOM_TIME;
            Thread.sleep(time);

            perro.acquire();
            System.out.println("Entra viajero mano a perro");
            rayos.release();
            System.out.println("sale viajero mano de rayos");

            time = ThreadLocalRandom.current().nextInt() % (MAX_RANDOM_TIME - MIN_RANDOM_TIME) + MIN_RANDOM_TIME;

            cuidador.acquire();

            System.out.println("Cuidador a perro");
        }
        catch(Exception e)
        {
            System.out.println("Ha ocurrido un error en un viajeroMano");
        }
    }
}
