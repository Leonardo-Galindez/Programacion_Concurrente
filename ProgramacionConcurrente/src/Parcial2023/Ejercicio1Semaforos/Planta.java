package Parcial2023.Ejercicio1Semaforos;

import java.util.concurrent.Semaphore;

public class Planta {

    private Semaphore cajaVino;
    private Semaphore cajaAgua;

    private Semaphore empaquetador;
    private Semaphore transportador;
    private Semaphore mandarCajaEmpacar;

    private Semaphore mutexVino;
    private Semaphore mutexAgua;
    private Semaphore mutexEmpaquetador;

    private int contVino = 0;
    private int contAgua = 0;
    private int contCajas = 0;

    private boolean reponerCajaVino = false;
    private boolean reponerCajaAgua = false;

    public Planta() {

        this.cajaVino = new Semaphore(10);
        this.cajaAgua = new Semaphore(10);
        this.empaquetador = new Semaphore(0);
        this.transportador = new Semaphore(0);
        this.mutexEmpaquetador = new Semaphore(1);
        this.mandarCajaEmpacar = new Semaphore(1);

        this.mutexVino = new Semaphore(1);
        this.mutexAgua = new Semaphore(1);

    }

    public void guardarVino() throws InterruptedException {
        cajaVino.acquire();
        System.out.println(Thread.currentThread().getName() + " guardo un vino ---");
        mutexVino.acquire();
        contVino++;
        if (contVino == 10) {
            mandarCajaEmpacar.acquire(); // para resolver que no le manden las dos cajas llenas al empaquetador
            System.out.println("CAJA DE VINO LLENA !!!");
            reponerCajaVino = true;
            empaquetador.release();
        }
        mutexVino.release();
    }

    public void guardarAgua() throws InterruptedException {
        cajaAgua.acquire();
        System.out.println(Thread.currentThread().getName() + " guardo un agua +++");
        mutexAgua.acquire();
        contAgua++;
        if (contAgua == 10) {
            mandarCajaEmpacar.acquire();
            System.out.println("CAJA DE AGUA LLENA !!!");
            reponerCajaAgua = true;
            empaquetador.release();
        }
        mutexAgua.release();
    }

    public void empaquetarCaja() throws InterruptedException {
        empaquetador.acquire();
        System.out.println(Thread.currentThread().getName() + " empaqueto una caja ");
        mutexEmpaquetador.acquire();
        contCajas++;
        if (contCajas == 10) {
            transportador.release();
        }
        mutexEmpaquetador.release();
    }

    public void reponerCaja() throws InterruptedException {
        mutexEmpaquetador.acquire();
        if (reponerCajaVino) {
            System.out.println(Thread.currentThread().getName() + " repone una caja de Vino ");
            contVino = 0;
            reponerCajaVino = false;
            cajaVino.release(10);
        } else {
            if (reponerCajaAgua) {
                System.out.println(Thread.currentThread().getName() + " repone una caja de Agua ");
                contAgua = 0;
                reponerCajaAgua = false;
                cajaAgua.release(10);
            }
        }
        mandarCajaEmpacar.release();
        mutexEmpaquetador.release();
    }

    public void transportarCajas() throws InterruptedException {
        transportador.acquire();
        System.out.println(Thread.currentThread().getName() + " salio a repartir cajas");
        contCajas = 0;
    }
}
