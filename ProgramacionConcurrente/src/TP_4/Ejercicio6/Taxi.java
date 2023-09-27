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

    //metodos para pasajero
    public void solicitarTaxi() {
        //despertamos al taxista
        taxista.release();
        System.out.println("El cliente despierta al taxista");
    }

    public void tomarTaxi() throws InterruptedException {
        //el pasajero toma el taxi
        taxi.acquire();
        System.out.println("El pasajero tomar el taxi");
    }

    public void liberarTaxi() throws InterruptedException {
        destino.acquire();
        taxi.release();//se libera taxi
        System.out.println("El pasajero libera el taxi");
    }

    //metodos para taxista
    public void esperarProximoPasajero() throws InterruptedException {
        taxista.acquire();//el taxista espera al proximo pasajero
        System.out.println("El taxista taxista espera al proximo cliente");
    }

    public void avisarDeLlegadaADestino() {
        destino.release();
        System.out.println("El le avisa al pasajero que se llego al destino");
    }

}
