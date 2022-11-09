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
    private MiCanvas canvas;
    public Cuidador(Semaphore _cuidador, Semaphore _perro, MiCanvas _canvas) {
        perro = _perro;
        cuidador = _cuidador;
        canvas = _canvas;
    }

    @Override
    public void run()
    {
        while (true) {
            boolean adquirido = false;
            try{
                cuidador.acquire();
                this.canvas.cuidadorEntra();
                System.out.println("Cuidador le da regalo a chikene");
                adquirido = true;
                int time = ThreadLocalRandom.current().nextInt(MIN_RANDOM_TIME, MAX_RANDOM_TIME);
                Thread.sleep(time);
            }
            catch(Exception e)
            {
                System.out.println("Error en el cuidador " + e);
            }
            finally
            {
                this.canvas.cuidadorSale();
                perro.release();
                System.out.println("Chikene contento");
                //if(adquirido) perro.release();
            }
        }
    }
}
