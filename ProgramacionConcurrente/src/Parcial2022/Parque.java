/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parcial2022;

import Estructuras.Cola;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author galin
 */
public class Parque {

    private Lock acceso;
    private Condition visitante;
    private Condition visitanteLocal;
    private int capacidad;
    private int conVisitantes = 0;
    private int contVisitEsperando = 0;
    private int contVisitLocalEsperando = 0;
    private Cola colaVisitantes;
    private Cola colaVisitantesLocal;

    public Parque(int capacidad) {
        this.capacidad = capacidad;
        this.acceso = new ReentrantLock();
        this.visitante = acceso.newCondition();
        this.visitanteLocal = acceso.newCondition();
        this.colaVisitantes = new Cola();
        this.colaVisitantesLocal = new Cola();
    }

    public void entrarParque(String tipo) {
        //V = visitante
        //Vl = visitante local
        try {
            acceso.lock();
            if (tipo.equals("V")) {
                colaVisitantes.poner(Thread.currentThread());
                contVisitEsperando++;
            } else {
                colaVisitantesLocal.poner(Thread.currentThread());
                contVisitLocalEsperando++;
            }
            while (conVisitantes >= capacidad || !colaVisitantes.obtenerFrente().equals(Thread.currentThread())) {
                System.out.println("--El visitante " + Thread.currentThread().getName() + " entro a la cola");
                visitante.await();
            }
            System.out.println("El visitante " + Thread.currentThread().getName() + " entro al parque");
            colaVisitantes.sacar();
            contVisitEsperando--;
            conVisitantes++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void entrarParqueLocal(String tipo) {
        //V = visitante
        //Vl = visitante local
        try {
            acceso.lock();
            colaVisitantesLocal.poner(Thread.currentThread());
            contVisitLocalEsperando++;
            while (conVisitantes >= capacidad || !colaVisitantesLocal.obtenerFrente().equals(Thread.currentThread())) {
                System.out.println("--El visitante local " + Thread.currentThread().getName() + " entro a la cola");
                visitanteLocal.await();
            }
            System.out.println("El visitante local " + Thread.currentThread().getName() + " entro al parque");
            colaVisitantesLocal.sacar();
            contVisitLocalEsperando--;
            conVisitantes++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void salirParqueLocal(String tipo) {
        //V = visitante
        //Vl = visitante local
        try {
            acceso.lock();
            System.out.println("El visitante local " + Thread.currentThread().getName() + " salio del parque");
            conVisitantes--;
            //esta implementacion va a permitir que entren primero todos los visitante local antes de los visitantes
            if (contVisitLocalEsperando != 0) {
                visitanteLocal.signalAll();
            } else {
                visitante.signalAll();
            }
        } finally {
            acceso.unlock();
        }
    }

    public void salirParque(String tipo) {
        //V = visitante
        //Vl = visitante local
        try {
            acceso.lock();
            System.out.println("El visitante " + Thread.currentThread().getName() + " salio del parque");
            conVisitantes--;
            //esta implementacion va a permitir que entren primero todos los visitante local antes de los visitantes
            if (contVisitLocalEsperando != 0) {
                visitanteLocal.signalAll();
            } else {
                visitante.signalAll();
            }
        } finally {
            acceso.unlock();
        }
    }
}
