/*
 * 
* @author ElPsy
 *
 */

public class Furgo extends Thread{
    private Tunel tunelsito;
    private final int MAX = 5000;
    private final int MIN = 1000;

    public Furgo(Tunel _tunel){
        this.tunelsito = _tunel;
    }

    @Override
    public void run(){
        try {
            int posicion = this.tunelsito.EntraFurgo();
            int sleep_duration = (int) (Math.random() * (this.MAX - this.MIN + 1) + this.MIN);
            Thread.sleep(sleep_duration);
            this.tunelsito.SaleFurgo(posicion);
        } catch (Exception e) {
            System.out.println("Excepcion no controlada: " + e);
        }
    }
}
