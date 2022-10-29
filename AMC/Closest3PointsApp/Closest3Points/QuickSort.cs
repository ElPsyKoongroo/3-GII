namespace Closest3Points;

public class QuickSort {
    public static void Ordena(Punto[] puntos, int primero, int ultimo) {
        Punto pivote;
        int posicion;
        
        if (primero < ultimo) {
            pivote = puntos[ultimo];
            posicion = partition(puntos, primero, ultimo, pivote);
            Ordena(puntos, primero, posicion -1);
            Ordena(puntos, posicion + 1, ultimo);
        }
    }
    
    private static int partition(Punto[] puntos, int primero, int ultimo, Punto pivote){
        int i = primero;
        int j = primero;
        
        while (i <= ultimo) {
            if (puntos[i].x > pivote.x)
                ++i;
            else {
                intercambia(puntos, i, j);
                i++;
                j++;
            }
        }
        return j-1;
    }
    private static void intercambia(Punto[] puntos, int i, int j){
        (puntos[i], puntos[j]) = (puntos[j], puntos[i]);
    }
}