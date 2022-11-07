/**
* @author ElPsy
 */
import java.util.*;


public class Cola implements ICola
{
    private Object[] datos;
    private int head;
    private int tail;
    private int capacidad;
    private int numelementos;

    public Cola(int capacidad) {

        datos = new Object[capacidad];
        head = 0;
        tail = 0;
        numelementos = 0;
        this.capacidad = capacidad;
    }

    @Override
    public int GetNum() { return numelementos; }

    @Override
    public void Acola(Object elemento) throws Exception {

        if(colallena()) throw new Exception("La cola está llena");

        datos[(tail++) % capacidad] = elemento;
        numelementos++;

    }

    @Override
    public Object Desacola() throws Exception {

        if(colavacia()) throw new Exception("La cola está vacia");

        numelementos--;
        return datos[(head++) % capacidad];

    }

    @Override
    public Object Primero() throws Exception{

        if (colavacia()) throw new Exception("La cola está vacia");

        return datos[head];
    }

    private boolean colavacia() { return numelementos == 0; }

    private boolean colallena() { return numelementos == capacidad; }

}
