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
    private boolean viajando = false;

    public Ferry(int capacidadPasajeros, int capacidadAutos) {
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadAutos = capacidadAutos;
    }

    //metodos Pasajeros
    public synchronized void ingresarPasajero() throws InterruptedException {
        while (contPasajeros >= capacidadPasajeros || viajando) {
            System.out.println("El pasajero " + Thread.currentThread().getName() + " esta esperando en el puerto");
            this.wait();
        }
        System.out.println("El pasajero " + Thread.currentThread().getName() + " subio al ferry");
        contPasajeros++;
        if (contPasajeros >= capacidadPasajeros) {
            this.notifyAll();
        }
    }

    public synchronized void salirPasajero() throws InterruptedException {
        while (!bajarPasajero || viajando) {
            this.wait();
        }
        System.out.println("El pasajero " + Thread.currentThread().getName() + " bajo del ferry");
        contPasajeros--;
    }

    //Metodos Autos
    public synchronized void ingresarAuto(int espacio) throws InterruptedException {
        //ingresar cola
        while (contAutos + espacio >= capacidadAutos || viajando) {
            System.out.println("El pasajero " + Thread.currentThread().getName() + " esta esperando en el puerto");
            this.wait();
        }
        //salir cola
        System.out.println("El auto " + Thread.currentThread().getName() + " subio al ferry");
        contAutos += espacio;
        if (contAutos >= capacidadPasajeros) {
            this.notifyAll();
        }
    }

    public synchronized void salirAuto(int espacio) throws InterruptedException {
        while (!bajarAuto) {
            this.wait();
        }
        System.out.println("El auto" + Thread.currentThread().getName() + " salio del ferry");
        contAutos -= espacio;
    }

    //Metodos ControlFerry
    public synchronized void iniciarViaje() throws InterruptedException {
        //se llena capacidad de pasajeros y autos
        System.out.println(contPasajeros);
        System.out.println(contAutos);
        while (contPasajeros <= capacidadPasajeros && contAutos <= capacidadAutos) {
            this.wait();
            //this.wait(tiempo);->se bloques en el conjunto y se desbloquea solo
            //iniciar=true para que iniciar auque no este lleno
        }
        System.out.println("Inicio el viaje");
        viajando = true;
    }

    public synchronized void finalizarViaje() throws InterruptedException {
        System.out.println("Finalizo el viaje");
        bajarAuto = true;
        bajarPasajero = true;
        viajando = false;
        this.notifyAll();
    }
}
