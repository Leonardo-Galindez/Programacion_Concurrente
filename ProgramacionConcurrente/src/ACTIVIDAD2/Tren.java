package ACTIVIDAD2;

import java.util.concurrent.Semaphore;

public class Tren {

    private int cantAsientos;
    private int ocupados;

    private Semaphore semAsiento;//se encarga de decir si el tren esta llego o no
    private Semaphore semViajar; // Tren viaja cuando ya se lleno
    private Semaphore subirTren; //sirve para permitir que cada pasajero suba al tren //prodria no ir????
    private Semaphore semBajar; //para saber si se puede bajar

    public Tren(int cantAsientos) {
        this.semAsiento = new Semaphore(1);
        this.semViajar = new Semaphore(0);
        this.semBajar = new Semaphore(0);
        this.subirTren = new Semaphore(1);
        this.cantAsientos = cantAsientos;
        this.ocupados = 0;
    }

    public void subirTren() {
        try {
            subirTren.acquire();
            if (cantAsientos > ocupados) {//llenamos los asientos
                //subirTren esta para que entren de a uno al tren
                System.out.println(Thread.currentThread().getName() + " subio al tren");
                ocupados++;
            } else {
                semAsiento.acquire();
                System.out.println(" TREN LLENO");
                semViajar.release();//liberamos el tren para viajar
                //si se llenan los asientos adquirimos el semaforo para decir que no pueden entrar mas 
            }
            subirTren.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void bajarTren() {
        try {
            if (ocupados != 0) {
                semBajar.acquire();
                System.out.println(Thread.currentThread().getName() + " SE BAJO  DEL TREN");
                ocupados--;
                semBajar.release();//el ultimo que baja libera semBajar
            } else {

                System.out.println(" TREN VACIO");
                semAsiento.release();//liberamos todos los asientos  
                subirTren.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ------------- ControlTren -------------
    /**
     * Comienza el viaje cuando ya se lleno el Tren de Pasajeros.
     */
    public void iniciarViaje() {
        try {
            semViajar.acquire();
            System.out.println(Thread.currentThread().getName() + " INICIO EL VIAJE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finaliza el viaje cuando ya se bajaron todos los Pasajeros.
     */
    public void finalizarViaje() {
        System.out.println(Thread.currentThread().getName() + " SE DETUVO EL TREN");
        semBajar.release();
    }
}
