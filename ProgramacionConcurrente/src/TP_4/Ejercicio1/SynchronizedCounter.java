/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio1;

/**
 *
 * @author galin
 */
public class SynchronizedCounter implements Runnable {

    private Contador c;

    public SynchronizedCounter(Contador c) {
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println(c.value());
        c.increment();
        System.out.println(c.value());
        System.out.println("----");
    }
}
