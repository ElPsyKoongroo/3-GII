package practica8;

import java.util.concurrent.Semaphore;

public class Tienda {
    private static final int nVendedores = 2;
    private Semaphore semaphore;
    private CanvasTienda canvas;
    private boolean[] libres; // vendedor , tecnico
    private int compradores;
    public Tienda(CanvasTienda _canvas){
        canvas = _canvas;
        semaphore = new Semaphore(nVendedores);
        libres = new boolean[]{true,true};
        compradores = 0;
    }

    
    public char entracompra(int id) throws InterruptedException 
    {
        canvas.inserta('C', id);
        compradores++;
        char donde;
        while(true)
        { 
            semaphore.acquire();
            if(libres[0])
            {
                libres[0] = false;
                donde = 'V';
                break;
            }
            // Comprobar primero si el mecanico esta libre
            else if(compradores > 2 && libres[1]) {
                libres[1] = false;
                donde = 'M';
                break;
            }
            semaphore.release();
        }
    
        canvas.quita('C', id);
        compradores--;
        canvas.compra(donde, id);
    

        return donde;
    }

    public void salecompra(char donde) throws InterruptedException {
        canvas.finalizado(donde);
        if(donde == 'V') libres[0] = true;
        else libres[1] = true;
        semaphore.release(); 
    }

 
    public void entrarepara(int id) throws InterruptedException {
        canvas.inserta('R', id);
        while(true)
        {
            semaphore.acquire();
            if(libres[1] && compradores <= 2) break;
            semaphore.release();
        }
        libres[1] = false;
        canvas.quita('R', id);
        canvas.repara(id);
    }

    public void salerepara() throws InterruptedException {
        canvas.finalizado('R');
        libres[1] = true;
        semaphore.release();
    }

}
