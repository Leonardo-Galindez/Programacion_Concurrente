/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Silla {

    private Semaphore silla;
    private Semaphore mozo;
    private Semaphore cocinero;
    private Semaphore comer;
    private Semaphore tomar;
    private Semaphore mutexMozo;
    private Semaphore mutexCocinero;

    public Silla() {
        this.silla = new Semaphore(2);
        this.mozo = new Semaphore(0);
        this.comer = new Semaphore(0);
        this.cocinero = new Semaphore(0);
        this.tomar = new Semaphore(0);
        this.mutexMozo = new Semaphore(1);//sirve para que el mozo no atienda a los 2 clientes a la vez
        this.mutexCocinero = new Semaphore(1);
    }

    //Metodos empleado
    public void tomarSilla() {
        try {
            silla.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " tomo uno silla");
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitarAtencionMozo() {
        try {
            //El adquiere al la atencion del mozo por ese cliente
            mozo.release();
            mutexMozo.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " solicita atencion del mozo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitarAtencionCocinero() {
        try {
            //El empleado libera al cocinero
            mutexCocinero.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " solicita atencion del cocinero");
            mozo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void liberarMozo() {
        System.out.println("El empleado " + Thread.currentThread().getName() + " libero la silla");
        silla.release();
        mozo.release();
    }

    public void liberarCocinero() {
        System.out.println("El empleado " + Thread.currentThread().getName() + " libero la silla");
        silla.release();
        cocinero.release();
        mutexCocinero.release();
    }

    public void comer() {
        //El empleado va a comer
        try {
            comer.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " esta comiendo......");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void tomar() {
        //El empleado va a tomar
        try {
            tomar.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " esta tomando......");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    //Metodos mozo

    public void servirEmpleado() {
        try {
            //El mozo le sirve al empleado y libera para que pueda tomar
            mozo.acquire();
            System.out.println("El empleado puede tomar ");
            tomar.release();
            mutexMozo.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void esperaProximoEmpleadoMozo() {
        try {
            //El mozo espera al proximo empleado disfrutar hobbie
            mozo.acquire();
            System.out.println("El mozo inventanda nuevas versiones de pollo");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    //Metodos cocinero
    public void cocinarEmpleado() {
        try {
            //El mozo le sirve al empleado y libera para que pueda comer
            cocinero.acquire();
            System.out.println("El empleado puede comer ");
            comer.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void esperaProximoEmpleadoCocinero() {
        try {
            //El mozo espera al proximo empleado disfrutar hobbie
            cocinero.acquire();
            System.out.println("El mozo inventanda nuevas versiones de pollo");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
