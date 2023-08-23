/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_2.Ejercicio4;

/**
 *
 * @author galin
 */
public class ThreadEjemploRunnable implements Runnable {

    private String nombre;

    public ThreadEjemploRunnable(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + this.nombre);
        }
        System.out.println("Termina thread " + this.nombre);
    }
}
