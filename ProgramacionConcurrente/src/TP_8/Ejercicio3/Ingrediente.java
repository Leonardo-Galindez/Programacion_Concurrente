/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_8.Ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Ingrediente {

    //capacidad
    private int carne;
    private int vegetales;
    private int pasta;

    //contadores
    private int contCarne;
    private int contVegetales;
    private int contPasta;

    //locks ingredientes
    private Lock lockCarne;
    private Condition cocineroCarne;

    private Lock lockVegetales;
    private Condition cocineroVegetales;

    private Lock lockPasta;
    private Condition cocineroPasta;

    public Ingrediente(int cantCarne, int cantVegetales, int cantPasta) {

        this.carne = cantCarne;
        this.vegetales = cantVegetales;
        this.pasta = cantPasta;

        this.contCarne = cantCarne;
        this.contVegetales = cantVegetales;
        this.contPasta = cantPasta;

        this.lockCarne = new ReentrantLock();
        this.lockVegetales = new ReentrantLock();
        this.lockPasta = new ReentrantLock();

        this.cocineroCarne = lockCarne.newCondition();
        this.cocineroVegetales = lockVegetales.newCondition();
        this.cocineroPasta = lockPasta.newCondition();
    }

    public void cocinarParrilla(int cantidad) {
        try {
            lockCarne.lock();
            while ((contCarne - cantidad) <= 0) {
                cocineroCarne.await();
            }
            System.out.println("El cocineroP " + Thread.currentThread().getName() + " tomo carne");
            contCarne -= cantidad;
        } catch (InterruptedException ex) {
            Logger.getLogger(Ingrediente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lockCarne.unlock();
        }
    }

}
