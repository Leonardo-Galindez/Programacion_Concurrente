/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio7;

import java.util.Random;
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
    private Semaphore comer;
    private Semaphore aviso;

    public Silla() {
        this.silla = new Semaphore(1);//sirve para los permisos de la silla
        this.mozo = new Semaphore(0);//sirve para solicitar atencion de mozo
        this.comer = new Semaphore(0);//sirve para indicar que esta comiendo
        this.aviso = new Semaphore(0);//sirve para indicar que el empleado ya puede comer
    }

    /*
    Metodos para empleados
     */
    public void tomarLugar() {
        //El empleado adquiere la silla si esta libre
        try {
            silla.acquire();
            System.out.println("El empleado " + Thread.currentThread().getName() + " tomo la silla");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void solicitarAtencion() {
        //El empleado libera al mozo
        System.out.println("El empleado " + Thread.currentThread().getName() + " solicita atencion");
        //esperaAtencion();
        aviso.release();
    }

    public void esperaAtencion() {
        //simulacion de espera
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
            System.out.println(Thread.currentThread().getName() + " esperando atencion....");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
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

    public void liberarLugar() {
        //El empleado libera la silla
        System.out.println("El empleado " + Thread.currentThread().getName() + " agradece al mozo");
        mozo.release();
        silla.release();
    }

    /*
    Metodos de mozo
     */
    public void servirEmpleado() {
        try {
            //El mozo le sirve al empleado y libera para que pueda comer
            aviso.acquire();
            System.out.println("El empleado puede comer");
            comer.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void esperaProximoEmpleado() {
        try {
            //El mozo espera al proximo empleado disfrutar hobbie
            mozo.acquire();
            System.out.println("El mozo inventanda nuevas versiones de pollo");
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
