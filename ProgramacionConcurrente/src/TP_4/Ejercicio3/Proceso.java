/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Proceso implements Runnable {

    private final Semaphore semActual;
    private final Semaphore semSiguiente;
    private final String nombre;

    public Proceso(Semaphore semActual, Semaphore semSiguiente, String nombre) {
        this.semActual = semActual;
        this.semSiguiente = semSiguiente;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            while (true) {
                semActual.acquire();
                System.out.println(nombre + " ejecutando...");
                // Simula el trabajo del proceso
                Thread.sleep(1000);
                semSiguiente.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
