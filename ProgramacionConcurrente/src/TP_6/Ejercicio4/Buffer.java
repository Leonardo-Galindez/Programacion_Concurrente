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
    private Cola almacen;
    private int cantProductos;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.cantProductos = 0;
        this.almacen = new Cola();
    }

    //El Productor pone un producto en el almacen
    public synchronized void ponerProducto() throws InterruptedException {     
        while (cantProductos >= capacidad) {
            //ponemos en espera al Productor
            this.wait();
        }
        cantProductos++;
        Producto unProducto = new Producto();
        almacen.poner(unProducto);
        System.out.println("Productor " + Thread.currentThread().getName() + " pone un producto" + unProducto.getId());
    }

    //El consumidor saca un producto del almacen
    public synchronized void sacarProducto() throws InterruptedException {   
        while (cantProductos <= 0) {
            this.wait();
        }
        cantProductos--;
        Producto unProducto = (Producto) almacen.obtenerFrente();
        almacen.sacar();
        System.out.println("Consumidor " + Thread.currentThread().getName() + " consume un producto" + unProducto.getId());    
        //El consumidor le notifica al producto que hay lugar
        this.notify();

    }

}
