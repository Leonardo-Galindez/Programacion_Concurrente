/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_7.Ejercicio2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Proyecto {

    private int cantLibros;
    private int cantComputadoras;
    private int contL = 0;
    private int contC = 0;
    private Lock acceso;//
    private Condition inicio;

    public Proyecto(int cantidad) {
        this.cantLibros = cantidad;
        this.cantComputadoras = cantidad;
        this.acceso = new ReentrantLock(true);
        inicio = acceso.newCondition();

    }

    public void tomarLibro() {
        try {
            acceso.lock();
            while (cantLibros <= contL) {
                System.out.println("El programador " + Thread.currentThread().getName() + " esta esperando libro");
                inicio.await();
            }
            System.out.println("El Programador" + Thread.currentThread().getName() + " tomo un libro");
            contL++;
        } catch (InterruptedException ex) {
            Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            acceso.unlock();
        }
    }

    public void tomarComputadora() {
        try {
            acceso.lock();
            while (cantComputadoras <= contC) {
                System.out.println("El programador " + Thread.currentThread().getName() + " esta esperando computadora");
                inicio.await();
            }
            System.out.println("El Programador " + Thread.currentThread().getName() + " tomo una computadora");
            contC++;
        } catch (InterruptedException ex) {
           ex.printStackTrace();
        } finally {
            acceso.unlock();
        }
    }

    public void finalizarProyecto() {
        try {
            acceso.lock();
            System.out.println("El programador " + Thread.currentThread().getName() + " termino de trabajar");
            contL--;
            contC--;
            inicio.signal();
        } finally {
            acceso.unlock();
        }
    }
}
