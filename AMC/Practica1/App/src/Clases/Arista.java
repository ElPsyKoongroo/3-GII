package Clases;

public class Arista {
    private Punto punto_inicio;
    private Punto punto_fin;
    private int coste;

    public Arista(Punto _punto_inicio, Punto _punto_final){
        this.punto_inicio = _punto_inicio;
        this.punto_fin = _punto_final;
        this.coste = ((int)(this.punto_inicio.Distancia(this.punto_fin) * 100) % 100) + 1;
    }

    public int getCoste(){return this.coste;}
    public Punto getPuntoInicio(){return this.punto_inicio;}
    public Punto getPuntoFin(){return this.punto_fin;}
}
