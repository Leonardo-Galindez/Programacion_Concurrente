/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio2;

import Estructuras.Cola;

/**
 *
 * @author galin
 */
public class Sala {

    private Cola colaSala;
    private int sillasTotales;
    private int sillasOcupadas;

    public Sala(int sillasTotales) {
        this.sillasTotales = sillasTotales;
        this.sillasOcupadas = 0;
        this.colaSala = new Cola();
    }

    public synchronized void ingresarSala() throws InterruptedException {
        while (sillasOcupadas >= sillasTotales) {
            this.wait();
            //hace cola para entrar
            System.out.println("Estudiante " + Thread.currentThread().getName() + " esta haciendo cola");
            colaSala.poner(Thread.currentThread());
        }
        System.out.println("Estudiante " + Thread.currentThread().getName() + " ocupo una silla");
        sillasOcupadas++;
    }

    public synchronized void salirSala() {
        System.out.println("Estudiante " + Thread.currentThread().getName() + " desocupo una silla");
        sillasOcupadas--;
        //se liberan en orden
        colaSala.poner(Thread.currentThread());
        this.notify();
    }
    
}
