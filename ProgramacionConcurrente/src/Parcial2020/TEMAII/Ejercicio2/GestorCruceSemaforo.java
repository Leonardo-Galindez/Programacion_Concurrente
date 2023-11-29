package Parcial2020.TEMAII.Ejercicio2;

import java.util.concurrent.Semaphore;

public class GestorCruceSemaforo {
    private Semaphore autosNorte;
    private Semaphore autosOeste;
    private Semaphore controlSemaforo;

    private boolean semaforoNorte = true;
    private boolean semaforoOester = false;

    public GestorCruceSemaforo() {
        this.autosNorte = new Semaphore(1);
        this.autosOeste = new Semaphore(0);
        this.controlSemaforo = new Semaphore(1);
    }

    public void llegaNorte() throws InterruptedException {
        autosNorte.acquire();
        System.out.println(Thread.currentThread().getName() + " esta cruzando NORTE--SUR+++++++++");
        autosNorte.release();
    }

    public void llegaOeste() throws InterruptedException {
        autosOeste.acquire();
        System.out.println(Thread.currentThread().getName() + " esta cruzando OESTE--ESTE+++++++++");
        autosOeste.release();
    }

    public void sale() {
        // System.out.println(Thread.currentThread().getName() + " salio del cruce");
    }

    public void cambiarSemaforos() throws InterruptedException {
        controlSemaforo.acquire();
        
        if (semaforoNorte) {
            semaforoNorte = false;
            semaforoOester = true;
            autosNorte.acquire();
            System.out.println("hola++++++++++++++++++++++++++++++++++++++++++++++++++");
            autosOeste.release();
        } else {
            semaforoNorte = true;
            semaforoOester = false;
            autosOeste.acquire();
            System.out.println("hola----------------------------------------------------");
            autosNorte.release();
        }
        System.out.println("CAMBIO SEMAFOROS-----------------------------------------------------------------------");
        controlSemaforo.release();
    }
}
