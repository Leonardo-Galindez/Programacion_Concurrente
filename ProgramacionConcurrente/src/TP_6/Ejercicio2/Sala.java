/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio2;

import Estructuras.Cola;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author galin
 */
public class Sala {
    private Queue colaSala;
    //private Cola colaSala;
    private int sillasTotales;
    private int sillasOcupadas;

    public Sala(int sillasTotales) {
        this.sillasTotales = sillasTotales;
        this.sillasOcupadas = 0;
        this.colaSala = new LinkedList<>();
    }

    public synchronized void ingresarSala() throws InterruptedException {
        colaSala.add(Thread.currentThread());
        System.out.println("Estudiante " + Thread.currentThread().getName() + " esta haciendo cola");
        while (sillasOcupadas >= sillasTotales || (!colaSala.isEmpty() && !colaSala.peek().equals(Thread.currentThread()))) {
            this.wait();
            //hace cola para entrar  
        }
        colaSala.remove();
        System.out.println("Estudiante " + Thread.currentThread().getName() + " ocupo una silla");
        sillasOcupadas++;
    }

    public synchronized void salirSala() {
        System.out.println("Estudiante " + Thread.currentThread().getName() + " desocupo una silla--------------------------");
        sillasOcupadas--;
        //se liberan en orden
        this.notifyAll();
    }

}
