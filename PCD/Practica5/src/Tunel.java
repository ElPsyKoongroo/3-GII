
/**
 *
 * @author ElPsy
 */
public class Tunel {
    public enum Estado {
        VACIO, 
        COCHE, 
        FURGO  
    }
    public static final int TUNELES = 3;
    private int tuneles_libres = TUNELES;
    private Estado[] tuneles;
    private MiCanvas canvas;

    public Tunel(MiCanvas _canvas) {
        tuneles = new Estado[TUNELES];
        for (int i = 0; i < TUNELES; i++) {
            tuneles[i] = Estado.VACIO;
        }
        tuneles_libres = TUNELES;
        canvas = _canvas;
    }

    public synchronized int EntraCoche() throws InterruptedException
    {
        int id = canvas.AddCola(Estado.COCHE);
        
        while (this.tuneles_libres == 0) {
            wait();
        }

        int i = 0;
        while (i < TUNELES && this.tuneles[i] != Estado.VACIO) {
            i++;
        }
        this.tuneles_libres--;
        this.tuneles[i] = Estado.COCHE;
        System.out.println("Coche entra a posicion: " + i);
        canvas.AddTunel(Estado.COCHE, i, id);
        return i;
    }

    public synchronized void SaleCoche(int posicion) {
        if (posicion < 0 || posicion >= TUNELES) {
            return;
        }
        this.tuneles_libres++;
        this.tuneles[posicion] = Estado.VACIO;
        System.out.println("Coche sale de posicion: " + posicion);
        canvas.RemoveTunel(posicion);
        notify();
    }

    private synchronized int getPosition(){
        for (int i = 0; i < TUNELES; i++) {
            boolean canGoIn = true;
            if (tuneles[i] != Estado.VACIO) {
                continue;
            }
            if (i - 1 >= 0 && tuneles[i - 1] == Estado.FURGO) {
                canGoIn = false;
            }
            if (i + 1 < TUNELES && tuneles[i + 1] == Estado.FURGO) {
                canGoIn = false;
            }
            if (canGoIn) {
                return i;
            }
        }
        return -1;
    }

    private synchronized int generalSolution() throws InterruptedException {
        int posicion = -1;
        while (this.tuneles_libres == 0 || (posicion = getPosition()) == -1) {
            wait();
        }
        return posicion;
    }

    public synchronized int EntraFurgo() throws InterruptedException {
        int id = canvas.AddCola(Estado.FURGO);
        
        int posicion = generalSolution();
        this.tuneles[posicion] = Estado.FURGO;
        this.tuneles_libres--;
        System.out.println("Furgoneta entra a posicion: " + posicion);
        canvas.AddTunel(Estado.FURGO, posicion, id);
        return posicion;
    }

    public synchronized void SaleFurgo(int posicion) {
        this.tuneles[posicion] = Estado.VACIO;
        tuneles_libres++;
        System.out.println("Furgoneta sale de posicion: " + posicion);
        canvas.RemoveTunel(posicion);
        notify();
    }

}
