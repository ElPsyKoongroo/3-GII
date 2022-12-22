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
public class Reparador implements Callable<Generador.ReturnValue> {

    private static int ID_GLOBAL = 1;
    private Tienda tienda;
    
    private int MIN_ESPERA = 1000;
    private int MAX_ESPERA = 3000;
    
    private int id;

    public Reparador(Tienda _tienda){
        id = ID_GLOBAL++;
        tienda = _tienda;
    }

    @Override
    public Generador.ReturnValue call() throws Exception {
        double sleep_duration = 0;
        try{
            System.out.println("Entra cliente a reparar: " + Thread.currentThread());
            this.tienda.entrarepara(id);
            sleep_duration = (double) (Math.random() * (this.MAX_ESPERA - this.MIN_ESPERA + 1) + this.MIN_ESPERA);
            Thread.sleep((long)sleep_duration);
            this.tienda.salerepara();
        }
        catch(InterruptedException e){
            System.out.println("Excepcion al reparar: " + Thread.currentThread() + "\n" + e);
        }

        return new Generador.ReturnValue(sleep_duration, false);
    }
    


}
