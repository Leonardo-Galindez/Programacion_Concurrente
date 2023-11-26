package Parcial2023.Ejercicio1Semaforos;

import java.util.concurrent.Semaphore;

public class Planta {

    private Semaphore cajaVino;
    private Semaphore cajaAgua;

    private Semaphore empaquetadorVino;
    private Semaphore empaquetadorAgua;
    private Semaphore transportador;

    private Semaphore mutexVino;
    private Semaphore mutexAgua;

    private int contVino = 0;
    private int contAgua = 0;

    public Planta() {

        this.cajaVino = new Semaphore(10);
        this.cajaAgua = new Semaphore(10);
        this.empaquetadorVino = new Semaphore(0);
        this.empaquetadorAgua = new Semaphore(0);
        this.transportador = new Semaphore(0);

        this.mutexVino = new Semaphore(1);
        this.mutexAgua = new Semaphore(1);

    }

    public void guardarVino() throws InterruptedException {
        cajaVino.acquire();
        System.out.println(Thread.currentThread().getName() + " guado un vino");
        mutexVino.acquire();
        contVino++;
        if (contVino == 10) {
            System.out.println("CAJA DE VINO LLENA !!!");
            empaquetadorVino.release();
        }
        mutexVino.release();
    }

    public void guardarAgua() throws InterruptedException {
        mutexAgua.acquire();
        System.out.println(Thread.currentThread().getName() + " guado un vino");
        mutexAgua.acquire();
        contAgua++;
        if (contAgua == 10) {
            System.out.println("CAJA DE AGUA LLENA !!!");
            empaquetadorAgua.release();
        }
        mutexAgua.release();
    }

    public void empaquetarCaja() throws InterruptedException {
        empaquetador.acquire();
        System.out.println(Thread.currentThread().getName() + "empaqueto una caja ");
        contAgua++;
        if (contAgua == 10) {
            transportador.release();
        }

    }

    public void reponerCaja() {

    }

    public void transportarCajas() throws InterruptedException {
        transportador.acquire();
        System.out.println(Thread.currentThread().getName() + " salio a repartir cajas");
        contAgua = 0;
    }
}
