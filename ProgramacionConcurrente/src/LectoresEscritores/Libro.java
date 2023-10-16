/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LectoresEscritores;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Libro {

    private Semaphore escritor;
    private Semaphore lector;
    private int cont;//contador para saber cuantos 

    public Libro() {
        this.escritor = new Semaphore(1);
        this.lector = new Semaphore(4);

    }

    public void leer() {
        try {//como se cuanto lectores estan leyendo para que el escritor pueda leer
            lector.acquire();
            System.out.println("El lector" + Thread.currentThread().getName() + " esta leyendo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarLeer() {
        System.out.println("El lector" + Thread.currentThread().getName() + " termino de leer");
        lector.release();
    }

    public void escribir() {
        try {
            escritor.acquire();
            lector.acquire(4);//para que ningun lector pueda acceder al libro
            //lectores.acquire();
            System.out.println("El escritor" + Thread.currentThread().getName() + " esta escribiendo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarEscribir() {
        System.out.println("El escritor" + Thread.currentThread().getName() + " termino de escribir");
        escritor.release();
        lector.release(4);
    }
}
