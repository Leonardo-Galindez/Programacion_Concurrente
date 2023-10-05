/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Taxi {

    Semaphore destino = new Semaphore(0);//sirve para notificar que se llego al destino
    Semaphore taxi = new Semaphore(1);//sirve para que el cliente solicite el taxi
    Semaphore taxista = new Semaphore(0);//sirve para indicar si el taxista esta durmiendo

    //metodos para pasajero
    public void tomarTaxi() {
        try {
            //el pasajero toma el taxi , no lo puede adquirir si el taxista esta durmiendo
            taxi.acquire();
            System.out.println("El pasajero:" + Thread.currentThread().getName() + " toma el taxi");
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitarTaxi() {
        //despertamos al taxista
        System.out.println("El pasajero:" + Thread.currentThread().getName() + " despierta al taxista");
        taxista.release();
    }

    public void liberarTaxi() {
        try {
            destino.acquire();
            System.out.println("El pasajero:" + Thread.currentThread().getName() + " libera el taxi");
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodos para taxista
    public void esperarProximoPasajero() {
        try {
            taxista.acquire();//el taxista espera al proximo pasajero durmiendo
            System.out.println("El taxista espera al proximo cliente durmiendo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarViaje() {
        destino.release();
        System.out.println("Llego a destino");
        taxi.release();
    }
}
