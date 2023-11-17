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

    private Semaphore mesas;
    private Semaphore mutexSala;
    private int cantEstudiates;
    private int cantMesas;

    public Sala(int cantMesas) {
        this.cantMesas = cantMesas;
        this.mesas = new Semaphore(cantMesas, true);
        this.mutexSala = new Semaphore(1);
        this.cantEstudiates = 0;
    }

    public void ingresarSala() throws InterruptedException {
        mutexSala.acquire();
        cantEstudiates++;
        System.out.println(cantEstudiates);
        if (cantEstudiates >= cantMesas) {
            System.out.println("--El estudiante " + Thread.currentThread().getName() + " esta haciendo cola");
        }
        mutexSala.release();
        mesas.acquire();
        System.out.println("El estudiante " + Thread.currentThread().getName() + " ingreso a la sala");
        mutexSala.acquire();
        cantEstudiates--;
        mutexSala.release();
    }

    public void salirSala() throws InterruptedException {
        System.out.println("El estudiante " + Thread.currentThread().getName() + " salio de la sala");
        mutexSala.acquire();
        cantEstudiates--;
        mutexSala.release();
        mesas.release();
    }
}
