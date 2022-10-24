#include "algoritmo.cpp"
#define SIZE 20'000




int main()
{
    std::cout << "Hello World!\n";
    srand(time(NULL));
    Punto* p = Algoritmo::GeneraPuntos(SIZE, 0, 100);

    auto start = std::chrono::high_resolution_clock::now();


    Punto* mejores = Algoritmo::DivideVenceras(p);

    auto finish = std::chrono::high_resolution_clock::now();

    auto time = std::chrono::duration_cast<std::chrono::milliseconds>(finish-start).count();




    for (int i = 0; i < 3; ++i)
    {
        std::cout << "x:" << mejores[i].x << ", y:" << mejores[i].y << std::endl;
    }

    std::cout << time << "ms\n";
    delete p;
}
