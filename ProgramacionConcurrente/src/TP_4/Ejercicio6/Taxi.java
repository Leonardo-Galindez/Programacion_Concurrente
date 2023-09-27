/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Taxi {

    Semaphore destino = new Semaphore(0);//sirve para notificar que se llego al destino
    Semaphore taxi = new Semaphore(1);//sirve para que el cliente solicite el taxi
    Semaphore taxista = new Semaphore(0);//sirve para indicar si el taxista esta durmiendo

    //metodo para pasajero
    public void tomarTaxi() throws InterruptedException {
        //el pasajero toma el taxi
        taxi.acquire();
    }

    public void solicitarTaxi() {
        //despertamos al taxista
        taxista.release();
    }
}
