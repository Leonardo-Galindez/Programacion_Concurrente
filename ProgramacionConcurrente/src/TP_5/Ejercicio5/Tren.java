/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio5;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Tren {

    private Semaphore capacidad;
    private Semaphore inicioViaje;
    private Semaphore finViaje;
    private Semaphore tickets;
    private int cont;

    public Tren() {
        this.capacidad = new Semaphore(5);//sirve para indicar capacidad de asientos 
        this.inicioViaje = new Semaphore(0);//sirve para indicar cuando este lleno el tren y iniciar el viaje
        this.finViaje = new Semaphore(0);//sirve para indicar cuando termina el viaje y pueden bajar todos lo pasajeros
        this.tickets = new Semaphore(20);//sirve para indicar la cantidad de tickets disponibles
        this.cont = 0;//atributo para saber cuando podemos liberar el viaje
    }

    //Metodos de Pasajero
    public void comprarTicket() {
        try {
            tickets.acquire();
            System.out.println(Thread.currentThread().getName() + " compro un ticket");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subirTren() {
        try {
            capacidad.acquire();
            if (cont < 5) {
                System.out.println(Thread.currentThread().getName() + " subio al tren");
                cont++;
                if (cont == 5) {
                    inicioViaje.release();//liberamos el viaje
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajarTren() {
        try {//esta mal aca ----
            finViaje.acquire();//el primer pasajero que se baja me bloquea
            if (cont != 0) {
                System.out.println(Thread.currentThread().getName() + " bajo del tren");
                cont--;
                if (cont == 0) {
                    capacidad.release(5);//libero todo de una para que no se suba nadie al tren hasta que se bajen todos
                } else {
                    finViaje.release();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodos de Gestor Tren
    public void iniciarViaje() {

        try {
            inicioViaje.acquire();
            System.out.println("Inicio el viaje");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarViaje() {
        System.out.println("El viaje Finalizo");
        finViaje.release();
    }

}
