/**
 *
* @author ElPsy
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Cuidador extends Thread
{
    private final int MAX_RANDOM_TIME = 3000;
    private final int MIN_RANDOM_TIME = 1000;
    private Semaphore perro;
    private Semaphore cuidador;
    public Cuidador(Semaphore _cuidador, Semaphore _perro) {
        perro = _perro;
        cuidador = _cuidador;
    }

    @Override
    public void run()
    {
        while (true) {
            boolean adquirido = false;
            try
            {
                cuidador.acquire();
                adquirido = true;
                int time = ThreadLocalRandom.current().nextInt() % (MAX_RANDOM_TIME - MIN_RANDOM_TIME) + MIN_RANDOM_TIME;
                Thread.sleep(time);
            }
            catch(Exception e)
            {
                System.out.println("Error en el cuidador");
            }
            finally
            {
                perro.release();

                if(adquirido) cuidador.release();
            }
        }
    }
}
