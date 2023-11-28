package Parcial2020.TEMAI.Ejercicio1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ControlTrasnbordadorLock {
    private Lock subirAcceso;
    private Lock bajarAcceso;
    private Condition autosSubir;
    private Condition autosBajar;
    private Condition transbordadorIr;
    private Condition transbordadorVolver;

    private int contAutos = 0;

    private boolean iniciarViaje = false;
    private boolean bajar = false;
    private boolean volver = false;

    public ControlTrasnbordadorLock() {
        this.subirAcceso = new ReentrantLock();
        this.bajarAcceso = new ReentrantLock();
        this.autosSubir = subirAcceso.newCondition();
        this.transbordadorIr = subirAcceso.newCondition();
        this.autosBajar = bajarAcceso.newCondition();
        this.transbordadorVolver = bajarAcceso.newCondition();
    }

    public void subirAutoLock() {
        try {
            subirAcceso.lock();
            while (contAutos >= 10) {
                System.out.println("+");
                autosSubir.await();
            }
            System.out.println(Thread.currentThread().getName() + " subio al transbordador");
            contAutos++;
            if (contAutos == 10) {
                iniciarViaje = true;
                transbordadorIr.signal();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            subirAcceso.unlock();
        }
    }

    public void bajarAutoLock() {
        try {
            bajarAcceso.lock();
            while (!bajar) {
                System.out.println("-");
                autosBajar.await();
            }
            System.out.println(Thread.currentThread().getName() + " bajo del transbordador");
            contAutos--;
            if (contAutos == 0) {
                volver = true;
                bajar = false;
                transbordadorVolver.signal();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            bajarAcceso.unlock();
        }
    }

    public void irLock() {
        try {
            subirAcceso.lock();
            while (!iniciarViaje) {
                transbordadorIr.await();
            }
            System.out.println(Thread.currentThread().getName() + " inicio el viaje");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            subirAcceso.unlock();
        }
    }

    public void destinoLock() {
        try {
            bajarAcceso.lock();
            System.out.println(Thread.currentThread().getName() + " llego a destino");
            bajar = true;
            autosBajar.signalAll();
        } finally {
            bajarAcceso.unlock();
        }
    }

    public void volverLock() {
        try {
            bajarAcceso.lock();
            subirAcceso.lock();
            while (!volver) {
                transbordadorVolver.await();
            }
            System.out.println(Thread.currentThread().getName() + " volvio del viaje");
            volver = false;
            iniciarViaje = false;
            autosSubir.signalAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            subirAcceso.unlock();
            bajarAcceso.unlock();
        }
    }
}
