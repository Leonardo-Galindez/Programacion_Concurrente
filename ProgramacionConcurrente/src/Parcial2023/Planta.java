package Parcial2023;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Planta {
    private Lock acceso;
    private Lock embotellador;

    private Condition embotelladorVino;
    private Condition embotelladorAgua;

    private Condition empaquetador;
    private Condition transportador;

    private int contVino = 0;
    private int contAgua = 0;

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
            while (contVino >= 10) {
                embotelladorVino.await();
            }
            contVino++;
            System.out.println("El embotellador " + Thread.currentThread().getName() + " preparo un vino");
            if (contVino == 10) {
                System.out.println("CAJA DE VINO LLENA!!!");
                empaquetador.signalAll();
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
                System.out.println("CAJA DE AGUA LLENA!!!");
                empaquetador.signalAll();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    // Empaquetador

    public void empaquetarVino() {

    }

    public void empaquetarAgua() {

    }

    public void guardarCaja() {

    }

    public void reponerCaja() {

    }

    // Transportador

    public void repartirCajas() {

    }
}
