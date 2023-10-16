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

    private Semaphore mozo;
    private Semaphore cocinero;
    private Semaphore silla;
    private Semaphore comer;
    private Semaphore tomar;
    private Semaphore mutexMozo;
    private Semaphore mutexCocinero;

    public Silla() {
        this.silla = new Semaphore(2);//sirve para saber si se llenaron los lugares
        this.mozo = new Semaphore(0);//sirve para indicar que esta esperando al proximo cliente
        this.comer = new Semaphore(0);//sirve para idicar que el empleado puede comer
        this.cocinero = new Semaphore(0);//sirve para indicar que esta esperando al proximo cliente
        this.tomar = new Semaphore(0);//sirve para indicar que el empleado puede tomar
        this.mutexMozo = new Semaphore(1);//sirve para que el mozo no atienda a los 2 clientes a la vez
        this.mutexCocinero = new Semaphore(1);//sirve para que el cocinero no atienda a los 2 clientes a la vez
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

            mutexMozo.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " solicita atencion del mozo");
            mozo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitarAtencionCocinero() {
        try {
            //El empleado libera al cocinero
            mutexCocinero.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " solicita atencion del cocinero");
            cocinero.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void liberarMozo() {
        System.out.println("El empleado " + Thread.currentThread().getName() + " libero la silla");
        silla.release();
        mutexMozo.release();
        mozo.release();
    }

    public void liberarCocinero() {
        System.out.println("El empleado " + Thread.currentThread().getName() + " libero la silla");
        silla.release();
        mutexCocinero.release();
        cocinero.release();
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
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void esperaProximoEmpleadoMozo() {
        try {
            mozo.acquire();
            System.out.println("El mozo inventanda nuevas versiones de pollo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodos cocinero
    public void cocinarEmpleado() {

        try {
            //El cocinero le sirve al empleado y libera para que pueda comer
            cocinero.acquire();
            System.out.println("El empleado puede comer ");
            comer.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void esperaProximoEmpleadoCocinero() {
        try {
            //El mozo espera al proximo empleado disfrutar hobbie
            cocinero.acquire();
            System.out.println("El cocinero ordena la cocina y probar nuevas recetas");
            cocinero.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
