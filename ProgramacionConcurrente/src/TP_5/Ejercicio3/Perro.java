/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio3;

import java.util.Random;

/**
 *
 * @author galin
 */
public class Perro implements Runnable {

    private Comedor comedor;

    public Perro(Comedor comedor) {
        this.comedor = comedor;
    }

    public void run() {
        comedor.comerPerro();
        comiendo();
        comedor.finalizarComerPerro();
    }

    public void comiendo() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
            System.out.println("Comiendo...");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
