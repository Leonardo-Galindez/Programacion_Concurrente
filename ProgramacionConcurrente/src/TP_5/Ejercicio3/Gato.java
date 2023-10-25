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
public class Gato implements Runnable {

    private Comedor comedor;
    private static final String tipo = "G";

    public Gato(Comedor comedor) {
        this.comedor = comedor;
    }

    public void run() {
        comedor.ingresarComedor(tipo);     
        comedor.comerGato(tipo);
        comiendo();
        comedor.finalizarComerGato();
    }

    public void comiendo() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
            System.out.println("Comiendo...");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void esperando() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public String getTipo() {
        return tipo;
    }
}
