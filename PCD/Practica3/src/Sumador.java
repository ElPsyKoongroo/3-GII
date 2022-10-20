/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ElPsy
 */
public class Sumador implements Runnable{
    
    private final int N_INCREMENTOS = 10_000;
    private Recurso r;
    private int posicion;
    public Sumador(Recurso r, int cual){
        this.r = r;
        this.posicion = cual;
    }
    
    @Override
    public void run() {
        for (int i=0; i < N_INCREMENTOS; i++){
            r.incrementa(this.posicion);
            try {
                Thread.sleep(0, 10);
            } catch (Exception ex) {}
        }
    }
    
}
