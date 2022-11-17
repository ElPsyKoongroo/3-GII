/**
*
* @author ElPsy
*/
package Algoritmos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import Clases.Arista;
import Clases.ExtraCanvas;
import Clases.GrafoDirigido;
import Clases.Punto;
import java.util.PriorityQueue;

public class DijkstraVisual {

    private GrafoDirigido grafo;
    private ExtraCanvas canvitas;
    public ArrayList<Double> dist;
    public ArrayList<Integer> prev;
    

    public DijkstraVisual(ArrayList<Arista> _aristas, ArrayList<Punto> _puntos, ExtraCanvas _canvitas) {
        this.grafo = new GrafoDirigido(_puntos, _aristas);
        this.canvitas = _canvitas;
        this.dist = new ArrayList<>();
        this.prev = new ArrayList<>();
        this.canvitas.addPuntos(_puntos);
    }

    

    public void CalculaBien() {

        Queue<Integer> porRecorrer = new PriorityQueue<Integer>();
        for (int i = 0; i < grafo.size(); i++) {
            dist.add(Double.MAX_VALUE);
            prev.add(-1);
            porRecorrer.add(i);
        }
        dist.set(0, 0.0);

        while (!porRecorrer.isEmpty()) {
            int u = Minimo(dist, porRecorrer);
            porRecorrer.remove(u);

            for (int i : grafo.adyacentesIndices(u)) {
                if (!porRecorrer.contains(i)) {
                    continue;
                }
                
                Arista aux = new Arista(grafo.getPuntoAt(u), grafo.getPuntoAt(i));
                
                
                this.canvitas.dijstra_step_by_step(aux);
                try{
                    Thread.sleep(100);
                } catch (Exception esto_va_a_ser_ignorado_muy_fuertemente){}

                double alt = dist.get(u) + aux.getCoste();

                if (alt < dist.get(i)) {
                    dist.set(i, alt);
                    prev.set(i, u);
                    this.canvitas.addPrevs(this.prev);
                }
            }
        }
    }

    private int Minimo(ArrayList<Double> lista, Queue<Integer> cola) {
        double minimo = Double.MAX_VALUE;
        int minimoInt = -1;
        for (int i = 0; i < lista.size(); i++) {

            if (cola.contains(i) && lista.get(i) < minimo) {
                minimo = lista.get(i);
                minimoInt = i;
            }
        }
        return minimoInt;
    }
}
