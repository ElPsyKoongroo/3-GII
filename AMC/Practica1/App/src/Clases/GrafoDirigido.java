package Clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Clases.Arista;
import Clases.Punto;

public class GrafoDirigido {
	private ArrayList<Punto> puntos;
	private ArrayList<Arista> aristas;
	private ArrayList<ArrayList<Punto>> adyacencia;

	public GrafoDirigido(ArrayList<Punto> _puntos, ArrayList<Arista> _aristas){
		this.puntos = _puntos;
		this.aristas = _aristas;
		this.adyacencia = new ArrayList<>();
		this.generaAdyacentes();
	}

    public void generaAdyacentes(){
        
        for(int i = 0; i<this.puntos.size(); i++){
            this.adyacencia.add(new ArrayList<>());
        }
        
        for(int i = 0; i<this.aristas.size(); i++){
            this.adyacencia.get(this.getIndexOf(this.aristas.get(i).getPuntoInicio())).add(this.aristas.get(i).getPuntoFin());
        }
    }

	public int getIndexOf(Punto p){
		return this.puntos.indexOf(p);
	}

	public Punto getPuntoAt(int index){
		return this.puntos.get(index);
	}

	public int size(){
		return this.puntos.size();
	}

	public HashSet<Punto> puntos(){
		HashSet<Punto> puntos = new HashSet<>();

		for(Punto p : this.puntos){
			puntos.add(p);
		}
		return puntos;
	}

	public HashSet<Arista> aristas(){
		HashSet<Arista> aristas = new HashSet<>();

		for(Arista a: this.aristas){
			aristas.add(a);
		}
		return aristas;
	}

	public Set<Punto> adyacentes(int index){
		Set<Punto> conjunto = new HashSet<>();
		 
		for(Punto p: this.adyacencia.get(index)){
			conjunto.add(p);
		}
		return conjunto;
	}
        
	public ArrayList<Integer> adyacentesIndices(int index){
		ArrayList<Integer> ad = new ArrayList<>();
		 
		for(Punto p: this.adyacencia.get(index)){
			ad.add(this.getIndexOf(p));
		}
		return ad;
	}

	public Set<Arista> salientes(int index){
		Set<Arista> salientes = new HashSet<>();	
		Punto p = getPuntoAt(index);
		for( Arista a : this.aristas ){
			if (a.getPuntoInicio().equals(p)) salientes.add(a);
		}	
		return salientes;
	}
}
