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

    private int num;
    private boolean ocupada;
    private Semaphore estado;

    public Impresora(int num) {
        this.ocupada = false;
        this.num = num;
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
            if (!this.ocupada) {
                this.ocupada = true;
                imprime();
                imprimiendo();
                liberarImpresora();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.ocupada;
    }

    public void utilizarImpresora(){
        
    }
    public void liberarImpresora() {
        this.ocupada = false;
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

    @Override
    public String toString() {
        return "" + this.num;
    }

}
