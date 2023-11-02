/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio4;

import Estructuras.Cola;

/**
 *
 * @author galin
 */
public class Buffer {

    private int capacidad;
    private int cantProductos;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.cantProductos = 0;
    }

    //El Productor pone un producto en el almacen
    public synchronized void ponerProducto() throws InterruptedException {
        while (cantProductos >= capacidad) {
            //ponemos en espera al Productor
            this.wait();
        }
        cantProductos++;
        System.out.println("Productor " + Thread.currentThread().getName() + " pone un producto");
        this.notifyAll();
    }

    //El consumidor saca un producto del almacen
    public synchronized void sacarProducto() throws InterruptedException {
        while (cantProductos == 0) {
            this.wait();
        }
        cantProductos--;
        System.out.println("Consumidor " + Thread.currentThread().getName() + " consume un producto");
        //El consumidor le notifica al producto que hay lugar
        this.notifyAll();
        //cuando termina el metodo sincronizado libera el log
    }
}
