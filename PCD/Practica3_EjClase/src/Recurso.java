/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ElPsy
 */
public class Recurso {
    private int[] contadores = {0,0};
    private MiCanvas cv;
    
    public Recurso(MiCanvas caravana){
        this.cv = caravana;
    }
    
    public synchronized void incrementa(int posicion){
        contadores[posicion]++;
        cv.representa(this.contadores);
    }
    
    public int[] getContadores(){
        return contadores;
    }
}
