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
    private CanvasCola canvas;

    public ColaLenta(int capacidad, CanvasCola elcanvas) {

        datos = new Object[capacidad];
        head = 0;
        tail = 0;
        numelementos = 0;
        this.capacidad = capacidad;
        this.canvas = elcanvas;
    }

    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public synchronized void Acola(Object elemento) throws Exception {

        //if(colallena()) throw new Exception("La cola está llena");
        if (colallena()) {
            return;
        }
        Thread.sleep(100);
        datos[tail] = elemento;
        Thread.sleep(100);
        tail = (tail + 1) % capacidad;
        Thread.sleep(100);
        numelementos++;
        Thread.sleep(100);
        canvas.representa(datos, head, tail, numelementos);
        if (colallena()) {
            canvas.avisa("COLA LLENA");
        }
        return;
    }

    @Override
    public synchronized Object Desacola() throws Exception {

        //if(colavacia()) throw new Exception("La cola está vacia");
        if (colavacia()) {
            return new Object();
        }
        Thread.sleep(100);
        numelementos--;
        Thread.sleep(100);
        int actualHead = head;
        Thread.sleep(100);
        head = (head + 1) % capacidad;
        Thread.sleep(100);
        canvas.representa(datos, head, tail, numelementos);
        if (colavacia()) {
            canvas.avisa("COLA VACIA");
        }
        return datos[actualHead];

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
