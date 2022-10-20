#include "algoritmo.cpp"

#define SIZE 100

int main()
{
    std::cout << "Hello World!\n";
    srand(time(NULL));
    Punto* p = Algoritmo::GeneraPuntos(SIZE, 0, 100);

    Algoritmo::DivideVenceras(p);

    Punto* mejores = Algoritmo::mejoresPuntos;

    for (int i = 0; i < 3; ++i)
    {
        std::cout << "x:" << mejores[i].x << ", y:" << mejores[i].y << std::endl;
    }

    delete p;
}
