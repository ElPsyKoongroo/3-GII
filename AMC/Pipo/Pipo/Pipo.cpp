#include "algoritmo.cpp"
#define SIZE 20'000
#define MEDIA 50



int main()
{
    std::cout << "Hello World!\n";
    srand(time(NULL));

    uint64_t med = 0;
    for(int i = 0 ; i < MEDIA ; ++i){
        Punto* p = Algoritmo::GeneraPuntos(SIZE, 0, 100);

        auto start = std::chrono::high_resolution_clock::now();

        Punto* mejores = Algoritmo::DivideVenceras(p);

        auto finish = std::chrono::high_resolution_clock::now();

        auto time = std::chrono::duration_cast<std::chrono::milliseconds>(finish-start).count();

        med += time;
        delete p;

    }

    med/=MEDIA;

    std::cout << med << "ms\n";

}
