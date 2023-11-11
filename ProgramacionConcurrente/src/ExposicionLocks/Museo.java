/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExposicionLocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author galin
 */
public class Museo {

    private int capacidad;
    private int cantVisitantes;
    private boolean criticoEsperando, criticoDentro, responsableDentro;

    private Lock acceso;

    private Condition visitante;
    private Condition critico;
    private Condition responsable;

    public Museo(int capacidad) {
        this.capacidad = capacidad;
        this.cantVisitantes = 0;

        this.criticoEsperando = false;
        this.criticoDentro = false;
        this.responsableDentro = false;

        this.acceso = new ReentrantLock();

        this.visitante = acceso.newCondition();
        this.critico = acceso.newCondition();
        this.responsable = acceso.newCondition();
    }

    public void entrarVisitante() {
        try {
            acceso.lock();
            while (criticoEsperando || criticoDentro || cantVisitantes >= capacidad) {
                visitante.await();
            }
            cantVisitantes++;
            System.out.println("El visitante " + Thread.currentThread().getName() + " entro a la sala");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void entrarResponsable() {
        try {
            acceso.lock();
            while (criticoEsperando || criticoDentro || responsableDentro) {
                responsable.await();
            }
            responsableDentro = true;
            System.out.println("El responsable " + Thread.currentThread().getName() + " entro a la sala");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void entrarCritico() {
        try {
            acceso.lock();
            while (criticoEsperando || criticoDentro || cantVisitantes != 0) {
                criticoEsperando = true;
                critico.await();
                criticoEsperando = false;
            }
            //criticoEsperando = false;
            criticoDentro = true;
            System.out.println("El critico " + Thread.currentThread().getName() + " entro a la sala");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void salirVisitante() {
        try {
            acceso.lock();
            System.out.println("El visitante " + Thread.currentThread().getName() + " salio de la sala");
            cantVisitantes--;
            visitante.signalAll();
            critico.signalAll();
        } finally {
            acceso.unlock();
        }
    }

    public void salirResponsable() {
        try {
            acceso.lock();
            System.out.println("El responsable " + Thread.currentThread().getName() + " salio de la sala");
            responsableDentro = false;
            responsable.signalAll();
        } finally {
            acceso.unlock();
        }
    }

    public void salirCritico() {
        try {
            acceso.lock();
            System.out.println("El critico " + Thread.currentThread().getName() + " salio de la sala");
            criticoDentro = false;
            //criticoEsperando = false;
            critico.signalAll();
            visitante.signalAll();
            responsable.signalAll();
        } finally {
            acceso.unlock();
        }
    }
}
