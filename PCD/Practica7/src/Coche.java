public class Coche extends Thread {
    private final int MAX_ESPERA = 3000;
    private final int MIN_ESPERA = 1000;
    private Tunel tunelsito;

    public Coche(Tunel _tunel){
        this.tunelsito = _tunel;
    }

    @Override
    public void run(){
        try{
            System.out.println("Coche: " + currentThread());
            int pos = this.tunelsito.EntraCoche();
            int sleep_duration = (int) (Math.random() * (this.MAX_ESPERA - this.MIN_ESPERA + 1) + this.MIN_ESPERA);
            sleep(sleep_duration);
            this.tunelsito.Salecoche(pos);
        } catch (InterruptedException e){
            System.out.println("Excepcion en coche: " + Thread.currentThread() + "\n" + e);
        }        
    };
}
