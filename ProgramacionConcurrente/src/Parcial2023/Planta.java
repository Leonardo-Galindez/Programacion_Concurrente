package Parcial2023;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Planta {
    private Lock acceso;
    // private Lock embotellador;

    private Condition embotelladorVino;
    private Condition embotelladorAgua;

    private Condition empaquetador;

    private Condition transportador;

    private int contVino = 0;
    private int contAgua = 0;
    private int cajas = 0;
    private boolean empacar = false;
    private boolean transportar = false;
    private boolean empaquetando = false;

    public Planta() {
        this.acceso = new ReentrantLock();
        // this.embotellador = new ReentrantLock();
        // se puede trabajar en concurrencia cuando un
        // embotellador no llego la caja y otro si

        this.embotelladorVino = acceso.newCondition();
        this.embotelladorAgua = acceso.newCondition();

        this.empaquetador = acceso.newCondition();

        this.transportador = acceso.newCondition();
    }

    // Embotellador

    public void prepararBotellaVino() {
        try {
            acceso.lock();
            while (contVino >= 10 || empaquetando) {
                embotelladorVino.await();
            }
            contVino++;
            System.out.println("El embotellador " + Thread.currentThread().getName() + " preparo un vino");
            if (contVino == 10) {
                System.out.println("CAJA DE VINO LLENA!!!");
                empacar = true;
                empaquetador.signalAll();
                // mandar señal que es caja de agua ejemplo: mandar una "V" o algo asi
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void prepararBotellaAgua() {
        try {
            acceso.lock();
            while (contAgua >= 10) {
                embotelladorAgua.await();
            }
            contAgua++;
            System.out.println("El embotellador " + Thread.currentThread().getName() + " preparo un agua");
            if (contVino == 10) {
                System.out.println("CAJA DE AGUA LLENA !!!");
                empacar = true;
                empaquetador.signalAll();
                // mandar señal que es caja de agua ejemplo: mandar una "A" o algo asi
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    // Empaquetador

    public void empaquetarCaja() {
        try {
            acceso.lock();
            while (!empacar) {
                empaquetador.await();
            }
            System.out.println("El empaquetador " + Thread.currentThread().getName() + " guarda la caja de vino");
            cajas++;
            empaquetando = true;
            if (cajas == 10) {
                System.out.println("Almacen Lleno !!!");
                transportar = true;
                transportador.signalAll();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void reponerCajaVino() {
        try {
            acceso.lock();
            contVino = 0;
            System.out.println("El empaquetador " + Thread.currentThread().getName() + " repone caja de vinos");
            empaquetando = false;
            embotelladorVino.signalAll();
            embotelladorAgua.signalAll();
        } finally {
            acceso.unlock();
        }
    }

    public void reponerCajaAgua() {
        try {
            acceso.lock();
            contAgua = 0;
            System.out.println("El empaquetador " + Thread.currentThread().getName() + " repone caja de agua");
            empaquetando = false;
            embotelladorAgua.signalAll();
            embotelladorVino.signalAll();
        } finally {
            acceso.unlock();
        }
    }

    // Transportador

    public void repartirCajas() {
        try {
            acceso.lock();
            while (!transportar) {
                transportador.await();
            }
            System.out.println("El transportador " + Thread.currentThread().getName() + " esta repartiendo las cajas");
            cajas = 0;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }
}
