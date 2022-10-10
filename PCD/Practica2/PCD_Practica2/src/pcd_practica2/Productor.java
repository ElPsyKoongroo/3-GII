/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_practica2;

/**
 *
 * @author ElPsy
 */
public class Productor extends Thread {
    ColaLenta colita;
    public Productor(ColaLenta c){
        this.colita = c;
    }
    
    public void Insertar() throws Exception{
        for(int i = 0; i<10; ++i){
            double random = Math.random();
            colita.Acola(random);
            
            System.out.println(
                    "\n\nNumero añadido: " + random
                    + "\nId: " + Thread.currentThread().getId()
                    + "\n\n"
            );
        }
    }
    
    @Override
    public void run(){
        
        System.out.println(
            "Id: " + Thread.currentThread().getId()
        );
        
        
        try{
            this.Insertar();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion: " + e);
        }
    }
}
