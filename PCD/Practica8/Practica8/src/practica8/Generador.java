/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica8;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.awt.Frame;

/**
 *
 * @author ElPsy
 */
public class Generador {

    public static class ReturnValue
    {
        private double time;
        private boolean sell;

        public ReturnValue(double _time, boolean _sell)
        {
            sell = _sell;
            time = _time;
        }
        public double GetTime() {return time;}
        public boolean IsASell(){return sell;}
    }
    
    private static final int nThread = 10;
    private static final int nClientes = 50;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        final int size = 800;
        Random random = new Random();
        
        ExecutorService ThreadPoolCompradores = Executors.newFixedThreadPool(nThread);
        ExecutorService ThreadPoolReparadores = Executors.newFixedThreadPool(nThread);

        ArrayList<Future<ReturnValue>> futures = new ArrayList<Future<ReturnValue>>();

        Frame frame = new Frame();
        frame.setSize(size, size);
        
        
        CanvasTienda canvas = new CanvasTienda(size, size);
        frame.add(canvas);
        frame.setVisible(true); 
        
        
        Tienda tienda = new Tienda(canvas);
        
        for(int i = 0; i < nClientes; ++i){
            int probabilidad = random.nextInt(2);
            if(probabilidad == 0){
                Comprador c = new Comprador(tienda);
                futures.add(ThreadPoolCompradores.submit(c));
            }
            else{
                Reparador c = new Reparador(tienda);
                futures.add(ThreadPoolReparadores.submit(c));
            }
            Thread.sleep(500);
        }

        double timeSelling = 0;
        double timeRepairing = 0;
        for (Future<ReturnValue> item : futures)
        {
            ReturnValue value = item.get();
            if(value.IsASell())
            {
                timeSelling += value.GetTime();
            }
            else
            {
                timeRepairing += value.GetTime();
            }
        }

        double totalTime = timeRepairing + timeSelling;
        
        System.out.println("Total Time: " + totalTime);
        System.out.println("\tSelling Time: " + timeSelling);
        System.out.println("\tRepairing Time: " + timeRepairing);
        
        frame.dispose();
        System.exit(0);
    }
    
}
