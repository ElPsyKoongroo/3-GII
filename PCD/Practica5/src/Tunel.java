/**
 *
 * @author ElPsy
 */
public class Tunel {

    private enum Estado {
        VACIO, // 0
        COCHE, // 1
        FURGO  // 2
    }    
    
    
    public static final int TUNELES = 3;
    
    private int tuneles_libres = TUNELES;
    private Estado[] tuneles;

    public Tunel(){
        tuneles = new Estado[TUNELES];
        for (int i = 0 ; i < TUNELES; i++) {
            tuneles[i] = Estado.VACIO;
        }
        tuneles_libres = TUNELES;
    }

    public synchronized void EntraCoche() throws InterruptedException {
        while(this.tuneles_libres==0) wait();

        for(int j = 0; j < TUNELES; ++j){
            if(this.tuneles[j] == Estado.VACIO){
                this.tuneles[j] = Estado.COCHE;
                break;
            }
        }

        this.tuneles_libres--; 
    }

    public synchronized void SaleCoche(int posicion){
        // en que momento va a llegar una posicion menor que 0 ?
        // por si acaso
        if (posicion < 0 || posicion >= TUNELES) return;
        this.tuneles_libres++;
        this.tuneles[posicion] = Estado.VACIO;
        notify(); 
    }


    // 
    private synchronized boolean furgoLateral() {
        // queremos saber si hay 
        return this.tuneles[0] == Estado.FURGO || this.tuneles[2] == Estado.FURGO;
    }

    public void EntraFurgo(){
        //S- Tenemos que mirar si el hueco del centro es el libre
        // y a los lados hay furgonetas
        // mejor que != Vacio es mejor == Furgo, porque si hay coche tambien
        // puede entrar  

        // Ahora tenemos que mirar que si el hueco es el del centro, que no
        // haya furgos a los lado
        /* 
            F - C - F Ok
            C - C - F Ok
            F - F - C Mal
            C - F - C Ok

            V - V - V 
            V - F - V
            C - F - C Si puede entrar
            F - V - F No puede entrar 

            Llegas, 
            1 - Hay hueco libre ?
            2 - Lo que hay en el centro es una furgoneta ?
            3 - Si el hueco libre esta en el centro, hay furgonetas a los laterales ?
            
            while (posicionesLibres == 0) wait()

            for (int i = 0; i < TUNELES; i++)
            {
                if(tuneles[i] != 'V') continue;

                bool canGoIn = true;

                if(i-1 >= 0 && tuneles[i-1] == 'F') canGoIn = false;

                if(i+1 < TUNELES && tuneles[i+1] == 'F') canGoIn = false;

                if(!canGoIn)
                {
                    notifyAll();

                }

                meterlo
                break;
            }
        */

        while(
            this.tuneles_libres == 0 ||
            this.tuneles[1] == Estado.FURGO || 
            this.tuneles[1] == Estado.VACIO && this.furgoLateral()
        ){
            
        }
    }
    public void SaleFurgo(){
        
    }
    
}
