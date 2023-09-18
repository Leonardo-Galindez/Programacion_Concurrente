/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio1;

/**
 *
 * @author galin
 */
public class Contador {

    private int c;

    public Contador() {
        this.c = 0;
    }

    public void increment() {
        synchronized (this) {
            c++;
        }
    }

    public void decrement() {
        synchronized (this) {
            c--;
        }

    }

    public int value() {
        synchronized (this) {
            return c;
        }

    }

}
