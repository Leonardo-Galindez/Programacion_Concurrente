/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Pista {

    private Semaphore pista;
    private Semaphore despegar;
    private Semaphore aterrizar;

    public Pista() {
        //this.pista = new Semaphore(0);
        this.despegar = new Semaphore(0);//sirve para saber si puede despegar o no
        this.aterrizar = new Semaphore(0);//sirve para saber si puede aterrizar o no
    }

    //Metodos Torre de control
    public void avilitarAterrizague() {
        //la torre de control avilida para aterrizague
        System.out.println("La torre de control avilito el aterrizague");
        aterrizar.release();
    }

    //Metodos avion
    public void aterrizar() {
        try {
            aterrizar.acquire();
            System.out.println("Avion " + Thread.currentThread().getName() + " aterrizo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
