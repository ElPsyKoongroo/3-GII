package Clases;

public interface Proceso {
    boolean esFinal(int estado);
    boolean Match(String cadena);
    // Comentamos esto porque la clase Object de Java ya tiene el toString, asi que para que lo quiero aqui ?
    // Hay que ser coherentes
    //String ToString();
}
