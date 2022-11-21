/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica8;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author ElPsy
 */
public class Generador {
    
    private static final int nThread = 10;
    private static final int nClientes = 50;
    
    public static void main(String[] args) throws InterruptedException {
        
        Random random = new Random();
        
        ExecutorService ThreadPoolCompradores = Executors.newFixedThreadPool(nThread);
        ExecutorService ThreadPoolReparadores = Executors.newFixedThreadPool(nThread);
        Future f[] = new Future[nClientes];
            
        
        for(int i = 0; i < nClientes; ++i){
              int probabilidad = random.nextInt(10);
              if(probabilidad < 5){
                  Comprador c = new Comprador();
                  ThreadPoolCompradores.submit(c);
              }
              else{
                  Reparador c = new Reparador();
                  ThreadPoolReparadores.submit(c);
              }
              Thread.sleep(500);
        }
        
        
        
        
        
    }
    
}
