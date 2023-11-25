package Parcial2023.Ejercicio2;

import java.util.concurrent.Semaphore;

public class Agua {

    private Semaphore hidrogeno;
    private Semaphore oxigeno;
    private Semaphore mutex;
    private Semaphore agua;
    private Semaphore recipiente;

    private int contHidrogeno;

    private boolean oListo = false;
    private boolean hListo = false;
    private boolean haciendoAgua = false;

    public Agua() {

        this.hidrogeno = new Semaphore(2);
        this.oxigeno = new Semaphore(1);
        this.mutex = new Semaphore(1);
        this.agua = new Semaphore(0);
        

        this.contHidrogeno = 0;

    }

    public void hListo() throws InterruptedException {
        hidrogeno.acquire();
        System.out.println("El hidrogeno " + Thread.currentThread().getName() + " esta listo");
        mutex.acquire();
        contHidrogeno++;
        if (contHidrogeno == 2) {
            hListo = true;
        }

        if (hListo && oListo && !haciendoAgua) {
            haciendoAgua = true;
            agua.release();
        }
        mutex.release();
    }

    public void oListo() throws InterruptedException {
        oxigeno.acquire();
        System.out.println("El oxigeno " + Thread.currentThread().getName() + " esta listo");
        mutex.acquire();
        oListo = true;

        if (oListo && hListo && !haciendoAgua) {
            haciendoAgua = true;
            agua.release();
        }
        mutex.release();

    }

    public void hacerAgua() throws InterruptedException {

        agua.acquire();
        System.out.println("SE CREO AGUA !!!");

    }

}
