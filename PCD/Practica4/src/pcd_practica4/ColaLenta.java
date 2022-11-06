/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_practica4;

import static pcd_practica4.Constants.MAX_TRIES;

/**
 *
 * @author Sergio
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

        int tries = 0;
        while (colallena() && tries < MAX_TRIES) {
            wait();
            tries++;
        }

        if (tries >= MAX_TRIES) {
            throw new Exception("Intentos agotados");
        }
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
        while (colavacia()) {
            Thread.sleep(10);
        }

        numelementos--;
        int actualHead = head;
        head = (head + 1) % capacidad;
        canvas.representa(datos, head, tail, numelementos);
        if (colavacia()) {
            canvas.avisa("COLA VACIA");
        }
        notifyAll();
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
