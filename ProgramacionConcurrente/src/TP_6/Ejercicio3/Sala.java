/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_6.Ejercicio3;

import Estructuras.Cola;
import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Sala {

    private int sillasOcupadas;
    private int capacidad;
    private Semaphore silla;
    private Semaphore mutexSala;
    private Cola colaEspera;

    public Sala(int capacidad) {
        this.silla = new Semaphore(capacidad);
        this.capacidad = capacidad;
        this.mutexSala = new Semaphore(1);
        this.colaEspera = new Cola();
        this.sillasOcupadas = 0;
    }

    public void ingresarSala() throws InterruptedException {
        mutexSala.acquire();        
        if (capacidad <= sillasOcupadas) {
            System.out.println("Estudiante " + Thread.currentThread().getName() + " esta haciendo cola");
            colaEspera.poner(Thread.currentThread());    
        }
        sillasOcupadas++;
        mutexSala.release();
        silla.acquire();
        mutexSala.acquire(); 
        System.out.println("Estudiante " + Thread.currentThread().getName() + " ocupo una silla");
        mutexSala.release();
    }

    public void salirSala() throws InterruptedException {
        //tendrian que entrar en orden despues
        mutexSala.acquire();
        System.out.println("Estudiante " + Thread.currentThread().getName() + " desocupo una silla");
        sillasOcupadas--;
        colaEspera.sacar();
        silla.release();
        mutexSala.release();
    }
}
