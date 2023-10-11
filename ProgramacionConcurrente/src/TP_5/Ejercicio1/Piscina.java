/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Piscina {

    private Semaphore capacidad;
    //sirve como permisos para entrar a la piscina

    public Piscina() {
        this.capacidad = new Semaphore(10);
    }

    //metodos persona
    public void ingresarPiscina() {
        try {
            capacidad.acquire();
            System.out.println("La persona " + Thread.currentThread().getName() + " entro a la piscina");
        } catch (InterruptedException ex) {
            Logger.getLogger(Piscina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salirPiscina() {
        System.out.println("La persona " + Thread.currentThread().getName() + " salio de la piscina");
        capacidad.release();
    }

    public void bañando() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
