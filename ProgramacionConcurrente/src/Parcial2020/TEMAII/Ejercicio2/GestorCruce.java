package Parcial2020.TEMAII.Ejercicio2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Estructuras.Cola;

public class GestorCruce {

    private Lock cruceNorte;
    private Lock cruceOeste;
    private Condition autoNorte;
    private Condition autoOeste;
    private Cola colaNorte; // importa el orden???
    private Cola colaOeste; // importa el orden???

    private boolean semaforoNorte = false;
    private boolean semaforoOeste = true;

    public GestorCruce() {
        this.cruceNorte = new ReentrantLock();
        this.cruceOeste = new ReentrantLock();
        this.autoNorte = cruceNorte.newCondition();
        this.autoOeste = cruceOeste.newCondition();
        this.colaNorte = new Cola();
        this.colaOeste = new Cola();
    }

    public void llegaNorte() {
        try {
            cruceNorte.lock();
            while (!semaforoNorte) {
                autoNorte.await();
            }
            System.out.println(Thread.currentThread().getName() + " esta cruzando NORTE--SUR+++++++++");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            cruceNorte.unlock();
        }
    }

    public void llegaOeste() {
        try {
            cruceOeste.lock();
            while (!semaforoOeste) {
                autoOeste.await();
            }
            System.out.println(Thread.currentThread().getName() + " esta cruzando OESTE--ESTE+++++++++");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            cruceOeste.unlock();
        }
    }

    public void sale() {
        System.out.println(Thread.currentThread().getName() + " salio del cruce");
    }

    public void cambiaSemaforos() {
        // importa cuantos unlock ponga???
        //forma de usar locks
        try {
            cruceNorte.lock();
            cruceOeste.lock();
            System.out.println("CAMBIO DE SEMAFOROS");
            if (semaforoNorte) {
                semaforoNorte = false;
                semaforoOeste = true;
                autoOeste.signalAll();
            } else {
                semaforoNorte = true;
                semaforoOeste = false;
                autoNorte.signalAll();
            }
        } finally {
            cruceNorte.unlock();
            cruceOeste.unlock();
        }
    }
}
