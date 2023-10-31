/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio7;

/**
 *
 * @author galin
 */
public class Ferry {

    private int capacidadPasajeros;
    private int capacidadAutos;
    private int contPasajeros = 0;
    private int contAutos = 0;
    private boolean bajarPasajero = false;
    private boolean bajarAuto = false;

    // CUANDO EL FERRY TERMINE RECORRIDO LIBERA A TODOS
    public Ferry(int capacidadPasajeros, int capacidadAutos) {
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadAutos = capacidadAutos;
    }

    //metodos Pasajeros
    public synchronized void ingresarPasajero() throws InterruptedException {
        while (contPasajeros >= capacidadPasajeros) {
            this.wait();
        }
        contPasajeros++;
        bajarPasajero = false;
    }

    public synchronized void salirPasajero() throws InterruptedException {
        while (!bajarPasajero) {
            this.wait();
        }
        contPasajeros--;
    }

    //Metodos Autos
    public synchronized void ingresarAuto() throws InterruptedException {
        while (contAutos >= capacidadAutos) {
            this.wait();
        }
        contAutos++;
        bajarAuto = false;
    }

    public synchronized void salirAuto() throws InterruptedException {
        while (!bajarAuto) {
            this.wait();
        }
        contAutos--;
    }
    //el ferry seria otro hilo ??? para tener el metodo fianlizar trayecto y iniciar trayecto
    //para que habilite a los pasajeros y autosd a bajar
}
