import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tunel {

    private final int NUM_TUNELES = 3;

    private enum Estado {
        VACIO,
        COCHE,
        FURGO
    }

    private int numCoches = 0;
    private int numFurgos = 0;

    private int esperaCoches = 0;
    private int esperaFurgos = 0;

    private int posLibres = 0;

    private Estado tuneles[];

    private ReentrantLock lockEntrada;
    private ReentrantLock lockSalida;
    private Condition condEntradaCoche;
    private Condition condEntradaFurgo;

    public Tunel(){
        this.tuneles = new Estado[NUM_TUNELES];
        for(int i = 0; i<NUM_TUNELES; i++)
            this.tuneles[i] = Estado.VACIO;
    
        this.lockEntrada = new ReentrantLock();
        this.lockSalida = new ReentrantLock();
        this.condEntradaCoche = this.lockEntrada.newCondition();
        this.condEntradaFurgo = this.lockEntrada.newCondition();
        this.posLibres = NUM_TUNELES;
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
        this.lockEntrada.lock();
        int pos = -1;
        try {
            esperaCoches++;
            while (numCoches == 2 || posLibres == 0) {
                condEntradaCoche.await();
            }
            System.out.println("Entra coche: " + Thread.currentThread());
            esperaCoches--;
            numCoches++;
            posLibres--;
            pos = this.huecoLibre();
            this.tuneles[pos] = Estado.COCHE;
        } finally {
            this.lockEntrada.unlock();
        }
        return pos;
        // TODO! add id to draw car in canvas
    }

    public void Salecoche(int posicion) {
        this.lockSalida.lock();
        try {
            this.numCoches--;
            this.posLibres++;
            this.tuneles[posicion] = Estado.VACIO;
            if (this.esperaFurgos > 0 && // Si hay furgos esperando Y
                    (numFurgos < 2 && esperaCoches > 0) || // hay menos de dos furgos y hay coches esperando O
                    (esperaCoches == 0)) { // si no hay coches esperando
                this.condEntradaFurgo.signal();
            } else if (numCoches < 2) {
                this.condEntradaCoche.signal();
            }
        } finally {
            this.lockSalida.unlock();
        }
        // TODO! remove the car from the canvas
    }

    public int EntraFurgo() throws InterruptedException {
        this.lockEntrada.lock();
        int pos = -1;
        try{
            this.esperaFurgos++;
            while(this.posLibres == 0 && 
            (this.numFurgos == 2 || this.esperaCoches > 0) ||
            (this.esperaCoches == 0)){
                this.condEntradaFurgo.await();
            }
            System.out.println("Entra furgo: " + Thread.currentThread());
            this.esperaFurgos--;
            this.numFurgos++;
            posLibres--;
            pos = this.huecoLibre();
            this.tuneles[pos] = Estado.FURGO;
        } finally {
            this.lockEntrada.unlock();
        }
        return pos;
        // TODO! add id for painting Furgo in canvas
    }

    public void SaleFurgo(int posicion) {
        this.lockSalida.lock();
        try {
            this.numFurgos--;
            this.posLibres++;
            this.tuneles[posicion] = Estado.VACIO;
            if (this.esperaFurgos > 0 && // Si hay furgos esperando Y
                    (numFurgos < 2 && esperaCoches > 0) || // hay menos de dos furgos y hay coches esperando O
                    (esperaCoches == 0)) { // si no hay coches esperando
                this.condEntradaFurgo.signal();
            } else if (numCoches < 2) {
                this.condEntradaCoche.signal();
            }
        } finally {
            this.lockSalida.unlock();
        }
    }
}
