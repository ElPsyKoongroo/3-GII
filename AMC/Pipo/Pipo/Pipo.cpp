#include "algoritmo.cpp"
#include <fstream>
#define SIZE 20'000
#define MEDIA 50

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

    bool incremeta()
    {
        



    }
};


int main()
{
    constexpr int size = 6;
    // int num = 10; // Combinaciones
    // while(num--){

    // }

















    int myints[size];
    std::fstream file("archivito.txt" , std::ios::trunc | std::ios::in | std::ios::out);

    for (int i = 1; i <= size; i++) {
        for (int j = 1; j <= size; j++) {
            if (j == i)
                continue;

            for (int k = 1; k <= size ; k++) {
                if (i == k || j == k)
                    continue;
                file << i << " " << j << " " << k << "\n";
            }   
        }
    }
    file.close();
    //int myints[] = {1,2,3,4,5};
    //std::sort (myints,myints+size-1);

    // std::cout << "The 3! possible permutations with 3 elements:\n";

    // auto start = std::chrono::high_resolution_clock::now();

    // do {
    //     std::cout << myints[0] << ' ' << myints[1] << ' ' << myints[2] << '\n';
    // } while ( std::next_permutation(myints,myints+size) );


    // auto finish = std::chrono::high_resolution_clock::now();

    // auto time = std::chrono::duration_cast<std::chrono::milliseconds>(finish-start).count();

    // std::cout << time << "ns\n";

    std::cout << "After loop: " << myints[0] << ' ' << myints[1] << ' ' << myints[2] << '\n';
    return 0;



    // std::cout << "Hello World!\n";
    // srand(time(NULL));

    // uint64_t med = 0;
    // for(int i = 0 ; i < MEDIA ; ++i){
    //     Punto* p = Algoritmo::GeneraPuntos(SIZE, 0, 100);

    //     auto start = std::chrono::high_resolution_clock::now();

    //     Punto* mejores = Algoritmo::DivideVenceras(p);

    //     auto finish = std::chrono::high_resolution_clock::now();

    //     auto time = std::chrono::duration_cast<std::chrono::milliseconds>(finish-start).count();

    //     med += time;
    //     delete p;

    // }

    // med/=MEDIA;

    // std::cout << med << "ms\n";

}
