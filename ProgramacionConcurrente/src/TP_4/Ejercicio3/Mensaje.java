/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Mensaje {

    private Semaphore sem1;
    private Semaphore sem2;
    private Semaphore sem3;

    public Mensaje() {
        this.sem1 = new Semaphore(1);
        this.sem2 = new Semaphore(0);
        this.sem3 = new Semaphore(0);
    }

    public void iniciarProceso() {
        try {
            sem1.acquire();
            System.out.println(Thread.currentThread().getName() + " Ejecutando....");
        } catch (InterruptedException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarProceso() {
        sem1.release();
    }

    public void p1() {
        try {
            sem1.acquire();
            System.out.println(Thread.currentThread().getName() + " Ejecutando....");
            sem3.release();//libera sem3 para que se ejecute el proceso 3
        } catch (InterruptedException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void p2() {
        try {
            sem2.acquire();
            System.out.println("P2 .... Ejecutando");
            sem1.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void p3() {
        try {
            sem3.acquire();
            System.out.println("P3 .... Ejecutando");
            sem2.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
