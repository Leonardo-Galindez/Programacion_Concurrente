/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiendo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Silla {

    private Semaphore silla;
    private Semaphore barbero;
    private Semaphore salida;

    public Silla() {
        this.silla = new Semaphore(1, true);
        this.barbero = new Semaphore(0);
        this.salida = new Semaphore(0);
    }

    public void verificarSillon() {
        try { 
            this.silla.acquire();
            System.out.println(Thread.currentThread().getName() + " verifica la silla");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void solicitarAtencion() {
        this.barbero.release();
        System.out.println(Thread.currentThread().getName() + " solicita un corte");
    }

    public void finalizarCorte() {
        this.barbero.release();
        System.out.println(Thread.currentThread().getName() + "Terminamo de cortar");
        this.salida.release();
    }

    public void esperarAtencion() {
        try {
            this.salida.acquire();
            System.out.println(Thread.currentThread().getName() + " le estan cortando el pelo ....");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void liberarSilla() {
        this.silla.release();
        System.out.println(Thread.currentThread().getName() + " libero la silla");
    }

    public void esperarProximoCliente() {
        try {
            this.barbero.acquire();
            System.out.println(Thread.currentThread().getName() + " espera al proximo cliente");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
