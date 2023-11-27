package Parcial2020.TEMAI.Ejercicio1;

import java.util.concurrent.Semaphore;

public class ControlTrasbordador {
    private Semaphore subir;
    private Semaphore bajar;
    private Semaphore volver;
    private Semaphore mutex;
    private Semaphore transbordador;

    private int contCapacidad = 0;
    private int contAutosEsperando = 0;

    public ControlTrasbordador() {
        this.subir = new Semaphore(10, true);
        this.bajar = new Semaphore(0, true);
        this.mutex = new Semaphore(1);
        this.volver = new Semaphore(0);
        this.transbordador = new Semaphore(0);
    }

    // Metodos de auto
    public void subirAuto() throws InterruptedException {
        subir.acquire();
        System.out.println(Thread.currentThread().getName() + " subio al transbordador");
        mutex.acquire();
        contCapacidad++;
        if (contCapacidad == 10) {// como permitir que inicie sin que se llene=????''
            System.out.println("TRANSBORDADOR LLENO !!!");
            transbordador.release();
        }
        mutex.release();
    }

    public void bajarAuto() throws InterruptedException {
        bajar.acquire(); // estoy permitiendo que bajen de a uno?
        System.out.println(Thread.currentThread().getName() + " bajo del transbordador");
        mutex.acquire();
        contCapacidad--;
        if (contCapacidad == 0) {
            System.out.println("TRANSBORDADOR VACIO !!!");
            volver.release();
        }
        mutex.release();
    }

    // Metodos de transbordador
    public void ir() throws InterruptedException {
        transbordador.acquire();
        System.out.println(Thread.currentThread().getName() + " inicio el viaje");
    }

    public void destino() {
        System.out.println(Thread.currentThread().getName() + " llego a destino");
        bajar.release(10);
    }

    public void volver() throws InterruptedException {
        volver.acquire();
        System.out.println(Thread.currentThread().getName() + " volvio del viaje");
        subir.release(10);
    }
}
