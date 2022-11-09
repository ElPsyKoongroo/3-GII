package Algoritmos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import Clases.Arista;
import Clases.GrafoDirigido;
import Clases.Punto;

public class Dijkstra {
	private GrafoDirigido grafo;	

	public Dijkstra(ArrayList<Arista> _aristas, ArrayList<Punto> _puntos){
		this.grafo = new GrafoDirigido(_puntos, _aristas);
	}

	public int[] Calcula(){
		Set<Arista> salientes = this.grafo.salientes(0);
		if (salientes.isEmpty()) return null;

		Set<Integer> nodosPorRecorrer = new HashSet<>();
		int distancias[] = new int[grafo.size()];
		int distanciaRecorrida = 0;
		int nodoActual = 0;
		int distanciaMin;
		
		for(int i = 0; i < distancias.length; i++){
			nodosPorRecorrer.add(i);
			distancias[i] = Integer.MAX_VALUE;
		}
		distancias[0] = 0;

		while(!nodosPorRecorrer.isEmpty()){
			nodosPorRecorrer.remove(nodoActual);

			for (Arista a : salientes){
				int i = grafo.getIndexOf(a.getPuntoInicio());
				if (distancias[i] > (a.getCoste()+distanciaRecorrida)) distancias[i] = a.getCoste()+distanciaRecorrida;
			}

			distanciaMin = Integer.MAX_VALUE;
			for (int i = 0; i < distancias.length; i++) {
				if (nodosPorRecorrer.contains(i) && distanciaMin > distancias[i]) {
					distanciaMin = distancias[i];
					nodoActual = i;
					distanciaRecorrida = distancias[i];
				}
			}
			
			salientes = grafo.salientes(nodoActual);
		}
		return distancias;
	}
}
