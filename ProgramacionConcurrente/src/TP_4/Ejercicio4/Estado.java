/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio4;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Estado {

    private boolean ocupada = false;
    private Semaphore estado;

    public Estado() {
        this.estado = new Semaphore(1);
    }

    public void verificarDisponibilidad() {
        if (!ocupada) {
            
        }
    }

    public void utilizarImpresora() {
        //this.estado.acquire();
        System.out.println("Cliente " + Thread.currentThread().getName() + " Utilizando Impresora");
    }

    public void liberarImpresora() {
        this.estado.release();
        System.out.println("Cliente " + Thread.currentThread().getName() + " Libero la Impresora");
    }
}
