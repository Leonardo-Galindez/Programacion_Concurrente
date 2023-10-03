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
    //consultar si hay otra forma de hacerlo
    private String nombre;
    private Semaphore semActual;
    private Semaphore semSiguiente;

    public Proceso(String nombre, Semaphore semActual, Semaphore semSiguiente) {
        this.nombre = nombre;
        this.semActual = semActual;
        this.semSiguiente = semSiguiente;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                semActual.acquire();
                // Realiza la lógica del proceso
                System.out.println("Proceso " + nombre + " ejecutándose");
                // Simulación de trabajo del proceso
                Thread.sleep(1000);
                System.out.println("Proceso " + nombre + " finalizado");
                semSiguiente.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
