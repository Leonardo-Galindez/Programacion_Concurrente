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
        System.out.println("");
        while (cantProductos == capacidad) {
            //ponemos en espera al Productor
            this.wait();
        }       
        cantProductos++;
        Producto unProducto = new Producto();
        almacen.poner(new Producto());
        System.out.println("Productor " + Thread.currentThread().getName() + " pone un producto");
    }

    //El consumidor saca un producto del almacen
    public synchronized void sacarProducto() throws InterruptedException {
        while (cantProductos == 0) {
            this.wait();
        } 
        cantProductos--;
        almacen.sacar();
        System.out.println("Consumidor " + Thread.currentThread().getName() + " consume un producto");
        //avisamos que hay un lugar habilitado
        this.notify();
    }

}
