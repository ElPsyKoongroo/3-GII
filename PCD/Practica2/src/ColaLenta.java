/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
* @author ElPsy
 */
public class ColaLenta implements ICola {

    private Object[] datos;
    private int head;
    private int tail;
    private int capacidad;
    private int numelementos;

    public ColaLenta(int capacidad) {

        datos = new Object[capacidad];
        head = 0;
        tail = 0;
        numelementos = 0;
        this.capacidad = capacidad;
    }

    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public void Acola(Object elemento) throws Exception {

        if (colallena()) {
            throw new Exception("La cola está llena");
        }
        Thread.sleep(100);
        datos[(tail++) % capacidad] = elemento;
        Thread.sleep(100);
        numelementos++;
    }

    @Override
    public Object Desacola() throws Exception {

        if (colavacia()) {
            throw new Exception("La cola está vacia");
        }
        Thread.sleep(100);
        numelementos--;

        Thread.sleep(100);
        return datos[(head++) % capacidad];

    }

    @Override
    public Object Primero() throws Exception {

        if (colavacia()) {
            throw new Exception("La cola está vacia");
        }

        return datos[head];
    }

    private boolean colavacia() {
        return numelementos == 0;
    }

    private boolean colallena() {
        return numelementos == capacidad;
    }
}
