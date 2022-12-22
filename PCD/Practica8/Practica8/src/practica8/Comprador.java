/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8;

import java.util.concurrent.Callable;

/**
 *
 * @author ElPsy
 */

public class Comprador implements Callable<Generador.ReturnValue> {
    
    private Tienda tienda;
    private static int ID_GLOBAL = 1;

    private int MIN_ESPERA = 1000;
    private int MAX_ESPERA = 3000;

    private int id;

    public Comprador(Tienda _tienda){
        tienda = _tienda;
        
        id = ID_GLOBAL++;
    }

    @Override
    public Generador.ReturnValue call() throws Exception {
        double sleep_duration = 0;
        try{
            System.out.println("Entra cliente a comprar: " + Thread.currentThread());
            char donde = this.tienda.entracompra(id);
            sleep_duration = (double) (Math.random() * (this.MAX_ESPERA - this.MIN_ESPERA + 1) + this.MIN_ESPERA);
            Thread.sleep((int)sleep_duration);
            this.tienda.salecompra(donde);
        }
        catch(InterruptedException e){
            System.out.println("Excepcion al comprar: " + Thread.currentThread() + "\n" + e);
        }

        return new Generador.ReturnValue(sleep_duration, true);
    }

}
