/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio7;

import java.util.Queue;

/**
 *
 * @author galin
 */
public class Ferry {

    private int capacidadPasajeros;
    private int capacidadAutos;

    private int contPasajeros;
    private int contAutos;
    private int contPasajerosEs;
    private int contAutosEs;
    private Queue colaAutos;
    private Queue colaPasajeros;

    private boolean llegoDestino = false;
    private boolean iniciarViaje = false;

    public Ferry(int capacidadPasajeros, int capacidadAutos) {
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadAutos = capacidadAutos;
        this.contPasajeros = capacidadPasajeros;
        this.contAutos = capacidadAutos;
    }

    public synchronized void subirPasajero() throws InterruptedException {
        colaPasajeros.add(Thread.currentThread().getName());
        contPasajerosEs++;
        while (contPasajeros <= 0 || iniciarViaje || (!colaPasajeros.peek().equals(Thread.currentThread().getName()))) {
            this.notify();
            this.wait();

        }
        System.out.println("El pasajero " + Thread.currentThread().getName() + " subio al ferry");
        colaPasajeros.remove();
        contPasajeros--;
        contPasajerosEs--;
        if (contPasajeros == 0) {
            this.notifyAll();//como solo esta el hilo del control esperando lo notificamos
        }
    }

    public synchronized void subirAuto(int espacio) throws InterruptedException {
        contAutosEs++;
        while ((contAutos) <= 0 || iniciarViaje) {
            this.wait();
        }
        System.out.println("El auto " + Thread.currentThread().getName() + " subio al ferry");
        contAutos -= espacio;
        contAutosEs--;
        if (contAutos == 0) {
            this.notifyAll();
        }
    }

    public synchronized void iniciarViaje() throws InterruptedException {
        /* while ((contPasajeros > 0 && contAutos == 0) || (contPasajeros == 0 && contAutos > 0) || (contPasajeros > 0 && contAutos > 0)) {
            this.wait();
        }*/
        while (contPasajeros > 0 || contAutos > 0) {
            this.wait();
        }
        iniciarViaje = true;
        System.out.println("Inicio el viaje");
    }

    public synchronized void finalizarViaje() throws InterruptedException {
        System.out.println("Finalizo el viaje");
        llegoDestino = true;
        this.notifyAll();
    }

    public synchronized void bajarPasajero() throws InterruptedException {
        while (!llegoDestino) {
            this.wait();
        }
        System.out.println("El pasajero " + Thread.currentThread().getName() + " bajo del ferry");
        contPasajeros++;
        if (contPasajeros == this.capacidadPasajeros && contAutos == this.capacidadAutos) {
            llegoDestino = false;
            iniciarViaje = false;
            System.out.println("Ferry VACIO");
        }
    }

    public synchronized void bajarAuto(int espacio) throws InterruptedException {
        while (!llegoDestino) {
            this.wait();
        }
        System.out.println("El auto " + Thread.currentThread().getName() + " bajo del ferry");
        contAutos += espacio;
        if (contPasajeros == this.capacidadPasajeros && contAutos == this.capacidadAutos) {
            llegoDestino = false;
            iniciarViaje = false;
            System.out.println("Ferry VACIO");
        }
    }
}
