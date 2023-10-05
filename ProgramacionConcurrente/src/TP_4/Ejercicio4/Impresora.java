/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Impresora {

    private boolean disponible;
    private Semaphore estado;

    public Impresora() {
        this.disponible = true;
        this.estado = new Semaphore(1);
    }

    /*
    impresora
ocupado = false
mutex

boolean imprimir()
	mutex.acquire()
	if(!ocupado)	
		ocupado=true;
		mutex.realease()
		imprime()
		mutex.acquire()
		ocupado=false
		mutex.realese()
	else
		mutex.liberar()
     */
    public boolean imprimir() {

        try {
            this.estado.acquire();
            if (!this.disponible) {
                this.disponible = true;
                imprime();
                imprimiendo();
                liberarImpresora();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.disponible;
    }

    public void utilizarImpresora() {

    }

    public void liberarImpresora() {
        this.disponible = false;
        this.estado.release();
    }

    public void imprimiendo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imprime() {
        System.out.print("Impresora " + Thread.currentThread().getName().toString());
        System.out.println(" Imprimiendo....");
    }
}
