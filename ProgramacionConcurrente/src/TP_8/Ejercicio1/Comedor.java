/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_8.Ejercicio1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Comedor {

    private Semaphore comedor;
    private Semaphore vandejas;
    private Semaphore abridores;
    private Semaphore postres;

    public Comedor(int capacidadSoldados, int capacidadVandejas, int capacidadAbridores, int capacidadPostres) {
        this.comedor = new Semaphore(capacidadSoldados, true);
        this.vandejas = new Semaphore(capacidadVandejas, true);
        this.abridores = new Semaphore(capacidadAbridores, true);
        this.postres = new Semaphore(capacidadPostres, true);
    }

    public void ingresarComedor() throws InterruptedException {
        comedor.acquire();
        System.out.println("El soldado " + Thread.currentThread().getName() + " ingreso al comedor");
    }

    public void tomarVandeja() throws InterruptedException {
        vandejas.acquire();
        System.out.println("El soldado " + Thread.currentThread().getName() + " tomo una vandeja");
    }

    public void tomarAbridor() throws InterruptedException {
        abridores.acquire();
        System.out.println("El soldado " + Thread.currentThread().getName() + " tomo un abridor");
    }

    public void tomarPostre() throws InterruptedException {
        postres.acquire();
        System.out.println("El soldado " + Thread.currentThread().getName() + " tomo un abridor");
    }

    public void salirComedor() {
        System.out.println("El soldado " + Thread.currentThread().getName() + " salio del comedor");
        comedor.release();
    }

    public void dejarVandeja() {
        System.out.println("El soldado " + Thread.currentThread().getName() + " dejo la vandeja");
        vandejas.release();
    }

    public void dejarAbridor() {
        System.out.println("El soldado " + Thread.currentThread().getName() + " dejo el abridor");
        abridores.release();
    }

    public void dejarPostre() {
        System.out.println("El soldado " + Thread.currentThread().getName() + " dejo el postre");
        postres.release();
    }

}
