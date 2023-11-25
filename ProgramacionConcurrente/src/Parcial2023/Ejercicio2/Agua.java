package Parcial2023.Ejercicio2;

import java.util.concurrent.Semaphore;

public class Agua {

    private Semaphore hidrogeno;
    private Semaphore oxigeno;
    private Semaphore mutex;
    private Semaphore mutexAgua;
    private int contHidrogeno;
    private int contAguas;
    private int limiteRecipiente;
    private boolean oListo = false;
    private boolean hListo = false;
    private boolean haciendoAgua = false;

    public Agua(int limite) {
        this.hidrogeno = new Semaphore(2);
        this.oxigeno = new Semaphore(1);
        this.mutex = new Semaphore(1);
        this.mutexAgua = new Semaphore(1);
        this.contHidrogeno = 0;
        this.limiteRecipiente = limite;
        this.contAguas = 0;
    }

    public void hListo() throws InterruptedException {
        hidrogeno.acquire();
        System.out.println("El " + Thread.currentThread().getName() + " esta listo");
        mutex.acquire();
        contHidrogeno++;
        if (contHidrogeno == 2) {
            hListo = true;
        }
        if (hListo && oListo && !haciendoAgua) {
            System.out.println("El " + Thread.currentThread().getName() + " mando hacer agua");
            haciendoAgua = true;
            this.hacerAgua();
        }
        mutex.release();
    }

    public void oListo() throws InterruptedException {
        oxigeno.acquire();
        System.out.println("El " + Thread.currentThread().getName() + " esta listo");
        mutex.acquire();
        oListo = true;
        if (oListo && hListo && !haciendoAgua) {
            System.out.println("El " + Thread.currentThread().getName() + " mando hacer agua");
            haciendoAgua = true;
            this.hacerAgua();
        }
        mutex.release();
    }

    private void hacerAgua() throws InterruptedException {
        mutexAgua.acquire();
        System.out.println("SE CREO AGUA !!!");
        contAguas++;
        if (contAguas == limiteRecipiente) {
            System.out.println("EL AGUA ESTA LISTA PARA ENVASARLA !!!");
            contAguas = 0;
        }
        haciendoAgua = false;
        oListo = false;
        hListo = false;
        contHidrogeno = 0;
        oxigeno.release();
        hidrogeno.release(2);
        mutexAgua.release();
    }

}
