/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_2.Ejercicio2;

/**
 *
 * @author galin
 */
public class MiEjecucion extends Thread {

    public void run() {
        ir();
    }

    public void ir() {
        hacerMas();
    }

    public void hacerMas() {
        /*
        En este código, se ha agregado un bloque
        synchronized en el método hacerMas() para
        asegurarse de que la impresión "En la pila"
        se realice de manera exclusiva, evitando que
        otros hilos lo hagan simultáneamente.
        */
        //A esto de refiere con forzar???
        synchronized (this) {
            System.out.println("En la pila");
        }
    }
}
