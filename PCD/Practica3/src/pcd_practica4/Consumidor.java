/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_practica4;

/**
 *
* @author ElPsy
 */
public class Consumidor extends Thread {
    ColaLenta lacola;
    public Consumidor(ColaLenta c){
        this.lacola = c;
    }
    
    public void Consumir() throws Exception{
        java.util.Random rand = new java.util.Random();
        for(int i = 0; i<100; ++i){
            System.out.println("Desacola");
            Object random = lacola.Desacola();
            Thread.sleep(rand.nextInt(100, 1000));
        }
    }
    
    @Override
    public void run(){
        try{
            this.Consumir();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion: " + e);
        }
    }
}

