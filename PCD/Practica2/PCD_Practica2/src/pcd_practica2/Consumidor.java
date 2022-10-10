/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_practica2;

/**
 *
 * @author ElPsy
 */
public class Consumidor implements Runnable {
    ColaLenta colita;
    public Consumidor(ColaLenta c){
        this.colita = c;
    }
    
    public void Extraer() throws Exception{
        for(int i = 0; i<10; ++i){
            Object random = colita.Desacola();
            System.out.println(
                    "Numero extraido: " + random
                    + "\nId: " + Thread.currentThread().getId()
            );
        }
    }
    
    @Override
    public void run(){
        try{
            this.Extraer();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion: " + e);
        }
    }
}

