package Parcial2020.TEMAI;

import java.util.concurrent.Semaphore;

public class ControlTrasbordador {
    private Semaphore subir;
    private Semaphore bajar;
    private Semaphore mutex;
    private Semaphore transbordador;

    private int contCapacidad = 0;
    private int contAutosEsperando = 0;

    public ControlTrasbordador() {
        this.subir = new Semaphore(10, true);
        this.bajar = new Semaphore(0, true);
        this.mutex = new Semaphore(1);
        this.transbordador = new Semaphore(0);
    }

    // Metodos de auto
    public void subirAuto() throws InterruptedException {
        mutex.acquire();
        contAutosEsperando++;
        mutex.release();
        subir.acquire();
        System.out.println(Thread.currentThread().getName() + " subio al transbordador");
        mutex.acquire();
        contAutosEsperando--;
        contCapacidad++;
        if (contCapacidad == 10 || contAutosEsperando == 0) {
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
            subir.release(10);
        }
        mutex.release();
    }

    // Metodos de transbordador
    public void ir() throws InterruptedException {
        transbordador.acquire();
        System.out.println(Thread.currentThread().getName() + " inicio el viaje");
    }

    public void volver() {
        System.out.println(Thread.currentThread().getName() + " volvio del viaje");
        bajar.release(10);
    }
}
