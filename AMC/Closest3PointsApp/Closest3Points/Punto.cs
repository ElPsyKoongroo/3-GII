namespace Closest3Points;

public class Punto
{
    public double x, y;
    public Punto(double X, double Y)
    {
        x = X;
        y = Y;
    }
    
    public double Distancia(Punto a) {
        return Math.Sqrt(Math.Pow(a.x - this.x, 2) + Math.Pow(a.y - this.y, 2));
    }
    public double Distancia3(Punto a, Punto b) {
        return this.Distancia(a) + a.Distancia(b);
    }

    public override string ToString()
    {
        return $"X: {x}, Y:{y}";
    }
}