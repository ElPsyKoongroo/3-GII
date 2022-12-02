/**
*
* @author ElPsy
*/
public class Furgo implements Runnable {
    private final int MAX_ESPERA = 3000;
    private final int MIN_ESPERA = 1000;
    private Tunel tunelsito;

    public Furgo(Tunel _tunel){
        this.tunelsito = _tunel;
    }

    @Override
    public void run(){
        try{
            System.out.println("Furgo: " + Thread.currentThread());
            int pos = this.tunelsito.EntraFurgo();
            int sleep_duration = (int) (Math.random() * (this.MAX_ESPERA - this.MIN_ESPERA + 1) + this.MIN_ESPERA);
            Thread.sleep(sleep_duration);
            System.out.println(sleep_duration);
            this.tunelsito.SaleFurgo(pos);
        } catch (InterruptedException e){
            System.out.println("Excepcion en coche: " + Thread.currentThread() + "\n" + e);
        }        
    };
}
