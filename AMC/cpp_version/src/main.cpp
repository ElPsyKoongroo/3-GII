#include "algoritmo.cpp"
#include <fstream>
#define SIZE 20'000
#define MEDIA 10 

class index{
public:

    int i,j,k;              // indexes
    uint64_t max_comb;      // all perms with symmetry
    uint64_t current;
    uint64_t size;          // cantidad de elementos y bloques
    uint64_t block;         // Block SIZE
    uint64_t subblock;      // Subblock SIZE

    index(int start, int end) // n²+3n+2 para n -> size - 3
    { 
        block = max_comb/size;
        size = end - start + 1;
        subblock = block/(size-1);
        max_comb = (size-3)*(size-3) + 3*(size-3) + 2;
        i = start;
        j = start+1;
        k = start+2;

    }
};


int main() {
    
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
