import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tunel {

    public enum Estado {
        VACIO,
        COCHE,
        FURGO
    }
    private final int NUM_TUNELES = 3;


    private int numCoches = 0;
    private int numFurgos = 0;

    private int esperaCoches = 0;
    private int esperaFurgos = 0;

    private int posLibres = 0;

    private Estado tuneles[];

    private ReentrantLock lock;
    private Condition condEntradaCoche;
    private Condition condEntradaFurgo;
    private MiCanvas canvas;

    public Tunel(MiCanvas _canvas){
        this.tuneles = new Estado[NUM_TUNELES];
        for(int i = 0; i<NUM_TUNELES; i++)
            this.tuneles[i] = Estado.VACIO;
    
        this.lock = new ReentrantLock();
        this.condEntradaCoche = this.lock.newCondition();
        this.condEntradaFurgo = this.lock.newCondition();
        this.posLibres = NUM_TUNELES;
        this.canvas = _canvas;
    }

    private int huecoLibre() {
        for (int i = 0; i < NUM_TUNELES; i++) {
            if (this.tuneles[i] == Estado.VACIO) {
                return i;
            }
        }
        return -1;
    }

    public int EntraCoche() throws InterruptedException {
        
        this.lock.lock();
        int id = canvas.AddCola(Estado.COCHE);
        int pos = -1;
        try {
            esperaCoches++;
            while ((numCoches == 2) || posLibres == 0 ) {
                condEntradaCoche.await();
                System.out.println("Esperando coche " + Thread.currentThread());
            }
            pos = this.huecoLibre();
            posLibres--;
            this.tuneles[pos] = Estado.COCHE;
            numCoches++;
            esperaCoches--;
            System.out.println("Entra coche: " + Thread.currentThread());
            canvas.AddTunel(Estado.COCHE, pos, id);
        } finally {
            this.lock.unlock();
        }
        return pos;
    }

    public void Salecoche(int posicion) {
        this.lock.lock();
        try {
            this.numCoches--;
            this.posLibres++;
            this.tuneles[posicion] = Estado.VACIO;
            System.out.println("Sale coche: " + Thread.currentThread());
            canvas.RemoveTunel(posicion);
            if (this.esperaFurgos > 0 &&    // Si hay furgos esperando Y
                    (numFurgos < 2) ||      // hay menos de dos furgos y hay coches esperando O
                    (esperaCoches == 0)) {  // si no hay coches esperando
                try{
                    System.out.println("LLamando Furgo\n");
                    this.condEntradaFurgo.signal();
                }
                catch(Exception e){
                    //System.out.println("Explotasion\n");
                }
            } else {
                try{
                    System.out.println("LLamando Coche\n");
                    this.condEntradaCoche.signal();
                    //this.condEntradaCoche.notify();
                }
                catch(Exception e){
                    //System.out.println("Explotasion\n");
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public int EntraFurgo() throws InterruptedException {
        this.lock.lock();
        int id = canvas.AddCola(Estado.FURGO);
        int pos = -1;
        try{
            this.esperaFurgos++;
            while(this.posLibres == 0 || 
            (this.numFurgos == 2 && this.esperaCoches > 0)){
                this.condEntradaFurgo.await();
            }
            System.out.println("Entra furgo: " + Thread.currentThread());
            this.esperaFurgos--;
            this.numFurgos++;
            posLibres--;
            pos = this.huecoLibre();
            canvas.AddTunel(Estado.FURGO, pos, id);
            this.tuneles[pos] = Estado.FURGO;
        } finally {
            this.lock.unlock();
        }
        return pos;
    }

    public void SaleFurgo(int posicion) {
        this.lock.lock();
        try {
            this.tuneles[posicion] = Estado.VACIO;
            this.numFurgos--;
            this.posLibres++;
            canvas.RemoveTunel(posicion);
            System.out.println("Sale furgo: " + Thread.currentThread());
            if (this.esperaFurgos > 0 && // Si hay furgos esperando Y
                    (numFurgos < 2) || // hay menos de dos furgos y hay coches esperando O
                    (esperaCoches == 0)) { // si no hay coches esperando
                    try{
                        System.out.println("LLamando Furgo\n");
                        this.condEntradaFurgo.signal();
                    }
                    catch(Exception e){
                    }
            } else{
                try{
                    System.out.println("LLamando Coche\n");
                    this.condEntradaCoche.signal();
                }
                catch(Exception e){
                    //System.out.println("Explotasion\n");
                }
            }
        } finally {
            this.lock.unlock();
        }
    }
}
