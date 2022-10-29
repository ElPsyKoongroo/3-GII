#include <ctime>
#include <iostream>
#include <limits>
#include <cmath>
#include <algorithm>
#include <cfloat>
#include <chrono>
struct Punto
{
public:
    double x, y;
};



class Algoritmo
{
    static const int START = 0;
    static const int END = 1;
    inline static int totalPuntos;

    static double Distancia(const Punto& a, const Punto& b) {
        return sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2));
    }

    static double Distancia3(const Punto& a, const Punto& b,const Punto& c) {
        return Distancia(a,b) + Distancia(b, c);
    }
    static double CalculaFixed(const Punto * const puntos, const int &start, int end)
    {
        double distanciaMinima = DBL_MAX;
        if (end - start + 1 < 3) return distanciaMinima;
        ++end;
        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                if (j == i)
                    continue;

                for (int k = start; k < end ; k++) {
                    if (i >= k || j == k)
                        continue;

                    double distancia = Distancia3(puntos[i], puntos[j], puntos[k]);

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

    static int* GetPointsBetween(const Punto* const puntos, double start, double end){
        int start_index = -1;
        while (puntos[++start_index].x < start) {};

        int end_index = start_index - 1;
        bool drc = false;
        while (puntos[++end_index].x <= end && (drc = end_index+1 < totalPuntos)) {};
        if (!drc) --end_index;
        // while (puntos.get(++end_index).x <= end && end_index+1 < puntos.size()) {};
        
        return new int[2]{start_index, end_index};
    }

    static bool PuntosRepetidos(const Punto* const puntos, const int& start, const int& end)
    {
        for (int i = start; i < end - 1; i++)
            if (puntos[i].x != puntos[i + 1].x) 
                return false;
        return true;
    }

    static double DivideVenceras(const Punto* puntos, double start, double end, int it) {
        
        double mitad = (start+end) / 2.0;
        int* indices = GetPointsBetween(puntos, start, end);

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
        
        
        int* new_indices = GetPointsBetween(puntos, mitad-mejorDistancia, mitad+mejorDistancia);
        if (distanciaMin < end - start){
            double aux = CalculaFixed(puntos, new_indices[START], new_indices[END]);
            return aux < distanciaMin ? aux : distanciaMin; 
        } 

        double aux = CalculaFixed(puntos, indices[START], indices[END]);
        return aux < distanciaMin ? aux : distanciaMin;  
    }

public:
    inline static Punto mejoresPuntos[3];
    inline static double mejorDistancia;
    static Punto* GeneraPuntos(const int& numPuntos, const int& maximo, const int& minimo)
    {
        //rand()
        Punto* puntos = new Punto[numPuntos];
        totalPuntos = numPuntos;

        if (puntos == nullptr)
        {
            std::cout << "Error pocho";
        }

        for (int i = 0 ; i < numPuntos; ++i)
        {
            puntos[i].x = (rand() % ((maximo + minimo) * 100)) / 100.0;
            puntos[i].y = (rand() % ((maximo + minimo) * 100)) / 100.0;
        }

        std::sort(puntos, puntos+numPuntos, [](Punto a, Punto b) {return a.x < b.x;});

        return puntos;
    }

    static Punto* DivideVenceras(const Punto* const puntos) {
        mejorDistancia = DBL_MAX;
        DivideVenceras(puntos, puntos[0].x, puntos[totalPuntos - 1].x, 0);
        return mejoresPuntos;
    }


};