
/**
 *
 * @author ElPsy
 */

public class Coche extends Thread{
    private Tunel tunelsito;
    private final int MAX = 5000;
    private final int MIN = 1000;

    public Coche(Tunel _tunel){
        this.tunelsito = _tunel;
    }

    @Override
    public void run(){
        try {
            int posicion = this.tunelsito.EntraCoche();
            int sleep_duration = (int) (Math.random() * (this.MAX - this.MIN + 1) + this.MIN);
            Thread.sleep(sleep_duration);
            this.tunelsito.SaleCoche(posicion);
        } catch (Exception e) {
            System.out.println("Excepcion no controlada: " + e);
        }
    }
}
