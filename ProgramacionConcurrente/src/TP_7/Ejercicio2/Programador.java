/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_7.Ejercicio2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author galin
 */
public class Programador implements Runnable {

    private Proyecto proyecto;

    public Programador(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void run() {
        proyecto.tomarComputadora();
        proyecto.tomarLibro();
        trabajarProyecto();
        proyecto.finalizarProyecto();
    }

    public void trabajarProyecto() {
        System.out.println("El Programador " + Thread.currentThread().getName() + "inicio a trabajar");
    }
}
