/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio3;

import java.util.Random;

/**
 *
 * @author galin
 */
public class Hamster implements Runnable {

    private String nombre;
    private Jaula jaula;

    public Hamster(Jaula jaula, String nombre) {
        this.jaula = jaula;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        //Como usar cualquier elemento de la jaula
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(3) + 1;
        switch (numeroAleatorio) {
            case 1:
                jaula.comerComida();
                jaula.usarHamaca();
                jaula.usarRueda();
                break;
            case 2:
                jaula.usarHamaca();
                jaula.usarRueda();
                jaula.comerComida();
                break;
            case 3:
                jaula.usarRueda();
                jaula.comerComida();
                jaula.usarHamaca();
                break;
        }
    }

    @Override
    public String toString() {
        return "Hamster{" + "nombre=" + nombre + '}';
    }

}
