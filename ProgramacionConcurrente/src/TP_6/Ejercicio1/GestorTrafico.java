/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio1;

import Estructuras.Cola;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author galin
 */
public class GestorTrafico {

    private String tipo = "";
    private int nEspe = 0;
    private int sEspe = 0;
    private int nCru = 0;
    private int sCru = 0;
    private int cont = 0;
    private int limite;
    private Lock acceso;
    private Condition n;
    private Condition s;
    private Cola ordenS;
    private Cola ordenN;

    public GestorTrafico(int limite) {
        this.acceso = new ReentrantLock();
        this.n = acceso.newCondition();
        this.s = acceso.newCondition();
        this.limite = limite;
        this.ordenS = new Cola();
        this.ordenN = new Cola();
    }

    public void ingresarControl(String unTipo) {
        try {
            acceso.lock();
            if (ordenN.esVacia() && nCru == 0 && ordenS.esVacia() && sCru == 0) {
                this.tipo = unTipo;
            }

            if (this.tipo.equals("N")) {
                System.out.println("Auto Norte " + Thread.currentThread().getName() + " esta en la cola");
                ordenN.poner(Thread.currentThread().getName());
                nEspe++;
            } else {
                System.out.println("Auto Sur " + Thread.currentThread().getName() + " esta en la cola");
                ordenS.poner(Thread.currentThread().getName());
                sEspe++;
            }
        } finally {
            acceso.unlock();
        }
    }

    public void cruzarAutoN() {
        try {
            acceso.lock();
            while (nCru >= limite || sCru != 0 || this.tipo.equals("S") || !ordenN.obtenerFrente().equals(Thread.currentThread().getName())) {
                n.await();
            }
            System.out.println("Auto Norte " + Thread.currentThread().getName() + " esta cruzando");
            nEspe--;
            ordenN.sacar();
            nCru++;
            cont++;
            //si llegamos al limite de un lado o no hay mas autos de n que quieran pasar cambiamos el tipo
            if (nCru == limite || ordenN.esVacia()) {
                //verificamos que hallan autos esperando en s
                if (!ordenS.esVacia()) {
                    this.tipo = "S";
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void salirAutoN() {
        try {
            acceso.lock();
            System.out.println("Auto Norte " + Thread.currentThread().getName() + " salio del puente");
            cont--;
            if (cont == 0) {
                nCru = 0;
                s.signalAll();
            }
        } finally {
            acceso.unlock();
        }
    }

    public void cruzarAutoS() {
        try {
            acceso.lock();

            while (sCru >= limite || nCru != 0 || this.tipo.equals("N") || !ordenS.obtenerFrente().equals(Thread.currentThread().getName())) {
                s.await();
            }
            System.out.println("Auto Sur " + Thread.currentThread().getName() + " esta cruzando");
            sEspe--;
            ordenS.sacar();
            sCru++;
            //si llegamos al limite de un lado o no hay mas autos de s que quieran pasar cambiamos el tipo
            if (sCru == limite || ordenS.esVacia()) {
                //verificamos que hallan autos esperando en n
                if (!ordenN.esVacia()) {
                    this.tipo = "N";
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void salirAutoS() {
    }
    /*public void ingresarPuente(String unTipo) {
        if (contAutosSur == 0 && contAutosNorte == 0 && esperandoSur.esVacia() && esperndoNorte.esVacia()) {
            if(){
                
            }
        }
    }
    public synchronized void entrarAutoNorte() throws InterruptedException {
        while (!esperandoNorte.esVacia() || contAutosNorte >= 10 || cruzandoSur) {
            //habilitamos a autos Sur
            if (contAutosSur >= 10 && contAutosNorte >= 10) {
                contAutosSur = 0;
                cruzandoSur = true;
                cruzandoNorte = false;
            }
            System.out.println("Auto Norte" + Thread.currentThread().getName() + " esta haciendo cola");
            esperandoNorte.poner(Thread.currentThread());
            this.wait();
        }
        cruzandoSur = false;
        cruzandoNorte = true;
        System.out.println("Auto Norte " + Thread.currentThread().getName() + " esta cruzando");
        contAutosNorte++;
    }

    public synchronized void salirAutoNorte() {
        System.out.println("Auto Norte " + Thread.currentThread().getName() + " termino de cruzar");
        esperandoNorte.sacar();//liberamos el siguiente auto en la cola
        this.notifyAll();//tenemos el problema que el conjunto de espera estan los autos de sur y norte juntos 
    }

    public synchronized void entrarAutoSur() throws InterruptedException {
        while (!esperandoSur.esVacia() || contAutosSur >= 10 || cruzandoNorte) {
            //habilitamos a autos Norte
            if (contAutosSur >= 10 && contAutosNorte >= 10) {
                contAutosNorte = 0;
                cruzandoNorte = true;
                cruzandoSur = false;
            }
            System.out.println("Auto Sur " + Thread.currentThread().getName() + " esta haciendo cola");
            esperandoSur.poner(Thread.currentThread());
            this.wait();
        }
        cruzandoNorte = false;
        cruzandoSur = true;
        System.out.println("Auto Sur " + Thread.currentThread().getName() + " esta cruzando");
        contAutosSur++;
    }

    public synchronized void salirAutoSur() {
        System.out.println("Auto Sur " + Thread.currentThread().getName() + " termino de cruzar");
        esperandoSur.sacar();//liberamos el siguiente auto en la cola
        this.notifyAll();//tenemos el problema que el conjunto de espera estan los autos de sur y norte juntos 
    }
     */
}
