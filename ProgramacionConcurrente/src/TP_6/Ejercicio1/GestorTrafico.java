/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio1;

import Estructuras.Cola;
import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class GestorTrafico {

    private int contAutosSur = 0;
    private int contAutosNorte = 0;
    private boolean cruzandoSur = false;
    private boolean cruzandoNorte = false;
    private Cola esperandoSur, esperandoNorte;

    public GestorTrafico() {
        this.esperandoSur = new Cola();
        this.esperandoNorte = new Cola();
    }

    /*public void ingresarPuente(String unTipo) {
        if (contAutosSur == 0 && contAutosNorte == 0 && esperandoSur.esVacia() && esperndoNorte.esVacia()) {
            if(){
                
            }
        }
    }*/
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

}
