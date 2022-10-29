using System.Linq;

namespace Closest3Points;

public static class Algoritmo
{
    private const int START = 0;
    private const int END = 1;
    public static Punto[] mejoresPuntos; 
    private static double mejorDistancia;
    
    public static Punto[] GeneraPuntos(int numPuntos, double maximo, double minimo) {
        Random rand = new Random();

        Punto[] puntos = new Punto[numPuntos];

        for (int i = 0; i < numPuntos; i++) {
            double x = rand.NextDouble() * (maximo - minimo) + minimo;
            double y = rand.NextDouble() * (maximo - minimo) + minimo;

            puntos[i] = new Punto(x, y);
        }
        QuickSort.Ordena(puntos, 0, numPuntos-1);
        return puntos;
    }

    private static double CalculaFixed(IReadOnlyList<Punto> puntos, int start, int end) {
        double distanciaMinima = Double.MaxValue;
        
        if (end - start + 1 < 3) return distanciaMinima; 
        ++end;
        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                if (j == i)
                    continue;

                for (int k = start; k < end; k++) {
                    if (i == k || j == k)
                        continue;

                    double distancia = puntos[i].Distancia3(puntos[j], puntos[k]);

                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        if (distanciaMinima < mejorDistancia) {
                            mejorDistancia = distanciaMinima;
                            
                            mejoresPuntos[0] = puntos[i];
                            mejoresPuntos[1] = puntos[j];
                            mejoresPuntos[2] = puntos[k];
                        }
                    }
                }
            }
        }
        return distanciaMinima;
    }
    
    private static int[] GetPointsBetween(IReadOnlyList<Punto> puntos, Double start, Double end) {
        int start_index = -1;
        while (puntos[++start_index].x < start) {};

        int end_index = start_index - 1;
        bool drc = false;
        while (puntos[++end_index].x <= end && (drc = end_index+1 < puntos.Count)) {};
        if (!drc) --end_index;

        return new int[] { start_index, end_index };
    }
    
    private static bool PuntosRepetidos(IReadOnlyList<Punto> puntos, int start, int end){
        for(int i = start; i<end-1; i++)
            if (puntos[i].x != puntos[i+1].x) return false;
        return true;
    }
    
    public static Punto[] DivideVenceras(IReadOnlyList<Punto> puntos) {
        mejorDistancia = Double.MaxValue;
        mejoresPuntos = new Punto[3];
        DivideVenceras(puntos, puntos[0].x, puntos[^1].x, 0);
        return mejoresPuntos;
    }
    private static double DivideVenceras(IReadOnlyList<Punto> puntos, double start, double end, int it) {
        
        double mitad = (start+end) / 2;
        int[] indices = GetPointsBetween(puntos, start, end);

        if (indices[END] - indices[START] + 1 < 3)
            return -1;
        
        if(PuntosRepetidos(puntos, indices[START], indices[END])) return -1;
        
        double izq = DivideVenceras(puntos, start, mitad, it + 1);
        double drc = DivideVenceras(puntos, mitad, end, it + 1);

        if ( izq == -1 && drc == -1) 
            return CalculaFixed(puntos, indices[START], indices[END]);
        

        double distanciaMin = izq;
        if (izq == -1) distanciaMin = drc;
        else if (drc != -1 && drc < izq ) distanciaMin = drc;
        
        
        int[] new_indices = GetPointsBetween(puntos, mitad-mejorDistancia, mitad+mejorDistancia);
        if (distanciaMin < end - start){
            double aux = CalculaFixed(puntos, new_indices[START], new_indices[END]);
            return aux < distanciaMin ? aux : distanciaMin; 
        } 

        double _aux = CalculaFixed(puntos, indices[START], indices[END]);
        return _aux < distanciaMin ? _aux : distanciaMin;
    }
}