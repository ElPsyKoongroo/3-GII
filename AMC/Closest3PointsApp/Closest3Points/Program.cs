using System.Diagnostics;

namespace Closest3Points;

public class Program
{
    public static void Main(string[] args)
    {
        int numPuntos = 2_000;
        //if (args.Length != 1) numPuntos = int.Parse(args[1]);

        Punto[] puntos = Algoritmo.GeneraPuntos(numPuntos, 0, 100);
        
        Stopwatch sp = Stopwatch.StartNew();
        
        Algoritmo.DivideVenceras(puntos);
        
        sp.Stop();

        Console.WriteLine($"Tiempo: {sp.ElapsedMilliseconds/1000.0}s");

        foreach (var x in Algoritmo.mejoresPuntos)
        {
            Console.WriteLine(x);
        }
    }
}